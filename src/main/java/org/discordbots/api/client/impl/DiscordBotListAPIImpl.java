package org.discordbots.api.client.impl;

import com.fatboyindustrial.gsonjavatime.OffsetDateTimeConverter;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import okhttp3.*;
import org.discordbots.api.client.DiscordBotListAPI;
import org.discordbots.api.client.entity.*;
import org.discordbots.api.client.io.DefaultResponseTransformer;
import org.discordbots.api.client.io.ResponseTransformer;
import org.discordbots.api.client.io.UnsuccessfulHttpException;
import org.json.JSONObject;

import java.io.IOException;
import java.time.OffsetDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;
import java.util.stream.Collectors;

public class DiscordBotListAPIImpl implements DiscordBotListAPI {
    
    /**
    * The HttpUrl builder for top.gg
    */
    private static final HttpUrl baseUrl = new HttpUrl.Builder()
            .scheme("https")
            .host("top.gg")
            .addPathSegment("api")
            .build();
    
    /**
    * The OkHttpClient
    */
    private final OkHttpClient httpClient;
    /**
    * The Gson
    */
    private final Gson gson;
    /**
    * The token and botId
    */
    private final String token, botId;
    
    /**
    * The implementation of the DBLAPI
    *
    * @param token the bots token retrieved from the webhooks page
    * @param botId the bots id
    */
    public DiscordBotListAPIImpl(String token, String botId) {
        this.token = token;
        this.botId = botId;

        this.gson = new GsonBuilder()
                .registerTypeAdapter(OffsetDateTime.class, new OffsetDateTimeConverter())
                .create();

        this.httpClient = new OkHttpClient.Builder()
                .addInterceptor((chain) -> {
                    Request req = chain.request().newBuilder()
                            .addHeader("Authorization", this.token)
                            .build();
                    return chain.proceed(req);
                })
                .build();
    }
    /**
    * Sets the stats of your bot
    *
    * @param shardId the shard id
    * @param shardTotal the shard total
    * @param serverCount the server count
    */
    public CompletionStage<Void> setStats(int shardId, int shardTotal, int serverCount) {
        JSONObject json = new JSONObject()
                .put("shard_id", shardId)
                .put("shard_count", shardTotal)
                .put("server_count", serverCount);

        return setStats(json);
    }
    /**
    * Sets the stats of your bot
    *
    * @param shardServerCounts the shard server counts
    */
    public CompletionStage<Void> setStats(List<Integer> shardServerCounts) {
        JSONObject json = new JSONObject()
                .put("shards", shardServerCounts);

        return setStats(json);
    }
    /**
    * Sets the stats of your bot
    *
    * @param serverCount the server count
    */
    public CompletionStage<Void> setStats(int serverCount) {
        JSONObject json = new JSONObject()
                .put("server_count", serverCount);

        return setStats(json);
    }
    /**
    * Sets the stats of your bot
    *
    * @param jsonBody sets the bots stats from a JSON file
    */
    private CompletionStage<Void> setStats(JSONObject jsonBody) {
        HttpUrl url = baseUrl.newBuilder()
                .addPathSegment("bots")
                .addPathSegment(botId)
                .addPathSegment("stats")
                .build();

        return post(url, jsonBody, Void.class);
    }
    /**
    * Gets the stats of your bot
    *
    * @param botId the bots id
    *
    * @return CompletionStage<BotStats>, the bots stats
    */
    public CompletionStage<BotStats> getStats(String botId) {
        HttpUrl url = baseUrl.newBuilder()
                .addPathSegment("bots")
                .addPathSegment(botId)
                .addPathSegment("stats")
                .build();

        return get(url, BotStats.class);
    }
    /**
    * Gets the voters for your bot
    *
    * @param botId the bots id
    *
    * @return CompletionStage<List<SimpleUser>>, the bots voters
    */
    public CompletionStage<List<SimpleUser>> getVoters(String botId) {
        HttpUrl url = baseUrl.newBuilder()
                .addPathSegment("bots")
                .addPathSegment(botId)
                .addPathSegment("votes")
                .build();

        return get(url, resp -> {
            // This is kinda awkward but this is done so that it can return it was a list instead of
            // an array
            ResponseTransformer<SimpleUser[]> arrayTransformer = new DefaultResponseTransformer<>(SimpleUser[].class, gson);
            return Arrays.asList(arrayTransformer.transform(resp));
        });
    }
    /**
    * Gets your bot
    *
    * @param botId the bots id
    *
    * @return CompletionStage<Bot>, the bot
    */
    public CompletionStage<Bot> getBot(String botId) {
        HttpUrl url = baseUrl.newBuilder()
                .addPathSegment("bots")
                .addPathSegment(botId)
                .build();

        return get(url, Bot.class);
    }
    /**
    * Gets the bot
    *
    * @param search A search string in the format of field: value field2: value2
    * @param limit The amount of bots to return. Max. 500
    * @param offset Amount of bots to skip
    *
    * @return CompletionStage<BotResult>, A list of bots
    */
    public CompletionStage<BotResult> getBots(Map<String, String> search, int limit, int offset) {
        return getBots(search, limit, offset, null);
    }
    /**
    * Gets the bot
    *
    * @param search A search string in the format of field: value field2: value2
    * @param limit The amount of bots to return. Max. 500
    * @param offset Amount of bots to skip
    * @param sort The field to sort by. Prefix with - to reverse the order
    *
    * @return CompletionStage<BotResult>, A list of bots
    */
    public CompletionStage<BotResult> getBots(Map<String, String> search, int limit, int offset, String sort) {
        return getBots(search, limit, offset, sort, null);
    }
    /**
    * Gets the bot
    *
    * @param search A search string in the format of field: value field2: value2
    * @param limit The amount of bots to return. Max. 500
    * @param offset Amount of bots to skip
    * @param sort The field to sort by. Prefix with - to reverse the order
    * @param fields A comma separated list of fields to show
    *
    * @return CompletionStage<BotResult>, A list of bots
    */
    public CompletionStage<BotResult> getBots(Map<String, String> search, int limit, int offset, String sort, List<String> fields) {
        // DBL search uses this format: field1: value1 field2: value2
        String searchString = search.entrySet().stream()
                .map(entry -> entry.getKey() + ": " + entry.getValue())
                .collect(Collectors.joining(" "));

        HttpUrl.Builder urlBuilder = baseUrl.newBuilder()
                .addPathSegment("bots")
                .addQueryParameter("search", searchString)
                .addQueryParameter("limit", String.valueOf(limit))
                .addQueryParameter("offset", String.valueOf(offset));

        if(sort != null) {
            urlBuilder.addQueryParameter("sort", sort);
        }

        if(fields != null) {
            String fieldsString = fields.stream()
                    .collect(Collectors.joining(" "));

            urlBuilder.addQueryParameter("fields", fieldsString);
        }

        return get(urlBuilder.build(), BotResult.class);
    }
    /**
    * Gets the user
    *
    * @param userId the id
    *
    * @return CompletionStage<User>, the user
    */
    public CompletionStage<User> getUser(String userId) {
        HttpUrl url = baseUrl.newBuilder()
                .addPathSegment("users")
                .addPathSegment(userId)
                .build();

        return get(url, User.class);
    }
    /**
    * If the user has voted
    *
    * @param userId the id
    *
    * @return CompletionStage<Boolean>, if voted
    */
    public CompletionStage<Boolean> hasVoted(String userId) {
        HttpUrl url = baseUrl.newBuilder()
                .addPathSegment("bots")
                .addPathSegment(botId)
                .addPathSegment("check")
                .addQueryParameter("userId", userId)
                .build();

        return get(url, (resp) -> {
            JSONObject json = new JSONObject(resp.body().string());
            return json.getInt("voted") == 1;
        });
    }
    /**
    * Gets the vote multiplier
    *
    * @return CompletionStage<VotingMultiplier>, the multiplier
    */
    public CompletionStage<VotingMultiplier> getVotingMultiplier() {
        HttpUrl url = baseUrl.newBuilder()
                .addPathSegment("weekend")
                .build();

        return get(url, VotingMultiplier.class);
    }
    /**
    * Gets the Http request
    *
    * @param url the http url
    * @param aClass the class
    *
    * @return <E> CompletionStage<E>, E
    */
    private <E> CompletionStage<E> get(HttpUrl url, Class<E> aClass) {
        return get(url, new DefaultResponseTransformer<>(aClass, gson));
    }
    /**
    * Gets the Http request
    *
    * @param url the http url
    * @param responseTransformer the response
    *
    * @return <E> CompletionStage<E>, E
    */
    private <E> CompletionStage<E> get(HttpUrl url, ResponseTransformer<E> responseTransformer) {
        Request req = new Request.Builder()
                .get()
                .url(url)
                .build();

        return execute(req, responseTransformer);
    }

    // The class provided in this is kinda unneeded because the only thing ever given to it
    // is Void, but I wanted to make it expandable (maybe some post methods will return objects
    // in the future)
    /**
    * posts a Http request
    *
    * @param url the http url
    * @param jsonBody the JSON file
    * @param aClass the class
    *
    * @return <E> CompletionStage<E>, E
    */
    private <E> CompletionStage<E> post(HttpUrl url, JSONObject jsonBody, Class<E> aClass) {
        return post(url, jsonBody, new DefaultResponseTransformer<>(aClass, gson));
    }
    /**
    * posts a Http request
    *
    * @param url the http url
    * @param jsonBody the JSON file
    * @param responseTransformer the response
    *
    * @return <E> CompletionStage<E>, E
    */
    private <E> CompletionStage<E> post(HttpUrl url, JSONObject jsonBody, ResponseTransformer<E> responseTransformer) {
        MediaType mediaType = MediaType.parse("application/json");
        RequestBody body = RequestBody.create(mediaType, jsonBody.toString());

        Request req = new Request.Builder()
                .post(body)
                .url(url)
                .build();

        return execute(req, responseTransformer);
    }
    /**
    * executes a request
    *
    * @param request the http request
    * @param responseTransformer the response
    *
    * @return <E> CompletionStage<E>, E
    */
    private <E> CompletionStage<E> execute(Request request, ResponseTransformer<E> responseTransformer) {
        Call call = httpClient.newCall(request);

        final CompletableFuture<E> future = new CompletableFuture<>();

        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                future.completeExceptionally(e);
            }

            @Override
            public void onResponse(Call call, Response response) {
                try {

                    if (response.isSuccessful()) {
                        E transformed = responseTransformer.transform(response);
                        future.complete(transformed);
                    } else {
                        String message = response.message();

                        // DBL sends error messages as part of the body and leaves the
                        // actual message blank so this will just pull that instead because
                        // it's 1000x more useful than the actual message
                        if (message == null || message.isEmpty()) {
                            try {
                                JSONObject body = new JSONObject(response.body().string());
                                message = body.getString("error");
                            } catch (Exception ignored) {}
                        }

                        Exception e = new UnsuccessfulHttpException(response.code(), message);
                        future.completeExceptionally(e);
                    }

                } catch (Exception e) {
                    future.completeExceptionally(e);
                } finally {
                    response.body().close();
                }
            }
        });

        return future;
    }

}

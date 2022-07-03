package org.discordbots.api.client;

import org.discordbots.api.client.entity.*;
import org.discordbots.api.client.impl.DiscordBotListAPIImpl;

import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletionStage;

public interface DiscordBotListAPI {
    /**
    * 
    * This method is used to set the shards Id Total and the amount of servers the bot is currently in.
    *
    * @param shardId The zero-indexed id of the shard posting. Makes server_count set the shard specific server count.
    * @param shardTotal The amount of shards the bot has.
    * @param serverCount Amount of servers the bot is in. If an Array, it acts like shards.
    */
    CompletionStage<Void> setStats(int shardId, int shardTotal, int serverCount);
    /**
    * 
    * This method is used to set the shard server counts.
    *
    * @param shardServerCounts Amount of servers the bot is in. If an Array, it acts like shards.
    */
    CompletionStage<Void> setStats(List<Integer> shardServerCounts);
    /**
    * 
    * This method is used to set the total amount of servers the bot is currently in.
    *
    * @param serverCount the amount of servers the bot is in.
    */
    CompletionStage<Void> setStats(int serverCount);
    /**
    * 
    * This method is used to get the bots stats.
    *
    * @param botId The id of the bot.
    *
    * @return BotStats, the current stats of the bot.
    */
    CompletionStage<BotStats> getStats(String botId);
    /**
    * 
    * This method is used to get a list of voters for the specified bot id.
    *
    * @param botId The id of the bot.
    *
    * @deprecated
    *
    * @return List, the list of voters that have voted in the previous 12 hours.
    */
    @Deprecated
    CompletionStage<List<SimpleUser>> getVoters(String botId);
    /**
    * 
    * This method is used to check if the user provided has voted.
    *
    * @param userId User's Discord ID
    *
    * @return Boolean, if the user has voted.
    */
    CompletionStage<Boolean> hasVoted(String userId);
    /**
    * 
    * This method is used to set the total amount of servers the bot is currently in.
    *
    * @param search A search string in the format of <code>field: value field2: value2</code>.
    * @param limit The amount of bots to return. Max. 500. default of 50.
    * @param offset Amount of bots to skip.
    *
    * @return BotResult, the results of the search.
    */
    CompletionStage<BotResult> getBots(Map<String, String> search, int limit, int offset);
    /**
    * 
    * This method is used to set the total amount of servers the bot is currently in.
    *
    * @param search A search string in the format of <code>field: value field2: value2</code>.
    * @param limit The amount of bots to return. Max. 500. default of 50.
    * @param offset Amount of bots to skip.
    * @param sort The field to sort by. Prefix with - to reverse the order.
    *
    * @return BotResult, the results of the search.
    */
    CompletionStage<BotResult> getBots(Map<String, String> search, int limit, int offset, String sort);
    /**
    * 
    * This method is used to set the total amount of servers the bot is currently in.
    *
    * @param search A search string in the format of <code>field: value field2: value2</code>.
    * @param limit The amount of bots to return. Max. 500. default of 50.
    * @param offset Amount of bots to skip.
    * @param sort The field to sort by. Prefix with - to reverse the order.
    * @param fields A comma separated list of fields to show.
    *
    * @return BotResult, the results of the search.
    */
    CompletionStage<BotResult> getBots(Map<String, String> search, int limit, int offset, String sort, List<String> fields);
    /**
    * 
    * Finds a single bot.
    *
    * @param botId The id of the bot.
    * 
    * @return Bot, Bot or 404
    */
    CompletionStage<Bot> getBot(String botId);
    /**
    * 
    * This method is used to get A user that represents a User account in top.gg. Which is not associated with any other platform like Discord.
    *
    * @param userId The id of the user
    * 
    * @return User, A user represents a User account in top.gg. It is not associated with any other platform like Discord.
    */
    CompletionStage<User> getUser(String userId);
    /**
    * 
    * Gets the voting multiplier 
    *
    * @return VotingMultiplier
    */
    CompletionStage<VotingMultiplier> getVotingMultiplier();

    class Builder {

        // Required
        /**
        * The id of the bot to be used for the api.
        * <p>This is a required field if not provided the api will not work.</p>
        */
        private String botId = null;
        /**
        * The token of the bot to be used for the api.
        * <p>This is a required field if not provided the api will not work.</p>
        */
        private String token = null;
        /**
        * 
        * This is the bots token object which is provied from top.gg within the webhook section of your bot.
        * 
        * @params token You will need a token to perform certain actions with the API such as posting your bot’s server count and checking who has upvoted your bot. API documentation can be found <a href="https://docs.top.gg/">here</a>.
        */
        public Builder token(String token) {
            this.token = token;
            return this;
        }
        /**
        * 
        * This is the bot id object which is used to get the webhook from top.gg.
        * 
        * @params botId The id of the bot.
        */
        public Builder botId(String botId) {
            this.botId = botId;
            return this;
        }
        /**
        *
        * builds a DiscordBotListAPI object.
        *
        */
        public DiscordBotListAPI build() {
            if(token == null)
                throw new IllegalArgumentException("The provided token cannot be null!");

            if(botId == null)
                throw new IllegalArgumentException("The provided bot ID cannot be null!");

            return new DiscordBotListAPIImpl(token, botId);
        }

    }

}

package org.discordbots.api.client.io;

import com.google.gson.Gson;
import okhttp3.Response;

import java.io.IOException;

public class DefaultResponseTransformer<E> implements ResponseTransformer<E> {

    /**
    * A class
    */
    private final Class<E> aClass;
    /**
    * A Gson
    */
    private final Gson gson;
    /**
    * A response transformer
    *
    * @param aClass A class
    * @param gson A Gson
    */
    public DefaultResponseTransformer(Class<E> aClass, Gson gson) {
        this.aClass = aClass;
        this.gson = gson;
    }
    /**
    * The transform
    *
    * @param response The Response
    *
    * @throws IOException
    *
    * @return E
    */
    @Override
    public E transform(Response response) throws IOException {
        String body = response.body().string();
        return gson.fromJson(body, aClass);
    }

}

package org.discordbots.api.client.entity;

import com.google.gson.annotations.SerializedName;

public class Vote {
    
    /**
    * Bots Discord ID
    */
    @SerializedName("bot")
    private String botId;
    /**
    * User's Discord ID
    */
    @SerializedName("user")
    private String userId;
    /**
    * the type
    */
    private String type;
    /**
    * the query
    */
    private String query;
    /**
    * If it's the weekend
    */
    @SerializedName("isWeekend")
    private boolean weekend;



    /**
    * The bots id
    *
    * @return String, the id
    */
    public String getBotId() {
        return botId;
    }
    /**
    * The users id
    *
    * @return String, the id
    */
    public String getUserId() {
        return userId;
    }
    /**
    * The type
    *
    * @return String, the type
    */
    public String getType() {
        return type;
    }
    /**
    * The query
    *
    * @return String, the query
    */
    public String getQuery() {
        return query;
    }
    /**
    * If it's the weekend
    *
    * @return boolean, the weekend
    */
    public boolean isWeekend() {
        return weekend;
    }

}

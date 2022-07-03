package org.discordbots.api.client.entity;

import com.google.gson.annotations.SerializedName;

import java.util.Collections;
import java.util.List;

public class BotStats {
    /**
    * The bots server count
    */
    @SerializedName("server_count")
    private int serverCount;
    /**
    * The bots shards
    */
    private List<Integer> shards;
    
    
    /**
    * Gets the bots server count
    *
    * @return int, the server count
    */
    public int getServerCount() { return serverCount; }
    /**
    * The bots shards
    *
    * @return List<Integer>, the shards
    */
    public List<Integer> getShards() { return Collections.unmodifiableList(shards); }
}

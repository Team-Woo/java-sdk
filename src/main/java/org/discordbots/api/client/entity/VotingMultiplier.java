package org.discordbots.api.client.entity;

import com.google.gson.annotations.SerializedName;

public class VotingMultiplier {
    /**
    * If it's the weekend
    */
    @SerializedName("is_weekend")
    private boolean weekend;


    /**
    * If it's the weekend
    *
    * @return boolean, the weekend
    */
    public boolean isWeekend() {
        return weekend;
    }

}

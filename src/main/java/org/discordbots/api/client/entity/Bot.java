package org.discordbots.api.client.entity;

import com.google.gson.annotations.SerializedName;

import java.time.OffsetDateTime;
import java.util.List;

public class Bot {
    /**
    * The bots id.
    */
    private String id;
    /**
    * The bots client id.
    */
    @SerializedName("clientid")
    private String clientId;
    /**
    * The bots username.
    */
    private String username;
    /**
    * The bots discriminator
    */
    private String discriminator;
    /**
    * The bots avatar.
    */
    private String avatar;
    /**
    * The bots default avatar.
    */
    @SerializedName("defAvatar")
    private String defaultAvatar;
    /**
    * The bots prefix.
    */
    private String prefix;
    /**
    * The bots invite link
    */
    private String invite;
    /**
    * The bots connected website.
    */
    private String website;
    /**
    * The bots vanity
    */
    private String vanity;
    /**
    * The bots support server
    */
    private String support;
    /**
    * The bots tags
    */
    private List<String> tags;
    /**
    * The bots long description
    */
    @SerializedName("longdesc")
    private String longDescription;
    /**
    * The bots short description.
    */
    @SerializedName("shortdesc")
    private String shortDescription;
    /**
    * The bots beta description.
    */
    @SerializedName("betadesc")
    private String betaDescription;
    /**
    * If the bots certified.
    */
    @SerializedName("certifiedBot")
    private boolean certified;
    /**
    * The bots approval time.
    */
    @SerializedName("date") // rename so that the naming actually makes sense
    private OffsetDateTime approvalTime;
    /**
    * The bots server count.
    */
    @SerializedName("server_count")
    private long serverCount;
    /**
    * A list of guilds the bot is in.
    */
    private List<String> guilds;
    /**
    * A list of shards.
    */
    private List<Integer> shards;
    /**
    * The bots monthly points.
    */
    private int monthlyPoints;
    /**
    * The bots points.
    */
    private int points;
    /**
    * If the bot is legacy
    */
    private boolean legacy;
    /**
    * The bots Id
    *
    * @return String, the bots id
    */
    public String getId() {
        return id;
    }
    /**
    * The bots client id.
    *
    * @return String, the client id
    */
    public String getClientId() {
        return clientId;
    }
    /**
    * The bots username.
    *
    * @return String, the username
    */
    public String getUsername() {
        return username;
    }
    /**
    * The bots discriminator.
    *
    * @return String, the discriminator
    */
    public String getDiscriminator() {
        return discriminator;
    }
    /**
    * The bots avatar.
    *
    * @return String, the avatar
    */
    public String getAvatar() {
        return avatar;
    }
    /**
    * The bots default avatar.
    *
    * @return String, the default avatar
    */
    public String getDefaultAvatar() {
        return defaultAvatar;
    }
    /**
    * The bots prefix.
    *
    * @return String, the prefix
    */
    public String getPrefix() {
        return prefix;
    }
    /**
    * The bots invite.
    *
    * @return String, the invite link
    */
    public String getInvite() {
        return invite;
    }
    /**
    * The bots website.
    *
    * @return String, the website url
    */
    public String getWebsite() {
        return website;
    }
    /**
    * The bots vanity.
    *
    * @return String, the vanity
    */
    public String getVanity() {
        return vanity;
    }
    /**
    * The bots support server.
    *
    * @return String, the support server
    */
    public String getSupport() {
        return support;
    }
    /**
    * The bots tags.
    *
    * @return List<String>, the tags
    */
    public List<String> getTags() {
        return tags;
    }
    /**
    * The bots long description.
    *
    * @return String, the long description
    */
    public String getLongDescription() {
        return longDescription;
    }
    /**
    * The bots short description.
    *
    * @return String, the short description
    */
    public String getShortDescription() {
        return shortDescription;
    }
    /**
    * The bots beta description.
    *
    * @return String, the beta description
    */
    public String getBetaDescription() {
        return betaDescription;
    }
    /**
    * If the bot is certified.
    *
    * @return boolean, if the bot is certified
    */
    public boolean isCertified() {
        return certified;
    }
    /**
    * The bots approval time.
    *
    * @return OffsetDateTime, the approval time
    */
    public OffsetDateTime getApprovalTime() {
        return approvalTime;
    }
    /**
    * The bots server count.
    *
    * @return long, the server count
    */
    public long getServerCount() {
        return serverCount;
    }
    /**
    * The bots guilds.
    *
    * @return List<String>, the guilds the bot is in
    */
    public List<String> getGuilds() {
        return guilds;
    }
    /**
    * The bots shards.
    *
    * @return List<Integer>, the bots shards
    */
    public List<Integer> getShards() {
        return shards;
    }
    /**
    * The bots monthly point count.
    *
    * @return int, the monthly point count
    */
    public int getMonthlyPoints() {
        return monthlyPoints;
    }
    /**
    * The bots points.
    *
    * @return int, the point count of the bot
    */
    public int getPoints() {
        return points;
    }
    /**
    * If the bot is legacy.
    *
    * @return boolean, if the bot is legacy
    */
    public boolean isLegacy() {
        return legacy;
    }

}

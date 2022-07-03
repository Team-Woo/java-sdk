package org.discordbots.api.client.entity;

import com.google.gson.annotations.SerializedName;

public class User extends SimpleUser {
    
    /**
    * The cdn hash of the user's avatar if the user has none
    */
    @SerializedName("defAvatar")
    private String defaultAvatar;
    /**
    * if the user is either an admin, mod or a webMod
    *
    * <p>admin - The admin status of the user</p>
    * <p>mod - The mod status of the user</p>
    * <p>webMod - The website moderator status of the user</p>
    */
    private boolean admin, mod, webMod;
    /**
    * if the user is either an artist, certified dev or a supporter
    *
    * <p>artist - The supporter status of the user</p>
    * <p>certifiedDev - The certified status of the user</p>
    * <p>supporter - The supporter status of the user</p>
    */
    private boolean artist, certifiedDev, supporter;
    /**
    * social - The social usernames of the user
    *
    * <p>social.youtube? - The youtube username of the user</p>
    * <p>social.reddit? - The reddit username of the user</p>
    * <p>social.twitter? - The twitter username of the user</p>
    * <p>social.instagram? - The instagram username of the user</p>
    * <p>social.github? - The github username of the user</p>
    */
    private Social social;


    /**
    * The users default avatar
    *
    * @return String, the default avatar
    */
    public String getDefaultAvatar() {
        return defaultAvatar;
    }
    /**
    * If the user is an admin
    *
    * @return boolean, is an admin
    */
    public boolean isAdmin() {
        return admin;
    }
    /**
    * If the user is a mod
    *
    * @return boolean, is a mod
    */
    public boolean isMod() {
        return mod;
    }
    /**
    * If the user is a webMod
    *
    * @return boolean, is a webMod
    */
    public boolean isWebMod() {
        return webMod;
    }
    /**
    * If the user is an artist
    *
    * @return boolean, is an artist
    */
    public boolean isArtist() {
        return artist;
    }
    /**
    * If the user is a certifiedDev
    *
    * @return boolean, is a certifiedDev
    */
    public boolean isCertifiedDev() {
        return certifiedDev;
    }
    /**
    * If the user is a Supporter
    *
    * @return boolean, is a Supporter
    */
    public boolean isSupporter() {
        return supporter;
    }
    /**
    * The users socials
    *
    * @return Social, the socials
    */
    public Social getSocial() {
        return social;
    }

}

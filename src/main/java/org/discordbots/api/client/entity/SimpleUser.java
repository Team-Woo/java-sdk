package org.discordbots.api.client.entity;

public class SimpleUser {
    /**
    * the users id
    */
    private String id;
    /**
    * the users username
    */
    private String username;
    /**
    * the users discriminator
    */
    private String discriminator;
    /**
    * the users avatar
    */
    private String avatar;


    /**
    * the users id
    *
    * @return String, the id
    */
    public String getId() {
        return id;
    }
    /**
    * the users username
    *
    * @return String, the username
    */
    public String getUsername() {
        return username;
    }
    /**
    * the users discriminator
    *
    * @return String, the discriminator
    */
    public String getDiscriminator() {
        return discriminator;
    }
    /**
    * the users avatar
    *
    * @return String, the avatar
    */
    public String getAvatar() {
        return avatar;
    }

}

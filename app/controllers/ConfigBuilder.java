package controllers;

import twitter4j.conf.ConfigurationBuilder;

/**
 * Created by arpit on 20/8/14.
 */
public class ConfigBuilder {
    static final ConfigurationBuilder getConfigBuilder() {
        ConfigurationBuilder cb = new ConfigurationBuilder();
        cb.setDebugEnabled(true)
                .setOAuthConsumerKey("pkgyqwnkhNPgDWTdKa9IysVCa")
                .setOAuthConsumerSecret("dAvIhZvLkqcrbwaBQFoRQGV37YVjOCu9KQ6fCc4bQmcZ6dDTri")
                .setOAuthAccessToken("1717837063-fPYom7nBq0X4kU2iiEuM8ws4ZL7nz4YmGdOAig6")
                .setOAuthAccessTokenSecret("biZfHMiRXCo5RVgTeJiGRAJjwIamKgRQ4rirYdEVzKgsP");
        return cb;
    }
}

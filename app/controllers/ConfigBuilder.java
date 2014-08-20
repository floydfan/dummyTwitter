package controllers;

import twitter4j.conf.ConfigurationBuilder;

/**
 * Created by arpit on 20/8/14.
 */
public class ConfigBuilder {
    static final ConfigurationBuilder getConfigBuilder() {
        ConfigurationBuilder cb = new ConfigurationBuilder();
        cb.setDebugEnabled(true)
                .setOAuthConsumerKey("ueA9UXPs4gsnv4RsZmdbi2QIS")
                .setOAuthConsumerSecret("dlJ9mNFQKdokGVRWjRTqJnw1tgElmq2x3aGs7pokKPlfe2hQY4")
                .setOAuthAccessToken("1717837063-R6wdfSAqKV2vRYDndZ8AmhYEPvqgE7Wyh5mWd5f")
                .setOAuthAccessTokenSecret("fndfn8kKStz4LcmzH0xLGLBbq71bcFMhMg5wzw1xwkD67");
        return cb;
    }
}

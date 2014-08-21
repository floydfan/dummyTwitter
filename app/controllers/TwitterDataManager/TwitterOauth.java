package controllers.TwitterDataManager;

import twitter4j.Twitter;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;

/**
 * Created by saurabhverma on 19/8/14.
 */
public class TwitterOauth {

    private final static String API_KEY = "r5ypWt5NLASe80DA1NOTeLBvn";
    private final static String API_KEY_SECRET = "Ql65q58l50fhBswci7gyX1gpl1EEVkZxc7AlOKk852uIxTzPHM";
    private final static String ACCESS_TOKEN = "59098581-V6xwDcTtezFgIsWgH7DlcDA0I5XzjAWDA6Hs80Lkk";
    private final static String ACCESS_TOKEN_SECRET = "J09Exgk9VM7u2nsUY4KONoASr7pkHIkokXLKKwRyJjLG3";

    public static Twitter getTwitterInstance()
    {
        ConfigurationBuilder cb = new ConfigurationBuilder();
        cb.setDebugEnabled(true)
                .setOAuthConsumerKey(API_KEY)
                .setOAuthConsumerSecret(API_KEY_SECRET)
                .setOAuthAccessToken(ACCESS_TOKEN)
                .setOAuthAccessTokenSecret(ACCESS_TOKEN_SECRET);

        return new TwitterFactory(cb.build()).getInstance();
    }
}

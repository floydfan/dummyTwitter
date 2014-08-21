package controllers.TwitterDataManager;

import twitter4j.Query;
import twitter4j.QueryResult;
import twitter4j.Twitter;
import twitter4j.TwitterException;

import java.sql.SQLException;
import java.util.logging.Logger;

/**
 * Created by saurabhverma on 10/8/14.
 */
public class TweetFetcher {

    private Twitter twitter;
    private TweetDumper tweetDumper;
    private play.Logger.ALogger logger;

    public TweetFetcher() throws SQLException, ClassNotFoundException {
        twitter = TwitterOauth.getTwitterInstance();
        tweetDumper = new TweetDumper();
        logger = play.Logger.of("application");
    }

    public void fetchTweets() throws TwitterException {
        Query query = new Query("@htc");
        query.setCount(100);

        System.out.println("showing search results");

        long id= Long.MAX_VALUE;

        int total_tweet=0;

        for(int i=0;i<450;i++)
        {
            query.setMaxId(id-1);
            QueryResult result = twitter.search(query);
            total_tweet += result.getTweets().size();
            System.out.println(total_tweet);

            if(result.getTweets().size()>0) {

                try {
                    id = tweetDumper.dumpToDatabase(result ,"@htc");
                } catch (Exception e) {
                    logger.debug(e.toString());
                }
            }
        }
    }
}
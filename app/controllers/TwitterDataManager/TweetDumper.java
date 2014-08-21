package controllers.TwitterDataManager;

import play.Logger;
import twitter4j.HashtagEntity;
import twitter4j.QueryResult;
import twitter4j.Status;

import java.sql.*;

/**
 * Created by saurabhverma on 19/8/14.
 */

public class TweetDumper {
	private final String allTweetsTable = "Tweet_Info";
    private final String brandTweetsTable = "Brand_Tweet";
    private final String twitterHandlesTable = "Twitter_Handle";
    private final String hashTagsTable = "Hash_Tags";
    private final String topicsTable = "Topics";
    private Connection dbConnection = null;
    private PreparedStatement preparedStatement = null;

    TweetDumper() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.jdbc.Driver");
        dbConnection = DriverManager.getConnection("jdbc:mysql://localhost/myApp", "root", "root");
	    initialize();
    }

	private void initialize() throws SQLException{
		if(!tableExists(allTweetsTable)) createTweetsTable();
	}

	private void createTweetsTable() throws SQLException{

		String query = "Create Table "+ allTweetsTable +
		" (Tweet_ID BIGINT(20) PRIMARY KEY NOT NULL," +
		"Tweet_Text VARCHAR(145),Time_Stamp TIMESTAMP NOT NULL," +
		"Retweet_Count INT(11),Longitude DOUBLE,Latitude DOUBLE," +
		"Sentiment_Score DOUBLE,User_ID BIGINT(20),Is_Retweet BIT(1))";
		preparedStatement = dbConnection.prepareStatement(query);
		preparedStatement.execute();
	}

    private void dumpTweet_Info(Status tweet)  {

        try {
            String query = "insert into Tweet_Info"
                    + "(Tweet_ID,Tweet_Text,Time_Stamp,Retweet_Count,Longitude,Latitude,/*Sentiment_Score,*/User_ID,Is_Retweet)"
                    + "values(?,?,?,?,?,?,?,/*?,*/?)";

            preparedStatement = dbConnection.prepareStatement(query);
            preparedStatement.setLong(1, tweet.getId());
            preparedStatement.setString(2, tweet.getText());
            preparedStatement.setTimestamp(3, new Timestamp(tweet.getCreatedAt().getTime()));
            preparedStatement.setInt(4, tweet.getRetweetCount());

            if (tweet.getGeoLocation() != null) {
                preparedStatement.setDouble(5, tweet.getGeoLocation().getLongitude());
                preparedStatement.setDouble(6, tweet.getGeoLocation().getLatitude());
            } else {
                preparedStatement.setDouble(5, 0.0);
                preparedStatement.setDouble(6, 0.0);
            }
            //preparedStatement.setDouble(7,);
            preparedStatement.setLong(7, tweet.getUser().getId()); //change value
            preparedStatement.setBoolean(8, tweet.isRetweet());   //change value
            preparedStatement.execute();
        } catch (SQLException e) {
            Logger.debug(e.toString());
        }
    }

    private boolean tweetExists(Status tweet) throws SQLException{
        String query = "Select * from Tweet_info where Tweet_Id = ?";
        preparedStatement = dbConnection.prepareStatement(query);
        preparedStatement.setLong(1, tweet.getId());
        ResultSet result = preparedStatement.executeQuery(query);
        return resultExists(result);

    }

    private void dumpBrand_Tweet(String brandName ,Status tweet) {

        try {
            String query = "insert into Brand_Tweet"
                    + "(Screen_Name,Tweet_ID)"
                    + "values(?,?)";

            preparedStatement = dbConnection.prepareStatement(query);
            preparedStatement.setString(1, brandName);
            preparedStatement.setLong(2, tweet.getId());
            preparedStatement.execute();

        } catch (SQLException e) {
            Logger.error(e.toString());
        }
    }

    private void dumpHash_Tags(Status tweet)
    {
        try {
            String query = "insert into Hash_Tags "
                            + "(Hash_Tag,Tweet_ID)"
                            + "values(?,?)";

            HashtagEntity[] hashtagEntities = tweet.getHashtagEntities();

            for(HashtagEntity hashtagEntity : hashtagEntities)
            {
                preparedStatement = dbConnection.prepareStatement(query);
                preparedStatement.setString(1, hashtagEntity.getText());
                preparedStatement.setLong(2, tweet.getId());
                preparedStatement.execute();
            }
        } catch (SQLException e) {
            Logger.error(e.toString());
        }
    }

	private boolean tableExists(String table) throws SQLException{
		String query = "show tables like '" + table +"'";
		preparedStatement = dbConnection.prepareStatement(query);
		ResultSet result = preparedStatement.executeQuery();
        return resultExists(result);
	}

    private boolean resultExists(ResultSet result) throws SQLException {
        result.last();
        return result.getRow() > 0;
    }

    public long dumpToDatabase(QueryResult result,String brandName) {

        long minId = Long.MAX_VALUE;

        for(Status tweet : result.getTweets())
        {
            minId = Math.min(minId,tweet.getId());
            dumpTweet_Info(tweet);
            //dumpBrand_Tweet(brandName,tweet);
            //dumpHash_Tags(tweet);
        }

        return minId;
    }

    public void dumpFollowers()
    {

    }
}

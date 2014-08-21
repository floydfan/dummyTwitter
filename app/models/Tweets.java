package models;

import javax.persistence.*;
import play.db.ebean.Model;

import java.sql.Timestamp;

/**
 * Created by arpit on 18/8/14.
 */
@Entity
public class Tweets extends Model{
    @Id
    public Long tweet_id;

    public String tweet_text;

    @Column(nullable = false)
    public Timestamp timestamp;

    public int retweet_count;

    public double longitude;

    public double latitude;

    public double sentiment_score;

    public int favorite_count;

    public boolean is_retweet;
}

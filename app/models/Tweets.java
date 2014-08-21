package models;

import javax.persistence.*;
import play.db.ebean.Model;

/**
 * Created by arpit on 18/8/14.
 */
@Entity
public class Tweets extends Model{
    @Id
    public Long tweet_id;

    public String tweet_text;

    public String created_at;

    public int favorite_count;

    public double latitude;

    public double longitude;

}

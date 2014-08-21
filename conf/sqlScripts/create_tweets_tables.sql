# --- Sql script to create different tables


Create Table Tweet_Info (
  Tweet_ID BIGINT(20) PRIMARY KEY NOT NULL,
  Tweet_Text VARCHAR(145),
  Time_Stamp TIMESTAMP NOT NULL,
  Retweet_Count INT (11),
  Favorite_Count INT (11),
  Longitude DOUBLE,
  Latitude DOUBLE,
  Sentiment_Score DOUBLE,
  User_ID BIGINT(20),
  Is_Retweet BIT(1));



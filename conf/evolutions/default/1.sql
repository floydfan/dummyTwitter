# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table task (
  id                        bigint auto_increment not null,
  label                     varchar(255),
  constraint pk_task primary key (id))
;

create table tweets (
  tweet_id                  bigint auto_increment not null,
  tweet_text                varchar(255),
  created_at                varchar(255),
  favorite_count            integer,
  latitude                  double,
  longitude                 double,
  constraint pk_tweets primary key (tweet_id))
;




# --- !Downs

SET FOREIGN_KEY_CHECKS=0;

drop table task;

drop table tweets;

SET FOREIGN_KEY_CHECKS=1;


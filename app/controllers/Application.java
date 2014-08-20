package controllers;

import models.Task;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.Results;
import twitter4j.*;
import twitter4j.Query;
import twitter4j.auth.AccessToken;
import twitter4j.conf.ConfigurationBuilder;

import java.util.List;

public class Application extends Controller {
    static Form<Task> taskForm = Form.form(Task.class);
    //static String database = DB.getDataSource();

    public static Result index() {
        return redirect(routes.Application.tasks());
    }

    public static Result tasks() {
        return ok(
                views.html.index.render(Task.all(), taskForm)
        );
    }

    public static Result newTask() {
        Form<Task> filledForm = taskForm.bindFromRequest();
        if (filledForm.hasErrors()) {
            return badRequest(
                    views.html.index.render(Task.all(), filledForm)
            );
        } else {
            Task.create(filledForm.get());
            return redirect(routes.Application.tasks());
        }
    }

    public static Result deleteTask(Long id) {
        Task.delete(id);
        return redirect(routes.Application.tasks());
    }

    public static Result getTweets(String searchString) throws TwitterException{
        Twitter twitter = new TwitterFactory(ConfigBuilder.getConfigBuilder()
                .build()).getInstance();
        try {
            Query query = new Query(searchString);
            QueryResult result;
            for (int i = 15; i > 0 ;i--){
                result = twitter.search(query);
                List<twitter4j.Status> tweets = result.getTweets();
                for (twitter4j.Status tweet : tweets) {
                    System.out.println("@" + tweet.getUser().getScreenName() +
                            " - " + tweet.getText());
                }
                //query = result.nextQuery();
            }
            System.exit(0);
        } catch (TwitterException te) {
            te.printStackTrace();
            System.out.println("Failed to search tweets: " + te.getMessage());
            System.exit(-1);
        }

        return Results.TODO;
    }

}

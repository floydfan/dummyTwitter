package controllers;

import controllers.TwitterDataManager.TweetFetcher;
import models.Task;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.Results;
import twitter4j.*;

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

    public static Result getTweets() throws Exception{
        TweetFetcher tweetFetcher = new TweetFetcher();
        tweetFetcher.fetchTweets();
        return ok("Fetching Tweets");

    }

    public static Result getTrends() {
        return Results.TODO;

    }

}

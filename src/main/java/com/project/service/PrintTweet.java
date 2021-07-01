package com.project.service;

import com.project.model.Tweet;
import java.util.List;

public abstract class PrintTweet {

    /**
     * Elegantly formats and prints tweets found using either search method
     *
     * @param listOfTweets List of Tweet objects queried from search methods
     */
    public static void printTweets(List<Tweet> listOfTweets) {
        for (Tweet tweet : listOfTweets) {
            System.out.println("Query Result Number #" + tweet.getQueryResultNum() + ", Tweet Number #" + tweet.getTweetNumber());
            System.out.println("@" + tweet.getStatus().getUser().getScreenName() + ":" + tweet.getStatus().getText());
            System.out.println("*************************************************************");
        }
    }

}

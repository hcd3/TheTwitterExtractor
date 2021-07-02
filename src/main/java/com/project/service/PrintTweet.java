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
            System.out.println("@" + tweet.getUserName() + ":" + tweet.getText());
            System.out.println("Date: " + tweet.getDate());
            System.out.println("*************************************************************");
        }
    }

}

package com.project.service;

import com.project.model.Tweet;
import twitter4j.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Filtered Stream: Get all the Tweets in real-time that match the search criteria you've set.
 */
public class FilteredStream {

    private final Twitter twitter;

    public FilteredStream(Twitter twitter) {
        this.twitter = twitter;
    }

    /**
     * Finds and prints real-time tweets based on the searchTerm passed
     * By default returns 5 tweets
     * Query object allows searching for certain strings while Status object represents an individual tweet
     * Reference: https://twitter4j.org/en/code-examples.html
     *
     * @param searchTerm The phrase that will be searched
     * @return           List of Tweet objects in the order they were retrieved
     */
    public List<Tweet> search(String searchTerm) {
        List<Tweet> listOfTweets = new ArrayList<>();
        try {
            // Each QueryResult will get around 15 tweets, unless otherwise assigned
            Query query = new Query(searchTerm);
            QueryResult result;
            result = twitter.search(query);
            int tweetNumber = 1;
            for (Status status : result.getTweets()) {
                if (tweetNumber <= 5) {
                    Tweet tweet = new Tweet(status);
                    listOfTweets.add(tweet);
                    tweetNumber++;
                }
            }
        } catch (TwitterException twe) {
            twe.printStackTrace();
            System.out.println("Could not find tweets due to a TwitterException error: " + twe.getErrorMessage());
        }

        return listOfTweets;
    }

    /**
     * Finds and prints real-time tweets based on the searchTerm passed
     * Returns amount of tweets specified (numOfQueryResults * numOfTweets)
     * Query object allows searching for certain strings while Status object represents an individual tweet
     * Reference: https://twitter4j.org/en/code-examples.html
     *
     * @param searchTerm        The phrase that will be searched
     * @param numOfQueryResults Amount of Query Result objects to search; each contains a max of 15 tweets
     * @param numOfTweets       Amount of tweets to search in each Query Result; does not exceed 15
     * @return                  List of Tweet objects in the order they were retrieved
     */
    public List<Tweet> search(String searchTerm, int numOfQueryResults, int numOfTweets) {
        List<Tweet> listOfTweets = new ArrayList<>();
        try {
            if (numOfQueryResults <= 0 || numOfTweets <= 0) {
                return listOfTweets;
            }
            Query query = new Query(searchTerm);
            QueryResult result;
            int queryResultNum = 1;

            do {
                result = twitter.search(query);
                int tweetNumber = 1;
                for (Status status : result.getTweets()) {
                    if (tweetNumber <= numOfTweets) {
                        Tweet tweet = new Tweet(status);
                        listOfTweets.add(tweet);
                        tweetNumber++;
                    }
                }
                queryResultNum++;
            } while ((query = result.nextQuery()) != null && queryResultNum <= numOfQueryResults);
        } catch (TwitterException twe) {
            twe.printStackTrace();
            System.out.println("Could not find tweets due to a TwitterException error: " + twe.getErrorMessage());
        }

        return listOfTweets;
    }
}

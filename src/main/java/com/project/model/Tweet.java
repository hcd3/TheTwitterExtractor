package com.project.model;

import twitter4j.Status;

/**
 * Contains the user's screen name, the text of a tweet, and information about the order it was retrieved through the
 * queryResultNum and the tweetNumber within that query.
 *
 * Will be used primarily in FilteredStream.java
 */
public class Tweet {

    // Status object representing the user's screen name and the text of the tweet
    private final Status status;
    // Current query result
    private final int queryResultNum;
    // Current tweet
    private final int tweetNumber;

    public Tweet(Status status, int queryResultNum, int tweetNumber) {
        this.status = status;
        this.queryResultNum = queryResultNum;
        this.tweetNumber = tweetNumber;
    }

    public Status getStatus() {
        return status;
    }

    public int getQueryResultNum() {
        return queryResultNum;
    }

    public int getTweetNumber() {
        return tweetNumber;
    }
}

package com.project.model;

import twitter4j.Status;

/**
 * Contains the user's screen name, the text of a tweet, and information about the order it was retrieved through the
 * queryResultNum and the tweetNumber within that query.
 *
 * Will be used primarily in FilteredStream.java
 */
public class Tweet {

    private final Status status;
    private final int queryResultNum;
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

package com.project.model;

import twitter4j.Status;
import java.util.Date;

/**
 * Contains the user's screen name, the text of a tweet, and the date & time it was tweeted
 *
 * Will be used primarily in FilteredStream.java
 */
public class Tweet {

    // Individual who made the tweet
    private final String userName;
    // Contents of the tweet
    private final String text;
    // Date the tweet was created
    private final Date date;

    public Tweet(Status status) {
        this.userName = status.getUser().getScreenName();
        this.text = status.getText();
        this.date = status.getCreatedAt();
    }

    public String getUserName() {
        return userName;
    }

    public String getText() {
        return text;
    }

    public Date getDate() {
        return date;
    }
}

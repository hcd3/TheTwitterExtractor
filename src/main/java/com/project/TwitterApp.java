package com.project;

import com.project.config.CredentialConfiguration;
import com.project.config.PropertyReader;
import com.project.model.Tweet;
import com.project.service.FilteredStream;
import com.project.service.KafkaClient;
import com.project.service.PrintTweet;
import twitter4j.*;

import java.util.List;

public class TwitterApp {

    //private final KafkaClient;

    public static void main(String args[]) {
        PropertyReader propertyReader = new PropertyReader();
        propertyReader.readPropertiesFile();

        CredentialConfiguration credentialConfig = new CredentialConfiguration(propertyReader);
        credentialConfig.buildConfig();
        TwitterFactory twitterFactory = credentialConfig.createTwitterFactory();
        Twitter twitter = twitterFactory.getInstance();

        FilteredStream filteredStream = new FilteredStream(twitter);
        List<Tweet> listOfTweets = filteredStream.search("Paul George", 17);
        PrintTweet.printTweets(listOfTweets);

        //KafkaClient kafkaClient = new KafkaClient();


    }
}

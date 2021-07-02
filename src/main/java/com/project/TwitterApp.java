package com.project;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.EnableKafka;

import com.project.model.Tweet;
import com.project.service.FilteredStream;
import com.project.service.KafkaClient;
import com.project.service.PrintTweet;

@SpringBootApplication
@EnableKafka
public class TwitterApp implements CommandLineRunner {

    @Autowired
    private KafkaClient kafkaClient;

    @Autowired
    private FilteredStream twitterService;

    public static void main(String[] args) {
        SpringApplication.run(TwitterApp.class, args);
    }

    @Override
    public void run(String... args) {
        List<Tweet> listOfTweets = twitterService.search("Paul George", 17);
        PrintTweet.printTweets(listOfTweets);

        kafkaClient.sendToTopic(listOfTweets);
    }


}

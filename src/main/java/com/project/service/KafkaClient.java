package com.project.service;

import com.project.model.Tweet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class KafkaClient {

    private final String topic;
    private final KafkaTemplate<String, Tweet> kafkaTemplate;

    /**
     * The constructor
     *
     * @param topic         Kafka topic tweets will be sent to
     * @param kafkaTemplate Kafka template to wrap around
     */
    @Autowired
    public KafkaClient(@Value("${kafka.topic.twitter-extractor-topic}") String topic,
                       KafkaTemplate<String, Tweet> kafkaTemplate) {
        this.topic = topic;
        this.kafkaTemplate = kafkaTemplate;
    }

    /**
     * Acts as a wrapper function for KafkaTemplate's send() function
     * Sends (produces) tweets to Kafka topic
     *
     * @param listOfTweets The list of Tweet objects to individually send
     */
    public void sendToTopic(List<Tweet> listOfTweets) {
        for (Tweet tweet : listOfTweets) {
            kafkaTemplate.send(topic, tweet);
        }
    }

    /**
     * Consumes Tweet from Kafka topic
     *
     * @param tweet The consumed tweet
     */
    @KafkaListener(topics = "${kafka.topic.twitter-extractor-topic}")
    protected void consumeFromTopic(Tweet tweet){

    }
}

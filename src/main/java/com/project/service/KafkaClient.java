package com.project.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import com.project.model.Tweet;

@Component
public class KafkaClient {

    private final KafkaTemplate<String, Tweet> kafkaTemplate;
    @Value("${kafka.topic.twitter-extractor-topic}")
    String topic;

    /**
     * The constructor
     *
     * @param kafkaTemplate kafkaConfig that provides a kafka template.
     */
    @Autowired
    public KafkaClient(KafkaTemplate<String, Tweet> kafkaTemplate) {
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

}

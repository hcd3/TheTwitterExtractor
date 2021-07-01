package com.project.service;

import com.project.model.Tweet;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;

public class KafkaClient {

    @Value("${kafka.topic.twitter-extractor-topic}")
    private final String topic;
    private final KafkaTemplate<String, Tweet> kafkaTemplate;

    /**
     * The constructor
     *
     * @param topic         Kafka topic tweets will be sent to
     * @param kafkaTemplate Kafka template to wrap around
     */
    public KafkaClient(String topic, KafkaTemplate<String, Tweet> kafkaTemplate) {
        this.topic = topic;
        this.kafkaTemplate = kafkaTemplate;
    }

    /**
     * Acts as a wrapper function for KafkaTemplate's send() function
     * Sends Tweet to Kafka topic
     *
     * @param tweet The Tweet object to send
     */
    public void sendToTopic(Tweet tweet) {
        kafkaTemplate.send(topic, tweet);
    }
}

package com.project.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.project.service.FilteredStream;

import twitter4j.Twitter;
import twitter4j.TwitterFactory;

@Configuration
public class TwitterConfig {

    @Bean
    public FilteredStream getTwitterService() {
        PropertyReader propertyReader = new PropertyReader();
        propertyReader.readPropertiesFile();

        CredentialConfiguration credentialConfig = new CredentialConfiguration(propertyReader);
        credentialConfig.buildConfig();
        TwitterFactory twitterFactory = credentialConfig.createTwitterFactory();
        Twitter twitter = twitterFactory.getInstance();
        return new FilteredStream(twitter);
    }
}

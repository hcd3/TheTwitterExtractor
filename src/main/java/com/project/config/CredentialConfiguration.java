package com.project.config;

import com.project.config.PropertyReader;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;

public class CredentialConfiguration {

    private final PropertyReader propertyReader;
    private final ConfigurationBuilder configBuilder;

    public CredentialConfiguration(PropertyReader propertyReader) {
        this.propertyReader = propertyReader;
        this.configBuilder = new ConfigurationBuilder();
    }

    /**
     * Configures OAuth credentials for Twitter4J using Configuration Builder class
     */
    public void buildConfig() {
        this.configBuilder.setDebugEnabled(true)
                .setOAuthConsumerKey(propertyReader.getConsumerKey())
                .setOAuthConsumerSecret(propertyReader.getConsumerSecret())
                .setOAuthAccessToken(propertyReader.getAccessToken())
                .setOAuthAccessTokenSecret(propertyReader.getAccessTokenSecret());
    }

    /**
     * Creates a Twitter Factory object
     *
     * @return Twitter Factory that is used to generate a Twitter stream that can be queried, sorted, etc.
     */
    public TwitterFactory createTwitterFactory() {
        return new TwitterFactory(this.configBuilder.build());
    }
}

package com.project;

import com.project.config.CredentialConfiguration;
import com.project.config.PropertyReader;
import com.project.service.FilteredStream;
import twitter4j.*;

public class TwitterApp {

    public static void main(String args[]) {
        PropertyReader propertyReader = new PropertyReader();
        propertyReader.readPropertiesFile();

        CredentialConfiguration credentialConfig = new CredentialConfiguration(propertyReader);
        credentialConfig.buildConfig();
        TwitterFactory twitterFactory = credentialConfig.createTwitterFactory();
        Twitter twitter = twitterFactory.getInstance();

        FilteredStream filteredStream = new FilteredStream(twitter);
        filteredStream.search("Lebron", 1, 1);

    }
}

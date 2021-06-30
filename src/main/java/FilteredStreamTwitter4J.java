import twitter4j.*;
import twitter4j.conf.ConfigurationBuilder;

// Filtered Stream: Get all the Tweets in real-time that match the search criteria you've set.
public class FilteredStreamTwitter4J {

    public static void main(String args[]) {
        PropertyReader propertyReader = new PropertyReader();
        propertyReader.readPropertiesFile();

        // Configures OAuth credentials for Twitter4J
        ConfigurationBuilder cb = new ConfigurationBuilder();
        cb.setDebugEnabled(true)
                .setOAuthConsumerKey(propertyReader.getConsumerKey())
                .setOAuthConsumerSecret(propertyReader.getConsumerSecret())
                .setOAuthAccessToken(propertyReader.getAccessToken())
                .setOAuthAccessTokenSecret(propertyReader.getAccessTokenSecret());
        TwitterFactory tf = new TwitterFactory(cb.build());
        Twitter twitter = tf.getInstance();

        // Reference this site: https://twitter4j.org/en/code-examples.html
        try {
            // Query object allows you to search for certain strings
            // Each QueryResult will get around 15 tweets, unless otherwise assigned
            Query query = new Query("suns");
            QueryResult result;
            int queryResultNum = 1;
            // TODO: Catch or manage SocketTimeoutException that occasionally occurs
            do {
                result = twitter.search(query);
                int tweetNumber = 1;
                // Each Status object represents an individual tweet
                for (Status status : result.getTweets()) {
                    if (tweetNumber <= 5) {
                        System.out.println("Query Result Number #" + queryResultNum + ", Tweet Number #" + tweetNumber);
                        System.out.println("@" + status.getUser().getScreenName() + ":" + status.getText());
                        System.out.println("*************************************************************");
                        tweetNumber++;
                    }
                }
                queryResultNum++;
            } while ((query = result.nextQuery()) != null && queryResultNum < 2);
        } catch (TwitterException twe) {
            twe.printStackTrace();
            System.out.println("Could not find tweets due to a TwitterException error: " + twe.getErrorMessage());
        }

    }
}

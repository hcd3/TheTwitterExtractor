import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class PropertyReader {

    private final Properties TwitterAPI;
    private String consumerKey;
    private String consumerSecret;
    private String accessToken;
    private String accessTokenSecret;

    public PropertyReader() {
        this.TwitterAPI = new Properties();
    }

    public String getConsumerKey() {
        return consumerKey;
    }

    public String getConsumerSecret() {
        return consumerSecret;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public String getAccessTokenSecret() {
        return accessTokenSecret;
    }

    /**
     * Reads in keys & tokens from the default local properties file to avoid displaying sensitive information
     */
    public void readPropertiesFile() {
        try (FileReader in = new FileReader("src/main/resources/TwitterAPI.properties")) {
            this.TwitterAPI.load(in);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Could not read default properties file due to an IOException: " + e.getMessage());
        }
        this.consumerKey = TwitterAPI.getProperty("ConsumerKey");
        this.consumerSecret = TwitterAPI.getProperty("ConsumerSecret");
        this.accessToken = TwitterAPI.getProperty("AccessToken");
        this.accessTokenSecret = TwitterAPI.getProperty("AccessTokenSecret");
    }

    /**
     * Reads in keys & tokens from a specified local properties file to avoid displaying sensitive information
     *
     * @param fileName Name or path of local properties file
     */
    public void readPropertiesFile(String fileName) {
        try (FileReader in = new FileReader(fileName)) {
            this.TwitterAPI.load(in);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Could not read properties file due to an IOException: " + e.getMessage());
        }
        this.consumerKey = TwitterAPI.getProperty("ConsumerKey");
        this.consumerSecret = TwitterAPI.getProperty("ConsumerSecret");
        this.accessToken = TwitterAPI.getProperty("AccessToken");
        this.accessTokenSecret = TwitterAPI.getProperty("AccessTokenSecret");
    }
}
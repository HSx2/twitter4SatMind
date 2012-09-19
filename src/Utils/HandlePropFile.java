/**
 * 
 */
package Utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import TwitterSMExceptions.TwitterSMException;

/**
 * @author Yaniv Dudu
 *
 */
public class HandlePropFile {
 
	private static File file = null;
	private static Properties prop = null;
	   
	public static void init() throws TwitterSMException {
		file = new File(Consts.PROP_FILE_NAME);
        prop = new Properties();
        InputStream is = null;
        try {
            if (file.exists()) {
                is = new FileInputStream(file);
                prop.load(is);
            }

        } catch (IOException ioe) {
        	throw new TwitterSMException (ioe.getMessage());
        } finally {
            if (is != null) {
                try {
                    is.close();
                } catch (IOException ignore) {
                }
            }
        }
	}
	
	public static String getConsumerSecret() throws TwitterSMException {
	    String consumerSecret = prop.getProperty(Consts.CONSUMER_SECRET);
	    if (consumerSecret == null)
	    	throw new TwitterSMException (Consts.PROP_FILE_NAME+" Doesn't contain consumer secret");
	    
	    return consumerSecret;
	}
	
	public static String getConsumerKey() throws TwitterSMException {
	    String consumerKey = prop.getProperty(Consts.CONSUMER_KEY);
	    if (consumerKey == null)
	    	throw new TwitterSMException (Consts.PROP_FILE_NAME+" Doesn't contain consumer key");
	    
	    return consumerKey;
	}
	
	public static String getUserAccessToken(String userName) throws TwitterSMException {
	    String userAccessToken = prop.getProperty(userName+".accessToken");
	    if (userAccessToken == null)
	    	throw new TwitterSMException (Consts.PROP_FILE_NAME+" Doesn't contain access token for user:"+userName);
	    
	    return userAccessToken;
	}
	
	public static String getUserAccessTokenSecret(String userName) throws TwitterSMException {
	    String userAccessToken = prop.getProperty(userName+".accessTokenSecret");
	    if (userAccessToken == null)
	    	throw new TwitterSMException (Consts.PROP_FILE_NAME+" Doesn't contain access token secret for user:"+userName);
	    
	    return userAccessToken;
	}
	
	public static boolean propExists(String key) {
		return prop.containsKey(key);
	}
}

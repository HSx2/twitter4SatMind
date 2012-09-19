/**
 * 
 */
package User;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

import twitter4j.Paging;
import twitter4j.Status;
import twitter4j.Tweet;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.auth.AccessToken;
import twitter4j.auth.RequestToken;
import TwitterSMExceptions.NeedUserAuthException;
import TwitterSMExceptions.TwitterSMException;
import Utils.Consts;
import Utils.HandlePropFile;

/**
 * @author Yaniv Dudu
 *
 */
public class TwitterUserImp implements TwitterUser {

	protected Twitter	twitter;
	private String 		userName;
	private String 		password;
	private String	 	accessToken;
	private String	 	accessTokenSecret;
	private long		maxId;				// represent the newest tweet processed in timeLine
	private long		sinceId;			// represent the oldest tweet processed in timeLine

	/**
	 * default Constructor, will initialize the user name to "" 
	 * @throws NeedUserAuthException 
	 */
	public TwitterUserImp() throws NeedUserAuthException {
		init();
	}

	/**
	 * default Constructor, will initialize userName to name
	 * @param name - user name as appears in twitter 
	 * @throws NeedUserAuthException 
	 */
	public TwitterUserImp(String name) throws NeedUserAuthException {
		this.userName = new String(name);
		init();
	}

	private void init() throws NeedUserAuthException {
		setOauthAccessTokenAndSecret();
// IRRELEVAT
		maxId = Consts.IRRELEVANT_TWEET_ID;
		sinceId = Consts.IRRELEVANT_TWEET_ID;
	
		twitter = new TwitterFactory().getInstance();
		try {
			twitter.setOAuthConsumer(HandlePropFile.getConsumerKey(), HandlePropFile.getConsumerSecret());
		} catch (TwitterSMException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		AccessToken access = new AccessToken(accessToken, accessTokenSecret);
		twitter.setOAuthAccessToken(access);

		if (!twitter.getAuthorization().isEnabled()) {
			System.out.println("OAuth consumer key/secret is not set.");
			System.exit(-1);
		}

	}

	public TwitterUserImp(String userName, String password) throws NeedUserAuthException {
		this.password = new String(password);
		this.userName = new String(userName);
		init();
	}

	@Override
	public String getUserName() {
		return this.userName;
	}

	private void setOauthAccessTokenAndSecret() throws NeedUserAuthException {
		try {
			accessToken = HandlePropFile.getUserAccessToken(userName);
			accessTokenSecret = HandlePropFile.getUserAccessTokenSecret(userName);
		} catch (TwitterSMException e) {
			throw new NeedUserAuthException ("user: "+userName+" need authentication");
		}
	}

	public String getOauthAccessTokenSecret() {
		return this.accessTokenSecret; 
	}

	public String getOauthAccessToken() {
		return this.accessToken;
	}

	/* 
	 * Each page contain 20 tweets.
	 * The method returns the specified page of recent statuses, including retweets, 
	 * posted by the authenticating user and that user's friends. 
	 * 
	 * This is the equivalent of /timeline/home on the Web.
	 * Usage note: This home_timeline call is identical to statuses/friends_timeline,
	 * except that home_timeline also contains retweets,
	 * while statuses/friends_timeline does not for backwards compatibility reasons. 
	 * 
	 * This method calls http://api.twitter.com/1/statuses/home_timeline
	 */
	public String getHomeTimeLine(int amount) throws TwitterSMException {
		String homeTimeLine = null;
		Paging page;
		if (maxId != Consts.IRRELEVANT_TWEET_ID && sinceId != Consts.IRRELEVANT_TWEET_ID) {	
			System.out.println("HERE second");
			page = new Paging(1,amount, sinceId, maxId);
		}
		else if (maxId == Consts.IRRELEVANT_TWEET_ID || sinceId == Consts.IRRELEVANT_TWEET_ID) {
			page = new Paging(1,amount);
			System.out.println("HERE at first");
		}
		else
			throw new TwitterSMException("Something went wrong, maxId is:"+maxId+" and sinceId is:"+sinceId);
		
		 
		List<Status> statuses;
		try {
			statuses = twitter.getHomeTimeline(page);
			for (Status status : statuses) {
				
				if (maxId < status.getId())
					maxId = status.getId();
				
				if (sinceId == Consts.IRRELEVANT_TWEET_ID || sinceId > status.getId())
					sinceId = status.getId();
				
				System.out.println("ID:"+status.getId()+"@" + status.getUser().getScreenName() + "-" + status.getText());
				homeTimeLine += "@" + status.getUser().getScreenName() + "-" + status.getText()+"\n";
			}
		} catch (TwitterException e) {
			throw new TwitterSMException(e.getMessage());
		}
		return homeTimeLine;
	}

	@Override
	public String getMentionedTweets(int pageNum) throws TwitterSMException {
		String mentionedTweets = null;
		Paging page = new Paging(pageNum);
		List<Status> statuses;
		try {
            statuses = twitter.getMentions(page);
            System.out.println("Showing mentions.");
            for (Status status : statuses) {
                System.out.println("@" + status.getUser().getScreenName() + " - " + status.getText());
                mentionedTweets +="@" + status.getUser().getScreenName() + " - " + status.getText()+"\n";
            }
		} catch (TwitterException e) {
			throw new TwitterSMException(e.getMessage());
		}
		return mentionedTweets;
	}

	@Override
	public String getMyLastTweets(int numOfTweets) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getNumOfUnreadTweets() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getMyFollowingList(int from, int to) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getMyFollowersList(int from, int to) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getExpendedTweetConversation(String tweetId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getDirectMessages() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean updateStatus(String strStatus) {
		Status status = null;
		try {
			status = twitter.updateStatus(strStatus);
			System.out.println("Successfully updated the status to [" + status.getText() + "].");
		} catch (TwitterException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		return true;
	}

	@Override
	public boolean deleteRecentlyPostedMessage(String messageId) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean SendDirectMessage(String recipient, String message) {
		// TODO Auto-generated method stub
		return false;
	}

}

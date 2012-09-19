package User;

import TwitterSMExceptions.TwitterSMException;

public interface TwitterUser {
	
	// Getters:
	public String getUserName();
	public String getHomeTimeLine(int amount) throws TwitterSMException;
	public String getMentionedTweets(int pageNum) throws TwitterSMException;
	public String getMyLastTweets(int pageNum);
	public String getNumOfUnreadTweets();
	public String getMyFollowingList(int from, int to);
	public String getMyFollowersList(int from, int to);
	public String getExpendedTweetConversation(String tweetId);
	public String getDirectMessages();
	
	// Setters:
	public boolean updateStatus(String status);
	public boolean deleteRecentlyPostedMessage(String messageId);
	public boolean SendDirectMessage(String recipient, String message);
	

	
}

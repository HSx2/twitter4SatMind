package User;

import TwitterSMExceptions.NeedUserAuthException;
import TwitterSMExceptions.TwitterSMException;

public class testTwitterUser {

	static TwitterUser twitterUser;
	static TwitterUser secTwitterUser;

	public static void testTwitterUser() {// TODO Auto-generated constructor stub

		if (testTwitterUserCreation() == false) {
			System.err.println("testTwitterUserCreation failed!");
		}
		if (testAccessTokenAndSecret() == false) {
			System.err.println("testAccessTokenAndSecret failed!");
		}
		//		if (testUpdateStatus() == false) {
		//			System.err.println("testUpdateStatus failed!");
		//		}
//		if (testgetHomeTimeLine() == false) {
//			System.err.println("testgetHomeTimeLine failed!");
//		}
		if (testgetMentioned() == false) {
			System.err.println("testgetMentioned failed!");
		}
	}

	private static boolean testgetMentioned() {
		try {
			twitterUser.getMentionedTweets(1);
		} catch (TwitterSMException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		return true;
	}

	private static boolean testgetHomeTimeLine() {
		try {
			twitterUser.getHomeTimeLine(10);
			twitterUser.getHomeTimeLine(3);
		} catch (TwitterSMException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		return true;
	}

	private static boolean testAccessTokenAndSecret() {
		if (((TwitterUserImp)twitterUser).getOauthAccessToken().equals("251581150-Gv67C4wDzjpMUMtlXjOJOyCdnIUkPEEdFdkHqncP")
				&& ((TwitterUserImp)twitterUser).getOauthAccessTokenSecret().equals("6RDkJ7yISFOwob9cIyLDMQ1eZxUPKAM7QA178DhwY"))
			return true;
		return false;
	}

	private static boolean testTwitterUserCreation() {
		try {
			twitterUser = new TwitterUserImp("YanivDudu");
			secTwitterUser = new TwitterUserImp("BoboRules");
		} catch (NeedUserAuthException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		return true;
	}
	private static boolean testUpdateStatus() {
		if (!twitterUser.updateStatus("Testing 123")) 
			return false;
		if (!secTwitterUser.updateStatus ("Testing 456"))
			return false;
		return true;
	}
}

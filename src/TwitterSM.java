import java.util.HashMap;

import TwitterSMExceptions.NeedUserAuthException;
import TwitterSMExceptions.TwitterSMException;
import User.TwitterUser;
import User.TwitterUserImp;

/**
 * 
 */

/**
 * @author Yaniv Dudu 08/09/2012
 *
 */

public class TwitterSM implements Twitter4SatMindAPI {

	private static HashMap<String, TwitterUser> twitterSMHash = new HashMap<String, TwitterUser>(Utils.Consts.HASHMAP_SIZE);
	private static boolean libInit = false;
	
	public static void init () {
		if (libInit)
			return;
		
		libInit = true;
	}
	public static void addUser (String userName, String password) throws TwitterSMException, NeedUserAuthException {

		if (twitterSMHash.containsKey(userName))
			throw new TwitterSMException("user name "+userName+" already exists");	
			
		twitterSMHash.put(userName, new TwitterUserImp(userName, password)); 
	}
	
	public static void removeUser (String userName) throws TwitterSMException {

		if (! twitterSMHash.containsKey(userName))
			throw new TwitterSMException("user name "+userName+" doesn't exists, so it cannot be removed");	
			
		twitterSMHash.remove(userName); 
	}
	
	public static TwitterUser getUserByName(String userName) throws TwitterSMException{
		TwitterUser twitterUser = twitterSMHash.get(userName);
		if (twitterUser == null)
			throw new TwitterSMException(userName+" dosn't exists");
		
		return twitterUser; 
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
	}

}

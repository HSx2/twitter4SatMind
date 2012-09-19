import TwitterSMExceptions.NeedUserAuthException;
import TwitterSMExceptions.TwitterSMException;
import User.TwitterUser;


public class testTwitterSM {

	public static void testTwitterSM() {
		if (addUserToHash() == false) {
			System.err.println("addUserToHash failed!");
		}
		if (getUserByName() == false) {
			System.err.println("getUserByName failed!");
		}	
		if (removeUser() == false) {
			System.err.println("removeUser failed!");
		}	
	}
	
	private static boolean removeUser() {
		try {
			TwitterSM.removeUser("YanivDudu");
		} catch (TwitterSMException e) {
			return false;
		}

		try {
			TwitterSM.removeUser("YanivDudu");
		} catch (TwitterSMException e) {
			return true;
		}

		return false;
	}

	private static boolean getUserByName() {
		TwitterUser name = null;
		try {
			name = TwitterSM.getUserByName("YanivDudu");
			if (!name.getUserName().equals("YanivDudu")) 
				return false;
			name = TwitterSM.getUserByName("Yossi");	
		} catch (TwitterSMException e) {
			if (e.getMessage().equals("TwitterSMException: Yossi dosn't exists"))
				return true;
			return false;
		}
		
		return false;
	}

	private static boolean addUserToHash() {
		try {
			TwitterSM.addUser("YanivDudu", "1111");
			TwitterSM.addUser("BoboRules", "1111");
			TwitterSM.addUser("YanivDudu", "1111");
		} catch (TwitterSMException e) {
			if (e.getMessage().equals("TwitterSMException: user name YanivDudu already exists")) {
				return true;
			}
			return false;
		} catch (NeedUserAuthException e) {
			System.err.println("No authentication for user YanivDudu");
			return false;
		}
		return false;
	}

}

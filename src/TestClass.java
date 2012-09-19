

import TwitterSMExceptions.TwitterSMException;
import User.TwitterUser;
import User.testTwitterUser;
import Utils.HandlePropFile;
import Utils.testHandlePropFile;


public class TestClass {
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println("Starting tests...");
		
		testHandlePropFile.testHandlePropFile();
		testTwitterSM.testTwitterSM();
		testTwitterUser.testTwitterUser();
		
		System.out.println("Tests Ended...");		
	}

}

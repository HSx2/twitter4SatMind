package Utils;

import TwitterSMExceptions.TwitterSMException;

public class testHandlePropFile {

	public static void testHandlePropFile() {
		if (testHandlePropFileGetConsumerSecret() == false) {
			System.err.println("testTwitterSMHashGetByName failed!");
		}
		if (testHandlePropFileGetConsumerKey() == false) {
			System.err.println("testTwitterSMHashGetByName failed!");
		}	
	}
	
	private static boolean testHandlePropFileGetConsumerSecret() {
		try {
			HandlePropFile.init();
			String consumerSecret = HandlePropFile.getConsumerSecret();
			if (consumerSecret.equals("T44cX4H9NmW1nqiuCq2ltLCpuHMbLc7Ef2ur2vrOczI"))
				return true;
			
		} catch (TwitterSMException e) {
			System.err.println(e.getMessage());
			return false;
		}
		return false;
	}
	
	private static boolean testHandlePropFileGetConsumerKey() {
		try {
			HandlePropFile.init();
			String consumerkey = HandlePropFile.getConsumerKey();
			if (consumerkey.equals("Kkg3z4Fdslmtracj9Jg"))
				return true;
			
		} catch (TwitterSMException e) {
			System.err.println(e.getMessage());
			return false;
		}
		return false;
	}

}

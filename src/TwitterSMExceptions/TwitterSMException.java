package TwitterSMExceptions;

public class TwitterSMException extends Exception{
	
	public TwitterSMException(String string) {
		super(string);
	}

	/* (non-Javadoc)
	 * @see java.lang.Throwable#getMessage()
	 */
	@Override
	public String getMessage() {
		// TODO Auto-generated method stub
		return "TwitterSMException: "+super.getMessage();
	}

}

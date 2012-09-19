package TwitterSMExceptions;

public class NeedUserAuthException extends Exception{
	
	public NeedUserAuthException(String string) {
		super(string);
	}

	/* (non-Javadoc)
	 * @see java.lang.Throwable#getMessage()
	 */
	@Override
	public String getMessage() {
		// TODO Auto-generated method stub
		return "NeedUserAuthException: "+super.getMessage();
	}

}

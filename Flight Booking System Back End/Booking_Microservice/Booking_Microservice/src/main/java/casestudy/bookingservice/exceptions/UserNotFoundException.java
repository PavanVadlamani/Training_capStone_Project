package casestudy.bookingservice.exceptions;

public class UserNotFoundException extends RuntimeException {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String user;

	public UserNotFoundException(String user) {
		super();
		this.user = user;
	}

	public UserNotFoundException() {
		super();
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}
	
}

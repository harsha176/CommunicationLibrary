package edu.ncsu.csc574.emailserver.exceptions;

public class InitalizationException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5811402499228755044L;

	String errorMessage;
	
	public InitalizationException(String message) {
		errorMessage = message;
	}
	
	public String getMessage() {
		return errorMessage;
	}
}

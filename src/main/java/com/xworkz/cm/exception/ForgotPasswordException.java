package com.xworkz.cm.exception;

public class ForgotPasswordException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ForgotPasswordException() {
		super();
	}

	public ForgotPasswordException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public ForgotPasswordException(String message, Throwable cause) {
		super(message, cause);
	}

	public ForgotPasswordException(String message) {
		super(message);
	}

	public ForgotPasswordException(Throwable cause) {
		super(cause);
	}

}

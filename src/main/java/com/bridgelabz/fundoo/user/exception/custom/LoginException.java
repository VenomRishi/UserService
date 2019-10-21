package com.bridgelabz.fundoo.user.exception.custom;

public class LoginException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	@Override
	public synchronized Throwable fillInStackTrace() {
		return this;
	}

	public LoginException(String message) {
		super(message);
	}

}

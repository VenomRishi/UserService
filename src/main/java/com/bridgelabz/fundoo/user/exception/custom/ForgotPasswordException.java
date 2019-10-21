package com.bridgelabz.fundoo.user.exception.custom;

public class ForgotPasswordException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	@Override
	public synchronized Throwable fillInStackTrace() {
		return this;
	}

	public ForgotPasswordException(String message) {
		super(message);
	}

}

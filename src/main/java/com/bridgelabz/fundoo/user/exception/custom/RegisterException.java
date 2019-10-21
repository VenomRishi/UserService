package com.bridgelabz.fundoo.user.exception.custom;

public class RegisterException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	@Override
	public synchronized Throwable fillInStackTrace() {
		return this;
	}

	public RegisterException(String message) {
		super(message);
	}
}

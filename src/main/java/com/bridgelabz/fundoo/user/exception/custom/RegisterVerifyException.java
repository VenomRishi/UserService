package com.bridgelabz.fundoo.user.exception.custom;

public class RegisterVerifyException extends RuntimeException  {
	private static final long serialVersionUID = 1L;

	@Override
	public synchronized Throwable fillInStackTrace() {
		return this;
	}

	public RegisterVerifyException(String message) {
		super(message);
	}
}

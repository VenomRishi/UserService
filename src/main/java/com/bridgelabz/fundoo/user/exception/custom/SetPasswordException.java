package com.bridgelabz.fundoo.user.exception.custom;

public class SetPasswordException extends RuntimeException  {
	private static final long serialVersionUID = 1L;

	@Override
	public synchronized Throwable fillInStackTrace() {
		return this;
	}

	public SetPasswordException(String message) {
		super(message);
	}
}

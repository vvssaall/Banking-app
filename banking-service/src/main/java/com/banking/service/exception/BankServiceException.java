package com.banking.service.exception;

/**
 * @author delep
 */

public class BankServiceException extends RuntimeException {

	
	private static final long serialVersionUID = 1L;

	public BankServiceException(String message) {
		super(message);
	}

	public BankServiceException(String message, Throwable throwable) {
		super(message, throwable);
	}

}

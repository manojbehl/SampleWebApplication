package com.ibm.ignite.errorhanding;

public class TechnicalException extends Exception{

	public TechnicalException(String message, Throwable throwable) {
	
		super(message, throwable);
	}

}

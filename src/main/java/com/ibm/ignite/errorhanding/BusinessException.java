package com.ibm.ignite.errorhanding;

public class BusinessException extends Exception{

	public BusinessException(String message, Throwable throwable) {
	
		super(message, throwable);
	}
	
	public BusinessException(String message) {
		
		super(message);
	}

}

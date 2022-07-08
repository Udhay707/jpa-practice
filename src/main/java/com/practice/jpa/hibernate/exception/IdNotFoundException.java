package com.practice.jpa.hibernate.exception;

public class IdNotFoundException extends RuntimeException{


	/**
	 * Id is not present in DB
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public IdNotFoundException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}
	


	
}

package com.nagarro.servicecatalogue.exception;

public class ProductCatalogueException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6946160143396008436L;
	
	String msg;

	public ProductCatalogueException(String msg) {
		this.msg = msg;
	}

	@Override
	public String getMessage() {
		return msg;
	}

}

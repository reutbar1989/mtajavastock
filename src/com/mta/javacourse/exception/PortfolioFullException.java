package com.mta.javacourse.exception;

import com.mta.javacourse.model.Portfolio;

public class PortfolioFullException extends Exception {

	public PortfolioFullException(){
		super (" You had reached maximum portpolio size! ");
	}
	
}

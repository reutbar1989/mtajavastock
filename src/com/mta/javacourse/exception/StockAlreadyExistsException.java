package com.mta.javacourse.exception;

public class StockAlreadyExistsException extends Exception {

	public  StockAlreadyExistsException(String symbol){
		super (" Stock "+symbol+" is allready exists! ");
	}
	
}



package com.mta.javacourse.exception;

/**
 * @author Reut Barhoom
 * Exception to be thrown when a stock already exists.
 */

public class StockAlreadyExistsException extends Exception {

	public  StockAlreadyExistsException(String symbol){
		super (" Stock "+symbol+" is allready exists! ");
	}
	
}



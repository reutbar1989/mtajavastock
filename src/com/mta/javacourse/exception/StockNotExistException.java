package com.mta.javacourse.exception;

/**
 * @author Reut Barhoom
 *  Exception to be thrown when a stock doesn’t exist.
 */

public class StockNotExistException extends Exception {

	public StockNotExistException(String symbol){
		super (" Stock "+symbol+" does not exists! 	");
	}
	
}

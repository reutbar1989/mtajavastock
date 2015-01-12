package com.mta.javacourse.exception;

public class StockNotExistException extends Exception {

	public StockNotExistException(String symbol){
		super (" Stock "+symbol+" does not exists! ");
	}
	
}

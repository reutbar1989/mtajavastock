package com.mta.javacourse.exception;

import com.mta.javacourse.model.Portfolio;

/**
 * @author Reut Barhoom
 *  Exception to be thrown when adding more stocks than  allowed.
 */

public class PortfolioFullException extends Exception {

	public PortfolioFullException(){
		super (" You had reached maximum portpolio size! ");
	}
	
}

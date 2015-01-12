package com.mta.javacourse.exception;

/**
 * @author Reut Barhoom
 *  Exception to be thrown when the portfolio balance becomes  negative.
 */

public class BalanceException extends Exception {

	public BalanceException (float wantToBuy, float balance) {
		super (" You want to buy with "+wantToBuy+" $, but you only have "+balance+" $! ");
	}
}

package com.mta.javacourse.exception;

public class BalanceException extends Exception {

	public BalanceException (float wantToBuy, float balance) {
		super (" You want to buy with "+wantToBuy+" $, but you only have "+balance+" $! ");
	}
}

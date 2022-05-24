package com.dbms.BankingApplicationSystem;

@SuppressWarnings("serial")
public class InsufficientBalanceException extends Exception {
	public InsufficientBalanceException(String message) {
		super(message);
		}
}

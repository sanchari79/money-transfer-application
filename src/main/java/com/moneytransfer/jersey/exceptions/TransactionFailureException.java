package com.moneytransfer.jersey.exceptions;

public class TransactionFailureException extends Exception {
    public TransactionFailureException() {
        super("Transaction failed!");
    }
}

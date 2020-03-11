package com.moneytransfer.jersey.exceptions;

import static com.moneytransfer.jersey.constants.MoneyTransferConstants.INVALID_USER_MESSAGE;

public class InvalidUserException extends Exception {
    public InvalidUserException() {
        super(INVALID_USER_MESSAGE);
    }
}

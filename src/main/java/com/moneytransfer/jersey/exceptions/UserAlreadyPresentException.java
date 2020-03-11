package com.moneytransfer.jersey.exceptions;

import static com.moneytransfer.jersey.constants.MoneyTransferConstants.EXISTING_USER_MESSAGE;

public class UserAlreadyPresentException extends Exception{

    public UserAlreadyPresentException() {
        super(EXISTING_USER_MESSAGE);
    }
}

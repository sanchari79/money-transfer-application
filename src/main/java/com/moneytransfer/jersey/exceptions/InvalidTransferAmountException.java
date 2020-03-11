package com.moneytransfer.jersey.exceptions;

import static com.moneytransfer.jersey.constants.MoneyTransferConstants.INVALID_TRANSFER_AMOUNT_MESSAGE;

public class InvalidTransferAmountException extends Exception {
    public InvalidTransferAmountException() {
        super(INVALID_TRANSFER_AMOUNT_MESSAGE);
    }
}

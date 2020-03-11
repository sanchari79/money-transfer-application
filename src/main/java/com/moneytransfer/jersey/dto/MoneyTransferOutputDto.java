package com.moneytransfer.jersey.dto;

public class MoneyTransferOutputDto {
    int transactionId;

    public MoneyTransferOutputDto(){}

    public MoneyTransferOutputDto(int transactionId) {
        this.transactionId = transactionId;
    }

    public int getTransactionId() {
        return transactionId;
    }
}

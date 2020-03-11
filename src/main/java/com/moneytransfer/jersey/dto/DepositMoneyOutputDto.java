package com.moneytransfer.jersey.dto;

public class DepositMoneyOutputDto {
    private int transactionId;

    public DepositMoneyOutputDto(){}

    public DepositMoneyOutputDto(int transactionId) {
        this.transactionId = transactionId;
    }

    public int getTransactionId() {
        return transactionId;
    }
}

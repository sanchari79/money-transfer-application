package com.moneytransfer.jersey.dto;

public class GetBalanceOutputDto {
    private double balance;

    public GetBalanceOutputDto() {
    }

    public GetBalanceOutputDto(double balance) {
        this.balance = balance;
    }

    public double getBalance() {
        return balance;
    }
}

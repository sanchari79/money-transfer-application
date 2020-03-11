package com.moneytransfer.jersey.dto;

public class GerBalanceOutputDto {
    private double balance;

    public GerBalanceOutputDto() {
    }

    public GerBalanceOutputDto(double balance) {
        this.balance=balance;
    }

    public double getBalance() {
        return balance;
    }
}

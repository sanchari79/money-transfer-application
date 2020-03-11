package com.moneytransfer.jersey.dto;

public class DepositMoneyInputDto {
    private double amount;
    private int userId;
    private String description;

    public DepositMoneyInputDto(){}

    public DepositMoneyInputDto(double amount, int userId, String description) {
        this.amount = amount;
        this.userId = userId;
        this.description = description;
    }

    public double getAmount() {
        return this.amount;
    }

    public int getUserId() {
        return userId;
    }

    public String getDescription() {
        return description;
    }
}

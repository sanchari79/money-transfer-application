package com.moneytransfer.jersey.dto;

public class MoneyTransferInputDto {
    private int fromUserId;
    private int toUserId;
    private double amount;
    private String description;

    public MoneyTransferInputDto(){}

    public MoneyTransferInputDto(int fromUserId, int toUserId, double amount, String description) {
        this.fromUserId = fromUserId;
        this.toUserId = toUserId;
        this.amount = amount;
        this.description=description;
    }

    public int getFromUserId() {
        return fromUserId;
    }

    public int getToUserId() {
        return toUserId;
    }

    public double getAmount() {
        return amount;
    }

    public String getDescription() {
        return description;
    }
}

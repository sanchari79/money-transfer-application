package com.moneytransfer.jersey.dto;

public class GetBalanceInputDto {
    private int userId;
//    public GetBalanceInputDto(){}

    public GetBalanceInputDto(int userId) {
        this.userId = userId;
    }

    public int getUserId() {
        return userId;
    }
}

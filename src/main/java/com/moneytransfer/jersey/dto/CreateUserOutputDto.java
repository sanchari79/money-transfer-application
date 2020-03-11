package com.moneytransfer.jersey.dto;

public class CreateUserOutputDto {
    private int userId;

    public CreateUserOutputDto(){}

    public CreateUserOutputDto(int userId){
        this.userId= userId;
    }

    public int getUserId() {
        return userId;
    }
}

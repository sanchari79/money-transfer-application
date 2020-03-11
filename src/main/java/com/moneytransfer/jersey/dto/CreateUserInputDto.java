package com.moneytransfer.jersey.dto;

public class CreateUserInputDto {
    private String name;
    private String emailId;
    private String phoneNumber;


    public CreateUserInputDto(){}

    public CreateUserInputDto(String name, String emailId, String phoneNumber){
        this.name=name;
        this.emailId=emailId;
        this.phoneNumber=phoneNumber;
    }

    public String getName() {
        return name;
    }

    public String getEmailId() {
        return emailId;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }
}

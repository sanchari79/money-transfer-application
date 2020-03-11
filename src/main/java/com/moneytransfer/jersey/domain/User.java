package com.moneytransfer.jersey.domain;

import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.SecureRandom;

import static com.moneytransfer.jersey.constants.MoneyTransferConstants.USER_ID_RANGE;

public class User {
    private int userId;
    private String name;
    private String emailId;
    private String phoneNumber;
    private Account account;
    static SecureRandom secureRandomGenerator;

    static {
        try {
            secureRandomGenerator = SecureRandom.getInstance("SHA1PRNG", "SUN");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchProviderException e) {
            e.printStackTrace();
        }
    }


    public User(String name, String emailId, String phoneNumber) {
        this.userId=secureRandomGenerator.nextInt(USER_ID_RANGE);
        this.name=name;
        this.emailId=emailId;
        this.phoneNumber=phoneNumber;
        this.account = new Account(userId);
    }

    public int getUserId() {
        return userId;
    }

    public Account getAccount() {
        return account;
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

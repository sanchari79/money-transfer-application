package com.moneytransfer.jersey.domain;

import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;

import static com.moneytransfer.jersey.constants.MoneyTransferConstants.ACCOUNT_ID_RANGE;
import static com.moneytransfer.jersey.constants.MoneyTransferConstants.DEFAULT_BALANCE;

public class Account {
    private int accountId;
    private int userId;
    private double balance;
    private double intermediateBalance;
    private List<Transaction> transactionList;
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

    public Account(int userId){
        this.accountId=secureRandomGenerator.nextInt(ACCOUNT_ID_RANGE);
        this.userId=userId;
        this.balance=DEFAULT_BALANCE;
        this.intermediateBalance=this.balance;
        this.transactionList=new ArrayList();
    }

    public synchronized boolean debit(Transaction transaction){
        if(insufficientBalance(transaction.getAmount())){
            return false;
        }
        this.intermediateBalance=this.balance;
        this.intermediateBalance-=transaction.getAmount();
        return true;
    }

    private boolean insufficientBalance(double transferAmount) {
        return this.balance<transferAmount;
    }

    public synchronized boolean credit(Transaction transaction){
        this.intermediateBalance=this.balance;
        this.intermediateBalance+=transaction.getAmount();
        return true;
    }

    public synchronized void persistBalance(Transaction transaction){
        this.balance=this.intermediateBalance;
        addTransaction(transaction);
    }

    public void addTransaction(Transaction transaction){
        transactionList.add(transaction);
    }

    public int getAccountId() {
        return accountId;
    }

    public double getBalance() {
        return balance;
    }

    public int getUserId() {
        return userId;
    }
    public List<Transaction> getTransactionList() {
        return transactionList;
    }
}

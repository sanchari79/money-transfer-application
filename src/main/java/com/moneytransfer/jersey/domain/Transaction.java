package com.moneytransfer.jersey.domain;

import com.moneytransfer.jersey.constants.TransactionStatus;

import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.SecureRandom;
import java.util.Date;

import static com.moneytransfer.jersey.constants.MoneyTransferConstants.TRANSACTION_ID_RANGE;

public class Transaction {
    private int transactionId;
    private  int fromAccountId;
    private int toAccountId;
    private double amount;
    private Date creationTime;
    private String description;
    private TransactionStatus status;
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
    public Transaction(int fromAccountId, int toAccountId, double amount, String description) {
        this.transactionId = secureRandomGenerator.nextInt(TRANSACTION_ID_RANGE);
        this.fromAccountId = fromAccountId;
        this.toAccountId = toAccountId;
        this.amount = amount;
        this.creationTime = new Date();
        this.description = description;
        this.status = TransactionStatus.INITIATED;
    }

    public int getTransactionId() {
        return transactionId;
    }

    public int getFromAccountId() {
        return fromAccountId;
    }

    public int getToAccountId() {
        return toAccountId;
    }

    public double getAmount() {
        return amount;
    }

    public Date getCreationTime() {
        return creationTime;
    }

    public String getDescription() {
        return description;
    }

    public TransactionStatus getStatus() {
        return status;
    }

    public void setStatus(TransactionStatus status) {
        this.status = status;
    }
}

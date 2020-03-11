package com.moneytransfer.jersey.repository;

import com.moneytransfer.jersey.constants.TransactionStatus;
import com.moneytransfer.jersey.domain.Transaction;
import com.moneytransfer.jersey.domain.User;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class MoneyTransferRepository {
    private static Map<Integer, User> userMap;
    private static Map<String, User> userEmailIdMap;
    private static Map<Integer, Transaction> transactionMap;

    public MoneyTransferRepository() {
        this.userMap = new ConcurrentHashMap();
        this.userEmailIdMap=new ConcurrentHashMap();
        this.transactionMap = new ConcurrentHashMap();
    }

    public void persistUser(User user) {
        userMap.put(user.getUserId(), user);
        userEmailIdMap.put(user.getEmailId(), user);
    }

    public boolean isUserIdPresent(int userId){
        return userMap.containsKey(userId);
    }

    public boolean isUserEmailPresent(String emailId){
        return userEmailIdMap.containsKey(emailId);
    }

    public User getUser(int userId){
        return userMap.get(userId);
    }

    public User getUserByEmailId(String emailId){
        return userEmailIdMap.get(emailId);
    }

    public synchronized void persistTransaction(Transaction transaction){
        transaction.setStatus(TransactionStatus.SUCCESS);
        transactionMap.put(transaction.getTransactionId(), transaction);
    }

}

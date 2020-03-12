package com.moneytransfer.jersey.service;

import com.moneytransfer.jersey.domain.Transaction;
import com.moneytransfer.jersey.domain.User;
import com.moneytransfer.jersey.exceptions.InvalidTransferAmountException;
import com.moneytransfer.jersey.exceptions.InvalidUserException;
import com.moneytransfer.jersey.exceptions.TransactionFailureException;
import com.moneytransfer.jersey.repository.MoneyTransferRepository;

public class MoneyTransferService {
    private static MoneyTransferRepository repository=new MoneyTransferRepository();

    public int transferMoney(int fromUserId,int toUserId, double amount, String description) throws InvalidUserException,
            InvalidTransferAmountException, TransactionFailureException {

        validateUsers(fromUserId, toUserId);
        validateAmount(amount);
        User fromUser=repository.getUser(fromUserId);
        User toUser=repository.getUser(toUserId);
        Transaction transaction=new Transaction(fromUser.getAccount().getAccountId(),
                toUser.getAccount().getAccountId(),
                amount,
                description);
        boolean debitSuccess = fromUser.getAccount().debit(transaction);
        if(!debitSuccess){
            throw new InvalidTransferAmountException();
        }
        boolean creditSuccess = toUser.getAccount().credit(transaction);
        if(!creditSuccess){
            throw new TransactionFailureException();
        }
        persistData(fromUser, toUser, transaction);
        return transaction.getTransactionId();
    }

    private void persistData(User fromUser, User toUser, Transaction transaction) {
        fromUser.getAccount().persistBalance(transaction);
        toUser.getAccount().persistBalance(transaction);
        repository.persistTransaction(transaction);
    }

    private void validateAmount(double amount) throws InvalidTransferAmountException{
        if(amount<=0)
            throw new InvalidTransferAmountException();
    }

    private void validateUsers(int fromUserId, int toUserId) throws InvalidUserException {
        if(!repository.isUserIdPresent(fromUserId) || !repository.isUserIdPresent(toUserId))
            throw new InvalidUserException();
    }


}

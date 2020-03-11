package com.moneytransfer.jersey.service;

import com.moneytransfer.jersey.constants.TransactionStatus;
import com.moneytransfer.jersey.domain.Transaction;
import com.moneytransfer.jersey.domain.User;
import com.moneytransfer.jersey.dto.DepositMoneyInputDto;
import com.moneytransfer.jersey.exceptions.InvalidTransferAmountException;
import com.moneytransfer.jersey.exceptions.InvalidUserException;
import com.moneytransfer.jersey.exceptions.TransactionFailureException;
import com.moneytransfer.jersey.repository.MoneyTransferRepository;

public class DepositMoneyService {
    private static MoneyTransferRepository repository=new MoneyTransferRepository();

    public int deposit(DepositMoneyInputDto depositMoneyInputDto) throws InvalidUserException,
            InvalidTransferAmountException, TransactionFailureException {
        validateInput(depositMoneyInputDto);
        int userId=depositMoneyInputDto.getUserId();
        User user=repository.getUser(userId);
        Transaction transaction=new Transaction(userId,userId,depositMoneyInputDto.getAmount(),depositMoneyInputDto.getDescription());
        boolean creditSuccess = user.getAccount().credit(transaction);
        if(!creditSuccess)
            throw new TransactionFailureException();
        user.getAccount().persistBalance(transaction);
        repository.persistTransaction(transaction);
        return transaction.getTransactionId();
    }

    private void validateInput(DepositMoneyInputDto depositMoneyInputDto) throws InvalidUserException, InvalidTransferAmountException {
        if(!repository.isUserIdPresent(depositMoneyInputDto.getUserId()))
            throw new InvalidUserException();
        if(depositMoneyInputDto.getAmount()<=0)
            throw new InvalidTransferAmountException();
    }
}

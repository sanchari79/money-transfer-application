package com.moneytransfer.jersey.service;

import com.moneytransfer.jersey.domain.User;
import com.moneytransfer.jersey.exceptions.InvalidUserException;
import com.moneytransfer.jersey.repository.MoneyTransferRepository;

public class GetBalanceService {
    private static MoneyTransferRepository repository=new MoneyTransferRepository();

    public double getBalance(int userId) throws InvalidUserException {
        validateInput(userId);
        User user=repository.getUser(userId);
        return user.getAccount().getBalance();
    }

    private void validateInput(int userId) throws InvalidUserException {
        if(!repository.isUserIdPresent(userId))
            throw new InvalidUserException();
    }

}

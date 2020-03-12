package com.moneytransfer.jersey.service;

import com.moneytransfer.jersey.exceptions.InvalidTransferAmountException;
import com.moneytransfer.jersey.exceptions.InvalidUserException;
import com.moneytransfer.jersey.exceptions.TransactionFailureException;
import com.moneytransfer.jersey.repository.MoneyTransferRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

public class MoneyTransferServiceTest {
    @InjectMocks
    MoneyTransferService moneyTransferService = new MoneyTransferService();
    @Mock
    MoneyTransferRepository repository;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test(expected = InvalidUserException.class)
    public void transferMoney() throws TransactionFailureException, InvalidTransferAmountException, InvalidUserException {
        moneyTransferService.transferMoney(1, 2, 10.0, "test");
        Mockito.verify(repository, Mockito.times(1)).isUserIdPresent(1);
        Mockito.verify(repository, Mockito.times(1)).isUserIdPresent(2);
        Mockito.verify(repository, Mockito.times(1)).getUser(1);
        Mockito.verify(repository, Mockito.times(1)).getUser(2);
        Mockito.verify(repository, Mockito.times(1)).persistTransaction(Mockito.anyObject());
    }

}

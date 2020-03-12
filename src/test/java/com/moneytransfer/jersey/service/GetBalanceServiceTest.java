package com.moneytransfer.jersey.service;

import com.moneytransfer.jersey.exceptions.InvalidUserException;
import com.moneytransfer.jersey.repository.MoneyTransferRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

public class GetBalanceServiceTest {
    @InjectMocks
    GetBalanceService getBalanceService = new GetBalanceService();

    @Mock
    MoneyTransferRepository repository;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test(expected = InvalidUserException.class)
    public void getBalanceTest() throws InvalidUserException {
        getBalanceService.getBalance(1);
        Mockito.verify(repository, Mockito.times(1)).isUserIdPresent(1);
        Mockito.verify(repository, Mockito.times(1)).getUser(1);
    }
}

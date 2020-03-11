package com.moneytransfer.jersey.service;

import com.moneytransfer.jersey.dto.DepositMoneyInputDto;
import com.moneytransfer.jersey.exceptions.InvalidTransferAmountException;
import com.moneytransfer.jersey.exceptions.InvalidUserException;
import com.moneytransfer.jersey.exceptions.TransactionFailureException;
import com.moneytransfer.jersey.repository.MoneyTransferRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import static com.moneytransfer.jersey.constants.MoneyTransferConstants.TRANSACTION_ID_RANGE;

public class DepositMoneyServiceTest {
    @InjectMocks
    DepositMoneyService depositMoneyService = new DepositMoneyService();
    @Mock
    MoneyTransferRepository repository;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test(expected = InvalidUserException.class)
    public void depositTest() throws TransactionFailureException, InvalidTransferAmountException, InvalidUserException {
        DepositMoneyInputDto depositMoneyInputDto = new DepositMoneyInputDto(50.0, 12, "test failed deposit");
        int transactionId = depositMoneyService.deposit(depositMoneyInputDto);
        Assert.assertTrue(transactionId < TRANSACTION_ID_RANGE);
        Mockito.verify(repository, Mockito.times(1)).getUser(Mockito.anyInt());
        Mockito.verify(repository, Mockito.times(1)).isUserIdPresent(Mockito.anyInt());
        Mockito.verify(repository, Mockito.times(1)).persistTransaction(Mockito.anyObject());
    }
}

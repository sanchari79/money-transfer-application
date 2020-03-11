package com.moneytransfer.jersey.service;

import com.moneytransfer.jersey.dto.CreateUserInputDto;
import com.moneytransfer.jersey.exceptions.UserAlreadyPresentException;
import com.moneytransfer.jersey.repository.MoneyTransferRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import static com.moneytransfer.jersey.constants.MoneyTransferConstants.USER_ID_RANGE;

public class CreateUserServiceTest {
    @InjectMocks
    CreateUserService service=new CreateUserService();
    @Mock
    MoneyTransferRepository repository;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void createUserTest(){
        CreateUserInputDto createUserInputDto=new CreateUserInputDto("testuser","testuser@gmail.com","11232323");
        try {
            int userId = service.createUser(createUserInputDto);
            Assert.assertTrue(userId<=USER_ID_RANGE);
            Mockito.verify(repository, Mockito.times(1)).persistUser(Mockito.anyObject());
        } catch (UserAlreadyPresentException e) {
            e.printStackTrace();
        }
    }
}

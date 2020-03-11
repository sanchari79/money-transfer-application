package com.moneytransfer.jersey.repository;

import com.moneytransfer.jersey.domain.User;
import org.junit.Assert;
import org.junit.Test;

public class MoneyTransferRepositoryTest {
    MoneyTransferRepository repository = new MoneyTransferRepository();

    @Test
    public void isUserIdpPresentTest() {
        int userId = 1;
        boolean success = repository.isUserIdPresent(userId);
        Assert.assertEquals(false, success);
    }

    @Test
    public void isUserEmailPresentTest() {
        String emailId = "ab@test.com";
        boolean success = repository.isUserEmailPresent(emailId);
        Assert.assertEquals(false, success);

    }

    @Test
    public void createUserTest() {
        String emailId = "testuser@test.com";
        User user = new User("testuser", emailId, "1234567890");
        repository.persistUser(user);
        Assert.assertEquals(true, repository.isUserEmailPresent(emailId));
    }
}

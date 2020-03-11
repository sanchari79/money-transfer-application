package com.moneytransfer.jersey.service;

import com.moneytransfer.jersey.domain.User;
import com.moneytransfer.jersey.dto.CreateUserInputDto;
import com.moneytransfer.jersey.exceptions.UserAlreadyPresentException;
import com.moneytransfer.jersey.repository.MoneyTransferRepository;

public class CreateUserService {
    static MoneyTransferRepository repository=new MoneyTransferRepository();

    public int createUser(CreateUserInputDto createUserInputDto) throws UserAlreadyPresentException {
        validateInput(createUserInputDto.getEmailId());
        User user=new User(createUserInputDto.getName(),createUserInputDto.getEmailId(),createUserInputDto.getPhoneNumber());
        repository.persistUser(user);
        return user.getUserId();
    }

    private void validateInput(String email) throws UserAlreadyPresentException {
        if(repository.isUserEmailPresent(email)){
            throw new UserAlreadyPresentException();
        }
    }
}

package com.moneytransfer.jersey.resources;

import com.moneytransfer.jersey.dto.*;
import com.moneytransfer.jersey.exceptions.InvalidTransferAmountException;
import com.moneytransfer.jersey.exceptions.InvalidUserException;
import com.moneytransfer.jersey.exceptions.UserAlreadyPresentException;
import com.moneytransfer.jersey.service.CreateUserService;
import com.moneytransfer.jersey.service.DepositMoneyService;
import com.moneytransfer.jersey.service.GetBalanceService;
import com.moneytransfer.jersey.service.MoneyTransferService;
import org.eclipse.jetty.http.HttpStatus;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/transfer/money")
public class ApiResource {
    private static CreateUserService createUserService = new CreateUserService();
    private static MoneyTransferService moneyTransferService = new MoneyTransferService();
    private static DepositMoneyService depositMoneyService = new DepositMoneyService();
    private static GetBalanceService getBalanceService = new GetBalanceService();

    @POST
    @Path("/create-user")
    @Produces(MediaType.APPLICATION_JSON)
    public Response createUser(CreateUserInputDto createUserInputDto) {
        try {
            int userId = createUserService.createUser(createUserInputDto);
            return Response.ok().entity(new CreateUserOutputDto(userId)).build();
        } catch (UserAlreadyPresentException e) {
            return Response.status(HttpStatus.UNPROCESSABLE_ENTITY_422).entity(e.getMessage()).build();
        } catch (Exception e) {
            return Response.status(HttpStatus.INTERNAL_SERVER_ERROR_500).entity(e.getMessage()).build();
        }
    }

    @POST
    @Path("/deposit-money")
    @Produces(MediaType.APPLICATION_JSON)
    public Response depositMoney(DepositMoneyInputDto depositMoneyInputDto) {
        try {
            int transactionId = depositMoneyService.deposit(depositMoneyInputDto);
            return Response.ok().entity(new DepositMoneyOutputDto(transactionId)).build();
        } catch (InvalidUserException | InvalidTransferAmountException e) {
            return Response.status(HttpStatus.UNPROCESSABLE_ENTITY_422).entity(e.getMessage()).build();
        } catch (Exception e) {
            return Response.status(HttpStatus.INTERNAL_SERVER_ERROR_500).entity(e.getMessage()).build();
        }
    }

    @POST
    @Path("/transfer")
    @Produces(MediaType.APPLICATION_JSON)
    public Response transfer(MoneyTransferInputDto moneyTransferInputDto) {
        try {
            int transactionId = moneyTransferService.transferMoney(moneyTransferInputDto.getFromUserId(),
                    moneyTransferInputDto.getToUserId(),
                    moneyTransferInputDto.getAmount(),
                    moneyTransferInputDto.getDescription());
            return Response.status(HttpStatus.OK_200).entity(new MoneyTransferOutputDto(transactionId)).build();
        } catch (InvalidUserException | InvalidTransferAmountException e) {
            return Response.status(HttpStatus.UNPROCESSABLE_ENTITY_422).entity(e.getMessage()).build();
        } catch (Exception e) {
            return Response.status(HttpStatus.INTERNAL_SERVER_ERROR_500).entity(e.getMessage()).build();
        }
    }


    @GET
    @Path("/get-balance")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getBalance(@QueryParam("userId") int userId) {
        try {
            double balance = getBalanceService.getBalance(userId);
            return Response.status(HttpStatus.OK_200).entity(new GetBalanceOutputDto(balance)).build();
        } catch (InvalidUserException e) {
            return Response.status(HttpStatus.UNPROCESSABLE_ENTITY_422).entity(e.getMessage()).build();
        } catch (Exception e) {
            return Response.status(HttpStatus.INTERNAL_SERVER_ERROR_500).entity(e.getMessage()).build();
        }
    }
}
package com.moneytransfer.jersey;

import com.moneytransfer.jersey.dto.CreateUserOutputDto;
import com.moneytransfer.jersey.dto.GetBalanceOutputDto;
import com.moneytransfer.jersey.resources.ApiResource;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;
import org.junit.Assert;
import org.junit.Test;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.Response;

import static org.eclipse.jetty.http.HttpStatus.UNPROCESSABLE_ENTITY_422;

public class ApiResourceTest extends JerseyTest {
    @Override
    protected Application configure() {
        return new ResourceConfig(ApiResource.class);
    }

    @Test
    public void createUserTest() {
        Response response = target("/transfer/money/create-user").request().post(Entity.json("{\"name\":\"testcreate\",\"emailId\":\"testcreate@terst.com\",\"phoneNumber\":\"1234568\"}"));
        Assert.assertEquals("Http Response should be 200: ", Response.Status.OK.getStatusCode(), response.getStatus());
    }

    @Test
    public void depositMoneyInvalidUserTest() {
        Response response = target("/transfer/money/deposit-money").request().post(Entity.json("{\"amount\":\"100\",\"userId\":\"1\",\"description\":\"test\"}"));
        Assert.assertEquals("Http Response should be 422: ", UNPROCESSABLE_ENTITY_422, response.getStatus());
    }

    @Test
    public void transferTest() {
        Response response = target("/transfer/money/transfer").request().post(Entity.json("{\"fromUserId\":\"1\",\"toUserId\":\"2\",\"amount\":\"50\"}"));
        Assert.assertEquals("Http Response should be 422: ", UNPROCESSABLE_ENTITY_422, response.getStatus());
    }

    @Test
    public void getBalanceTest() {
        Response response = target("/transfer/money/get-balance").queryParam("userId", "1").request().get();
        Assert.assertEquals("Http Response should be 422: ", UNPROCESSABLE_ENTITY_422, response.getStatus());
    }

    @Test
    public void moneyTransferEndToEndSuccessTest() {
        Response createFirstUserResponse = target("/transfer/money/create-user").request().post(Entity.json("{\"name\":\"testuser1\",\"emailId\":\"testuser1@test.com\",\"phoneNumber\":\"1234568\"}"));
        Assert.assertEquals("Create user response should be 200: ", Response.Status.OK.getStatusCode(), createFirstUserResponse.getStatus());
        CreateUserOutputDto createUserOutputDto = createFirstUserResponse.readEntity(CreateUserOutputDto.class);

        Response createSecondUserResponse = target("/transfer/money/create-user").request().post(Entity.json("{\"name\":\"testuser2\",\"emailId\":\"testuser2@test.com\",\"phoneNumber\":\"12374568\"}"));
        Assert.assertEquals("Create user response should be 200: ", Response.Status.OK.getStatusCode(), createFirstUserResponse.getStatus());
        CreateUserOutputDto createSecondUserOutputDto = createSecondUserResponse.readEntity(CreateUserOutputDto.class);

        Response depositMoneyResponse = target("/transfer/money/deposit-money").request().post(Entity.json("{\"amount\":\"100\",\"userId\":\"" + createUserOutputDto.getUserId() + "\"}"));
        Assert.assertEquals("deposit money response should be 200: ", Response.Status.OK.getStatusCode(), depositMoneyResponse.getStatus());

        Response transferResponse = target("/transfer/money/transfer").request().post(Entity.json("{\"fromUserId\":\"" + createUserOutputDto.getUserId() + "\",\"toUserId\":\"" + createSecondUserOutputDto.getUserId() + "\",\"amount\":\"20\"}"));
        Assert.assertEquals("transfer response should be 200: ", Response.Status.OK.getStatusCode(), transferResponse.getStatus());

        Response getFirstBalanceResponse = target("/transfer/money/get-balance").queryParam("userId", createUserOutputDto.getUserId()).request().get();
        Assert.assertEquals("get balance response for first user should be 200: ", Response.Status.OK.getStatusCode(), getFirstBalanceResponse.getStatus());
        Assert.assertEquals(80.0, getFirstBalanceResponse.readEntity(GetBalanceOutputDto.class).getBalance(), 0);

        Response getSecondBalanceResponse = target("/transfer/money/get-balance").queryParam("userId", createSecondUserOutputDto.getUserId()).request().get();
        Assert.assertEquals("get balance response for second user should be 200: ", Response.Status.OK.getStatusCode(), getSecondBalanceResponse.getStatus());
        Assert.assertEquals(20.0, getSecondBalanceResponse.readEntity(GetBalanceOutputDto.class).getBalance(), 0);
    }

    @Test
    public void moneyTransferEndToEndFailureWithNoBalanceTest() {
        Response createFirstUserResponse = target("/transfer/money/create-user").request().post(Entity.json("{\"name\":\"testfailuser1\",\"emailId\":\"testfailuser1@test.com\",\"phoneNumber\":\"1234568\"}"));
        Assert.assertEquals("Create user response should be 200: ", Response.Status.OK.getStatusCode(), createFirstUserResponse.getStatus());
        CreateUserOutputDto createUserOutputDto = createFirstUserResponse.readEntity(CreateUserOutputDto.class);

        Response createSecondUserResponse = target("/transfer/money/create-user").request().post(Entity.json("{\"name\":\"testfailuser2\",\"emailId\":\"testfailuser2@test.com\",\"phoneNumber\":\"12374568\"}"));
        Assert.assertEquals("Create user response should be 200: ", Response.Status.OK.getStatusCode(), createFirstUserResponse.getStatus());
        CreateUserOutputDto createSecondUserOutputDto = createSecondUserResponse.readEntity(CreateUserOutputDto.class);

        Response transferResponse = target("/transfer/money/transfer").request().post(Entity.json("{\"fromUserId\":\"" + createUserOutputDto.getUserId() + "\",\"toUserId\":\"" + createSecondUserOutputDto.getUserId() + "\",\"amount\":\"20\"}"));
        Assert.assertEquals("transfer response should be 422: ", UNPROCESSABLE_ENTITY_422, transferResponse.getStatus());

        Response getFirstBalanceResponse = target("/transfer/money/get-balance").queryParam("userId", createUserOutputDto.getUserId()).request().get();
        Assert.assertEquals("get balance response for first user should be 200: ", Response.Status.OK.getStatusCode(), getFirstBalanceResponse.getStatus());
        Assert.assertEquals(0.0, getFirstBalanceResponse.readEntity(GetBalanceOutputDto.class).getBalance(), 0);

        Response getSecondBalanceResponse = target("/transfer/money/get-balance").queryParam("userId", createSecondUserOutputDto.getUserId()).request().get();
        Assert.assertEquals("get balance response for second user should be 200: ", Response.Status.OK.getStatusCode(), getSecondBalanceResponse.getStatus());
        Assert.assertEquals(0.0, getSecondBalanceResponse.readEntity(GetBalanceOutputDto.class).getBalance(), 0);
    }
}

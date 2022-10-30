package tests.users.validscenarios;

import controllers.UserService;
import datageneration.UserDataGeneration;
import io.restassured.response.Response;
import models.users.User;
import models.users.UserData;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.testng.Assert.assertEquals;

public class Login {

    @Test(description = "Verify Login Response Schema Is Returned Successfully")
    public void verifyLoginResponseSchema() {
        Response response = createUser(UserDataGeneration.createFullUserData());
        User actualUser = response.as(User.class);

        Map<String,String> loginBody = new HashMap<>();
        loginBody.put("walletID",actualUser.getUserData().getWalletName());
        loginBody.put("channelType","phone");

        response = UserService.login(loginBody);

        assertEquals(response.statusCode(), 200, "The status code should be 200");
        response.then().assertThat().body(matchesJsonSchemaInClasspath("schema/login.json"));

        //deleteUser();
    }

    @Test(description = "Verify Response Is 404 When I Login With Wallet Id That Does Not Exist")
    public void verifyResponseIs404WhenILoginWithWalletIdThatDoesNotExist() {
        Map<String,String> loginBody = new HashMap<>();
        loginBody.put("walletID","NOT_EXIST_WALLET_ID.testnet");
        loginBody.put("channelType","phone");

        Response response = UserService.login(loginBody);

        assertEquals(response.statusCode(), 404, "The status code should be 404 as the wallet Id not found");
    }

    @Test(description = "Verify Response Is 400 When I Login With Empty Wallet Id")
    public void verifyResponseIs400WhenILoginWithEmptyWalletId() {
        Map<String,String> loginBody = new HashMap<>();
        loginBody.put("walletID","");
        loginBody.put("channelType","phone");

        Response response = UserService.login(loginBody);

        assertEquals(response.statusCode(), 400, "The status code should be 400 as the wallet Id is empty");
    }

    @Test(description = "Verify Response Is 400 When I Login With Wallet Id More Than 50 Letter")
    public void verifyResponseIs400WhenILoginWithWalletIdMoreThan50Letter() {
        Map<String,String> loginBody = new HashMap<>();
        loginBody.put("walletID","AaaaaaaaaAAaaaaaaaaAAaaaaaaaaAAaaaaaaaaAAaaaaa.testnet");
        loginBody.put("channelType","phone");

        Response response = UserService.login(loginBody);

        assertEquals(response.statusCode(), 400, "The status code should be 400 as the wallet Id length is 51 letters");
    }

    @Test(description = "Verify Response Is 200 When I Login Without Channel Type")
    public void verifyResponseIs200WhenILoginWithoutChannelType() {

        Response response = createUser(UserDataGeneration.createFullUserData());
        User actualUser = response.as(User.class);

        Map<String,String> loginBody = new HashMap<>();
        loginBody.put("walletID",actualUser.getUserData().getWalletName());

        response = UserService.login(loginBody);

        assertEquals(response.statusCode(), 200, "The status code should be 200 as channel type is not required");
    }

    @Test(description = "Verify Response Is 400 When I Login Without Wallet Id")
    public void verifyResponseIs400WhenILoginWithoutWalletId() {

        Map<String,String> loginBody = new HashMap<>();
        loginBody.put("channelType","phone");

        Response response = UserService.login(loginBody);

        assertEquals(response.statusCode(), 400, "The status code should be 400 as Wallet Id is required");
    }

    @Test(description = "Verify Response Is 400 When I Login With Channel Type Not Email Or Phone")
    public void verifyResponseIs400WhenILoginWithChannelTypeNotEmailOrPhone() {

        Map<String,String> loginBody = new HashMap<>();
        loginBody.put("walletID","anyWalletId.testnet");
        loginBody.put("channelType","anything");

        Response response = UserService.login(loginBody);

        assertEquals(response.statusCode(), 400, "The status code should be 400 as channel type is not phone or email");
    }

    private Response createUser(UserData userData) {
        Response response = UserService.createUser(userData);

        assertEquals(response.statusCode(), 201, "The status code should be 201 user created successfully");

        return response;
    }
}

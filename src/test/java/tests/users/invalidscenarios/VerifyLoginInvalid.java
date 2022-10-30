package tests.users.invalidscenarios;

import controllers.UserService;
import datageneration.UserDataGeneration;
import io.restassured.response.Response;
import models.users.User;
import models.users.UserData;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static org.testng.Assert.assertEquals;

public class VerifyLoginInvalid {

    @Test(description = "Verify Response Is 404 When I Verify Login With Wallet Id That Does Not Exist")
    public void verifyResponseIs404WhenIVerifyLoginWithWalletIdThatDoesNotExist() {
        Map<String,String> loginBody = new HashMap<>();
        loginBody.put("walletID","NOT_EXIST_WALLET_ID.testnet");
        loginBody.put("OTP","123456");

        Response response = UserService.verifyLogin(loginBody);

        assertEquals(response.statusCode(), 404, "The status code should be 404 as the wallet Id not found");
    }

    @Test(description = "Verify Response Is 404 When I Verify Login With Wrong OTP")
    public void verifyResponseIs404WhenIVerifyLoginWithWrongOTP() {
        Response response = createUser(UserDataGeneration.createFullUserData());
        User actualUser = response.as(User.class);

        Map<String,String> loginBody = new HashMap<>();
        loginBody.put("walletID",actualUser.getUserData().getWalletName());
        loginBody.put("OTP","123456");

        response = UserService.verifyLogin(loginBody);

        assertEquals(response.statusCode(), 404, "The status code should be 404 as the OTP is wrong");
    }

    @Test(description = "Verify Response Is 400 When I Verify Login Without Mandatory Field")
    public void verifyResponseIs400WhenIVerifyLoginWithoutMandatoryField() {

        Map<String,String> loginBody = new HashMap<>();
        loginBody.put("OTP","123456");

        Response response = UserService.verifyLogin(loginBody);

        assertEquals(response.statusCode(), 400, "The status code should be 400 as Wallet Id is required");
    }

    @Test(description = "Verify Response Is 400 When I Verify Login With Empty Wallet Id")
    public void verifyResponseIs400WhenIVerifyLoginWithEmptyWalletId() {
        Map<String,String> loginBody = new HashMap<>();
        loginBody.put("walletID","");
        loginBody.put("OTP","123456");

        Response response = UserService.verifyLogin(loginBody);

        assertEquals(response.statusCode(), 400, "The status code should be 400 as the wallet Id is empty");
    }

    @Test(description = "Verify Response Is 400 When I Verify Login With Wallet Id More Than 50 Letter")
    public void verifyResponseIs400WhenIVerifyLoginWithWalletIdMoreThan50Letter() {
        Map<String,String> loginBody = new HashMap<>();
        loginBody.put("walletID","AaaaaaaaaAAaaaaaaaaAAaaaaaaaaAAaaaaaaaaAAaaaaa.testnet");
        loginBody.put("OTP","123456");

        Response response = UserService.verifyLogin(loginBody);

        assertEquals(response.statusCode(), 400, "The status code should be 400 as the wallet Id length is 51 letters");
    }

    @Test(description = "Verify Response Is 400 When I Verify Login With Empty OTP")
    public void verifyResponseIs400WhenIVerifyLoginWithEmptyOTP() {
        Map<String,String> loginBody = new HashMap<>();
        loginBody.put("walletID","testUser.testnet");
        loginBody.put("OTP","");

        Response response = UserService.verifyLogin(loginBody);

        assertEquals(response.statusCode(), 400, "The status code should be 400 as the OTP is empty");
    }

    @Test(description = "Verify Response Is 400 When I Verify Login With OTP Field Contains Alphanumeric Value")
    public void verifyResponseIs400WhenIVerifyLoginWithOTPFieldContainsAlphanumericValue() {
        Map<String,String> loginBody = new HashMap<>();
        loginBody.put("walletID","testUser.testnet");
        loginBody.put("OTP","123ab6");

        Response response = UserService.verifyLogin(loginBody);

        assertEquals(response.statusCode(), 400, "The status code should be 400 as the OTP contains Alphanumeric value");
    }

    @Test(description = "Verify Response Is 400 When I Verify Login With OTP Field Contains Special Characters")
    public void verifyResponseIs400WhenIVerifyLoginWithOTPFieldContainsSpecialCharacters() {
        Map<String,String> loginBody = new HashMap<>();
        loginBody.put("walletID","testUser.testnet");
        loginBody.put("OTP","1!3@$6");

        Response response = UserService.verifyLogin(loginBody);

        assertEquals(response.statusCode(), 400, "The status code should be 400 as the OTP contains special characters");
    }

    @Test(description = "Verify Response Is 400 When I Verify Login With OTP Field More Than Six Numbers")
    public void verifyResponseIs400WhenIVerifyLoginWithOTPFieldMoreThanSixNumbers() {
        Map<String,String> loginBody = new HashMap<>();
        loginBody.put("walletID","testUser.testnet");
        loginBody.put("OTP","1234567");

        Response response = UserService.verifyLogin(loginBody);

        assertEquals(response.statusCode(), 400, "The status code should be 400 as the OTP is More than 6 numbers");
    }

    @Test(description = "Verify Response Is 400 When I Verify Login With OTP Field Less Than Six Numbers")
    public void verifyResponseIs400WhenIVerifyLoginWithOTPFieldLessThanSixNumbers() {
        Map<String,String> loginBody = new HashMap<>();
        loginBody.put("walletID","testUser.testnet");
        loginBody.put("OTP","12345");

        Response response = UserService.verifyLogin(loginBody);

        assertEquals(response.statusCode(), 400, "The status code should be 400 as the OTP is Less than 6 numbers");
    }

    private Response createUser(UserData userData) {
        Response response = UserService.createUser(userData);

        assertEquals(response.statusCode(), 201, "The status code should be 201 user created successfully");

        return response;
    }
}

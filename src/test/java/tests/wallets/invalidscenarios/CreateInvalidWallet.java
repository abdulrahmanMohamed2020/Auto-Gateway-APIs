package tests.wallets.invalidscenarios;

import com.github.javafaker.Faker;
import controllers.UserService;
import controllers.WalletService;
import datageneration.UserDataGeneration;
import io.restassured.response.Response;
import models.users.User;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static org.testng.Assert.assertEquals;

public class CreateInvalidWallet {

    private String userToken;

    @Test(description = "Verify Response Is 400 When Creating New Wallet With Empty Wallet Name")
    public void verifyResponseIs400WhenSendingRequestWithEmptyWalletName() {
        createUser();

        Map<String,String> walletData = new HashMap<>();
        walletData.put("walletName",new Faker().bothify(""));

        Response response = WalletService.createWallet(walletData,userToken);

        assertEquals(response.statusCode(), 400, "The status code should be 400 bad request as the Wallet Name is empty");
    }

    @Test(description = "Verify Response Is 400 When Creating New Wallet With Wallet Name Has White Spaces")
    public void verifyResponseIs400WhenSendingRequestWithWalletNameHasSpaces() {
        createUser();

        Map<String,String> walletData = new HashMap<>();
        walletData.put("walletName",new Faker().bothify(new Faker().bothify("????? ???###.testnet")));

        Response response = WalletService.createWallet(walletData,userToken);

        assertEquals(response.statusCode(), 400, "The status code should be 400 bad request as the Wallet Name has spaces");
    }

    @Test(description = "Verify Response Is 409 When Creating New Wallet With Wallet Name Used Before")
    public void verifyResponseIs409WhenSendingRequestWithWalletNameUsedBefore() {
        createUser();

        String walletName = UserService.createUser(UserDataGeneration.createFullUserData())
                .as(User.class).getUserData().getWalletName();

        Map<String,String> walletData = new HashMap<>();
        walletData.put("walletName",walletName);

        Response response = WalletService.createWallet(walletData,userToken);

        assertEquals(response.statusCode(), 409, "The status code should be 409 conflict as the Wallet Name already exist");
    }

    @Test(description = "Verify Response Is 400 When Creating New Wallet With Wallet Name Does Not End With Correct Suffix (.testnet)")
    public void verifyResponseIs400WhenSendingRequestWithWalletNameDoesNotEndWithCorrectSuffix() {
        createUser();

        Map<String,String> walletData = new HashMap<>();
        walletData.put("walletName",new Faker().bothify("????????###"));

        Response response = WalletService.createWallet(walletData,userToken);

        assertEquals(response.statusCode(), 400, "The status code should be 400 as bad request");
    }

    @Test(description = "Verify Response Is 403 When Creating New Wallet With Invalid Token")
    public void verifyResponseIs403WhenCreatingNewWalletWithInvalidToken() {

        Map<String,String> walletData = new HashMap<>();
        walletData.put("walletName",new Faker().bothify("????????###.testnet"));

        Response response = WalletService.createWallet(walletData,"INVALID_TOKEN");

        assertEquals(response.statusCode(), 403, "The status code should be 403 Forbidden as the token is invalid");
    }

    @Test(description = "Verify Response Is 403 When Creating New Wallet With Empty Token")
    public void verifyResponseIs403WhenCreatingNewWalletWithEmptyToken() {

        Map<String,String> walletData = new HashMap<>();
        walletData.put("walletName",new Faker().bothify("????????###.testnet"));

        Response response = WalletService.createWallet(walletData,"");

        assertEquals(response.statusCode(), 403, "The status code should be 403 Forbidden as the token is empty");
    }

    private void createUser() {
        Response response = UserService.createUser(UserDataGeneration.createFullUserData());

        assertEquals(response.statusCode(), 201, "The status code should be 201 and the user created successfully");

        userToken = response.as(User.class).getJwtAccessToken();
    }

}

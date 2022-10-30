package tests.wallets.invalidscenarios;

import com.github.javafaker.Faker;
import controllers.UserService;
import controllers.WalletService;
import datageneration.UserDataGeneration;
import io.restassured.response.Response;
import models.users.User;
import models.wallets.Wallet;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static org.testng.Assert.assertEquals;

public class GetInvalidWallet {

    private String userToken, walletId;

    @Test(description = "Verify Response Is 403 When Getting Wallet With Invalid User Token")
    public void verifyResponseIs403WhenGettingWalletWithInvalidUserToken() {

        createUser();
        createWallet();
        Response response = WalletService.getWallet(walletId,"INVALID_TOKEN");

        assertEquals(response.statusCode(), 403, "The status code should be 403 Forbidden");

        deleteWallet();
    }

    @Test(description = "Verify Response Is 403 When Getting Wallet With Empty User Token")
    public void verifyResponseIs403WhenGettingWalletWithEmptyUserToken() {

        createUser();
        createWallet();
        Response response = WalletService.getWallet(walletId,"");

        assertEquals(response.statusCode(), 403, "The status code should be 403 Forbidden");

        deleteWallet();
    }

    @Test(description = "Verify Response Is 404 When Getting Wallet With Invalid Wallet Id Or Not Exist")
    public void verifyResponseIs404WhenGettingWalletWithInvalidWalletId() {

        createUser();
        Response response = WalletService.getWallet("INVALID_WALLET_ID",userToken);

        assertEquals(response.statusCode(), 404, "The status code should be 404 Invalid wallet ID or does not exist");
    }

    @Test(description = "Verify Response Is 400 When Getting Wallet With Empty Wallet Id")
    public void verifyResponseIs400WhenGettingWalletWithEmptyWalletId() {

        createUser();
        Response response = WalletService.getWallet("",userToken);

        assertEquals(response.statusCode(), 400, "The status code should be 400 as the Wallet Id is empty");
    }

    @Test(description = "Verify Response Is 401 When Getting Wallet Doesn't Belong To Me")
    public void verifyResponseIs401WhenGettingWalletDoesNotBelongToMe() {

        createUser();
        createWallet();
        createUser();
        Response response = WalletService.getWallet(walletId,userToken);

        assertEquals(response.statusCode(), 401, "The status code should be 401 as This Wallet Doesn't Belong to me");
    }

    private void createUser() {
        Response response = UserService.createUser(UserDataGeneration.createFullUserData());

        assertEquals(response.statusCode(), 201, "The status code should be 201 and the user created successfully");

        userToken = response.as(User.class).getJwtAccessToken();
    }

    private void createWallet() {
        Map<String,String> walletData = new HashMap<>();
        walletData.put("walletName",new Faker().bothify("????????###.testnet"));

        Response response = WalletService.createWallet(walletData,userToken);

        assertEquals(response.statusCode(), 201, "The status code should be 201 and the Wallet created Successfully");

        walletId = response.as(Wallet.class).getWalletId();
    }

    private void deleteWallet() {
        Response response = WalletService.deleteWallet(walletId,userToken);

        assertEquals(response.statusCode(), 202, "The status code should be 202 and the wallet has been deleted successfully");
    }
}
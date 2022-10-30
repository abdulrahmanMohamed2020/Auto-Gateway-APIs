package tests.wallets.validscenarios;

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

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.testng.Assert.assertEquals;

public class CreateWallet {

    private String userToken;

    @Test(description = "Verify Response Is 201 As Created When I Create New Wallet")
    public void verifyResponseIs201CreatedWhenICreateNewWallet() {
        createUser();

        Map<String,String> walletData = new HashMap<>();
        walletData.put("walletName",new Faker().bothify("????????###.testnet"));

        Response response = WalletService.createWallet(walletData,userToken);

        assertEquals(response.statusCode(), 201, "The status code should be 201 and the Wallet created Successfully");

        // delete wallet
        String walletId = response.as(Wallet.class).getWalletId();
        deleteWallet(walletId);
    }

    @Test(description = "Verify Response Schema(Required Data) Is Returned Successfully When I Create New Wallet")
    public void verifyResponseSchemaIsReturnedSuccessfullyWhenICreateNewWallet() {
        createUser();

        Map<String,String> walletData = new HashMap<>();
        walletData.put("walletName",new Faker().bothify("????????###.testnet"));

        Response response = WalletService.createWallet(walletData,userToken);

        assertEquals(response.statusCode(), 201, "The status code should be 201 and the Wallet created Successfully");
        response.then().assertThat().body(matchesJsonSchemaInClasspath("schema/createWallet.json"));

        // delete wallet
        String walletId = response.as(Wallet.class).getWalletId();
        deleteWallet(walletId);
    }

    private void createUser() {
        Response response = UserService.createUser(UserDataGeneration.createFullUserData());

        assertEquals(response.statusCode(), 201, "The status code should be 201 and the user created successfully");

        userToken = response.as(User.class).getJwtAccessToken();
    }

    private void deleteWallet(String walletId) {
        Response response = WalletService.deleteWallet(walletId,userToken);

        assertEquals(response.statusCode(), 202, "The status code should be 202 and the wallet has been deleted successfully");
    }
}
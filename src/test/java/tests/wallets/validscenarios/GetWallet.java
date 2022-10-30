package tests.wallets.validscenarios;

import com.github.javafaker.Faker;
import controllers.NftService;
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

public class GetWallet {

    private String userToken, walletId;

    @Test(description = "Verify Response Is Ok When Getting Wallet")
    public void verifyResponseIsOkWhenGettingWallet() {
        createUser();
        createWallet();
        Response response = WalletService.getWallet(walletId,userToken);

        assertEquals(response.statusCode(), 200, "The status code should be 200 and the Wallet returned successfully");

        deleteWallet();
    }

    @Test(description = "Verify Response Schema(Required Data) Is Returned Successfully When I Get Wallet Details")
    public void verifyResponseSchemaIsReturnedSuccessfullyWhenIGetWallet() {
        createUser();
        createWallet();
        Response response = WalletService.getWallet(walletId,userToken);

        assertEquals(response.statusCode(), 200, "The status code should be 200 and the Wallet returned successfully");
        response.then().assertThat().body(matchesJsonSchemaInClasspath("schema/getWallet.json"));

        deleteWallet();
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

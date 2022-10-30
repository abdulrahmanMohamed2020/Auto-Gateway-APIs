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

public class FetchInvalidWallets {

    private String userToken;

    @Test(description = "Verify Response Is 403 When Fetching Wallets With Invalid User Token")
    public void verifyResponseIs403WhenFetchingWalletsWithInvalidUserToken() {
        createUser();
        createWallet();
        Response response = WalletService.fetchUserWallets("INVALID_TOKEN");

        assertEquals(response.statusCode(), 403, "The status code should be 403 Forbidden");
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
    }
}

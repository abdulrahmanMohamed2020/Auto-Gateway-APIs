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

import static org.testng.Assert.assertEquals;

public class DeleteWallet {

    private String userToken, walletId;

    @Test(description = "Verify The User Can Delete Wallet Successfully")
    public void verifyTheUserCanDeleteWalletSuccessfully() {
        createUser();
        createWallet();

        Response response = WalletService.deleteWallet(walletId,userToken);

        assertEquals(response.statusCode(), 202, "The status code should be 202 and the wallet has been deleted successfully");
    }

    @Test(description = "Verify The Wallet Status Is Deleted After The User Delete It")
    public void verifyTheWalletStatusAfterDeleting() {
        createUser();
        createWallet();

        Response response = WalletService.deleteWallet(walletId,userToken);
        assertEquals(response.statusCode(), 202, "The status code should be 202 and the wallet has been deleted successfully");

        String walletStatus = WalletService.getWallet(walletId,userToken).as(Wallet.class).getStatus();

        assertEquals(walletStatus, "deleted", "The wallet status should be deleted");
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
}

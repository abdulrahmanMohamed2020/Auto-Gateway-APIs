package tests.folders.invalidscenarios;

import com.github.javafaker.Faker;
import controllers.FileService;
import controllers.UserService;
import datageneration.UserDataGeneration;
import io.restassured.response.Response;
import models.users.User;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static org.testng.Assert.assertEquals;

public class CreateInvalidFolder {

    private String userToken, walletId;

    @Test(description = "Verify Response Is 400 bad request When Creating A New Folder With Empty Name")
    public void verifyResponseIs400WhenCreatingFolderWithEmptyName() {
        createUser();

        Map<String,String> folderData = new HashMap<>();
        folderData.put("name", new Faker().letterify(""));

        Response response = FileService.createFolder(folderData,walletId,userToken);

        assertEquals(response.statusCode(), 400, "The status code should be 400 bad request as the folder name is empty");
    }

    @Test(description = "Verify Response Is 404 When Creating A New Folder By Passing Invalid Or Empty Wallet Id In Request URL")
    public void verifyResponseIs404WhenCreatingFolderBypassingEmptyWalletID() {
        createUser();

        Map<String,String> folderData = new HashMap<>();
        folderData.put("name", new Faker().letterify("???????????"));

        Response response = FileService.createFolder(folderData,"",userToken);

        assertEquals(response.statusCode(), 404, "The status code should be 404 Not Found as the Wallet Id is empty");
    }

    @Test(description = "Verify Response Is 403 When Creating A New Folder With Invalid User Token")
    public void verifyResponseIs403WhenCreatingFolderWithInvalidUserToken() {
        createUser();

        Map<String,String> folderData = new HashMap<>();
        folderData.put("name", new Faker().letterify("???????????"));

        Response response = FileService.createFolder(folderData,walletId,"INVALID_TOKEN");

        assertEquals(response.statusCode(), 403, "The status code should be 403 Forbidden as the user token is invalid");
    }

    @Test(description = "Verify Response Is 409 Conflict When Creating A New Folder With Name Already Exists")
    public void verifyResponseIs409WhenCreatingFolderWithNameAlreadyExists() {
        createUser();

        Map<String,String> folderData = new HashMap<>();
        folderData.put("name", new Faker().letterify("?????????"));

        FileService.createFolder(folderData,walletId,userToken);
        Response response = FileService.createFolder(folderData,walletId,userToken);

        assertEquals(response.statusCode(), 409, "The status code should be 409 Conflict as this folder name already exists");
    }

    private void createUser() {
        Response response = UserService.createUser(UserDataGeneration.createFullUserData());

        assertEquals(response.statusCode(), 201, "The status code should be 201 and the user created successfully");

        userToken = response.as(User.class).getJwtAccessToken();
        walletId = response.as(User.class).getUserData().getWalletName();
    }
}

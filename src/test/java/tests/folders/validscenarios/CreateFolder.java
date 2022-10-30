package tests.folders.validscenarios;

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

public class CreateFolder {

    private String userToken, walletId;

    @Test(description = "Verify Response Is Ok When Creating A New Folder")
    public void verifyResponseIsOkWhenCreatingFolder() {
        createUser();

        Map<String,String> folderData = new HashMap<>();
        folderData.put("name", new Faker().letterify("????????????"));

        Response response = FileService.createFolder(folderData,walletId,userToken);

        assertEquals(response.statusCode(), 200, "The status code should be 200 and the Folder created successfully");
    }

    @Test(description = "Verify Response Is Ok When Creating A New Folder With Name Has Spaces")
    public void verifyResponseIsOkWhenCreatingFolderWithNameHasSpaces() {
        createUser();

        Map<String,String> folderData = new HashMap<>();
        folderData.put("name", new Faker().letterify("?????? ??????"));

        Response response = FileService.createFolder(folderData,walletId,userToken);

        assertEquals(response.statusCode(), 200, "The status code should be 200 and the Folder created successfully");
    }

    @Test(description = "Verify Response Is Ok When Creating A New Folder With Name Has Hyphen")
    public void verifyResponseIsOkWhenCreatingFolderWithNameHasHyphen() {
        createUser();

        Map<String,String> folderData = new HashMap<>();
        folderData.put("name", new Faker().letterify("??????-??????"));

        Response response = FileService.createFolder(folderData,walletId,userToken);

        assertEquals(response.statusCode(), 200, "The status code should be 200 and the Folder created successfully");
    }

    private void createUser() {
        Response response = UserService.createUser(UserDataGeneration.createFullUserData());

        assertEquals(response.statusCode(), 201, "The status code should be 201 and the user created successfully");

        userToken = response.as(User.class).getJwtAccessToken();
        walletId = response.as(User.class).getUserData().getWalletName();
    }
}

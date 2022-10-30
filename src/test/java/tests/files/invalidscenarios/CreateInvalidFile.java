package tests.files.invalidscenarios;

import controllers.FileService;
import controllers.UserService;
import datageneration.FileDataGeneration;
import datageneration.UserDataGeneration;
import io.restassured.response.Response;
import models.users.User;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class CreateInvalidFile {

    private String userToken, walletId;

    @Test(description = "Verify Response Is 400 When Creating File With Missing Mandatory Data (File Name)")
    public void verifyResponseIs400WhenCreatingFileWithMissingMandatoryData() {
        createUser();
        Response response = FileService.createFile(FileDataGeneration.createFileDataWithoutName(),walletId,userToken);

        assertEquals(response.statusCode(), 400, "The status code should be 400 as the File name is missing");
    }

    private void createUser() {
        Response response = UserService.createUser(UserDataGeneration.createFullUserData());

        assertEquals(response.statusCode(), 201, "The status code should be 201 and the user created successfully");

        userToken = response.as(User.class).getJwtAccessToken();
        walletId = response.as(User.class).getUserData().getWalletName();
    }
}

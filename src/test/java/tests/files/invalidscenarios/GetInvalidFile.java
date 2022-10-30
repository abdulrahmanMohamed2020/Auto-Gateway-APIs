package tests.files.invalidscenarios;

import controllers.FileService;
import controllers.UserService;
import datageneration.FileDataGeneration;
import datageneration.UserDataGeneration;
import io.restassured.response.Response;
import models.files.File;
import models.users.User;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class GetInvalidFile {

    private String userToken, fileId, walletId;

    @Test(description = "Verify Response Is 403 When Getting File With Invalid User Token")
    public void verifyResponseIs403WhenGettingFileWithInvalidUserToken() {
        createUser();
        createFile();
        Response response = FileService.getFile(fileId,walletId,"INVALID_TOKEN");

        assertEquals(response.statusCode(), 403, "The status code should be 403 as the user token in invalid");
    }

    @Test(description = "Verify Response Is 400 When Getting File With Empty File Id")
    public void verifyResponseIs400WhenGettingFileWithEmptyFileId() {
        createUser();
        createFile();
        Response response = FileService.getFile("",walletId,userToken);

        assertEquals(response.statusCode(), 400, "The status code should be 400 bad request as the file Id is empty");
    }

    private void createUser() {
        Response response = UserService.createUser(UserDataGeneration.createFullUserData());

        assertEquals(response.statusCode(), 201, "The status code should be 201 and the user created successfully");

        userToken = response.as(User.class).getJwtAccessToken();
        walletId = response.as(User.class).getUserData().getWalletName();
    }

    private void createFile() {
        Response response = FileService.createFile(FileDataGeneration.createFileData(),walletId,userToken);

        assertEquals(response.statusCode(), 200, "The status code should be 200 and the File created successfully");

        fileId = response.as(File.class).getFileId();
    }
}

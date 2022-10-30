package tests.files.validscenarios;

import controllers.FileService;
import controllers.UserService;
import datageneration.FileDataGeneration;
import datageneration.UserDataGeneration;
import io.restassured.response.Response;
import models.files.File;
import models.users.User;
import org.testng.annotations.Test;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.testng.Assert.assertEquals;

public class GetFile {

    private String userToken, fileId, walletId;

    @Test(description = "Verify Response Is Ok When Getting File")
    public void verifyResponseIsOkWhenGettingFile() {
        createUser();
        createFile();
        Response response = FileService.getFile(fileId,walletId,userToken);

        assertEquals(response.statusCode(), 200, "The status code should be 200 and the File returned successfully");
    }

    @Test(description = "Verify Response Schema(All Required Data) is Returned Successfully When Getting File")
    public void verifyResponseSchemaWhenGettingFile() {
        createUser();
        createFile();
        Response response = FileService.getFile(fileId,walletId,userToken);

        response.then().assertThat().body(matchesJsonSchemaInClasspath("schema/getFile.json"));
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

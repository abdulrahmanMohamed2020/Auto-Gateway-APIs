package tests.files.validscenarios;

import controllers.FileService;
import controllers.UserService;
import datageneration.FileDataGeneration;
import datageneration.UserDataGeneration;
import io.restassured.response.Response;
import models.users.User;
import org.testng.annotations.Test;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.testng.Assert.assertEquals;

public class CreateFile {

    private String userToken, walletId;

    @Test(description = "Verify Response Is Ok When Creating File With All The Mandatory And Valid Data")
    public void verifyResponseIsOkWhenCreatingFileWithAllTheMandatoryAndValidData() {
        createUser();
        Response response = FileService.createFile(FileDataGeneration.createFileData(),walletId,userToken);

        assertEquals(response.statusCode(), 200, "The status code should be 200 and the File created successfully");
    }

    @Test(description = "Verify Response Schema(All Required Data) is Returned Successfully When Creating New File")
    public void verifyResponseSchemaWhenCreatingFileWithAllTheMandatoryAndValidData() {
        createUser();
        Response response = FileService.createFile(FileDataGeneration.createFileData(),walletId,userToken);

        assertEquals(response.statusCode(), 200, "The status code should be 200 and the File created successfully");
        response.then().assertThat().body(matchesJsonSchemaInClasspath("schema/createFile.json"));
    }

    private void createUser() {
        Response response = UserService.createUser(UserDataGeneration.createFullUserData());

        assertEquals(response.statusCode(), 201, "The status code should be 201 and the user created successfully");

        userToken = response.as(User.class).getJwtAccessToken();
        walletId = response.as(User.class).getUserData().getWalletName();
    }
}

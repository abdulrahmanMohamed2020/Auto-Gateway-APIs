package tests.nfts.validscenarios;

import controllers.FileService;
import controllers.NftService;
import controllers.UserService;
import datageneration.FileDataGeneration;
import datageneration.NftDataGeneration;
import datageneration.UserDataGeneration;
import io.restassured.response.Response;
import models.files.File;
import models.nfts.NftData;
import models.users.User;
import org.testng.annotations.Test;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.testng.Assert.assertEquals;

public class CreateNft {

    private String userToken, fileId, filePath, walletId;

    @Test(description = "Verify Response Is Ok When Creating Nft With All The Mandatory And Valid Data")
    public void verifyResponseIsOkWhenCreatingNftWithAllTheMandatoryAndValidData() {
        createUser();
        createFile();
        Response response = createNft();

        assertEquals(response.statusCode(), 200, "The status code should be 200 and the NFT created successfully");
    }

    @Test(description = "Verify Response Schema(All Required Data) is Returned Successfully When Creating Nft With All The Mandatory And Valid Data")
    public void verifyResponseSchemaWhenCreatingNftWithAllTheMandatoryAndValidData() {
        createUser();
        createFile();
        Response response = createNft();

        assertEquals(response.statusCode(), 200, "The status code should be 200 and the NFT created successfully");
        response.then().assertThat().body(matchesJsonSchemaInClasspath("schema/createNft.json"));
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
        filePath = response.as(File.class).getUrl();
    }

    private Response createNft() {
        return NftService.createNft(NftDataGeneration.createNftData(fileId,filePath),userToken);
    }
}

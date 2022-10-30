package tests.nfts.validscenarios;

import controllers.FileService;
import controllers.NftService;
import controllers.UserService;
import datageneration.FileDataGeneration;
import datageneration.NftDataGeneration;
import datageneration.UserDataGeneration;
import io.restassured.response.Response;
import models.files.File;
import models.nfts.Nft;
import models.users.User;
import org.testng.annotations.Test;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.testng.Assert.assertEquals;

public class GetNft {

    private String userToken,nftId, fileId, filePath, walletId;

    @Test(description = "Verify Response Is Ok When Getting Nft")
    public void verifyResponseIsOkWhenGettingNft() {
        createUser();
        createFile();
        createNft();
        Response response = NftService.getNft(nftId,userToken);

        assertEquals(response.statusCode(), 200, "The status code should be 200 and the NFT returned successfully");
    }

    @Test(description = "Verify Response Schema And The Required Data Is Returned Successfully When Getting Nft")
    public void verifyResponseSchemaAndTheRequiredDataIsReturnedSuccessfullyWhenGettingNft() {
        createUser();
        createFile();
        createNft();
        Response response = NftService.getNft(nftId,userToken);

        assertEquals(response.statusCode(), 200, "The status code should be 200 and the NFT returned successfully");
        response.then().assertThat().body(matchesJsonSchemaInClasspath("schema/getNft.json"));
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
    private void createNft() {
        Response response = NftService.createNft(NftDataGeneration.createNftData(fileId,filePath),userToken);
        assertEquals(response.statusCode(), 200, "The status code should be 200 and the NFT created successfully");
        nftId = response.as(Nft.class).getData().getNftId();
    }
}

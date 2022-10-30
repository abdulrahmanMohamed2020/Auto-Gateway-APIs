package tests.nfts.validscenarios;

import controllers.FileService;
import controllers.NftService;
import controllers.UserService;
import datageneration.FileDataGeneration;
import datageneration.NftDataGeneration;
import datageneration.UserDataGeneration;
import io.restassured.response.Response;
import models.files.File;
import models.users.User;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class ListAllNfts {

    private String userToken, fileId, filePath, walletId;

    @Test(description = "Verify Response Is Ok When Listing All Nfts On User")
    public void verifyResponseIsOkWhenListingAllNftsOnUser() {
        createUser();
        createFile();
        createNft();
        Response response = NftService.getAllNftsDetailsOfAnUser(userToken);

        assertEquals(response.statusCode(), 200, "The status code should be 200 and the All NFTs returned successfully");
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
    }

}

package tests.nfts.validscenarios;

import com.github.javafaker.Faker;
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

import java.util.HashMap;
import java.util.Map;

import static org.testng.Assert.assertEquals;

public class UpdateNft {

    private String userToken,nftId, fileId, filePath, walletId;

    @Test(description = "Verify Response Is 200 Ok When Updating Nft Title")
    public void verifyResponseIsOkWhenUpdatingNftTitle() {
        createUser();
        createFile();
        createNft();

        Map<String,String> updatedTitle = new HashMap<>();
        updatedTitle.put("title",new Faker().letterify("????????"));

        Response response = NftService.updateNft(nftId,userToken,updatedTitle);

        assertEquals(response.statusCode(), 200, "The status code should be 200 and the NFT Title updated successfully");
    }

    @Test(description = "Verify Response Is 200 Ok When Updating Nft Description")
    public void verifyResponseIsOkWhenUpdatingNftDescription() {
        createUser();
        createFile();
        createNft();

        Map<String,String> updatedTitle = new HashMap<>();
        updatedTitle.put("description",new Faker().letterify("??????????"));

        Response response = NftService.updateNft(nftId,userToken,updatedTitle);

        assertEquals(response.statusCode(), 200, "The status code should be 200 and the NFT Description updated successfully");
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

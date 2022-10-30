package tests.nfts.invalidscenarios;

import controllers.NftService;
import controllers.UserService;
import datageneration.NftDataGeneration;
import datageneration.UserDataGeneration;
import io.restassured.response.Response;
import models.nfts.NftData;
import models.users.User;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class CreateInvalidNft {

    private String userToken;

    @Test(description = "Verify Response Is 403 When Creating Nft With Invalid User Token")
    public void verifyResponseIs403WhenCreatingNftWithInvalidUserToken() {

        Response response = createNft(NftDataGeneration.createInvalidNftData(),"INVALID_TOKEN");

        assertEquals(response.statusCode(), 403, "The status code should be 403 Forbidden");
    }

    @Test(description = "Verify Response Is 403 When Creating Nft With Empty User Token")
    public void verifyResponseIs403WhenCreatingNftWithEmptyUserToken() {

        Response response = createNft(NftDataGeneration.createInvalidNftData(),"");

        assertEquals(response.statusCode(), 403, "The status code should be 403 Forbidden");
    }

    @Test(description = "Verify Response Is 400 When Creating Nft With Empty Title")
    public void verifyResponseIs400WhenCreatingNftWithEmptyTitle() {
        createUser();
        Response response = createNft(NftDataGeneration.createNftDataWithEmptyTitle(),userToken);

        assertEquals(response.statusCode(), 400, "The status code should be 400 as the Title is empty");
    }

    @Test(description = "Verify Response Is 400 When Creating Nft With Empty File Path")
    public void verifyResponseIs400WhenCreatingNftWithEmptyFilePath() {
        createUser();
        Response response = createNft(NftDataGeneration.createNftDataWithEmptyFilePath(),userToken);

        assertEquals(response.statusCode(), 400, "The status code should be 400 as the File Path is empty");
    }

    @Test(description = "Verify Response Is 400 When Creating Nft With Empty Category Id")
    public void verifyResponseIs400WhenCreatingNftWithEmptyCategoryId() {
        createUser();
        Response response = createNft(NftDataGeneration.createNftDataWithEmptyCategoryId(),userToken);

        assertEquals(response.statusCode(), 400, "The status code should be 400 as the Category Id is empty");
    }

    @Test(description = "Verify Response Is 400 When Creating Nft Without Sending Title")
    public void verifyResponseIs400WhenCreatingNftWithoutSendingTitle() {
        createUser();
        Response response = createNft(NftDataGeneration.createNftDataWithoutTitle(),userToken);

        assertEquals(response.statusCode(), 400, "The status code should be 400 as the Title is missing");
    }

    @Test(description = "Verify Response Is 400 When Creating Nft Without Sending File Path")
    public void verifyResponseIs400WhenCreatingNftWithoutSendingFilePath() {
        createUser();
        Response response = createNft(NftDataGeneration.createNftDataWithoutFilePath(),userToken);

        assertEquals(response.statusCode(), 400, "The status code should be 400 as the File Path is missing");
    }

    @Test(description = "Verify Response Is 400 When Creating Nft Without Sending Category Id")
    public void verifyResponseIs400WhenCreatingNftWithoutSendingCategoryId() {
        createUser();
        Response response = createNft(NftDataGeneration.createNftDataWithoutCategoryId(),userToken);

        assertEquals(response.statusCode(), 400, "The status code should be 400 as the Category Id is missing");
    }

    @Test(description = "Verify Response Is 400 When Creating Nft With Sending More Than 50 Character In Title")
    public void verifyResponseIs400WhenCreatingNftWithSendingMoreThan50CharacterInTitle() {
        createUser();
        Response response = createNft(NftDataGeneration.createNftDataWithTitleMoreThan50Character(),userToken);

        assertEquals(response.statusCode(), 400, "The status code should be 400 as title length is 51 character");
    }

    private Response createUser() {
        Response response = UserService.createUser(UserDataGeneration.createFullUserData());

        assertEquals(response.statusCode(), 201, "The status code should be 201 and the user created successfully");

        userToken = response.as(User.class).getJwtAccessToken();

        return response;
    }

    private Response createNft(NftData nftData, String userToken) {
        return NftService.createNft(nftData,userToken);
    }
}

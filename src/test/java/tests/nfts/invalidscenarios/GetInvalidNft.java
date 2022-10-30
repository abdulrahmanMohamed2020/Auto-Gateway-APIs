package tests.nfts.invalidscenarios;

import controllers.NftService;
import controllers.UserService;
import datageneration.NftDataGeneration;
import datageneration.UserDataGeneration;
import io.restassured.response.Response;
import models.nfts.Nft;
import models.users.User;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class GetInvalidNft {

    private String userToken,nftId;

    @Test(description = "Verify Response Is 401 When Getting Nft With Invalid User Token")
    public void verifyResponseIs401WhenGettingNftWithInvalidUserToken() {

        createUser();
        createNft();

        Response response = NftService.getNft(nftId,"INVALID_TOKEN");

        assertEquals(response.statusCode(), 401, "The status code should be 401 Unauthorized");
    }

    @Test(description = "Verify Response Is 401 When Getting Nft With Empty User Token")
    public void verifyResponseIs401WhenGettingNftWithEmptyUserToken() {

        createUser();
        createNft();

        Response response = NftService.getNft(nftId,"");

        assertEquals(response.statusCode(), 401, "The status code should be 401 Unauthorized");
    }

    @Test(description = "Verify Response Is 404 When Getting Nft With Invalid Nft Id Or Not Exist")
    public void verifyResponseIs404WhenGettingNftWithInvalidNftIdOrNotExist() {

        createUser();

        Response response = NftService.getNft("INVALID_NFT_ID",userToken);

        assertEquals(response.statusCode(), 404, "The status code should be 404 as the NFT id is not found");
    }

    @Test(description = "Verify Response Is 400 When Getting Nft With Empty Nft Id")
    public void verifyResponseIs400WhenGettingNftWithEmptyNftId() {

        createUser();

        Response response = NftService.getNft("",userToken);

        assertEquals(response.statusCode(), 400, "The status code should be 400 as the NFT id is empty");
    }

    @Test(description = "Verify Response Is 400 When Getting Nft With Empty Nft Id And User Token")
    public void verifyResponseIs400WhenGettingNftWithEmptyNftIdAndUserToken() {

        Response response = NftService.getNft("","");

        assertEquals(response.statusCode(), 400, "The status code should be 400 as the NFT id and User Token are empty");
    }

    private void createUser() {
        Response response = UserService.createUser(UserDataGeneration.createFullUserData());

        assertEquals(response.statusCode(), 201, "The status code should be 201 and the user created successfully");

        userToken = response.as(User.class).getJwtAccessToken();
    }

    private void createNft() {
        Response response = NftService.createNft(NftDataGeneration.createInvalidNftData(),userToken);
        assertEquals(response.statusCode(), 200, "The status code should be 200 and the NFT created successfully");
        nftId = response.as(Nft.class).getData().getNftId();
    }
}

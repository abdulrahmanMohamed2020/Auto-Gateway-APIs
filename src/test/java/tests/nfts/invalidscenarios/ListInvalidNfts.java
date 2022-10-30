package tests.nfts.invalidscenarios;

import controllers.NftService;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class ListInvalidNfts {

    @Test(description = "Verify Response Is 401 When Listing All Nfts With Invalid User Token")
    public void verifyResponseIs401WhenListingAllNftsWithInvalidUserToken() {
        Response response = NftService.getAllNftsDetailsOfAnUser("INVALID_TOKEN");

        assertEquals(response.statusCode(), 401, "The status code should be 401 Unauthorized");
    }

    @Test(description = "Verify Response Is 401 When Listing All Nfts With Empty User Token")
    public void verifyResponseIs401WhenListingAllNftsWithEmptyUserToken() {
        Response response = NftService.getAllNftsDetailsOfAnUser("");

        assertEquals(response.statusCode(), 401, "The status code should be 401 Unauthorized");
    }

}

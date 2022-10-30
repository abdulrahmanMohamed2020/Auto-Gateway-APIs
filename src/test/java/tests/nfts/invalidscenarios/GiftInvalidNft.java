package tests.nfts.invalidscenarios;

import com.github.javafaker.Faker;
import controllers.ContactsService;
import controllers.NftService;
import controllers.UserService;
import datageneration.ContactDataGeneration;
import datageneration.NftDataGeneration;
import datageneration.UserDataGeneration;
import io.restassured.response.Response;
import models.contacts.Contact;
import models.nfts.Nft;
import models.users.User;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.testng.Assert.assertEquals;

public class GiftInvalidNft {

    private String userToken,userId,nftId,contactId;

    @Test(description = "Verify Response Is 404 When Gifting Nft By Invalid Nft Id Or Not Found")
    public void verifyResponseIs404WhenGiftingNftByInvalidNftId() {

        Map<String,List<String>> contactIdsMap = new HashMap<>();
        List<String> contactIdsList = new ArrayList<>();

        createUser();
        createContact();

        contactIdsList.add(contactId);

        contactIdsMap.put("contactIds", contactIdsList);

        Response response = NftService.giftNft(contactIdsMap,userToken,"INVALID_NFT_ID");

        assertEquals(response.statusCode(), 404, "The status code should be 404 as the NFT ID is invalid or not found");
    }

    @Test(description = "Verify Response Is 400 When Gifting Nft By Empty Nft Id")
    public void verifyResponseIs400WhenGiftingNftByEmptyNftId() {

        Map<String,List<String>> contactIdsMap = new HashMap<>();
        List<String> contactIdsList = new ArrayList<>();

        createUser();
        createContact();

        contactIdsList.add(contactId);

        contactIdsMap.put("contactIds", contactIdsList);

        Response response = NftService.giftNft(contactIdsMap,userToken,"");

        assertEquals(response.statusCode(), 400, "The status code should be 400 bad request as the NFT ID is empty");
    }

    @Test(description = "Verify Response Is 403 When Gifting Nft With Invalid User Token")
    public void verifyResponseIs403WhenGiftingNftWithInvalidUserToken() {

        Map<String,List<String>> contactIdsMap = new HashMap<>();
        List<String> contactIdsList = new ArrayList<>();

        createUser();
        createNft();
        createContact();

        contactIdsList.add(contactId);

        contactIdsMap.put("contactIds", contactIdsList);

        Response response = NftService.giftNft(contactIdsMap,"INVALID_TOKEN",nftId);

        assertEquals(response.statusCode(), 403, "The status code should be 403 Forbidden");
    }

    @Test(description = "Verify Response Is 403 When Gifting Nft With Empty User Token")
    public void verifyResponseIs403WhenGiftingNftWithEmptyUserToken() {

        Map<String,List<String>> contactIdsMap = new HashMap<>();
        List<String> contactIdsList = new ArrayList<>();

        createUser();
        createNft();
        createContact();

        contactIdsList.add(contactId);

        contactIdsMap.put("contactIds", contactIdsList);

        Response response = NftService.giftNft(contactIdsMap,"",nftId);

        assertEquals(response.statusCode(), 403, "The status code should be 403 Forbidden");
    }

    @Test(description = "Verify Response Is 400 When Gifting Nft With Empty Nft Id And User Token")
    public void verifyResponseIs400WhenGiftingNftWithEmptyNftIdAndUserToken() {

        Map<String,List<String>> contactIdsMap = new HashMap<>();
        List<String> contactIdsList = new ArrayList<>();

        contactIdsList.add(new Faker().letterify("?????????????????????"));

        contactIdsMap.put("contactIds", contactIdsList);

        Response response = NftService.giftNft(contactIdsMap,"","");

        assertEquals(response.statusCode(), 400, "The status code should be 400 bad request as the NFT id and User Token are empty");
    }

    @Test(description = "Verify Response Is 400 When Gifting Nft With Empty Contact Id")
    public void verifyResponseIs400WhenGiftingNftWithEmptyContactId() {

        Map<String,List<String>> contactIdsMap = new HashMap<>();
        List<String> contactIdsList = new ArrayList<>();

        createUser();
        createNft();

        contactIdsList.add("");

        contactIdsMap.put("contactIds", contactIdsList);

        Response response = NftService.giftNft(contactIdsMap,userToken,nftId);

        assertEquals(response.statusCode(), 400, "The status code should be 400 bad request as the Contact Id is empty");
    }

    @Test(description = "Verify Response Is 404 When Gifting Nft With Invalid Contact Id Or Not Found")
    public void verifyResponseIs404WhenGiftingNftWithInvalidContactId() {

        Map<String,List<String>> contactIdsMap = new HashMap<>();
        List<String> contactIdsList = new ArrayList<>();

        createUser();
        createNft();

        contactIdsList.add(new Faker().letterify("?????????????????????"));

        contactIdsMap.put("contactIds", contactIdsList);

        Response response = NftService.giftNft(contactIdsMap,userToken,nftId);

        assertEquals(response.statusCode(), 404, "The status code should be 404 as the Contact Id is invalid or not found");
    }

    @Test(description = "Verify That The Response Is 401 When Gifting Nft Does Not Belong To My Wallet")
    public void verifyResponseIs401WhenGiftingNftDoesNotBelongToMe() {

        Map<String,List<String>> contactIdsMap = new HashMap<>();
        List<String> contactIdsList = new ArrayList<>();

        createUser();
        createNft();
        createUser();

        contactIdsList.add(new Faker().letterify("?????????????????????"));

        contactIdsMap.put("contactIds", contactIdsList);

        Response response = NftService.giftNft(contactIdsMap,userToken,nftId);

        assertEquals(response.statusCode(), 401, "The status code should be 401 as the NFT does not belong to this user");
    }

    private void createUser() {
        Response response = UserService.createUser(UserDataGeneration.createFullUserData());

        assertEquals(response.statusCode(), 201, "The status code should be 201 and the user created successfully");

        userToken = response.as(User.class).getJwtAccessToken();
        userId = response.as(User.class).getUserData().getUserId();
    }

    private void createNft() {
        Response response = NftService.createNft(NftDataGeneration.createInvalidNftData(),userToken);
        assertEquals(response.statusCode(), 200, "The status code should be 200 and the NFT created successfully");
        nftId = response.as(Nft.class).getData().getNftId();
    }

    private void createContact() {
        Response response = ContactsService
                .createContact(ContactDataGeneration.createContact(),userId,userToken);

        assertEquals(response.statusCode(), 201, "The status code should be 201 and the Contact added successfully");
        contactId = response.as(Contact.class).getData().getContactJSON().getContactId();
    }
}

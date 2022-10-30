package tests.nfts.validscenarios;

import controllers.ContactsService;
import controllers.FileService;
import controllers.NftService;
import controllers.UserService;
import datageneration.ContactDataGeneration;
import datageneration.FileDataGeneration;
import datageneration.NftDataGeneration;
import datageneration.UserDataGeneration;
import io.restassured.response.Response;
import models.contacts.Contact;
import models.files.File;
import models.nfts.Nft;
import models.users.User;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.testng.Assert.assertEquals;

public class GiftNft {

    private String userToken,userId,nftId,contactId, fileId, filePath, walletId;

    @Test(description = "Verify Response Is Ok When gifting an Nft To One Contact")
    public void verifyResponseIsOkWhenGiftingNft() {

        Map<String,List<String>> contactIdsMap = new HashMap<>();
        List<String> contactIdsList = new ArrayList<>();

        createUser();
        createFile();
        createNft();
        createContact();

        contactIdsList.add(contactId);

        contactIdsMap.put("contactIds", contactIdsList);

        Response response = NftService.giftNft(contactIdsMap,userToken,nftId);

        assertEquals(response.statusCode(), 200, "The status code should be 200 and the NFT sent successfully");
    }

    @Test(description = "Verify Response Is Ok When gifting an Nft To List Of Contacts")
    public void verifyResponseIsOkWhenGiftingNftToListOfContacts() {

        Map<String,List<String>> contactIdsMap = new HashMap<>();
        List<String> contactIdsList = new ArrayList<>();

        createUser();
        createFile();
        createNft();

        createContact();
        contactIdsList.add(contactId);
        createContact();
        contactIdsList.add(contactId);

        contactIdsMap.put("contactIds", contactIdsList);

        Response response = NftService.giftNft(contactIdsMap,userToken,nftId);

        assertEquals(response.statusCode(), 200, "The status code should be 200 and the NFT sent successfully");
    }

    private void createUser() {
        Response response = UserService.createUser(UserDataGeneration.createFullUserData());

        assertEquals(response.statusCode(), 201, "The status code should be 201 and the user created successfully");

        userToken = response.as(User.class).getJwtAccessToken();
        userId = response.as(User.class).getUserData().getUserId();
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

    private void createContact() {
        Response response = ContactsService
                .createContact(ContactDataGeneration.createContact(),userId,userToken);

        assertEquals(response.statusCode(), 201, "The status code should be 201 and the Contact added successfully");
        contactId = response.as(Contact.class).getData().getContactJSON().getContactId();
    }
}

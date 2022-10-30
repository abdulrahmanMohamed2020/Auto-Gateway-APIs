package tests.contacts.validscenarios;

import controllers.ContactsService;
import controllers.UserService;
import datageneration.ContactDataGeneration;
import datageneration.UserDataGeneration;
import io.restassured.response.Response;
import models.contacts.ContactJSON;
import models.users.User;
import org.testng.annotations.Test;

import java.util.List;

import static org.testng.Assert.assertEquals;

public class ImportContacts {

    private String userToken,userId;

    @Test(description = "Verify Response Is 200 Successfully When Importing X Contacts")
    public void verifyResponseIs200SuccessfullyWhenImportContact() {
        createUser();

        Response response = importContacts(ContactDataGeneration.importContacts(2));

        System.out.println("The User Id: "+userId);
        System.out.println("The User Token: "+userToken);

        assertEquals(response.statusCode(), 200, "The status code should be 200");
    }

    private void createUser() {
        Response response = UserService.createUser(UserDataGeneration.createFullUserData());

        assertEquals(response.statusCode(), 201, "The status code should be 201 and the user created successfully");

        userToken = response.as(User.class).getJwtAccessToken();
        userId = response.as(User.class).getUserData().getUserId();
    }

    private Response importContacts(List<ContactJSON> contactJSON) {
        return ContactsService.importContacts(contactJSON,userToken,userId);
    }
}

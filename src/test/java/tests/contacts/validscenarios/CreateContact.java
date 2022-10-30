package tests.contacts.validscenarios;

import controllers.ContactsService;
import controllers.UserService;
import datageneration.ContactDataGeneration;
import datageneration.UserDataGeneration;
import io.restassured.response.Response;
import models.contacts.ContactJSON;
import models.users.User;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class CreateContact {

    private String userToken,userId;

    @Test(description = "Verify Response Is Ok When Creating Contact With All The Mandatory And Valid Data")
    public void verifyResponseIsOkWhenCreatingContactWithAllTheMandatoryAndValidData() {
        createUser();
        Response response = createContact(ContactDataGeneration.createContact());

        assertEquals(response.statusCode(), 201, "The status code should be 201 and the Contact added successfully");
    }

    @Test(description = "Verify Response Is 201 Successfully When Creating Contact With Phone Equals 14 Number")
    public void verifyResponseIs201SuccessfullyWhenCreatingContactWithPhoneEquals14Number() {
        createUser();
        Response response = createContact(ContactDataGeneration.createContactWithPhoneEquals14Number());

        assertEquals(response.statusCode(), 201, "The status code should be 201 as the Phone length is 14 number");
    }

    private void createUser() {
        Response response = UserService.createUser(UserDataGeneration.createFullUserData());

        assertEquals(response.statusCode(), 201, "The status code should be 201 and the user created successfully");

        userToken = response.as(User.class).getJwtAccessToken();
        userId = response.as(User.class).getUserData().getUserId();
    }

    private Response createContact(ContactJSON contactJSON) {
        return ContactsService.createContact(contactJSON,userId,userToken);
    }
}

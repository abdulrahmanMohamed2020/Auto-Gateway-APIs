package tests.contacts.invalidscenarios;

import controllers.ContactsService;
import controllers.UserService;
import datageneration.ContactDataGeneration;
import datageneration.UserDataGeneration;
import io.restassured.response.Response;
import models.contacts.Contact;
import models.users.User;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class GetInvalidContact {

    private String userId,userToken,contactId;

    @Test(description = "Verify Response Is 403 When Getting Contact With Invalid User Token")
    public void verifyResponseIs403WhenGettingContactWithInvalidUserToken() {

        createUser();
        createContact();

        Response response = ContactsService.getContact(contactId,"INVALID_TOKEN");

        assertEquals(response.statusCode(), 403, "The status code should be 403 Forbidden");
    }

    @Test(description = "Verify Response Is 403 When Getting Contact With Empty User Token")
    public void verifyResponseIs403WhenGettingContactWithEmptyUserToken() {

        createUser();
        createContact();

        Response response = ContactsService.getContact(contactId,"");

        assertEquals(response.statusCode(), 403, "The status code should be 403 Forbidden");
    }

    @Test(description = "Verify Response Is 400 When Getting Contact With Empty Contact Id")
    public void verifyResponseIs400WhenGettingContactWithEmptyContactId() {

        createUser();

        Response response = ContactsService.getContact("",userToken);

        assertEquals(response.statusCode(), 400, "The status code should be 400 as the contact id is empty");
    }

    @Test(description = "Verify Response Is 403 When Getting Contact With Empty Contact Id And User Token")
    public void verifyResponseIs403WhenGettingContactWithEmptyContactIdAndUserToken() {

        Response response = ContactsService.getContact("","");

        assertEquals(response.statusCode(), 403, "The status code should be 403 Forbidden");
    }

    @Test(description = "Verify Response Is 404 When Getting Contact With Invalid Contact Id Or Not Exist")
    public void verifyResponseIs404WhenGettingContactWithInvalidContactIdOrNotExist() {

        createUser();

        Response response = ContactsService.getContact("INVALID_CONTACT_ID",userToken);

        assertEquals(response.statusCode(), 404, "The status code should be 404 as the contact id is invalid or not found");
    }

    private void createUser() {
        Response response = UserService.createUser(UserDataGeneration.createFullUserData());

        assertEquals(response.statusCode(), 201, "The status code should be 201 and the user created successfully");

        userToken = response.as(User.class).getJwtAccessToken();
        userId = response.as(User.class).getUserData().getUserId();
    }

    private void createContact() {
        Response response = ContactsService.createContact(ContactDataGeneration.createContact(),userId,userToken);

        assertEquals(response.statusCode(), 201, "The status code should be 201 and the contact created successfully");

        contactId = response.as(Contact.class).getData().getContactJSON().getContactId();
    }
}

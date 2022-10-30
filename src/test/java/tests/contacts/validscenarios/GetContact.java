package tests.contacts.validscenarios;

import controllers.ContactsService;
import controllers.UserService;
import datageneration.ContactDataGeneration;
import datageneration.UserDataGeneration;
import io.restassured.response.Response;
import models.contacts.Contact;
import models.contacts.ContactJSON;
import models.users.User;
import org.testng.annotations.Test;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.testng.Assert.assertEquals;

public class GetContact {

    private String userId,userToken,contactId;

    @Test(description = "Verify Response Is Ok When Getting Contact")
    public void verifyResponseIsOkWhenGettingContact() {
        createUser();
        createContact(ContactDataGeneration.createContact());
        Response response = ContactsService.getContact(contactId,userToken);

        assertEquals(response.statusCode(), 200, "The status code should be 200 and the Contact returned successfully");
    }

    @Test(description = "Verify Response Schema And The Required Data Is Returned Successfully When Getting Contact")
    public void verifyResponseSchemaAndTheRequiredDataIsReturnedSuccessfullyWhenGettingContact() {
        createUser();
        createContact(ContactDataGeneration.createContact());
        Response response = ContactsService.getContact(contactId,userToken);

        assertEquals(response.statusCode(), 200, "The status code should be 200 and the Contact returned successfully");
        response.then().assertThat().body(matchesJsonSchemaInClasspath("schema/getContact.json"));
    }

    private void createUser() {
        Response response = UserService.createUser(UserDataGeneration.createFullUserData());

        assertEquals(response.statusCode(), 201, "The status code should be 201 and the user created successfully");

        userToken = response.as(User.class).getJwtAccessToken();
        userId = response.as(User.class).getUserData().getUserId();
    }

    private void createContact(ContactJSON contactJSON) {
        Response response = ContactsService.createContact(contactJSON,userId,userToken);

        assertEquals(response.statusCode(), 201, "The status code should be 201 and the contact created successfully");

        contactId = response.as(Contact.class).getData().getContactJSON().getContactId();
    }
}

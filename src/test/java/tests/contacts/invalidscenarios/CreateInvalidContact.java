package tests.contacts.invalidscenarios;

import controllers.ContactsService;
import controllers.UserService;
import datageneration.ContactDataGeneration;
import datageneration.UserDataGeneration;
import io.restassured.response.Response;
import models.contacts.ContactJSON;
import models.users.User;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class CreateInvalidContact {

    private String userToken,userId;

    @Test(description = "Verify Response Is 400 When Sending Request With Invalid User Id")
    public void verifyResponseIs400WhenSendingRequestWithInvalidUserId() {
        createUser();
        Response response = ContactsService.createContact(ContactDataGeneration.createContact(), "INVALID_USER_ID", userToken);

        assertEquals(response.statusCode(), 400, "The status code should be 400 as the user Id is invalid");
    }

    @Test(description = "Verify Response Is 400 When Sending Request With Empty User Id")
    public void verifyResponseIs400WhenSendingRequestWithEmptyUserId() {
        createUser();
        Response response = ContactsService.createContact(ContactDataGeneration.createContact(), "", userToken);

        assertEquals(response.statusCode(), 400, "The status code should be 400 as the user Id is empty");
    }

    @Test(description = "Verify Response Is 403 When Sending Request With Invalid Token")
    public void verifyResponseIs403WhenSendingRequestWithInvalidToken() {
        createUser();
        Response response = ContactsService.createContact(ContactDataGeneration.createContact(), userId, "INVALID_TOKEN");

        assertEquals(response.statusCode(), 403, "The status code should be 403 Forbidden");
    }

    @Test(description = "Verify Response Is 403 When Sending Request With Empty Token")
    public void verifyResponseIs403WhenSendingRequestWithEmptyToken() {
        createUser();
        Response response = ContactsService.createContact(ContactDataGeneration.createContact(), userId, "");

        assertEquals(response.statusCode(), 403, "The status code should be 403 Forbidden");
    }

    @Test(description = "Verify Response Is 400 When Creating Contact With First Name Length More Than 26 Character")
    public void verifyResponseIs400WhenCreatingContactWithFirstNameLengthMoreThan26Character() {
        createUser();
        Response response = createContact(ContactDataGeneration.createContactWithFirstNameMoreThan26Character());

        assertEquals(response.statusCode(), 400, "The status code should be 400 as the first name length is 27 character");
    }

    @Test(description = "Verify Response Is 400 When Creating Contact With First Name Length Less Than 2 Character")
    public void verifyResponseIs400WhenCreatingContactWithFirstNameLengthLessThan2Character() {
        createUser();
        Response response = createContact(ContactDataGeneration.createContactWithFirstNameLessThan2Character());

        assertEquals(response.statusCode(), 400, "The status code should be 400 as the first name length is one character");
    }

    @Test(description = "Verify Response Is 400 When Creating Contact With First Name As Numbers Format")
    public void verifyResponseIs400WhenCreatingContactWithFirstNameAsNumbersFormat() {
        createUser();
        Response response = createContact(ContactDataGeneration.createContactWithFirstNameInNumbers());

        assertEquals(response.statusCode(), 400, "The status code should be 400 as the first name is numbers");
    }

    @Test(description = "Verify Response Is 400 When Creating Contact With First Name As Spaces")
    public void verifyResponseIs400WhenCreatingContactWithFirstNameAsSpaces() {
        createUser();
        Response response = createContact(ContactDataGeneration.createContactWithFirstNameAsSpaces());

        assertEquals(response.statusCode(), 400, "The status code should be 400 as the first name is white spaces");
    }

    @Test(description = "Verify Response Is 400 When Creating Contact With Empty First Name")
    public void verifyResponseIs400WhenCreatingContactWithEmptyFirstName() {
        createUser();
        Response response = createContact(ContactDataGeneration.createContactWithEmptyFirstName());

        assertEquals(response.statusCode(), 400, "The status code should be 400 as the first name is empty");
    }

    @Test(description = "Verify Response Is 400 When Creating Contact With Last Name Length More Than 26 Character")
    public void verifyResponseIs400WhenCreatingContactWithLastNameLengthMoreThan26Character() {
        createUser();
        Response response = createContact(ContactDataGeneration.createContactWithLastNameMoreThan26Character());

        assertEquals(response.statusCode(), 400, "The status code should be 400 as the Last name length is 27 character");
    }

    @Test(description = "Verify Response Is 400 When Creating Contact With Last Name Length Less Than 2 Character")
    public void verifyResponseIs400WhenCreatingContactWithLastNameLengthLessThan2Character() {
        createUser();
        Response response = createContact(ContactDataGeneration.createContactWithLastNameLessThan2Character());

        assertEquals(response.statusCode(), 400, "The status code should be 400 as the Last name length is one character");
    }

    @Test(description = "Verify Response Is 400 When Creating Contact With Last Name As Numbers Format")
    public void verifyResponseIs400WhenCreatingContactWithLastNameAsNumbersFormat() {
        createUser();
        Response response = createContact(ContactDataGeneration.createContactWithLastNameInNumbers());

        assertEquals(response.statusCode(), 400, "The status code should be 400 as the Last name is numbers");
    }

    @Test(description = "Verify Response Is 400 When Creating Contact With Last Name As Spaces")
    public void verifyResponseIs400WhenCreatingContactWithLastNameAsSpaces() {
        createUser();
        Response response = createContact(ContactDataGeneration.createContactWithLastNameAsSpaces());

        assertEquals(response.statusCode(), 400, "The status code should be 400 as the Last name is white spaces");
    }

    @Test(description = "Verify Response Is 400 When Creating Contact With Empty Last Name")
    public void verifyResponseIs400WhenCreatingContactWithEmptyLastName() {
        createUser();
        Response response = createContact(ContactDataGeneration.createContactWithEmptyLastName());

        assertEquals(response.statusCode(), 400, "The status code should be 400 as the Last name is empty");
    }

    @Test(description = "Verify Response Is 400 When Creating Contact With Missing Mandatory Data Like First Name")
    public void verifyResponseIs400WhenCreatingContactWithMissingMandatoryDataLikeFirstName() {
        createUser();
        Response response = createContact(ContactDataGeneration.createContactWithoutFirstName());

        assertEquals(response.statusCode(), 400, "The status code should be 400 as the First name is missing");
    }

    @Test(description = "Verify Response Is 400 When Creating Contact With Invalid Email Format")
    public void verifyResponseIs400WhenCreatingContactWithInvalidEmailFormat() {
        createUser();
        Response response = createContact(ContactDataGeneration.createContactWithInvalidEmailFormat());

        assertEquals(response.statusCode(), 400, "The status code should be 400 as the Email format is invalid");
    }

    @Test(description = "Verify Response Is 400 When Creating Contact With Empty Email")
    public void verifyResponseIs400WhenCreatingContactWithEmptyEmail() {
        createUser();
        Response response = createContact(ContactDataGeneration.createContactWithEmptyEmail());

        assertEquals(response.statusCode(), 400, "The status code should be 400 as the Email is empty");
    }

    @Test(description = "Verify Response Is 400 When Creating Contact With Invalid Phone Format")
    public void verifyResponseIs400WhenCreatingContactWithInvalidPhoneFormat() {
        createUser();
        Response response = createContact(ContactDataGeneration.createContactWithInvalidPhoneFormat());

        assertEquals(response.statusCode(), 400, "The status code should be 400 as the Phone format is invalid");
    }

    @Test(description = "Verify Response Is 400 When Creating Contact With Empty Phone")
    public void verifyResponseIs400WhenCreatingContactWithEmptyPhone() {
        createUser();
        Response response = createContact(ContactDataGeneration.createContactWithEmptyPhone());

        assertEquals(response.statusCode(), 400, "The status code should be 400 as the Phone is empty");
    }

    @Test(description = "Verify Response Is 400 When Creating Contact With Phone More Than 14 Number")
    public void verifyResponseIs400WhenCreatingContactWithPhoneMoreThan14Number() {
        createUser();
        Response response = createContact(ContactDataGeneration.createContactWithPhoneMoreThan14Number());

        assertEquals(response.statusCode(), 400, "The status code should be 400 as the Phone length is 15 number");
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

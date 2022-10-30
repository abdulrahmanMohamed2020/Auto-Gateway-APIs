package tests.users.validscenarios;

import controllers.UserService;
import datageneration.UserDataGeneration;
import io.restassured.response.Response;
import models.users.User;
import models.users.UserData;
import org.testng.annotations.Test;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.testng.Assert.*;
import static org.testng.Assert.assertEquals;

public class CreateUser {

    @Test(description = "Verify Response Is 201 Created When Creating Valid User")
    public void verifyResponseIs201CreatedWhenCreatingValidUser() {
        Response response = createUser(UserDataGeneration.createFullUserData());

        assertEquals(response.statusCode(), 201, "The status code should be 201 and the user created successfully");

        //deleteUser();
    }

    @Test(description = "Verify Schema Of Response(All Required Data) Is Returned Successfully When Creating Valid User")
    public void verifySchemaOfResponseWhenCreatingValidUser() {
        Response response = createUser(UserDataGeneration.createFullUserData());

        assertEquals(response.statusCode(), 201, "The status code should be 201 and the user created successfully");
        response.then().assertThat().body(matchesJsonSchemaInClasspath("schema/createUser.json"));

        //deleteUser();
    }

    @Test(description = "Verify That The User Data Is Returned Successfully And Not Empty When I Create User")
    public void verifyThatTheUserDataIsReturnedSuccessfullyAndNotEmptyWhenICreateUser() {
        Response response = createUser(UserDataGeneration.createFullUserData());

        assertEquals(response.statusCode(), 201, "The status code should be 201 and the user created successfully");

        User actualUser = response.as(User.class);

        assertNotNull(actualUser, "The User Data data is empty");
        assertFalse(actualUser.getUserData().toString().isEmpty(),"The User Data is empty");

        //deleteUser();
    }

    @Test(description = "Verify Response Is 201 When Sending Request With First Name Has Spaces")
    public void verifyResponseIs201WhenSendingRequestWithFirstNameHasSpaces() {
        Response response = createUser(UserDataGeneration.createUserDataWithFirstNameHasSpaces());

        assertEquals(response.statusCode(), 201, "The status code should be 201 and the user created successfully");
    }

    @Test(description = "Verify Response Is 201 When Sending Request With First Name Has Hyphen")
    public void verifyResponseIs201WhenSendingRequestWithFirstNameHasHyphen() {
        Response response = createUser(UserDataGeneration.createUserDataWithFirstNameHasHyphen());

        assertEquals(response.statusCode(), 201, "The status code should be 201 and the user created successfully");
    }

    @Test(description = "Verify Response Is 201 When Sending Request With Last Name Has Spaces")
    public void verifyResponseIs201WhenSendingRequestWithLastNameHasSpaces() {
        Response response = createUser(UserDataGeneration.createUserDataWithLastNameHasSpaces());

        assertEquals(response.statusCode(), 201, "The status code should be 201 and the user created successfully");
    }

    @Test(description = "Verify Response Is 201 When Sending Request With Last Name Has Hyphen")
    public void verifyResponseIs201WhenSendingRequestWithLastNameHasHyphen() {
        Response response = createUser(UserDataGeneration.createUserDataWithLastNameHasHyphen());

        assertEquals(response.statusCode(), 201, "The status code should be 201 and the user created successfully");
    }

    @Test(description = "Verify Response Is 201 When Sending Request With Phone Number Equals 15 Number")
    public void verifyResponseIs201WhenSendingRequestWithPhoneNumberEquals15Number() {
        Response response = createUser(UserDataGeneration.createUserDataWithPhoneEquals15Number());

        assertEquals(response.statusCode(), 201, "The status code should be 201 as phone length equals 15 number");

        //deleteUser();
    }

    @Test(description = "Verify Response Is 201 When Sending Request With Phone Number Equals 10 Numbers")
    public void verifyResponseIs201WhenSendingRequestWithPhoneNumberEquals10Numbers() {
        Response response = createUser(UserDataGeneration.createUserDataWithPhoneEquals10Numbers());

        assertEquals(response.statusCode(), 201, "The status code should be 201 as phone length equals 10 numbers");

        //deleteUser();
    }

    private Response createUser(UserData userData) {
        return UserService.createUser(userData);
    }
}

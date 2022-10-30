package tests.users.invalidscenarios;

import controllers.UserService;
import datageneration.UserDataGeneration;
import io.restassured.response.Response;
import models.users.User;
import models.users.UserData;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class CreateInvalidUser {

    @Test(description = "Verify Response Is 400 When Sending Request With Missing Mandatory Data")
    public void verifyResponseIs400WhenSendingRequestWithMissingMandatoryData() {
        Response response = createUser(UserDataGeneration.createUserDataWithMissingFirstName());

        assertEquals(response.statusCode(), 400, "The status code should be 400 as First Name missing");
    }

    @Test(description = "Verify Response Is 400 When Sending Request With First Name More Than 50 Letter")
    public void verifyResponseIs400WhenSendingRequestWithFirstNameMoreThan50Letter() {
        Response response = createUser(UserDataGeneration.createUserDataWithFirstNameMoreThan50Letter());

        assertEquals(response.statusCode(), 400, "The status code should be 400 as the First Name is 51 letter");
    }

    @Test(description = "Verify Response Is 400 When Sending Request With Empty First Name")
    public void verifyResponseIs400WhenSendingRequestWithEmptyFirstName() {
        Response response = createUser(UserDataGeneration.createUserDataWithEmptyFirstName());

        assertEquals(response.statusCode(), 400, "The status code should be 400 as bad request");
    }

    @Test(description = "Verify Response Is 400 When Sending Request With Last Name More Than 50 Letter")
    public void verifyResponseIs400WhenSendingRequestWithLastNameMoreThan50Letter() {
        Response response = createUser(UserDataGeneration.createUserDataWithLastNameMoreThan50Letter());

        assertEquals(response.statusCode(), 400, "The status code should be 400 as the Last Name is 51 letter");
    }

    @Test(description = "Verify Response Is 400 When Sending Request With Empty Last Name")
    public void verifyResponseIs400WhenSendingRequestWithEmptyLastName() {
        Response response = createUser(UserDataGeneration.createUserDataWithEmptyLastName());

        assertEquals(response.statusCode(), 400, "The status code should be 400 as bad request");
    }

    @Test(description = "Verify Response Is 400 When Sending Request With Wallet Name More Than 50 Letter")
    public void verifyResponseIs400WhenSendingRequestWithWalletNameMoreThan50Letter() {
        Response response = createUser(UserDataGeneration.createUserDataWithWalletNameMoreThan50Letter());

        assertEquals(response.statusCode(), 400, "The status code should be 400 as bad request");
    }

    @Test(description = "Verify Response Is 400 When Sending Request With Empty Wallet Name")
    public void verifyResponseIs400WhenSendingRequestWithEmptyWalletName() {
        Response response = createUser(UserDataGeneration.createUserDataWithEmptyWalletName());

        assertEquals(response.statusCode(), 400, "The status code should be 400 as bad request");
    }

    @Test(description = "Verify Response Is 400 When Sending Request With Wallet Name Has Spaces")
    public void verifyResponseIs400WhenSendingRequestWithWalletNameHasSpaces() {
        Response response = createUser(UserDataGeneration.createUserDataWithWalletNameHasSpaces());

        assertEquals(response.statusCode(), 400, "The status code should be 400 as bad request");
    }

    @Test(description = "Verify Response Is 409 When Sending Request With Wallet Name Used Before")
    public void verifyResponseIs409WhenSendingRequestWithWalletNameUsedBefore() {
        User user = createUser(UserDataGeneration.createFullUserData()).as(User.class);
        Response response = createUser(UserDataGeneration.createUserDataWithWalletNameUsedBefore(user.getUserData().getWalletName()));

        assertEquals(response.statusCode(), 409, "The status code should be 409 as this wallet name already exist");
    }

    @Test(description = "Verify Response Is 400 When Sending Request With Wallet Name Does Not End With Correct Suffix")
    public void verifyResponseIs400WhenSendingRequestWithWalletNameDoesNotEndWithCorrectSuffix() {
        Response response = createUser(UserDataGeneration.createUserDataWithWalletNameDoesNotEndWithCorrectSuffix());

        assertEquals(response.statusCode(), 400, "The status code should be 400 as bad request");
    }

    @Test(description = "Verify Response Is 400 When Sending Request With Invalid Email Format")
    public void verifyResponseIs400WhenSendingRequestWithInvalidEmailFormat() {
        Response response = createUser(UserDataGeneration.createUserDataWithInvalidEmailFormat());

        assertEquals(response.statusCode(), 400, "The status code should be 400 as bad request");
    }

    @Test(description = "Verify Response Is 409 When Sending Request With Email Used Before")
    public void verifyResponseIs409WhenSendingRequestWithEmailUsedBefore() {
        User user = createUser(UserDataGeneration.createFullUserData()).as(User.class);
        Response response = createUser(UserDataGeneration.createUserDataWithEmailUsedBefore(user.getUserData().getEmail()));

        assertEquals(response.statusCode(), 409, "The status code should be 409 as this email already exist");
    }

    @Test(description = "Verify Response Is 400 When Sending Request With Missing Email And Phone")
    public void verifyResponseIs400WhenSendingRequestWithMissingEmailAndPhone() {
        Response response = createUser(UserDataGeneration.createUserDataWithMissingEmailAndPhone());

        assertEquals(response.statusCode(), 400, "The status code should be 400 as Email and Phone are missing");
    }

    @Test(description = "Verify Response Is 409 When Sending Request With Phone Used Before")
    public void verifyResponseIs409WhenSendingRequestWithPhoneUsedBefore() {
        User user = createUser(UserDataGeneration.createFullUserData()).as(User.class);
        Response response = createUser(UserDataGeneration.createUserDataWithPhoneUsedBefore(user.getUserData().getPhone()));

        assertEquals(response.statusCode(), 409, "The status code should be 409 as this phone already exist");
    }

    @Test(description = "Verify Response Is 400 When Sending Request With Country Code More Than 4 Letters")
    public void verifyResponseIs400WhenSendingRequestWithCountryCodeMoreThan4Letters() {
        Response response = createUser(UserDataGeneration.createUserDataWithCountryCodeMoreThan4Letters());

        assertEquals(response.statusCode(), 400, "The status code should be 400 as bad request");
    }

    @Test(description = "Verify Response Is 400 When Sending Request With Country Code Less Than 2 Letters")
    public void verifyResponseIs400WhenSendingRequestWithCountryCodeLessThan2Letters() {
        Response response = createUser(UserDataGeneration.createUserDataWithCountryCodeLessThan2Letters());

        assertEquals(response.statusCode(), 400, "The status code should be 400 as bad request");
    }

    @Test(description = "Verify Response Is 400 When Sending Request With Country Code Does Not Start With Correct Prefix")
    public void verifyResponseIs400WhenSendingRequestWithCountryCodeDoesNotStartWithCorrectPrefix() {
        Response response = createUser(UserDataGeneration.createUserDataWithCountryCodeDoesNotStartWithCorrectPrefix());

        assertEquals(response.statusCode(), 400, "The status code should be 400 as bad request");
    }

    @Test(description = "Verify Response Is 400 When Sending Request Without Country Code")
    public void verifyResponseIs400WhenSendingRequestWithoutCountryCode() {
        Response response = createUser(UserDataGeneration.createUserDataWithoutCountryCode());

        assertEquals(response.statusCode(), 400, "The status code should be 400 as bad request");
    }

    @Test(description = "Verify Response Is 400 When Sending Request With Phone Number Less Than 10 Numbers")
    public void verifyResponseIs400WhenSendingRequestWithPhoneNumberLessThan10Numbers() {
        Response response = createUser(UserDataGeneration.createUserDataWithPhoneLessThan10Numbers());

        assertEquals(response.statusCode(), 400, "The status code should be 400 as bad request");
    }

    @Test(description = "Verify Response Is 400 When Sending Request With Phone Number More Than 15 Number")
    public void verifyResponseIs400WhenSendingRequestWithPhoneNumberMoreThan15Number() {
        Response response = createUser(UserDataGeneration.createUserDataWithPhoneMoreThan15Number());

        assertEquals(response.statusCode(), 400, "The status code should be 400 as phone length is 15 number");
    }

    private Response createUser(UserData userData) {
        return UserService.createUser(userData);
    }
}

package tests.users.invalidscenarios;

import controllers.UserService;
import datageneration.UserDataGeneration;
import io.restassured.response.Response;
import models.users.User;
import com.github.javafaker.Faker;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static org.testng.Assert.assertEquals;

public class InvalidUpdateUser {

    private String userId,userToken,phone,email;

    @Test(description = "Verify Response Is 400 When Sending Update Request With Empty First Name")
    public void verifyResponseIs400WhenSendingUpdateRequestWithEmptyFirstName() {

        createUserTobeUpdated();

        Map<String,String> updatedData = new HashMap<>();
        updatedData.put("firstName","");

        Response response = UserService.updateUser(userId,userToken,updatedData);

        assertEquals(response.statusCode(), 400, "The status code should be 400 as First Name is empty");
    }

    @Test(description = "Verify Response Is 400 When Sending Update Request With First Name More Than 50 Character")
    public void verifyResponseIs400WhenSendingUpdateRequestWithFirstNameMoreThan50Character() {

        createUserTobeUpdated();

        Map<String,String> updatedData = new HashMap<>();
        updatedData.put("firstName",new Faker().bothify("???????????????????????????????????????????????????"));

        Response response = UserService.updateUser(userId,userToken,updatedData);

        assertEquals(response.statusCode(), 400, "The status code should be 400 as First Name is more than 50 character");
    }

    @Test(description = "Verify Response Is 400 When Sending Update Request With Empty Last Name")
    public void verifyResponseIs400WhenSendingUpdateRequestWithEmptyLastName() {

        createUserTobeUpdated();

        Map<String,String> updatedData = new HashMap<>();
        updatedData.put("lastName","");

        Response response = UserService.updateUser(userId,userToken,updatedData);

        assertEquals(response.statusCode(), 400, "The status code should be 400 as Last Name is empty");
    }

    @Test(description = "Verify Response Is 400 When Sending Update Request With Last Name More Than 50 Character")
    public void verifyResponseIs400WhenSendingUpdateRequestWithLastNameMoreThan50Character() {

        createUserTobeUpdated();

        Map<String,String> updatedData = new HashMap<>();
        updatedData.put("lastName",new Faker().bothify("???????????????????????????????????????????????????"));

        Response response = UserService.updateUser(userId,userToken,updatedData);

        assertEquals(response.statusCode(), 400, "The status code should be 400 as Last Name is more than 50 character");
    }

    @Test(description = "Verify Response Is 400 When Sending Update Request With Empty Email")
    public void verifyResponseIs400WhenSendingUpdateRequestWithEmptyEmail() {

        createUserTobeUpdated();

        Map<String,String> updatedData = new HashMap<>();
        updatedData.put("email","");

        Response response = UserService.updateUser(userId,userToken,updatedData);

        assertEquals(response.statusCode(), 400, "The status code should be 400 as Email is empty");
    }

    @Test(description = "Verify Response Is 400 When Sending Update Request With Incorrect Email Format")
    public void verifyResponseIs400WhenSendingUpdateRequestWithIncorrectEmailFormat() {

        createUserTobeUpdated();

        Map<String,String> updatedData = new HashMap<>();
        updatedData.put("email",new Faker().bothify("?????##test.com"));

        Response response = UserService.updateUser(userId,userToken,updatedData);

        assertEquals(response.statusCode(), 400, "The status code should be 400 as Email format is incorrect");
    }

    @Test(description = "Verify Response Is 400 When Sending Update Request With Empty Phone Number")
    public void verifyResponseIs400WhenSendingUpdateRequestWithEmptyPhoneNumber() {

        createUserTobeUpdated();

        Map<String,String> updatedData = new HashMap<>();
        updatedData.put("phone","");

        Response response = UserService.updateUser(userId,userToken,updatedData);

        assertEquals(response.statusCode(), 400, "The status code should be 400 as Phone is empty");
    }

    @Test(description = "Verify Response Is 409 When Sending Update Request With Email Already Exist")
    public void verifyResponseIs409WhenSendingUpdateRequestWithEmailAlreadyExist() {

        createUserTobeUpdated();

        Response response = UserService.createUser(UserDataGeneration.createFullUserData());

        String userToken = response.as(User.class).getJwtAccessToken();
        String  userId = response.as(User.class).getUserData().getUserId();

        Map<String,String> updatedData = new HashMap<>();
        updatedData.put("email",email);

        response = UserService.updateUser(userId,userToken,updatedData);

        assertEquals(response.statusCode(), 409, "The status code should be 409 as Email already exist");
    }

    @Test(description = "Verify Response Is 409 When Sending Update Request With Phone Already Exist")
    public void verifyResponseIs409WhenSendingUpdateRequestWithPhoneAlreadyExist() {

        createUserTobeUpdated();

        Response response = UserService.createUser(UserDataGeneration.createFullUserData());

        String userToken = response.as(User.class).getJwtAccessToken();
        String  userId = response.as(User.class).getUserData().getUserId();

        Map<String,String> updatedData = new HashMap<>();
        updatedData.put("phone",phone);
        updatedData.put("countryCode","+1");

        response = UserService.updateUser(userId,userToken,updatedData);

        assertEquals(response.statusCode(), 409, "The status code should be 409 as Phone already exist");
    }

    @Test(description = "Verify Response Is 400 When Sending Update Request With Phone Number More Than 15 Number")
    public void verifyResponseIs400WhenSendingUpdateRequestWithPhoneNumberMoreThan15Number() {

        createUserTobeUpdated();

        Map<String,String> updatedData = new HashMap<>();
        updatedData.put("phone",new Faker().numerify("################"));

        Response response = UserService.updateUser(userId,userToken,updatedData);

        assertEquals(response.statusCode(), 400, "The status code should be 400 as Phone Length is more than 15 number");
    }

    @Test(description = "Verify Response Is 400 When Sending Update Request With Phone Number Less Than 10 Number")
    public void verifyResponseIs400WhenSendingUpdateRequestWithPhoneNumberLessThan10Number() {

        createUserTobeUpdated();

        Map<String,String> updatedData = new HashMap<>();
        updatedData.put("phone",new Faker().numerify("#########"));
        updatedData.put("countryCode","+1");

        Response response = UserService.updateUser(userId,userToken,updatedData);

        assertEquals(response.statusCode(), 400, "The status code should be 400 as Phone Length is less than 10 number");
    }

    @Test(description = "Verify Response Is 400 When Sending Update Request With Empty Country Code")
    public void verifyResponseIs400WhenSendingUpdateRequestWithEmptyCountryCode() {

        createUserTobeUpdated();

        Map<String,String> updatedData = new HashMap<>();
        updatedData.put("countryCode","");

        Response response = UserService.updateUser(userId,userToken,updatedData);

        assertEquals(response.statusCode(), 400, "The status code should be 400 as the country code is empty");
    }

    @Test(description = "Verify Response Is 400 When Sending Update Request With Country Code More Than 4 Letters")
    public void verifyResponseIs400WhenSendingUpdateRequestWithCountryCodeMoreThan4Letters() {

        createUserTobeUpdated();

        Map<String,String> updatedData = new HashMap<>();
        updatedData.put("countryCode","+12345");

        Response response = UserService.updateUser(userId,userToken,updatedData);

        assertEquals(response.statusCode(), 400, "The status code should be 400 as the country code is more than 4 numbers");
    }

    @Test(description = "Verify Response Is 400 When Sending Update Request With Country Code Less Than 2 Letters")
    public void verifyResponseIs400WhenSendingUpdateRequestWithCountryCodeLessThan2Letters() {

        createUserTobeUpdated();

        Map<String,String> updatedData = new HashMap<>();
        updatedData.put("countryCode","+");

        Response response = UserService.updateUser(userId,userToken,updatedData);

        assertEquals(response.statusCode(), 400, "The status code should be 400 as the country code is less than 2 numbers");
    }

    @Test(description = "Verify Response Is 400 When Sending Update Request With Empty User ID")
    public void verifyResponseIs400WhenSendingUpdateRequestWithEmptyUserId() {

        createUserTobeUpdated();

        Response response = UserService.updateUser("",userToken,UserDataGeneration.createUserDataForUpdate());

        assertEquals(response.statusCode(), 400, "The status code should be 400 as the User Id is empty");
    }

    @Test(description = "Verify Response Is 404 When Sending Update Request With Invalid User ID Or Does Not Exist")
    public void verifyResponseIs404WhenSendingUpdateRequestWithInvalidUserId() {

        createUserTobeUpdated();

        Response response = UserService
                .updateUser(new Faker().bothify("??????????"),
                        userToken,UserDataGeneration.createUserDataForUpdate());

        assertEquals(response.statusCode(), 404, "The status code should be 400 as the User Id is Invalid ID or does not exist");
    }

    @Test(description = "Verify Response Is 403 When Sending Update Request With Empty Token")
    public void verifyResponseIs403WhenSendingUpdateRequestWithEmptyUserToken() {

        createUserTobeUpdated();

        Response response = UserService.updateUser(userId,"",UserDataGeneration.createUserDataForUpdate());

        assertEquals(response.statusCode(), 403, "The status code should be 403 Forbidden as the User Token is empty");
    }

    @Test(description = "Verify Response Is 403 When Sending Update Request With Invalid User Token")
    public void verifyResponseIs403WhenSendingUpdateRequestWithInvalidUserToken() {

        createUserTobeUpdated();

        Response response = UserService
                .updateUser(userId,
                        "INVALID_TOKEN",UserDataGeneration.createUserDataForUpdate());

        assertEquals(response.statusCode(), 403, "The status code should be 403 Forbidden as the User Token is Invalid");
    }

    private void createUserTobeUpdated() {
        Response response = UserService.createUser(UserDataGeneration.createFullUserData());

        assertEquals(response.statusCode(), 201, "The status code should be 201 user created successfully");
        userToken = response.as(User.class).getJwtAccessToken();
        userId = response.as(User.class).getUserData().getUserId();
        email = response.as(User.class).getUserData().getEmail();
        phone = response.as(User.class).getUserData().getPhone();
    }

}

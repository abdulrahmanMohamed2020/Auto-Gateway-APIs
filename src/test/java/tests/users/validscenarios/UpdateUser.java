package tests.users.validscenarios;

import controllers.UserService;
import datageneration.UserDataGeneration;
import io.restassured.response.Response;
import models.users.User;
import models.users.UserData;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.testng.Assert.assertEquals;

public class UpdateUser {

    private String userId,userToken,email;

    @Test(description = "Verify Schema Of Response When Updating User")
    public void verifySchemaOfResponseWhenUpdatingUser() {

        createUserTobeUpdated();

        Response response = UserService.updateUser(userId,userToken,UserDataGeneration.createUserDataForUpdate());

        assertEquals(response.statusCode(), 202, "The status code should be 202 as Accepted");
        response.then().assertThat().body(matchesJsonSchemaInClasspath("schema/updateUser.json"));

        //deleteUser();
    }

    @Test(description = "Verify Schema Of Response When Updating User With First Name Has Hyphen")
    public void verifySchemaOfResponseWhenUpdatingUserWithFirstNameHasHyphen() {

        createUserTobeUpdated();

        Map<String,String> updatedData = new HashMap<>();
        updatedData.put("firstName","Abdel-rahman");
        updatedData.put("email",email);

        Response response = UserService.updateUser(userId,userToken,updatedData);

        assertEquals(response.statusCode(), 202, "The status code should be 202 as Accepted");
        response.then().assertThat().body(matchesJsonSchemaInClasspath("schema/updateUser.json"));

        //deleteUser();
    }

    @Test(description = "Verify The Response Is Ok When Updating User With First Name Has Spaces")
    public void verifyResponseIsOkWhenUpdatingUserWithFirstNameHasSpaces() {

        createUserTobeUpdated();

        Map<String,String> updatedData = new HashMap<>();
        updatedData.put("firstName","Abdel rahman");
        updatedData.put("email",email);

        Response response = UserService.updateUser(userId,userToken,updatedData);

        assertEquals(response.statusCode(), 202, "The status code should be 202 as Accepted");

        //deleteUser();
    }

    @Test(description = "Verify Schema Of Response When Updating User With Last Name Has Hyphen")
    public void verifySchemaOfResponseWhenUpdatingUserWithLastNameHasHyphen() {

        createUserTobeUpdated();

        Map<String,String> updatedData = new HashMap<>();
        updatedData.put("lastName","Nour-eldin");
        updatedData.put("email",email);

        Response response = UserService.updateUser(userId,userToken,updatedData);

        assertEquals(response.statusCode(), 202, "The status code should be 202 as Accepted");
        response.then().assertThat().body(matchesJsonSchemaInClasspath("schema/updateUser.json"));

        //deleteUser();
    }

    @Test(description = "Verify The Response Is Ok When Updating User With Last Name Has Spaces")
    public void verifyResponseIsOkWhenUpdatingUserWithLastNameHasSpaces() {

        createUserTobeUpdated();

        Map<String,String> updatedData = new HashMap<>();
        updatedData.put("lastName","Nour eldin");
        updatedData.put("email",email);

        Response response = UserService.updateUser(userId,userToken,updatedData);

        assertEquals(response.statusCode(), 202, "The status code should be 202 as Accepted");

        //deleteUser();
    }

    private void createUserTobeUpdated() {
        UserData userData = UserDataGeneration.createFullUserData();
        email = userData.getEmail();
        Response response = UserService.createUser(userData);

        assertEquals(response.statusCode(), 201, "The status code should be 201 user created successfully");
        userToken = response.as(User.class).getJwtAccessToken();
        userId = response.as(User.class).getUserData().getUserId();
    }
}

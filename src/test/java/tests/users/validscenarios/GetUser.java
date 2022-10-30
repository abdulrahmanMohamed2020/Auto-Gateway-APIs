package tests.users.validscenarios;

import controllers.UserService;
import datageneration.UserDataGeneration;
import io.restassured.response.Response;
import models.users.User;
import models.users.UserData;
import org.testng.annotations.Test;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.testng.Assert.*;

public class GetUser {

    private String userId,userToken;

    @Test(description = "Verify Schema Of Response When Getting User")
    public void verifySchemaOfResponseWhenGettingUser() {
        createUser(UserDataGeneration.createFullUserData());

        Response response = UserService.getUser(userId,userToken);

        assertEquals(response.statusCode(), 200, "The status code should be 200");
        response.then().assertThat().body(matchesJsonSchemaInClasspath("schema/getUser.json"));

        //deleteUser();
    }

    @Test(description = "Verify That The User Data Is Returned Successfully And Not Empty When I Get User")
    public void verifyThatTheUserDataIsReturnedSuccessfullyAndNotEmptyWhenIGetUser() {
        createUser(UserDataGeneration.createFullUserData());

        Response response = UserService.getUser(userId,userToken);
        User actualUser = response.as(User.class);

        assertEquals(response.statusCode(), 200, "The status code should be 200");
        assertNotNull(actualUser, "The User Data data is empty");
        assertFalse(actualUser.getUserData().toString().isEmpty(),"The User Data is empty");
    }

    private void createUser(UserData userData) {
        Response response = UserService.createUser(userData);

        assertEquals(response.statusCode(), 201, "The status code should be 201 user created successfully");
        userToken = response.as(User.class).getJwtAccessToken();
        userId = response.as(User.class).getUserData().getUserId();
    }
}

package tests.users.invalidscenarios;

import controllers.UserService;
import datageneration.UserDataGeneration;
import io.restassured.response.Response;
import models.users.User;
import models.users.UserData;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class GetInvalidUser {

    @Test(description = "Verify Response Is 404 When Sending Get Request With Invalid User Id Or Not Exist")
    public void verifyResponseIs404WhenSendingGetRequestWithInvalidUserIdOrNotExist() {
        User user = createUser(UserDataGeneration.createFullUserData()).as(User.class);
        String userToken = user.getJwtAccessToken();

        Response response = UserService.getUser("INVALID_USERID",userToken);

        assertEquals(response.statusCode(), 404, "The status code should be 404");
    }

    @Test(description = "Verify Response Is 400 When Sending Get Request With Empty User Id")
    public void verifyResponseIs400WhenSendingGetRequestWithEmptyUserId() {
        User user = createUser(UserDataGeneration.createFullUserData()).as(User.class);
        String userToken = user.getJwtAccessToken();

        Response response = UserService.getUser("",userToken);

        assertEquals(response.statusCode(), 400, "The status code should be 400");
    }

    @Test(description = "Verify Response Is 401 When Sending Get Request With Invalid User Token")
    public void verifyResponseIs401WhenSendingGetRequestWithInvalidUserToken() {
        User user = createUser(UserDataGeneration.createFullUserData()).as(User.class);
        String userId = user.getUserData().getUserId();

        Response response = UserService.getUser(userId,"INVALID_AUTHORIZATION");

        assertEquals(response.statusCode(), 401, "The status code should be 401 Unauthorized");
    }

    @Test(description = "Verify Response Is 401 When Sending Get Request With Empty User Token")
    public void verifyResponseIs401WhenSendingGetRequestWithEmptyUserToken() {
        User user = createUser(UserDataGeneration.createFullUserData()).as(User.class);
        String userId = user.getUserData().getUserId();

        Response response = UserService.getUser(userId,"");

        assertEquals(response.statusCode(), 401, "The status code should be 401 Unauthorized");
    }

    private Response createUser(UserData userData) {
        return UserService.createUser(userData);
    }
}

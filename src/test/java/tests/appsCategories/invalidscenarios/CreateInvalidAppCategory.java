package tests.appsCategories.invalidscenarios;

import com.github.javafaker.Faker;
import controllers.AppsCategoriesService;
import controllers.UserService;
import datageneration.UserDataGeneration;
import io.restassured.response.Response;
import models.users.User;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static org.testng.Assert.assertEquals;

public class CreateInvalidAppCategory {

    private String userToken;

    @Test(description = "Verify Response Is 400 When Creating App Category With Empty Name")
    public void verifyResponseIs400WhenCreatingAppCategoryWithEmptyName() {
        createUser();

        Map<String,String> appCategoryData = new HashMap<>();
        appCategoryData.put("name", "");
        appCategoryData.put("description", new Faker().letterify("????? ????????"));

        Response response = AppsCategoriesService.createAppCategory(appCategoryData,userToken);

        assertEquals(response.statusCode(), 400, "The status code should be 400 bad request as the name is empty");
    }

    @Test(description = "Verify Response Is 400 When Creating App Category With Empty Description")
    public void verifyResponseIs400WhenCreatingAppCategoryWithEmptyDescription() {
        createUser();

        Map<String,String> appCategoryData = new HashMap<>();
        appCategoryData.put("name", new Faker().letterify("???????"));
        appCategoryData.put("description", "");

        Response response = AppsCategoriesService.createAppCategory(appCategoryData,userToken);

        assertEquals(response.statusCode(), 400, "The status code should be 400 bad request as the Description is empty");
    }

    @Test(description = "Verify Response Is 400 When Creating App Category Without Description")
    public void verifyResponseIs400WhenCreatingAppCategoryWithoutDescription() {
        createUser();

        Map<String,String> appCategoryData = new HashMap<>();
        appCategoryData.put("name", new Faker().letterify("???????"));

        Response response = AppsCategoriesService.createAppCategory(appCategoryData,userToken);

        assertEquals(response.statusCode(), 400, "The status code should be 400 bad request as the Description is missing");
    }

    @Test(description = "Verify Response Is 400 When Creating App Category Without Name")
    public void verifyResponseIs400WhenCreatingAppCategoryWithoutName() {
        createUser();

        Map<String,String> appCategoryData = new HashMap<>();
        appCategoryData.put("description", new Faker().letterify("????? ??????"));

        Response response = AppsCategoriesService.createAppCategory(appCategoryData,userToken);

        assertEquals(response.statusCode(), 400, "The status code should be 400 bad request as the Name is missing");
    }

    @Test(description = "Verify Response Is 403 When Creating App Category With Empty Token")
    public void verifyResponseIs403WhenCreatingAppCategoryWithEmptyToken() {

        Map<String,String> appCategoryData = new HashMap<>();
        appCategoryData.put("name", new Faker().letterify("???????"));
        appCategoryData.put("description", new Faker().letterify("??????? ?????"));

        Response response = AppsCategoriesService.createAppCategory(appCategoryData,"");

        assertEquals(response.statusCode(), 403, "The status code should be 403 Forbidden as the Token is empty");
    }

    @Test(description = "Verify Response Is 403 When Creating App Category With INVALID Token")
    public void verifyResponseIs403WhenCreatingAppCategoryWithInvalidToken() {

        Map<String,String> appCategoryData = new HashMap<>();
        appCategoryData.put("name", new Faker().letterify("???????"));
        appCategoryData.put("description", new Faker().letterify("??????? ?????"));

        Response response = AppsCategoriesService.createAppCategory(appCategoryData,"INVALID_TOKEN");

        assertEquals(response.statusCode(), 403, "The status code should be 403 Forbidden as the Token is Invalid");
    }

    private void createUser() {
        Response response = UserService.createUser(UserDataGeneration.createFullUserData());

        assertEquals(response.statusCode(), 201, "The status code should be 201 and the user created successfully");

        userToken = response.as(User.class).getJwtAccessToken();
    }
}

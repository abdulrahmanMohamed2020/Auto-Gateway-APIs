package tests.appsCategories.validscenarios;

import com.github.javafaker.Faker;
import controllers.AppsCategoriesService;
import controllers.UserService;
import datageneration.UserDataGeneration;
import io.restassured.response.Response;
import models.users.User;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.testng.Assert.assertEquals;

public class CreateAppCategory {

    private String userToken;

    @Test(description = "Verify Response Is 201 Created When Creating New App Category")
    public void verifyResponseIs201CreatedWhenCreatingNewAppCategory() {
        createUser();

        Map<String,String> appCategoryData = new HashMap<>();
        appCategoryData.put("name", new Faker().letterify("???????"));
        appCategoryData.put("description", new Faker().letterify("????? ????????"));

        Response response = AppsCategoriesService.createAppCategory(appCategoryData,userToken);

        assertEquals(response.statusCode(), 201, "The status code should be 201 and the App Category Created successfully");
    }

    @Test(description = "Verify Response Schema(All Required Data) is Returned Successfully When Creating App Category")
    public void verifyResponseSchemaWhenCreatingAppCategory() {
        createUser();

        Map<String,String> appCategoryData = new HashMap<>();
        appCategoryData.put("name", new Faker().letterify("???????"));
        appCategoryData.put("description", new Faker().letterify("????? ????????"));

        Response response = AppsCategoriesService.createAppCategory(appCategoryData,userToken);

        assertEquals(response.statusCode(), 201, "The status code should be 201 and the App Category Created successfully");
        response.then().assertThat().body(matchesJsonSchemaInClasspath("schema/createAppCategory.json"));
    }

    private void createUser() {
        Response response = UserService.createUser(UserDataGeneration.createFullUserData());

        assertEquals(response.statusCode(), 201, "The status code should be 201 and the user created successfully");

        userToken = response.as(User.class).getJwtAccessToken();
    }
}

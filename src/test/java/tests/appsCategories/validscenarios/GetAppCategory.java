package tests.appsCategories.validscenarios;

import com.github.javafaker.Faker;
import controllers.AppsCategoriesService;
import controllers.UserService;
import datageneration.UserDataGeneration;
import io.restassured.response.Response;
import models.appcategories.AppCategory;
import models.users.User;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.testng.Assert.assertEquals;

public class GetAppCategory {

    private String userToken, appCategoryId;

    @Test(description = "Verify Response Is 200 Ok When Getting App Category Details")
    public void verifyResponseIs200OkWhenGettingAppCategory() {
        createUser();
        createAppCategory();

        Response response = AppsCategoriesService.getAppCategoryDetails(appCategoryId,userToken);

        assertEquals(response.statusCode(), 200, "The status code should be 200 and the App Category fetched successfully");
    }

    @Test(description = "Verify Response Schema(All Required Data) is Returned Successfully When Getting App Category")
    public void verifyResponseSchemaWhenGettingAppCategory() {
        createUser();
        createAppCategory();

        Response response = AppsCategoriesService.getAppCategoryDetails(appCategoryId,userToken);

        assertEquals(response.statusCode(), 200, "The status code should be 200 and the App Category fetched successfully");
        response.then().assertThat().body(matchesJsonSchemaInClasspath("schema/getAppCategory.json"));
    }

    private void createUser() {
        Response response = UserService.createUser(UserDataGeneration.createFullUserData());

        assertEquals(response.statusCode(), 201, "The status code should be 201 and the user created successfully");

        userToken = response.as(User.class).getJwtAccessToken();
    }

    private void createAppCategory() {
        Map<String,String> appCategoryData = new HashMap<>();
        appCategoryData.put("name", new Faker().letterify("???????"));
        appCategoryData.put("description", new Faker().letterify("????? ????????"));

        Response response = AppsCategoriesService.createAppCategory(appCategoryData,userToken);

        assertEquals(response.statusCode(), 201, "The status code should be 201 and the App Category Created successfully");

        appCategoryId = response.as(AppCategory.class).getAppCategoryData().getCategoryId();
    }
}

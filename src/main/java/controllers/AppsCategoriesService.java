package controllers;

import constants.EndPoints;
import controllers.headers.HeaderProvider;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import java.util.HashMap;
import java.util.Map;

public class AppsCategoriesService extends Base {

    public static Response createAppCategory(Object appCategoryData, String userToken) {
        return restAssuredHelper
                        .postRequest(appCategoryData,
                                HeaderProvider.bearerToken(userToken), ContentType.JSON, EndPoints.CREATE_APP_CATEGORY);
    }

    public static Response getAppCategoryDetails(String appCategoryId, String userToken) {
        return restAssuredHelper
                        .getRequestWithPathParam(setPathParams(appCategoryId),
                                HeaderProvider.bearerToken(userToken), EndPoints.GET_APP_CATEGORY);
    }

    public static Response getAllAppsCategories(String userToken) {
        return restAssuredHelper
                .getRequestWithEndpoint(HeaderProvider.bearerToken(userToken),
                        EndPoints.GET_ALL_APP_CATEGORIES);
    }

    public static Response updateAppCategory(String appCategoryId, String userToken, Object appCategoryData) {
        return restAssuredHelper
                        .putRequest(setPathParams(appCategoryId),
                                HeaderProvider.bearerToken(userToken),
                                appCategoryData,
                                ContentType.JSON,
                                EndPoints.UPDATE_APP_CATEGORY);
    }

    public static Response deleteAppCategory(String appCategoryId, String userToken) {
        return restAssuredHelper
                        .deleteRequest(setPathParams(appCategoryId),
                                HeaderProvider.bearerToken(userToken), EndPoints.DELETE_APP_CATEGORY);
    }

    private static Map<String,String> setPathParams(String pathParam) {

        Map<String,String> userParamsMap = new HashMap<>();
        userParamsMap.put("categoryId", pathParam);

        return userParamsMap;
    }
}
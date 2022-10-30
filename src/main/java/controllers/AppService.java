package controllers;

import constants.EndPoints;
import controllers.headers.HeaderProvider;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import java.util.HashMap;
import java.util.Map;

public class AppService extends Base {

    public static Response createApp(Object appData, String userToken) {
        return restAssuredHelper
                        .postRequest(appData,
                                HeaderProvider.bearerToken(userToken), ContentType.JSON, EndPoints.CREATE_APP);
    }

    public static Response getAppDetails(String appId, String userToken) {
        return restAssuredHelper.getRequestWithPathParam(setPathParams(appId),
                HeaderProvider.bearerToken(userToken), EndPoints.GET_APP);
    }

    public static Response getAllAppsDetails(String userToken) {
        return restAssuredHelper
                        .getRequestWithEndpoint(HeaderProvider.bearerToken(userToken),
                                EndPoints.GET_ALL_APPS);
    }

    public static Response updateApp(String appId, String userToken, Object appData) {
        return restAssuredHelper
                        .putRequest(setPathParams(appId),
                                HeaderProvider.bearerToken(userToken),
                                appData,
                                ContentType.JSON,
                                EndPoints.UPDATE_APP);
    }

    public static Response deleteApp(String appId, String userToken) {
        return restAssuredHelper
                        .deleteRequest(setPathParams(appId),
                                HeaderProvider.bearerToken(userToken), EndPoints.DELETE_APP);
    }

    private static Map<String,String> setPathParams(String pathParam) {

        Map<String,String> userParamsMap = new HashMap<>();
        userParamsMap.put("appId", pathParam);

        return userParamsMap;
    }
}

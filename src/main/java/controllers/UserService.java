package controllers;

import constants.EndPoints;
import controllers.headers.HeaderProvider;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import java.util.HashMap;
import java.util.Map;

public class UserService extends Base {

    public static Response createUser(Object userData) {
        return restAssuredHelper
                .postRequest(userData,
                        ContentType.JSON,
                        EndPoints.CREATE_USER);
    }

    public static Response getUser(String userId, String userToken) {
        return restAssuredHelper
                .getRequestWithPathParam(setPathParams(userId),
                        HeaderProvider.bearerToken(userToken),
                        EndPoints.GET_USER);
    }

    public static Response updateUser(String userId, String userToken, Object userData) {
        return restAssuredHelper
                .putRequest(setPathParams(userId),
                        HeaderProvider.bearerToken(userToken),
                        userData,
                        ContentType.JSON,
                        EndPoints.UPDATE_USER);
    }

    public static Response deleteUser(String userId, String userToken) {
        return restAssuredHelper
                .deleteRequest(setPathParams(userId),
                        HeaderProvider.bearerToken(userToken),
                        EndPoints.DELETE_USER);
    }

    public static Response login(Object userData) {
        return restAssuredHelper
                .postRequest(userData,
                        ContentType.JSON,
                        EndPoints.LOGIN);
    }

    public static Response verifyLogin(Object userData) {
        return restAssuredHelper
                .postRequest(userData,
                        ContentType.JSON,
                        EndPoints.VERIFY_LOGIN);
    }

    private static Map<String,String> setPathParams(String pathParam) {

        Map<String,String> userParamsMap = new HashMap<>();
        userParamsMap.put("userId", pathParam);

        return userParamsMap;
    }
}
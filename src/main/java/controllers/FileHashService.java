package controllers;

import constants.EndPoints;
import controllers.headers.HeaderProvider;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class FileHashService extends Base{

    public static Response generateHash(Object fileData, String userToken) {
        return restAssuredHelper
                        .postRequest(fileData,
                                HeaderProvider.bearerToken(userToken),
                                ContentType.JSON,
                                EndPoints.GENERATE_HASH);
    }

    public static Response verifyHash(Object fileData, String userToken) {
        return restAssuredHelper
                        .postRequest(fileData,
                                HeaderProvider.bearerToken(userToken),
                                ContentType.JSON,
                                EndPoints.VERIFY_HASH);
    }
}

package controllers;

import constants.EndPoints;
import controllers.headers.HeaderProvider;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import java.util.HashMap;
import java.util.Map;

public class WalletService extends Base{

    public static Response createWallet(Object walletData, String userToken) {
        return restAssuredHelper
                .postRequest(walletData,
                        HeaderProvider.bearerToken(userToken),
                        ContentType.JSON,
                        EndPoints.CREATE_WALLET);
    }

    public static Response fetchUserWallets(String userToken) {
        return restAssuredHelper
                .getRequestWithEndpoint(HeaderProvider.bearerToken(userToken),
                        EndPoints.FETCH_WALLETS_BY_USERID);
    }

    public static Response verifyWallet(String walletId, String userId, String userToken) {

        Map<String,String> walletParamsMap = new HashMap<>();
        walletParamsMap.put("walletId",walletId);
        walletParamsMap.put("userId",userId);

        return restAssuredHelper
                .getRequestWithPathParam(walletParamsMap,
                        HeaderProvider.bearerToken(userToken),
                        EndPoints.VERIFY_WALLET);
    }

    public static Response getWallet(String walletId, String userToken) {
        return restAssuredHelper
                .getRequestWithPathParam(setPathParams(walletId),
                        HeaderProvider.bearerToken(userToken),
                        EndPoints.GET_WALLET);
    }

    public static Response updateWallet(Object walletData, String walletId, String userToken) {
        return restAssuredHelper
                .patchRequest(setPathParams(walletId),
                        HeaderProvider.bearerToken(userToken),
                        walletData,
                        ContentType.JSON,
                        EndPoints.GET_WALLET);
    }

    public static Response deleteWallet(String walletId, String userToken) {
        return restAssuredHelper
                .deleteRequest(setPathParams(walletId),
                        HeaderProvider.bearerToken(userToken),
                        EndPoints.DELETE_WALLET);
    }

    private static Map<String,String> setPathParams(String pathParam) {

        Map<String,String> userParamsMap = new HashMap<>();
        userParamsMap.put("walletId", pathParam);

        return userParamsMap;
    }
}
package controllers;

import constants.EndPoints;
import controllers.headers.HeaderProvider;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import java.util.HashMap;
import java.util.Map;

public class FileService extends Base{

    public static Response shareFile(Object fileData, String userToken) {
        return restAssuredHelper
                        .postRequest(fileData, HeaderProvider.bearerToken(userToken),
                                ContentType.JSON,
                                EndPoints.SHARE_FILE);
    }

    public static Response acceptShare(Object fileData, String userToken) {
        return restAssuredHelper
                        .postRequest(fileData, HeaderProvider.bearerToken(userToken),
                                ContentType.JSON,
                                EndPoints.ACCEPT_SHARE);
    }

    public static Response encryptFile(Object fileData, String userToken) {
        return restAssuredHelper
                        .postRequest(fileData, HeaderProvider.bearerToken(userToken),
                                ContentType.JSON,
                                EndPoints.ENCRYPT_FILE);
    }

    public static Response grantFilePermission(Object fileData, String userToken) {
        return restAssuredHelper
                        .postRequest(fileData, HeaderProvider.bearerToken(userToken),
                                ContentType.JSON,
                                EndPoints.GRANT_ACCESS_PERMISSION);
    }

    public static Response revokeFilePermission(Object fileData, String userToken) {
        return restAssuredHelper
                        .postRequest(fileData,
                                HeaderProvider.bearerToken(userToken),
                                ContentType.JSON,
                                EndPoints.REVOKE_ACCESS_PERMISSION);
    }

    public static Response listUsersAccessFile(String fileHash, String userToken) {

        Map<String,String> fileParamsMap = new HashMap<>();
        fileParamsMap.put("fileHash", fileHash);

        return restAssuredHelper
                        .getRequestWithQueryParam(HeaderProvider.bearerToken(userToken),
                                EndPoints.LIST_FILE_ACCESS,
                                fileParamsMap);
    }

    public static Response listSharedFilesWithUser(String userToken) {
        return restAssuredHelper
                        .getRequestWithEndpoint(HeaderProvider.bearerToken(userToken),
                                EndPoints.LIST_SHARED_FILES);
    }

    public static Response listFilesUserHasShared(String userToken) {
        return restAssuredHelper
                        .getRequestWithEndpoint(HeaderProvider.bearerToken(userToken),
                                EndPoints.LIST_FILES_SHARED);
    }

    public static Response getFile(String fileId, String walletId, String userToken) {
        return restAssuredHelper
                        .getRequestWithPathParam(setPathParams(fileId,walletId),
                                HeaderProvider.bearerToken(userToken),
                                EndPoints.GET_A_FILE);
    }

    public static Response updateFile(String fileId, String walletId, String userToken, Object file) {
        return restAssuredHelper
                        .putRequest(setPathParams(fileId,walletId),
                                HeaderProvider.bearerToken(userToken),
                                file, ContentType.JSON,
                                EndPoints.UPDATE_A_FILE);
    }

    public static Response createFile(Object fileData, String walletId, String userToken) {
        Map<String,String> pathParamsMap = new HashMap<>();
        pathParamsMap.put("walletId", walletId);

        return restAssuredHelper
                        .postRequest(fileData, HeaderProvider.bearerToken(userToken),
                                pathParamsMap,
                                ContentType.JSON,
                                EndPoints.CREATE_A_FILE);
    }

    public static Response createFolder(Object folderDada, String walletId, String userToken) {
        Map<String,String> pathParamsMap = new HashMap<>();
        pathParamsMap.put("walletId", walletId);

        return restAssuredHelper
                .postRequest(folderDada, HeaderProvider.bearerToken(userToken),
                        pathParamsMap,
                        ContentType.JSON,
                        EndPoints.CREATE_FOLDER);
    }

    public static Response listAllFilesUserHasCreated(String userToken) {
        return restAssuredHelper
                        .getRequestWithEndpoint(HeaderProvider.bearerToken(userToken),
                                EndPoints.LIST_USER_FILES);
    }

    private static Map<String,String> setPathParams(String fileId, String walletId) {

        Map<String,String> userParamsMap = new HashMap<>();
        userParamsMap.put("fileId", fileId);
        userParamsMap.put("walletId", walletId);

        return userParamsMap;
    }

}

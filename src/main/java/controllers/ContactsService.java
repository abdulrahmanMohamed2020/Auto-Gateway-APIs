package controllers;

import constants.EndPoints;
import controllers.headers.HeaderProvider;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import java.util.HashMap;
import java.util.Map;

public class ContactsService extends Base{

    public static Response createContact(Object contactData, String userId, String userToken) {
        Map<String,String> userParamsMap = new HashMap<>();
        userParamsMap.put("userId", userId);

        return restAssuredHelper
                .postRequest(contactData,
                        HeaderProvider.bearerToken(userToken),
                        userParamsMap,
                        ContentType.JSON, EndPoints.CREATE_CONTACT);
    }

    public static Response getContact(String contactId, String userToken) {
        return restAssuredHelper
                .getRequestWithPathParam(setPathParams(contactId),
                        HeaderProvider.bearerToken(userToken),
                        EndPoints.GET_CONTACT);
    }

    public static Response updateContact(Object contactData, String contactId, String userToken) {
        return restAssuredHelper
                .putRequest(setPathParams(contactId),
                        HeaderProvider.bearerToken(userToken),
                        contactData,ContentType.JSON,
                        EndPoints.UPDATE_CONTACT);
    }

    public static Response deleteContact(String contactId, String userToken) {
        return restAssuredHelper
                .deleteRequest(setPathParams(contactId),
                        HeaderProvider.bearerToken(userToken),
                        EndPoints.DELETE_CONTACT);
    }

    public static Response importContacts(Object contactData, String userToken, String userId) {

        Map<String,String> userParamsMap = new HashMap<>();
        userParamsMap.put("userId", userId);

        return restAssuredHelper
                .postRequest(contactData,
                        HeaderProvider.bearerToken(userToken),
                        userParamsMap,
                        ContentType.JSON, EndPoints.IMPORT_CONTACTS);
    }

    public static Response listContacts(String userToken) {
        return restAssuredHelper
                .getRequestWithEndpoint(HeaderProvider.bearerToken(userToken),
                        EndPoints.GET_LIST_OF_CONTACTS);
    }

    public static Response blockContact(String contactId, String userToken) {
        return restAssuredHelper
                .patchRequest(setPathParams(contactId),
                        HeaderProvider.bearerToken(userToken),
                        EndPoints.BLOCK_CONTACT);
    }

    public static Response inviteContact(String contactId, String userToken) {
        return restAssuredHelper
                .postRequest(setPathParams(contactId),
                        HeaderProvider.bearerToken(userToken),
                        EndPoints.INVITE_CONTACT);
    }

    public static Response addFavoriteContact(String contactId, String userToken) {
        return restAssuredHelper
                .postRequest(setPathParams(contactId),
                        HeaderProvider.bearerToken(userToken),
                        EndPoints.ADD_CONTACT_TO_FAVORITE_LIST);
    }

    public static Response getContactPhoto(String contactId, String userToken) {
        return restAssuredHelper
                .getRequestWithPathParam(setPathParams(contactId),
                        HeaderProvider.bearerToken(userToken),
                        EndPoints.GET_CONTACT_PHOTO);
    }

    private static Map<String,String> setPathParams(String pathParam) {

        Map<String,String> userParamsMap = new HashMap<>();
        userParamsMap.put("contactId", pathParam);

        return userParamsMap;
    }
}

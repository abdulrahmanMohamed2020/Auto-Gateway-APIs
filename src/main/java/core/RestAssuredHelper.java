package core;

import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import io.restassured.filter.log.LogDetail;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.parsing.Parser;
import io.restassured.response.Response;

import java.util.Map;

public class RestAssuredHelper {

    public RestAssuredHelper(String baseUrl) {
        RestAssured.baseURI = baseUrl;
        RestAssured.registerParser("text/plain", Parser.JSON);
        RestAssured.filters(new AllureRestAssured(),
                new RequestLoggingFilter(),
                new ResponseLoggingFilter(LogDetail.BODY));
    }

    public Response postRequest(Object object,
                                Map<String,String> headers,
                                ContentType contentType,
                                String endpoint) {

        return RestAssured
                .given().body(object)
                .headers(headers)
                .contentType(contentType)
                .post(endpoint);
    }

    public Response postRequest(Object object,
                                Map<String,String> headers,
                                Map<String,String> pathParams,
                                ContentType contentType,
                                String endpoint) {

        return RestAssured
                .given().body(object)
                .headers(headers)
                .pathParams(pathParams)
                .contentType(contentType)
                .post(endpoint);
    }

    public Response postRequest(Object object,
                                ContentType contentType,
                                String endpoint) {

        return RestAssured
                .given().body(object)
                .contentType(contentType)
                .post(endpoint);
    }

    public Response postRequest(Map<String,String> pathParams,
                                Map<String,String> headers,
                                String endpoint) {

        return RestAssured
                .given()
                .headers(headers)
                .pathParams(pathParams)
                .post(endpoint);
    }

    public Response getRequestWithPathParam(Map<String,String> pathParams,
                                            Map<String,String> headers,
                                            String endpoint) {

        return RestAssured
                .given()
                .headers(headers)
                .pathParams(pathParams)
                .get(endpoint);
    }

    public Response getRequestWithQueryParam(Map<String,String> headers,
                                             String endpoint,
                                             Map<String ,String> queryParams) {

        return RestAssured
                .given()
                .queryParams(queryParams)
                .headers(headers)
                .get(endpoint);
    }

    public Response getRequestWithEndpoint(Map<String,String> headers, String endpoint) {
        return RestAssured
                .given()
                .headers(headers)
                .get(endpoint);
    }

    public Response putRequest(Map<String,String> pathParams,
                               Map<String,String> headers,
                               Object object,
                               ContentType contentType,
                               String endpoint) {

        return RestAssured
                .given()
                .headers(headers)
                .body(object)
                .contentType(contentType)
                .pathParams(pathParams)
                .put(endpoint);
    }

    public Response patchRequest(Map<String,String> pathParams,
                               Map<String,String> headers,
                               Object object,
                               ContentType contentType,
                               String endpoint) {

        return RestAssured
                .given()
                .headers(headers)
                .body(object)
                .contentType(contentType)
                .pathParams(pathParams)
                .patch(endpoint);
    }

    public Response patchRequest(Map<String,String> pathParams,
                                 Map<String,String> headers,
                                 String endpoint) {

        return RestAssured
                .given()
                .headers(headers)
                .pathParams(pathParams)
                .patch(endpoint);
    }

    public Response deleteRequest(Map<String,String> pathParams,
                                  Map<String,String> headers, String endpoint) {

        return RestAssured
                .given()
                .headers(headers)
                .pathParams(pathParams)
                .delete(endpoint);
    }
}
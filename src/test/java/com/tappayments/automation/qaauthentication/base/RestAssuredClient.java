package com.tappayments.automation.qaauthentication.base;

import com.tappayments.automation.qaauthentication.config.ConfigManager;
import com.tappayments.automation.qaauthentication.utils.AppConstants;
import com.tappayments.automation.qaauthentication.utils.AppUtils;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpStatus;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class RestAssuredClient extends BaseTest {

    private static String sessionToken;

    static {
        try {
            sessionToken = generateSessionToken(ConfigManager.getPropertyValue("authorizationToken"));
        } catch (Exception e) {
            throw new RuntimeException("Failed to initialize session token", e);
        }
    }

    public static RequestSpecification requestSpecification(Map<String, String> headers){

        return given().headers(headers);
    }


    public static Response postRequest(String body, String endPoint){

        Map<String, String> headers = Map.of(AppConstants.CONTENT_TYPE, ConfigManager.getPropertyValue(AppConstants.CONTENT_TYPE_VALUE),
                AppConstants.AUTHORIZATION, AppConstants.BEARER + ConfigManager.getPropertyValue(AppConstants.AUTHORIZATION_VALUE),
                AppConstants.MERCHANT_ID, ConfigManager.getPropertyValue(AppConstants.MERCHANT_ID_VALUE),
                AppConstants.LIVE_MODE, ConfigManager.getPropertyValue(AppConstants.LIVE_MODE_VALUE),
                AppConstants.SESSION_TOKEN, sessionToken);

        RequestSpecification requestSpecification = requestSpecification(headers);
        Response response = requestSpecification.body(body)
                .when()
                .post(endPoint);

        return response;
    }

    private static String generateSessionToken(String key){

        Map<String, String> requestBody = new HashMap<>();
        requestBody.put("key", key);

        String body = AppUtils.transactionRequestToJson(requestBody);

        RequestSpecification requestSpecification = requestSpecification(Map.of(AppConstants.CONTENT_TYPE, ConfigManager.getPropertyValue(AppConstants.CONTENT_TYPE_VALUE)));

        Response response = requestSpecification.body(body)
                .when()
                .post(ConfigManager.getPropertyValue(AppConstants.SESSION_TOKEN_URI));

        if(response.getStatusCode() == HttpStatus.SC_OK)
            return response.jsonPath().getString(AppConstants.DATA + "." + AppConstants.SESSION_TOKEN);

        return "";
    }
}

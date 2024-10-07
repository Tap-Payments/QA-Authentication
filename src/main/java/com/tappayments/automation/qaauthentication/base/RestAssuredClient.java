package com.tappayments.automation.qaauthentication.base;

import com.tappayments.automation.qaauthentication.App;
import com.tappayments.automation.qaauthentication.config.ConfigManager;
import com.tappayments.automation.config.ExtentReportManager;
import com.tappayments.automation.qaauthentication.model.CardRequest;
import com.tappayments.automation.qaauthentication.utils.AppConstants;
import com.tappayments.automation.qaauthentication.utils.AppUtils;
import com.tappayments.automation.utils.CommonAutomationUtils;
import io.restassured.response.Response;
import io.restassured.specification.QueryableRequestSpecification;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.SpecificationQuerier;
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


        printRequestLogInReport(requestSpecification);
        printResponseLogInReport(response);

        return response;
    }

    public static Response postMethodResponseHeaders(Map<String, String> headers,
                                                            String payloadJson,
                                                            String endpoint){

        RequestSpecification requestSpecification = requestSpecification(headers);

        Response response = requestSpecification
                .body(payloadJson)
                .when()
                .post(endpoint);

        return response;
    }

    public static String generateSessionToken(String key){

        Map<String, String> requestBody = new HashMap<>();
        requestBody.put("key", key);

        String body = CommonAutomationUtils.stringToJson(requestBody);

        RequestSpecification requestSpecification = requestSpecification(Map.of(AppConstants.CONTENT_TYPE, ConfigManager.getPropertyValue(AppConstants.CONTENT_TYPE_VALUE)));

        Response response = requestSpecification.body(body)
                .when()
                .post(ConfigManager.getPropertyValue(AppConstants.SESSION_TOKEN_URI));

        if(response.getStatusCode() == HttpStatus.SC_OK)
            return response.jsonPath().getString(AppConstants.DATA + "." + AppConstants.SESSION_TOKEN);

        return "";
    }

    private static void printRequestLogInReport(RequestSpecification requestSpecification){

        QueryableRequestSpecification queryableRequestSpecification = SpecificationQuerier.query(requestSpecification);
        ExtentReportManager.logInfoDetails("Endpoint is : " + queryableRequestSpecification.getURI());
        ExtentReportManager.logInfoDetails("Method is : " + queryableRequestSpecification.getMethod());
        ExtentReportManager.logInfoDetails("Headers are");
        ExtentReportManager.logHeaders(queryableRequestSpecification.getHeaders().asList());
        ExtentReportManager.logInfoDetails("Request body is: ");
        ExtentReportManager.logJson(queryableRequestSpecification.getBody());
    }

    private static void printResponseLogInReport(Response response){

        ExtentReportManager.logInfoDetails("Response status : " + response.getStatusCode());
        ExtentReportManager.logInfoDetails("Response body is : ");
        ExtentReportManager.logJson(response.getBody().prettyPrint());
    }

    public static String generateCardDetailToken() {

        CardRequest cardRequest = AppUtils.createCardRequest();
        return generateCardDetailToken(cardRequest);
    }

    public static String generateCardDetailToken(CardRequest cardRequest){

        String body = CommonAutomationUtils.stringToJson(cardRequest);

        RequestSpecification requestSpecification = requestSpecification(
                Map.of(
                        AppConstants.CONTENT_TYPE, ConfigManager.getPropertyValue(AppConstants.CONTENT_TYPE_VALUE),
                        AppConstants.AUTHORIZATION, AppConstants.BEARER + ConfigManager.getPropertyValue(AppConstants.AUTHORIZATION_VALUE)
                )
        );

        Response response = requestSpecification.body(body)
                .when()
                .post(ConfigManager.getPropertyValue(AppConstants.TAP_BASE_URI) + AppConstants.CARD_DETAIL_TOKEN_URI);

        if(response.getStatusCode() == HttpStatus.SC_OK)
            return response.jsonPath().getString(AppConstants.ID);

        System.out.println("Adding delay of 5 seconds before retrying");
        try {
            Thread.sleep(5000);  // Adding a delay before retrying (optional)
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();  // Handle interruption during sleep
        }

        System.out.println("Retrying the request");
        return generateCardDetailToken();  // Retry the request
    }

}
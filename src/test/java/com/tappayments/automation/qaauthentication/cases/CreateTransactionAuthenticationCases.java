package com.tappayments.automation.qaauthentication.cases;

import com.fasterxml.jackson.databind.JsonNode;
import com.tappayments.automation.qaauthentication.model.TransactionRequest;
import com.tappayments.automation.qaauthentication.model.enums.AuthenticationChannel;
import com.tappayments.automation.qaauthentication.model.enums.AuthenticationPurpose;
import com.tappayments.automation.qaauthentication.model.enums.TransactionType;
import com.tappayments.automation.qaauthentication.base.BaseTest;
import com.tappayments.automation.qaauthentication.requests.AuthenticationTransactionRequest;
import com.tappayments.automation.qaauthentication.utils.AppConstants;
import com.tappayments.automation.qaauthentication.utils.AppUtils;
import com.tappayments.automation.utils.CommonAutomationUtils;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Map;
import java.util.UUID;

public class CreateTransactionAuthenticationCases extends BaseTest {

    private boolean isExternalAuth;

    @Parameters({"isExternalAuth"})
    @BeforeMethod(onlyForGroups = "external_auth")
    public void setup(@Optional(value = "false") String isExternalAuthParam) {

        this.isExternalAuth = Boolean.parseBoolean(isExternalAuthParam);
    }

    @Test(groups = {"MC:Authentication", "internal_auth", "external_auth", "Abdul Rehman", "SECTION:Amount Validation", "SC:Amount"}, description = "Validates transaction authentication for USD currency with a valid amount", priority = 1)
    public void transactionAuthenticationAmountUSDCurrencyCase() {

        String endpoint = AppConstants.INTERNAL_END_POINT;
        TransactionRequest transactionRequest = AppUtils.transactionRequestInstance();

        transactionRequest.setAmount(10.11);
        transactionRequest.setCurrency("USD");

        if (isExternalAuth){

            endpoint = "";
            AppUtils.updateSourceAndMerchantIdForExternal(transactionRequest);
        }

        String body = CommonAutomationUtils.stringToJson(transactionRequest);
        if (isExternalAuth)
            body = AppUtils.removeCardAndRoutingFromRequest(body);
        Response response = AuthenticationTransactionRequest.postRequest(body, endpoint);
        CommonAutomationUtils.verifyCommonResponseSuccessValidation(response, HttpStatus.SC_OK, Map.of(AppConstants.AMOUNT, 10.11, AppConstants.CURRENCY, "USD"));
    }

//    @Test(groups = {"MC:Authentication", "internal_auth", "external_auth", "Abdul Rehman", "SECTION:Amount Validation", "SC:Amount"}, description = "Validates transaction authentication fails for USD currency with more than two decimal places", priority = 2)
//    public void transactionAuthenticationAmountMoreDecimalUSDCurrencyCase() {
//
//        String endpoint = AppConstants.INTERNAL_END_POINT;
//        TransactionRequest transactionRequest = AppUtils.transactionRequestInstance();
//        transactionRequest.setAmount(10.111);
//        transactionRequest.setCurrency("USD");
//        if (isExternalAuth){
//
//            endpoint = "";
//            AppUtils.updateSourceAndMerchantIdForExternal(transactionRequest);
//        }
//        String body = CommonAutomationUtils.stringToJson(transactionRequest);
//        if (isExternalAuth)
//            body = AppUtils.removeCardAndRoutingFromRequest(body);
//        Response response = AuthenticationTransactionRequest.postRequest(body, endpoint);
//        String textResponse = response.getBody().asString();
//        JsonNode jsonResponse = CommonAutomationUtils.convertToJson(textResponse);
//
//        CommonAutomationUtils.verifyCommonResponseFailedValidation(jsonResponse, response.getStatusCode(), HttpStatus.SC_BAD_REQUEST, AppConstants.INVALID_DATA_CODE, AppConstants.INVALID_DATA_ERROR);
//    }
//
//    @Test(groups = {"MC:Authentication", "internal_auth", "external_auth", "Abdul Rehman", "SECTION:Amount Validation", "SC:Amount"}, description = "Validates transaction authentication fails for SAR currency with more than two decimal places", priority = 3)
//    public void transactionAuthenticationAmountMoreDecimalSARCurrencyCase() {
//
//        String endpoint = AppConstants.INTERNAL_END_POINT;
//        TransactionRequest transactionRequest = AppUtils.transactionRequestInstance();
//
//        transactionRequest.setAmount(10.111);
//        transactionRequest.setCurrency("SAR");
//
//        if (isExternalAuth){
//            endpoint = "";
//            AppUtils.updateSourceAndMerchantIdForExternal(transactionRequest);
//        }
//
//        String body = CommonAutomationUtils.stringToJson(transactionRequest);
//        if (isExternalAuth)
//            body = AppUtils.removeCardAndRoutingFromRequest(body);
//
//        Response response = AuthenticationTransactionRequest.postRequest(body, endpoint);
//        String textResponse = response.getBody().asString();
//        JsonNode jsonResponse = CommonAutomationUtils.convertToJson(textResponse);
//
//        CommonAutomationUtils.verifyCommonResponseFailedValidation(jsonResponse, response.getStatusCode(), HttpStatus.SC_BAD_REQUEST, AppConstants.INVALID_DATA_CODE, AppConstants.INVALID_DATA_ERROR);
//    }
//
//    @Test(groups = {"MC:Authentication", "internal_auth", "external_auth", "Abdul Rehman", "SECTION:Amount Validation", "SC:Amount"}, description = "Validates transaction authentication for KWD currency with a valid amount", priority = 4)
//    public void transactionAuthenticationAmountKWDCurrencyCase() {
//
//        String endpoint = AppConstants.INTERNAL_END_POINT;
//        TransactionRequest transactionRequest = AppUtils.transactionRequestInstance();
//
//        transactionRequest.setAmount(10.13);
//        transactionRequest.setCurrency("KWD");
//
//        if (isExternalAuth){
//            endpoint = "";
//            AppUtils.updateSourceAndMerchantIdForExternal(transactionRequest);
//        }
//
//        String body = CommonAutomationUtils.stringToJson(transactionRequest);
//        if (isExternalAuth)
//            body = AppUtils.removeCardAndRoutingFromRequest(body);
//
//        Response response = AuthenticationTransactionRequest.postRequest(body, endpoint);
//        CommonAutomationUtils.verifyCommonResponseSuccessValidation(response, HttpStatus.SC_OK, Map.of(AppConstants.AMOUNT, 10.13, AppConstants.CURRENCY, "KWD"));
//    }
//
//    @Test(groups = {"MC:Authentication", "internal_auth", "external_auth", "Abdul Rehman", "SECTION:Amount Validation", "SC:Amount"}, description = "Validates transaction authentication for KWD currency with more than two decimal places", priority = 5)
//    public void transactionAuthenticationAmountMoreDecimalKWDCurrencyCase() {
//
//        String endpoint = AppConstants.INTERNAL_END_POINT;
//        TransactionRequest transactionRequest = AppUtils.transactionRequestInstance();
//
//        transactionRequest.setAmount(10.133);
//        transactionRequest.setCurrency("KWD");
//
//        if (isExternalAuth){
//            endpoint = "";
//            AppUtils.updateSourceAndMerchantIdForExternal(transactionRequest);
//        }
//
//        String body = CommonAutomationUtils.stringToJson(transactionRequest);
//        if (isExternalAuth)
//            body = AppUtils.removeCardAndRoutingFromRequest(body);
//
//        Response response = AuthenticationTransactionRequest.postRequest(body, endpoint);
//        CommonAutomationUtils.verifyCommonResponseSuccessValidation(response, HttpStatus.SC_OK, Map.of(AppConstants.AMOUNT, 10.133, AppConstants.CURRENCY, "KWD"));
//    }
//
//    @Test(groups = {"MC:Authentication", "internal_auth", "external_auth", "Abdul Rehman", "SECTION:Amount Validation", "SC:Amount"}, description = "Validates transaction authentication fails for KWD currency with more than four decimal places", priority = 6)
//    public void transactionAuthenticationAmountMoreFourDecimalSARCurrencyCase() {
//
//        String endpoint = AppConstants.INTERNAL_END_POINT;
//        TransactionRequest transactionRequest = AppUtils.transactionRequestInstance();
//
//        transactionRequest.setAmount(10.1234);
//        transactionRequest.setCurrency("KWD");
//
//        if (isExternalAuth){
//            endpoint = "";
//            AppUtils.updateSourceAndMerchantIdForExternal(transactionRequest);
//        }
//
//        String body = CommonAutomationUtils.stringToJson(transactionRequest);
//        if (isExternalAuth)
//            body = AppUtils.removeCardAndRoutingFromRequest(body);
//
//        Response response = AuthenticationTransactionRequest.postRequest(body, endpoint);
//        String textResponse = response.getBody().asString();
//        JsonNode jsonResponse = CommonAutomationUtils.convertToJson(textResponse);
//
//        CommonAutomationUtils.verifyCommonResponseFailedValidation(jsonResponse, response.getStatusCode(), HttpStatus.SC_BAD_REQUEST, AppConstants.INVALID_DATA_CODE, AppConstants.INVALID_DATA_ERROR);
//    }
//
//    @Test(groups = {"MC:Authentication", "internal_auth", "external_auth", "Abdul Rehman", "SECTION:Amount Validation", "SC:Amount"}, description = "Validates transaction authentication for a negative amount", priority = 7)
//    public void transactionAuthenticationNegativeAmountCase() {
//
//        String endpoint = AppConstants.INTERNAL_END_POINT;
//        TransactionRequest transactionRequest = AppUtils.transactionRequestInstance();
//        transactionRequest.setAmount(-10.10);
//
//        if (isExternalAuth){
//            endpoint = "";
//            AppUtils.updateSourceAndMerchantIdForExternal(transactionRequest);
//        }
//
//        String body = CommonAutomationUtils.stringToJson(transactionRequest);
//        if (isExternalAuth)
//            body = AppUtils.removeCardAndRoutingFromRequest(body);
//
//        Response response = AuthenticationTransactionRequest.postRequest(body, endpoint);
//        String textResponse = response.getBody().asString();
//        JsonNode jsonResponse = CommonAutomationUtils.convertToJson(textResponse);
//
//        CommonAutomationUtils.verifyCommonResponseFailedValidation(jsonResponse, response.getStatusCode(), HttpStatus.SC_BAD_REQUEST, AppConstants.INVALID_DATA_CODE, AppConstants.INVALID_DATA_ERROR);
//    }
//
//    @Test(groups = {"MC:Authentication", "internal_auth", "external_auth", "Abdul Rehman", "SECTION:Amount Validation", "SC:Amount"}, description = "Validates transaction authentication for a valid amount", priority = 8)
//    public void transactionAuthenticationValidAmountCase() {
//
//        String endpoint = AppConstants.INTERNAL_END_POINT;
//        TransactionRequest transactionRequest = AppUtils.transactionRequestInstance();
//        transactionRequest.setAmount(10.11);
//
//        if (isExternalAuth){
//            endpoint = "";
//            AppUtils.updateSourceAndMerchantIdForExternal(transactionRequest);
//        }
//
//        String body = CommonAutomationUtils.stringToJson(transactionRequest);
//        if (isExternalAuth)
//            body = AppUtils.removeCardAndRoutingFromRequest(body);
//
//        Response response = AuthenticationTransactionRequest.postRequest(body, endpoint);
//        CommonAutomationUtils.verifyCommonResponseSuccessValidation(response, HttpStatus.SC_OK, Map.of(AppConstants.AMOUNT, 10.11));
//    }
//
//    @Test(groups = {"MC:Authentication", "internal_auth", "external_auth", "Abdul Rehman", "SECTION:Amount Validation", "SC:Amount"}, description = "Validates transaction authentication with a null amount", priority = 9)
//    public void transactionAuthenticationNullAmountCase() {
//
//        String endpoint = AppConstants.INTERNAL_END_POINT;
//        TransactionRequest transactionRequest = AppUtils.transactionRequestInstance();
//        transactionRequest.setAmount(null);
//
//        if (isExternalAuth){
//            endpoint = "";
//            AppUtils.updateSourceAndMerchantIdForExternal(transactionRequest);
//        }
//
//        String body = CommonAutomationUtils.stringToJson(transactionRequest);
//        if (isExternalAuth)
//            body = AppUtils.removeCardAndRoutingFromRequest(body);
//
//        Response response = AuthenticationTransactionRequest.postRequest(body, endpoint);
//        CommonAutomationUtils.verifyCommonResponseSuccessValidation(response, HttpStatus.SC_OK);
//        CommonAutomationUtils.verifyNonAvailableKey(response, List.of(AppConstants.AMOUNT));
//    }
//
//    @Test(groups = {"MC:Authentication", "internal_auth", "external_auth", "Abdul Rehman", "SECTION:Amount Validation", "SC:Amount"}, description = "Validates transaction authentication for the maximum allowable amount", priority = 10)
//    public void transactionAuthenticationMaxAmountCase() {
//
//        String endpoint = AppConstants.INTERNAL_END_POINT;
//        TransactionRequest transactionRequest = AppUtils.transactionRequestInstance();
//        transactionRequest.setAmount(9999999999.99);
//
//        if (isExternalAuth){
//            endpoint = "";
//            AppUtils.updateSourceAndMerchantIdForExternal(transactionRequest);
//        }
//
//        String body = CommonAutomationUtils.stringToJson(transactionRequest);
//        if (isExternalAuth)
//            body = AppUtils.removeCardAndRoutingFromRequest(body);
//
//        Response response = AuthenticationTransactionRequest.postRequest(body, endpoint);
//        CommonAutomationUtils.verifyCommonResponseSuccessValidation(response, HttpStatus.SC_OK, Map.of(AppConstants.AMOUNT, 9999999999.99));
//    }
//
//    @Test(groups = {"MC:Authentication", "internal_auth", "external_auth", "Abdul Rehman", "SECTION:Currency Validation", "SC:Currency"}, description = "Validates transaction authentication with a valid currency (SAR)", priority = 11)
//    public void transactionAuthenticationValidCurrencyCase() {
//
//        String endpoint = AppConstants.INTERNAL_END_POINT;
//        TransactionRequest transactionRequest = AppUtils.transactionRequestInstance();
//        transactionRequest.setCurrency("SAR");
//
//        if (isExternalAuth){
//            endpoint = "";
//            AppUtils.updateSourceAndMerchantIdForExternal(transactionRequest);
//        }
//
//        String body = CommonAutomationUtils.stringToJson(transactionRequest);
//        if (isExternalAuth)
//            body = AppUtils.removeCardAndRoutingFromRequest(body);
//
//        Response response = AuthenticationTransactionRequest.postRequest(body, endpoint);
//        CommonAutomationUtils.verifyCommonResponseSuccessValidation(response, HttpStatus.SC_OK, Map.of(AppConstants.CURRENCY, "SAR"));
//    }
//
//    @Test(groups = {"MC:Authentication", "internal_auth", "external_auth", "Abdul Rehman", "SECTION:Currency Validation", "SC:Currency"}, description = "Validates transaction authentication with an unknown but valid currency (YEN)", priority = 12)
//    public void transactionAuthenticationValidUnknownCurrencyCase() {
//
//        String endpoint = AppConstants.INTERNAL_END_POINT;
//        TransactionRequest transactionRequest = AppUtils.transactionRequestInstance();
//        transactionRequest.setCurrency("YEN");
//
//        if (isExternalAuth){
//            endpoint = "";
//            AppUtils.updateSourceAndMerchantIdForExternal(transactionRequest);
//        }
//
//        String body = CommonAutomationUtils.stringToJson(transactionRequest);
//        if (isExternalAuth)
//            body = AppUtils.removeCardAndRoutingFromRequest(body);
//
//        Response response = AuthenticationTransactionRequest.postRequest(body, endpoint);
//        CommonAutomationUtils.verifyCommonResponseSuccessValidation(response, HttpStatus.SC_OK, Map.of(AppConstants.CURRENCY, "YEN"));
//    }
//
//    @Test(groups = {"MC:Authentication", "internal_auth", "external_auth", "Abdul Rehman", "SECTION:Currency Validation", "SC:Currency"}, description = "Validates transaction authentication fails with an invalid currency code (ABC)", priority = 13)
//    public void transactionAuthenticationInvalidCurrencyCase() {
//
//        String endpoint = AppConstants.INTERNAL_END_POINT;
//        TransactionRequest transactionRequest = AppUtils.transactionRequestInstance();
//        transactionRequest.setCurrency("ABC");
//
//        if (isExternalAuth){
//            endpoint = "";
//            AppUtils.updateSourceAndMerchantIdForExternal(transactionRequest);
//        }
//
//        String body = CommonAutomationUtils.stringToJson(transactionRequest);
//        if (isExternalAuth)
//            body = AppUtils.removeCardAndRoutingFromRequest(body);
//
//        Response response = AuthenticationTransactionRequest.postRequest(body, endpoint);
//        String textResponse = response.getBody().asString();
//        JsonNode jsonResponse = CommonAutomationUtils.convertToJson(textResponse);
//
//        CommonAutomationUtils.verifyCommonResponseFailedValidation(jsonResponse, response.getStatusCode(), HttpStatus.SC_BAD_REQUEST, AppConstants.INVALID_DATA_CODE, AppConstants.INVALID_DATA_ERROR);
//    }
//
//    @Test(groups = {"MC:Authentication", "internal_auth", "external_auth", "Abdul Rehman", "SECTION:Currency Validation", "SC:Currency"}, description = "Validates transaction authentication fails when currency is null", priority = 14)
//    public void transactionAuthenticationNullCurrencyCase() {
//
//        String endpoint = AppConstants.INTERNAL_END_POINT;
//        TransactionRequest transactionRequest = AppUtils.transactionRequestInstance();
//        transactionRequest.setCurrency(null);
//
//        if (isExternalAuth){
//            endpoint = "";
//            AppUtils.updateSourceAndMerchantIdForExternal(transactionRequest);
//        }
//
//        String body = CommonAutomationUtils.stringToJson(transactionRequest);
//        if (isExternalAuth)
//            body = AppUtils.removeCardAndRoutingFromRequest(body);
//
//        Response response = AuthenticationTransactionRequest.postRequest(body, endpoint);
//        String textResponse = response.getBody().asString();
//        JsonNode jsonResponse = CommonAutomationUtils.convertToJson(textResponse);
//
//        CommonAutomationUtils.verifyCommonResponseFailedValidation(jsonResponse, response.getStatusCode(), HttpStatus.SC_BAD_REQUEST, AppConstants.INVALID_DATA_CODE, AppConstants.INVALID_DATA_ERROR);
//    }
//
//    @Test(groups = {"MC:Authentication", "internal_auth", "external_auth", "Abdul Rehman", "SECTION:Currency Validation", "SC:Currency"}, description = "Validates transaction authentication fails with an empty currency value", priority = 15)
//    public void transactionAuthenticationEmptyCurrencyCase() {
//
//        String endpoint = AppConstants.INTERNAL_END_POINT;
//        TransactionRequest transactionRequest = AppUtils.transactionRequestInstance();
//        transactionRequest.setCurrency("");
//
//        if (isExternalAuth){
//            endpoint = "";
//            AppUtils.updateSourceAndMerchantIdForExternal(transactionRequest);
//        }
//
//        String body = CommonAutomationUtils.stringToJson(transactionRequest);
//        if (isExternalAuth)
//            body = AppUtils.removeCardAndRoutingFromRequest(body);
//
//        Response response = AuthenticationTransactionRequest.postRequest(body, endpoint);
//        String textResponse = response.getBody().asString();
//        JsonNode jsonResponse = CommonAutomationUtils.convertToJson(textResponse);
//
//        CommonAutomationUtils.verifyCommonResponseFailedValidation(jsonResponse, response.getStatusCode(), HttpStatus.SC_BAD_REQUEST, AppConstants.INVALID_DATA_CODE, AppConstants.INVALID_DATA_ERROR);
//    }
//
//    @Test(groups = {"MC:Authentication", "internal_auth", "external_auth", "Abdul Rehman", "SECTION:Currency Validation", "SC:Currency"}, description = "Validates transaction authentication fails when currency is numeric (123)", priority = 16)
//    public void transactionAuthenticationNumbericCurrencyCase() {
//
//        String endpoint = AppConstants.INTERNAL_END_POINT;
//        TransactionRequest transactionRequest = AppUtils.transactionRequestInstance();
//        transactionRequest.setCurrency("123");
//
//        if (isExternalAuth){
//            endpoint = "";
//            AppUtils.updateSourceAndMerchantIdForExternal(transactionRequest);
//        }
//
//        String body = CommonAutomationUtils.stringToJson(transactionRequest);
//        if (isExternalAuth)
//            body = AppUtils.removeCardAndRoutingFromRequest(body);
//
//        Response response = AuthenticationTransactionRequest.postRequest(body, endpoint);
//        String textResponse = response.getBody().asString();
//        JsonNode jsonResponse = CommonAutomationUtils.convertToJson(textResponse);
//
//        CommonAutomationUtils.verifyCommonResponseFailedValidation(jsonResponse, response.getStatusCode(), HttpStatus.SC_BAD_REQUEST, AppConstants.INVALID_DATA_CODE, AppConstants.INVALID_DATA_ERROR);
//    }
//
//    @Test(groups = {"MC:Authentication", "internal_auth", "external_auth", "Abdul Rehman", "SECTION:Save Card Validation", "SC:Save Card"}, description = "Validates transaction authentication with save card option set to false", priority = 17)
//    public void transactionAuthenticationFalseSaveCardCase() {
//
//        String endpoint = AppConstants.INTERNAL_END_POINT;
//        TransactionRequest transactionRequest = AppUtils.transactionRequestInstance();
//        transactionRequest.setSaveCard(false);
//
//        if (isExternalAuth){
//            endpoint = "";
//            AppUtils.updateSourceAndMerchantIdForExternal(transactionRequest);
//        }
//
//        String body = CommonAutomationUtils.stringToJson(transactionRequest);
//        if (isExternalAuth)
//            body = AppUtils.removeCardAndRoutingFromRequest(body);
//
//        Response response = AuthenticationTransactionRequest.postRequest(body, endpoint);
//        CommonAutomationUtils.verifyCommonResponseSuccessValidation(response, HttpStatus.SC_OK, Map.of(AppConstants.SAVE_CARD, false));
//    }
//
//    @Test(groups = {"MC:Authentication", "internal_auth", "external_auth", "Abdul Rehman", "SECTION:Save Card Validation", "SC:Save Card"}, description = "Validates transaction authentication with save card option set to true", priority = 18)
//    public void transactionAuthenticationTrueSaveCardCase() {
//
//        String endpoint = AppConstants.INTERNAL_END_POINT;
//        TransactionRequest transactionRequest = AppUtils.transactionRequestInstance();
//        transactionRequest.setSaveCard(true);
//
//        if (isExternalAuth){
//            endpoint = "";
//            AppUtils.updateSourceAndMerchantIdForExternal(transactionRequest);
//        }
//
//        String body = CommonAutomationUtils.stringToJson(transactionRequest);
//        if (isExternalAuth)
//            body = AppUtils.removeCardAndRoutingFromRequest(body);
//
//        Response response = AuthenticationTransactionRequest.postRequest(body, endpoint);
//        CommonAutomationUtils.verifyCommonResponseSuccessValidation(response, HttpStatus.SC_OK, Map.of(AppConstants.SAVE_CARD, true));
//    }
//
//    @Test(groups = {"MC:Authentication", "internal_auth", "external_auth", "Abdul Rehman", "SECTION:Save Card Validation", "SC:Save Card"}, description = "Validates transaction authentication with save card option set to null", priority = 19)
//    public void transactionAuthenticationNullSaveCardCase() {
//
//        String endpoint = AppConstants.INTERNAL_END_POINT;
//        TransactionRequest transactionRequest = AppUtils.transactionRequestInstance();
//        transactionRequest.setSaveCard(null);
//
//        if (isExternalAuth){
//            endpoint = "";
//            AppUtils.updateSourceAndMerchantIdForExternal(transactionRequest);
//        }
//
//        String body = CommonAutomationUtils.stringToJson(transactionRequest);
//        if (isExternalAuth)
//            body = AppUtils.removeCardAndRoutingFromRequest(body);
//
//        Response response = AuthenticationTransactionRequest.postRequest(body, endpoint);
//        CommonAutomationUtils.verifyCommonResponseSuccessValidation(response, HttpStatus.SC_OK);
//        CommonAutomationUtils.verifyNonAvailableKey(response, List.of(AppConstants.SAVE_CARD));
//    }
//
//    @Test(groups = {"MC:Authentication", "internal_auth", "external_auth", "Abdul Rehman", "SECTION:Description Validation", "SC:Description"}, description = "Validates transaction authentication with a valid description", priority = 20)
//    public void transactionAuthenticationValidDescriptionCase() {
//
//        String endpoint = AppConstants.INTERNAL_END_POINT;
//        TransactionRequest transactionRequest = AppUtils.transactionRequestInstance();
//        transactionRequest.setDescription("Test Description");
//
//        if (isExternalAuth){
//            endpoint = "";
//            AppUtils.updateSourceAndMerchantIdForExternal(transactionRequest);
//        }
//
//        String body = CommonAutomationUtils.stringToJson(transactionRequest);
//        if (isExternalAuth)
//            body = AppUtils.removeCardAndRoutingFromRequest(body);
//
//        Response response = AuthenticationTransactionRequest.postRequest(body, endpoint);
//        CommonAutomationUtils.verifyCommonResponseSuccessValidation(response, HttpStatus.SC_OK, Map.of(AppConstants.DESCRIPTION, "Test Description"));
//    }
//
//    @Test(groups = {"MC:Authentication", "internal_auth", "external_auth", "Abdul Rehman", "SECTION:Description Validation", "SC:Description"}, description = "Validates transaction authentication with an empty description", priority = 21)
//    public void transactionAuthenticationEmptyDescriptionCase() {
//
//        String endpoint = AppConstants.INTERNAL_END_POINT;
//        TransactionRequest transactionRequest = AppUtils.transactionRequestInstance();
//        transactionRequest.setDescription("");
//
//        if (isExternalAuth){
//            endpoint = "";
//            AppUtils.updateSourceAndMerchantIdForExternal(transactionRequest);
//        }
//
//        String body = CommonAutomationUtils.stringToJson(transactionRequest);
//        if (isExternalAuth)
//            body = AppUtils.removeCardAndRoutingFromRequest(body);
//
//        Response response = AuthenticationTransactionRequest.postRequest(body, endpoint);
//        CommonAutomationUtils.verifyCommonResponseSuccessValidation(response, HttpStatus.SC_OK, Map.of(AppConstants.DESCRIPTION, ""));
//    }
//
//    @Test(groups = {"MC:Authentication", "internal_auth", "external_auth", "Abdul Rehman", "SECTION:Description Validation", "SC:Description"}, description = "Validates transaction authentication with a null description", priority = 22)
//    public void transactionAuthenticationNullDescriptionCase() {
//
//        String endpoint = AppConstants.INTERNAL_END_POINT;
//        TransactionRequest transactionRequest = AppUtils.transactionRequestInstance();
//        transactionRequest.setDescription(null);
//
//        if (isExternalAuth){
//            endpoint = "";
//            AppUtils.updateSourceAndMerchantIdForExternal(transactionRequest);
//        }
//
//        String body = CommonAutomationUtils.stringToJson(transactionRequest);
//        if (isExternalAuth)
//            body = AppUtils.removeCardAndRoutingFromRequest(body);
//
//        Response response = AuthenticationTransactionRequest.postRequest(body, endpoint);
//        CommonAutomationUtils.verifyCommonResponseSuccessValidation(response, HttpStatus.SC_OK);
//        CommonAutomationUtils.verifyNonAvailableKey(response, List.of(AppConstants.DESCRIPTION));
//    }
//
//    @Test(groups = {"MC:Authentication", "internal_auth", "external_auth", "Abdul Rehman", "SECTION:Description Validation", "SC:Description"}, description = "Validates transaction authentication with a numeric description", priority = 23)
//    public void transactionAuthenticationNumericDescriptionCase() {
//
//        String endpoint = AppConstants.INTERNAL_END_POINT;
//        TransactionRequest transactionRequest = AppUtils.transactionRequestInstance();
//        transactionRequest.setDescription("12345");
//
//        if (isExternalAuth){
//            endpoint = "";
//            AppUtils.updateSourceAndMerchantIdForExternal(transactionRequest);
//        }
//
//        String body = CommonAutomationUtils.stringToJson(transactionRequest);
//        if (isExternalAuth)
//            body = AppUtils.removeCardAndRoutingFromRequest(body);
//
//        Response response = AuthenticationTransactionRequest.postRequest(body, endpoint);
//        CommonAutomationUtils.verifyCommonResponseSuccessValidation(response, HttpStatus.SC_OK, Map.of(AppConstants.DESCRIPTION, "12345"));
//    }
//
//    @Test(groups = {"MC:Authentication", "internal_auth", "external_auth", "Abdul Rehman", "SECTION:Transaction Validation", "SC:Transaction"}, description = "Validates transaction authentication with a null transaction", priority = 24)
//    public void transactionAuthenticationNullTransactionCase() {
//
//        String endpoint = AppConstants.INTERNAL_END_POINT;
//        TransactionRequest transactionRequest = AppUtils.transactionRequestInstance();
//        transactionRequest.setTransaction(null);
//
//        if (isExternalAuth){
//            endpoint = "";
//            AppUtils.updateSourceAndMerchantIdForExternal(transactionRequest);
//        }
//
//        String body = CommonAutomationUtils.stringToJson(transactionRequest);
//        if (isExternalAuth)
//            body = AppUtils.removeCardAndRoutingFromRequest(body);
//
//        Response response = AuthenticationTransactionRequest.postRequest(body, endpoint);
//        CommonAutomationUtils.verifyCommonResponseSuccessValidation(response, HttpStatus.SC_OK);
//    }
//
//    @Test(groups = {"MC:Authentication", "internal_auth", "external_auth", "Abdul Rehman", "SECTION:Transaction Id Validation", "SC:Transaction Id"}, description = "Validates transaction authentication with a valid charge transaction ID", priority = 25)
//    public void transactionAuthenticationChargeTransactionIdCase() {
//
//        String endpoint = AppConstants.INTERNAL_END_POINT;
//        UUID uuid = UUID.randomUUID();
//        TransactionRequest transactionRequest = AppUtils.transactionRequestInstance();
//        TransactionRequest.Transaction transaction = transactionRequest.getTransaction();
//        transaction.setId("trx_charge_" + uuid);
//        transaction.setType(TransactionType.CHARGE);
//
//        if (isExternalAuth){
//            endpoint = "";
//            AppUtils.updateSourceAndMerchantIdForExternal(transactionRequest);
//        }
//
//        String body = CommonAutomationUtils.stringToJson(transactionRequest);
//        if (isExternalAuth)
//            body = AppUtils.removeCardAndRoutingFromRequest(body);
//
//        Response response = AuthenticationTransactionRequest.postRequest(body, endpoint);
//        CommonAutomationUtils.verifyCommonResponseSuccessValidation(response, HttpStatus.SC_OK, Map.of(AppConstants.TRANSACTION + "." + AppConstants.ID, "trx_charge_" + uuid,
//                AppConstants.TRANSACTION + "." + AppConstants.TYPE, "CHARGE"));
//    }
//
//    @Test(groups = {"MC:Authentication", "internal_auth", "external_auth", "Abdul Rehman", "SECTION:Transaction Id Validation", "SC:Transaction Id"}, description = "Validates transaction authentication with a valid authorize transaction ID", priority = 26)
//    public void transactionAuthenticationAuthorizeTransactionTypeIdCase() {
//
//        String endpoint = AppConstants.INTERNAL_END_POINT;
//        UUID uuid = UUID.randomUUID();
//        TransactionRequest transactionRequest = AppUtils.transactionRequestInstance();
//        TransactionRequest.Transaction transaction = transactionRequest.getTransaction();
//        transaction.setId("trx_charge_" + uuid);
//        transaction.setType(TransactionType.AUTHORIZE);
//
//        if (isExternalAuth){
//            endpoint = "";
//            AppUtils.updateSourceAndMerchantIdForExternal(transactionRequest);
//        }
//
//        String body = CommonAutomationUtils.stringToJson(transactionRequest);
//        if (isExternalAuth)
//            body = AppUtils.removeCardAndRoutingFromRequest(body);
//
//        Response response = AuthenticationTransactionRequest.postRequest(body, endpoint);
//        CommonAutomationUtils.verifyCommonResponseSuccessValidation(response, HttpStatus.SC_OK, Map.of(AppConstants.TRANSACTION + "." + AppConstants.ID, "trx_charge_" + uuid,
//                AppConstants.TRANSACTION + "." + AppConstants.TYPE, "AUTHORIZE"));
//    }
//
//    @Test(groups = {"MC:Authentication", "internal_auth", "external_auth", "Abdul Rehman", "SECTION:Transaction Id Validation", "SC:Transaction Id"}, description = "Validates transaction authentication with an empty transaction ID", priority = 27)
//    public void transactionAuthenticationEmptyTransactionIdCase() {
//
//        String endpoint = AppConstants.INTERNAL_END_POINT;
//        TransactionRequest transactionRequest = AppUtils.transactionRequestInstance();
//        TransactionRequest.Transaction transaction = transactionRequest.getTransaction();
//        transaction.setId("");
//        transaction.setType(TransactionType.CHARGE);
//
//        if (isExternalAuth){
//            endpoint = "";
//            AppUtils.updateSourceAndMerchantIdForExternal(transactionRequest);
//        }
//
//        String body = CommonAutomationUtils.stringToJson(transactionRequest);
//        if (isExternalAuth)
//            body = AppUtils.removeCardAndRoutingFromRequest(body);
//
//        Response response = AuthenticationTransactionRequest.postRequest(body, endpoint);
//        CommonAutomationUtils.verifyCommonResponseSuccessValidation(response, HttpStatus.SC_OK, Map.of(AppConstants.TRANSACTION + "." + AppConstants.TYPE, "CHARGE"));
//    }
//
//    @Test(groups = {"MC:Authentication", "internal_auth", "external_auth", "Abdul Rehman", "SECTION:Transaction Id Validation", "SC:Transaction Id"}, description = "Validates transaction authentication with a null transaction ID", priority = 28)
//    public void transactionAuthenticationNullTransactionIdCase() {
//
//        String endpoint = AppConstants.INTERNAL_END_POINT;
//        TransactionRequest transactionRequest = AppUtils.transactionRequestInstance();
//        TransactionRequest.Transaction transaction = transactionRequest.getTransaction();
//        transaction.setId(null);
//        transaction.setType(TransactionType.CHARGE);
//
//        if (isExternalAuth){
//            endpoint = "";
//            AppUtils.updateSourceAndMerchantIdForExternal(transactionRequest);
//        }
//
//        String body = CommonAutomationUtils.stringToJson(transactionRequest);
//        if (isExternalAuth)
//            body = AppUtils.removeCardAndRoutingFromRequest(body);
//
//        Response response = AuthenticationTransactionRequest.postRequest(body, endpoint);
//        CommonAutomationUtils.verifyCommonResponseSuccessValidation(response, HttpStatus.SC_OK, Map.of(AppConstants.TRANSACTION + "." + AppConstants.TYPE, "CHARGE"));
//        CommonAutomationUtils.verifyNonAvailableKey(response, List.of(AppConstants.TRANSACTION + "." + AppConstants.ID));
//    }
//
//    @Test(groups = {"MC:Authentication", "internal_auth", "external_auth", "Abdul Rehman", "SECTION:Transaction Type Validation", "SC:Transaction Type"}, description = "Validates transaction authentication with an empty transaction type ID", priority = 29)
//    public void transactionAuthenticationEmptyTransactionTypeIdCase() {
//
//        String endpoint = AppConstants.INTERNAL_END_POINT;
//        TransactionRequest transactionRequest = AppUtils.transactionRequestInstance();
//        TransactionRequest.Transaction transaction = transactionRequest.getTransaction();
//        transaction.setId("trx_charge_" + UUID.randomUUID());
//
//        if (isExternalAuth){
//            endpoint = "";
//            AppUtils.updateSourceAndMerchantIdForExternal(transactionRequest);
//        }
//
//        String body = CommonAutomationUtils.stringToJson(transactionRequest);
//        String updatedBody = CommonAutomationUtils.modifyJson(body, "MODIFY", "transaction.type", "");
//        if (isExternalAuth)
//            updatedBody = AppUtils.removeCardAndRoutingFromRequest(updatedBody);
//
//        Response response = AuthenticationTransactionRequest.postRequest(updatedBody, endpoint);
//        String textResponse = response.getBody().asString();
//        JsonNode jsonResponse = CommonAutomationUtils.convertToJson(textResponse);
//
//        CommonAutomationUtils.verifyCommonResponseFailedValidation(jsonResponse, response.getStatusCode(), HttpStatus.SC_BAD_REQUEST, AppConstants.INVALID_DATA_CODE, AppConstants.INVALID_DATA_ERROR);
//    }
//
//    @Test(groups = {"MC:Authentication", "internal_auth", "external_auth", "Abdul Rehman", "SECTION:Transaction Type Validation", "SC:Transaction Type"}, description = "Validates transaction authentication with an invalid transaction type ID", priority = 30)
//    public void transactionAuthenticationInvalidTransactionTypeIdCase() {
//
//        String endpoint = AppConstants.INTERNAL_END_POINT;
//        TransactionRequest transactionRequest = AppUtils.transactionRequestInstance();
//        TransactionRequest.Transaction transaction = transactionRequest.getTransaction();
//        transaction.setId("trx_charge_" + UUID.randomUUID());
//        transaction.setType(TransactionType.CHARGE);
//
//        if (isExternalAuth){
//            endpoint = "";
//            AppUtils.updateSourceAndMerchantIdForExternal(transactionRequest);
//        }
//
//        String body = CommonAutomationUtils.stringToJson(transactionRequest);
//        String updatedBody = CommonAutomationUtils.modifyJson(body, "MODIFY", "transaction.type", "INVALID_TYPE");
//        if (isExternalAuth)
//            updatedBody = AppUtils.removeCardAndRoutingFromRequest(updatedBody);
//
//        Response response = AuthenticationTransactionRequest.postRequest(updatedBody, endpoint);
//        String textResponse = response.getBody().asString();
//        JsonNode jsonResponse = CommonAutomationUtils.convertToJson(textResponse);
//
//        CommonAutomationUtils.verifyCommonResponseFailedValidation(jsonResponse, response.getStatusCode(), HttpStatus.SC_BAD_REQUEST, AppConstants.INVALID_DATA_CODE, AppConstants.INVALID_DATA_ERROR);
//    }
//
//    @Test(groups = {"MC:Authentication", "internal_auth", "external_auth", "Abdul Rehman", "SECTION:Transaction Type Validation", "SC:Transaction Type"}, description = "Validates transaction authentication with a null transaction type ID", priority = 31)
//    public void transactionAuthenticationNullTransactionTypeIdCase() {
//
//        String endpoint = AppConstants.INTERNAL_END_POINT;
//        TransactionRequest transactionRequest = AppUtils.transactionRequestInstance();
//        TransactionRequest.Transaction transaction = transactionRequest.getTransaction();
//        transaction.setId("trx_charge_" + UUID.randomUUID());
//        transaction.setType(null);
//
//        if (isExternalAuth){
//            endpoint = "";
//            AppUtils.updateSourceAndMerchantIdForExternal(transactionRequest);
//        }
//
//        String body = CommonAutomationUtils.stringToJson(transactionRequest);
//        if (isExternalAuth)
//            body = AppUtils.removeCardAndRoutingFromRequest(body);
//
//        Response response = AuthenticationTransactionRequest.postRequest(body, endpoint);
//        String textResponse = response.getBody().asString();
//        JsonNode jsonResponse = CommonAutomationUtils.convertToJson(textResponse);
//
//        CommonAutomationUtils.verifyCommonResponseFailedValidation(jsonResponse, response.getStatusCode(), HttpStatus.SC_BAD_REQUEST, AppConstants.INVALID_DATA_CODE, AppConstants.INVALID_DATA_ERROR);
//    }
//
//    @Test(groups = {"MC:Authentication", "internal_auth", "external_auth", "Abdul Rehman", "SECTION:Reference Validation", "SC:Reference"}, description = "Validates transaction authentication fails with a null reference value", priority = 32)
//    public void transactionAuthenticationNullReferenceCase() {
//
//        String endpoint = AppConstants.INTERNAL_END_POINT;
//        TransactionRequest transactionRequest = AppUtils.transactionRequestInstance();
//        transactionRequest.setReference(null);
//
//        if (isExternalAuth){
//            endpoint = "";
//            AppUtils.updateSourceAndMerchantIdForExternal(transactionRequest);
//        }
//
//        String body = CommonAutomationUtils.stringToJson(transactionRequest);
//        if (isExternalAuth)
//            body = AppUtils.removeCardAndRoutingFromRequest(body);
//
//        Response response = AuthenticationTransactionRequest.postRequest(body, endpoint);
//        CommonAutomationUtils.verifyCommonResponseSuccessValidation(response, HttpStatus.SC_OK);
//        CommonAutomationUtils.verifyNonAvailableKey(response, List.of(AppConstants.REFERENCE));
//    }
//
//    @Test(groups = {"MC:Authentication", "internal_auth", "external_auth", "Abdul Rehman", "SECTION:Reference Validation", "SC:Reference"}, description = "Validates transaction authentication with a valid reference transaction", priority = 33)
//    public void transactionAuthenticationValidReferenceTransactionCase() {
//
//        String endpoint = AppConstants.INTERNAL_END_POINT;
//        TransactionRequest transactionRequest = AppUtils.transactionRequestInstance();
//
//        if (isExternalAuth){
//            endpoint = "";
//            AppUtils.updateSourceAndMerchantIdForExternal(transactionRequest);
//        }
//
//        String body = CommonAutomationUtils.stringToJson(transactionRequest);
//        if (isExternalAuth)
//            body = AppUtils.removeCardAndRoutingFromRequest(body);
//
//        Response response = AuthenticationTransactionRequest.postRequest(body, endpoint);
//        CommonAutomationUtils.verifyCommonResponseSuccessValidation(response, HttpStatus.SC_OK, Map.of(AppConstants.REFERENCE + "." + AppConstants.TRANSACTION, "tck_LV02G0720231644Xj5u3007999",
//                AppConstants.REFERENCE + "." + AppConstants.ORDER, "7730231644079995502"));
//    }
//
//    @Test(groups = {"MC:Authentication", "internal_auth", "external_auth", "Abdul Rehman", "SECTION:Reference Validation", "SC:Reference"}, description = "Validates transaction authentication with a null reference transaction value", priority = 34)
//    public void transactionAuthenticationReferenceNullTransactionCase() {
//
//        String endpoint = AppConstants.INTERNAL_END_POINT;
//        TransactionRequest transactionRequest = AppUtils.transactionRequestInstance();
//        transactionRequest.getReference().setTransaction(null);
//
//        if (isExternalAuth){
//            endpoint = "";
//            AppUtils.updateSourceAndMerchantIdForExternal(transactionRequest);
//        }
//
//        String body = CommonAutomationUtils.stringToJson(transactionRequest);
//        if (isExternalAuth)
//            body = AppUtils.removeCardAndRoutingFromRequest(body);
//
//        Response response = AuthenticationTransactionRequest.postRequest(body, endpoint);
//        CommonAutomationUtils.verifyCommonResponseSuccessValidation(response, HttpStatus.SC_OK, Map.of(AppConstants.REFERENCE + "." + AppConstants.ORDER, "7730231644079995502"));
//        CommonAutomationUtils.verifyNonAvailableKey(response, List.of(AppConstants.REFERENCE + "." + AppConstants.TRANSACTION));
//    }
//
//    @Test(groups = {"MC:Authentication", "internal_auth", "external_auth", "Abdul Rehman", "SECTION:Reference Validation", "SC:Reference"}, description = "Validates transaction authentication with an empty reference transaction value", priority = 35)
//    public void transactionAuthenticationReferenceEmptyTransactionCase() {
//
//        String endpoint = AppConstants.INTERNAL_END_POINT;
//        TransactionRequest transactionRequest = AppUtils.transactionRequestInstance();
//        transactionRequest.getReference().setTransaction("");
//
//        if (isExternalAuth){
//            endpoint = "";
//            AppUtils.updateSourceAndMerchantIdForExternal(transactionRequest);
//        }
//
//        String body = CommonAutomationUtils.stringToJson(transactionRequest);
//        if (isExternalAuth)
//            body = AppUtils.removeCardAndRoutingFromRequest(body);
//
//        Response response = AuthenticationTransactionRequest.postRequest(body, endpoint);
//        CommonAutomationUtils.verifyCommonResponseSuccessValidation(response, HttpStatus.SC_OK, Map.of(AppConstants.REFERENCE + "." + AppConstants.TRANSACTION, "",
//                AppConstants.REFERENCE + "." + AppConstants.ORDER, "7730231644079995502"));
//    }
//
//    @Test(groups = {"MC:Authentication", "internal_auth", "external_auth", "Abdul Rehman", "SECTION:Reference Validation", "SC:Reference"}, description = "Validates transaction authentication with an empty reference order value", priority = 36)
//    public void transactionAuthenticationReferenceEmptyOrderCase() {
//
//        String endpoint = AppConstants.INTERNAL_END_POINT;
//        TransactionRequest transactionRequest = AppUtils.transactionRequestInstance();
//        transactionRequest.getReference().setOrder("");
//
//        if (isExternalAuth){
//            endpoint = "";
//            AppUtils.updateSourceAndMerchantIdForExternal(transactionRequest);
//        }
//
//        String body = CommonAutomationUtils.stringToJson(transactionRequest);
//        if (isExternalAuth)
//            body = AppUtils.removeCardAndRoutingFromRequest(body);
//
//        Response response = AuthenticationTransactionRequest.postRequest(body, endpoint);
//        CommonAutomationUtils.verifyCommonResponseSuccessValidation(response, HttpStatus.SC_OK, Map.of(AppConstants.REFERENCE + "." + AppConstants.TRANSACTION, "tck_LV02G0720231644Xj5u3007999",
//                AppConstants.REFERENCE + "." + AppConstants.ORDER, ""));
//    }
//
//    @Test(groups = {"MC:Authentication", "internal_auth", "external_auth", "Abdul Rehman", "SECTION:Reference Validation", "SC:Reference"}, description = "Validates transaction authentication with a null reference order value", priority = 37)
//    public void transactionAuthenticationReferenceNullOrderCase() {
//
//        String endpoint = AppConstants.INTERNAL_END_POINT;
//        TransactionRequest transactionRequest = AppUtils.transactionRequestInstance();
//        transactionRequest.getReference().setOrder(null);
//
//        if (isExternalAuth){
//            endpoint = "";
//            AppUtils.updateSourceAndMerchantIdForExternal(transactionRequest);
//        }
//
//        String body = CommonAutomationUtils.stringToJson(transactionRequest);
//        if (isExternalAuth)
//            body = AppUtils.removeCardAndRoutingFromRequest(body);
//
//        Response response = AuthenticationTransactionRequest.postRequest(body, endpoint);
//        CommonAutomationUtils.verifyCommonResponseSuccessValidation(response, HttpStatus.SC_OK, Map.of(AppConstants.REFERENCE + "." + AppConstants.TRANSACTION, "tck_LV02G0720231644Xj5u3007999"));
//        CommonAutomationUtils.verifyNonAvailableKey(response, List.of(AppConstants.REFERENCE + "." + AppConstants.ORDER));
//    }
//
//    @Test(groups = {"MC:Authentication", "internal_auth", "external_auth", "Abdul Rehman", "SECTION:Invoice Validation", "SC:Invoice"}, description = "Validates transaction authentication with a null invoice value", priority = 38)
//    public void transactionAuthenticationNullInvoiceCase() {
//
//        String endpoint = AppConstants.INTERNAL_END_POINT;
//        TransactionRequest transactionRequest = AppUtils.transactionRequestInstance();
//        transactionRequest.setInvoice(null);
//
//        if (isExternalAuth){
//            endpoint = "";
//            AppUtils.updateSourceAndMerchantIdForExternal(transactionRequest);
//        }
//
//        String body = CommonAutomationUtils.stringToJson(transactionRequest);
//        if (isExternalAuth)
//            body = AppUtils.removeCardAndRoutingFromRequest(body);
//
//        Response response = AuthenticationTransactionRequest.postRequest(body, endpoint);
//        CommonAutomationUtils.verifyCommonResponseSuccessValidation(response, HttpStatus.SC_OK);
//        CommonAutomationUtils.verifyNonAvailableKey(response, List.of(AppConstants.INVOICE));
//    }
//
//    @Test(groups = {"MC:Authentication", "internal_auth", "external_auth", "Abdul Rehman", "SECTION:Invoice Id Validation", "SC:Invoice Id"}, description = "Validates transaction authentication with a valid invoice ID", priority = 39)
//    public void transactionAuthenticationValidInvoiceIdCase() {
//
//        String endpoint = AppConstants.INTERNAL_END_POINT;
//        TransactionRequest transactionRequest = AppUtils.transactionRequestInstance();
//        transactionRequest.getInvoice().setId("invoice_12345");
//
//        if (isExternalAuth){
//            endpoint = "";
//            AppUtils.updateSourceAndMerchantIdForExternal(transactionRequest);
//        }
//
//        String body = CommonAutomationUtils.stringToJson(transactionRequest);
//        if (isExternalAuth)
//            body = AppUtils.removeCardAndRoutingFromRequest(body);
//
//        Response response = AuthenticationTransactionRequest.postRequest(body, endpoint);
//        CommonAutomationUtils.verifyCommonResponseSuccessValidation(response, HttpStatus.SC_OK, Map.of(AppConstants.INVOICE + "." + AppConstants.ID, "invoice_12345"));
//    }
//
//    @Test(groups = {"MC:Authentication", "internal_auth", "external_auth", "Abdul Rehman", "SECTION:Invoice Id Validation", "SC:Invoice Id"}, description = "Validates transaction authentication with an empty invoice ID", priority = 40)
//    public void transactionAuthenticationEmptyInvoiceIdCase() {
//
//        String endpoint = AppConstants.INTERNAL_END_POINT;
//        TransactionRequest transactionRequest = AppUtils.transactionRequestInstance();
//        transactionRequest.getInvoice().setId("");
//
//        if (isExternalAuth){
//            endpoint = "";
//            AppUtils.updateSourceAndMerchantIdForExternal(transactionRequest);
//        }
//
//        String body = CommonAutomationUtils.stringToJson(transactionRequest);
//        if (isExternalAuth)
//            body = AppUtils.removeCardAndRoutingFromRequest(body);
//
//        Response response = AuthenticationTransactionRequest.postRequest(body, endpoint);
//        CommonAutomationUtils.verifyCommonResponseSuccessValidation(response, HttpStatus.SC_OK, Map.of(AppConstants.INVOICE + "." + AppConstants.ID, ""));
//    }
//
//    @Test(groups = {"MC:Authentication", "internal_auth", "external_auth", "Abdul Rehman", "SECTION:Invoice Id Validation", "SC:Invoice Id"}, description = "Validates transaction authentication with a null invoice ID", priority = 41)
//    public void transactionAuthenticationNullInvoiceIdCase() {
//
//        String endpoint = AppConstants.INTERNAL_END_POINT;
//        TransactionRequest transactionRequest = AppUtils.transactionRequestInstance();
//        transactionRequest.getInvoice().setId(null);
//
//        if (isExternalAuth){
//            endpoint = "";
//            AppUtils.updateSourceAndMerchantIdForExternal(transactionRequest);
//        }
//
//        String body = CommonAutomationUtils.stringToJson(transactionRequest);
//        if (isExternalAuth)
//            body = AppUtils.removeCardAndRoutingFromRequest(body);
//
//        Response response = AuthenticationTransactionRequest.postRequest(body, endpoint);
//        CommonAutomationUtils.verifyCommonResponseSuccessValidation(response, HttpStatus.SC_OK);
//        CommonAutomationUtils.verifyNonAvailableKey(response, List.of(AppConstants.INVOICE + "." + AppConstants.ID));
//    }
//
//    @Test(groups = {"MC:Authentication", "internal_auth", "external_auth", "Abdul Rehman", "SECTION:Customer Validation", "SC:Customer"}, description = "Validates transaction authentication with a null customer value", priority = 42)
//    public void transactionAuthenticationNullCustomerCase() {
//
//        String endpoint = AppConstants.INTERNAL_END_POINT;
//        TransactionRequest transactionRequest = AppUtils.transactionRequestInstance();
//        transactionRequest.setCustomer(null);
//
//        if (isExternalAuth){
//            endpoint = "";
//            AppUtils.updateSourceAndMerchantIdForExternal(transactionRequest);
//        }
//
//        String body = CommonAutomationUtils.stringToJson(transactionRequest);
//        if (isExternalAuth)
//            body = AppUtils.removeCardAndRoutingFromRequest(body);
//
//        Response response = AuthenticationTransactionRequest.postRequest(body, endpoint);
//        CommonAutomationUtils.verifyCommonResponseSuccessValidation(response, HttpStatus.SC_OK);
//        CommonAutomationUtils.verifyNonAvailableKey(response, List.of(AppConstants.CUSTOMER));
//    }
//
//    @Test(groups = {"MC:Authentication", "internal_auth", "external_auth", "Abdul Rehman", "SECTION:Customer Id Validation", "SC:Customer Id"}, description = "Validates transaction authentication with a valid customer ID", priority = 43)
//    public void transactionAuthenticationValidCustomerIdCase() {
//
//        String endpoint = AppConstants.INTERNAL_END_POINT;
//        TransactionRequest transactionRequest = AppUtils.transactionRequestInstance();
//
//        if (isExternalAuth){
//            endpoint = "";
//            AppUtils.updateSourceAndMerchantIdForExternal(transactionRequest);
//        }
//
//        String body = CommonAutomationUtils.stringToJson(transactionRequest);
//        if (isExternalAuth)
//            body = AppUtils.removeCardAndRoutingFromRequest(body);
//
//        Response response = AuthenticationTransactionRequest.postRequest(body, endpoint);
//        CommonAutomationUtils.verifyCommonResponseSuccessValidation(response, HttpStatus.SC_OK, Map.of(AppConstants.CUSTOMER + "." + AppConstants.ID, "cus_TS06A5420231128h9FP1110651"));
//    }
//
//    @Test(groups = {"MC:Authentication", "internal_auth", "external_auth", "Abdul Rehman", "SECTION:Customer Id Validation", "SC:Customer Id"}, description = "Validates transaction authentication with an invalid customer ID", priority = 44)
//    public void transactionAuthenticationInvalidCustomerIdCase() {
//
//        String endpoint = AppConstants.INTERNAL_END_POINT;
//        TransactionRequest transactionRequest = AppUtils.transactionRequestInstance();
//        transactionRequest.getCustomer().setId("invalid_id");
//
//        if (isExternalAuth){
//            endpoint = "";
//            AppUtils.updateSourceAndMerchantIdForExternal(transactionRequest);
//        }
//
//        String body = CommonAutomationUtils.stringToJson(transactionRequest);
//        if (isExternalAuth)
//            body = AppUtils.removeCardAndRoutingFromRequest(body);
//
//        Response response = AuthenticationTransactionRequest.postRequest(body, endpoint);
//        CommonAutomationUtils.verifyCommonResponseSuccessValidation(response, HttpStatus.SC_OK, Map.of(AppConstants.CUSTOMER + "." + AppConstants.ID, "invalid_id"));
//    }
//
//    @Test(groups = {"MC:Authentication", "internal_auth", "external_auth", "Abdul Rehman", "SECTION:Customer Id Validation", "SC:Customer Id"}, description = "Validates transaction authentication with an empty customer ID", priority = 45)
//    public void transactionAuthenticationEmptyCustomerIdCase() {
//
//        String endpoint = AppConstants.INTERNAL_END_POINT;
//        TransactionRequest transactionRequest = AppUtils.transactionRequestInstance();
//        transactionRequest.getCustomer().setId("");
//
//        if (isExternalAuth){
//            endpoint = "";
//            AppUtils.updateSourceAndMerchantIdForExternal(transactionRequest);
//        }
//
//        String body = CommonAutomationUtils.stringToJson(transactionRequest);
//        if (isExternalAuth)
//            body = AppUtils.removeCardAndRoutingFromRequest(body);
//
//        Response response = AuthenticationTransactionRequest.postRequest(body, endpoint);
//        CommonAutomationUtils.verifyCommonResponseSuccessValidation(response, HttpStatus.SC_OK, Map.of(AppConstants.CUSTOMER + "." + AppConstants.ID, ""));
//    }
//
//    @Test(groups = {"MC:Authentication", "internal_auth", "external_auth", "Abdul Rehman", "SECTION:Customer Id Validation", "SC:Customer Id"}, description = "Validates transaction authentication with a null customer ID", priority = 46)
//    public void transactionAuthenticationNullCustomerIdCase() {
//
//        String endpoint = AppConstants.INTERNAL_END_POINT;
//        TransactionRequest transactionRequest = AppUtils.transactionRequestInstance();
//        transactionRequest.getCustomer().setId(null);
//
//        if (isExternalAuth){
//            endpoint = "";
//            AppUtils.updateSourceAndMerchantIdForExternal(transactionRequest);
//        }
//
//        String body = CommonAutomationUtils.stringToJson(transactionRequest);
//        if (isExternalAuth)
//            body = AppUtils.removeCardAndRoutingFromRequest(body);
//
//        Response response = AuthenticationTransactionRequest.postRequest(body, endpoint);
//        CommonAutomationUtils.verifyCommonResponseSuccessValidation(response, HttpStatus.SC_OK);
//        CommonAutomationUtils.verifyNonAvailableKey(response, List.of(AppConstants.CUSTOMER + "." + AppConstants.ID));
//    }
//
//    @Test(groups = {"MC:Authentication", "internal_auth", "external_auth", "Abdul Rehman", "SECTION:Customer Name Validation", "SC:Customer Name"}, description = "Validates transaction authentication with a null customer name value", priority = 47)
//    public void transactionAuthenticationNullCustomerNameCase() {
//
//        String endpoint = AppConstants.INTERNAL_END_POINT;
//        TransactionRequest transactionRequest = AppUtils.transactionRequestInstance();
//        transactionRequest.getCustomer().setName(null);
//
//        if (isExternalAuth){
//            endpoint = "";
//            AppUtils.updateSourceAndMerchantIdForExternal(transactionRequest);
//        }
//
//        String body = CommonAutomationUtils.stringToJson(transactionRequest);
//        if (isExternalAuth)
//            body = AppUtils.removeCardAndRoutingFromRequest(body);
//
//        Response response = AuthenticationTransactionRequest.postRequest(body, endpoint);
//        CommonAutomationUtils.verifyCommonResponseSuccessValidation(response, HttpStatus.SC_OK);
//        CommonAutomationUtils.verifyNonAvailableKey(response, List.of(AppConstants.CUSTOMER + "." + AppConstants.NAME));
//    }
//
//    @Test(groups = {"MC:Authentication", "internal_auth", "external_auth", "Abdul Rehman", "SECTION:Customer Name Validation", "SC:Customer Name"}, description = "Validates transaction authentication fails with an empty customer first name value", priority = 48)
//    public void transactionAuthenticationEmptyCustomerNameFirstNameCase() {
//
//        String endpoint = AppConstants.INTERNAL_END_POINT;
//        TransactionRequest transactionRequest = AppUtils.transactionRequestInstance();
//        TransactionRequest.Customer customer = transactionRequest.getCustomer();
//        List<TransactionRequest.Customer.Name> nameList = customer.getName();
//        nameList.get(0).setFirstName("");
//
//        if (isExternalAuth){
//            endpoint = "";
//            AppUtils.updateSourceAndMerchantIdForExternal(transactionRequest);
//        }
//
//        String body = CommonAutomationUtils.stringToJson(transactionRequest);
//        if (isExternalAuth)
//            body = AppUtils.removeCardAndRoutingFromRequest(body);
//
//        Response response = AuthenticationTransactionRequest.postRequest(body, endpoint);
//        String textResponse = response.getBody().asString();
//        JsonNode jsonResponse = CommonAutomationUtils.convertToJson(textResponse);
//
//        CommonAutomationUtils.verifyCommonResponseFailedValidation(jsonResponse, response.getStatusCode(), HttpStatus.SC_BAD_REQUEST, AppConstants.REQUIRED_INPUT_INVALID_CODE, AppConstants.REQUIRED_INPUT_INVALID_ERROR);
//    }
//
//    @Test(groups = {"MC:Authentication", "internal_auth", "external_auth", "Abdul Rehman", "SECTION:Customer Name Validation", "SC:Customer Name"}, description = "Validates transaction authentication with a null customer first name value", priority = 49)
//    public void transactionAuthenticationNullCustomerNameFirstNameCase() {
//
//        String endpoint = AppConstants.INTERNAL_END_POINT;
//        TransactionRequest transactionRequest = AppUtils.transactionRequestInstance();
//        TransactionRequest.Customer customer = transactionRequest.getCustomer();
//        List<TransactionRequest.Customer.Name> nameList = customer.getName();
//        nameList.get(0).setFirstName(null);
//
//        if (isExternalAuth){
//            endpoint = "";
//            AppUtils.updateSourceAndMerchantIdForExternal(transactionRequest);
//        }
//
//        String body = CommonAutomationUtils.stringToJson(transactionRequest);
//        if (isExternalAuth)
//            body = AppUtils.removeCardAndRoutingFromRequest(body);
//
//        Response response = AuthenticationTransactionRequest.postRequest(body, endpoint);
//        CommonAutomationUtils.verifyCommonResponseSuccessValidation(response, HttpStatus.SC_OK);
//        CommonAutomationUtils.verifyNonAvailableKey(response, List.of(AppConstants.CUSTOMER + "." + AppConstants.NAME + "." + AppConstants.FIRST_NAME));
//    }
//
//    @Test(groups = {"MC:Authentication", "internal_auth", "external_auth", "Abdul Rehman", "SECTION:Customer Name Firstname Validation", "SC:Customer Name Firstname"}, description = "Validates transaction authentication with a valid customer first name value", priority = 50)
//    public void transactionAuthenticationValidCustomerNameFirstNameCase() {
//
//        String endpoint = AppConstants.INTERNAL_END_POINT;
//        TransactionRequest transactionRequest = AppUtils.transactionRequestInstance();
//        TransactionRequest.Customer customer = transactionRequest.getCustomer();
//        List<TransactionRequest.Customer.Name> nameList = customer.getName();
//        nameList.get(0).setFirstName("Ali Usman");
//
//        if (isExternalAuth){
//            endpoint = "";
//            AppUtils.updateSourceAndMerchantIdForExternal(transactionRequest);
//        }
//
//        String body = CommonAutomationUtils.stringToJson(transactionRequest);
//        if (isExternalAuth)
//            body = AppUtils.removeCardAndRoutingFromRequest(body);
//
//        Response response = AuthenticationTransactionRequest.postRequest(body, endpoint);
//        CommonAutomationUtils.verifyCommonResponseSuccessValidation(response, HttpStatus.SC_OK, Map.of(AppConstants.CUSTOMER + "." + AppConstants.NAME + "[0]." + AppConstants.FIRST_NAME, "Ali Usman"));
//    }
//
//    @Test(groups = {"MC:Authentication", "internal_auth", "external_auth", "Abdul Rehman", "SECTION:Customer Name Firstname Validation", "SC:Customer Name Firstname"}, description = "Validates transaction authentication with a numeric customer first name value", priority = 51)
//    public void transactionAuthenticationNumericCustomerNameFirstNameCase() {
//
//        String endpoint = AppConstants.INTERNAL_END_POINT;
//        TransactionRequest transactionRequest = AppUtils.transactionRequestInstance();
//        TransactionRequest.Customer customer = transactionRequest.getCustomer();
//        List<TransactionRequest.Customer.Name> nameList = customer.getName();
//        nameList.get(0).setFirstName("12345");
//
//        if (isExternalAuth){
//            endpoint = "";
//            AppUtils.updateSourceAndMerchantIdForExternal(transactionRequest);
//        }
//
//        String body = CommonAutomationUtils.stringToJson(transactionRequest);
//        String updatedBody = CommonAutomationUtils.modifyJson(body, "MODIFY", "customer.name.first_name", 12345);
//        if (isExternalAuth)
//            updatedBody = AppUtils.removeCardAndRoutingFromRequest(updatedBody);
//
//        Response response = AuthenticationTransactionRequest.postRequest(updatedBody, endpoint);
//        CommonAutomationUtils.verifyCommonResponseSuccessValidation(response, HttpStatus.SC_OK, Map.of(AppConstants.CUSTOMER + "." + AppConstants.NAME + "[0]." + AppConstants.FIRST_NAME, "12345"));
//    }
//
//    @Test(groups = {"MC:Authentication", "internal_auth", "external_auth", "Abdul Rehman", "SECTION:Customer Name Firstname Validation", "SC:Customer Name Firstname"}, description = "Validates transaction authentication fails with a customer first name value exceeding the maximum length", priority = 52)
//    public void transactionAuthenticationMaxCustomerNameFirstNameCase() {
//
//        String endpoint = AppConstants.INTERNAL_END_POINT;
//        TransactionRequest transactionRequest = AppUtils.transactionRequestInstance();
//        TransactionRequest.Customer customer = transactionRequest.getCustomer();
//        List<TransactionRequest.Customer.Name> nameList = customer.getName();
//        nameList.get(0).setFirstName("MaximillianAlessandroValentinHarringtonTheThirdOfEvergreenEstateOnTheBanksOfRiverThamesWithGoldenHairAndEyesOfEmeraldGreenTheSeekerOfTruthInTheLandOfTheRisingSun");
//
//        if (isExternalAuth){
//            endpoint = "";
//            AppUtils.updateSourceAndMerchantIdForExternal(transactionRequest);
//        }
//
//        String body = CommonAutomationUtils.stringToJson(transactionRequest);
//        if (isExternalAuth)
//            body = AppUtils.removeCardAndRoutingFromRequest(body);
//
//        Response response = AuthenticationTransactionRequest.postRequest(body, endpoint);
//        String textResponse = response.getBody().asString();
//        JsonNode jsonResponse = CommonAutomationUtils.convertToJson(textResponse);
//
//        CommonAutomationUtils.verifyCommonResponseFailedValidation(jsonResponse, response.getStatusCode(), HttpStatus.SC_BAD_REQUEST, AppConstants.REQUIRED_INPUT_INVALID_CODE, AppConstants.REQUIRED_INPUT_INVALID_ERROR);
//    }
//
//    @Test(groups = {"MC:Authentication", "internal_auth", "external_auth", "Abdul Rehman", "SECTION:Customer Name Middlename Validation", "SC:Customer Name Middlename"}, description = "Validates transaction authentication with an empty customer middle name value", priority = 53)
//    public void transactionAuthenticationEmptyCustomerNameMiddleNameCase() {
//
//        String endpoint = AppConstants.INTERNAL_END_POINT;
//        TransactionRequest transactionRequest = AppUtils.transactionRequestInstance();
//        TransactionRequest.Customer customer = transactionRequest.getCustomer();
//        List<TransactionRequest.Customer.Name> nameList = customer.getName();
//        nameList.get(0).setMiddleName("");
//
//        if (isExternalAuth){
//            endpoint = "";
//            AppUtils.updateSourceAndMerchantIdForExternal(transactionRequest);
//        }
//
//        String body = CommonAutomationUtils.stringToJson(transactionRequest);
//        if (isExternalAuth)
//            body = AppUtils.removeCardAndRoutingFromRequest(body);
//
//        Response response = AuthenticationTransactionRequest.postRequest(body, endpoint);
//        CommonAutomationUtils.verifyCommonResponseSuccessValidation(response, HttpStatus.SC_OK, Map.of(AppConstants.CUSTOMER + "." + AppConstants.NAME + "[0]." + AppConstants.MIDDLE_NAME, ""));
//    }
//
//    @Test(groups = {"MC:Authentication", "internal_auth", "external_auth", "Abdul Rehman", "SECTION:Customer Name Middlename Validation", "SC:Customer Name Middlename"}, description = "Validates transaction authentication with a null customer middle name value", priority = 54)
//    public void transactionAuthenticationNullCustomerNameMiddleNameCase() {
//
//        String endpoint = AppConstants.INTERNAL_END_POINT;
//        TransactionRequest transactionRequest = AppUtils.transactionRequestInstance();
//        TransactionRequest.Customer customer = transactionRequest.getCustomer();
//        List<TransactionRequest.Customer.Name> nameList = customer.getName();
//        nameList.get(0).setMiddleName(null);
//
//        if (isExternalAuth){
//            endpoint = "";
//            AppUtils.updateSourceAndMerchantIdForExternal(transactionRequest);
//        }
//
//        String body = CommonAutomationUtils.stringToJson(transactionRequest);
//        if (isExternalAuth)
//            body = AppUtils.removeCardAndRoutingFromRequest(body);
//
//        Response response = AuthenticationTransactionRequest.postRequest(body, endpoint);
//        CommonAutomationUtils.verifyCommonResponseSuccessValidation(response, HttpStatus.SC_OK);
//        CommonAutomationUtils.verifyNonAvailableKey(response, List.of(AppConstants.CUSTOMER + "." + AppConstants.NAME + "." + AppConstants.MIDDLE_NAME));
//    }
//
//    @Test(groups = {"MC:Authentication", "internal_auth", "external_auth", "Abdul Rehman", "SECTION:Customer Name Middlename Validation", "SC:Customer Name Middlename"}, description = "Validates transaction authentication with a valid customer middle name value", priority = 55)
//    public void transactionAuthenticationValidCustomerNameMiddleNameCase() {
//
//        String endpoint = AppConstants.INTERNAL_END_POINT;
//        TransactionRequest transactionRequest = AppUtils.transactionRequestInstance();
//        TransactionRequest.Customer customer = transactionRequest.getCustomer();
//        List<TransactionRequest.Customer.Name> nameList = customer.getName();
//        nameList.get(0).setMiddleName("Ali Usman");
//
//        if (isExternalAuth){
//            endpoint = "";
//            AppUtils.updateSourceAndMerchantIdForExternal(transactionRequest);
//        }
//
//        String body = CommonAutomationUtils.stringToJson(transactionRequest);
//        if (isExternalAuth)
//            body = AppUtils.removeCardAndRoutingFromRequest(body);
//
//        Response response = AuthenticationTransactionRequest.postRequest(body, endpoint);
//        CommonAutomationUtils.verifyCommonResponseSuccessValidation(response, HttpStatus.SC_OK, Map.of(AppConstants.CUSTOMER + "." + AppConstants.NAME + "[0]." + AppConstants.MIDDLE_NAME, "Ali Usman"));
//    }
//
//    @Test(groups = {"MC:Authentication", "internal_auth", "external_auth", "Abdul Rehman", "SECTION:Customer Name Middlename Validation", "SC:Customer Name Middlename"}, description = "Validates transaction authentication with a numeric customer middle name value", priority = 56)
//    public void transactionAuthenticationNumericCustomerNameMiddleNameCase() {
//
//        String endpoint = AppConstants.INTERNAL_END_POINT;
//        TransactionRequest transactionRequest = AppUtils.transactionRequestInstance();
//        TransactionRequest.Customer customer = transactionRequest.getCustomer();
//        List<TransactionRequest.Customer.Name> nameList = customer.getName();
//        nameList.get(0).setMiddleName("12345");
//
//        if (isExternalAuth){
//            endpoint = "";
//            AppUtils.updateSourceAndMerchantIdForExternal(transactionRequest);
//        }
//
//        String body = CommonAutomationUtils.stringToJson(transactionRequest);
//        String updatedBody = CommonAutomationUtils.modifyJson(body, "MODIFY", "customer.name.middle_name", 12345);
//        if (isExternalAuth)
//            updatedBody = AppUtils.removeCardAndRoutingFromRequest(updatedBody);
//
//        Response response = AuthenticationTransactionRequest.postRequest(updatedBody, endpoint);
//        CommonAutomationUtils.verifyCommonResponseSuccessValidation(response, HttpStatus.SC_OK, Map.of(AppConstants.CUSTOMER + "." + AppConstants.NAME + "[0]." + AppConstants.MIDDLE_NAME, "12345"));
//    }
//
//    @Test(groups = {"MC:Authentication", "internal_auth", "external_auth", "Abdul Rehman", "SECTION:Customer Name Lastname Validation", "SC:Customer Name Lastname"}, description = "Validates transaction authentication with an empty customer last name value", priority = 57)
//    public void transactionAuthenticationEmptyCustomerNameLastNameCase() {
//
//        String endpoint = AppConstants.INTERNAL_END_POINT;
//        TransactionRequest transactionRequest = AppUtils.transactionRequestInstance();
//        TransactionRequest.Customer customer = transactionRequest.getCustomer();
//        List<TransactionRequest.Customer.Name> nameList = customer.getName();
//        nameList.get(0).setLastName("");
//
//        if (isExternalAuth){
//            endpoint = "";
//            AppUtils.updateSourceAndMerchantIdForExternal(transactionRequest);
//        }
//
//        String body = CommonAutomationUtils.stringToJson(transactionRequest);
//        if (isExternalAuth)
//            body = AppUtils.removeCardAndRoutingFromRequest(body);
//
//        Response response = AuthenticationTransactionRequest.postRequest(body, endpoint);
//        CommonAutomationUtils.verifyCommonResponseSuccessValidation(response, HttpStatus.SC_OK, Map.of(AppConstants.CUSTOMER + "." + AppConstants.NAME + "[0]." + AppConstants.LAST_NAME, ""));
//    }
//
//    @Test(groups = {"MC:Authentication", "internal_auth", "external_auth", "Abdul Rehman", "SECTION:Customer Name Lastname Validation", "SC:Customer Name Lastname"}, description = "Validates transaction authentication with a null customer last name value", priority = 58)
//    public void transactionAuthenticationNullCustomerNameLastNameCase() {
//
//        String endpoint = AppConstants.INTERNAL_END_POINT;
//        TransactionRequest transactionRequest = AppUtils.transactionRequestInstance();
//        TransactionRequest.Customer customer = transactionRequest.getCustomer();
//        List<TransactionRequest.Customer.Name> nameList = customer.getName();
//        nameList.get(0).setLastName(null);
//
//        if (isExternalAuth){
//            endpoint = "";
//            AppUtils.updateSourceAndMerchantIdForExternal(transactionRequest);
//        }
//
//        String body = CommonAutomationUtils.stringToJson(transactionRequest);
//        if (isExternalAuth)
//            body = AppUtils.removeCardAndRoutingFromRequest(body);
//
//        Response response = AuthenticationTransactionRequest.postRequest(body, endpoint);
//        CommonAutomationUtils.verifyCommonResponseSuccessValidation(response, HttpStatus.SC_OK);
//        CommonAutomationUtils.verifyNonAvailableKey(response, List.of(AppConstants.CUSTOMER + "." + AppConstants.NAME + "." + AppConstants.LAST_NAME));
//    }
//
//    @Test(groups = {"MC:Authentication", "internal_auth", "external_auth", "Abdul Rehman", "SECTION:Customer Name Lastname Validation", "SC:Customer Name Lastname"}, description = "Validates transaction authentication with a valid customer last name value", priority = 59)
//    public void transactionAuthenticationValidCustomerNameLastNameCase() {
//
//        String endpoint = AppConstants.INTERNAL_END_POINT;
//        TransactionRequest transactionRequest = AppUtils.transactionRequestInstance();
//        TransactionRequest.Customer customer = transactionRequest.getCustomer();
//        List<TransactionRequest.Customer.Name> nameList = customer.getName();
//        nameList.get(0).setLastName("Ali Usman");
//
//        if (isExternalAuth){
//            endpoint = "";
//            AppUtils.updateSourceAndMerchantIdForExternal(transactionRequest);
//        }
//
//        String body = CommonAutomationUtils.stringToJson(transactionRequest);
//        if (isExternalAuth)
//            body = AppUtils.removeCardAndRoutingFromRequest(body);
//
//        Response response = AuthenticationTransactionRequest.postRequest(body, endpoint);
//        CommonAutomationUtils.verifyCommonResponseSuccessValidation(response, HttpStatus.SC_OK, Map.of(AppConstants.CUSTOMER + "." + AppConstants.NAME + "[0]." + AppConstants.LAST_NAME, "Ali Usman"));
//    }
//
//    @Test(groups = {"MC:Authentication", "internal_auth", "external_auth", "Abdul Rehman", "SECTION:Customer Name Lastname Validation", "SC:Customer Name Lastname"}, description = "Validates transaction authentication with a numeric customer last name value", priority = 60)
//    public void transactionAuthenticationNumericCustomerNameLastNameCase() {
//
//        String endpoint = AppConstants.INTERNAL_END_POINT;
//        TransactionRequest transactionRequest = AppUtils.transactionRequestInstance();
//        TransactionRequest.Customer customer = transactionRequest.getCustomer();
//        List<TransactionRequest.Customer.Name> nameList = customer.getName();
//        nameList.get(0).setLastName("12345");
//
//        if (isExternalAuth){
//            endpoint = "";
//            AppUtils.updateSourceAndMerchantIdForExternal(transactionRequest);
//        }
//
//        String body = CommonAutomationUtils.stringToJson(transactionRequest);
//        String updatedBody = CommonAutomationUtils.modifyJson(body, "MODIFY", "customer.name.last_name", 12345);
//        if (isExternalAuth)
//            updatedBody = AppUtils.removeCardAndRoutingFromRequest(updatedBody);
//
//        Response response = AuthenticationTransactionRequest.postRequest(updatedBody, endpoint);
//        CommonAutomationUtils.verifyCommonResponseSuccessValidation(response, HttpStatus.SC_OK, Map.of(AppConstants.CUSTOMER + "." + AppConstants.NAME + "[0]." + AppConstants.LAST_NAME, "12345"));
//    }
//
//    @Test(groups = {"MC:Authentication", "internal_auth", "external_auth", "Abdul Rehman", "SECTION:Customer Name Locale Validation", "SC:Customer Name Locale"}, description = "Validates transaction authentication with an empty customer locale value", priority = 61)
//    public void transactionAuthenticationEmptyCustomerNameLocaleCase() {
//
//        String endpoint = AppConstants.INTERNAL_END_POINT;
//        TransactionRequest transactionRequest = AppUtils.transactionRequestInstance();
//        TransactionRequest.Customer customer = transactionRequest.getCustomer();
//        List<TransactionRequest.Customer.Name> nameList = customer.getName();
//        nameList.get(0).setLocale("");
//
//        if (isExternalAuth){
//            endpoint = "";
//            AppUtils.updateSourceAndMerchantIdForExternal(transactionRequest);
//        }
//
//        String body = CommonAutomationUtils.stringToJson(transactionRequest);
//        if (isExternalAuth)
//            body = AppUtils.removeCardAndRoutingFromRequest(body);
//
//        Response response = AuthenticationTransactionRequest.postRequest(body, endpoint);
//        CommonAutomationUtils.verifyCommonResponseSuccessValidation(response, HttpStatus.SC_OK, Map.of(AppConstants.CUSTOMER + "." + AppConstants.NAME + "[0]." + AppConstants.LOCALE, ""));
//    }
//
//    @Test(groups = {"MC:Authentication", "internal_auth", "external_auth", "Abdul Rehman", "SECTION:Customer Name Locale Validation", "SC:Customer Name Locale"}, description = "Validates transaction authentication with a null customer locale value", priority = 62)
//    public void transactionAuthenticationNullCustomerNameLocaleCase() {
//
//        String endpoint = AppConstants.INTERNAL_END_POINT;
//        TransactionRequest transactionRequest = AppUtils.transactionRequestInstance();
//        TransactionRequest.Customer customer = transactionRequest.getCustomer();
//        List<TransactionRequest.Customer.Name> nameList = customer.getName();
//        nameList.get(0).setLocale(null);
//
//        if (isExternalAuth){
//            endpoint = "";
//            AppUtils.updateSourceAndMerchantIdForExternal(transactionRequest);
//        }
//
//        String body = CommonAutomationUtils.stringToJson(transactionRequest);
//        if (isExternalAuth)
//            body = AppUtils.removeCardAndRoutingFromRequest(body);
//
//        Response response = AuthenticationTransactionRequest.postRequest(body, endpoint);
//        CommonAutomationUtils.verifyCommonResponseSuccessValidation(response, HttpStatus.SC_OK);
//        CommonAutomationUtils.verifyNonAvailableKey(response, List.of(AppConstants.CUSTOMER + "." + AppConstants.NAME + "." + AppConstants.LOCALE));
//    }
//
//    @Test(groups = {"MC:Authentication", "internal_auth", "external_auth", "Abdul Rehman", "SECTION:Customer Name Locale Validation", "SC:Customer Name Locale"}, description = "Validates transaction authentication with a valid customer locale value", priority = 63)
//    public void transactionAuthenticationValidCustomerNameLocaleCase() {
//
//        String endpoint = AppConstants.INTERNAL_END_POINT;
//        TransactionRequest transactionRequest = AppUtils.transactionRequestInstance();
//        TransactionRequest.Customer customer = transactionRequest.getCustomer();
//        List<TransactionRequest.Customer.Name> nameList = customer.getName();
//        nameList.get(0).setLocale("en");
//
//        if (isExternalAuth){
//            endpoint = "";
//            AppUtils.updateSourceAndMerchantIdForExternal(transactionRequest);
//        }
//
//        String body = CommonAutomationUtils.stringToJson(transactionRequest);
//        if (isExternalAuth)
//            body = AppUtils.removeCardAndRoutingFromRequest(body);
//
//        Response response = AuthenticationTransactionRequest.postRequest(body, endpoint);
//        CommonAutomationUtils.verifyCommonResponseSuccessValidation(response, HttpStatus.SC_OK, Map.of(AppConstants.CUSTOMER + "." + AppConstants.NAME + "[0]." + AppConstants.LOCALE, "en"));
//    }
//
//    @Test(groups = {"MC:Authentication", "internal_auth", "external_auth", "Abdul Rehman", "SECTION:Customer Name Locale Validation", "SC:Customer Name Locale"}, description = "Validates transaction authentication with an invalid customer locale value", priority = 64)
//    public void transactionAuthenticationInvalidCustomerNameLocaleCase() {
//
//        String endpoint = AppConstants.INTERNAL_END_POINT;
//        TransactionRequest transactionRequest = AppUtils.transactionRequestInstance();
//        TransactionRequest.Customer customer = transactionRequest.getCustomer();
//        List<TransactionRequest.Customer.Name> nameList = customer.getName();
//        nameList.get(0).setLocale("end");
//
//        if (isExternalAuth){
//            endpoint = "";
//            AppUtils.updateSourceAndMerchantIdForExternal(transactionRequest);
//        }
//
//        String body = CommonAutomationUtils.stringToJson(transactionRequest);
//        if (isExternalAuth)
//            body = AppUtils.removeCardAndRoutingFromRequest(body);
//
//        Response response = AuthenticationTransactionRequest.postRequest(body, endpoint);
//        CommonAutomationUtils.verifyCommonResponseSuccessValidation(response, HttpStatus.SC_OK, Map.of(AppConstants.CUSTOMER + "." + AppConstants.NAME + "[0]." + AppConstants.LOCALE, "end"));
//    }
//
//    @Test(groups = {"MC:Authentication", "internal_auth", "external_auth", "Abdul Rehman", "SECTION:Customer Name Locale Validation", "SC:Customer Name Locale"}, description = "Validates transaction authentication with a numeric customer locale value", priority = 65)
//    public void transactionAuthenticationNumericCustomerNameLocaleCase() {
//
//        String endpoint = AppConstants.INTERNAL_END_POINT;
//        TransactionRequest transactionRequest = AppUtils.transactionRequestInstance();
//        TransactionRequest.Customer customer = transactionRequest.getCustomer();
//        List<TransactionRequest.Customer.Name> nameList = customer.getName();
//        nameList.get(0).setLocale("123");
//
//        if (isExternalAuth){
//            endpoint = "";
//            AppUtils.updateSourceAndMerchantIdForExternal(transactionRequest);
//        }
//
//        String body = CommonAutomationUtils.stringToJson(transactionRequest);
//        String updatedBody = CommonAutomationUtils.modifyJson(body, "MODIFY", "customer.name.locale", 123);
//        if (isExternalAuth)
//            updatedBody = AppUtils.removeCardAndRoutingFromRequest(updatedBody);
//
//        Response response = AuthenticationTransactionRequest.postRequest(updatedBody, endpoint);
//        CommonAutomationUtils.verifyCommonResponseSuccessValidation(response, HttpStatus.SC_OK, Map.of(AppConstants.CUSTOMER + "." + AppConstants.NAME + "[0]." + AppConstants.LOCALE, "123"));
//    }
//
//    @Test(groups = {"MC:Authentication", "internal_auth", "external_auth", "Abdul Rehman", "SECTION:Customer Name On Card Validation", "SC:Customer Name On Card"}, description = "Validates transaction authentication with an empty customer name on card value", priority = 66)
//    public void transactionAuthenticationEmptyCustomerNameOnCardCase() {
//        String endpoint = AppConstants.INTERNAL_END_POINT;
//        TransactionRequest transactionRequest = AppUtils.transactionRequestInstance();
//        transactionRequest.getCustomer().setNameOnCard("");
//
//        if (isExternalAuth) {
//            endpoint = "";
//            AppUtils.updateSourceAndMerchantIdForExternal(transactionRequest);
//        }
//
//        String body = CommonAutomationUtils.stringToJson(transactionRequest);
//        if (isExternalAuth)
//            body = AppUtils.removeCardAndRoutingFromRequest(body);
//
//        Response response = AuthenticationTransactionRequest.postRequest(body, endpoint);
//        CommonAutomationUtils.verifyCommonResponseSuccessValidation(response, HttpStatus.SC_OK, Map.of(AppConstants.CUSTOMER + "." + AppConstants.NAME_ON_CARD, ""));
//    }
//
//    @Test(groups = {"MC:Authentication", "internal_auth", "external_auth", "Abdul Rehman", "SECTION:Customer Name On Card Validation", "SC:Customer Name On Card"}, description = "Validates transaction authentication with a null customer name on card value", priority = 67)
//    public void transactionAuthenticationNullCustomerNameOnCardCase() {
//        String endpoint = AppConstants.INTERNAL_END_POINT;
//        TransactionRequest transactionRequest = AppUtils.transactionRequestInstance();
//        transactionRequest.getCustomer().setNameOnCard(null);
//
//        if (isExternalAuth) {
//            endpoint = "";
//            AppUtils.updateSourceAndMerchantIdForExternal(transactionRequest);
//        }
//
//        String body = CommonAutomationUtils.stringToJson(transactionRequest);
//        if (isExternalAuth)
//            body = AppUtils.removeCardAndRoutingFromRequest(body);
//
//        Response response = AuthenticationTransactionRequest.postRequest(body, endpoint);
//        CommonAutomationUtils.verifyCommonResponseSuccessValidation(response, HttpStatus.SC_OK);
//        CommonAutomationUtils.verifyNonAvailableKey(response, List.of(AppConstants.CUSTOMER + "." + AppConstants.NAME_ON_CARD));
//    }
//
//    @Test(groups = {"MC:Authentication", "internal_auth", "external_auth", "Abdul Rehman", "SECTION:Customer Name On Card Validation", "SC:Customer Name On Card"}, description = "Validates transaction authentication with a valid customer name on card value", priority = 68)
//    public void transactionAuthenticationValidCustomerNameOnCardCase() {
//        String endpoint = AppConstants.INTERNAL_END_POINT;
//        TransactionRequest transactionRequest = AppUtils.transactionRequestInstance();
//        transactionRequest.getCustomer().setNameOnCard("Test Name");
//
//        if (isExternalAuth) {
//            endpoint = "";
//            AppUtils.updateSourceAndMerchantIdForExternal(transactionRequest);
//        }
//
//        String body = CommonAutomationUtils.stringToJson(transactionRequest);
//        if (isExternalAuth)
//            body = AppUtils.removeCardAndRoutingFromRequest(body);
//
//        Response response = AuthenticationTransactionRequest.postRequest(body, endpoint);
//        CommonAutomationUtils.verifyCommonResponseSuccessValidation(response, HttpStatus.SC_OK, Map.of(AppConstants.CUSTOMER + "." + AppConstants.NAME_ON_CARD, "Test Name"));
//    }
//
//    @Test(groups = {"MC:Authentication", "internal_auth", "external_auth", "Abdul Rehman", "SECTION:Customer Name On Card Validation", "SC:Customer Name On Card"}, description = "Validates transaction authentication with a numeric customer name on card value", priority = 69)
//    public void transactionAuthenticationNumericCustomerNameOnCardCase() {
//        String endpoint = AppConstants.INTERNAL_END_POINT;
//        TransactionRequest transactionRequest = AppUtils.transactionRequestInstance();
//        transactionRequest.getCustomer().setNameOnCard("123");
//
//        if (isExternalAuth) {
//            endpoint = "";
//            AppUtils.updateSourceAndMerchantIdForExternal(transactionRequest);
//        }
//
//        String body = CommonAutomationUtils.stringToJson(transactionRequest);
//        String updatedBody = CommonAutomationUtils.modifyJson(body, "MODIFY", "customer.name_on_card", 123);
//        if (isExternalAuth)
//            updatedBody = AppUtils.removeCardAndRoutingFromRequest(updatedBody);
//
//        Response response = AuthenticationTransactionRequest.postRequest(updatedBody, endpoint);
//        CommonAutomationUtils.verifyCommonResponseSuccessValidation(response, HttpStatus.SC_OK, Map.of(AppConstants.CUSTOMER + "." + AppConstants.NAME_ON_CARD, "123"));
//    }
//
//    @Test(groups = {"MC:Authentication", "internal_auth", "external_auth", "Abdul Rehman", "SECTION:Customer Email Validation", "SC:Customer Email"}, description = "Validates transaction authentication with a valid customer email", priority = 70)
//    public void transactionAuthenticationValidCustomerEmailCase() {
//        String endpoint = AppConstants.INTERNAL_END_POINT;
//        TransactionRequest transactionRequest = AppUtils.transactionRequestInstance();
//        transactionRequest.getCustomer().setEmail("test@test.com");
//
//        if (isExternalAuth) {
//            endpoint = "";
//            AppUtils.updateSourceAndMerchantIdForExternal(transactionRequest);
//        }
//
//        String body = CommonAutomationUtils.stringToJson(transactionRequest);
//        if (isExternalAuth)
//            body = AppUtils.removeCardAndRoutingFromRequest(body);
//
//        Response response = AuthenticationTransactionRequest.postRequest(body, endpoint);
//        CommonAutomationUtils.verifyCommonResponseSuccessValidation(response, HttpStatus.SC_OK, Map.of(AppConstants.CUSTOMER + "." + AppConstants.EMAIL, "test@test.com"));
//    }
//
//    @Test(groups = {"MC:Authentication", "internal_auth", "external_auth", "Abdul Rehman", "SECTION:Customer Email Validation", "SC:Customer Email"}, description = "Validates transaction authentication fails with an invalid customer email (too short)", priority = 71)
//    public void transactionAuthenticationInvalidCustomerEmailCase() {
//        String endpoint = AppConstants.INTERNAL_END_POINT;
//        TransactionRequest transactionRequest = AppUtils.transactionRequestInstance();
//        transactionRequest.getCustomer().setEmail("a@b.c");
//
//        if (isExternalAuth) {
//            endpoint = "";
//            AppUtils.updateSourceAndMerchantIdForExternal(transactionRequest);
//        }
//
//        String body = CommonAutomationUtils.stringToJson(transactionRequest);
//        if (isExternalAuth)
//            body = AppUtils.removeCardAndRoutingFromRequest(body);
//
//        Response response = AuthenticationTransactionRequest.postRequest(body, endpoint);
//        String textResponse = response.getBody().asString();
//        JsonNode jsonResponse = CommonAutomationUtils.convertToJson(textResponse);
//
//        CommonAutomationUtils.verifyCommonResponseFailedValidation(jsonResponse, response.getStatusCode(), HttpStatus.SC_BAD_REQUEST, AppConstants.REQUIRED_INPUT_INVALID_CODE, AppConstants.REQUIRED_INPUT_INVALID_ERROR);
//    }
//
//    @Test(groups = {"MC:Authentication", "internal_auth", "external_auth", "Abdul Rehman", "SECTION:Customer Email Validation", "SC:Customer Email"}, description = "Validates transaction authentication fails with an email exceeding the maximum length", priority = 72)
//    public void transactionAuthenticationMoreThanMaxCustomerEmailCase() {
//        String endpoint = AppConstants.INTERNAL_END_POINT;
//        TransactionRequest transactionRequest = AppUtils.transactionRequestInstance();
//        transactionRequest.getCustomer().setEmail("a.very.long.email.address.with.many.characters.to.reach.the.limit@example.extremely.long.domain.name.with.many.subdomains.subdomains.to.maximize.length.com");
//
//        if (isExternalAuth) {
//            endpoint = "";
//            AppUtils.updateSourceAndMerchantIdForExternal(transactionRequest);
//        }
//
//        String body = CommonAutomationUtils.stringToJson(transactionRequest);
//        if (isExternalAuth)
//            body = AppUtils.removeCardAndRoutingFromRequest(body);
//
//        Response response = AuthenticationTransactionRequest.postRequest(body, endpoint);
//        String textResponse = response.getBody().asString();
//        JsonNode jsonResponse = CommonAutomationUtils.convertToJson(textResponse);
//
//        CommonAutomationUtils.verifyCommonResponseFailedValidation(jsonResponse, response.getStatusCode(), HttpStatus.SC_BAD_REQUEST, AppConstants.REQUIRED_INPUT_INVALID_CODE, AppConstants.REQUIRED_INPUT_INVALID_ERROR);
//    }
//
//    @Test(groups = {"MC:Authentication", "internal_auth", "external_auth", "Abdul Rehman", "SECTION:Customer Email Validation", "SC:Customer Email"}, description = "Validates transaction authentication fails with an empty customer email", priority = 73)
//    public void transactionAuthenticationEmptyCustomerEmailCase() {
//        String endpoint = AppConstants.INTERNAL_END_POINT;
//        TransactionRequest transactionRequest = AppUtils.transactionRequestInstance();
//        transactionRequest.getCustomer().setEmail("");
//
//        if (isExternalAuth) {
//            endpoint = "";
//            AppUtils.updateSourceAndMerchantIdForExternal(transactionRequest);
//        }
//
//        String body = CommonAutomationUtils.stringToJson(transactionRequest);
//        if (isExternalAuth)
//            body = AppUtils.removeCardAndRoutingFromRequest(body);
//
//        Response response = AuthenticationTransactionRequest.postRequest(body, endpoint);
//        String textResponse = response.getBody().asString();
//        JsonNode jsonResponse = CommonAutomationUtils.convertToJson(textResponse);
//
//        CommonAutomationUtils.verifyCommonResponseFailedValidation(jsonResponse, response.getStatusCode(), HttpStatus.SC_BAD_REQUEST, AppConstants.REQUIRED_INPUT_INVALID_CODE, AppConstants.REQUIRED_INPUT_INVALID_ERROR);
//    }
//
//    @Test(groups = {"MC:Authentication", "internal_auth", "external_auth", "Abdul Rehman", "SECTION:Customer Email Validation", "SC:Customer Email"}, description = "Validates transaction authentication fails with an invalid customer email containing only string characters", priority = 74)
//    public void transactionAuthenticationStringOnlyCustomerEmailCase() {
//        String endpoint = AppConstants.INTERNAL_END_POINT;
//        TransactionRequest transactionRequest = AppUtils.transactionRequestInstance();
//        transactionRequest.getCustomer().setEmail("abcabc");
//
//        if (isExternalAuth) {
//            endpoint = "";
//            AppUtils.updateSourceAndMerchantIdForExternal(transactionRequest);
//        }
//
//        String body = CommonAutomationUtils.stringToJson(transactionRequest);
//        if (isExternalAuth)
//            body = AppUtils.removeCardAndRoutingFromRequest(body);
//
//        Response response = AuthenticationTransactionRequest.postRequest(body, endpoint);
//        String textResponse = response.getBody().asString();
//        JsonNode jsonResponse = CommonAutomationUtils.convertToJson(textResponse);
//
//        CommonAutomationUtils.verifyCommonResponseFailedValidation(jsonResponse, response.getStatusCode(), HttpStatus.SC_BAD_REQUEST, AppConstants.REQUIRED_INPUT_INVALID_CODE, AppConstants.REQUIRED_INPUT_INVALID_ERROR);
//    }
//
//    @Test(groups = {"MC:Authentication", "internal_auth", "external_auth", "Abdul Rehman", "SECTION:Customer Email Validation", "SC:Customer Email"}, description = "Validates transaction authentication with a null customer email value", priority = 75)
//    public void transactionAuthenticationNullCustomerEmailCase() {
//        String endpoint = AppConstants.INTERNAL_END_POINT;
//        TransactionRequest transactionRequest = AppUtils.transactionRequestInstance();
//        transactionRequest.getCustomer().setEmail(null);
//
//        if (isExternalAuth) {
//            endpoint = "";
//            AppUtils.updateSourceAndMerchantIdForExternal(transactionRequest);
//        }
//
//        String body = CommonAutomationUtils.stringToJson(transactionRequest);
//        if (isExternalAuth)
//            body = AppUtils.removeCardAndRoutingFromRequest(body);
//
//        Response response = AuthenticationTransactionRequest.postRequest(body, endpoint);
//        CommonAutomationUtils.verifyCommonResponseSuccessValidation(response, HttpStatus.SC_OK);
//        CommonAutomationUtils.verifyNonAvailableKey(response, List.of(AppConstants.CUSTOMER + "." + AppConstants.EMAIL));
//    }
//
//    @Test(groups = {"MC:Authentication", "internal_auth", "external_auth", "Abdul Rehman", "SECTION:Customer Phone Validation", "SC:Customer Phone"}, description = "Validates transaction authentication fails with a null customer phone value", priority = 76)
//    public void transactionAuthenticationNullCustomerPhoneCase() {
//        String endpoint = AppConstants.INTERNAL_END_POINT;
//        TransactionRequest transactionRequest = AppUtils.transactionRequestInstance();
//        transactionRequest.getCustomer().setPhone(null);
//
//        if (isExternalAuth) {
//            endpoint = "";
//            AppUtils.updateSourceAndMerchantIdForExternal(transactionRequest);
//        }
//
//        String body = CommonAutomationUtils.stringToJson(transactionRequest);
//        if (isExternalAuth)
//            body = AppUtils.removeCardAndRoutingFromRequest(body);
//
//        Response response = AuthenticationTransactionRequest.postRequest(body, endpoint);
//        String textResponse = response.getBody().asString();
//        JsonNode jsonResponse = CommonAutomationUtils.convertToJson(textResponse);
//
//        CommonAutomationUtils.verifyCommonResponseSuccessValidation(response, HttpStatus.SC_OK, Map.of(AppConstants.CUSTOMER + "." + AppConstants.EMAIL, "test@test.com"));
//    }
//
//    @Test(groups = {"MC:Authentication", "internal_auth", "external_auth", "Abdul Rehman", "SECTION:Customer Phone Country Code Validation", "SC:Customer Phone Country Code"}, description = "Validates transaction authentication with a valid customer phone number and country code", priority = 77)
//    public void transactionAuthenticationValidCustomerPhoneCountryCodeCase() {
//        String endpoint = AppConstants.INTERNAL_END_POINT;
//        TransactionRequest transactionRequest = AppUtils.transactionRequestInstance();
//        TransactionRequest.Customer customer = transactionRequest.getCustomer();
//        TransactionRequest.Customer.Phone phone = customer.getPhone();
//        phone.setCountryCode("965");
//        phone.setNumber("50000000");
//
//        if (isExternalAuth) {
//            endpoint = "";
//            AppUtils.updateSourceAndMerchantIdForExternal(transactionRequest);
//        }
//
//        String body = CommonAutomationUtils.stringToJson(transactionRequest);
//        if (isExternalAuth)
//            body = AppUtils.removeCardAndRoutingFromRequest(body);
//
//        Response response = AuthenticationTransactionRequest.postRequest(body, endpoint);
//
//        CommonAutomationUtils.verifyCommonResponseSuccessValidation(response, HttpStatus.SC_OK, Map.of(AppConstants.CUSTOMER + "." + AppConstants.PHONE + "." + AppConstants.COUNTRY_CODE, "965",
//                AppConstants.CUSTOMER + "." + AppConstants.PHONE + "." + AppConstants.NUMBER, "50000000"));
//    }
//
//    @Test(groups = {"MC:Authentication", "internal_auth", "external_auth", "Abdul Rehman", "SECTION:Customer Phone Country Code Validation", "SC:Customer Phone Country Code"}, description = "Validates transaction authentication fails with an empty customer phone country code", priority = 78)
//    public void transactionAuthenticationEmptyCustomerPhoneCountryCodeCase() {
//        String endpoint = AppConstants.INTERNAL_END_POINT;
//        TransactionRequest transactionRequest = AppUtils.transactionRequestInstance();
//        TransactionRequest.Customer customer = transactionRequest.getCustomer();
//        TransactionRequest.Customer.Phone phone = customer.getPhone();
//        phone.setCountryCode("");
//        phone.setNumber("50000000");
//
//        if (isExternalAuth) {
//            endpoint = "";
//            AppUtils.updateSourceAndMerchantIdForExternal(transactionRequest);
//        }
//
//        String body = CommonAutomationUtils.stringToJson(transactionRequest);
//        if (isExternalAuth)
//            body = AppUtils.removeCardAndRoutingFromRequest(body);
//
//        Response response = AuthenticationTransactionRequest.postRequest(body, endpoint);
//        String textResponse = response.getBody().asString();
//        JsonNode jsonResponse = CommonAutomationUtils.convertToJson(textResponse);
//
//        CommonAutomationUtils.verifyCommonResponseFailedValidation(jsonResponse, response.getStatusCode(), HttpStatus.SC_BAD_REQUEST, AppConstants.REQUIRED_INPUT_INVALID_CODE, AppConstants.REQUIRED_INPUT_INVALID_ERROR);
//    }
//
//    @Test(groups = {"MC:Authentication", "internal_auth", "external_auth", "Abdul Rehman", "SECTION:Customer Phone Country Code Validation", "SC:Customer Phone Country Code"}, description = "Validates transaction authentication fails with a customer phone country code exceeding the maximum length", priority = 79)
//    public void transactionAuthenticationMoreThanMaxCustomerPhoneCountryCodeCase() {
//        String endpoint = AppConstants.INTERNAL_END_POINT;
//        TransactionRequest transactionRequest = AppUtils.transactionRequestInstance();
//        TransactionRequest.Customer customer = transactionRequest.getCustomer();
//        TransactionRequest.Customer.Phone phone = customer.getPhone();
//        phone.setCountryCode("123456");
//        phone.setNumber("50000000");
//
//        if (isExternalAuth) {
//            endpoint = "";
//            AppUtils.updateSourceAndMerchantIdForExternal(transactionRequest);
//        }
//
//        String body = CommonAutomationUtils.stringToJson(transactionRequest);
//        if (isExternalAuth)
//            body = AppUtils.removeCardAndRoutingFromRequest(body);
//
//        Response response = AuthenticationTransactionRequest.postRequest(body, endpoint);
//        String textResponse = response.getBody().asString();
//        JsonNode jsonResponse = CommonAutomationUtils.convertToJson(textResponse);
//
//        CommonAutomationUtils.verifyCommonResponseFailedValidation(jsonResponse, response.getStatusCode(), HttpStatus.SC_BAD_REQUEST, AppConstants.REQUIRED_INPUT_INVALID_CODE, AppConstants.REQUIRED_INPUT_INVALID_ERROR);
//    }
//
//    @Test(groups = {"MC:Authentication", "internal_auth", "external_auth", "Abdul Rehman", "SECTION:Customer Phone Country Code Validation", "SC:Customer Phone Country Code"}, description = "Validates transaction authentication with a null customer phone country code value", priority = 80)
//    public void transactionAuthenticationNullCustomerPhoneCountryCodeCase() {
//        String endpoint = AppConstants.INTERNAL_END_POINT;
//        TransactionRequest transactionRequest = AppUtils.transactionRequestInstance();
//        TransactionRequest.Customer customer = transactionRequest.getCustomer();
//        TransactionRequest.Customer.Phone phone = customer.getPhone();
//        phone.setCountryCode(null);
//        phone.setNumber("50000000");
//
//        if (isExternalAuth) {
//            endpoint = "";
//            AppUtils.updateSourceAndMerchantIdForExternal(transactionRequest);
//        }
//
//        String body = CommonAutomationUtils.stringToJson(transactionRequest);
//        if (isExternalAuth)
//            body = AppUtils.removeCardAndRoutingFromRequest(body);
//
//        Response response = AuthenticationTransactionRequest.postRequest(body, endpoint);
//
//        CommonAutomationUtils.verifyCommonResponseSuccessValidation(response, HttpStatus.SC_OK);
//        CommonAutomationUtils.verifyNonAvailableKey(response, List.of(AppConstants.CUSTOMER + "." + AppConstants.PHONE + "." + AppConstants.COUNTRY_CODE));
//    }
//
//    @Test(groups = {"MC:Authentication", "internal_auth", "external_auth", "Abdul Rehman", "SECTION:Customer Phone Country Code Validation", "SC:Customer Phone Country Code"}, description = "Validates transaction authentication with a numeric customer phone country code value", priority = 81)
//    public void transactionAuthenticationNumericCustomerPhoneCountryCodeCase() {
//        String endpoint = AppConstants.INTERNAL_END_POINT;
//        TransactionRequest transactionRequest = AppUtils.transactionRequestInstance();
//        TransactionRequest.Customer customer = transactionRequest.getCustomer();
//        TransactionRequest.Customer.Phone phone = customer.getPhone();
//        phone.setCountryCode("123");
//        phone.setNumber("50000000");
//
//        if (isExternalAuth) {
//            endpoint = "";
//            AppUtils.updateSourceAndMerchantIdForExternal(transactionRequest);
//        }
//
//        String body = CommonAutomationUtils.stringToJson(transactionRequest);
//        String updatedBody = CommonAutomationUtils.modifyJson(body, "MODIFY", "customer.phone.country_code", 123);
//        if (isExternalAuth)
//            updatedBody = AppUtils.removeCardAndRoutingFromRequest(updatedBody);
//
//        Response response = AuthenticationTransactionRequest.postRequest(updatedBody, endpoint);
//
//        CommonAutomationUtils.verifyCommonResponseSuccessValidation(response, HttpStatus.SC_OK, Map.of(AppConstants.CUSTOMER + "." + AppConstants.PHONE + "." + AppConstants.COUNTRY_CODE, "123"));
//    }
//
//    @Test(groups = {"MC:Authentication", "internal_auth", "external_auth", "Abdul Rehman", "SECTION:Customer Phone Number Validation", "SC:Customer Phone Number"}, description = "Validates transaction authentication fails with an empty customer phone number value", priority = 82)
//    public void transactionAuthenticationEmptyCustomerPhoneNumberCase() {
//        String endpoint = AppConstants.INTERNAL_END_POINT;
//        TransactionRequest transactionRequest = AppUtils.transactionRequestInstance();
//        TransactionRequest.Customer customer = transactionRequest.getCustomer();
//        TransactionRequest.Customer.Phone phone = customer.getPhone();
//        phone.setCountryCode("965");
//        phone.setNumber("");
//
//        if (isExternalAuth) {
//            endpoint = "";
//            AppUtils.updateSourceAndMerchantIdForExternal(transactionRequest);
//        }
//
//        String body = CommonAutomationUtils.stringToJson(transactionRequest);
//        if (isExternalAuth)
//            body = AppUtils.removeCardAndRoutingFromRequest(body);
//
//        Response response = AuthenticationTransactionRequest.postRequest(body, endpoint);
//        String textResponse = response.getBody().asString();
//        JsonNode jsonResponse = CommonAutomationUtils.convertToJson(textResponse);
//
//        CommonAutomationUtils.verifyCommonResponseFailedValidation(jsonResponse, response.getStatusCode(), HttpStatus.SC_BAD_REQUEST, AppConstants.REQUIRED_INPUT_INVALID_CODE, AppConstants.REQUIRED_INPUT_INVALID_ERROR);
//    }
//
//    @Test(groups = {"MC:Authentication", "internal_auth", "external_auth", "Abdul Rehman", "SECTION:Customer Phone Number Validation", "SC:Customer Phone Number"}, description = "Validates transaction authentication fails with a customer phone number exceeding the maximum length", priority = 83)
//    public void transactionAuthenticationMoreThanMaxCustomerPhoneNumberCase() {
//        String endpoint = AppConstants.INTERNAL_END_POINT;
//        TransactionRequest transactionRequest = AppUtils.transactionRequestInstance();
//        TransactionRequest.Customer customer = transactionRequest.getCustomer();
//        TransactionRequest.Customer.Phone phone = customer.getPhone();
//        phone.setCountryCode("965");
//        phone.setNumber("12345678912345678912345");
//
//        if (isExternalAuth) {
//            endpoint = "";
//            AppUtils.updateSourceAndMerchantIdForExternal(transactionRequest);
//        }
//
//        String body = CommonAutomationUtils.stringToJson(transactionRequest);
//        if (isExternalAuth)
//            body = AppUtils.removeCardAndRoutingFromRequest(body);
//
//        Response response = AuthenticationTransactionRequest.postRequest(body, endpoint);
//        String textResponse = response.getBody().asString();
//        JsonNode jsonResponse = CommonAutomationUtils.convertToJson(textResponse);
//
//        CommonAutomationUtils.verifyCommonResponseFailedValidation(jsonResponse, response.getStatusCode(), HttpStatus.SC_BAD_REQUEST, AppConstants.REQUIRED_INPUT_INVALID_CODE, AppConstants.REQUIRED_INPUT_INVALID_ERROR);
//    }
//
//    @Test(groups = {"MC:Authentication", "internal_auth", "external_auth", "Abdul Rehman", "SECTION:Customer Phone Number Validation", "SC:Customer Phone Number"}, description = "Validates transaction authentication with a null customer phone number value", priority = 84)
//    public void transactionAuthenticationNullCustomerPhoneNumberCase() {
//        String endpoint = AppConstants.INTERNAL_END_POINT;
//        TransactionRequest transactionRequest = AppUtils.transactionRequestInstance();
//        TransactionRequest.Customer customer = transactionRequest.getCustomer();
//        TransactionRequest.Customer.Phone phone = customer.getPhone();
//        phone.setCountryCode("123");
//        phone.setNumber(null);
//
//        if (isExternalAuth) {
//            endpoint = "";
//            AppUtils.updateSourceAndMerchantIdForExternal(transactionRequest);
//        }
//
//        String body = CommonAutomationUtils.stringToJson(transactionRequest);
//        if (isExternalAuth)
//            body = AppUtils.removeCardAndRoutingFromRequest(body);
//
//        Response response = AuthenticationTransactionRequest.postRequest(body, endpoint);
//
//        CommonAutomationUtils.verifyCommonResponseSuccessValidation(response, HttpStatus.SC_OK);
//        CommonAutomationUtils.verifyNonAvailableKey(response, List.of(AppConstants.CUSTOMER + "." + AppConstants.PHONE + "." + AppConstants.NUMBER));
//    }
//
//    @Test(groups = {"MC:Authentication", "internal_auth", "external_auth", "Abdul Rehman", "SECTION:Customer Phone Number Validation", "SC:Customer Phone Number"}, description = "Validates transaction authentication with a customer phone number without country code", priority = 85)
//    public void transactionAuthenticationCustomerWithPhoneNumberWithoutCountryCodeCase() {
//        String endpoint = AppConstants.INTERNAL_END_POINT;
//        TransactionRequest transactionRequest = AppUtils.transactionRequestInstance();
//
//        if (isExternalAuth) {
//            endpoint = "";
//            AppUtils.updateSourceAndMerchantIdForExternal(transactionRequest);
//        }
//
//        String body = CommonAutomationUtils.stringToJson(transactionRequest);
//        String updatedBody = CommonAutomationUtils.modifyJson(body, "DELETE", "customer.phone.country_code", null);
//        if (isExternalAuth)
//            updatedBody = AppUtils.removeCardAndRoutingFromRequest(updatedBody);
//
//        Response response = AuthenticationTransactionRequest.postRequest(updatedBody, endpoint);
//
//        CommonAutomationUtils.verifyCommonResponseSuccessValidation(response, HttpStatus.SC_OK, Map.of(AppConstants.CUSTOMER + "." + AppConstants.PHONE + "." + AppConstants.NUMBER, "50000000"));
//    }
//
//    @Test(groups = {"MC:Authentication", "internal_auth", "external_auth", "Abdul Rehman", "SECTION:Customer Phone Number Validation", "SC:Customer Phone Number"}, description = "Validates transaction authentication with a customer country code without phone number", priority = 86)
//    public void transactionAuthenticationCustomerWithoutPhoneNumberWithCountryCodeCase() {
//        String endpoint = AppConstants.INTERNAL_END_POINT;
//        TransactionRequest transactionRequest = AppUtils.transactionRequestInstance();
//
//        if (isExternalAuth) {
//            endpoint = "";
//            AppUtils.updateSourceAndMerchantIdForExternal(transactionRequest);
//        }
//
//        String body = CommonAutomationUtils.stringToJson(transactionRequest);
//        String updatedBody = CommonAutomationUtils.modifyJson(body, "DELETE", "customer.phone.number", null);
//        if (isExternalAuth)
//            updatedBody = AppUtils.removeCardAndRoutingFromRequest(updatedBody);
//
//        Response response = AuthenticationTransactionRequest.postRequest(updatedBody, endpoint);
//
//        CommonAutomationUtils.verifyCommonResponseSuccessValidation(response, HttpStatus.SC_OK, Map.of(AppConstants.CUSTOMER + "." + AppConstants.PHONE + "." + AppConstants.COUNTRY_CODE, "965"));
//    }
//
//    @Test(groups = {"MC:Authentication", "internal_auth", "external_auth", "Abdul Rehman", "SECTION:Customer Phone Number Validation", "SC:Customer Phone Number"}, description = "Validates transaction authentication with a customer email but without phone number", priority = 87)
//    public void transactionAuthenticationWithCustomerEmailWithoutPhoneCase() {
//        String endpoint = AppConstants.INTERNAL_END_POINT;
//        TransactionRequest transactionRequest = AppUtils.transactionRequestInstance();
//
//        if (isExternalAuth) {
//            endpoint = "";
//            AppUtils.updateSourceAndMerchantIdForExternal(transactionRequest);
//        }
//
//        String body = CommonAutomationUtils.stringToJson(transactionRequest);
//        String updatedBody = CommonAutomationUtils.modifyJson(body, "DELETE", "customer.phone", null);
//        if (isExternalAuth)
//            updatedBody = AppUtils.removeCardAndRoutingFromRequest(updatedBody);
//
//        Response response = AuthenticationTransactionRequest.postRequest(updatedBody, endpoint);
//
//        CommonAutomationUtils.verifyCommonResponseSuccessValidation(response, HttpStatus.SC_OK, Map.of(AppConstants.CUSTOMER + "." + AppConstants.EMAIL, "test@test.com"));
//    }
//
//    @Test(groups = {"MC:Authentication", "internal_auth", "external_auth", "Abdul Rehman", "SECTION:Customer Phone Number Validation", "SC:Customer Phone Number"}, description = "Validates transaction authentication with a customer phone number but without email", priority = 88)
//    public void transactionAuthenticationWithoutCustomerEmailWithPhoneCase() {
//        String endpoint = AppConstants.INTERNAL_END_POINT;
//        TransactionRequest transactionRequest = AppUtils.transactionRequestInstance();
//
//        if (isExternalAuth) {
//            endpoint = "";
//            AppUtils.updateSourceAndMerchantIdForExternal(transactionRequest);
//        }
//
//        String body = CommonAutomationUtils.stringToJson(transactionRequest);
//        String updatedBody = CommonAutomationUtils.modifyJson(body, "DELETE", "customer.email", null);
//        if (isExternalAuth)
//            updatedBody = AppUtils.removeCardAndRoutingFromRequest(updatedBody);
//
//        Response response = AuthenticationTransactionRequest.postRequest(updatedBody, endpoint);
//
//        CommonAutomationUtils.verifyCommonResponseSuccessValidation(response, HttpStatus.SC_OK, Map.of(AppConstants.CUSTOMER + "." + AppConstants.PHONE + "." + AppConstants.COUNTRY_CODE, "965", AppConstants.CUSTOMER + "." + AppConstants.PHONE + "." + AppConstants.NUMBER, "50000000"));
//    }
//
//    @Test(groups = {"MC:Authentication", "internal_auth", "external_auth", "Abdul Rehman", "SECTION:Customer Phone Number Validation", "SC:Customer Phone Number"}, description = "Validates transaction authentication without both customer email and phone number", priority = 89)
//    public void transactionAuthenticationWithoutCustomerEmailWithOutPhoneCase() {
//        String endpoint = AppConstants.INTERNAL_END_POINT;
//        TransactionRequest transactionRequest = AppUtils.transactionRequestInstance();
//
//        if (isExternalAuth) {
//            endpoint = "";
//            AppUtils.updateSourceAndMerchantIdForExternal(transactionRequest);
//        }
//
//        String body = CommonAutomationUtils.stringToJson(transactionRequest);
//        String updatedBody = CommonAutomationUtils.modifyJson(body, "DELETE", "customer.email", null);
//        updatedBody = CommonAutomationUtils.modifyJson(updatedBody, "DELETE", "customer.phone", null);
//        if (isExternalAuth)
//            updatedBody = AppUtils.removeCardAndRoutingFromRequest(updatedBody);
//
//        Response response = AuthenticationTransactionRequest.postRequest(updatedBody, endpoint);
//        String textResponse = response.getBody().asString();
//        JsonNode jsonResponse = CommonAutomationUtils.convertToJson(textResponse);
//
//        CommonAutomationUtils.verifyCommonResponseFailedValidation(jsonResponse, response.getStatusCode(), HttpStatus.SC_BAD_REQUEST, AppConstants.REQUIRED_FIELD_INVALID_CODE, AppConstants.REQUIRED_FIELD_INVALID_ERROR);
//    }
//
//    @Test(groups = {"MC:Authentication", "internal_auth", "external_auth", "Abdul Rehman", "SECTION:Device Validation", "SC:Device"}, description = "Validates transaction authentication fails with a null device value", priority = 90)
//    public void transactionAuthenticationNullDeviceCase() {
//        String endpoint = AppConstants.INTERNAL_END_POINT;
//        TransactionRequest transactionRequest = AppUtils.transactionRequestInstance();
//        transactionRequest.setDevice(null);
//
//        if (isExternalAuth) {
//            endpoint = "";
//            AppUtils.updateSourceAndMerchantIdForExternal(transactionRequest);
//        }
//
//        String body = CommonAutomationUtils.stringToJson(transactionRequest);
//        if (isExternalAuth)
//            body = AppUtils.removeCardAndRoutingFromRequest(body);
//
//        Response response = AuthenticationTransactionRequest.postRequest(body, endpoint);
//        String textResponse = response.getBody().asString();
//        JsonNode jsonResponse = CommonAutomationUtils.convertToJson(textResponse);
//
//        CommonAutomationUtils.verifyCommonResponseFailedValidation(jsonResponse, response.getStatusCode(), HttpStatus.SC_BAD_REQUEST, AppConstants.REQUIRED_FIELD_INVALID_CODE, AppConstants.REQUIRED_FIELD_INVALID_ERROR);
//    }
//
//    @Test(groups = {"MC:Authentication", "internal_auth", "external_auth", "Abdul Rehman", "SECTION:Device Ipaddress Validation", "SC:Device Ipaddress"}, description = "Validates transaction authentication with a valid IPv4 device IP address", priority = 91)
//    public void transactionAuthenticationValidDeviceIpAddressV4Case() {
//        String endpoint = AppConstants.INTERNAL_END_POINT;
//        TransactionRequest transactionRequest = AppUtils.transactionRequestInstance();
//
//        if (isExternalAuth) {
//            endpoint = "";
//            AppUtils.updateSourceAndMerchantIdForExternal(transactionRequest);
//        }
//
//        String body = CommonAutomationUtils.stringToJson(transactionRequest);
//        if (isExternalAuth)
//            body = AppUtils.removeCardAndRoutingFromRequest(body);
//
//        Response response = AuthenticationTransactionRequest.postRequest(body, endpoint);
//
//        CommonAutomationUtils.verifyCommonResponseSuccessValidation(response, HttpStatus.SC_OK);
//    }
//
//    @Test(groups = {"MC:Authentication", "internal_auth", "external_auth", "Abdul Rehman", "SECTION:Device Ipaddress Validation", "SC:Device Ipaddress"}, description = "Validates transaction authentication with a valid IPv6 device IP address", priority = 92)
//    public void transactionAuthenticationValidDeviceIpAddressV6Case() {
//        String endpoint = AppConstants.INTERNAL_END_POINT;
//        TransactionRequest transactionRequest = AppUtils.transactionRequestInstance();
//        transactionRequest.getDevice().setIpAddress("2001:0db8:85a3:0000:0000:8a2e:0370:7334");
//
//        if (isExternalAuth) {
//            endpoint = "";
//            AppUtils.updateSourceAndMerchantIdForExternal(transactionRequest);
//        }
//
//        String body = CommonAutomationUtils.stringToJson(transactionRequest);
//        if (isExternalAuth)
//            body = AppUtils.removeCardAndRoutingFromRequest(body);
//
//        Response response = AuthenticationTransactionRequest.postRequest(body, endpoint);
//
//        CommonAutomationUtils.verifyCommonResponseSuccessValidation(response, HttpStatus.SC_OK);
//    }
//
//    @Test(groups = {"MC:Authentication", "internal_auth", "external_auth", "Abdul Rehman", "SECTION:Device Ipaddress Validation", "SC:Device Ipaddress"}, description = "Validates transaction authentication with an empty device IP address", priority = 93)
//    public void transactionAuthenticationEmptyDeviceIpAddressCase() {
//        String endpoint = AppConstants.INTERNAL_END_POINT;
//        TransactionRequest transactionRequest = AppUtils.transactionRequestInstance();
//        transactionRequest.getDevice().setIpAddress("");
//
//        if (isExternalAuth) {
//            endpoint = "";
//            AppUtils.updateSourceAndMerchantIdForExternal(transactionRequest);
//        }
//
//        String body = CommonAutomationUtils.stringToJson(transactionRequest);
//        if (isExternalAuth)
//            body = AppUtils.removeCardAndRoutingFromRequest(body);
//
//        Response response = AuthenticationTransactionRequest.postRequest(body, endpoint);
//
//        CommonAutomationUtils.verifyCommonResponseSuccessValidation(response, HttpStatus.SC_OK);
//    }
//
//    @Test(groups = {"MC:Authentication", "internal_auth", "external_auth", "Abdul Rehman", "SECTION:Device Ipaddress Validation", "SC:Device Ipaddress"}, description = "Validates transaction authentication with an invalid device IP address", priority = 94)
//    public void transactionAuthenticationInvalidDeviceIpAddressCase() {
//        String endpoint = AppConstants.INTERNAL_END_POINT;
//        TransactionRequest transactionRequest = AppUtils.transactionRequestInstance();
//        transactionRequest.getDevice().setIpAddress("192.168.-1.-1");
//
//        if (isExternalAuth) {
//            endpoint = "";
//            AppUtils.updateSourceAndMerchantIdForExternal(transactionRequest);
//        }
//
//        String body = CommonAutomationUtils.stringToJson(transactionRequest);
//        if (isExternalAuth)
//            body = AppUtils.removeCardAndRoutingFromRequest(body);
//
//        Response response = AuthenticationTransactionRequest.postRequest(body, endpoint);
//
//        CommonAutomationUtils.verifyCommonResponseSuccessValidation(response, HttpStatus.SC_OK);
//    }
//
//    @Test(groups = {"MC:Authentication", "internal_auth", "external_auth", "Abdul Rehman", "SECTION:Device Ipaddress Validation", "SC:Device Ipaddress"}, description = "Validates transaction authentication with a null device IP address value", priority = 95)
//    public void transactionAuthenticationNullDeviceIpAddressCase() {
//        String endpoint = AppConstants.INTERNAL_END_POINT;
//        TransactionRequest transactionRequest = AppUtils.transactionRequestInstance();
//        transactionRequest.getDevice().setIpAddress(null);
//
//        if (isExternalAuth) {
//            endpoint = "";
//            AppUtils.updateSourceAndMerchantIdForExternal(transactionRequest);
//        }
//
//        String body = CommonAutomationUtils.stringToJson(transactionRequest);
//        if (isExternalAuth)
//            body = AppUtils.removeCardAndRoutingFromRequest(body);
//
//        Response response = AuthenticationTransactionRequest.postRequest(body, endpoint);
//
//        CommonAutomationUtils.verifyCommonResponseSuccessValidation(response, HttpStatus.SC_OK);
//        CommonAutomationUtils.verifyNonAvailableKey(response, List.of(AppConstants.DEVICE + "." + AppConstants.IP_ADDRESS));
//    }
//
//    @Test(groups = {"MC:Authentication", "internal_auth", "external_auth", "Abdul Rehman", "SECTION:Device Ipaddress Validation", "SC:Device Ipaddress"}, description = "Validates transaction authentication with a device IP address and port", priority = 96)
//    public void transactionAuthenticationNullDeviceIpAddressWithPortCase() {
//        String endpoint = AppConstants.INTERNAL_END_POINT;
//        TransactionRequest transactionRequest = AppUtils.transactionRequestInstance();
//        transactionRequest.getDevice().setIpAddress("37.210.100.199:8080");
//
//        if (isExternalAuth) {
//            endpoint = "";
//            AppUtils.updateSourceAndMerchantIdForExternal(transactionRequest);
//        }
//
//        String body = CommonAutomationUtils.stringToJson(transactionRequest);
//        if (isExternalAuth)
//            body = AppUtils.removeCardAndRoutingFromRequest(body);
//
//        Response response = AuthenticationTransactionRequest.postRequest(body, endpoint);
//
//        CommonAutomationUtils.verifyCommonResponseSuccessValidation(response, HttpStatus.SC_OK);
//    }
//
//    @Test(groups = {"MC:Authentication", "internal_auth", "external_auth", "Abdul Rehman", "SECTION:Device Browser Details Validation", "SC:Device Browser Details"}, description = "Validates transaction authentication fails with a null browser details value", priority = 97)
//    public void transactionAuthenticationNullBrowserDetailsCase() {
//        String endpoint = AppConstants.INTERNAL_END_POINT;
//        TransactionRequest transactionRequest = AppUtils.transactionRequestInstance();
//        transactionRequest.getDevice().setBrowserDetails(null);
//
//        if (isExternalAuth) {
//            endpoint = "";
//            AppUtils.updateSourceAndMerchantIdForExternal(transactionRequest);
//        }
//
//        String body = CommonAutomationUtils.stringToJson(transactionRequest);
//        if (isExternalAuth)
//            body = AppUtils.removeCardAndRoutingFromRequest(body);
//
//        Response response = AuthenticationTransactionRequest.postRequest(body, endpoint);
//        String textResponse = response.getBody().asString();
//        JsonNode jsonResponse = CommonAutomationUtils.convertToJson(textResponse);
//
//        CommonAutomationUtils.verifyCommonResponseFailedValidation(jsonResponse, response.getStatusCode(), HttpStatus.SC_BAD_REQUEST, AppConstants.REQUIRED_FIELD_INVALID_CODE, AppConstants.REQUIRED_FIELD_INVALID_ERROR);
//    }
//
//    @Test(groups = {"MC:Authentication", "internal_auth", "external_auth", "Abdul Rehman", "SECTION:Device Browser Details Screen Heights Validation", "SC:Device Browser Details Screen Heights"}, description = "Validates transaction authentication with an invalid browser screen height value", priority = 98)
//    public void transactionAuthenticationInvalidBrowserDetailsScreenHeightCase() {
//        String endpoint = AppConstants.INTERNAL_END_POINT;
//        TransactionRequest transactionRequest = AppUtils.transactionRequestInstance();
//        transactionRequest.getDevice().getBrowserDetails().setScreenHeight(-111);
//
//        if (isExternalAuth) {
//            endpoint = "";
//            AppUtils.updateSourceAndMerchantIdForExternal(transactionRequest);
//        }
//
//        String body = CommonAutomationUtils.stringToJson(transactionRequest);
//        if (isExternalAuth)
//            body = AppUtils.removeCardAndRoutingFromRequest(body);
//
//        Response response = AuthenticationTransactionRequest.postRequest(body, endpoint);
//
//        CommonAutomationUtils.verifyCommonResponseSuccessValidation(response, HttpStatus.SC_OK);
//    }
//
//    @Test(groups = {"MC:Authentication", "internal_auth", "external_auth", "Abdul Rehman", "SECTION:Device Browser Details Screen Heights Validation", "SC:Device Browser Details Screen Heights"}, description = "Validates transaction authentication with a browser screen height of zero", priority = 99)
//    public void transactionAuthenticationZeroBrowserDetailsScreenHeightCase() {
//        String endpoint = AppConstants.INTERNAL_END_POINT;
//        TransactionRequest transactionRequest = AppUtils.transactionRequestInstance();
//        transactionRequest.getDevice().getBrowserDetails().setScreenHeight(0);
//
//        if (isExternalAuth) {
//            endpoint = "";
//            AppUtils.updateSourceAndMerchantIdForExternal(transactionRequest);
//        }
//
//        String body = CommonAutomationUtils.stringToJson(transactionRequest);
//        if (isExternalAuth)
//            body = AppUtils.removeCardAndRoutingFromRequest(body);
//
//        Response response = AuthenticationTransactionRequest.postRequest(body, endpoint);
//
//        CommonAutomationUtils.verifyCommonResponseSuccessValidation(response, HttpStatus.SC_OK);
//    }
//
//    @Test(groups = {"MC:Authentication", "internal_auth", "external_auth", "Abdul Rehman", "SECTION:Device Browser Details Screen Heights Validation", "SC:Device Browser Details Screen Heights"}, description = "Validates transaction authentication with a null browser screen height value", priority = 100)
//    public void transactionAuthenticationNullBrowserDetailsScreenHeightCase() {
//        String endpoint = AppConstants.INTERNAL_END_POINT;
//        TransactionRequest transactionRequest = AppUtils.transactionRequestInstance();
//        transactionRequest.getDevice().getBrowserDetails().setScreenHeight(null);
//
//        if (isExternalAuth) {
//            endpoint = "";
//            AppUtils.updateSourceAndMerchantIdForExternal(transactionRequest);
//        }
//
//        String body = CommonAutomationUtils.stringToJson(transactionRequest);
//        if (isExternalAuth)
//            body = AppUtils.removeCardAndRoutingFromRequest(body);
//
//        Response response = AuthenticationTransactionRequest.postRequest(body, endpoint);
//
//        CommonAutomationUtils.verifyCommonResponseSuccessValidation(response, HttpStatus.SC_OK);
//        CommonAutomationUtils.verifyNonAvailableKey(response, List.of(AppConstants.DEVICE + "." + AppConstants.BROWSER_DETAIL + "." + AppConstants.SCREEN_HEIGHT));
//    }
//
//    @Test(groups = {"MC:Authentication", "internal_auth", "external_auth", "Abdul Rehman", "SECTION:Device Browser Details Screen Heights Validation", "SC:Device Browser Details Screen Heights"}, description = "Validates transaction authentication with an empty browser details screen height", priority = 101)
//    public void transactionAuthenticationEmptyBrowserDetailsScreenHeightCase() {
//        String endpoint = AppConstants.INTERNAL_END_POINT;
//        TransactionRequest transactionRequest = AppUtils.transactionRequestInstance();
//
//        if (isExternalAuth) {
//            endpoint = "";
//            AppUtils.updateSourceAndMerchantIdForExternal(transactionRequest);
//        }
//
//        String body = CommonAutomationUtils.stringToJson(transactionRequest);
//        String updatedBody = CommonAutomationUtils.modifyJson(body, "MODIFY", "device.browserDetails.screenHeight", "");
//        if (isExternalAuth)
//            updatedBody = AppUtils.removeCardAndRoutingFromRequest(updatedBody);
//
//        Response response = AuthenticationTransactionRequest.postRequest(updatedBody, endpoint);
//
//        CommonAutomationUtils.verifyCommonResponseSuccessValidation(response, HttpStatus.SC_OK);
//    }
//
//    @Test(groups = {"MC:Authentication", "internal_auth", "external_auth", "Abdul Rehman", "SECTION:Device Browser Details Screen Width Validation", "SC:Device Browser Details Screen Width"}, description = "Validates transaction authentication with an invalid browser details screen width", priority = 102)
//    public void transactionAuthenticationInvalidBrowserDetailsScreenWidthCase() {
//        String endpoint = AppConstants.INTERNAL_END_POINT;
//        TransactionRequest transactionRequest = AppUtils.transactionRequestInstance();
//        transactionRequest.getDevice().getBrowserDetails().setScreenWidth(-111);
//
//        if (isExternalAuth) {
//            endpoint = "";
//            AppUtils.updateSourceAndMerchantIdForExternal(transactionRequest);
//        }
//
//        String body = CommonAutomationUtils.stringToJson(transactionRequest);
//        if (isExternalAuth)
//            body = AppUtils.removeCardAndRoutingFromRequest(body);
//
//        Response response = AuthenticationTransactionRequest.postRequest(body, endpoint);
//
//        CommonAutomationUtils.verifyCommonResponseSuccessValidation(response, HttpStatus.SC_OK);
//    }
//
//    @Test(groups = {"MC:Authentication", "internal_auth", "external_auth", "Abdul Rehman", "SECTION:Device Browser Details Screen Width Validation", "SC:Device Browser Details Screen Width"}, description = "Validates transaction authentication with a browser details screen width of zero", priority = 103)
//    public void transactionAuthenticationZeroBrowserDetailsScreenWidthCase() {
//        String endpoint = AppConstants.INTERNAL_END_POINT;
//        TransactionRequest transactionRequest = AppUtils.transactionRequestInstance();
//        transactionRequest.getDevice().getBrowserDetails().setScreenWidth(0);
//
//        if (isExternalAuth) {
//            endpoint = "";
//            AppUtils.updateSourceAndMerchantIdForExternal(transactionRequest);
//        }
//
//        String body = CommonAutomationUtils.stringToJson(transactionRequest);
//        if (isExternalAuth)
//            body = AppUtils.removeCardAndRoutingFromRequest(body);
//
//        Response response = AuthenticationTransactionRequest.postRequest(body, endpoint);
//
//        CommonAutomationUtils.verifyCommonResponseSuccessValidation(response, HttpStatus.SC_OK);
//    }
//
//    @Test(groups = {"MC:Authentication", "internal_auth", "external_auth", "Abdul Rehman", "SECTION:Device Browser Details Screen Width Validation", "SC:Device Browser Details Screen Width"}, description = "Validates transaction authentication with a null browser details screen width", priority = 104)
//    public void transactionAuthenticationNullBrowserDetailsScreenWidthCase() {
//        String endpoint = AppConstants.INTERNAL_END_POINT;
//        TransactionRequest transactionRequest = AppUtils.transactionRequestInstance();
//        transactionRequest.getDevice().getBrowserDetails().setScreenWidth(null);
//
//        if (isExternalAuth) {
//            endpoint = "";
//            AppUtils.updateSourceAndMerchantIdForExternal(transactionRequest);
//        }
//
//        String body = CommonAutomationUtils.stringToJson(transactionRequest);
//        if (isExternalAuth)
//            body = AppUtils.removeCardAndRoutingFromRequest(body);
//
//        Response response = AuthenticationTransactionRequest.postRequest(body, endpoint);
//
//        CommonAutomationUtils.verifyCommonResponseSuccessValidation(response, HttpStatus.SC_OK);
//        CommonAutomationUtils.verifyNonAvailableKey(response, List.of(AppConstants.DEVICE + "." + AppConstants.BROWSER_DETAIL + "." + AppConstants.SCREEN_WIDTH));
//    }
//
//    @Test(groups = {"MC:Authentication", "internal_auth", "external_auth", "Abdul Rehman", "SECTION:Device Browser Details Screen Width Validation", "SC:Device Browser Details Screen Width"}, description = "Validates transaction authentication with an empty browser details screen width", priority = 105)
//    public void transactionAuthenticationEmptyBrowserDetailsScreenWidthCase() {
//        String endpoint = AppConstants.INTERNAL_END_POINT;
//        TransactionRequest transactionRequest = AppUtils.transactionRequestInstance();
//
//        if (isExternalAuth) {
//            endpoint = "";
//            AppUtils.updateSourceAndMerchantIdForExternal(transactionRequest);
//        }
//
//        String body = CommonAutomationUtils.stringToJson(transactionRequest);
//        String updatedBody = CommonAutomationUtils.modifyJson(body, "MODIFY", "device.browserDetails.screenWidth", "");
//        if (isExternalAuth)
//            updatedBody = AppUtils.removeCardAndRoutingFromRequest(updatedBody);
//
//        Response response = AuthenticationTransactionRequest.postRequest(updatedBody, endpoint);
//
//        CommonAutomationUtils.verifyCommonResponseSuccessValidation(response, HttpStatus.SC_OK);
//    }
//
//    @Test(groups = {"MC:Authentication", "internal_auth", "external_auth", "Abdul Rehman", "SECTION:Device Browser Details Language Validation", "SC:Device Browser Details Language"}, description = "Validates transaction authentication with an invalid browser details language", priority = 106)
//    public void transactionAuthenticationInvalidBrowserDetailsLanguageCase() {
//        String endpoint = AppConstants.INTERNAL_END_POINT;
//        TransactionRequest transactionRequest = AppUtils.transactionRequestInstance();
//        transactionRequest.getDevice().getBrowserDetails().setLanguage("een-USSS");
//
//        if (isExternalAuth) {
//            endpoint = "";
//            AppUtils.updateSourceAndMerchantIdForExternal(transactionRequest);
//        }
//
//        String body = CommonAutomationUtils.stringToJson(transactionRequest);
//        if (isExternalAuth)
//            body = AppUtils.removeCardAndRoutingFromRequest(body);
//
//        Response response = AuthenticationTransactionRequest.postRequest(body, endpoint);
//
//        CommonAutomationUtils.verifyCommonResponseSuccessValidation(response, HttpStatus.SC_OK);
//    }
//
//    @Test(groups = {"MC:Authentication", "internal_auth", "external_auth", "Abdul Rehman", "SECTION:Device Browser Details Language Validation", "SC:Device Browser Details Language"}, description = "Validates transaction authentication with a null browser details language", priority = 107)
//    public void transactionAuthenticationNullBrowserDetailsLanguageCase() {
//        String endpoint = AppConstants.INTERNAL_END_POINT;
//        TransactionRequest transactionRequest = AppUtils.transactionRequestInstance();
//        transactionRequest.getDevice().getBrowserDetails().setLanguage(null);
//
//        if (isExternalAuth) {
//            endpoint = "";
//            AppUtils.updateSourceAndMerchantIdForExternal(transactionRequest);
//        }
//
//        String body = CommonAutomationUtils.stringToJson(transactionRequest);
//        if (isExternalAuth)
//            body = AppUtils.removeCardAndRoutingFromRequest(body);
//
//        Response response = AuthenticationTransactionRequest.postRequest(body, endpoint);
//
//        CommonAutomationUtils.verifyCommonResponseSuccessValidation(response, HttpStatus.SC_OK);
//        CommonAutomationUtils.verifyNonAvailableKey(response, List.of(AppConstants.DEVICE + "." + AppConstants.BROWSER_DETAIL + "." + AppConstants.LANGUAGE));
//    }
//
//    @Test(groups = {"MC:Authentication", "internal_auth", "external_auth", "Abdul Rehman", "SECTION:Device Browser Details Language Validation", "SC:Device Browser Details Language"}, description = "Validates transaction authentication with an empty browser details language", priority = 108)
//    public void transactionAuthenticationEmptyBrowserDetailsScreenLanguageCase() {
//        String endpoint = AppConstants.INTERNAL_END_POINT;
//        TransactionRequest transactionRequest = AppUtils.transactionRequestInstance();
//
//        if (isExternalAuth) {
//            endpoint = "";
//            AppUtils.updateSourceAndMerchantIdForExternal(transactionRequest);
//        }
//
//        String body = CommonAutomationUtils.stringToJson(transactionRequest);
//        String updatedBody = CommonAutomationUtils.modifyJson(body, "MODIFY", "device.browserDetails.language", "");
//        if (isExternalAuth)
//            updatedBody = AppUtils.removeCardAndRoutingFromRequest(updatedBody);
//
//        Response response = AuthenticationTransactionRequest.postRequest(updatedBody, endpoint);
//
//        CommonAutomationUtils.verifyCommonResponseSuccessValidation(response, HttpStatus.SC_OK);
//    }
//
//    @Test(groups = {"MC:Authentication", "internal_auth", "external_auth", "Abdul Rehman", "SECTION:Device Browser Details Color Depth Validation", "SC:Device Browser Details Color Depth"}, description = "Validates transaction authentication with a negative browser details color depth", priority = 109)
//    public void transactionAuthenticationNegativeBrowserDetailsColorDepthCase() {
//        String endpoint = AppConstants.INTERNAL_END_POINT;
//        TransactionRequest transactionRequest = AppUtils.transactionRequestInstance();
//        transactionRequest.getDevice().getBrowserDetails().setColorDepth(-123);
//
//        if (isExternalAuth) {
//            endpoint = "";
//            AppUtils.updateSourceAndMerchantIdForExternal(transactionRequest);
//        }
//
//        String body = CommonAutomationUtils.stringToJson(transactionRequest);
//        if (isExternalAuth)
//            body = AppUtils.removeCardAndRoutingFromRequest(body);
//
//        Response response = AuthenticationTransactionRequest.postRequest(body, endpoint);
//
//        CommonAutomationUtils.verifyCommonResponseSuccessValidation(response, HttpStatus.SC_OK);
//    }
//
//    @Test(groups = {"MC:Authentication", "internal_auth", "external_auth", "Abdul Rehman", "SECTION:Device Browser Details Color Depth Validation", "SC:Device Browser Details Color Depth"}, description = "Validates transaction authentication with a browser details color depth of zero", priority = 110)
//    public void transactionAuthenticationZeroBrowserDetailsColorDepthCase() {
//        String endpoint = AppConstants.INTERNAL_END_POINT;
//        TransactionRequest transactionRequest = AppUtils.transactionRequestInstance();
//        transactionRequest.getDevice().getBrowserDetails().setColorDepth(0);
//
//        if (isExternalAuth) {
//            endpoint = "";
//            AppUtils.updateSourceAndMerchantIdForExternal(transactionRequest);
//        }
//
//        String body = CommonAutomationUtils.stringToJson(transactionRequest);
//        if (isExternalAuth)
//            body = AppUtils.removeCardAndRoutingFromRequest(body);
//
//        Response response = AuthenticationTransactionRequest.postRequest(body, endpoint);
//
//        CommonAutomationUtils.verifyCommonResponseSuccessValidation(response, HttpStatus.SC_OK);
//    }
//
//    @Test(groups = {"MC:Authentication", "internal_auth", "external_auth", "Abdul Rehman", "SECTION:Device Browser Details Color Depth Validation", "SC:Device Browser Details Color Depth"}, description = "Validates transaction authentication with a null browser details color depth", priority = 111)
//    public void transactionAuthenticationNullBrowserDetailsColorDepthCase() {
//        String endpoint = AppConstants.INTERNAL_END_POINT;
//        TransactionRequest transactionRequest = AppUtils.transactionRequestInstance();
//        transactionRequest.getDevice().getBrowserDetails().setColorDepth(null);
//
//        if (isExternalAuth) {
//            endpoint = "";
//            AppUtils.updateSourceAndMerchantIdForExternal(transactionRequest);
//        }
//
//        String body = CommonAutomationUtils.stringToJson(transactionRequest);
//        if (isExternalAuth)
//            body = AppUtils.removeCardAndRoutingFromRequest(body);
//
//        Response response = AuthenticationTransactionRequest.postRequest(body, endpoint);
//
//        CommonAutomationUtils.verifyCommonResponseSuccessValidation(response, HttpStatus.SC_OK);
//        CommonAutomationUtils.verifyNonAvailableKey(response, List.of(AppConstants.DEVICE + "." + AppConstants.BROWSER_DETAIL + "." + AppConstants.COLOR_DEPTH));
//    }
//
//    @Test(groups = {"MC:Authentication", "internal_auth", "external_auth", "Abdul Rehman", "SECTION:Device Browser Details Color Depth Validation", "SC:Device Browser Details Color Depth"}, description = "Validates transaction authentication with an empty browser details color depth", priority = 112)
//    public void transactionAuthenticationEmptyBrowserDetailsColorDepthCase() {
//        String endpoint = AppConstants.INTERNAL_END_POINT;
//        TransactionRequest transactionRequest = AppUtils.transactionRequestInstance();
//
//        if (isExternalAuth) {
//            endpoint = "";
//            AppUtils.updateSourceAndMerchantIdForExternal(transactionRequest);
//        }
//
//        String body = CommonAutomationUtils.stringToJson(transactionRequest);
//        String updatedBody = CommonAutomationUtils.modifyJson(body, "MODIFY", "device.browserDetails.colorDepth", "");
//        if (isExternalAuth)
//            updatedBody = AppUtils.removeCardAndRoutingFromRequest(updatedBody);
//
//        Response response = AuthenticationTransactionRequest.postRequest(updatedBody, endpoint);
//
//        CommonAutomationUtils.verifyCommonResponseSuccessValidation(response, HttpStatus.SC_OK);
//    }
//
//    @Test(groups = {"MC:Authentication", "internal_auth", "external_auth", "Abdul Rehman", "SECTION:Device Browser Details Java Enabled Validation", "SC:Device Browser Details Java Enabled"}, description = "Validates transaction authentication with browser details Java enabled set to false", priority = 113)
//    public void transactionAuthenticationFalseBrowserDetailsJavaEnabledCase() {
//        String endpoint = AppConstants.INTERNAL_END_POINT;
//        TransactionRequest transactionRequest = AppUtils.transactionRequestInstance();
//        transactionRequest.getDevice().getBrowserDetails().setJavaEnabled("false");
//
//        if (isExternalAuth) {
//            endpoint = "";
//            AppUtils.updateSourceAndMerchantIdForExternal(transactionRequest);
//        }
//
//        String body = CommonAutomationUtils.stringToJson(transactionRequest);
//        if (isExternalAuth)
//            body = AppUtils.removeCardAndRoutingFromRequest(body);
//
//        Response response = AuthenticationTransactionRequest.postRequest(body, endpoint);
//
//        CommonAutomationUtils.verifyCommonResponseSuccessValidation(response, HttpStatus.SC_OK);
//    }
//
//    @Test(groups = {"MC:Authentication", "internal_auth", "external_auth", "Abdul Rehman", "SECTION:Device Browser Details Java Enabled Validation", "SC:Device Browser Details Java Enabled"}, description = "Validates transaction authentication with browser details Java enabled set to true", priority = 114)
//    public void transactionAuthenticationTrueBrowserDetailsJavaEnabledCase() {
//        String endpoint = AppConstants.INTERNAL_END_POINT;
//        TransactionRequest transactionRequest = AppUtils.transactionRequestInstance();
//        transactionRequest.getDevice().getBrowserDetails().setJavaEnabled("true");
//
//        if (isExternalAuth) {
//            endpoint = "";
//            AppUtils.updateSourceAndMerchantIdForExternal(transactionRequest);
//        }
//
//        String body = CommonAutomationUtils.stringToJson(transactionRequest);
//        if (isExternalAuth)
//            body = AppUtils.removeCardAndRoutingFromRequest(body);
//
//        Response response = AuthenticationTransactionRequest.postRequest(body, endpoint);
//
//        CommonAutomationUtils.verifyCommonResponseSuccessValidation(response, HttpStatus.SC_OK);
//    }
//
//    @Test(groups = {"MC:Authentication", "internal_auth", "external_auth", "Abdul Rehman", "SECTION:Device Browser Details Java Enabled Validation", "SC:Device Browser Details Java Enabled"}, description = "Validates transaction authentication with case-sensitive Java enabled value", priority = 115)
//    public void transactionAuthenticationCaseSensitiveBrowserDetailsJavaEnabledCase() {
//        String endpoint = AppConstants.INTERNAL_END_POINT;
//        TransactionRequest transactionRequest = AppUtils.transactionRequestInstance();
//        transactionRequest.getDevice().getBrowserDetails().setJavaEnabled("TRuE");
//
//        if (isExternalAuth) {
//            endpoint = "";
//            AppUtils.updateSourceAndMerchantIdForExternal(transactionRequest);
//        }
//
//        String body = CommonAutomationUtils.stringToJson(transactionRequest);
//        if (isExternalAuth)
//            body = AppUtils.removeCardAndRoutingFromRequest(body);
//
//        Response response = AuthenticationTransactionRequest.postRequest(body, endpoint);
//        String textResponse = response.getBody().asString();
//        JsonNode jsonResponse = CommonAutomationUtils.convertToJson(textResponse);
//
//        CommonAutomationUtils.verifyCommonResponseFailedValidation(jsonResponse, response.getStatusCode(), HttpStatus.SC_BAD_REQUEST, AppConstants.UNACCEPTED_JSON_REQUEST_CODE, AppConstants.UNACCEPTED_JSON_REQUEST_ERROR);
//    }
//
//    @Test(groups = {"MC:Authentication", "internal_auth", "external_auth", "Abdul Rehman", "SECTION:Device Browser Details Java Enabled Validation", "SC:Device Browser Details Java Enabled"}, description = "Validates transaction authentication with a null browser details Java enabled value", priority = 116)
//    public void transactionAuthenticationNullBrowserDetailsJavaEnabledCase() {
//        String endpoint = AppConstants.INTERNAL_END_POINT;
//        TransactionRequest transactionRequest = AppUtils.transactionRequestInstance();
//        transactionRequest.getDevice().getBrowserDetails().setJavaEnabled(null);
//
//        if (isExternalAuth) {
//            endpoint = "";
//            AppUtils.updateSourceAndMerchantIdForExternal(transactionRequest);
//        }
//
//        String body = CommonAutomationUtils.stringToJson(transactionRequest);
//        if (isExternalAuth)
//            body = AppUtils.removeCardAndRoutingFromRequest(body);
//
//        Response response = AuthenticationTransactionRequest.postRequest(body, endpoint);
//
//        CommonAutomationUtils.verifyCommonResponseSuccessValidation(response, HttpStatus.SC_OK);
//        CommonAutomationUtils.verifyNonAvailableKey(response, List.of(AppConstants.DEVICE + "." + AppConstants.BROWSER_DETAIL + "." + AppConstants.JAVA_ENABLED));
//    }
//
//    @Test(groups = {"MC:Authentication", "internal_auth", "external_auth", "Abdul Rehman", "SECTION:Device Browser Details Java Enabled Validation", "SC:Device Browser Details Java Enabled"}, description = "Validates transaction authentication with an empty browser details Java enabled value", priority = 117)
//    public void transactionAuthenticationEmptyBrowserDetailsJavaEnabledCase() {
//        String endpoint = AppConstants.INTERNAL_END_POINT;
//        TransactionRequest transactionRequest = AppUtils.transactionRequestInstance();
//
//        if (isExternalAuth) {
//            endpoint = "";
//            AppUtils.updateSourceAndMerchantIdForExternal(transactionRequest);
//        }
//
//        String body = CommonAutomationUtils.stringToJson(transactionRequest);
//        String updatedBody = CommonAutomationUtils.modifyJson(body, "MODIFY", "device.browserDetails.javaEnabled", "");
//        if (isExternalAuth)
//            updatedBody = AppUtils.removeCardAndRoutingFromRequest(updatedBody);
//
//        Response response = AuthenticationTransactionRequest.postRequest(updatedBody, endpoint);
//
//        CommonAutomationUtils.verifyCommonResponseSuccessValidation(response, HttpStatus.SC_OK);
//    }
//
//    @Test(groups = {"MC:Authentication", "internal_auth", "external_auth", "Abdul Rehman", "SECTION:Device Browser Details Java Enabled Validation", "SC:Device Browser Details Java Enabled"}, description = "Validates transaction authentication with an invalid browser details Java enabled value", priority = 118)
//    public void transactionAuthenticationInvalidBrowserDetailsJavaEnabledCase() {
//        String endpoint = AppConstants.INTERNAL_END_POINT;
//        TransactionRequest transactionRequest = AppUtils.transactionRequestInstance();
//        transactionRequest.getDevice().getBrowserDetails().setJavaEnabled("invalid");
//
//        if (isExternalAuth) {
//            endpoint = "";
//            AppUtils.updateSourceAndMerchantIdForExternal(transactionRequest);
//        }
//
//        String body = CommonAutomationUtils.stringToJson(transactionRequest);
//        if (isExternalAuth)
//            body = AppUtils.removeCardAndRoutingFromRequest(body);
//
//        Response response = AuthenticationTransactionRequest.postRequest(body, endpoint);
//        String textResponse = response.getBody().asString();
//        JsonNode jsonResponse = CommonAutomationUtils.convertToJson(textResponse);
//
//        CommonAutomationUtils.verifyCommonResponseFailedValidation(jsonResponse, response.getStatusCode(), HttpStatus.SC_BAD_REQUEST, AppConstants.UNACCEPTED_JSON_REQUEST_CODE, AppConstants.UNACCEPTED_JSON_REQUEST_ERROR);
//    }
//
//    @Test(groups = {"MC:Authentication", "internal_auth", "external_auth", "Abdul Rehman", "SECTION:Device Browser Details Timezone Validation", "SC:Device Browser Details Timezone"}, description = "Validates transaction authentication with a null browser details timezone", priority = 119)
//    public void transactionAuthenticationNullBrowserDetailsTimezoneCase() {
//        String endpoint = AppConstants.INTERNAL_END_POINT;
//        TransactionRequest transactionRequest = AppUtils.transactionRequestInstance();
//        transactionRequest.getDevice().getBrowserDetails().setTimeZone(null);
//
//        if (isExternalAuth) {
//            endpoint = "";
//            AppUtils.updateSourceAndMerchantIdForExternal(transactionRequest);
//        }
//
//        String body = CommonAutomationUtils.stringToJson(transactionRequest);
//        if (isExternalAuth)
//            body = AppUtils.removeCardAndRoutingFromRequest(body);
//
//        Response response = AuthenticationTransactionRequest.postRequest(body, endpoint);
//
//        CommonAutomationUtils.verifyCommonResponseSuccessValidation(response, HttpStatus.SC_OK);
//        CommonAutomationUtils.verifyNonAvailableKey(response, List.of(AppConstants.DEVICE + "." + AppConstants.BROWSER_DETAIL + "." + AppConstants.BROWSER_DETAIL));
//    }
//
//    @Test(groups = {"MC:Authentication", "internal_auth", "external_auth", "Abdul Rehman", "SECTION:Device Browser Details Timezone Validation", "SC:Device Browser Details Timezone"}, description = "Validates transaction authentication with an empty browser details timezone", priority = 120)
//    public void transactionAuthenticationEmptyBrowserDetailsTimezoneCase() {
//        String endpoint = AppConstants.INTERNAL_END_POINT;
//        TransactionRequest transactionRequest = AppUtils.transactionRequestInstance();
//
//        if (isExternalAuth) {
//            endpoint = "";
//            AppUtils.updateSourceAndMerchantIdForExternal(transactionRequest);
//        }
//
//        String body = CommonAutomationUtils.stringToJson(transactionRequest);
//        String updatedBody = CommonAutomationUtils.modifyJson(body, "MODIFY", "device.browserDetails.timeZone", "");
//        if (isExternalAuth)
//            updatedBody = AppUtils.removeCardAndRoutingFromRequest(updatedBody);
//
//        Response response = AuthenticationTransactionRequest.postRequest(updatedBody, endpoint);
//
//        CommonAutomationUtils.verifyCommonResponseSuccessValidation(response, HttpStatus.SC_OK);
//    }
//
//    @Test(groups = {"MC:Authentication", "internal_auth", "external_auth", "Abdul Rehman", "SECTION:Device Browser Details Timezone Validation", "SC:Device Browser Details Timezone"}, description = "Validates transaction authentication with an invalid browser details timezone", priority = 121)
//    public void transactionAuthenticationInvalidBrowserDetailsTimezoneCase() {
//        String endpoint = AppConstants.INTERNAL_END_POINT;
//        TransactionRequest transactionRequest = AppUtils.transactionRequestInstance();
//        transactionRequest.getDevice().getBrowserDetails().setTimeZone(-99999);
//
//        if (isExternalAuth) {
//            endpoint = "";
//            AppUtils.updateSourceAndMerchantIdForExternal(transactionRequest);
//        }
//
//        String body = CommonAutomationUtils.stringToJson(transactionRequest);
//        if (isExternalAuth)
//            body = AppUtils.removeCardAndRoutingFromRequest(body);
//
//        Response response = AuthenticationTransactionRequest.postRequest(body, endpoint);
//
//        CommonAutomationUtils.verifyCommonResponseSuccessValidation(response, HttpStatus.SC_OK);
//    }
//
//    @Test(groups = {"MC:Authentication", "internal_auth", "external_auth", "Abdul Rehman", "SECTION:Device Browser Details Timezone Validation", "SC:Device Browser Details Timezone"}, description = "Validates transaction authentication with a valid browser details timezone", priority = 122)
//    public void transactionAuthenticationValidBrowserDetailsTimezoneCase() {
//        String endpoint = AppConstants.INTERNAL_END_POINT;
//        TransactionRequest transactionRequest = AppUtils.transactionRequestInstance();
//        transactionRequest.getDevice().getBrowserDetails().setTimeZone(100);
//
//        if (isExternalAuth) {
//            endpoint = "";
//            AppUtils.updateSourceAndMerchantIdForExternal(transactionRequest);
//        }
//
//        String body = CommonAutomationUtils.stringToJson(transactionRequest);
//        if (isExternalAuth)
//            body = AppUtils.removeCardAndRoutingFromRequest(body);
//
//        Response response = AuthenticationTransactionRequest.postRequest(body, endpoint);
//
//        CommonAutomationUtils.verifyCommonResponseSuccessValidation(response, HttpStatus.SC_OK);
//    }
//
//    @Test(groups = {"MC:Authentication", "internal_auth", "external_auth", "Abdul Rehman", "SECTION:Device Browser Details Accept Header Validation", "SC:Device Browser Details Accept Header"}, description = "Validates transaction authentication with a null browser details accept headers", priority = 123)
//    public void transactionAuthenticationNullBrowserDetailsAcceptHeadersCase() {
//        String endpoint = AppConstants.INTERNAL_END_POINT;
//        TransactionRequest transactionRequest = AppUtils.transactionRequestInstance();
//        transactionRequest.getDevice().getBrowserDetails().setAcceptHeaders(null);
//
//        if (isExternalAuth) {
//            endpoint = "";
//            AppUtils.updateSourceAndMerchantIdForExternal(transactionRequest);
//        }
//
//        String body = CommonAutomationUtils.stringToJson(transactionRequest);
//        if (isExternalAuth)
//            body = AppUtils.removeCardAndRoutingFromRequest(body);
//
//        Response response = AuthenticationTransactionRequest.postRequest(body, endpoint);
//
//        CommonAutomationUtils.verifyCommonResponseSuccessValidation(response, HttpStatus.SC_OK);
//        CommonAutomationUtils.verifyNonAvailableKey(response, List.of(AppConstants.DEVICE + "." + AppConstants.BROWSER_DETAIL + "." + AppConstants.ACCEPT_HEADER));
//    }
//
//    @Test(groups = {"MC:Authentication", "internal_auth", "external_auth", "Abdul Rehman", "SECTION:Device Browser Details Accept Header Validation", "SC:Device Browser Details Accept Header"}, description = "Validates transaction authentication with an empty browser details accept headers", priority = 124)
//    public void transactionAuthenticationEmptyBrowserDetailsAcceptHeadersCase() {
//        String endpoint = AppConstants.INTERNAL_END_POINT;
//        TransactionRequest transactionRequest = AppUtils.transactionRequestInstance();
//
//        if (isExternalAuth) {
//            endpoint = "";
//            AppUtils.updateSourceAndMerchantIdForExternal(transactionRequest);
//        }
//
//        String body = CommonAutomationUtils.stringToJson(transactionRequest);
//        String updatedBody = CommonAutomationUtils.modifyJson(body, "MODIFY", "device.browserDetails.acceptHeaders", "");
//        if (isExternalAuth)
//            updatedBody = AppUtils.removeCardAndRoutingFromRequest(updatedBody);
//
//        Response response = AuthenticationTransactionRequest.postRequest(updatedBody, endpoint);
//
//        CommonAutomationUtils.verifyCommonResponseSuccessValidation(response, HttpStatus.SC_OK);
//    }
//
//    @Test(groups = {"MC:Authentication", "internal_auth", "external_auth", "Abdul Rehman", "SECTION:Device Browser Details Accept Header Validation", "SC:Device Browser Details Accept Header"}, description = "Validates transaction authentication with an invalid browser details accept headers", priority = 125)
//    public void transactionAuthenticationInvalidBrowserDetailsAcceptHeadersCase() {
//        String endpoint = AppConstants.INTERNAL_END_POINT;
//        TransactionRequest transactionRequest = AppUtils.transactionRequestInstance();
//        transactionRequest.getDevice().getBrowserDetails().setAcceptHeaders("invalid_header");
//
//        if (isExternalAuth) {
//            endpoint = "";
//            AppUtils.updateSourceAndMerchantIdForExternal(transactionRequest);
//        }
//
//        String body = CommonAutomationUtils.stringToJson(transactionRequest);
//        if (isExternalAuth)
//            body = AppUtils.removeCardAndRoutingFromRequest(body);
//
//        Response response = AuthenticationTransactionRequest.postRequest(body, endpoint);
//
//        CommonAutomationUtils.verifyCommonResponseSuccessValidation(response, HttpStatus.SC_OK);
//    }
//
//    @Test(groups = {"MC:Authentication", "internal_auth", "external_auth", "Abdul Rehman", "SECTION:Device Browser Details Accept Header Validation", "SC:Device Browser Details Accept Header"}, description = "Validates transaction authentication with a valid browser details accept headers", priority = 126)
//    public void transactionAuthenticationValidBrowserDetailsAcceptHeadersCase() {
//        String endpoint = AppConstants.INTERNAL_END_POINT;
//        TransactionRequest transactionRequest = AppUtils.transactionRequestInstance();
//        transactionRequest.getDevice().getBrowserDetails().setAcceptHeaders("application/json");
//
//        if (isExternalAuth) {
//            endpoint = "";
//            AppUtils.updateSourceAndMerchantIdForExternal(transactionRequest);
//        }
//
//        String body = CommonAutomationUtils.stringToJson(transactionRequest);
//        if (isExternalAuth)
//            body = AppUtils.removeCardAndRoutingFromRequest(body);
//
//        Response response = AuthenticationTransactionRequest.postRequest(body, endpoint);
//
//        CommonAutomationUtils.verifyCommonResponseSuccessValidation(response, HttpStatus.SC_OK);
//    }
//
//    @Test(groups = {"MC:Authentication", "internal_auth", "external_auth", "Abdul Rehman", "SECTION:Device Browser Details Accept Header Validation", "SC:Device Browser Details Accept Header"}, description = "Validates transaction authentication with multiple valid browser details accept headers", priority = 127)
//    public void transactionAuthenticationMultipleValidBrowserDetailsAcceptHeadersCase() {
//        String endpoint = AppConstants.INTERNAL_END_POINT;
//        TransactionRequest transactionRequest = AppUtils.transactionRequestInstance();
//        transactionRequest.getDevice().getBrowserDetails().setAcceptHeaders("application/json, application/xml");
//
//        if (isExternalAuth) {
//            endpoint = "";
//            AppUtils.updateSourceAndMerchantIdForExternal(transactionRequest);
//        }
//
//        String body = CommonAutomationUtils.stringToJson(transactionRequest);
//        if (isExternalAuth)
//            body = AppUtils.removeCardAndRoutingFromRequest(body);
//
//        Response response = AuthenticationTransactionRequest.postRequest(body, endpoint);
//
//        CommonAutomationUtils.verifyCommonResponseSuccessValidation(response, HttpStatus.SC_OK);
//    }
//
//    @Test(groups = {"MC:Authentication", "internal_auth", "external_auth", "Abdul Rehman", "SECTION:Device Browser Details Challenge Window Validation", "SC:Device Browser Details Challenge Window"}, description = "Validates transaction authentication with a valid fullscreen browser details challenge window size", priority = 128)
//    public void transactionAuthenticationValidFullScreenBrowserDetailsChallengeWindowSizeCase() {
//        String endpoint = AppConstants.INTERNAL_END_POINT;
//        TransactionRequest transactionRequest = AppUtils.transactionRequestInstance();
//        transactionRequest.getDevice().getBrowserDetails().setChallengeWindowSize("FULL_SCREEN");
//
//        if (isExternalAuth) {
//            endpoint = "";
//            AppUtils.updateSourceAndMerchantIdForExternal(transactionRequest);
//        }
//
//        String body = CommonAutomationUtils.stringToJson(transactionRequest);
//        if (isExternalAuth)
//            body = AppUtils.removeCardAndRoutingFromRequest(body);
//
//        Response response = AuthenticationTransactionRequest.postRequest(body, endpoint);
//
//        CommonAutomationUtils.verifyCommonResponseSuccessValidation(response, HttpStatus.SC_OK);
//    }
//
//    @Test(groups = {"MC:Authentication", "internal_auth", "external_auth", "Abdul Rehman", "SECTION:Device Browser Details Challenge Window Validation", "SC:Device Browser Details Challenge Window"}, description = "Validates transaction authentication with a valid 250x400 browser details challenge window size", priority = 129)
//    public void transactionAuthenticationValid250X400BrowserDetailsChallengeWindowSizeCase() {
//        String endpoint = AppConstants.INTERNAL_END_POINT;
//        TransactionRequest transactionRequest = AppUtils.transactionRequestInstance();
//        transactionRequest.getDevice().getBrowserDetails().setChallengeWindowSize("250_X_400");
//
//        if (isExternalAuth) {
//            endpoint = "";
//            AppUtils.updateSourceAndMerchantIdForExternal(transactionRequest);
//        }
//
//        String body = CommonAutomationUtils.stringToJson(transactionRequest);
//        if (isExternalAuth)
//            body = AppUtils.removeCardAndRoutingFromRequest(body);
//
//        Response response = AuthenticationTransactionRequest.postRequest(body, endpoint);
//
//        CommonAutomationUtils.verifyCommonResponseSuccessValidation(response, HttpStatus.SC_OK);
//    }
//
//    @Test(groups = {"MC:Authentication", "internal_auth", "external_auth", "Abdul Rehman", "SECTION:Device Browser Details Challenge Window Validation", "SC:Device Browser Details Challenge Window"}, description = "Validates transaction authentication with a valid 390x400 browser details challenge window size", priority = 130)
//    public void transactionAuthenticationValid390_X_400BrowserDetailsChallengeWindowSizeCase() {
//        String endpoint = AppConstants.INTERNAL_END_POINT;
//        TransactionRequest transactionRequest = AppUtils.transactionRequestInstance();
//        transactionRequest.getDevice().getBrowserDetails().setChallengeWindowSize("390_X_400");
//
//        if (isExternalAuth) {
//            endpoint = "";
//            AppUtils.updateSourceAndMerchantIdForExternal(transactionRequest);
//        }
//
//        String body = CommonAutomationUtils.stringToJson(transactionRequest);
//        if (isExternalAuth)
//            body = AppUtils.removeCardAndRoutingFromRequest(body);
//
//        Response response = AuthenticationTransactionRequest.postRequest(body, endpoint);
//
//        CommonAutomationUtils.verifyCommonResponseSuccessValidation(response, HttpStatus.SC_OK);
//    }
//
//    @Test(groups = {"MC:Authentication", "internal_auth", "external_auth", "Abdul Rehman", "SECTION:Device Browser Details Challenge Window Validation", "SC:Device Browser Details Challenge Window"}, description = "Validates transaction authentication with a valid 500x600 browser details challenge window size", priority = 131)
//    public void transactionAuthenticationValid500_X_600BrowserDetailsChallengeWindowSizeCase() {
//
//        String endpoint = AppConstants.INTERNAL_END_POINT;
//        TransactionRequest transactionRequest = AppUtils.transactionRequestInstance();
//        transactionRequest.getDevice().getBrowserDetails().setChallengeWindowSize("500_X_600");
//
//        if (isExternalAuth) {
//            endpoint = "";
//            AppUtils.updateSourceAndMerchantIdForExternal(transactionRequest);
//        }
//
//        String body = CommonAutomationUtils.stringToJson(transactionRequest);
//        if (isExternalAuth)
//            body = AppUtils.removeCardAndRoutingFromRequest(body);
//        Response response = AuthenticationTransactionRequest.postRequest(body, endpoint);
//
//        CommonAutomationUtils.verifyCommonResponseSuccessValidation(response, HttpStatus.SC_OK);
//    }
//
//    @Test(groups = {"MC:Authentication", "internal_auth", "external_auth", "Abdul Rehman", "SECTION:Device Browser Details Challenge Window Validation", "SC:Device Browser Details Challenge Window"}, description = "Validates transaction authentication with a valid 600x400 browser details challenge window size", priority = 132)
//    public void transactionAuthenticationValid600_X_400BrowserDetailsChallengeWindowSizeCase() {
//
//        String endpoint = AppConstants.INTERNAL_END_POINT;
//        TransactionRequest transactionRequest = AppUtils.transactionRequestInstance();
//        transactionRequest.getDevice().getBrowserDetails().setChallengeWindowSize("600_X_400");
//
//        if (isExternalAuth) {
//            endpoint = "";
//            AppUtils.updateSourceAndMerchantIdForExternal(transactionRequest);
//        }
//
//        String body = CommonAutomationUtils.stringToJson(transactionRequest);
//        if (isExternalAuth)
//            body = AppUtils.removeCardAndRoutingFromRequest(body);
//        Response response = AuthenticationTransactionRequest.postRequest(body, endpoint);
//
//        CommonAutomationUtils.verifyCommonResponseSuccessValidation(response, HttpStatus.SC_OK);
//    }
//
//    @Test(groups = {"MC:Authentication", "internal_auth", "external_auth", "Abdul Rehman", "SECTION:Device Browser Details Challenge Window Validation", "SC:Device Browser Details Challenge Window"}, description = "Validates transaction authentication with a null browser details challenge window size", priority = 133)
//    public void transactionAuthenticationNullBrowserDetailsChallengeWindowSizeCase() {
//
//        String endpoint = AppConstants.INTERNAL_END_POINT;
//        TransactionRequest transactionRequest = AppUtils.transactionRequestInstance();
//        transactionRequest.getDevice().getBrowserDetails().setChallengeWindowSize(null);
//
//        if (isExternalAuth) {
//            endpoint = "";
//            AppUtils.updateSourceAndMerchantIdForExternal(transactionRequest);
//        }
//
//        String body = CommonAutomationUtils.stringToJson(transactionRequest);
//        if (isExternalAuth)
//            body = AppUtils.removeCardAndRoutingFromRequest(body);
//        Response response = AuthenticationTransactionRequest.postRequest(body, endpoint);
//
//        CommonAutomationUtils.verifyCommonResponseSuccessValidation(response, HttpStatus.SC_OK);
//        CommonAutomationUtils.verifyNonAvailableKey(response, List.of(AppConstants.DEVICE + "." + AppConstants.BROWSER_DETAIL + "." + AppConstants.CHALLENGE_WINDOW_SIZE));
//    }
//
//    @Test(groups = {"MC:Authentication", "internal_auth", "external_auth", "Abdul Rehman", "SECTION:Device Browser Details Challenge Window Validation", "SC:Device Browser Details Challenge Window"}, description = "Validates transaction authentication with an empty browser details challenge window size", priority = 134)
//    public void transactionAuthenticationEmptyBrowserDetailsChallengeWindowSizeCase() {
//
//        String endpoint = AppConstants.INTERNAL_END_POINT;
//        TransactionRequest transactionRequest = AppUtils.transactionRequestInstance();
//
//        if (isExternalAuth) {
//            endpoint = "";
//            AppUtils.updateSourceAndMerchantIdForExternal(transactionRequest);
//        }
//
//        String body = CommonAutomationUtils.stringToJson(transactionRequest);
//        String updatedBody = CommonAutomationUtils.modifyJson(body, "MODIFY", "device.browserDetails.challengeWindowSize", "");
//
//        if (isExternalAuth)
//            updatedBody = AppUtils.removeCardAndRoutingFromRequest(updatedBody);
//
//        Response response = AuthenticationTransactionRequest.postRequest(updatedBody, endpoint);
//        CommonAutomationUtils.verifyCommonResponseSuccessValidation(response, HttpStatus.SC_OK);
//    }
//
//    @Test(groups = {"MC:Authentication", "internal_auth", "external_auth", "Abdul Rehman", "SECTION:Device Browser Details Challenge Window Validation", "SC:Device Browser Details Challenge Window"}, description = "Validates transaction authentication with an invalid browser details challenge window size", priority = 135)
//    public void transactionAuthenticationInvalidBrowserDetailsChallengeWindowSizeCase() {
//
//        String endpoint = AppConstants.INTERNAL_END_POINT;
//        TransactionRequest transactionRequest = AppUtils.transactionRequestInstance();
//        transactionRequest.getDevice().getBrowserDetails().setChallengeWindowSize("invalid_challenge_window_size");
//
//        if (isExternalAuth) {
//            endpoint = "";
//            AppUtils.updateSourceAndMerchantIdForExternal(transactionRequest);
//        }
//
//        String body = CommonAutomationUtils.stringToJson(transactionRequest);
//        if (isExternalAuth)
//            body = AppUtils.removeCardAndRoutingFromRequest(body);
//        Response response = AuthenticationTransactionRequest.postRequest(body, endpoint);
//
//        CommonAutomationUtils.verifyCommonResponseSuccessValidation(response, HttpStatus.SC_OK);
//    }
//
//    @Test(groups = {"MC:Authentication", "internal_auth", "external_auth", "Abdul Rehman", "SECTION:Device Browser User Agent Validation", "SC:Device Browser User Agent"}, description = "Validates transaction authentication with a valid browser details user agent", priority = 136)
//    public void transactionAuthenticationValidBrowserDetailsBrowserUserAgentCase() {
//
//        String endpoint = AppConstants.INTERNAL_END_POINT;
//        TransactionRequest transactionRequest = AppUtils.transactionRequestInstance();
//        transactionRequest.getDevice().getBrowserDetails().setBrowserUserAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/128.0.0.0 Safari/537.36");
//
//        if (isExternalAuth) {
//            endpoint = "";
//            AppUtils.updateSourceAndMerchantIdForExternal(transactionRequest);
//        }
//
//        String body = CommonAutomationUtils.stringToJson(transactionRequest);
//        if (isExternalAuth)
//            body = AppUtils.removeCardAndRoutingFromRequest(body);
//        Response response = AuthenticationTransactionRequest.postRequest(body, endpoint);
//
//        CommonAutomationUtils.verifyCommonResponseSuccessValidation(response, HttpStatus.SC_OK);
//    }
//
//    @Test(groups = {"MC:Authentication", "internal_auth", "external_auth", "Abdul Rehman", "SECTION:Device Browser User Agent Validation", "SC:Device Browser User Agent"}, description = "Validates transaction authentication with a null browser details user agent", priority = 137)
//    public void transactionAuthenticationNullBrowserDetailsBrowserUserAgentCase() {
//
//        String endpoint = AppConstants.INTERNAL_END_POINT;
//        TransactionRequest transactionRequest = AppUtils.transactionRequestInstance();
//        transactionRequest.getDevice().getBrowserDetails().setBrowserUserAgent(null);
//
//        if (isExternalAuth) {
//            endpoint = "";
//            AppUtils.updateSourceAndMerchantIdForExternal(transactionRequest);
//        }
//
//        String body = CommonAutomationUtils.stringToJson(transactionRequest);
//        if (isExternalAuth)
//            body = AppUtils.removeCardAndRoutingFromRequest(body);
//        Response response = AuthenticationTransactionRequest.postRequest(body, endpoint);
//
//        CommonAutomationUtils.verifyCommonResponseSuccessValidation(response, HttpStatus.SC_OK);
//        CommonAutomationUtils.verifyNonAvailableKey(response, List.of(AppConstants.DEVICE + "." + AppConstants.BROWSER_DETAIL + "." + AppConstants.BROWSER_USER_AGENT));
//    }
//
//    @Test(groups = {"MC:Authentication", "internal_auth", "external_auth", "Abdul Rehman", "SECTION:Device Browser User Agent Validation", "SC:Device Browser User Agent"}, description = "Validates transaction authentication with an empty browser details user agent", priority = 138)
//    public void transactionAuthenticationEmptyBrowserDetailsBrowserUserAgentCase() {
//
//        String endpoint = AppConstants.INTERNAL_END_POINT;
//        TransactionRequest transactionRequest = AppUtils.transactionRequestInstance();
//
//        if (isExternalAuth) {
//            endpoint = "";
//            AppUtils.updateSourceAndMerchantIdForExternal(transactionRequest);
//        }
//
//        String body = CommonAutomationUtils.stringToJson(transactionRequest);
//        String updatedBody = CommonAutomationUtils.modifyJson(body, "MODIFY", "device.browserDetails.browserUserAgent", "");
//
//        if (isExternalAuth)
//            updatedBody = AppUtils.removeCardAndRoutingFromRequest(updatedBody);
//        Response response = AuthenticationTransactionRequest.postRequest(updatedBody, endpoint);
//
//        CommonAutomationUtils.verifyCommonResponseSuccessValidation(response, HttpStatus.SC_OK);
//    }
//
//    @Test(groups = {"MC:Authentication", "internal_auth", "external_auth", "Abdul Rehman", "SECTION:Device Browser User Agent Validation", "SC:Device Browser User Agent"}, description = "Validates transaction authentication with an invalid browser details user agent", priority = 139)
//    public void transactionAuthenticationInvalidBrowserDetailsBrowserUserAgentCase() {
//
//        String endpoint = AppConstants.INTERNAL_END_POINT;
//        TransactionRequest transactionRequest = AppUtils.transactionRequestInstance();
//        transactionRequest.getDevice().getBrowserDetails().setBrowserUserAgent("invalid_browser_user_agent");
//
//        if (isExternalAuth) {
//            endpoint = "";
//            AppUtils.updateSourceAndMerchantIdForExternal(transactionRequest);
//        }
//
//        String body = CommonAutomationUtils.stringToJson(transactionRequest);
//        if (isExternalAuth)
//            body = AppUtils.removeCardAndRoutingFromRequest(body);
//        Response response = AuthenticationTransactionRequest.postRequest(body, endpoint);
//
//        CommonAutomationUtils.verifyCommonResponseSuccessValidation(response, HttpStatus.SC_OK);
//    }
//
//    @Test(groups = {"MC:Authentication", "internal_auth", "external_auth", "Abdul Rehman", "SECTION:Authentication Validation", "SC:Authentication"}, description = "Validates transaction authentication with a null authentication object", priority = 201)
//    public void transactionAuthenticationNullAuthenticationCase() {
//
//        String endpoint = AppConstants.INTERNAL_END_POINT;
//        TransactionRequest transactionRequest = AppUtils.transactionRequestInstance();
//        transactionRequest.setAuthentication(null);
//
//        if (isExternalAuth) {
//            endpoint = "";
//            AppUtils.updateSourceAndMerchantIdForExternal(transactionRequest);
//        }
//
//        String body = CommonAutomationUtils.stringToJson(transactionRequest);
//        if (isExternalAuth)
//            body = AppUtils.removeCardAndRoutingFromRequest(body);
//        Response response = AuthenticationTransactionRequest.postRequest(body, endpoint);
//        String textResponse = response.getBody().asString();
//        JsonNode jsonResponse = CommonAutomationUtils.convertToJson(textResponse);
//
//        CommonAutomationUtils.verifyCommonResponseFailedValidation(jsonResponse, response.getStatusCode(), HttpStatus.SC_BAD_REQUEST, AppConstants.REQUIRED_FIELD_INVALID_CODE, AppConstants.REQUIRED_FIELD_INVALID_ERROR);
//    }
//
//    @Test(groups = {"MC:Authentication", "internal_auth", "external_auth", "Abdul Rehman", "SECTION:Authentication Validation", "SC:Authentication"}, description = "Validates transaction authentication with an empty authentication object", priority = 202)
//    public void transactionAuthenticationEmptyAuthenticationObjectCase() {
//
//        String endpoint = AppConstants.INTERNAL_END_POINT;
//        TransactionRequest transactionRequest = AppUtils.transactionRequestInstance();
//
//        String body = CommonAutomationUtils.stringToJson(transactionRequest);
//        String updatedBody = CommonAutomationUtils.modifyJson(body, "DELETE", "authentication.channel", null);
//        updatedBody = CommonAutomationUtils.modifyJson(updatedBody, "DELETE", "authentication.purpose", null);
//
//        if (isExternalAuth) {
//            endpoint = "";
//            AppUtils.updateSourceAndMerchantIdForExternal(transactionRequest);
//        }
//
//        if (isExternalAuth)
//            updatedBody = AppUtils.removeCardAndRoutingFromRequest(updatedBody);
//        Response response = AuthenticationTransactionRequest.postRequest(updatedBody, endpoint);
//        String textResponse = response.getBody().asString();
//        JsonNode jsonResponse = CommonAutomationUtils.convertToJson(textResponse);
//
//        CommonAutomationUtils.verifyCommonResponseFailedValidation(jsonResponse, response.getStatusCode(), HttpStatus.SC_BAD_REQUEST, AppConstants.REQUIRED_FIELD_INVALID_CODE, AppConstants.REQUIRED_FIELD_INVALID_ERROR);
//    }
//
//    @Test(groups = {"MC:Authentication", "internal_auth", "external_auth", "Abdul Rehman", "SECTION:Authentication Validation", "SC:Authentication"}, description = "Validates transaction authentication with an authentication object without channel", priority = 202)
//    public void transactionAuthenticationAuthenticationObjectWithoutChannelCase() {
//
//        String endpoint = AppConstants.INTERNAL_END_POINT;
//        TransactionRequest transactionRequest = AppUtils.transactionRequestInstance();
//
//        String body = CommonAutomationUtils.stringToJson(transactionRequest);
//        String updatedBody = CommonAutomationUtils.modifyJson(body, "DELETE", "authentication.channel", null);
//
//        if (isExternalAuth) {
//            endpoint = "";
//            AppUtils.updateSourceAndMerchantIdForExternal(transactionRequest);
//        }
//
//        if (isExternalAuth)
//            updatedBody = AppUtils.removeCardAndRoutingFromRequest(updatedBody);
//        Response response = AuthenticationTransactionRequest.postRequest(updatedBody, endpoint);
//        String textResponse = response.getBody().asString();
//        JsonNode jsonResponse = CommonAutomationUtils.convertToJson(textResponse);
//
//        CommonAutomationUtils.verifyCommonResponseFailedValidation(jsonResponse, response.getStatusCode(), HttpStatus.SC_BAD_REQUEST, AppConstants.REQUIRED_FIELD_INVALID_CODE, AppConstants.REQUIRED_FIELD_INVALID_ERROR);
//    }
//
//    @Test(groups = {"MC:Authentication", "internal_auth", "external_auth", "Abdul Rehman", "SECTION:Authentication Validation", "SC:Authentication"}, description = "Validates transaction authentication with an authentication object without purpose", priority = 203)
//    public void transactionAuthenticationAuthenticationObjectWithoutPurposeCase() {
//
//        String endpoint = AppConstants.INTERNAL_END_POINT;
//        TransactionRequest transactionRequest = AppUtils.transactionRequestInstance();
//
//        if (isExternalAuth) {
//            endpoint = "";
//            AppUtils.updateSourceAndMerchantIdForExternal(transactionRequest);
//        }
//
//        String body = CommonAutomationUtils.stringToJson(transactionRequest);
//        String updatedBody = CommonAutomationUtils.modifyJson(body, "DELETE", "authentication.purpose", null);
//
//        if (isExternalAuth)
//            updatedBody = AppUtils.removeCardAndRoutingFromRequest(updatedBody);
//        Response response = AuthenticationTransactionRequest.postRequest(updatedBody, endpoint);
//
//        CommonAutomationUtils.verifyCommonResponseSuccessValidation(response, HttpStatus.SC_OK, Map.of(AppConstants.AUTHENTICATION + "." + AppConstants.CHANNEL, AuthenticationChannel.PAYER_BROWSER));
//        CommonAutomationUtils.verifyNonEmpty(response, List.of(AppConstants.AUTHENTICATION + "." + AppConstants.URL));
//    }
//
//    @Test(groups = {"MC:Authentication", "internal_auth", "external_auth", "Abdul Rehman", "SECTION:Authentication Channel Validation", "SC:Authentication Channel"}, description = "Validates transaction authentication with a valid authentication channel", priority = 204)
//    public void transactionAuthenticationValidAuthenticationChannelCase() {
//
//        String endpoint = AppConstants.INTERNAL_END_POINT;
//        TransactionRequest transactionRequest = AppUtils.transactionRequestInstance();
//
//        if (isExternalAuth) {
//            endpoint = "";
//            AppUtils.updateSourceAndMerchantIdForExternal(transactionRequest);
//        }
//
//        String body = CommonAutomationUtils.stringToJson(transactionRequest);
//        if (isExternalAuth)
//            body = AppUtils.removeCardAndRoutingFromRequest(body);
//        Response response = AuthenticationTransactionRequest.postRequest(body, endpoint);
//
//        CommonAutomationUtils.verifyCommonResponseSuccessValidation(response, HttpStatus.SC_OK, Map.of(AppConstants.AUTHENTICATION + "." + AppConstants.CHANNEL, "PAYER_BROWSER"));
//        CommonAutomationUtils.verifyNonEmpty(response, List.of(AppConstants.AUTHENTICATION + "." + AppConstants.URL));
//    }
//
//    @Test(groups = {"MC:Authentication", "internal_auth", "external_auth", "Abdul Rehman", "SECTION:Authentication Channel Validation", "SC:Authentication Channel"}, description = "Validates transaction authentication with an invalid authentication channel", priority = 205)
//    public void transactionAuthenticationInvalidAuthenticationChannelCase() {
//
//        String endpoint = AppConstants.INTERNAL_END_POINT;
//        TransactionRequest transactionRequest = AppUtils.transactionRequestInstance();
//
//        if (isExternalAuth) {
//            endpoint = "";
//            AppUtils.updateSourceAndMerchantIdForExternal(transactionRequest);
//        }
//
//        String body = CommonAutomationUtils.stringToJson(transactionRequest);
//        String updatedBody = CommonAutomationUtils.modifyJson(body, "MODIFY", AppConstants.AUTHENTICATION + "." + AppConstants.CHANNEL, "invalid_authentication_channel");
//
//        if (isExternalAuth)
//            updatedBody = AppUtils.removeCardAndRoutingFromRequest(updatedBody);
//        Response response = AuthenticationTransactionRequest.postRequest(updatedBody, endpoint);
//        String textResponse = response.getBody().asString();
//        JsonNode jsonResponse = CommonAutomationUtils.convertToJson(textResponse);
//
//        CommonAutomationUtils.verifyCommonResponseFailedValidation(jsonResponse, response.getStatusCode(), HttpStatus.SC_BAD_REQUEST, AppConstants.INVALID_DATA_CODE, AppConstants.INVALID_DATA_ERROR);
//    }
//
//    @Test(groups = {"MC:Authentication", "internal_auth", "external_auth", "Abdul Rehman", "SECTION:Authentication Channel Validation", "SC:Authentication Channel"}, description = "Validates transaction authentication with a numeric authentication channel", priority = 206)
//    public void transactionAuthenticationNumericAuthenticationChannelCase() {
//
//        String endpoint = AppConstants.INTERNAL_END_POINT;
//        TransactionRequest transactionRequest = AppUtils.transactionRequestInstance();
//
//        if (isExternalAuth) {
//            endpoint = "";
//            AppUtils.updateSourceAndMerchantIdForExternal(transactionRequest);
//        }
//
//        String body = CommonAutomationUtils.stringToJson(transactionRequest);
//        String updatedBody = CommonAutomationUtils.modifyJson(body, "MODIFY", AppConstants.AUTHENTICATION + "." + AppConstants.CHANNEL, 123456);
//
//        if (isExternalAuth)
//            updatedBody = AppUtils.removeCardAndRoutingFromRequest(updatedBody);
//        Response response = AuthenticationTransactionRequest.postRequest(updatedBody, endpoint);
//        String textResponse = response.getBody().asString();
//        JsonNode jsonResponse = CommonAutomationUtils.convertToJson(textResponse);
//
//        CommonAutomationUtils.verifyCommonResponseFailedValidation(jsonResponse, response.getStatusCode(), HttpStatus.SC_BAD_REQUEST, AppConstants.INVALID_DATA_CODE, AppConstants.INVALID_DATA_ERROR);
//    }
//
//    @Test(groups = {"MC:Authentication", "internal_auth", "external_auth", "Abdul Rehman", "SECTION:Authentication Channel Validation", "SC:Authentication Channel"}, description = "Validates transaction authentication with an empty authentication channel", priority = 207)
//    public void transactionAuthenticationEmptyAuthenticationChannelCase() {
//
//        String endpoint = AppConstants.INTERNAL_END_POINT;
//        TransactionRequest transactionRequest = AppUtils.transactionRequestInstance();
//
//        if (isExternalAuth) {
//            endpoint = "";
//            AppUtils.updateSourceAndMerchantIdForExternal(transactionRequest);
//        }
//
//        String body = CommonAutomationUtils.stringToJson(transactionRequest);
//        String updatedBody = CommonAutomationUtils.modifyJson(body, "MODIFY", AppConstants.AUTHENTICATION + "." + AppConstants.CHANNEL, "");
//
//        if (isExternalAuth)
//            updatedBody = AppUtils.removeCardAndRoutingFromRequest(updatedBody);
//        Response response = AuthenticationTransactionRequest.postRequest(updatedBody, endpoint);
//        String textResponse = response.getBody().asString();
//        JsonNode jsonResponse = CommonAutomationUtils.convertToJson(textResponse);
//
//        CommonAutomationUtils.verifyCommonResponseFailedValidation(jsonResponse, response.getStatusCode(), HttpStatus.SC_BAD_REQUEST, AppConstants.INVALID_DATA_CODE, AppConstants.INVALID_DATA_ERROR);
//    }
//
//    @Test(groups = {"MC:Authentication", "internal_auth", "external_auth", "Abdul Rehman", "SECTION:Authentication Channel Validation", "SC:Authentication Channel"}, description = "Validates transaction authentication with no authentication channel", priority = 208)
//    public void transactionAuthenticationNoAuthenticationChannelCase() {
//
//        String endpoint = AppConstants.INTERNAL_END_POINT;
//        TransactionRequest transactionRequest = AppUtils.transactionRequestInstance();
//
//        String body = CommonAutomationUtils.stringToJson(transactionRequest);
//        String updatedBody = CommonAutomationUtils.modifyJson(body, "DELETE", AppConstants.AUTHENTICATION + "." + AppConstants.CHANNEL, null);
//
//        if (isExternalAuth) {
//            endpoint = "";
//            AppUtils.updateSourceAndMerchantIdForExternal(transactionRequest);
//        }
//
//        if (isExternalAuth)
//            updatedBody = AppUtils.removeCardAndRoutingFromRequest(updatedBody);
//        Response response = AuthenticationTransactionRequest.postRequest(updatedBody, endpoint);
//        String textResponse = response.getBody().asString();
//        JsonNode jsonResponse = CommonAutomationUtils.convertToJson(textResponse);
//
//        CommonAutomationUtils.verifyCommonResponseFailedValidation(jsonResponse, response.getStatusCode(), HttpStatus.SC_BAD_REQUEST, AppConstants.REQUIRED_FIELD_INVALID_CODE, AppConstants.REQUIRED_FIELD_INVALID_ERROR);
//    }
//
//    @Test(groups = {"MC:Authentication", "internal_auth", "external_auth", "Abdul Rehman", "SECTION:Authentication Channel Validation", "SC:Authentication Channel"}, description = "Validates transaction authentication with a null authentication channel", priority = 209)
//    public void transactionAuthenticationNullAuthenticationChannelCase() {
//
//        String endpoint = AppConstants.INTERNAL_END_POINT;
//        TransactionRequest transactionRequest = AppUtils.transactionRequestInstance();
//        transactionRequest.getAuthentication().setChannel(null);
//
//        if (isExternalAuth) {
//            endpoint = "";
//            AppUtils.updateSourceAndMerchantIdForExternal(transactionRequest);
//        }
//
//        String body = CommonAutomationUtils.stringToJson(transactionRequest);
//        if (isExternalAuth)
//            body = AppUtils.removeCardAndRoutingFromRequest(body);
//        Response response = AuthenticationTransactionRequest.postRequest(body, endpoint);
//        String textResponse = response.getBody().asString();
//        JsonNode jsonResponse = CommonAutomationUtils.convertToJson(textResponse);
//
//        CommonAutomationUtils.verifyCommonResponseFailedValidation(jsonResponse, response.getStatusCode(), HttpStatus.SC_BAD_REQUEST, AppConstants.REQUIRED_FIELD_INVALID_CODE, AppConstants.REQUIRED_FIELD_INVALID_ERROR);
//    }
//
//    @Test(groups = {"MC:Authentication", "internal_auth", "external_auth", "Abdul Rehman", "SECTION:Authentication Purpose Validation", "SC:Authentication Purpose"}, description = "Validates transaction authentication with a null authentication purpose", priority = 210)
//    public void transactionAuthenticationNullAuthenticationPurposeCase() {
//
//        String endpoint = AppConstants.INTERNAL_END_POINT;
//        TransactionRequest transactionRequest = AppUtils.transactionRequestInstance();
//        transactionRequest.getAuthentication().setPurpose(null);
//
//        if (isExternalAuth) {
//            endpoint = "";
//            AppUtils.updateSourceAndMerchantIdForExternal(transactionRequest);
//        }
//
//        String body = CommonAutomationUtils.stringToJson(transactionRequest);
//        if (isExternalAuth)
//            body = AppUtils.removeCardAndRoutingFromRequest(body);
//        Response response = AuthenticationTransactionRequest.postRequest(body, endpoint);
//
//        CommonAutomationUtils.verifyCommonResponseSuccessValidation(response, HttpStatus.SC_OK, Map.of(AppConstants.AUTHENTICATION + "." + AppConstants.CHANNEL, "PAYER_BROWSER", AppConstants.AUTHENTICATION + "." + AppConstants.PURPOSE, "CHARGE"));
//        CommonAutomationUtils.verifyNonEmpty(response, List.of(AppConstants.AUTHENTICATION + "." + AppConstants.URL));
//    }
//
//    @Test(groups = {"MC:Authentication", "internal_auth", "external_auth", "Abdul Rehman", "SECTION:Authentication Purpose Validation", "SC:Authentication Purpose"}, description = "Validates transaction authentication with a valid payment transaction purpose", priority = 211)
//    public void transactionAuthenticationValidAuthenticationPurposePaymentTransactionCase() {
//
//        String endpoint = AppConstants.INTERNAL_END_POINT;
//        TransactionRequest transactionRequest = AppUtils.transactionRequestInstance();
//
//        if (isExternalAuth) {
//            endpoint = "";
//            AppUtils.updateSourceAndMerchantIdForExternal(transactionRequest);
//        }
//
//        String body = CommonAutomationUtils.stringToJson(transactionRequest);
//        if (isExternalAuth)
//            body = AppUtils.removeCardAndRoutingFromRequest(body);
//        Response response = AuthenticationTransactionRequest.postRequest(body, endpoint);
//
//        CommonAutomationUtils.verifyCommonResponseSuccessValidation(response, HttpStatus.SC_OK, Map.of(AppConstants.AUTHENTICATION + "." + AppConstants.CHANNEL, "PAYER_BROWSER", AppConstants.AUTHENTICATION + "." + AppConstants.PURPOSE, "PAYMENT_TRANSACTION"));
//        CommonAutomationUtils.verifyNonEmpty(response, List.of(AppConstants.AUTHENTICATION + "." + AppConstants.URL));
//    }
//
//    @Test(groups = {"MC:Authentication", "internal_auth", "external_auth", "Abdul Rehman", "SECTION:Authentication Purpose Validation", "SC:Authentication Purpose"}, description = "Validates transaction authentication with a valid charge purpose", priority = 212)
//    public void transactionAuthenticationValidAuthenticationPurposeChargeCase() {
//
//        String endpoint = AppConstants.INTERNAL_END_POINT;
//        TransactionRequest transactionRequest = AppUtils.transactionRequestInstance();
//        transactionRequest.getAuthentication().setPurpose(AuthenticationPurpose.CHARGE);
//
//        if (isExternalAuth) {
//            endpoint = "";
//            AppUtils.updateSourceAndMerchantIdForExternal(transactionRequest);
//        }
//
//        String body = CommonAutomationUtils.stringToJson(transactionRequest);
//        if (isExternalAuth)
//            body = AppUtils.removeCardAndRoutingFromRequest(body);
//        Response response = AuthenticationTransactionRequest.postRequest(body, endpoint);
//
//        CommonAutomationUtils.verifyCommonResponseSuccessValidation(response, HttpStatus.SC_OK, Map.of(AppConstants.AUTHENTICATION + "." + AppConstants.CHANNEL, "PAYER_BROWSER", AppConstants.AUTHENTICATION + "." + AppConstants.PURPOSE, "CHARGE"));
//        CommonAutomationUtils.verifyNonEmpty(response, List.of(AppConstants.AUTHENTICATION + "." + AppConstants.URL));
//    }
//
//    @Test(groups = {"MC:Authentication", "internal_auth", "external_auth", "Abdul Rehman", "SECTION:Authentication Purpose Validation", "SC:Authentication Purpose"}, description = "Validates transaction authentication with a valid authorize purpose", priority = 213)
//    public void transactionAuthenticationValidAuthenticationPurposeAuthorizeCase() {
//
//        String endpoint = AppConstants.INTERNAL_END_POINT;
//        TransactionRequest transactionRequest = AppUtils.transactionRequestInstance();
//        transactionRequest.getAuthentication().setPurpose(AuthenticationPurpose.AUTHORIZE);
//
//        if (isExternalAuth) {
//            endpoint = "";
//            AppUtils.updateSourceAndMerchantIdForExternal(transactionRequest);
//        }
//
//        String body = CommonAutomationUtils.stringToJson(transactionRequest);
//        if (isExternalAuth)
//            body = AppUtils.removeCardAndRoutingFromRequest(body);
//        Response response = AuthenticationTransactionRequest.postRequest(body, endpoint);
//
//        CommonAutomationUtils.verifyCommonResponseSuccessValidation(response, HttpStatus.SC_OK, Map.of(AppConstants.AUTHENTICATION + "." + AppConstants.CHANNEL, "PAYER_BROWSER", AppConstants.AUTHENTICATION + "." + AppConstants.PURPOSE, "AUTHORIZE"));
//        CommonAutomationUtils.verifyNonEmpty(response, List.of(AppConstants.AUTHENTICATION + "." + AppConstants.URL));
//    }
//
//    @Test(groups = {"MC:Authentication", "internal_auth", "external_auth", "Abdul Rehman", "SECTION:Authentication Purpose Validation", "SC:Authentication Purpose"}, description = "Validates transaction authentication with a valid save token purpose", priority = 214)
//    public void transactionAuthenticationValidAuthenticationPurposeSaveTokenCase() {
//
//        String endpoint = AppConstants.INTERNAL_END_POINT;
//        TransactionRequest transactionRequest = AppUtils.transactionRequestInstance();
//        transactionRequest.getAuthentication().setPurpose(AuthenticationPurpose.SAVE_TOKEN);
//
//        if (isExternalAuth) {
//            endpoint = "";
//            AppUtils.updateSourceAndMerchantIdForExternal(transactionRequest);
//        }
//
//        String body = CommonAutomationUtils.stringToJson(transactionRequest);
//        if (isExternalAuth)
//            body = AppUtils.removeCardAndRoutingFromRequest(body);
//        Response response = AuthenticationTransactionRequest.postRequest(body, endpoint);
//
//        CommonAutomationUtils.verifyCommonResponseSuccessValidation(response, HttpStatus.SC_OK, Map.of(AppConstants.AUTHENTICATION + "." + AppConstants.CHANNEL, "PAYER_BROWSER", AppConstants.AUTHENTICATION + "." + AppConstants.PURPOSE, "SAVE_TOKEN"));
//        CommonAutomationUtils.verifyNonEmpty(response, List.of(AppConstants.AUTHENTICATION + "." + AppConstants.URL));
//    }
//
//    @Test(groups = {"MC:Authentication", "internal_auth", "external_auth", "Abdul Rehman", "SECTION:Authentication Purpose Validation", "SC:Authentication Purpose"}, description = "Validates transaction authentication with a valid channel but invalid purpose", priority = 215)
//    public void transactionAuthenticationAuthenticationValidChannelInvalidPurposeCase() {
//
//        String endpoint = AppConstants.INTERNAL_END_POINT;
//        TransactionRequest transactionRequest = AppUtils.transactionRequestInstance();
//        transactionRequest.getAuthentication().setPurpose(AuthenticationPurpose.SAVE_TOKEN);
//
//        if (isExternalAuth) {
//            endpoint = "";
//            AppUtils.updateSourceAndMerchantIdForExternal(transactionRequest);
//        }
//
//        String body = CommonAutomationUtils.stringToJson(transactionRequest);
//        String updatedBody = CommonAutomationUtils.modifyJson(body, "MODIFY", AppConstants.AUTHENTICATION + "." + AppConstants.PURPOSE, "invalid_authentication_purpose");
//
//        if (isExternalAuth)
//            updatedBody = AppUtils.removeCardAndRoutingFromRequest(updatedBody);
//        Response response = AuthenticationTransactionRequest.postRequest(updatedBody, endpoint);
//
//        CommonAutomationUtils.verifyCommonResponseSuccessValidation(response, HttpStatus.SC_OK, Map.of(AppConstants.AUTHENTICATION + "." + AppConstants.CHANNEL, "PAYER_BROWSER", AppConstants.AUTHENTICATION + "." + AppConstants.PURPOSE, "CHARGE"));
//        CommonAutomationUtils.verifyNonEmpty(response, List.of(AppConstants.AUTHENTICATION + "." + AppConstants.URL));
//    }
//
//    @Test(groups = {"MC:Authentication", "internal_auth", "external_auth", "Abdul Rehman", "SECTION:Merchant Validation", "SC:Merchant"}, description = "Validates transaction authentication with a null merchant object", priority = 216)
//    public void transactionAuthenticationNullMerchantCase() {
//
//        String endpoint = AppConstants.INTERNAL_END_POINT;
//        TransactionRequest transactionRequest = AppUtils.transactionRequestInstance();
//        transactionRequest.setMerchant(null);
//
//        if (isExternalAuth) {
//            endpoint = "";
//            AppUtils.updateSourceAndMerchantIdForExternal(transactionRequest);
//        }
//
//        String body = CommonAutomationUtils.stringToJson(transactionRequest);
//        if (isExternalAuth)
//            body = AppUtils.removeCardAndRoutingFromRequest(body);
//        Response response = AuthenticationTransactionRequest.postRequest(body, endpoint);
//
//        CommonAutomationUtils.verifyCommonResponseSuccessValidation(response, HttpStatus.SC_OK);
//    }
//
//    @Test(groups = {"MC:Authentication", "internal_auth", "external_auth", "Abdul Rehman", "SECTION:Merchant Id Validation", "SC:Merchant Id"}, description = "Validates transaction authentication with a valid merchant ID", priority = 217)
//    public void transactionAuthenticationValidMerchantIdCase() {
//
//        String endpoint = AppConstants.INTERNAL_END_POINT;
//        TransactionRequest transactionRequest = AppUtils.transactionRequestInstance();
//
//        if (isExternalAuth) {
//            endpoint = "";
//            AppUtils.updateSourceAndMerchantIdForExternal(transactionRequest);
//        }
//
//        String body = CommonAutomationUtils.stringToJson(transactionRequest);
//        if (isExternalAuth)
//            body = AppUtils.removeCardAndRoutingFromRequest(body);
//        Response response = AuthenticationTransactionRequest.postRequest(body, endpoint);
//
//        CommonAutomationUtils.verifyCommonResponseSuccessValidation(response, HttpStatus.SC_OK, Map.of(AppConstants.MERCHANT + "." + AppConstants.ID, "1124340"));
//    }
//
//    @Test(groups = {"MC:Authentication", "internal_auth", "external_auth", "Abdul Rehman", "SECTION:Merchant Id Validation", "SC:Merchant Id"}, description = "Validates transaction authentication with an invalid merchant ID", priority = 218)
//    public void transactionAuthenticationInvalidMerchantIdCase() {
//
//        String endpoint = AppConstants.INTERNAL_END_POINT;
//        TransactionRequest transactionRequest = AppUtils.transactionRequestInstance();
//        transactionRequest.getMerchant().setId("invalid_merchant_id");
//
//        if (isExternalAuth) {
//            endpoint = "";
//            AppUtils.updateSourceAndMerchantIdForExternal(transactionRequest);
//        }
//
//        String body = CommonAutomationUtils.stringToJson(transactionRequest);
//        if (isExternalAuth)
//            body = AppUtils.removeCardAndRoutingFromRequest(body);
//        Response response = AuthenticationTransactionRequest.postRequest(body, endpoint);
//
//        if (isExternalAuth)
//            CommonAutomationUtils.verifyCommonResponseSuccessValidation(response, HttpStatus.SC_OK, Map.of(AppConstants.MERCHANT + "." + AppConstants.ID, "1124340"));
//        else
//            CommonAutomationUtils.verifyCommonResponseSuccessValidation(response, HttpStatus.SC_OK, Map.of(AppConstants.MERCHANT + "." + AppConstants.ID, "invalid_merchant_id"));
//    }
//
//    @Test(groups = {"MC:Authentication", "internal_auth", "external_auth", "Abdul Rehman", "SECTION:Merchant Id Validation", "SC:Merchant Id"}, description = "Validates transaction authentication with an empty merchant ID", priority = 219)
//    public void transactionAuthenticationEmptyMerchantIdCase() {
//
//        String endpoint = AppConstants.INTERNAL_END_POINT;
//        TransactionRequest transactionRequest = AppUtils.transactionRequestInstance();
//        transactionRequest.getMerchant().setId("");
//
//        if (isExternalAuth) {
//            endpoint = "";
//            AppUtils.updateSourceAndMerchantIdForExternal(transactionRequest);
//        }
//
//        String body = CommonAutomationUtils.stringToJson(transactionRequest);
//        if (isExternalAuth)
//            body = AppUtils.removeCardAndRoutingFromRequest(body);
//        Response response = AuthenticationTransactionRequest.postRequest(body, endpoint);
//
//        if (isExternalAuth)
//            CommonAutomationUtils.verifyCommonResponseSuccessValidation(response, HttpStatus.SC_OK, Map.of(AppConstants.MERCHANT + "." + AppConstants.ID, "1124340"));
//        else
//            CommonAutomationUtils.verifyCommonResponseSuccessValidation(response, HttpStatus.SC_OK, Map.of(AppConstants.MERCHANT + "." + AppConstants.ID, ""));
//    }
//
//    @Test(groups = {"MC:Authentication", "internal_auth", "external_auth", "Abdul Rehman", "SECTION:Merchant Id Validation", "SC:Merchant Id"}, description = "Validates transaction authentication with a null merchant ID", priority = 220)
//    public void transactionAuthenticationNullMerchantIdCase() {
//
//        String endpoint = AppConstants.INTERNAL_END_POINT;
//        TransactionRequest transactionRequest = AppUtils.transactionRequestInstance();
//        transactionRequest.getMerchant().setId(null);
//
//        if (isExternalAuth) {
//            endpoint = "";
//            AppUtils.updateSourceAndMerchantIdForExternal(transactionRequest);
//        }
//
//        String body = CommonAutomationUtils.stringToJson(transactionRequest);
//        if (isExternalAuth)
//            body = AppUtils.removeCardAndRoutingFromRequest(body);
//        Response response = AuthenticationTransactionRequest.postRequest(body, endpoint);
//
//        CommonAutomationUtils.verifyCommonResponseSuccessValidation(response, HttpStatus.SC_OK);
//        if (isExternalAuth)
//            CommonAutomationUtils.verifyCommonResponseSuccessValidation(response, HttpStatus.SC_OK, Map.of(AppConstants.MERCHANT + "." + AppConstants.ID, "1124340"));
//        else
//            CommonAutomationUtils.verifyNonAvailableKey(response, List.of(AppConstants.MERCHANT + "." + AppConstants.ID));
//    }
//
//    @Test(groups = {"MC:Authentication", "internal_auth", "external_auth", "Abdul Rehman", "SECTION:Merchant Id Validation", "SC:Merchant Id"}, description = "Validates transaction authentication with no merchant ID", priority = 221)
//    public void transactionAuthenticationNoMerchantIdCase() {
//
//        String endpoint = AppConstants.INTERNAL_END_POINT;
//        TransactionRequest transactionRequest = AppUtils.transactionRequestInstance();
//
//        if (isExternalAuth) {
//            endpoint = "";
//            AppUtils.updateSourceAndMerchantIdForExternal(transactionRequest);
//        }
//
//        String body = CommonAutomationUtils.stringToJson(transactionRequest);
//        String updatedBody = CommonAutomationUtils.modifyJson(body, "DELETE", AppConstants.MERCHANT + "." + AppConstants.ID, null);
//
//        if (isExternalAuth)
//            updatedBody = AppUtils.removeCardAndRoutingFromRequest(updatedBody);
//        Response response = AuthenticationTransactionRequest.postRequest(updatedBody, endpoint);
//
//        CommonAutomationUtils.verifyCommonResponseSuccessValidation(response, HttpStatus.SC_OK);
//        if (isExternalAuth)
//            CommonAutomationUtils.verifyCommonResponseSuccessValidation(response, HttpStatus.SC_OK, Map.of(AppConstants.MERCHANT + "." + AppConstants.ID, "1124340"));
//        else
//            CommonAutomationUtils.verifyNonAvailableKey(response, List.of(AppConstants.MERCHANT + "." + AppConstants.ID));
//    }
//
//    @Test(groups = {"MC:Authentication", "internal_auth", "external_auth", "Abdul Rehman", "SECTION:Metadata Validation", "SC:Metadata"}, description = "Validates transaction authentication with a null metadata object", priority = 222)
//    public void transactionAuthenticationNullMetadataCase() {
//
//        String endpoint = AppConstants.INTERNAL_END_POINT;
//        TransactionRequest transactionRequest = AppUtils.transactionRequestInstance();
//        transactionRequest.setMetadata(null);
//
//        if (isExternalAuth) {
//            endpoint = "";
//            AppUtils.updateSourceAndMerchantIdForExternal(transactionRequest);
//        }
//
//        String body = CommonAutomationUtils.stringToJson(transactionRequest);
//        if (isExternalAuth)
//            body = AppUtils.removeCardAndRoutingFromRequest(body);
//        Response response = AuthenticationTransactionRequest.postRequest(body, endpoint);
//
//        CommonAutomationUtils.verifyCommonResponseSuccessValidation(response, HttpStatus.SC_OK);
//        CommonAutomationUtils.verifyNonAvailableKey(response, List.of(AppConstants.META_DATA));
//    }
//
//    @Test(groups = {"MC:Authentication", "internal_auth", "external_auth", "Abdul Rehman", "SECTION:Metadata Udf1 and Udf2 Validation", "SC:Metadata Udf1 and Udf2"}, description = "Validates transaction authentication with valid metadata UDF1 and UDF2", priority = 223)
//    public void transactionAuthenticationValidMetadataUdf1AndUdf2Case() {
//
//        String endpoint = AppConstants.INTERNAL_END_POINT;
//        TransactionRequest transactionRequest = AppUtils.transactionRequestInstance();
//
//        if (isExternalAuth) {
//            endpoint = "";
//            AppUtils.updateSourceAndMerchantIdForExternal(transactionRequest);
//        }
//
//        String body = CommonAutomationUtils.stringToJson(transactionRequest);
//        if (isExternalAuth)
//            body = AppUtils.removeCardAndRoutingFromRequest(body);
//        Response response = AuthenticationTransactionRequest.postRequest(body, endpoint);
//
//        CommonAutomationUtils.verifyCommonResponseSuccessValidation(response, HttpStatus.SC_OK, Map.of(AppConstants.META_DATA + "." + AppConstants.UDF1, "test1", AppConstants.META_DATA + "." + AppConstants.UDF2, "test2"));
//    }
//
//    @Test(groups = {"MC:Authentication", "internal_auth", "external_auth", "Abdul Rehman", "SECTION:Metadata Udf1 and Udf2 Validation", "SC:Metadata Udf1 and Udf2"}, description = "Validates transaction authentication with numeric metadata UDF1 and UDF2", priority = 224)
//    public void transactionAuthenticationNumericMetadataUdf1AndUdf2Case() {
//
//        String endpoint = AppConstants.INTERNAL_END_POINT;
//        TransactionRequest transactionRequest = AppUtils.transactionRequestInstance();
//
//        if (isExternalAuth) {
//            endpoint = "";
//            AppUtils.updateSourceAndMerchantIdForExternal(transactionRequest);
//        }
//
//        String body = CommonAutomationUtils.stringToJson(transactionRequest);
//        String updatedBody = CommonAutomationUtils.modifyJson(body, "MODIFY", AppConstants.META_DATA + "." + AppConstants.UDF1, 12345);
//        updatedBody = CommonAutomationUtils.modifyJson(updatedBody, "MODIFY", AppConstants.META_DATA + "." + AppConstants.UDF2, 56789);
//
//        if (isExternalAuth)
//            updatedBody = AppUtils.removeCardAndRoutingFromRequest(updatedBody);
//        Response response = AuthenticationTransactionRequest.postRequest(updatedBody, endpoint);
//
//        CommonAutomationUtils.verifyCommonResponseSuccessValidation(response, HttpStatus.SC_OK, Map.of(AppConstants.META_DATA + "." + AppConstants.UDF1, "12345", AppConstants.META_DATA + "." + AppConstants.UDF2, "56789"));
//    }
//
//    @Test(groups = {"MC:Authentication", "internal_auth", "external_auth", "Abdul Rehman", "SECTION:Metadata Udf1 and Udf2 Validation", "SC:Metadata Udf1 and Udf2"}, description = "Validates transaction authentication with no metadata UDF1 and UDF2", priority = 225)
//    public void transactionAuthenticationNoMetadataUdf1AndUdf2Case() {
//
//        String endpoint = AppConstants.INTERNAL_END_POINT;
//        TransactionRequest transactionRequest = AppUtils.transactionRequestInstance();
//
//        if (isExternalAuth) {
//            endpoint = "";
//            AppUtils.updateSourceAndMerchantIdForExternal(transactionRequest);
//        }
//
//        String body = CommonAutomationUtils.stringToJson(transactionRequest);
//        String updatedBody = CommonAutomationUtils.modifyJson(body, "DELETE", AppConstants.META_DATA + "." + AppConstants.UDF1, null);
//        updatedBody = CommonAutomationUtils.modifyJson(updatedBody, "DELETE", AppConstants.META_DATA + "." + AppConstants.UDF2, null);
//
//        if (isExternalAuth)
//            updatedBody = AppUtils.removeCardAndRoutingFromRequest(updatedBody);
//        Response response = AuthenticationTransactionRequest.postRequest(updatedBody, endpoint);
//
//        CommonAutomationUtils.verifyCommonResponseSuccessValidation(response, HttpStatus.SC_OK);
//        CommonAutomationUtils.verifyNonAvailableKey(response, List.of(AppConstants.META_DATA + "." + AppConstants.UDF1, AppConstants.META_DATA + "." + AppConstants.UDF2));
//    }
//
//    @Test(groups = {"MC:Authentication", "internal_auth", "external_auth", "Abdul Rehman", "SECTION:Airline Validation", "SC:Airline"}, description = "Validates transaction authentication with a null airline object", priority = 226)
//    public void transactionAuthenticationNullAirlineCase() {
//
//        String endpoint = AppConstants.INTERNAL_END_POINT;
//        TransactionRequest transactionRequest = AppUtils.transactionRequestInstance();
//        transactionRequest.setAirline(null);
//
//        if (isExternalAuth) {
//            endpoint = "";
//            AppUtils.updateSourceAndMerchantIdForExternal(transactionRequest);
//        }
//
//        String body = CommonAutomationUtils.stringToJson(transactionRequest);
//        if (isExternalAuth)
//            body = AppUtils.removeCardAndRoutingFromRequest(body);
//        Response response = AuthenticationTransactionRequest.postRequest(body, endpoint);
//
//        CommonAutomationUtils.verifyCommonResponseSuccessValidation(response, HttpStatus.SC_OK);
//        CommonAutomationUtils.verifyNonAvailableKey(response, List.of(AppConstants.AIRLINE));
//    }
//
//    @Test(groups = {"MC:Authentication", "internal_auth", "external_auth", "Abdul Rehman", "SECTION:Airline Validation", "SC:Airline"}, description = "Validates transaction authentication with a valid airline ID", priority = 227)
//    public void transactionAuthenticationValidAirlineIdCase() {
//
//        String endpoint = AppConstants.INTERNAL_END_POINT;
//        TransactionRequest transactionRequest = AppUtils.transactionRequestInstance();
//
//        if (isExternalAuth) {
//            endpoint = "";
//            AppUtils.updateSourceAndMerchantIdForExternal(transactionRequest);
//        }
//
//        String body = CommonAutomationUtils.stringToJson(transactionRequest);
//        if (isExternalAuth)
//            body = AppUtils.removeCardAndRoutingFromRequest(body);
//        Response response = AuthenticationTransactionRequest.postRequest(body, endpoint);
//
//        CommonAutomationUtils.verifyCommonResponseSuccessValidation(response, HttpStatus.SC_OK, Map.of(AppConstants.AIRLINE + "." + AppConstants.ID, "test_airline"));
//    }
//
//    @Test(groups = {"MC:Authentication", "internal_auth", "external_auth", "Abdul Rehman", "SECTION:Airline Validation", "SC:Airline"}, description = "Validates transaction authentication with a numeric airline ID", priority = 228)
//    public void transactionAuthenticationNumericAirlineIdCase() {
//
//        String endpoint = AppConstants.INTERNAL_END_POINT;
//        TransactionRequest transactionRequest = AppUtils.transactionRequestInstance();
//
//        if (isExternalAuth) {
//            endpoint = "";
//            AppUtils.updateSourceAndMerchantIdForExternal(transactionRequest);
//        }
//
//        String body = CommonAutomationUtils.stringToJson(transactionRequest);
//        String updatedBody = CommonAutomationUtils.modifyJson(body, "MODIFY", AppConstants.AIRLINE + "." + AppConstants.ID, 12345);
//
//        if (isExternalAuth)
//            updatedBody = AppUtils.removeCardAndRoutingFromRequest(updatedBody);
//        Response response = AuthenticationTransactionRequest.postRequest(updatedBody, endpoint);
//
//        CommonAutomationUtils.verifyCommonResponseSuccessValidation(response, HttpStatus.SC_OK, Map.of(AppConstants.AIRLINE + "." + AppConstants.ID, "12345"));
//    }
//
//    @Test(groups = {"MC:Authentication", "internal_auth", "external_auth", "Abdul Rehman", "SECTION:Airline Validation", "SC:Airline"}, description = "Validates transaction authentication with no airline ID", priority = 229)
//    public void transactionAuthenticationNoAirlineIdCase() {
//
//        String endpoint = AppConstants.INTERNAL_END_POINT;
//        TransactionRequest transactionRequest = AppUtils.transactionRequestInstance();
//
//        if (isExternalAuth) {
//            endpoint = "";
//            AppUtils.updateSourceAndMerchantIdForExternal(transactionRequest);
//        }
//
//        String body = CommonAutomationUtils.stringToJson(transactionRequest);
//        String updatedBody = CommonAutomationUtils.modifyJson(body, "DELETE", AppConstants.AIRLINE + "." + AppConstants.ID, null);
//
//        if (isExternalAuth)
//            updatedBody = AppUtils.removeCardAndRoutingFromRequest(updatedBody);
//        Response response = AuthenticationTransactionRequest.postRequest(updatedBody, endpoint);
//
//        CommonAutomationUtils.verifyCommonResponseSuccessValidation(response, HttpStatus.SC_OK);
//        CommonAutomationUtils.verifyNonAvailableKey(response, List.of(AppConstants.AIRLINE + "." + AppConstants.ID));
//    }
//
//    @Test(groups = {"MC:Authentication", "internal_auth", "external_auth", "Abdul Rehman", "SECTION:Redirect Validation", "SC:Redirect"}, description = "Validates transaction authentication with a null redirect object", priority = 230)
//    public void transactionAuthenticationNullRedirectCase() {
//
//        String endpoint = AppConstants.INTERNAL_END_POINT;
//        TransactionRequest transactionRequest = AppUtils.transactionRequestInstance();
//        transactionRequest.setRedirect(null);
//
//        if (isExternalAuth) {
//            endpoint = "";
//            AppUtils.updateSourceAndMerchantIdForExternal(transactionRequest);
//        }
//
//        String body = CommonAutomationUtils.stringToJson(transactionRequest);
//        if (isExternalAuth)
//            body = AppUtils.removeCardAndRoutingFromRequest(body);
//        Response response = AuthenticationTransactionRequest.postRequest(body, endpoint);
//        String textResponse = response.getBody().asString();
//        JsonNode jsonResponse = CommonAutomationUtils.convertToJson(textResponse);
//
//        CommonAutomationUtils.verifyCommonResponseFailedValidation(jsonResponse, response.getStatusCode(), HttpStatus.SC_BAD_REQUEST, AppConstants.REQUIRED_FIELD_INVALID_CODE, AppConstants.REQUIRED_FIELD_INVALID_ERROR);
//    }
//
//    @Test(groups = {"MC:Authentication", "internal_auth", "external_auth", "Abdul Rehman", "SECTION:Redirect Url Validation", "SC:Redirect Url"}, description = "Validates transaction authentication with a valid redirect URL", priority = 231)
//    public void transactionAuthenticationValidRedirectUrlCase() {
//
//        String endpoint = AppConstants.INTERNAL_END_POINT;
//        TransactionRequest transactionRequest = AppUtils.transactionRequestInstance();
//
//        if (isExternalAuth) {
//            endpoint = "";
//            AppUtils.updateSourceAndMerchantIdForExternal(transactionRequest);
//        }
//
//        String body = CommonAutomationUtils.stringToJson(transactionRequest);
//        if (isExternalAuth)
//            body = AppUtils.removeCardAndRoutingFromRequest(body);
//        Response response = AuthenticationTransactionRequest.postRequest(body, endpoint);
//
//        CommonAutomationUtils.verifyCommonResponseSuccessValidation(response, HttpStatus.SC_OK, Map.of(AppConstants.REDIRECT + "." + AppConstants.URL, "http://yourwebsite.com/redirect_url"));
//    }
//
//    @Test(groups = {"MC:Authentication", "internal_auth", "external_auth", "Abdul Rehman", "SECTION:Redirect Url Validation", "SC:Redirect Url"}, description = "Validates transaction authentication with an invalid redirect URL", priority = 232)
//    public void transactionAuthenticationInvalidRedirectUrlCase() {
//
//        String endpoint = AppConstants.INTERNAL_END_POINT;
//        TransactionRequest transactionRequest = AppUtils.transactionRequestInstance();
//        transactionRequest.getRedirect().setUrl("http:/");
//
//        if (isExternalAuth) {
//            endpoint = "";
//            AppUtils.updateSourceAndMerchantIdForExternal(transactionRequest);
//        }
//
//        String body = CommonAutomationUtils.stringToJson(transactionRequest);
//        if (isExternalAuth)
//            body = AppUtils.removeCardAndRoutingFromRequest(body);
//        Response response = AuthenticationTransactionRequest.postRequest(body, endpoint);
//        String textResponse = response.getBody().asString();
//        JsonNode jsonResponse = CommonAutomationUtils.convertToJson(textResponse);
//
//        CommonAutomationUtils.verifyCommonResponseFailedValidation(jsonResponse, response.getStatusCode(), HttpStatus.SC_BAD_REQUEST, AppConstants.INVALID_DATA_CODE, AppConstants.INVALID_MERCHANT_REDIRECT_URL_ERROR);
//    }
//
//    @Test(groups = {"MC:Authentication", "internal_auth", "external_auth", "Abdul Rehman", "SECTION:Redirect Url Validation", "SC:Redirect Url"}, description = "Validates transaction authentication with no redirect URL", priority = 233)
//    public void transactionAuthenticationNoRedirectUrlCase() {
//
//        String endpoint = AppConstants.INTERNAL_END_POINT;
//        TransactionRequest transactionRequest = AppUtils.transactionRequestInstance();
//
//        if (isExternalAuth) {
//            endpoint = "";
//            AppUtils.updateSourceAndMerchantIdForExternal(transactionRequest);
//        }
//
//        String body = CommonAutomationUtils.stringToJson(transactionRequest);
//        String updatedBody = CommonAutomationUtils.modifyJson(body, "DELETE", AppConstants.REDIRECT + "." + AppConstants.URL, null);
//
//        if (isExternalAuth)
//            updatedBody = AppUtils.removeCardAndRoutingFromRequest(updatedBody);
//        Response response = AuthenticationTransactionRequest.postRequest(updatedBody, endpoint);
//        String textResponse = response.getBody().asString();
//        JsonNode jsonResponse = CommonAutomationUtils.convertToJson(textResponse);
//
//        CommonAutomationUtils.verifyCommonResponseFailedValidation(jsonResponse, response.getStatusCode(), HttpStatus.SC_BAD_REQUEST, AppConstants.REQUIRED_FIELD_INVALID_CODE, AppConstants.REQUIRED_FIELD_INVALID_ERROR);
//    }
//
//    @Test(groups = {"MC:Authentication", "internal_auth", "external_auth", "Abdul Rehman", "SECTION:Redirect Url Validation", "SC:Redirect Url"}, description = "Validates transaction authentication with an empty redirect URL", priority = 234)
//    public void transactionAuthenticationEmptyRedirectUrlCase() {
//
//        String endpoint = AppConstants.INTERNAL_END_POINT;
//        TransactionRequest transactionRequest = AppUtils.transactionRequestInstance();
//        transactionRequest.getRedirect().setUrl("");
//
//        if (isExternalAuth) {
//            endpoint = "";
//            AppUtils.updateSourceAndMerchantIdForExternal(transactionRequest);
//        }
//
//        String body = CommonAutomationUtils.stringToJson(transactionRequest);
//        if (isExternalAuth)
//            body = AppUtils.removeCardAndRoutingFromRequest(body);
//        Response response = AuthenticationTransactionRequest.postRequest(body, endpoint);
//        String textResponse = response.getBody().asString();
//        JsonNode jsonResponse = CommonAutomationUtils.convertToJson(textResponse);
//
//        CommonAutomationUtils.verifyCommonResponseFailedValidation(jsonResponse, response.getStatusCode(), HttpStatus.SC_BAD_REQUEST, AppConstants.INVALID_DATA_CODE, AppConstants.INVALID_MERCHANT_REDIRECT_URL_ERROR);
//    }
//
//    @Test(groups = {"MC:Authentication", "internal_auth", "external_auth", "Abdul Rehman", "SECTION:Redirect Url Validation", "SC:Redirect Url"}, description = "Validates transaction authentication with a null redirect URL", priority = 235)
//    public void transactionAuthenticationNullRedirectUrlCase() {
//
//        String endpoint = AppConstants.INTERNAL_END_POINT;
//        TransactionRequest transactionRequest = AppUtils.transactionRequestInstance();
//        transactionRequest.getRedirect().setUrl(null);
//
//        if (isExternalAuth) {
//            endpoint = "";
//            AppUtils.updateSourceAndMerchantIdForExternal(transactionRequest);
//        }
//
//        String body = CommonAutomationUtils.stringToJson(transactionRequest);
//        if (isExternalAuth)
//            body = AppUtils.removeCardAndRoutingFromRequest(body);
//        Response response = AuthenticationTransactionRequest.postRequest(body, endpoint);
//        String textResponse = response.getBody().asString();
//        JsonNode jsonResponse = CommonAutomationUtils.convertToJson(textResponse);
//
//        CommonAutomationUtils.verifyCommonResponseFailedValidation(jsonResponse, response.getStatusCode(), HttpStatus.SC_BAD_REQUEST, AppConstants.REQUIRED_FIELD_INVALID_CODE, AppConstants.REQUIRED_FIELD_INVALID_ERROR);
//    }
//
//    @Test(groups = {"MC:Authentication", "internal_auth", "external_auth", "Abdul Rehman", "SECTION:Post Validation", "SC:Post"}, description = "Validates transaction authentication with a null post object", priority = 236)
//    public void transactionAuthenticationNullPostCase() {
//
//        String endpoint = AppConstants.INTERNAL_END_POINT;
//        TransactionRequest transactionRequest = AppUtils.transactionRequestInstance();
//        transactionRequest.setPost(null);
//
//        if (isExternalAuth) {
//            endpoint = "";
//            AppUtils.updateSourceAndMerchantIdForExternal(transactionRequest);
//        }
//
//        String body = CommonAutomationUtils.stringToJson(transactionRequest);
//        if (isExternalAuth)
//            body = AppUtils.removeCardAndRoutingFromRequest(body);
//        Response response = AuthenticationTransactionRequest.postRequest(body, endpoint);
//
//        CommonAutomationUtils.verifyCommonResponseSuccessValidation(response, HttpStatus.SC_OK);
//        CommonAutomationUtils.verifyNonAvailableKey(response, List.of(AppConstants.POST));
//    }
//
//    @Test(groups = {"MC:Authentication", "internal_auth", "external_auth", "Abdul Rehman", "SECTION:Post Url Validation", "SC:Post Url"}, description = "Validates transaction authentication with a valid post URL", priority = 237)
//    public void transactionAuthenticationValidPostUrlCase() {
//
//        String endpoint = AppConstants.INTERNAL_END_POINT;
//        TransactionRequest transactionRequest = AppUtils.transactionRequestInstance();
//
//        if (isExternalAuth) {
//            endpoint = "";
//            AppUtils.updateSourceAndMerchantIdForExternal(transactionRequest);
//        }
//
//        String body = CommonAutomationUtils.stringToJson(transactionRequest);
//        if (isExternalAuth)
//            body = AppUtils.removeCardAndRoutingFromRequest(body);
//        Response response = AuthenticationTransactionRequest.postRequest(body, endpoint);
//
//        CommonAutomationUtils.verifyCommonResponseSuccessValidation(response, HttpStatus.SC_OK, Map.of(AppConstants.POST + "." + AppConstants.URL, "http://yourwebsite.com/post_url"));
//    }
//
//    @Test(groups = {"MC:Authentication", "internal_auth", "external_auth", "Abdul Rehman", "SECTION:Post Url Validation", "SC:Post Url"}, description = "Validates transaction authentication with an invalid post URL", priority = 238)
//    public void transactionAuthenticationInvalidPostUrlCase() {
//
//        String endpoint = AppConstants.INTERNAL_END_POINT;
//        TransactionRequest transactionRequest = AppUtils.transactionRequestInstance();
//        transactionRequest.getPost().setUrl("http:/");
//
//        if (isExternalAuth) {
//            endpoint = "";
//            AppUtils.updateSourceAndMerchantIdForExternal(transactionRequest);
//        }
//
//        String body = CommonAutomationUtils.stringToJson(transactionRequest);
//        if (isExternalAuth)
//            body = AppUtils.removeCardAndRoutingFromRequest(body);
//        Response response = AuthenticationTransactionRequest.postRequest(body, endpoint);
//
//        CommonAutomationUtils.verifyCommonResponseSuccessValidation(response, HttpStatus.SC_OK, Map.of(AppConstants.POST + "." + AppConstants.URL, "http:/"));
//    }
//
//    @Test(groups = {"MC:Authentication", "internal_auth", "external_auth", "Abdul Rehman", "SECTION:Post Url Validation", "SC:Post Url"}, description = "Validates transaction authentication with no post URL", priority = 239)
//    public void transactionAuthenticationNoPostUrlCase() {
//
//        String endpoint = AppConstants.INTERNAL_END_POINT;
//        TransactionRequest transactionRequest = AppUtils.transactionRequestInstance();
//
//        if (isExternalAuth) {
//            endpoint = "";
//            AppUtils.updateSourceAndMerchantIdForExternal(transactionRequest);
//        }
//
//        String body = CommonAutomationUtils.stringToJson(transactionRequest);
//        String updatedBody = CommonAutomationUtils.modifyJson(body, "DELETE", AppConstants.POST + "." + AppConstants.URL, null);
//
//        if (isExternalAuth)
//            updatedBody = AppUtils.removeCardAndRoutingFromRequest(updatedBody);
//        Response response = AuthenticationTransactionRequest.postRequest(updatedBody, endpoint);
//
//        CommonAutomationUtils.verifyCommonResponseSuccessValidation(response, HttpStatus.SC_OK);
//        CommonAutomationUtils.verifyNonAvailableKey(response, List.of(AppConstants.POST + "." + AppConstants.URL));
//    }
//
//    @Test(groups = {"MC:Authentication", "internal_auth", "external_auth", "Abdul Rehman", "SECTION:Post Url Validation", "SC:Post Url"}, description = "Validates transaction authentication with a null post URL", priority = 240)
//    public void transactionAuthenticationNullPostUrlCase() {
//
//        String endpoint = AppConstants.INTERNAL_END_POINT;
//        TransactionRequest transactionRequest = AppUtils.transactionRequestInstance();
//        transactionRequest.getPost().setUrl(null);
//
//        if (isExternalAuth) {
//            endpoint = "";
//            AppUtils.updateSourceAndMerchantIdForExternal(transactionRequest);
//        }
//
//        String body = CommonAutomationUtils.stringToJson(transactionRequest);
//        if (isExternalAuth)
//            body = AppUtils.removeCardAndRoutingFromRequest(body);
//        Response response = AuthenticationTransactionRequest.postRequest(body, endpoint);
//
//        CommonAutomationUtils.verifyCommonResponseSuccessValidation(response, HttpStatus.SC_OK);
//        CommonAutomationUtils.verifyNonAvailableKey(response, List.of(AppConstants.POST + "." + AppConstants.URL));
//    }
//
//    @Test(groups = {"MC:Authentication", "internal_auth", "external_auth", "Abdul Rehman", "SECTION:Post Url Validation", "SC:Post Url"}, description = "Validates transaction authentication with an empty post URL", priority = 241)
//    public void transactionAuthenticationEmptyPostUrlCase() {
//
//        String endpoint = AppConstants.INTERNAL_END_POINT;
//        TransactionRequest transactionRequest = AppUtils.transactionRequestInstance();
//        transactionRequest.getPost().setUrl("");
//
//        if (isExternalAuth) {
//            endpoint = "";
//            AppUtils.updateSourceAndMerchantIdForExternal(transactionRequest);
//        }
//
//        String body = CommonAutomationUtils.stringToJson(transactionRequest);
//        if (isExternalAuth)
//            body = AppUtils.removeCardAndRoutingFromRequest(body);
//        Response response = AuthenticationTransactionRequest.postRequest(body, endpoint);
//
//        CommonAutomationUtils.verifyCommonResponseSuccessValidation(response, HttpStatus.SC_OK);
//    }
//
//    @Test(groups = {"MC:Authentication", "internal_auth", "external_auth", "Abdul Rehman", "SECTION:Customer Validation", "SC:Customer"}, description = "Validates transaction authentication with a null customer object and save token purpose.", priority = 242)
//    public void transactionAuthenticationNullCustomerANDSaveTokenPurposeCase(){
//
//        String endpoint = AppConstants.INTERNAL_END_POINT;
//        TransactionRequest transactionRequest = AppUtils.transactionRequestInstance();
//        transactionRequest.setCustomer(null);
//        transactionRequest.getAuthentication().setPurpose(AuthenticationPurpose.SAVE_TOKEN);
//
//        if (isExternalAuth) {
//            endpoint = "";
//            AppUtils.updateSourceAndMerchantIdForExternal(transactionRequest);
//        }
//
//        String body = CommonAutomationUtils.stringToJson(transactionRequest);
//        if (isExternalAuth)
//            body = AppUtils.removeCardAndRoutingFromRequest(body);
//        Response response = AuthenticationTransactionRequest.postRequest(body, endpoint);
//        String textResponse = response.getBody().asString();
//        JsonNode jsonResponse = CommonAutomationUtils.convertToJson(textResponse);
//
//        CommonAutomationUtils.verifyCommonResponseFailedValidation(jsonResponse, response.getStatusCode(), HttpStatus.SC_BAD_REQUEST, AppConstants.REQUIRED_FIELD_INVALID_CODE, AppConstants.REQUIRED_FIELD_INVALID_ERROR);
//    }
//
//    @Test(groups = {"MC:Authentication", "internal_auth", "external_auth", "Abdul Rehman", "SECTION:Customer Name Validation", "SC:Customer Name"}, description = "Validates transaction authentication with a customer first name spaces trailing once.", priority = 243)
//    public void transactionAuthenticationCustomerFirstNameTrailingSpaceCase(){
//
//        String endpoint = AppConstants.INTERNAL_END_POINT;
//        TransactionRequest transactionRequest = AppUtils.transactionRequestInstance();
//        TransactionRequest.Customer customer = transactionRequest.getCustomer();
//        List<TransactionRequest.Customer.Name> nameList = customer.getName();
//        nameList.get(0).setFirstName("  Osama  ");
//
//        if (isExternalAuth) {
//            endpoint = "";
//            AppUtils.updateSourceAndMerchantIdForExternal(transactionRequest);
//        }
//
//        String body = CommonAutomationUtils.stringToJson(transactionRequest);
//        if (isExternalAuth)
//            body = AppUtils.removeCardAndRoutingFromRequest(body);
//        Response response = AuthenticationTransactionRequest.postRequest(body, endpoint);
//
//        CommonAutomationUtils.verifyCommonResponseSuccessValidation(response, HttpStatus.SC_OK, Map.of(AppConstants.CUSTOMER + "." + AppConstants.NAME + "[0]." + AppConstants.FIRST_NAME, "  Osama  "));
//    }
//
//    @Test(groups = {"MC:Authentication", "internal_auth", "external_auth", "Abdul Rehman", "SECTION:Customer Name Validation", "SC:Customer Name"}, description = "Validates transaction authentication with a customer first name full of spaces.", priority = 244)
//    public void transactionAuthenticationCustomerFirstNameOnlySpaceCase(){
//
//        String endpoint = AppConstants.INTERNAL_END_POINT;
//        TransactionRequest transactionRequest = AppUtils.transactionRequestInstance();
//        TransactionRequest.Customer customer = transactionRequest.getCustomer();
//        List<TransactionRequest.Customer.Name> nameList = customer.getName();
//        nameList.get(0).setFirstName("     ");
//
//        if (isExternalAuth) {
//            endpoint = "";
//            AppUtils.updateSourceAndMerchantIdForExternal(transactionRequest);
//        }
//
//        String body = CommonAutomationUtils.stringToJson(transactionRequest);
//        if (isExternalAuth)
//            body = AppUtils.removeCardAndRoutingFromRequest(body);
//        Response response = AuthenticationTransactionRequest.postRequest(body, endpoint);
//        String textResponse = response.getBody().asString();
//        JsonNode jsonResponse = CommonAutomationUtils.convertToJson(textResponse);
//
//        CommonAutomationUtils.verifyCommonResponseFailedValidation(jsonResponse, response.getStatusCode(), HttpStatus.SC_BAD_REQUEST, AppConstants.REQUIRED_INPUT_INVALID_CODE, AppConstants.REQUIRED_INPUT_INVALID_ERROR);
//    }

}
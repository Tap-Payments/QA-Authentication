package com.tappayments.automation.qaauthentication.cases;

import com.fasterxml.jackson.databind.JsonNode;
import com.tappayments.automation.qaauthentication.model.TransactionRequest;
import com.tappayments.automation.qaauthentication.requests.AuthenticationTransactionRequest;
import com.tappayments.automation.qaauthentication.utils.AppConstants;
import com.tappayments.automation.qaauthentication.utils.AppUtils;
import com.tappayments.automation.utils.CommonAutomationUtils;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Map;

public class CreateTransactionAuthenticationInternalCases {

    @Test(groups = {"MC:Authentication", "Abdul Rehman", "SECTION:Source Validation", "SC:Source"}, description = "Validates transaction authentication with a null source.", priority = 140)
    public void transactionAuthenticationNullSourceCase() {

        TransactionRequest transactionRequest = AppUtils.transactionRequestInstance();
        transactionRequest.setSource(null);

        String body = CommonAutomationUtils.stringToJson(transactionRequest);
        Response response = AuthenticationTransactionRequest.postRequest(body, AppConstants.INTERNAL_END_POINT);
        String textResponse = response.getBody().asString();
        JsonNode jsonResponse = CommonAutomationUtils.convertToJson(textResponse);

        CommonAutomationUtils.verifyCommonResponseFailedValidation(jsonResponse, response.getStatusCode(), HttpStatus.SC_BAD_REQUEST, AppConstants.INVALID_DATA_CODE, AppConstants.INVALID_DATA_ERROR);
    }

    @Test(groups = {"MC:Authentication", "Abdul Rehman", "SECTION:Source Id Validation", "SC:Source Id"}, description = "Validates transaction authentication with a valid source ID", priority = 141)
    public void transactionAuthenticationValidSourceIdCase() {

        TransactionRequest transactionRequest = AppUtils.transactionRequestInstance();

        String body = CommonAutomationUtils.stringToJson(transactionRequest);
        Response response = AuthenticationTransactionRequest.postRequest(body, AppConstants.INTERNAL_END_POINT);

        CommonAutomationUtils.verifyCommonResponseSuccessValidation(response, HttpStatus.SC_OK, Map.of(AppConstants.SOURCE + "." + AppConstants.ID, transactionRequest.getSource().getId()));
    }
//
//    @Test(groups = {"MC:Authentication", "Abdul Rehman", "SECTION:Source Id Validation", "SC:Source Id"}, description = "Validates transaction authentication with a null source ID", priority = 142)
//    public void transactionAuthenticationNullSourceIdCase() {
//
//        TransactionRequest transactionRequest = AppUtils.transactionRequestInstance();
//        transactionRequest.getSource().setId(null);
//
//        String body = CommonAutomationUtils.stringToJson(transactionRequest);
//        Response response = AuthenticationTransactionRequest.postRequest(body, AppConstants.INTERNAL_END_POINT);
//        String textResponse = response.getBody().asString();
//        JsonNode jsonResponse = CommonAutomationUtils.convertToJson(textResponse);
//
//        CommonAutomationUtils.verifyCommonResponseFailedValidation(jsonResponse, response.getStatusCode(), HttpStatus.SC_BAD_REQUEST, AppConstants.REQUIRED_FIELD_INVALID_CODE, AppConstants.REQUIRED_FIELD_INVALID_ERROR);
//    }
//
//    @Test(groups = {"MC:Authentication", "Abdul Rehman", "SECTION:Source Id Validation", "SC:Source Id"}, description = "Validates transaction authentication with an empty source ID", priority = 143)
//    public void transactionAuthenticationEmptySourceIdCase() {
//
//        TransactionRequest transactionRequest = AppUtils.transactionRequestInstance();
//
//        String body = CommonAutomationUtils.stringToJson(transactionRequest);
//        String updatedBody = CommonAutomationUtils.modifyJson(body, "MODIFY", "source.id", "");
//        Response response = AuthenticationTransactionRequest.postRequest(updatedBody, AppConstants.INTERNAL_END_POINT);
//        String textResponse = response.getBody().asString();
//        JsonNode jsonResponse = CommonAutomationUtils.convertToJson(textResponse);
//
//        CommonAutomationUtils.verifyCommonResponseFailedDescriptionValidation(jsonResponse, response.getStatusCode(), HttpStatus.SC_BAD_REQUEST, AppConstants.INVALID_EMPTY_TOKEN_ID_CODE, AppConstants.INVALID_EMPTY_TOKEN_ID_DESCRIPTION);
//    }
//
//    @Test(groups = {"MC:Authentication", "Abdul Rehman", "SECTION:Source Id Validation", "SC:Source Id"}, description = "Validates transaction authentication with an invalid source ID", priority = 144)
//    public void transactionAuthenticationInvalidSourceIdCase() {
//
//        TransactionRequest transactionRequest = AppUtils.transactionRequestInstance();
//        transactionRequest.getSource().setId("invalid_source_id");
//
//        String body = CommonAutomationUtils.stringToJson(transactionRequest);
//        Response response = AuthenticationTransactionRequest.postRequest(body, AppConstants.INTERNAL_END_POINT);
//        String textResponse = response.getBody().asString();
//        JsonNode jsonResponse = CommonAutomationUtils.convertToJson(textResponse);
//
//        CommonAutomationUtils.verifyCommonResponseFailedDescriptionValidation(jsonResponse, response.getStatusCode(), HttpStatus.SC_BAD_REQUEST, AppConstants.INVALID_TOKEN_ID_CODE, AppConstants.INVALID_TOKEN_ID_DESCRIPTION);
//    }
//
//    @Test(groups = {"MC:Authentication", "Abdul Rehman", "SECTION:Source Card First Six Validation", "SC:Source Card First Six"}, description = "Validates transaction authentication with a valid source card first six digits", priority = 145)
//    public void transactionAuthenticationValidSourceCardFirstSixCase() {
//
//        TransactionRequest transactionRequest = AppUtils.transactionRequestInstance();
//        transactionRequest.getSource().getCard().setFirstSix("484783");
//
//        String body = CommonAutomationUtils.stringToJson(transactionRequest);
//        Response response = AuthenticationTransactionRequest.postRequest(body, AppConstants.INTERNAL_END_POINT);
//
//        CommonAutomationUtils.verifyCommonResponseSuccessValidation(response, HttpStatus.SC_OK, Map.of(AppConstants.CARD + "." + AppConstants.FIRST_SIX, "484783"));
//    }
//
//    @Test(groups = {"MC:Authentication", "Abdul Rehman", "SECTION:Source Card First Six Validation", "SC:Source Card First Six"}, description = "Validates transaction authentication with less than six digits in source card first six", priority = 146)
//    public void transactionAuthenticationLessSixDigitSourceCardFirstSixCase() {
//
//        TransactionRequest transactionRequest = AppUtils.transactionRequestInstance();
//        transactionRequest.getSource().getCard().setFirstSix("4847");
//
//        String body = CommonAutomationUtils.stringToJson(transactionRequest);
//        Response response = AuthenticationTransactionRequest.postRequest(body, AppConstants.INTERNAL_END_POINT);
//
//        CommonAutomationUtils.verifyCommonResponseSuccessValidation(response, HttpStatus.SC_OK, Map.of(AppConstants.CARD + "." + AppConstants.FIRST_SIX, "4847"));
//    }
//
//    @Test(groups = {"MC:Authentication", "Abdul Rehman", "SECTION:Source Card First Six Validation", "SC:Source Card First Six"}, description = "Validates transaction authentication with string value in source card first six digits", priority = 147)
//    public void transactionAuthenticationStringSourceCardFirstSixCase() {
//
//        TransactionRequest transactionRequest = AppUtils.transactionRequestInstance();
//        transactionRequest.getSource().getCard().setFirstSix("abcdef");
//
//        String body = CommonAutomationUtils.stringToJson(transactionRequest);
//        Response response = AuthenticationTransactionRequest.postRequest(body, AppConstants.INTERNAL_END_POINT);
//        String textResponse = response.getBody().asString();
//        JsonNode jsonResponse = CommonAutomationUtils.convertToJson(textResponse);
//
//        CommonAutomationUtils.verifyCommonResponseFailedValidation(jsonResponse, response.getStatusCode(), HttpStatus.SC_BAD_REQUEST, AppConstants.INVALID_DATA_CODE, AppConstants.INVALID_DATA_ERROR);
//    }
//
//    @Test(groups = {"MC:Authentication", "Abdul Rehman", "SECTION:Source Card First Six Validation", "SC:Source Card First Six"}, description = "Validates transaction authentication with leading zeroes in source card first six digits", priority = 148)
//    public void transactionAuthenticationLeadingZeroSourceCardFirstSixCase() {
//
//        TransactionRequest transactionRequest = AppUtils.transactionRequestInstance();
//        transactionRequest.getSource().getCard().setFirstSix("000000");
//
//        String body = CommonAutomationUtils.stringToJson(transactionRequest);
//        Response response = AuthenticationTransactionRequest.postRequest(body, AppConstants.INTERNAL_END_POINT);
//
//        CommonAutomationUtils.verifyCommonResponseSuccessValidation(response, HttpStatus.SC_OK, Map.of(AppConstants.CARD + "." + AppConstants.FIRST_SIX, "000000"));
//    }
//
//    @Test(groups = {"MC:Authentication", "Abdul Rehman", "SECTION:Source Card First Six Validation", "SC:Source Card First Six"}, description = "Validates transaction authentication with a null source card first six digits", priority = 149)
//    public void transactionAuthenticationNullSourceCardFirstSixCase() {
//
//        TransactionRequest transactionRequest = AppUtils.transactionRequestInstance();
//        transactionRequest.getSource().getCard().setFirstSix(null);
//
//        String body = CommonAutomationUtils.stringToJson(transactionRequest);
//        Response response = AuthenticationTransactionRequest.postRequest(body, AppConstants.INTERNAL_END_POINT);
//        String textResponse = response.getBody().asString();
//        JsonNode jsonResponse = CommonAutomationUtils.convertToJson(textResponse);
//
//        CommonAutomationUtils.verifyCommonResponseFailedValidation(jsonResponse, response.getStatusCode(), HttpStatus.SC_BAD_REQUEST, AppConstants.INVALID_DATA_CODE, AppConstants.INVALID_DATA_ERROR);
//    }
//
//    @Test(groups = {"MC:Authentication", "Abdul Rehman", "SECTION:Source Card First Six Validation", "SC:Source Card First Six"}, description = "Validates transaction authentication with an empty source card first six digits", priority = 150)
//    public void transactionAuthenticationEmptySourceCardFirstSixCase() {
//
//        TransactionRequest transactionRequest = AppUtils.transactionRequestInstance();
//        transactionRequest.getSource().getCard().setFirstSix("");
//
//        String body = CommonAutomationUtils.stringToJson(transactionRequest);
//        Response response = AuthenticationTransactionRequest.postRequest(body, AppConstants.INTERNAL_END_POINT);
//        String textResponse = response.getBody().asString();
//        JsonNode jsonResponse = CommonAutomationUtils.convertToJson(textResponse);
//
//        CommonAutomationUtils.verifyCommonResponseFailedValidation(jsonResponse, response.getStatusCode(), HttpStatus.SC_BAD_REQUEST, AppConstants.INVALID_DATA_CODE, AppConstants.INVALID_DATA_ERROR);
//    }
//
//    @Test(groups = {"MC:Authentication", "Abdul Rehman", "SECTION:Source Card First Six Validation", "SC:Source Card First Six"}, description = "Validates transaction authentication with no source card first six digits", priority = 151)
//    public void transactionAuthenticationNoSourceCardFirstSixCase() {
//
//        TransactionRequest transactionRequest = AppUtils.transactionRequestInstance();
//        transactionRequest.getSource().getCard().setFirstSix("");
//
//        String body = CommonAutomationUtils.stringToJson(transactionRequest);
//        String updatedBody = CommonAutomationUtils.modifyJson(body, "DELETE", AppConstants.SOURCE + "." + AppConstants.CARD + "." + AppConstants.FIRST_SIX, null);
//        Response response = AuthenticationTransactionRequest.postRequest(updatedBody, AppConstants.INTERNAL_END_POINT);
//        String textResponse = response.getBody().asString();
//        JsonNode jsonResponse = CommonAutomationUtils.convertToJson(textResponse);
//
//        CommonAutomationUtils.verifyCommonResponseFailedValidation(jsonResponse, response.getStatusCode(), HttpStatus.SC_BAD_REQUEST, AppConstants.INVALID_DATA_CODE, AppConstants.INVALID_DATA_ERROR);
//    }
//
//    @Test(groups = {"MC:Authentication", "Abdul Rehman", "SECTION:Source Card Scheme Validation", "SC:Source Card Scheme"}, description = "Validates transaction authentication with a valid source card scheme", priority = 152)
//    public void transactionAuthenticationValidSourceCardSchemeCase() {
//
//        TransactionRequest transactionRequest = AppUtils.transactionRequestInstance();
//        transactionRequest.getSource().getCard().setScheme("MADA");
//
//        String body = CommonAutomationUtils.stringToJson(transactionRequest);
//        Response response = AuthenticationTransactionRequest.postRequest(body, AppConstants.INTERNAL_END_POINT);
//
//        CommonAutomationUtils.verifyCommonResponseSuccessValidation(response, HttpStatus.SC_OK, Map.of(AppConstants.CARD + "." + AppConstants.SCHEME, "MADA"));
//    }
//
//    @Test(groups = {"MC:Authentication", "Abdul Rehman", "SECTION:Source Card Scheme Validation", "SC:Source Card Scheme"}, description = "Validates transaction authentication with an invalid source card scheme", priority = 153)
//    public void transactionAuthenticationInvalidSourceCardSchemeCase() {
//
//        TransactionRequest transactionRequest = AppUtils.transactionRequestInstance();
//        transactionRequest.getSource().getCard().setScheme("invalid_scheme");
//
//        String body = CommonAutomationUtils.stringToJson(transactionRequest);
//        Response response = AuthenticationTransactionRequest.postRequest(body, AppConstants.INTERNAL_END_POINT);
//        String textResponse = response.getBody().asString();
//        JsonNode jsonResponse = CommonAutomationUtils.convertToJson(textResponse);
//
//        CommonAutomationUtils.verifyCommonResponseFailedValidation(jsonResponse, response.getStatusCode(), HttpStatus.SC_BAD_REQUEST, AppConstants.INVALID_DATA_CODE, AppConstants.INVALID_DATA_ERROR);
//    }
//
//    @Test(groups = {"MC:Authentication", "Abdul Rehman", "SECTION:Source Card Scheme Validation", "SC:Source Card Scheme"}, description = "Validates transaction authentication with a null source card scheme", priority = 154)
//    public void transactionAuthenticationNullSourceCardSchemeCase() {
//
//        TransactionRequest transactionRequest = AppUtils.transactionRequestInstance();
//        transactionRequest.getSource().getCard().setScheme(null);
//
//        String body = CommonAutomationUtils.stringToJson(transactionRequest);
//        Response response = AuthenticationTransactionRequest.postRequest(body, AppConstants.INTERNAL_END_POINT);
//        String textResponse = response.getBody().asString();
//        JsonNode jsonResponse = CommonAutomationUtils.convertToJson(textResponse);
//
//        CommonAutomationUtils.verifyCommonResponseFailedValidation(jsonResponse, response.getStatusCode(), HttpStatus.SC_BAD_REQUEST, AppConstants.INVALID_DATA_CODE, AppConstants.INVALID_DATA_ERROR);
//    }
//
//    @Test(groups = {"MC:Authentication", "Abdul Rehman", "SECTION:Source Card Scheme Validation", "SC:Source Card Scheme"}, description = "Validates transaction authentication with an empty source card scheme", priority = 155)
//    public void transactionAuthenticationEmptySourceCardSchemeCase() {
//
//        TransactionRequest transactionRequest = AppUtils.transactionRequestInstance();
//        transactionRequest.getSource().getCard().setScheme("");
//
//        String body = CommonAutomationUtils.stringToJson(transactionRequest);
//        Response response = AuthenticationTransactionRequest.postRequest(body, AppConstants.INTERNAL_END_POINT);
//
//        CommonAutomationUtils.verifyCommonResponseSuccessValidation(response, HttpStatus.SC_OK, Map.of(AppConstants.CARD + "." + AppConstants.SCHEME, ""));
//    }
//
//    @Test(groups = {"MC:Authentication", "Abdul Rehman", "SECTION:Source Card Scheme Validation", "SC:Source Card Scheme"}, description = "Validates transaction authentication with no source card scheme", priority = 156)
//    public void transactionAuthenticationNoSourceCardSchemeCase() {
//
//        TransactionRequest transactionRequest = AppUtils.transactionRequestInstance();
//
//        String body = CommonAutomationUtils.stringToJson(transactionRequest);
//        String updatedBody = CommonAutomationUtils.modifyJson(body, "DELETE", AppConstants.SOURCE + "." + AppConstants.CARD + "." + AppConstants.SCHEME, null);
//        Response response = AuthenticationTransactionRequest.postRequest(updatedBody, AppConstants.INTERNAL_END_POINT);
//
//        CommonAutomationUtils.verifyCommonResponseSuccessValidation(response, HttpStatus.SC_OK);
//    }
//
//    @Test(groups = {"MC:Authentication", "Abdul Rehman", "SECTION:Source Card Brand Validation", "SC:Source Card Brand"}, description = "Validates transaction authentication with a valid source card brand", priority = 157)
//    public void transactionAuthenticationValidSourceCardBrandCase() {
//
//        TransactionRequest transactionRequest = AppUtils.transactionRequestInstance();
//        transactionRequest.getSource().getCard().setBrand("VISA");
//
//        String body = CommonAutomationUtils.stringToJson(transactionRequest);
//        Response response = AuthenticationTransactionRequest.postRequest(body, AppConstants.INTERNAL_END_POINT);
//
//        CommonAutomationUtils.verifyCommonResponseSuccessValidation(response, HttpStatus.SC_OK, Map.of(AppConstants.CARD + "." + AppConstants.BRAND, "VISA"));
//    }
//
//    @Test(groups = {"MC:Authentication", "Abdul Rehman", "SECTION:Source Card Brand Validation", "SC:Source Card Brand"}, description = "Validates transaction authentication with an invalid source card brand", priority = 158)
//    public void transactionAuthenticationInvalidSourceCardBrandCase() {
//
//        TransactionRequest transactionRequest = AppUtils.transactionRequestInstance();
//        transactionRequest.getSource().getCard().setBrand("invalid_brand");
//
//        String body = CommonAutomationUtils.stringToJson(transactionRequest);
//        Response response = AuthenticationTransactionRequest.postRequest(body, AppConstants.INTERNAL_END_POINT);
//
//        CommonAutomationUtils.verifyCommonResponseSuccessValidation(response, HttpStatus.SC_OK, Map.of(AppConstants.CARD + "." + AppConstants.BRAND, "invalid_brand"));
//    }
//
//    @Test(groups = {"MC:Authentication", "Abdul Rehman", "SECTION:Source Card Brand Validation", "SC:Source Card Brand"}, description = "Validates transaction authentication with a null source card brand", priority = 159)
//    public void transactionAuthenticationNullSourceCardBrandCase() {
//
//        TransactionRequest transactionRequest = AppUtils.transactionRequestInstance();
//        transactionRequest.getSource().getCard().setBrand(null);
//
//        String body = CommonAutomationUtils.stringToJson(transactionRequest);
//        Response response = AuthenticationTransactionRequest.postRequest(body, AppConstants.INTERNAL_END_POINT);
//        String textResponse = response.getBody().asString();
//        JsonNode jsonResponse = CommonAutomationUtils.convertToJson(textResponse);
//
//        CommonAutomationUtils.verifyCommonResponseFailedValidation(jsonResponse, response.getStatusCode(), HttpStatus.SC_BAD_REQUEST, AppConstants.REQUIRED_FIELD_INVALID_CODE, AppConstants.REQUIRED_FIELD_INVALID_ERROR);
//    }
//
//    @Test(groups = {"MC:Authentication", "Abdul Rehman", "SECTION:Source Card Brand Validation", "SC:Source Card Brand"}, description = "Validates transaction authentication with an empty source card brand", priority = 160)
//    public void transactionAuthenticationEmptySourceCardBrandCase() {
//
//        TransactionRequest transactionRequest = AppUtils.transactionRequestInstance();
//        transactionRequest.getSource().getCard().setBrand("");
//
//        String body = CommonAutomationUtils.stringToJson(transactionRequest);
//        Response response = AuthenticationTransactionRequest.postRequest(body, AppConstants.INTERNAL_END_POINT);
//        String textResponse = response.getBody().asString();
//        JsonNode jsonResponse = CommonAutomationUtils.convertToJson(textResponse);
//
//        CommonAutomationUtils.verifyCommonResponseFailedValidation(jsonResponse, response.getStatusCode(), HttpStatus.SC_BAD_REQUEST, AppConstants.REQUIRED_FIELD_INVALID_CODE, AppConstants.REQUIRED_FIELD_INVALID_ERROR);
//    }
//
//    @Test(groups = {"MC:Authentication", "Abdul Rehman", "SECTION:Source Card Brand Validation", "SC:Source Card Brand"}, description = "Validates transaction authentication with no source card brand", priority = 161)
//    public void transactionAuthenticationNoSourceCardBrandCase() {
//
//        TransactionRequest transactionRequest = AppUtils.transactionRequestInstance();
//
//        String body = CommonAutomationUtils.stringToJson(transactionRequest);
//        String updatedBody = CommonAutomationUtils.modifyJson(body, "DELETE", AppConstants.SOURCE + "." + AppConstants.CARD + "." + AppConstants.BRAND, null);
//        Response response = AuthenticationTransactionRequest.postRequest(updatedBody, AppConstants.INTERNAL_END_POINT);
//        String textResponse = response.getBody().asString();
//        JsonNode jsonResponse = CommonAutomationUtils.convertToJson(textResponse);
//
//        CommonAutomationUtils.verifyCommonResponseFailedValidation(jsonResponse, response.getStatusCode(), HttpStatus.SC_BAD_REQUEST, AppConstants.REQUIRED_FIELD_INVALID_CODE, AppConstants.REQUIRED_FIELD_INVALID_ERROR);
//    }
//    @Test(groups = {"MC:Authentication", "Abdul Rehman", "SECTION:Source Card Category Validation", "SC:Source Card Category"}, description = "Validates transaction authentication with a valid source card category", priority = 162)
//    public void transactionAuthenticationValidSourceCardCategoryCase() {
//
//        TransactionRequest transactionRequest = AppUtils.transactionRequestInstance();
//        transactionRequest.getSource().getCard().setCategory("CLASSIC");
//
//        String body = CommonAutomationUtils.stringToJson(transactionRequest);
//        Response response = AuthenticationTransactionRequest.postRequest(body, AppConstants.INTERNAL_END_POINT);
//
//        CommonAutomationUtils.verifyCommonResponseSuccessValidation(response, HttpStatus.SC_OK, Map.of(AppConstants.CARD + "." + AppConstants.CATEGORY, "CLASSIC"));
//    }
//
//    @Test(groups = {"MC:Authentication", "Abdul Rehman", "SECTION:Source Card Category Validation", "SC:Source Card Category"}, description = "Validates transaction authentication with an invalid source card category", priority = 163)
//    public void transactionAuthenticationInvalidSourceCardCategoryCase() {
//
//        TransactionRequest transactionRequest = AppUtils.transactionRequestInstance();
//        transactionRequest.getSource().getCard().setCategory("invalid_category");
//
//        String body = CommonAutomationUtils.stringToJson(transactionRequest);
//        Response response = AuthenticationTransactionRequest.postRequest(body, AppConstants.INTERNAL_END_POINT);
//
//        CommonAutomationUtils.verifyCommonResponseSuccessValidation(response, HttpStatus.SC_OK, Map.of(AppConstants.CARD + "." + AppConstants.CATEGORY, "invalid_category"));
//    }
//
//    @Test(groups = {"MC:Authentication", "Abdul Rehman", "SECTION:Source Card Category Validation", "SC:Source Card Category"}, description = "Validates transaction authentication with a null source card category", priority = 164)
//    public void transactionAuthenticationNullSourceCardCategoryCase() {
//
//        TransactionRequest transactionRequest = AppUtils.transactionRequestInstance();
//        transactionRequest.getSource().getCard().setCategory(null);
//
//        String body = CommonAutomationUtils.stringToJson(transactionRequest);
//        Response response = AuthenticationTransactionRequest.postRequest(body, AppConstants.INTERNAL_END_POINT);
//
//        CommonAutomationUtils.verifyCommonResponseSuccessValidation(response, HttpStatus.SC_OK);
//        CommonAutomationUtils.verifyNonAvailableKey(response, List.of(AppConstants.SOURCE + "." + AppConstants.CARD + "." + AppConstants.CATEGORY));
//    }
//
//    @Test(groups = {"MC:Authentication", "Abdul Rehman", "SECTION:Source Card Category Validation", "SC:Source Card Category"}, description = "Validates transaction authentication with an empty source card category", priority = 165)
//    public void transactionAuthenticationEmptySourceCardCategoryCase() {
//
//        TransactionRequest transactionRequest = AppUtils.transactionRequestInstance();
//        transactionRequest.getSource().getCard().setCategory("");
//
//        String body = CommonAutomationUtils.stringToJson(transactionRequest);
//        Response response = AuthenticationTransactionRequest.postRequest(body, AppConstants.INTERNAL_END_POINT);
//
//        CommonAutomationUtils.verifyCommonResponseSuccessValidation(response, HttpStatus.SC_OK, Map.of(AppConstants.CARD + "." + AppConstants.CATEGORY, ""));
//    }
//
//    @Test(groups = {"MC:Authentication", "Abdul Rehman", "SECTION:Source Card Category Validation", "SC:Source Card Category"}, description = "Validates transaction authentication with no source card category", priority = 166)
//    public void transactionAuthenticationNoSourceCardCategoryCase() {
//
//        TransactionRequest transactionRequest = AppUtils.transactionRequestInstance();
//
//        String body = CommonAutomationUtils.stringToJson(transactionRequest);
//        String updatedBody = CommonAutomationUtils.modifyJson(body, "DELETE", AppConstants.SOURCE + "." + AppConstants.CARD + "." + AppConstants.CATEGORY, null);
//        Response response = AuthenticationTransactionRequest.postRequest(updatedBody, AppConstants.INTERNAL_END_POINT);
//
//        CommonAutomationUtils.verifyCommonResponseSuccessValidation(response, HttpStatus.SC_OK);
//    }
//
//    @Test(groups = {"MC:Authentication", "Abdul Rehman", "SECTION:Source Card Last Four Validation", "SC:Source Card Last Four"}, description = "Validates transaction authentication with a valid source card last four digits", priority = 167)
//    public void transactionAuthenticationValidSourceCardLastFourCase() {
//
//        TransactionRequest transactionRequest = AppUtils.transactionRequestInstance();
//        transactionRequest.getSource().getCard().setLastFour("SALWA HEDAR");
//
//        String body = CommonAutomationUtils.stringToJson(transactionRequest);
//        Response response = AuthenticationTransactionRequest.postRequest(body, AppConstants.INTERNAL_END_POINT);
//
//        CommonAutomationUtils.verifyCommonResponseSuccessValidation(response, HttpStatus.SC_OK, Map.of(AppConstants.CARD + "." + AppConstants.LAST_FOUR, "SALWA HEDAR"));
//    }
//
//    @Test(groups = {"MC:Authentication", "Abdul Rehman", "SECTION:Source Card Last Four Validation", "SC:Source Card Last Four"}, description = "Validates transaction authentication with an invalid source card last four digits", priority = 168)
//    public void transactionAuthenticationInvalidSourceCardLastFourCase() {
//
//        TransactionRequest transactionRequest = AppUtils.transactionRequestInstance();
//        transactionRequest.getSource().getCard().setLastFour("ABC");
//
//        String body = CommonAutomationUtils.stringToJson(transactionRequest);
//        Response response = AuthenticationTransactionRequest.postRequest(body, AppConstants.INTERNAL_END_POINT);
//
//        CommonAutomationUtils.verifyCommonResponseSuccessValidation(response, HttpStatus.SC_OK, Map.of(AppConstants.CARD + "." + AppConstants.LAST_FOUR, "ABC"));
//    }
//
//    @Test(groups = {"MC:Authentication", "Abdul Rehman", "SECTION:Source Card Last Four Validation", "SC:Source Card Last Four"}, description = "Validates transaction authentication with a null source card last four digits", priority = 169)
//    public void transactionAuthenticationNullSourceCardLastFourCase() {
//
//        TransactionRequest transactionRequest = AppUtils.transactionRequestInstance();
//        transactionRequest.getSource().getCard().setLastFour(null);
//
//        String body = CommonAutomationUtils.stringToJson(transactionRequest);
//        Response response = AuthenticationTransactionRequest.postRequest(body, AppConstants.INTERNAL_END_POINT);
//
//        CommonAutomationUtils.verifyCommonResponseSuccessValidation(response, HttpStatus.SC_OK);
//        CommonAutomationUtils.verifyNonAvailableKey(response, List.of(AppConstants.SOURCE + "." + AppConstants.CARD + "." + AppConstants.LAST_FOUR));
//    }
//
//    @Test(groups = {"MC:Authentication", "Abdul Rehman", "SECTION:Source Card Last Four Validation", "SC:Source Card Last Four"}, description = "Validates transaction authentication with an empty source card last four digits", priority = 170)
//    public void transactionAuthenticationEmptySourceCardLastFourCase() {
//
//        TransactionRequest transactionRequest = AppUtils.transactionRequestInstance();
//        transactionRequest.getSource().getCard().setLastFour("");
//
//        String body = CommonAutomationUtils.stringToJson(transactionRequest);
//        Response response = AuthenticationTransactionRequest.postRequest(body, AppConstants.INTERNAL_END_POINT);
//
//        CommonAutomationUtils.verifyCommonResponseSuccessValidation(response, HttpStatus.SC_OK, Map.of(AppConstants.CARD + "." + AppConstants.LAST_FOUR, ""));
//    }
//
//    @Test(groups = {"MC:Authentication", "Abdul Rehman", "SECTION:Source Card Last Four Validation", "SC:Source Card Last Four"}, description = "Validates transaction authentication with no source card last four digits", priority = 171)
//    public void transactionAuthenticationNoSourceCardLastFourCase() {
//
//        TransactionRequest transactionRequest = AppUtils.transactionRequestInstance();
//
//        String body = CommonAutomationUtils.stringToJson(transactionRequest);
//        String updatedBody = CommonAutomationUtils.modifyJson(body, "DELETE", AppConstants.SOURCE + "." + AppConstants.CARD + "." + AppConstants.LAST_FOUR, null);
//        Response response = AuthenticationTransactionRequest.postRequest(updatedBody, AppConstants.INTERNAL_END_POINT);
//
//        CommonAutomationUtils.verifyCommonResponseSuccessValidation(response, HttpStatus.SC_OK);
//    }
//
//    @Test(groups = {"MC:Authentication", "Abdul Rehman", "SECTION:Source Card Name Validation", "SC:Source Card Name"}, description = "Validates transaction authentication with a valid source card name", priority = 172)
//    public void transactionAuthenticationValidSourceCardNameCase() {
//
//        TransactionRequest transactionRequest = AppUtils.transactionRequestInstance();
//        transactionRequest.getSource().getCard().setName("SALWA HEDAR");
//
//        String body = CommonAutomationUtils.stringToJson(transactionRequest);
//        Response response = AuthenticationTransactionRequest.postRequest(body, AppConstants.INTERNAL_END_POINT);
//
//        CommonAutomationUtils.verifyCommonResponseSuccessValidation(response, HttpStatus.SC_OK, Map.of(AppConstants.CARD + "." + AppConstants.NAME, "SALWA HEDAR"));
//    }
//
//    @Test(groups = {"MC:Authentication", "Abdul Rehman", "SECTION:Source Card Name Validation", "SC:Source Card Name"}, description = "Validates transaction authentication with a null source card name", priority = 173)
//    public void transactionAuthenticationNullSourceCardNameCase() {
//
//        TransactionRequest transactionRequest = AppUtils.transactionRequestInstance();
//        transactionRequest.getSource().getCard().setName(null);
//
//        String body = CommonAutomationUtils.stringToJson(transactionRequest);
//        Response response = AuthenticationTransactionRequest.postRequest(body, AppConstants.INTERNAL_END_POINT);
//
//        CommonAutomationUtils.verifyCommonResponseSuccessValidation(response, HttpStatus.SC_OK);
//        CommonAutomationUtils.verifyNonAvailableKey(response, List.of(AppConstants.SOURCE + "." + AppConstants.CARD + "." + AppConstants.NAME));
//    }
//
//    @Test(groups = {"MC:Authentication", "Abdul Rehman", "SECTION:Source Card Name Validation", "SC:Source Card Name"}, description = "Validates transaction authentication with an empty source card name", priority = 174)
//    public void transactionAuthenticationEmptySourceCardNameCase() {
//
//        TransactionRequest transactionRequest = AppUtils.transactionRequestInstance();
//        transactionRequest.getSource().getCard().setName("");
//
//        String body = CommonAutomationUtils.stringToJson(transactionRequest);
//        Response response = AuthenticationTransactionRequest.postRequest(body, AppConstants.INTERNAL_END_POINT);
//
//        CommonAutomationUtils.verifyCommonResponseSuccessValidation(response, HttpStatus.SC_OK, Map.of(AppConstants.CARD + "." + AppConstants.NAME, ""));
//    }
//
//    @Test(groups = {"MC:Authentication", "Abdul Rehman", "SECTION:Source Card Name Validation", "SC:Source Card Name"}, description = "Validates transaction authentication with no source card name", priority = 175)
//    public void transactionAuthenticationNoSourceCardNameCase() {
//
//        TransactionRequest transactionRequest = AppUtils.transactionRequestInstance();
//
//        String body = CommonAutomationUtils.stringToJson(transactionRequest);
//        String updatedBody = CommonAutomationUtils.modifyJson(body, "DELETE", AppConstants.SOURCE + "." + AppConstants.CARD + "." + AppConstants.NAME, null);
//        Response response = AuthenticationTransactionRequest.postRequest(updatedBody, AppConstants.INTERNAL_END_POINT);
//
//        CommonAutomationUtils.verifyCommonResponseSuccessValidation(response, HttpStatus.SC_OK);
//    }
//
//    @Test(groups = {"MC:Authentication", "Abdul Rehman", "SECTION:Source Card Expiry Validation", "SC:Source Card Expiry"}, description = "Validates transaction authentication with a null source card expiry", priority = 176)
//    public void transactionAuthenticationNullSourceCardExpiryCase() {
//
//        TransactionRequest transactionRequest = AppUtils.transactionRequestInstance();
//        transactionRequest.getSource().getCard().setExpiry(null);
//
//        String body = CommonAutomationUtils.stringToJson(transactionRequest);
//        Response response = AuthenticationTransactionRequest.postRequest(body, AppConstants.INTERNAL_END_POINT);
//        String textResponse = response.getBody().asString();
//        JsonNode jsonResponse = CommonAutomationUtils.convertToJson(textResponse);
//
//        CommonAutomationUtils.verifyCommonResponseFailedValidation(jsonResponse, response.getStatusCode(), HttpStatus.SC_BAD_REQUEST, AppConstants.REQUIRED_FIELD_INVALID_CODE, AppConstants.REQUIRED_FIELD_INVALID_ERROR);
//    }
//
//    @Test(groups = {"MC:Authentication", "Abdul Rehman", "SECTION:Source Card Expiry Validation", "SC:Source Card Expiry"}, description = "Validates transaction authentication with a valid month and year in source card expiry", priority = 177)
//    public void transactionAuthenticationSourceCardExpiryValidMonthValidYearCase() {
//
//        TransactionRequest transactionRequest = AppUtils.transactionRequestInstance();
//        TransactionRequest.Source.Card.Expiry expiry = transactionRequest.getSource().getCard().getExpiry();
//        expiry.setMonth("12");
//        expiry.setYear("24");
//
//        String body = CommonAutomationUtils.stringToJson(transactionRequest);
//        Response response = AuthenticationTransactionRequest.postRequest(body, AppConstants.INTERNAL_END_POINT);
//
//        CommonAutomationUtils.verifyCommonResponseSuccessValidation(response, HttpStatus.SC_OK, Map.of(AppConstants.CARD + "." + AppConstants.EXPIRY + "." + AppConstants.MONTH, "12", AppConstants.CARD + "." + AppConstants.EXPIRY + "." + AppConstants.YEAR, "24"));
//    }
//
//    @Test(groups = {"MC:Authentication", "Abdul Rehman", "SECTION:Source Card Expiry Validation", "SC:Source Card Expiry"}, description = "Validates transaction authentication with a valid month and invalid year in source card expiry", priority = 178)
//    public void transactionAuthenticationSourceCardExpiryValidMonthInvalidYearCase() {
//
//        TransactionRequest transactionRequest = AppUtils.transactionRequestInstance();
//        TransactionRequest.Source.Card.Expiry expiry = transactionRequest.getSource().getCard().getExpiry();
//        expiry.setMonth("12");
//        expiry.setYear("00");
//
//        String body = CommonAutomationUtils.stringToJson(transactionRequest);
//        Response response = AuthenticationTransactionRequest.postRequest(body, AppConstants.INTERNAL_END_POINT);
//
//        CommonAutomationUtils.verifyCommonResponseSuccessValidation(response, HttpStatus.SC_OK, Map.of(AppConstants.CARD + "." + AppConstants.EXPIRY + "." + AppConstants.MONTH, "12", AppConstants.CARD + "." + AppConstants.EXPIRY + "." + AppConstants.YEAR, "00"));
//    }
//
//    @Test(groups = {"MC:Authentication", "Abdul Rehman", "SECTION:Source Card Expiry Validation", "SC:Source Card Expiry"}, description = "Validates transaction authentication with an invalid month and valid year in source card expiry", priority = 179)
//    public void transactionAuthenticationSourceCardExpiryInValidMonthInvalidYearCase() {
//
//        TransactionRequest transactionRequest = AppUtils.transactionRequestInstance();
//        TransactionRequest.Source.Card.Expiry expiry = transactionRequest.getSource().getCard().getExpiry();
//        expiry.setMonth("00");
//        expiry.setYear("24");
//
//        String body = CommonAutomationUtils.stringToJson(transactionRequest);
//        Response response = AuthenticationTransactionRequest.postRequest(body, AppConstants.INTERNAL_END_POINT);
//
//        CommonAutomationUtils.verifyCommonResponseSuccessValidation(response, HttpStatus.SC_OK, Map.of(AppConstants.CARD + "." + AppConstants.EXPIRY + "." + AppConstants.MONTH, "00", AppConstants.CARD + "." + AppConstants.EXPIRY + "." + AppConstants.YEAR, "24"));
//    }
//
//    @Test(groups = {"MC:Authentication", "Abdul Rehman", "SECTION:Source Card Expiry Validation", "SC:Source Card Expiry"}, description = "Validates transaction authentication with a non-numeric month and year in source card expiry", priority = 180)
//    public void transactionAuthenticationSourceNonNumericCardExpiryCase() {
//
//        TransactionRequest transactionRequest = AppUtils.transactionRequestInstance();
//        TransactionRequest.Source.Card.Expiry expiry = transactionRequest.getSource().getCard().getExpiry();
//        expiry.setMonth("aa");
//        expiry.setYear("bb");
//
//        String body = CommonAutomationUtils.stringToJson(transactionRequest);
//        Response response = AuthenticationTransactionRequest.postRequest(body, AppConstants.INTERNAL_END_POINT);
//
//        CommonAutomationUtils.verifyCommonResponseSuccessValidation(response, HttpStatus.SC_OK, Map.of(AppConstants.CARD + "." + AppConstants.EXPIRY + "." + AppConstants.MONTH, "aa", AppConstants.CARD + "." + AppConstants.EXPIRY + "." + AppConstants.YEAR, "bb"));
//    }
//
//    @Test(groups = {"MC:Authentication", "Abdul Rehman", "SECTION:Source Card Expiry Validation", "SC:Source Card Expiry"}, description = "Validates transaction authentication with null values in source card expiry", priority = 181)
//    public void transactionAuthenticationSourceNullCardExpiryCase() {
//
//        TransactionRequest transactionRequest = AppUtils.transactionRequestInstance();
//        TransactionRequest.Source.Card.Expiry expiry = transactionRequest.getSource().getCard().getExpiry();
//        expiry.setMonth(null);
//        expiry.setYear(null);
//
//        String body = CommonAutomationUtils.stringToJson(transactionRequest);
//        Response response = AuthenticationTransactionRequest.postRequest(body, AppConstants.INTERNAL_END_POINT);
//        String textResponse = response.getBody().asString();
//        JsonNode jsonResponse = CommonAutomationUtils.convertToJson(textResponse);
//
//        CommonAutomationUtils.verifyCommonResponseFailedValidation(jsonResponse, response.getStatusCode(), HttpStatus.SC_BAD_REQUEST, AppConstants.REQUIRED_FIELD_INVALID_CODE, AppConstants.REQUIRED_FIELD_INVALID_ERROR);
//    }
//
//    @Test(groups = {"MC:Authentication", "Abdul Rehman", "SECTION:Source Card Funding Validation", "SC:Source Card Funding"}, description = "Validates transaction authentication with a valid source card funding type", priority = 182)
//    public void transactionAuthenticationValidSourceCardFundingCase() {
//
//        TransactionRequest transactionRequest = AppUtils.transactionRequestInstance();
//        transactionRequest.getSource().getCard().setFunding("CREDIT");
//
//        String body = CommonAutomationUtils.stringToJson(transactionRequest);
//        Response response = AuthenticationTransactionRequest.postRequest(body, AppConstants.INTERNAL_END_POINT);
//
//        CommonAutomationUtils.verifyCommonResponseSuccessValidation(response, HttpStatus.SC_OK, Map.of(AppConstants.CARD + "." + AppConstants.FUNDING, "CREDIT"));
//    }
//
//    @Test(groups = {"MC:Authentication", "Abdul Rehman", "SECTION:Source Card Funding Validation", "SC:Source Card Funding"}, description = "Validates transaction authentication with an invalid source card funding type", priority = 183)
//    public void transactionAuthenticationInvalidSourceCardFundingCase() {
//
//        TransactionRequest transactionRequest = AppUtils.transactionRequestInstance();
//        transactionRequest.getSource().getCard().setFunding("invalid_funding");
//
//        String body = CommonAutomationUtils.stringToJson(transactionRequest);
//        Response response = AuthenticationTransactionRequest.postRequest(body, AppConstants.INTERNAL_END_POINT);
//
//        CommonAutomationUtils.verifyCommonResponseSuccessValidation(response, HttpStatus.SC_OK, Map.of(AppConstants.CARD + "." + AppConstants.FUNDING, "invalid_funding"));
//    }
//
//    @Test(groups = {"MC:Authentication", "Abdul Rehman", "SECTION:Source Card Funding Validation", "SC:Source Card Funding"}, description = "Validates transaction authentication with a null source card funding type", priority = 184)
//    public void transactionAuthenticationNullSourceCardFundingCase() {
//
//        TransactionRequest transactionRequest = AppUtils.transactionRequestInstance();
//        transactionRequest.getSource().getCard().setFunding(null);
//
//        String body = CommonAutomationUtils.stringToJson(transactionRequest);
//        Response response = AuthenticationTransactionRequest.postRequest(body, AppConstants.INTERNAL_END_POINT);
//
//        CommonAutomationUtils.verifyCommonResponseSuccessValidation(response, HttpStatus.SC_OK);
//        CommonAutomationUtils.verifyNonAvailableKey(response, List.of(AppConstants.SOURCE + "." + AppConstants.CARD + "." + AppConstants.FUNDING));
//    }
//
//    @Test(groups = {"MC:Authentication", "Abdul Rehman", "SECTION:Source Card Funding Validation", "SC:Source Card Funding"}, description = "Validates transaction authentication with an empty source card funding type", priority = 185)
//    public void transactionAuthenticationEmptySourceCardFundingCase() {
//
//        TransactionRequest transactionRequest = AppUtils.transactionRequestInstance();
//        transactionRequest.getSource().getCard().setFunding("");
//
//        String body = CommonAutomationUtils.stringToJson(transactionRequest);
//        Response response = AuthenticationTransactionRequest.postRequest(body, AppConstants.INTERNAL_END_POINT);
//
//        CommonAutomationUtils.verifyCommonResponseSuccessValidation(response, HttpStatus.SC_OK, Map.of(AppConstants.CARD + "." + AppConstants.FUNDING, ""));
//    }
//
//    @Test(groups = {"MC:Authentication", "Abdul Rehman", "SECTION:Source Card Funding Validation", "SC:Source Card Funding"}, description = "Validates transaction authentication with no source card funding type", priority = 186)
//    public void transactionAuthenticationNoSourceCardFundingCase() {
//
//        TransactionRequest transactionRequest = AppUtils.transactionRequestInstance();
//
//        String body = CommonAutomationUtils.stringToJson(transactionRequest);
//        String updatedBody = CommonAutomationUtils.modifyJson(body, "DELETE", AppConstants.SOURCE + "." + AppConstants.CARD + "." + AppConstants.FUNDING, null);
//        Response response = AuthenticationTransactionRequest.postRequest(updatedBody, AppConstants.INTERNAL_END_POINT);
//
//        CommonAutomationUtils.verifyCommonResponseSuccessValidation(response, HttpStatus.SC_OK);
//    }
//
//    @Test(groups = {"MC:Authentication", "Abdul Rehman", "SECTION:Routing Validation", "SC:Routing"}, description = "Validates transaction authentication with a null routing provider", priority = 187)
//    public void transactionAuthenticationNullRoutingCase() {
//
//        TransactionRequest transactionRequest = AppUtils.transactionRequestInstance();
//        transactionRequest.setRouting(null);
//
//        String body = CommonAutomationUtils.stringToJson(transactionRequest);
//        Response response = AuthenticationTransactionRequest.postRequest(body, AppConstants.INTERNAL_END_POINT);
//        String textResponse = response.getBody().asString();
//        JsonNode jsonResponse = CommonAutomationUtils.convertToJson(textResponse);
//
//        CommonAutomationUtils.verifyCommonResponseFailedValidation(jsonResponse, response.getStatusCode(), HttpStatus.SC_BAD_REQUEST, AppConstants.ROUTING_DETAIL_NOT_FOUND_CODE, AppConstants.ROUTING_DETAIL_NOT_FOUND_ERROR);
//    }
//
//    @Test(groups = {"MC:Authentication", "Abdul Rehman", "SECTION:Routing Provider Validation", "SC:Routing Provider"}, description = "Validates transaction authentication with a valid routing provider", priority = 188)
//    public void transactionAuthenticationValidRoutingProviderCase() {
//
//        TransactionRequest transactionRequest = AppUtils.transactionRequestInstance();
//        transactionRequest.getRouting().setProvider("MPGS");
//
//        String body = CommonAutomationUtils.stringToJson(transactionRequest);
//        Response response = AuthenticationTransactionRequest.postRequest(body, AppConstants.INTERNAL_END_POINT);
//
//        CommonAutomationUtils.verifyCommonResponseSuccessValidation(response, HttpStatus.SC_OK, Map.of(AppConstants.ROUTING + "." + AppConstants.PROVIDER, "MPGS"));
//    }
//
//    @Test(groups = {"MC:Authentication", "Abdul Rehman", "SECTION:Routing Provider Validation", "SC:Routing Provider"}, description = "Validates transaction authentication with an invalid routing provider", priority = 189)
//    public void transactionAuthenticationInvalidRoutingProviderCase() {
//
//        TransactionRequest transactionRequest = AppUtils.transactionRequestInstance();
//        transactionRequest.getRouting().setProvider("invalid_provider");
//
//        String body = CommonAutomationUtils.stringToJson(transactionRequest);
//        Response response = AuthenticationTransactionRequest.postRequest(body, AppConstants.INTERNAL_END_POINT);
//        String textResponse = response.getBody().asString();
//        JsonNode jsonResponse = CommonAutomationUtils.convertToJson(textResponse);
//
//        CommonAutomationUtils.verifyCommonResponseFailedValidation(jsonResponse, response.getStatusCode(), HttpStatus.SC_BAD_REQUEST, AppConstants.REQUIRED_INPUT_INVALID_CODE, AppConstants.REQUIRED_INPUT_INVALID_ERROR);
//    }
//
//    @Test(groups = {"MC:Authentication", "Abdul Rehman", "SECTION:Routing Provider Validation", "SC:Routing Provider"}, description = "Validates transaction authentication with a numeric routing provider", priority = 190)
//    public void transactionAuthenticationNumericRoutingProviderCase() {
//
//        TransactionRequest transactionRequest = AppUtils.transactionRequestInstance();
//
//        String body = CommonAutomationUtils.stringToJson(transactionRequest);
//        String updatedBody = CommonAutomationUtils.modifyJson(body, "MODIFY", AppConstants.ROUTING + "." + AppConstants.PROVIDER, 12345);
//        Response response = AuthenticationTransactionRequest.postRequest(updatedBody, AppConstants.INTERNAL_END_POINT);
//        String textResponse = response.getBody().asString();
//        JsonNode jsonResponse = CommonAutomationUtils.convertToJson(textResponse);
//
//        CommonAutomationUtils.verifyCommonResponseFailedValidation(jsonResponse, response.getStatusCode(), HttpStatus.SC_BAD_REQUEST, AppConstants.REQUIRED_INPUT_INVALID_CODE, AppConstants.REQUIRED_INPUT_INVALID_ERROR);
//    }
//
//    @Test(groups = {"MC:Authentication", "Abdul Rehman", "SECTION:Routing Provider Validation", "SC:Routing Provider"}, description = "Validates transaction authentication with leading space in routing provider", priority = 191)
//    public void transactionAuthenticationLeadingSpaceRoutingProviderCase() {
//
//        TransactionRequest transactionRequest = AppUtils.transactionRequestInstance();
//        transactionRequest.getRouting().setProvider("  MPGS  ");
//
//        String body = CommonAutomationUtils.stringToJson(transactionRequest);
//        Response response = AuthenticationTransactionRequest.postRequest(body, AppConstants.INTERNAL_END_POINT);
//        String textResponse = response.getBody().asString();
//        JsonNode jsonResponse = CommonAutomationUtils.convertToJson(textResponse);
//
//        CommonAutomationUtils.verifyCommonResponseFailedValidation(jsonResponse, response.getStatusCode(), HttpStatus.SC_BAD_REQUEST, AppConstants.REQUIRED_INPUT_INVALID_CODE, AppConstants.REQUIRED_INPUT_INVALID_ERROR);
//    }
//
//    @Test(groups = {"MC:Authentication", "Abdul Rehman", "SECTION:Routing Provider Validation", "SC:Routing Provider"}, description = "Validates transaction authentication with an empty routing provider", priority = 192)
//    public void transactionAuthenticationEmptyRoutingProviderCase() {
//
//        TransactionRequest transactionRequest = AppUtils.transactionRequestInstance();
//        transactionRequest.getRouting().setProvider("");
//
//        String body = CommonAutomationUtils.stringToJson(transactionRequest);
//        Response response = AuthenticationTransactionRequest.postRequest(body, AppConstants.INTERNAL_END_POINT);
//        String textResponse = response.getBody().asString();
//        JsonNode jsonResponse = CommonAutomationUtils.convertToJson(textResponse);
//
//        CommonAutomationUtils.verifyCommonResponseFailedValidation(jsonResponse, response.getStatusCode(), HttpStatus.SC_BAD_REQUEST, AppConstants.ROUTING_DETAIL_NOT_FOUND_CODE, AppConstants.ROUTING_DETAIL_NOT_FOUND_ERROR);
//    }
//
//    @Test(groups = {"MC:Authentication", "Abdul Rehman", "SECTION:Routing Provider Validation", "SC:Routing Provider"}, description = "Validates transaction authentication with no routing provider", priority = 193)
//    public void transactionAuthenticationNoRoutingProviderCase() {
//
//        TransactionRequest transactionRequest = AppUtils.transactionRequestInstance();
//
//        String body = CommonAutomationUtils.stringToJson(transactionRequest);
//        String updatedBody = CommonAutomationUtils.modifyJson(body, "DELETE", AppConstants.ROUTING + "." + AppConstants.PROVIDER, null);
//        Response response = AuthenticationTransactionRequest.postRequest(updatedBody, AppConstants.INTERNAL_END_POINT);
//        String textResponse = response.getBody().asString();
//        JsonNode jsonResponse = CommonAutomationUtils.convertToJson(textResponse);
//
//        CommonAutomationUtils.verifyCommonResponseFailedValidation(jsonResponse, response.getStatusCode(), HttpStatus.SC_BAD_REQUEST, AppConstants.ROUTING_DETAIL_NOT_FOUND_CODE, AppConstants.ROUTING_DETAIL_NOT_FOUND_ERROR);
//    }
//
//    @Test(groups = {"MC:Authentication", "Abdul Rehman", "SECTION:Routing Provider Validation", "SC:Routing Provider"}, description = "Validates transaction authentication with a null routing provider", priority = 194)
//    public void transactionAuthenticationNullRoutingProviderCase() {
//
//        TransactionRequest transactionRequest = AppUtils.transactionRequestInstance();
//        transactionRequest.getRouting().setProvider(null);
//
//        String body = CommonAutomationUtils.stringToJson(transactionRequest);
//        Response response = AuthenticationTransactionRequest.postRequest(body, AppConstants.INTERNAL_END_POINT);
//        String textResponse = response.getBody().asString();
//        JsonNode jsonResponse = CommonAutomationUtils.convertToJson(textResponse);
//
//        CommonAutomationUtils.verifyCommonResponseFailedValidation(jsonResponse, response.getStatusCode(), HttpStatus.SC_BAD_REQUEST, AppConstants.ROUTING_DETAIL_NOT_FOUND_CODE, AppConstants.ROUTING_DETAIL_NOT_FOUND_ERROR);
//    }
//
//    @Test(groups = {"MC:Authentication", "Abdul Rehman", "SECTION:Routing Terminal Id Validation", "SC:Routing Terminal Id"}, description = "Validates transaction authentication with a valid routing terminal ID", priority = 195)
//    public void transactionAuthenticationValidRoutingTerminalIdCase() {
//
//        TransactionRequest transactionRequest = AppUtils.transactionRequestInstance();
//        transactionRequest.getRouting().setTerminalId("ter_p3F64020211320j6XQ1002702");
//
//        String body = CommonAutomationUtils.stringToJson(transactionRequest);
//        Response response = AuthenticationTransactionRequest.postRequest(body, AppConstants.INTERNAL_END_POINT);
//
//        CommonAutomationUtils.verifyCommonResponseSuccessValidation(response, HttpStatus.SC_OK, Map.of(AppConstants.ROUTING + "." + AppConstants.TERMINAL_ID, "ter_p3F64020211320j6XQ1002702"));
//    }
//
//    @Test(groups = {"MC:Authentication", "Abdul Rehman", "SECTION:Routing Terminal Id Validation", "SC:Routing Terminal Id"}, description = "Validates transaction authentication with an invalid routing terminal ID", priority = 196)
//    public void transactionAuthenticationInvalidRoutingTerminalIdCase() {
//
//        TransactionRequest transactionRequest = AppUtils.transactionRequestInstance();
//        transactionRequest.getRouting().setTerminalId("invalid_terminal_id");
//
//        String body = CommonAutomationUtils.stringToJson(transactionRequest);
//        Response response = AuthenticationTransactionRequest.postRequest(body, AppConstants.INTERNAL_END_POINT);
//        String textResponse = response.getBody().asString();
//        JsonNode jsonResponse = CommonAutomationUtils.convertToJson(textResponse);
//
//        CommonAutomationUtils.verifyCommonResponseFailedDescriptionValidation(jsonResponse, response.getStatusCode(), HttpStatus.SC_BAD_REQUEST, AppConstants.INVALID_EMPTY_TOKEN_ID_CODE, AppConstants.INVALID_EMPTY_TOKEN_ID_DESCRIPTION);
//    }
//
//    @Test(groups = {"MC:Authentication", "Abdul Rehman", "SECTION:Routing Terminal Id Validation", "SC:Routing Terminal Id"}, description = "Validates transaction authentication with a numeric routing terminal ID", priority = 197)
//    public void transactionAuthenticationNumericRoutingTerminalIdCase() {
//
//        TransactionRequest transactionRequest = AppUtils.transactionRequestInstance();
//
//        String body = CommonAutomationUtils.stringToJson(transactionRequest);
//        String updatedBody = CommonAutomationUtils.modifyJson(body, "MODIFY", AppConstants.ROUTING + "." + AppConstants.TERMINAL_ID, 1234567890);
//        Response response = AuthenticationTransactionRequest.postRequest(updatedBody, AppConstants.INTERNAL_END_POINT);
//        String textResponse = response.getBody().asString();
//        JsonNode jsonResponse = CommonAutomationUtils.convertToJson(textResponse);
//
//        CommonAutomationUtils.verifyCommonResponseFailedDescriptionValidation(jsonResponse, response.getStatusCode(), HttpStatus.SC_BAD_REQUEST, AppConstants.INVALID_EMPTY_TOKEN_ID_CODE, AppConstants.INVALID_EMPTY_TOKEN_ID_DESCRIPTION);
//    }
//
//    @Test(groups = {"MC:Authentication", "Abdul Rehman", "SECTION:Routing Terminal Id Validation", "SC:Routing Terminal Id"}, description = "Validates transaction authentication with an empty routing terminal ID", priority = 198)
//    public void transactionAuthenticationEmptyRoutingTerminalIdCase() {
//
//        TransactionRequest transactionRequest = AppUtils.transactionRequestInstance();
//        transactionRequest.getRouting().setTerminalId("");
//
//        String body = CommonAutomationUtils.stringToJson(transactionRequest);
//        Response response = AuthenticationTransactionRequest.postRequest(body, AppConstants.INTERNAL_END_POINT);
//        String textResponse = response.getBody().asString();
//        JsonNode jsonResponse = CommonAutomationUtils.convertToJson(textResponse);
//
//        CommonAutomationUtils.verifyCommonResponseFailedValidation(jsonResponse, response.getStatusCode(), HttpStatus.SC_BAD_REQUEST, AppConstants.ROUTING_DETAIL_NOT_FOUND_CODE, AppConstants.ROUTING_DETAIL_NOT_FOUND_ERROR);
//    }
//
//    @Test(groups = {"MC:Authentication", "Abdul Rehman", "SECTION:Routing Terminal Id Validation", "SC:Routing Terminal Id"}, description = "Validates transaction authentication with no routing terminal ID", priority = 199)
//    public void transactionAuthenticationNoRoutingTerminalIdCase() {
//
//        TransactionRequest transactionRequest = AppUtils.transactionRequestInstance();
//
//        String body = CommonAutomationUtils.stringToJson(transactionRequest);
//        String updatedBody = CommonAutomationUtils.modifyJson(body, "DELETE", AppConstants.ROUTING + "." + AppConstants.TERMINAL_ID, null);
//        Response response = AuthenticationTransactionRequest.postRequest(updatedBody, AppConstants.INTERNAL_END_POINT);
//        String textResponse = response.getBody().asString();
//        JsonNode jsonResponse = CommonAutomationUtils.convertToJson(textResponse);
//
//        CommonAutomationUtils.verifyCommonResponseFailedValidation(jsonResponse, response.getStatusCode(), HttpStatus.SC_BAD_REQUEST, AppConstants.ROUTING_DETAIL_NOT_FOUND_CODE, AppConstants.ROUTING_DETAIL_NOT_FOUND_ERROR);
//    }
//
//    @Test(groups = {"MC:Authentication", "Abdul Rehman", "SECTION:Routing Terminal Id Validation", "SC:Routing Terminal Id"}, description = "Validates transaction authentication with a null routing terminal ID", priority = 200)
//    public void transactionAuthenticationNullRoutingTerminalIdCase() {
//
//        TransactionRequest transactionRequest = AppUtils.transactionRequestInstance();
//        transactionRequest.getRouting().setTerminalId(null);
//
//        String body = CommonAutomationUtils.stringToJson(transactionRequest);
//        Response response = AuthenticationTransactionRequest.postRequest(body, AppConstants.INTERNAL_END_POINT);
//        String textResponse = response.getBody().asString();
//        JsonNode jsonResponse = CommonAutomationUtils.convertToJson(textResponse);
//
//        CommonAutomationUtils.verifyCommonResponseFailedValidation(jsonResponse, response.getStatusCode(), HttpStatus.SC_BAD_REQUEST, AppConstants.ROUTING_DETAIL_NOT_FOUND_CODE, AppConstants.ROUTING_DETAIL_NOT_FOUND_ERROR);
//    }
}

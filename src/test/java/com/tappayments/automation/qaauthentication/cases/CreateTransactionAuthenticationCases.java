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
import org.testng.annotations.Test;

import java.util.List;
import java.util.Map;
import java.util.UUID;

public class CreateTransactionAuthenticationCases extends BaseTest {

    @Test(groups = {"MC:Authentication", "Abdul Rehman", "SECTION:Amount Validation", "SC:Amount"}, description = "Validates transaction authentication for USD currency with a valid amount", priority = 1)
    public void transactionAuthenticationAmountUSDCurrencyCase() {

        TransactionRequest transactionRequest = AppUtils.transactionRequestInstance();

        transactionRequest.setAmount(10.11);
        transactionRequest.setCurrency("USD");

        String body = CommonAutomationUtils.stringToJson(transactionRequest);
        Response response = AuthenticationTransactionRequest.postRequest(body, AppConstants.INTERNAL_END_POINT);
        CommonAutomationUtils.verifyCommonResponseSuccessValidation(response, HttpStatus.SC_OK, Map.of(AppConstants.AMOUNT, 10.11, AppConstants.CURRENCY, "USD"));
    }

    @Test(groups = {"MC:Authentication", "Abdul Rehman", "SECTION:Amount Validation", "SC:Amount"}, description = "Validates transaction authentication fails for USD currency with more than two decimal places", priority = 2)
    public void transactionAuthenticationAmountMoreDecimalUSDCurrencyCase() {

        TransactionRequest transactionRequest = AppUtils.transactionRequestInstance();

        transactionRequest.setAmount(10.111);
        transactionRequest.setCurrency("USD");

        String body = CommonAutomationUtils.stringToJson(transactionRequest);
        Response response = AuthenticationTransactionRequest.postRequest(body, AppConstants.INTERNAL_END_POINT);
        String textResponse = response.getBody().asString();
        JsonNode jsonResponse = CommonAutomationUtils.convertToJson(textResponse);

        CommonAutomationUtils.verifyCommonResponseFailedValidation(jsonResponse, response.getStatusCode(), HttpStatus.SC_BAD_REQUEST, AppConstants.INVALID_DATA_CODE, AppConstants.INVALID_DATA_ERROR);
    }

    @Test(groups = {"MC:Authentication", "Abdul Rehman", "SECTION:Amount Validation", "SC:Amount"}, description = "Validates transaction authentication fails for SAR currency with more than two decimal places", priority = 3)
    public void transactionAuthenticationAmountMoreDecimalSARCurrencyCase() {

        TransactionRequest transactionRequest = AppUtils.transactionRequestInstance();

        transactionRequest.setAmount(10.111);
        transactionRequest.setCurrency("SAR");

        String body = CommonAutomationUtils.stringToJson(transactionRequest);
        Response response = AuthenticationTransactionRequest.postRequest(body, AppConstants.INTERNAL_END_POINT);
        String textResponse = response.getBody().asString();
        JsonNode jsonResponse = CommonAutomationUtils.convertToJson(textResponse);

        CommonAutomationUtils.verifyCommonResponseFailedValidation(jsonResponse, response.getStatusCode(), HttpStatus.SC_BAD_REQUEST, AppConstants.INVALID_DATA_CODE, AppConstants.INVALID_DATA_ERROR);
    }

    @Test(groups = {"MC:Authentication", "Abdul Rehman", "SECTION:Amount Validation", "SC:Amount"}, description = "Validates transaction authentication for KWD currency with a valid amount", priority = 4)
    public void transactionAuthenticationAmountKWDCurrencyCase() {

        TransactionRequest transactionRequest = AppUtils.transactionRequestInstance();

        transactionRequest.setAmount(10.13);
        transactionRequest.setCurrency("KWD");

        String body = CommonAutomationUtils.stringToJson(transactionRequest);
        Response response = AuthenticationTransactionRequest.postRequest(body, AppConstants.INTERNAL_END_POINT);
        CommonAutomationUtils.verifyCommonResponseSuccessValidation(response, HttpStatus.SC_OK, Map.of(AppConstants.AMOUNT, 10.13, AppConstants.CURRENCY, "KWD"));
    }

    @Test(groups = {"MC:Authentication", "Abdul Rehman", "SECTION:Amount Validation", "SC:Amount"}, description = "Validates transaction authentication for KWD currency with more than two decimal places", priority = 5)
    public void transactionAuthenticationAmountMoreDecimalKWDCurrencyCase() {

        TransactionRequest transactionRequest = AppUtils.transactionRequestInstance();

        transactionRequest.setAmount(10.133);
        transactionRequest.setCurrency("KWD");

        String body = CommonAutomationUtils.stringToJson(transactionRequest);
        Response response = AuthenticationTransactionRequest.postRequest(body, AppConstants.INTERNAL_END_POINT);
        CommonAutomationUtils.verifyCommonResponseSuccessValidation(response, HttpStatus.SC_OK, Map.of(AppConstants.AMOUNT, 10.133, AppConstants.CURRENCY, "KWD"));
    }

    @Test(groups = {"MC:Authentication", "Abdul Rehman", "SECTION:Amount Validation", "SC:Amount"}, description = "Validates transaction authentication fails for KWD currency with more than four decimal places", priority = 6)
    public void transactionAuthenticationAmountMoreFourDecimalSARCurrencyCase() {

        TransactionRequest transactionRequest = AppUtils.transactionRequestInstance();

        transactionRequest.setAmount(10.1234);
        transactionRequest.setCurrency("KWD");

        String body = CommonAutomationUtils.stringToJson(transactionRequest);
        Response response = AuthenticationTransactionRequest.postRequest(body, AppConstants.INTERNAL_END_POINT);
        String textResponse = response.getBody().asString();
        JsonNode jsonResponse = CommonAutomationUtils.convertToJson(textResponse);

        CommonAutomationUtils.verifyCommonResponseFailedValidation(jsonResponse, response.getStatusCode(), HttpStatus.SC_BAD_REQUEST, AppConstants.INVALID_DATA_CODE, AppConstants.INVALID_DATA_ERROR);
    }

    @Test(groups = {"MC:Authentication", "Abdul Rehman", "SECTION:Amount Validation", "SC:Amount"}, description = "Validates transaction authentication for a negative amount", priority = 7)
    public void transactionAuthenticationNegativeAmountCase() {

        TransactionRequest transactionRequest = AppUtils.transactionRequestInstance();
        transactionRequest.setAmount(-10.10);

        String body = CommonAutomationUtils.stringToJson(transactionRequest);
        Response response = AuthenticationTransactionRequest.postRequest(body, AppConstants.INTERNAL_END_POINT);
        String textResponse = response.getBody().asString();
        JsonNode jsonResponse = CommonAutomationUtils.convertToJson(textResponse);

        CommonAutomationUtils.verifyCommonResponseFailedValidation(jsonResponse, response.getStatusCode(), HttpStatus.SC_BAD_REQUEST, AppConstants.INVALID_DATA_CODE, AppConstants.INVALID_DATA_ERROR);
    }

    @Test(groups = {"MC:Authentication", "Abdul Rehman", "SECTION:Amount Validation", "SC:Amount"}, description = "Validates transaction authentication for a valid amount", priority = 8)
    public void transactionAuthenticationValidAmountCase() {

        TransactionRequest transactionRequest = AppUtils.transactionRequestInstance();

        transactionRequest.setAmount(10.11);

        String body = CommonAutomationUtils.stringToJson(transactionRequest);
        Response response = AuthenticationTransactionRequest.postRequest(body, AppConstants.INTERNAL_END_POINT);
        CommonAutomationUtils.verifyCommonResponseSuccessValidation(response, HttpStatus.SC_OK, Map.of(AppConstants.AMOUNT, 10.11));
    }

    @Test(groups = {"MC:Authentication", "Abdul Rehman", "SECTION:Amount Validation", "SC:Amount"}, description = "Validates transaction authentication with a null amount", priority = 9)
    public void transactionAuthenticationNullAmountCase() {

        TransactionRequest transactionRequest = AppUtils.transactionRequestInstance();

        transactionRequest.setAmount(null);

        String body = CommonAutomationUtils.stringToJson(transactionRequest);
        Response response = AuthenticationTransactionRequest.postRequest(body, AppConstants.INTERNAL_END_POINT);
        CommonAutomationUtils.verifyCommonResponseSuccessValidation(response, HttpStatus.SC_OK);
        CommonAutomationUtils.verifyNonAvailableKey(response, List.of(AppConstants.AMOUNT));
    }

    @Test(groups = {"MC:Authentication", "Abdul Rehman", "SECTION:Amount Validation", "SC:Amount"}, description = "Validates transaction authentication for the maximum allowable amount", priority = 10)
    public void transactionAuthenticationMaxAmountCase() {

        TransactionRequest transactionRequest = AppUtils.transactionRequestInstance();

        transactionRequest.setAmount(9999999999.99);

        String body = CommonAutomationUtils.stringToJson(transactionRequest);
        Response response = AuthenticationTransactionRequest.postRequest(body, AppConstants.INTERNAL_END_POINT);
        CommonAutomationUtils.verifyCommonResponseSuccessValidation(response, HttpStatus.SC_OK, Map.of(AppConstants.AMOUNT, 9999999999.99));
    }

    @Test(groups = {"MC:Authentication", "Abdul Rehman", "SECTION:Currency Validation", "SC:Currency"}, description = "Validates transaction authentication with a valid currency (SAR)", priority = 11)
    public void transactionAuthenticationValidCurrencyCase() {

        TransactionRequest transactionRequest = AppUtils.transactionRequestInstance();

        transactionRequest.setCurrency("SAR");

        String body = CommonAutomationUtils.stringToJson(transactionRequest);
        Response response = AuthenticationTransactionRequest.postRequest(body, AppConstants.INTERNAL_END_POINT);
        CommonAutomationUtils.verifyCommonResponseSuccessValidation(response, HttpStatus.SC_OK, Map.of(AppConstants.CURRENCY, "SAR"));
    }

    @Test(groups = {"MC:Authentication", "Abdul Rehman", "SECTION:Currency Validation", "SC:Currency"}, description = "Validates transaction authentication with an unknown but valid currency (YEN)", priority = 12)
    public void transactionAuthenticationValidUnknownCurrencyCase() {

        TransactionRequest transactionRequest = AppUtils.transactionRequestInstance();

        transactionRequest.setCurrency("YEN");

        String body = CommonAutomationUtils.stringToJson(transactionRequest);
        Response response = AuthenticationTransactionRequest.postRequest(body, AppConstants.INTERNAL_END_POINT);
        CommonAutomationUtils.verifyCommonResponseSuccessValidation(response, HttpStatus.SC_OK, Map.of(AppConstants.CURRENCY, "YEN"));
    }

    @Test(groups = {"MC:Authentication", "Abdul Rehman", "SECTION:Currency Validation", "SC:Currency"}, description = "Validates transaction authentication fails with an invalid currency code (ABC)", priority = 13)
    public void transactionAuthenticationInvalidCurrencyCase() {

        TransactionRequest transactionRequest = AppUtils.transactionRequestInstance();

        transactionRequest.setCurrency("ABC");

        String body = CommonAutomationUtils.stringToJson(transactionRequest);
        Response response = AuthenticationTransactionRequest.postRequest(body, AppConstants.INTERNAL_END_POINT);
        String textResponse = response.getBody().asString();
        JsonNode jsonResponse = CommonAutomationUtils.convertToJson(textResponse);

        CommonAutomationUtils.verifyCommonResponseFailedValidation(jsonResponse, response.getStatusCode(), HttpStatus.SC_BAD_REQUEST, AppConstants.INVALID_DATA_CODE, AppConstants.INVALID_DATA_ERROR);
    }

    @Test(groups = {"MC:Authentication", "Abdul Rehman", "SECTION:Currency Validation", "SC:Currency"}, description = "Validates transaction authentication fails when currency is null", priority = 14)
    public void transactionAuthenticationNullCurrencyCase() {

        TransactionRequest transactionRequest = AppUtils.transactionRequestInstance();

        transactionRequest.setCurrency(null);

        String body = CommonAutomationUtils.stringToJson(transactionRequest);
        Response response = AuthenticationTransactionRequest.postRequest(body, AppConstants.INTERNAL_END_POINT);
        String textResponse = response.getBody().asString();
        JsonNode jsonResponse = CommonAutomationUtils.convertToJson(textResponse);

        CommonAutomationUtils.verifyCommonResponseFailedValidation(jsonResponse, response.getStatusCode(), HttpStatus.SC_BAD_REQUEST, AppConstants.INVALID_DATA_CODE, AppConstants.INVALID_DATA_ERROR);
        CommonAutomationUtils.verifyNonAvailableKey(response, List.of(AppConstants.CURRENCY));
    }

    @Test(groups = {"MC:Authentication", "Abdul Rehman", "SECTION:Currency Validation", "SC:Currency"}, description = "Validates transaction authentication fails with an empty currency value", priority = 15)
    public void transactionAuthenticationEmptyCurrencyCase() {

        TransactionRequest transactionRequest = AppUtils.transactionRequestInstance();

        transactionRequest.setCurrency("");

        String body = CommonAutomationUtils.stringToJson(transactionRequest);
        Response response = AuthenticationTransactionRequest.postRequest(body, AppConstants.INTERNAL_END_POINT);
        String textResponse = response.getBody().asString();
        JsonNode jsonResponse = CommonAutomationUtils.convertToJson(textResponse);

        CommonAutomationUtils.verifyCommonResponseFailedValidation(jsonResponse, response.getStatusCode(), HttpStatus.SC_BAD_REQUEST, AppConstants.INVALID_DATA_CODE, AppConstants.INVALID_DATA_ERROR);
    }

    @Test(groups = {"MC:Authentication", "Abdul Rehman", "SECTION:Currency Validation", "SC:Currency"}, description = "Validates transaction authentication fails when currency is numeric (123)", priority = 16)
    public void transactionAuthenticationNumbericCurrencyCase() {

        TransactionRequest transactionRequest = AppUtils.transactionRequestInstance();

        transactionRequest.setCurrency("123");

        String body = CommonAutomationUtils.stringToJson(transactionRequest);
        Response response = AuthenticationTransactionRequest.postRequest(body, AppConstants.INTERNAL_END_POINT);
        String textResponse = response.getBody().asString();
        JsonNode jsonResponse = CommonAutomationUtils.convertToJson(textResponse);

        CommonAutomationUtils.verifyCommonResponseFailedValidation(jsonResponse, response.getStatusCode(), HttpStatus.SC_BAD_REQUEST, AppConstants.INVALID_DATA_CODE, AppConstants.INVALID_DATA_ERROR);
    }

    @Test(groups = {"MC:Authentication", "Abdul Rehman", "SECTION:Save Card Validation", "SC:Save Card"}, description = "Validates transaction authentication with save card option set to false", priority = 17)
    public void transactionAuthenticationFalseSaveCardCase() {

        TransactionRequest transactionRequest = AppUtils.transactionRequestInstance();

        transactionRequest.setSaveCard(false);

        String body = CommonAutomationUtils.stringToJson(transactionRequest);
        Response response = AuthenticationTransactionRequest.postRequest(body, AppConstants.INTERNAL_END_POINT);
        CommonAutomationUtils.verifyCommonResponseSuccessValidation(response, HttpStatus.SC_OK, Map.of(AppConstants.SAVE_CARD, false));
    }

    @Test(groups = {"MC:Authentication", "Abdul Rehman", "SECTION:Save Card Validation", "SC:Save Card"}, description = "Validates transaction authentication with save card option set to true", priority = 18)
    public void transactionAuthenticationTrueSaveCardCase() {

        TransactionRequest transactionRequest = AppUtils.transactionRequestInstance();

        transactionRequest.setSaveCard(true);

        String body = CommonAutomationUtils.stringToJson(transactionRequest);
        Response response = AuthenticationTransactionRequest.postRequest(body, AppConstants.INTERNAL_END_POINT);
        CommonAutomationUtils.verifyCommonResponseSuccessValidation(response, HttpStatus.SC_OK, Map.of(AppConstants.SAVE_CARD, true));
    }

    @Test(groups = {"MC:Authentication", "Abdul Rehman", "SECTION:Save Card Validation", "SC:Save Card"}, description = "Validates transaction authentication with save card option set to null", priority = 19)
    public void transactionAuthenticationNullSaveCardCase() {

        TransactionRequest transactionRequest = AppUtils.transactionRequestInstance();

        transactionRequest.setSaveCard(null);

        String body = CommonAutomationUtils.stringToJson(transactionRequest);
        Response response = AuthenticationTransactionRequest.postRequest(body, AppConstants.INTERNAL_END_POINT);
        CommonAutomationUtils.verifyCommonResponseSuccessValidation(response, HttpStatus.SC_OK);
        CommonAutomationUtils.verifyNonAvailableKey(response, List.of(AppConstants.SAVE_CARD));
    }

    @Test(groups = {"MC:Authentication", "Abdul Rehman", "SECTION:Description Validation", "SC:Description"}, description = "Validates transaction authentication with a valid description", priority = 20)
    public void transactionAuthenticationValidDescriptionCase() {

        TransactionRequest transactionRequest = AppUtils.transactionRequestInstance();
        String body = CommonAutomationUtils.stringToJson(transactionRequest);
        Response response = AuthenticationTransactionRequest.postRequest(body, AppConstants.INTERNAL_END_POINT);
        CommonAutomationUtils.verifyCommonResponseSuccessValidation(response, HttpStatus.SC_OK, Map.of(AppConstants.DESCRIPTION, "Test Description"));
    }

    @Test(groups = {"MC:Authentication", "Abdul Rehman", "SECTION:Description Validation", "SC:Description"}, description = "Validates transaction authentication with an empty description", priority = 21)
    public void transactionAuthenticationEmptyDescriptionCase() {

        TransactionRequest transactionRequest = AppUtils.transactionRequestInstance();
        transactionRequest.setDescription("");
        String body = CommonAutomationUtils.stringToJson(transactionRequest);
        Response response = AuthenticationTransactionRequest.postRequest(body, AppConstants.INTERNAL_END_POINT);
        CommonAutomationUtils.verifyCommonResponseSuccessValidation(response, HttpStatus.SC_OK, Map.of(AppConstants.DESCRIPTION, ""));
    }

    @Test(groups = {"MC:Authentication", "Abdul Rehman", "SECTION:Description Validation", "SC:Description"}, description = "Validates transaction authentication with a null description", priority = 22)
    public void transactionAuthenticationNullDescriptionCase() {

        TransactionRequest transactionRequest = AppUtils.transactionRequestInstance();
        transactionRequest.setDescription(null);
        String body = CommonAutomationUtils.stringToJson(transactionRequest);
        Response response = AuthenticationTransactionRequest.postRequest(body, AppConstants.INTERNAL_END_POINT);
        CommonAutomationUtils.verifyCommonResponseSuccessValidation(response, HttpStatus.SC_OK);
        CommonAutomationUtils.verifyNonAvailableKey(response, List.of(AppConstants.DESCRIPTION));
    }

    @Test(groups = {"MC:Authentication", "Abdul Rehman", "SECTION:Description Validation", "SC:Description"}, description = "Validates transaction authentication with a numeric description", priority = 23)
    public void transactionAuthenticationNumericDescriptionCase() {

        TransactionRequest transactionRequest = AppUtils.transactionRequestInstance();
        transactionRequest.setDescription(12345);
        String body = CommonAutomationUtils.stringToJson(transactionRequest);
        Response response = AuthenticationTransactionRequest.postRequest(body, AppConstants.INTERNAL_END_POINT);
        CommonAutomationUtils.verifyCommonResponseSuccessValidation(response, HttpStatus.SC_OK, Map.of(AppConstants.DESCRIPTION, "12345"));
    }

    @Test(groups = {"MC:Authentication", "Abdul Rehman", "SECTION:Transaction Validation", "SC:Transaction"}, description = "Validates transaction authentication with a null transaction", priority = 24)
    public void transactionAuthenticationNullTransactionCase() {

        TransactionRequest transactionRequest = AppUtils.transactionRequestInstance();
        transactionRequest.setTransaction(null);
        String body = CommonAutomationUtils.stringToJson(transactionRequest);
        Response response = AuthenticationTransactionRequest.postRequest(body, AppConstants.INTERNAL_END_POINT);
        CommonAutomationUtils.verifyCommonResponseSuccessValidation(response, HttpStatus.SC_OK);
    }

    @Test(groups = {"MC:Authentication", "Abdul Rehman", "SECTION:Transaction Id Validation", "SC:Transaction Id"}, description = "Validates transaction authentication with a valid charge transaction ID", priority = 25)
    public void transactionAuthenticationChargeTransactionIdCase() {

        UUID uuid = UUID.randomUUID();
        TransactionRequest transactionRequest = AppUtils.transactionRequestInstance();
        TransactionRequest.Transaction transaction = transactionRequest.getTransaction();
        transaction.setId("trx_charge_" + uuid);
        transaction.setType(TransactionType.CHARGE);

        String body = CommonAutomationUtils.stringToJson(transactionRequest);
        Response response = AuthenticationTransactionRequest.postRequest(body, AppConstants.INTERNAL_END_POINT);
        CommonAutomationUtils.verifyCommonResponseSuccessValidation(response, HttpStatus.SC_OK, Map.of(AppConstants.TRANSACTION + "." + AppConstants.ID, "trx_charge_" + uuid,
                AppConstants.TRANSACTION + "." + AppConstants.TYPE, "CHARGE"));
    }

    @Test(groups = {"MC:Authentication", "Abdul Rehman", "SECTION:Transaction Id Validation", "SC:Transaction Id"}, description = "Validates transaction authentication with a valid authorize transaction ID", priority = 26)
    public void transactionAuthenticationAuthorizeTransactionTypeIdCase() {

        UUID uuid = UUID.randomUUID();
        TransactionRequest transactionRequest = AppUtils.transactionRequestInstance();
        TransactionRequest.Transaction transaction = transactionRequest.getTransaction();
        transaction.setId("trx_charge_" + uuid);
        transaction.setType(TransactionType.AUTHORIZE);

        String body = CommonAutomationUtils.stringToJson(transactionRequest);
        Response response = AuthenticationTransactionRequest.postRequest(body, AppConstants.INTERNAL_END_POINT);
        CommonAutomationUtils.verifyCommonResponseSuccessValidation(response, HttpStatus.SC_OK, Map.of(AppConstants.TRANSACTION + "." + AppConstants.ID, "trx_charge_" + uuid,
                AppConstants.TRANSACTION + "." + AppConstants.TYPE, "AUTHORIZE"));
    }

    @Test(groups = {"MC:Authentication", "Abdul Rehman", "SECTION:Transaction Id Validation", "SC:Transaction Id"}, description = "Validates transaction authentication with an empty transaction ID", priority = 27)
    public void transactionAuthenticationEmptyTransactionIdCase() {

        TransactionRequest transactionRequest = AppUtils.transactionRequestInstance();
        TransactionRequest.Transaction transaction = transactionRequest.getTransaction();
        transaction.setId("");
        transaction.setType(TransactionType.CHARGE);

        String body = CommonAutomationUtils.stringToJson(transactionRequest);
        Response response = AuthenticationTransactionRequest.postRequest(body, AppConstants.INTERNAL_END_POINT);
        CommonAutomationUtils.verifyCommonResponseSuccessValidation(response, HttpStatus.SC_OK, Map.of(AppConstants.TRANSACTION + "." + AppConstants.TYPE, "CHARGE"));
    }

    @Test(groups = {"MC:Authentication", "Abdul Rehman", "SECTION:Transaction Id Validation", "SC:Transaction Id"}, description = "Validates transaction authentication with a null transaction ID", priority = 28)
    public void transactionAuthenticationNullTransactionIdCase() {

        TransactionRequest transactionRequest = AppUtils.transactionRequestInstance();
        TransactionRequest.Transaction transaction = transactionRequest.getTransaction();
        transaction.setId(null);
        transaction.setType(TransactionType.CHARGE);

        String body = CommonAutomationUtils.stringToJson(transactionRequest);
        Response response = AuthenticationTransactionRequest.postRequest(body, AppConstants.INTERNAL_END_POINT);
        CommonAutomationUtils.verifyCommonResponseSuccessValidation(response, HttpStatus.SC_OK, Map.of(AppConstants.TRANSACTION + "." + AppConstants.TYPE, "CHARGE"));
        CommonAutomationUtils.verifyNonAvailableKey(response, List.of(AppConstants.TRANSACTION + "." + AppConstants.ID));
    }

    @Test(groups = {"MC:Authentication", "Abdul Rehman", "SECTION:Transaction Type Validation", "SC:Transaction Type"}, description = "Validates transaction authentication with an empty transaction type ID", priority = 29)
    public void transactionAuthenticationEmptyTransactionTypeIdCase() {

        TransactionRequest transactionRequest = AppUtils.transactionRequestInstance();
        TransactionRequest.Transaction transaction = transactionRequest.getTransaction();
        transaction.setId("trx_charge_" + UUID.randomUUID());

        String body = CommonAutomationUtils.stringToJson(transactionRequest);
        String updatedBody = CommonAutomationUtils.modifyJson(body, "MODIFY", "transaction.type", "");
        Response response = AuthenticationTransactionRequest.postRequest(updatedBody, AppConstants.INTERNAL_END_POINT);
        String textResponse = response.getBody().asString();
        JsonNode jsonResponse = CommonAutomationUtils.convertToJson(textResponse);

        CommonAutomationUtils.verifyCommonResponseFailedValidation(jsonResponse, response.getStatusCode(), HttpStatus.SC_BAD_REQUEST, AppConstants.INVALID_DATA_CODE, AppConstants.INVALID_DATA_ERROR);
    }

    @Test(groups = {"MC:Authentication", "Abdul Rehman", "SECTION:Transaction Type Validation", "SC:Transaction Type"}, description = "Validates transaction authentication with an invalid transaction type ID", priority = 30)
    public void transactionAuthenticationInvalidTransactionTypeIdCase() {

        TransactionRequest transactionRequest = AppUtils.transactionRequestInstance();
        TransactionRequest.Transaction transaction = transactionRequest.getTransaction();
        transaction.setId("trx_charge_" + UUID.randomUUID());
        transaction.setType(TransactionType.CHARGE);

        String body = CommonAutomationUtils.stringToJson(transactionRequest);
        String updatedBody = CommonAutomationUtils.modifyJson(body, "MODIFY", "transaction.type", "INVALID_TYPE");
        Response response = AuthenticationTransactionRequest.postRequest(updatedBody, AppConstants.INTERNAL_END_POINT);
        String textResponse = response.getBody().asString();
        JsonNode jsonResponse = CommonAutomationUtils.convertToJson(textResponse);

        CommonAutomationUtils.verifyCommonResponseFailedValidation(jsonResponse, response.getStatusCode(), HttpStatus.SC_BAD_REQUEST, AppConstants.INVALID_DATA_CODE, AppConstants.INVALID_DATA_ERROR);
    }

    @Test(groups = {"MC:Authentication", "Abdul Rehman", "SECTION:Transaction Type Validation", "SC:Transaction Type"}, description = "Validates transaction authentication with a null transaction type ID", priority = 31)
    public void transactionAuthenticationNullTransactionTypeIdCase() {

        TransactionRequest transactionRequest = AppUtils.transactionRequestInstance();
        TransactionRequest.Transaction transaction = transactionRequest.getTransaction();
        transaction.setId("trx_charge_" + UUID.randomUUID());
        transaction.setType(null);

        String body = CommonAutomationUtils.stringToJson(transactionRequest);
        Response response = AuthenticationTransactionRequest.postRequest(body, AppConstants.INTERNAL_END_POINT);
        String textResponse = response.getBody().asString();
        JsonNode jsonResponse = CommonAutomationUtils.convertToJson(textResponse);

        CommonAutomationUtils.verifyCommonResponseFailedValidation(jsonResponse, response.getStatusCode(), HttpStatus.SC_BAD_REQUEST, AppConstants.INVALID_DATA_CODE, AppConstants.INVALID_DATA_ERROR);
    }

    @Test(groups = {"MC:Authentication", "Abdul Rehman", "SECTION:Reference Validation", "SC:Reference"}, description = "Validates transaction authentication fails with a null reference value", priority = 32)
    public void transactionAuthenticationNullReferenceCase() {

        TransactionRequest transactionRequest = AppUtils.transactionRequestInstance();

        transactionRequest.setReference(null);

        String body = CommonAutomationUtils.stringToJson(transactionRequest);
        Response response = AuthenticationTransactionRequest.postRequest(body, AppConstants.INTERNAL_END_POINT);

        CommonAutomationUtils.verifyCommonResponseSuccessValidation(response, HttpStatus.SC_OK);
        CommonAutomationUtils.verifyNonAvailableKey(response, List.of(AppConstants.REFERENCE));
    }

    @Test(groups = {"MC:Authentication", "Abdul Rehman", "SECTION:Reference Validation", "SC:Reference"}, description = "Validates transaction authentication with a valid reference transaction", priority = 33)
    public void transactionAuthenticationValidReferenceTransactionCase() {

        TransactionRequest transactionRequest = AppUtils.transactionRequestInstance();

        String body = CommonAutomationUtils.stringToJson(transactionRequest);
        Response response = AuthenticationTransactionRequest.postRequest(body, AppConstants.INTERNAL_END_POINT);

        CommonAutomationUtils.verifyCommonResponseSuccessValidation(response, HttpStatus.SC_OK, Map.of(AppConstants.REFERENCE + "." + AppConstants.TRANSACTION, "tck_LV02G0720231644Xj5u3007999",
                AppConstants.REFERENCE + "." + AppConstants.ORDER, "7730231644079995502"));
    }

    @Test(groups = {"MC:Authentication", "Abdul Rehman", "SECTION:Reference Validation", "SC:Reference"}, description = "Validates transaction authentication with a null reference transaction value", priority = 34)
    public void transactionAuthenticationReferenceNullTransactionCase() {

        TransactionRequest transactionRequest = AppUtils.transactionRequestInstance();
        transactionRequest.getReference().setTransaction(null);

        String body = CommonAutomationUtils.stringToJson(transactionRequest);
        Response response = AuthenticationTransactionRequest.postRequest(body, AppConstants.INTERNAL_END_POINT);

        CommonAutomationUtils.verifyCommonResponseSuccessValidation(response, HttpStatus.SC_OK, Map.of(AppConstants.REFERENCE + "." + AppConstants.ORDER, "7730231644079995502"));
        CommonAutomationUtils.verifyNonAvailableKey(response, List.of(AppConstants.REFERENCE + "." + AppConstants.TRANSACTION));
    }

    @Test(groups = {"MC:Authentication", "Abdul Rehman", "SECTION:Reference Validation", "SC:Reference"}, description = "Validates transaction authentication with an empty reference transaction value", priority = 35)
    public void transactionAuthenticationReferenceEmptyTransactionCase() {

        TransactionRequest transactionRequest = AppUtils.transactionRequestInstance();
        transactionRequest.getReference().setTransaction("");

        String body = CommonAutomationUtils.stringToJson(transactionRequest);
        Response response = AuthenticationTransactionRequest.postRequest(body, AppConstants.INTERNAL_END_POINT);

        CommonAutomationUtils.verifyCommonResponseSuccessValidation(response, HttpStatus.SC_OK, Map.of(AppConstants.REFERENCE + "." + AppConstants.TRANSACTION, "",
                AppConstants.REFERENCE + "." + AppConstants.ORDER, "7730231644079995502"));
    }

    @Test(groups = {"MC:Authentication", "Abdul Rehman", "SECTION:Reference Validation", "SC:Reference"}, description = "Validates transaction authentication with an empty reference order value", priority = 36)
    public void transactionAuthenticationReferenceEmptyOrderCase() {

        TransactionRequest transactionRequest = AppUtils.transactionRequestInstance();
        transactionRequest.getReference().setOrder("");

        String body = CommonAutomationUtils.stringToJson(transactionRequest);
        Response response = AuthenticationTransactionRequest.postRequest(body, AppConstants.INTERNAL_END_POINT);

        CommonAutomationUtils.verifyCommonResponseSuccessValidation(response, HttpStatus.SC_OK, Map.of(AppConstants.REFERENCE + "." + AppConstants.TRANSACTION, "tck_LV02G0720231644Xj5u3007999",
                AppConstants.REFERENCE + "." + AppConstants.ORDER, ""));
    }

    @Test(groups = {"MC:Authentication", "Abdul Rehman", "SECTION:Reference Validation", "SC:Reference"}, description = "Validates transaction authentication with a null reference order value", priority = 37)
    public void transactionAuthenticationReferenceNullOrderCase() {

        TransactionRequest transactionRequest = AppUtils.transactionRequestInstance();
        transactionRequest.getReference().setOrder(null);

        String body = CommonAutomationUtils.stringToJson(transactionRequest);
        Response response = AuthenticationTransactionRequest.postRequest(body, AppConstants.INTERNAL_END_POINT);

        CommonAutomationUtils.verifyCommonResponseSuccessValidation(response, HttpStatus.SC_OK, Map.of(AppConstants.REFERENCE + "." + AppConstants.TRANSACTION, "tck_LV02G0720231644Xj5u3007999"));
        CommonAutomationUtils.verifyNonAvailableKey(response, List.of(AppConstants.REFERENCE + "." + AppConstants.ORDER));
    }

    @Test(groups = {"MC:Authentication", "Abdul Rehman", "SECTION:Invoice Validation", "SC:Invoice"}, description = "Validates transaction authentication with a null invoice value", priority = 38)
    public void transactionAuthenticationNullInvoiceCase() {

        TransactionRequest transactionRequest = AppUtils.transactionRequestInstance();
        transactionRequest.setInvoice(null);

        String body = CommonAutomationUtils.stringToJson(transactionRequest);
        Response response = AuthenticationTransactionRequest.postRequest(body, AppConstants.INTERNAL_END_POINT);

        CommonAutomationUtils.verifyCommonResponseSuccessValidation(response, HttpStatus.SC_OK);
        CommonAutomationUtils.verifyNonAvailableKey(response, List.of(AppConstants.INVOICE));
    }

    @Test(groups = {"MC:Authentication", "Abdul Rehman", "SECTION:Invoice Id Validation", "SC:Invoice Id"}, description = "Validates transaction authentication with a valid invoice ID", priority = 39)
    public void transactionAuthenticationValidInvoiceIdCase() {

        TransactionRequest transactionRequest = AppUtils.transactionRequestInstance();
        transactionRequest.getInvoice().setId("invoice_12345");

        String body = CommonAutomationUtils.stringToJson(transactionRequest);
        Response response = AuthenticationTransactionRequest.postRequest(body, AppConstants.INTERNAL_END_POINT);

        CommonAutomationUtils.verifyCommonResponseSuccessValidation(response, HttpStatus.SC_OK, Map.of(AppConstants.INVOICE + "." + AppConstants.ID, "invoice_12345"));
    }

    @Test(groups = {"MC:Authentication", "Abdul Rehman", "SECTION:Invoice Id Validation", "SC:Invoice Id"}, description = "Validates transaction authentication with an empty invoice ID", priority = 40)
    public void transactionAuthenticationEmptyInvoiceIdCase() {

        TransactionRequest transactionRequest = AppUtils.transactionRequestInstance();
        transactionRequest.getInvoice().setId("");

        String body = CommonAutomationUtils.stringToJson(transactionRequest);
        Response response = AuthenticationTransactionRequest.postRequest(body, AppConstants.INTERNAL_END_POINT);

        CommonAutomationUtils.verifyCommonResponseSuccessValidation(response, HttpStatus.SC_OK, Map.of(AppConstants.INVOICE + "." + AppConstants.ID, ""));
    }

    @Test(groups = {"MC:Authentication", "Abdul Rehman", "SECTION:Invoice Id Validation", "SC:Invoice Id"}, description = "Validates transaction authentication with a null invoice ID", priority = 41)
    public void transactionAuthenticationNullInvoiceIdCase() {

        TransactionRequest transactionRequest = AppUtils.transactionRequestInstance();
        transactionRequest.getInvoice().setId(null);

        String body = CommonAutomationUtils.stringToJson(transactionRequest);
        Response response = AuthenticationTransactionRequest.postRequest(body, AppConstants.INTERNAL_END_POINT);

        CommonAutomationUtils.verifyCommonResponseSuccessValidation(response, HttpStatus.SC_OK);
        CommonAutomationUtils.verifyNonAvailableKey(response, List.of(AppConstants.INVOICE + "." + AppConstants.ID));
    }

    @Test(groups = {"MC:Authentication", "Abdul Rehman", "SECTION:Customer Validation", "SC:Customer"}, description = "Validates transaction authentication with a null customer value", priority = 42)
    public void transactionAuthenticationNullCustomerCase() {

        TransactionRequest transactionRequest = AppUtils.transactionRequestInstance();
        transactionRequest.setCustomer(null);

        String body = CommonAutomationUtils.stringToJson(transactionRequest);
        Response response = AuthenticationTransactionRequest.postRequest(body, AppConstants.INTERNAL_END_POINT);

        CommonAutomationUtils.verifyCommonResponseSuccessValidation(response, HttpStatus.SC_OK);
        CommonAutomationUtils.verifyNonAvailableKey(response, List.of(AppConstants.CUSTOMER));
    }

    @Test(groups = {"MC:Authentication", "Abdul Rehman", "SECTION:Customer Id Validation", "SC:Customer Id"}, description = "Validates transaction authentication with a valid customer ID", priority = 43)
    public void transactionAuthenticationValidCustomerIdCase() {

        TransactionRequest transactionRequest = AppUtils.transactionRequestInstance();

        String body = CommonAutomationUtils.stringToJson(transactionRequest);
        Response response = AuthenticationTransactionRequest.postRequest(body, AppConstants.INTERNAL_END_POINT);

        CommonAutomationUtils.verifyCommonResponseSuccessValidation(response, HttpStatus.SC_OK, Map.of(AppConstants.CUSTOMER + "." + AppConstants.ID, "cus_TS06A5420231128h9FP1110651"));
    }

    @Test(groups = {"MC:Authentication", "Abdul Rehman", "SECTION:Customer Id Validation", "SC:Customer Id"}, description = "Validates transaction authentication with an invalid customer ID", priority = 44)
    public void transactionAuthenticationInvalidCustomerIdCase() {

        TransactionRequest transactionRequest = AppUtils.transactionRequestInstance();
        transactionRequest.getCustomer().setId("invalid_id");

        String body = CommonAutomationUtils.stringToJson(transactionRequest);
        Response response = AuthenticationTransactionRequest.postRequest(body, AppConstants.INTERNAL_END_POINT);

        CommonAutomationUtils.verifyCommonResponseSuccessValidation(response, HttpStatus.SC_OK, Map.of(AppConstants.CUSTOMER + "." + AppConstants.ID, "invalid_id"));
    }

    @Test(groups = {"MC:Authentication", "Abdul Rehman", "SECTION:Customer Id Validation", "SC:Customer Id"}, description = "Validates transaction authentication with an empty customer ID", priority = 45)
    public void transactionAuthenticationEmptyCustomerIdCase() {

        TransactionRequest transactionRequest = AppUtils.transactionRequestInstance();
        transactionRequest.getCustomer().setId("");

        String body = CommonAutomationUtils.stringToJson(transactionRequest);
        Response response = AuthenticationTransactionRequest.postRequest(body, AppConstants.INTERNAL_END_POINT);

        CommonAutomationUtils.verifyCommonResponseSuccessValidation(response, HttpStatus.SC_OK, Map.of(AppConstants.CUSTOMER + "." + AppConstants.ID, ""));
    }

    @Test(groups = {"MC:Authentication", "Abdul Rehman", "SECTION:Customer Id Validation", "SC:Customer Id"}, description = "Validates transaction authentication with a null customer ID", priority = 46)
    public void transactionAuthenticationNullCustomerIdCase() {

        TransactionRequest transactionRequest = AppUtils.transactionRequestInstance();
        transactionRequest.getCustomer().setId(null);

        String body = CommonAutomationUtils.stringToJson(transactionRequest);
        Response response = AuthenticationTransactionRequest.postRequest(body, AppConstants.INTERNAL_END_POINT);

        CommonAutomationUtils.verifyCommonResponseSuccessValidation(response, HttpStatus.SC_OK);
        CommonAutomationUtils.verifyNonAvailableKey(response, List.of(AppConstants.CUSTOMER + "." + AppConstants.ID));
    }

    @Test(groups = {"MC:Authentication", "Abdul Rehman", "SECTION:Customer Name Validation", "SC:Customer Name"}, description = "Validates transaction authentication with a null customer name value", priority = 47)
    public void transactionAuthenticationNullCustomerNameCase() {

        TransactionRequest transactionRequest = AppUtils.transactionRequestInstance();
        transactionRequest.getCustomer().setName(null);

        String body = CommonAutomationUtils.stringToJson(transactionRequest);
        Response response = AuthenticationTransactionRequest.postRequest(body, AppConstants.INTERNAL_END_POINT);

        CommonAutomationUtils.verifyCommonResponseSuccessValidation(response, HttpStatus.SC_OK);
        CommonAutomationUtils.verifyNonAvailableKey(response, List.of(AppConstants.CUSTOMER + "." + AppConstants.NAME));
    }

    @Test(groups = {"MC:Authentication", "Abdul Rehman", "SECTION:Customer Name Validation", "SC:Customer Name"}, description = "Validates transaction authentication fails with an empty customer first name value", priority = 48)
    public void transactionAuthenticationEmptyCustomerNameFirstNameCase() {

        TransactionRequest transactionRequest = AppUtils.transactionRequestInstance();
        TransactionRequest.Customer customer = transactionRequest.getCustomer();
        List<TransactionRequest.Customer.Name> nameList = customer.getName();
        nameList.get(0).setFirstName("");

        String body = CommonAutomationUtils.stringToJson(transactionRequest);
        Response response = AuthenticationTransactionRequest.postRequest(body, AppConstants.INTERNAL_END_POINT);
        String textResponse = response.getBody().asString();
        JsonNode jsonResponse = CommonAutomationUtils.convertToJson(textResponse);

        CommonAutomationUtils.verifyCommonResponseFailedValidation(jsonResponse, response.getStatusCode(), HttpStatus.SC_BAD_REQUEST, AppConstants.REQUIRED_INPUT_INVALID_CODE, AppConstants.REQUIRED_INPUT_INVALID_ERROR);
    }

    @Test(groups = {"MC:Authentication", "Abdul Rehman", "SECTION:Customer Name Validation", "SC:Customer Name"}, description = "Validates transaction authentication with a null customer first name value", priority = 49)
    public void transactionAuthenticationNullCustomerNameFirstNameCase() {

        TransactionRequest transactionRequest = AppUtils.transactionRequestInstance();
        TransactionRequest.Customer customer = transactionRequest.getCustomer();
        List<TransactionRequest.Customer.Name> nameList = customer.getName();
        nameList.get(0).setFirstName(null);

        String body = CommonAutomationUtils.stringToJson(transactionRequest);
        Response response = AuthenticationTransactionRequest.postRequest(body, AppConstants.INTERNAL_END_POINT);

        CommonAutomationUtils.verifyCommonResponseSuccessValidation(response, HttpStatus.SC_OK);
        CommonAutomationUtils.verifyNonAvailableKey(response, List.of(AppConstants.CUSTOMER + "." + AppConstants.NAME + "." + AppConstants.FIRST_NAME));
    }

    @Test(groups = {"MC:Authentication", "Abdul Rehman", "SECTION:Customer Name Firstname Validation", "SC:Customer Name Firstname"}, description = "Validates transaction authentication with a valid customer first name value", priority = 50)
    public void transactionAuthenticationValidCustomerNameFirstNameCase() {

        TransactionRequest transactionRequest = AppUtils.transactionRequestInstance();
        TransactionRequest.Customer customer = transactionRequest.getCustomer();
        List<TransactionRequest.Customer.Name> nameList = customer.getName();
        nameList.get(0).setFirstName("Ali Usman");

        String body = CommonAutomationUtils.stringToJson(transactionRequest);
        Response response = AuthenticationTransactionRequest.postRequest(body, AppConstants.INTERNAL_END_POINT);

        CommonAutomationUtils.verifyCommonResponseSuccessValidation(response, HttpStatus.SC_OK, Map.of(AppConstants.CUSTOMER + "." + AppConstants.NAME + "[0]." + AppConstants.FIRST_NAME, "Ali Usman"));
    }

    @Test(groups = {"MC:Authentication", "Abdul Rehman", "SECTION:Customer Name Firstname Validation", "SC:Customer Name Firstname"}, description = "Validates transaction authentication with a numeric customer first name value", priority = 51)
    public void transactionAuthenticationNumericCustomerNameFirstNameCase() {

        TransactionRequest transactionRequest = AppUtils.transactionRequestInstance();
        TransactionRequest.Customer customer = transactionRequest.getCustomer();
        List<TransactionRequest.Customer.Name> nameList = customer.getName();
        nameList.get(0).setFirstName("12345");

        String body = CommonAutomationUtils.stringToJson(transactionRequest);
        String updatedBody = CommonAutomationUtils.modifyJson(body, "MODIFY", "customer.name.first_name", 12345);
        Response response = AuthenticationTransactionRequest.postRequest(updatedBody, AppConstants.INTERNAL_END_POINT);

        CommonAutomationUtils.verifyCommonResponseSuccessValidation(response, HttpStatus.SC_OK, Map.of(AppConstants.CUSTOMER + "." + AppConstants.NAME + "[0]." + AppConstants.FIRST_NAME, "12345"));
    }

    @Test(groups = {"MC:Authentication", "Abdul Rehman", "SECTION:Customer Name Firstname Validation", "SC:Customer Name Firstname"}, description = "Validates transaction authentication fails with a customer first name value exceeding the maximum length", priority = 52)
    public void transactionAuthenticationMaxCustomerNameFirstNameCase() {

        TransactionRequest transactionRequest = AppUtils.transactionRequestInstance();
        TransactionRequest.Customer customer = transactionRequest.getCustomer();
        List<TransactionRequest.Customer.Name> nameList = customer.getName();
        nameList.get(0).setFirstName("MaximillianAlessandroValentinHarringtonTheThirdOfEvergreenEstateOnTheBanksOfRiverThamesWithGoldenHairAndEyesOfEmeraldGreenTheSeekerOfTruthInTheLandOfTheRisingSun");

        String body = CommonAutomationUtils.stringToJson(transactionRequest);
        Response response = AuthenticationTransactionRequest.postRequest(body, AppConstants.INTERNAL_END_POINT);
        String textResponse = response.getBody().asString();
        JsonNode jsonResponse = CommonAutomationUtils.convertToJson(textResponse);

        CommonAutomationUtils.verifyCommonResponseFailedValidation(jsonResponse, response.getStatusCode(), HttpStatus.SC_BAD_REQUEST, AppConstants.REQUIRED_INPUT_INVALID_CODE, AppConstants.REQUIRED_INPUT_INVALID_ERROR);
    }

    @Test(groups = {"MC:Authentication", "Abdul Rehman", "SECTION:Customer Name Middlename Validation", "SC:Customer Name Middlename"}, description = "Validates transaction authentication with an empty customer middle name value", priority = 53)
    public void transactionAuthenticationEmptyCustomerNameMiddleNameCase() {

        TransactionRequest transactionRequest = AppUtils.transactionRequestInstance();
        TransactionRequest.Customer customer = transactionRequest.getCustomer();
        List<TransactionRequest.Customer.Name> nameList = customer.getName();
        nameList.get(0).setMiddleName("");

        String body = CommonAutomationUtils.stringToJson(transactionRequest);
        Response response = AuthenticationTransactionRequest.postRequest(body, AppConstants.INTERNAL_END_POINT);

        CommonAutomationUtils.verifyCommonResponseSuccessValidation(response, HttpStatus.SC_OK, Map.of(AppConstants.CUSTOMER + "." + AppConstants.NAME + "[0]." + AppConstants.MIDDLE_NAME, ""));
    }

    @Test(groups = {"MC:Authentication", "Abdul Rehman", "SECTION:Customer Name Middlename Validation", "SC:Customer Name Middlename"}, description = "Validates transaction authentication with a null customer middle name value", priority = 54)
    public void transactionAuthenticationNullCustomerNameMiddleNameCase() {

        TransactionRequest transactionRequest = AppUtils.transactionRequestInstance();
        TransactionRequest.Customer customer = transactionRequest.getCustomer();
        List<TransactionRequest.Customer.Name> nameList = customer.getName();
        nameList.get(0).setMiddleName(null);

        String body = CommonAutomationUtils.stringToJson(transactionRequest);
        Response response = AuthenticationTransactionRequest.postRequest(body, AppConstants.INTERNAL_END_POINT);

        CommonAutomationUtils.verifyCommonResponseSuccessValidation(response, HttpStatus.SC_OK);
        CommonAutomationUtils.verifyNonAvailableKey(response, List.of(AppConstants.CUSTOMER + "." + AppConstants.NAME + "." + AppConstants.MIDDLE_NAME));
    }

    @Test(groups = {"MC:Authentication", "Abdul Rehman", "SECTION:Customer Name Middlename Validation", "SC:Customer Name Middlename"}, description = "Validates transaction authentication with a valid customer middle name value", priority = 55)
    public void transactionAuthenticationValidCustomerNameMiddleNameCase() {

        TransactionRequest transactionRequest = AppUtils.transactionRequestInstance();
        TransactionRequest.Customer customer = transactionRequest.getCustomer();
        List<TransactionRequest.Customer.Name> nameList = customer.getName();
        nameList.get(0).setMiddleName("Ali Usman");

        String body = CommonAutomationUtils.stringToJson(transactionRequest);
        Response response = AuthenticationTransactionRequest.postRequest(body, AppConstants.INTERNAL_END_POINT);

        CommonAutomationUtils.verifyCommonResponseSuccessValidation(response, HttpStatus.SC_OK, Map.of(AppConstants.CUSTOMER + "." + AppConstants.NAME + "[0]." + AppConstants.MIDDLE_NAME, "Ali Usman"));
    }

    @Test(groups = {"MC:Authentication", "Abdul Rehman", "SECTION:Customer Name Middlename Validation", "SC:Customer Name Middlename"}, description = "Validates transaction authentication with a numeric customer middle name value", priority = 56)
    public void transactionAuthenticationNumericCustomerNameMiddleNameCase() {

        TransactionRequest transactionRequest = AppUtils.transactionRequestInstance();
        TransactionRequest.Customer customer = transactionRequest.getCustomer();
        List<TransactionRequest.Customer.Name> nameList = customer.getName();
        nameList.get(0).setMiddleName("12345");

        String body = CommonAutomationUtils.stringToJson(transactionRequest);
        String updatedBody = CommonAutomationUtils.modifyJson(body, "MODIFY", "customer.name.middle_name", 12345);
        Response response = AuthenticationTransactionRequest.postRequest(updatedBody, AppConstants.INTERNAL_END_POINT);

        CommonAutomationUtils.verifyCommonResponseSuccessValidation(response, HttpStatus.SC_OK, Map.of(AppConstants.CUSTOMER + "." + AppConstants.NAME + "[0]." + AppConstants.MIDDLE_NAME, "12345"));
    }

    @Test(groups = {"MC:Authentication", "Abdul Rehman", "SECTION:Customer Name Lastname Validation", "SC:Customer Name Lastname"}, description = "Validates transaction authentication with an empty customer last name value", priority = 57)
    public void transactionAuthenticationEmptyCustomerNameLastNameCase() {

        TransactionRequest transactionRequest = AppUtils.transactionRequestInstance();
        TransactionRequest.Customer customer = transactionRequest.getCustomer();
        List<TransactionRequest.Customer.Name> nameList = customer.getName();
        nameList.get(0).setLastName("");

        String body = CommonAutomationUtils.stringToJson(transactionRequest);
        Response response = AuthenticationTransactionRequest.postRequest(body, AppConstants.INTERNAL_END_POINT);

        CommonAutomationUtils.verifyCommonResponseSuccessValidation(response, HttpStatus.SC_OK, Map.of(AppConstants.CUSTOMER + "." + AppConstants.NAME + "[0]." + AppConstants.LAST_NAME, ""));
    }

    @Test(groups = {"MC:Authentication", "Abdul Rehman", "SECTION:Customer Name Lastname Validation", "SC:Customer Name Lastname"}, description = "Validates transaction authentication with a null customer last name value", priority = 58)
    public void transactionAuthenticationNullCustomerNameLastNameCase() {

        TransactionRequest transactionRequest = AppUtils.transactionRequestInstance();
        TransactionRequest.Customer customer = transactionRequest.getCustomer();
        List<TransactionRequest.Customer.Name> nameList = customer.getName();
        nameList.get(0).setLastName(null);

        String body = CommonAutomationUtils.stringToJson(transactionRequest);
        Response response = AuthenticationTransactionRequest.postRequest(body, AppConstants.INTERNAL_END_POINT);

        CommonAutomationUtils.verifyCommonResponseSuccessValidation(response, HttpStatus.SC_OK);
        CommonAutomationUtils.verifyNonAvailableKey(response, List.of(AppConstants.CUSTOMER + "." + AppConstants.NAME + "." + AppConstants.LAST_NAME));
    }

    @Test(groups = {"MC:Authentication", "Abdul Rehman", "SECTION:Customer Name Lastname Validation", "SC:Customer Name Lastname"}, description = "Validates transaction authentication with a valid customer last name value", priority = 59)
    public void transactionAuthenticationValidCustomerNameLastNameCase() {

        TransactionRequest transactionRequest = AppUtils.transactionRequestInstance();
        TransactionRequest.Customer customer = transactionRequest.getCustomer();
        List<TransactionRequest.Customer.Name> nameList = customer.getName();
        nameList.get(0).setLastName("Ali Usman");

        String body = CommonAutomationUtils.stringToJson(transactionRequest);
        Response response = AuthenticationTransactionRequest.postRequest(body, AppConstants.INTERNAL_END_POINT);

        CommonAutomationUtils.verifyCommonResponseSuccessValidation(response, HttpStatus.SC_OK, Map.of(AppConstants.CUSTOMER + "." + AppConstants.NAME + "[0]." + AppConstants.LAST_NAME, "Ali Usman"));
    }

    @Test(groups = {"MC:Authentication", "Abdul Rehman", "SECTION:Customer Name Lastname Validation", "SC:Customer Name Lastname"}, description = "Validates transaction authentication with a numeric customer last name value", priority = 60)
    public void transactionAuthenticationNumericCustomerNameLastNameCase() {

        TransactionRequest transactionRequest = AppUtils.transactionRequestInstance();
        TransactionRequest.Customer customer = transactionRequest.getCustomer();
        List<TransactionRequest.Customer.Name> nameList = customer.getName();
        nameList.get(0).setLastName("12345");

        String body = CommonAutomationUtils.stringToJson(transactionRequest);
        String updatedBody = CommonAutomationUtils.modifyJson(body, "MODIFY", "customer.name.last_name", 12345);
        Response response = AuthenticationTransactionRequest.postRequest(updatedBody, AppConstants.INTERNAL_END_POINT);

        CommonAutomationUtils.verifyCommonResponseSuccessValidation(response, HttpStatus.SC_OK, Map.of(AppConstants.CUSTOMER + "." + AppConstants.NAME + "[0]." + AppConstants.LAST_NAME, "12345"));
    }

    @Test(groups = {"MC:Authentication", "Abdul Rehman", "SECTION:Customer Name Locale Validation", "SC:Customer Name Locale"}, description = "Validates transaction authentication with an empty customer locale value", priority = 61)
    public void transactionAuthenticationEmptyCustomerNameLocaleCase() {

        TransactionRequest transactionRequest = AppUtils.transactionRequestInstance();
        TransactionRequest.Customer customer = transactionRequest.getCustomer();
        List<TransactionRequest.Customer.Name> nameList = customer.getName();
        nameList.get(0).setLocale("");

        String body = CommonAutomationUtils.stringToJson(transactionRequest);
        Response response = AuthenticationTransactionRequest.postRequest(body, AppConstants.INTERNAL_END_POINT);

        CommonAutomationUtils.verifyCommonResponseSuccessValidation(response, HttpStatus.SC_OK, Map.of(AppConstants.CUSTOMER + "." + AppConstants.NAME + "[0]." + AppConstants.LOCALE, ""));
    }

    @Test(groups = {"MC:Authentication", "Abdul Rehman", "SECTION:Customer Name Locale Validation", "SC:Customer Name Locale"}, description = "Validates transaction authentication with a null customer locale value", priority = 62)
    public void transactionAuthenticationNullCustomerNameLocaleCase() {

        TransactionRequest transactionRequest = AppUtils.transactionRequestInstance();
        TransactionRequest.Customer customer = transactionRequest.getCustomer();
        List<TransactionRequest.Customer.Name> nameList = customer.getName();
        nameList.get(0).setLocale(null);

        String body = CommonAutomationUtils.stringToJson(transactionRequest);
        Response response = AuthenticationTransactionRequest.postRequest(body, AppConstants.INTERNAL_END_POINT);

        CommonAutomationUtils.verifyCommonResponseSuccessValidation(response, HttpStatus.SC_OK);
        CommonAutomationUtils.verifyNonAvailableKey(response, List.of(AppConstants.CUSTOMER + "." + AppConstants.NAME + "." + AppConstants.LOCALE));
    }

    @Test(groups = {"MC:Authentication", "Abdul Rehman", "SECTION:Customer Name Locale Validation", "SC:Customer Name Locale"}, description = "Validates transaction authentication with a valid customer locale value", priority = 63)
    public void transactionAuthenticationValidCustomerNameLocaleCase() {

        TransactionRequest transactionRequest = AppUtils.transactionRequestInstance();
        TransactionRequest.Customer customer = transactionRequest.getCustomer();
        List<TransactionRequest.Customer.Name> nameList = customer.getName();
        nameList.get(0).setLocale("en");

        String body = CommonAutomationUtils.stringToJson(transactionRequest);
        Response response = AuthenticationTransactionRequest.postRequest(body, AppConstants.INTERNAL_END_POINT);

        CommonAutomationUtils.verifyCommonResponseSuccessValidation(response, HttpStatus.SC_OK, Map.of(AppConstants.CUSTOMER + "." + AppConstants.NAME + "[0]." + AppConstants.LOCALE, "en"));
    }

    @Test(groups = {"MC:Authentication", "Abdul Rehman", "SECTION:Customer Name Locale Validation", "SC:Customer Name Locale"}, description = "Validates transaction authentication with an invalid customer locale value", priority = 64)
    public void transactionAuthenticationInvalidCustomerNameLocaleCase() {

        TransactionRequest transactionRequest = AppUtils.transactionRequestInstance();
        TransactionRequest.Customer customer = transactionRequest.getCustomer();
        List<TransactionRequest.Customer.Name> nameList = customer.getName();
        nameList.get(0).setLocale("end");

        String body = CommonAutomationUtils.stringToJson(transactionRequest);
        Response response = AuthenticationTransactionRequest.postRequest(body, AppConstants.INTERNAL_END_POINT);

        CommonAutomationUtils.verifyCommonResponseSuccessValidation(response, HttpStatus.SC_OK, Map.of(AppConstants.CUSTOMER + "." + AppConstants.NAME + "[0]." + AppConstants.LOCALE, "end"));
    }

    @Test(groups = {"MC:Authentication", "Abdul Rehman", "SECTION:Customer Name Locale Validation", "SC:Customer Name Locale"}, description = "Validates transaction authentication with a numeric customer locale value", priority = 65)
    public void transactionAuthenticationNumericCustomerNameLocaleCase() {

        TransactionRequest transactionRequest = AppUtils.transactionRequestInstance();
        TransactionRequest.Customer customer = transactionRequest.getCustomer();
        List<TransactionRequest.Customer.Name> nameList = customer.getName();
        nameList.get(0).setLocale("123");

        String body = CommonAutomationUtils.stringToJson(transactionRequest);
        String updatedBody = CommonAutomationUtils.modifyJson(body, "MODIFY", "customer.name.locale", 123);
        Response response = AuthenticationTransactionRequest.postRequest(updatedBody, AppConstants.INTERNAL_END_POINT);

        CommonAutomationUtils.verifyCommonResponseSuccessValidation(response, HttpStatus.SC_OK, Map.of(AppConstants.CUSTOMER + "." + AppConstants.NAME + "[0]." + AppConstants.LOCALE, "123"));
    }

    @Test(groups = {"MC:Authentication", "Abdul Rehman", "SECTION:Customer Name On Card Validation", "SC:Customer Name On Card"}, description = "Validates transaction authentication with an empty customer name on card value", priority = 66)
    public void transactionAuthenticationEmptyCustomerNameOnCardCase() {

        TransactionRequest transactionRequest = AppUtils.transactionRequestInstance();
        transactionRequest.getCustomer().setNameOnCard("");

        String body = CommonAutomationUtils.stringToJson(transactionRequest);
        Response response = AuthenticationTransactionRequest.postRequest(body, AppConstants.INTERNAL_END_POINT);

        CommonAutomationUtils.verifyCommonResponseSuccessValidation(response, HttpStatus.SC_OK, Map.of(AppConstants.CUSTOMER + "." + AppConstants.NAME_ON_CARD, ""));
    }

    @Test(groups = {"MC:Authentication", "Abdul Rehman", "SECTION:Customer Name On Card Validation", "SC:Customer Name On Card"}, description = "Validates transaction authentication with a null customer name on card value", priority = 67)
    public void transactionAuthenticationNullCustomerNameOnCardCase() {

        TransactionRequest transactionRequest = AppUtils.transactionRequestInstance();
        transactionRequest.getCustomer().setNameOnCard(null);

        String body = CommonAutomationUtils.stringToJson(transactionRequest);
        Response response = AuthenticationTransactionRequest.postRequest(body, AppConstants.INTERNAL_END_POINT);

        CommonAutomationUtils.verifyCommonResponseSuccessValidation(response, HttpStatus.SC_OK);
        CommonAutomationUtils.verifyNonAvailableKey(response, List.of(AppConstants.CUSTOMER + "." + AppConstants.NAME_ON_CARD));
    }

    @Test(groups = {"MC:Authentication", "Abdul Rehman", "SECTION:Customer Name On Card Validation", "SC:Customer Name On Card"}, description = "Validates transaction authentication with a valid customer name on card value", priority = 68)
    public void transactionAuthenticationValidCustomerNameOnCardCase() {

        TransactionRequest transactionRequest = AppUtils.transactionRequestInstance();
        transactionRequest.getCustomer().setNameOnCard("Test Name");

        String body = CommonAutomationUtils.stringToJson(transactionRequest);
        Response response = AuthenticationTransactionRequest.postRequest(body, AppConstants.INTERNAL_END_POINT);

        CommonAutomationUtils.verifyCommonResponseSuccessValidation(response, HttpStatus.SC_OK, Map.of(AppConstants.CUSTOMER + "." + AppConstants.NAME_ON_CARD, "Test Name"));
    }

    @Test(groups = {"MC:Authentication", "Abdul Rehman", "SECTION:Customer Name On Card Validation", "SC:Customer Name On Card"}, description = "Validates transaction authentication with a numeric customer name on card value", priority = 69)
    public void transactionAuthenticationNumericCustomerNameOnCardCase() {

        TransactionRequest transactionRequest = AppUtils.transactionRequestInstance();
        transactionRequest.getCustomer().setNameOnCard("123");

        String body = CommonAutomationUtils.stringToJson(transactionRequest);
        String updatedBody = CommonAutomationUtils.modifyJson(body, "MODIFY", "customer.name_on_card", 123);
        Response response = AuthenticationTransactionRequest.postRequest(updatedBody, AppConstants.INTERNAL_END_POINT);

        CommonAutomationUtils.verifyCommonResponseSuccessValidation(response, HttpStatus.SC_OK, Map.of(AppConstants.CUSTOMER + "." + AppConstants.NAME_ON_CARD, "123"));
    }

    @Test(groups = {"MC:Authentication", "Abdul Rehman", "SECTION:Customer Email Validation", "SC:Customer Email"}, description = "Validates transaction authentication with a valid customer email", priority = 70)
    public void transactionAuthenticationValidCustomerEmailCase() {

        TransactionRequest transactionRequest = AppUtils.transactionRequestInstance();
        transactionRequest.getCustomer().setEmail("test@test.com");

        String body = CommonAutomationUtils.stringToJson(transactionRequest);
        Response response = AuthenticationTransactionRequest.postRequest(body, AppConstants.INTERNAL_END_POINT);

        CommonAutomationUtils.verifyCommonResponseSuccessValidation(response, HttpStatus.SC_OK, Map.of(AppConstants.CUSTOMER + "." + AppConstants.EMAIL, "test@test.com"));
    }

    @Test(groups = {"MC:Authentication", "Abdul Rehman", "SECTION:Customer Email Validation", "SC:Customer Email"}, description = "Validates transaction authentication fails with an invalid customer email (too short)", priority = 71)
    public void transactionAuthenticationInvalidCustomerEmailCase() {

        TransactionRequest transactionRequest = AppUtils.transactionRequestInstance();
        transactionRequest.getCustomer().setEmail("a@b.c");

        String body = CommonAutomationUtils.stringToJson(transactionRequest);
        Response response = AuthenticationTransactionRequest.postRequest(body, AppConstants.INTERNAL_END_POINT);
        String textResponse = response.getBody().asString();
        JsonNode jsonResponse = CommonAutomationUtils.convertToJson(textResponse);

        CommonAutomationUtils.verifyCommonResponseFailedValidation(jsonResponse, response.getStatusCode(), HttpStatus.SC_BAD_REQUEST, AppConstants.REQUIRED_INPUT_INVALID_CODE, AppConstants.REQUIRED_INPUT_INVALID_ERROR);
    }

    @Test(groups = {"MC:Authentication", "Abdul Rehman", "SECTION:Customer Email Validation", "SC:Customer Email"}, description = "Validates transaction authentication fails with an email exceeding the maximum length", priority = 72)
    public void transactionAuthenticationMoreThanMaxCustomerEmailCase() {

        TransactionRequest transactionRequest = AppUtils.transactionRequestInstance();
        transactionRequest.getCustomer().setEmail("a.very.long.email.address.with.many.characters.to.reach.the.limit@example.extremely.long.domain.name.with.many.subdomains.subdomains.to.maximize.length.com");

        String body = CommonAutomationUtils.stringToJson(transactionRequest);
        Response response = AuthenticationTransactionRequest.postRequest(body, AppConstants.INTERNAL_END_POINT);
        String textResponse = response.getBody().asString();
        JsonNode jsonResponse = CommonAutomationUtils.convertToJson(textResponse);

        CommonAutomationUtils.verifyCommonResponseFailedValidation(jsonResponse, response.getStatusCode(), HttpStatus.SC_BAD_REQUEST, AppConstants.REQUIRED_INPUT_INVALID_CODE, AppConstants.REQUIRED_INPUT_INVALID_ERROR);
    }

    @Test(groups = {"MC:Authentication", "Abdul Rehman", "SECTION:Customer Email Validation", "SC:Customer Email"}, description = "Validates transaction authentication fails with an empty customer email", priority = 73)
    public void transactionAuthenticationEmptyCustomerEmailCase() {

        TransactionRequest transactionRequest = AppUtils.transactionRequestInstance();
        transactionRequest.getCustomer().setEmail("");

        String body = CommonAutomationUtils.stringToJson(transactionRequest);
        Response response = AuthenticationTransactionRequest.postRequest(body, AppConstants.INTERNAL_END_POINT);
        String textResponse = response.getBody().asString();
        JsonNode jsonResponse = CommonAutomationUtils.convertToJson(textResponse);

        CommonAutomationUtils.verifyCommonResponseFailedValidation(jsonResponse, response.getStatusCode(), HttpStatus.SC_BAD_REQUEST, AppConstants.REQUIRED_INPUT_INVALID_CODE, AppConstants.REQUIRED_INPUT_INVALID_ERROR);
    }

    @Test(groups = {"MC:Authentication", "Abdul Rehman", "SECTION:Customer Email Validation", "SC:Customer Email"}, description = "Validates transaction authentication fails with an invalid customer email containing only string characters", priority = 74)
    public void transactionAuthenticationStringOnlyCustomerEmailCase() {

        TransactionRequest transactionRequest = AppUtils.transactionRequestInstance();
        transactionRequest.getCustomer().setEmail("abcabc");

        String body = CommonAutomationUtils.stringToJson(transactionRequest);
        Response response = AuthenticationTransactionRequest.postRequest(body, AppConstants.INTERNAL_END_POINT);
        String textResponse = response.getBody().asString();
        JsonNode jsonResponse = CommonAutomationUtils.convertToJson(textResponse);

        CommonAutomationUtils.verifyCommonResponseFailedValidation(jsonResponse, response.getStatusCode(), HttpStatus.SC_BAD_REQUEST, AppConstants.REQUIRED_INPUT_INVALID_CODE, AppConstants.REQUIRED_INPUT_INVALID_ERROR);
    }

    @Test(groups = {"MC:Authentication", "Abdul Rehman", "SECTION:Customer Email Validation", "SC:Customer Email"}, description = "Validates transaction authentication with a null customer email value", priority = 75)
    public void transactionAuthenticationNullCustomerEmailCase() {

        TransactionRequest transactionRequest = AppUtils.transactionRequestInstance();
        transactionRequest.getCustomer().setEmail(null);

        String body = CommonAutomationUtils.stringToJson(transactionRequest);
        Response response = AuthenticationTransactionRequest.postRequest(body, AppConstants.INTERNAL_END_POINT);

        CommonAutomationUtils.verifyCommonResponseSuccessValidation(response, HttpStatus.SC_OK);
        CommonAutomationUtils.verifyNonAvailableKey(response, List.of(AppConstants.CUSTOMER + "." + AppConstants.EMAIL));
    }

    @Test(groups = {"MC:Authentication", "Abdul Rehman", "SECTION:Customer Phone Validation", "SC:Customer Phone"}, description = "Validates transaction authentication fails with a null customer phone value", priority = 76)
    public void transactionAuthenticationNullCustomerPhoneCase() {

        TransactionRequest transactionRequest = AppUtils.transactionRequestInstance();
        transactionRequest.getCustomer().setPhone(null);

        String body = CommonAutomationUtils.stringToJson(transactionRequest);
        Response response = AuthenticationTransactionRequest.postRequest(body, AppConstants.INTERNAL_END_POINT);
        String textResponse = response.getBody().asString();
        JsonNode jsonResponse = CommonAutomationUtils.convertToJson(textResponse);

        CommonAutomationUtils.verifyCommonResponseSuccessValidation(response, HttpStatus.SC_OK, Map.of(AppConstants.CUSTOMER + "." + AppConstants.EMAIL, "test@test.com"));
    }

    @Test(groups = {"MC:Authentication", "Abdul Rehman", "SECTION:Customer Phone Country Code Validation", "SC:Customer Phone Country Code"}, description = "Validates transaction authentication with a valid customer phone number and country code", priority = 77)
    public void transactionAuthenticationValidCustomerPhoneCountryCodeCase() {

        TransactionRequest transactionRequest = AppUtils.transactionRequestInstance();
        TransactionRequest.Customer customer = transactionRequest.getCustomer();
        TransactionRequest.Customer.Phone phone = customer.getPhone();
        phone.setCountryCode("965");
        phone.setNumber("50000000");

        String body = CommonAutomationUtils.stringToJson(transactionRequest);
        Response response = AuthenticationTransactionRequest.postRequest(body, AppConstants.INTERNAL_END_POINT);

        CommonAutomationUtils.verifyCommonResponseSuccessValidation(response, HttpStatus.SC_OK, Map.of(AppConstants.CUSTOMER + "." + AppConstants.PHONE + "." + AppConstants.COUNTRY_CODE, "965",
                AppConstants.CUSTOMER + "." + AppConstants.PHONE + "." + AppConstants.NUMBER, "50000000"));
    }

    @Test(groups = {"MC:Authentication", "Abdul Rehman", "SECTION:Customer Phone Country Code Validation", "SC:Customer Phone Country Code"}, description = "Validates transaction authentication fails with an empty customer phone country code", priority = 78)
    public void transactionAuthenticationEmptyCustomerPhoneCountryCodeCase() {

        TransactionRequest transactionRequest = AppUtils.transactionRequestInstance();
        TransactionRequest.Customer customer = transactionRequest.getCustomer();
        TransactionRequest.Customer.Phone phone = customer.getPhone();
        phone.setCountryCode("");
        phone.setNumber("50000000");

        String body = CommonAutomationUtils.stringToJson(transactionRequest);
        Response response = AuthenticationTransactionRequest.postRequest(body, AppConstants.INTERNAL_END_POINT);
        String textResponse = response.getBody().asString();
        JsonNode jsonResponse = CommonAutomationUtils.convertToJson(textResponse);

        CommonAutomationUtils.verifyCommonResponseFailedValidation(jsonResponse, response.getStatusCode(), HttpStatus.SC_BAD_REQUEST, AppConstants.REQUIRED_INPUT_INVALID_CODE, AppConstants.REQUIRED_INPUT_INVALID_ERROR);
    }

    @Test(groups = {"MC:Authentication", "Abdul Rehman", "SECTION:Customer Phone Country Code Validation", "SC:Customer Phone Country Code"}, description = "Validates transaction authentication fails with a customer phone country code exceeding the maximum length", priority = 79)
    public void transactionAuthenticationMoreThanMaxCustomerPhoneCountryCodeCase() {

        TransactionRequest transactionRequest = AppUtils.transactionRequestInstance();
        TransactionRequest.Customer customer = transactionRequest.getCustomer();
        TransactionRequest.Customer.Phone phone = customer.getPhone();
        phone.setCountryCode("123456");
        phone.setNumber("50000000");

        String body = CommonAutomationUtils.stringToJson(transactionRequest);
        Response response = AuthenticationTransactionRequest.postRequest(body, AppConstants.INTERNAL_END_POINT);
        String textResponse = response.getBody().asString();
        JsonNode jsonResponse = CommonAutomationUtils.convertToJson(textResponse);

        CommonAutomationUtils.verifyCommonResponseFailedValidation(jsonResponse, response.getStatusCode(), HttpStatus.SC_BAD_REQUEST, AppConstants.REQUIRED_INPUT_INVALID_CODE, AppConstants.REQUIRED_INPUT_INVALID_ERROR);
    }

    @Test(groups = {"MC:Authentication", "Abdul Rehman", "SECTION:Customer Phone Country Code Validation", "SC:Customer Phone Country Code"}, description = "Validates transaction authentication with a null customer phone country code value", priority = 80)
    public void transactionAuthenticationNullCustomerPhoneCountryCodeCase() {

        TransactionRequest transactionRequest = AppUtils.transactionRequestInstance();
        TransactionRequest.Customer customer = transactionRequest.getCustomer();
        TransactionRequest.Customer.Phone phone = customer.getPhone();
        phone.setCountryCode(null);
        phone.setNumber("50000000");

        String body = CommonAutomationUtils.stringToJson(transactionRequest);
        Response response = AuthenticationTransactionRequest.postRequest(body, AppConstants.INTERNAL_END_POINT);

        CommonAutomationUtils.verifyCommonResponseSuccessValidation(response, HttpStatus.SC_OK);
        CommonAutomationUtils.verifyNonAvailableKey(response, List.of(AppConstants.CUSTOMER + "." + AppConstants.PHONE + "." + AppConstants.COUNTRY_CODE));
    }

    @Test(groups = {"MC:Authentication", "Abdul Rehman", "SECTION:Customer Phone Country Code Validation", "SC:Customer Phone Country Code"}, description = "Validates transaction authentication with a numeric customer phone country code value", priority = 81)
    public void transactionAuthenticationNumericCustomerPhoneCountryCodeCase() {

        TransactionRequest transactionRequest = AppUtils.transactionRequestInstance();
        TransactionRequest.Customer customer = transactionRequest.getCustomer();
        TransactionRequest.Customer.Phone phone = customer.getPhone();
        phone.setCountryCode("123");
        phone.setNumber("50000000");

        String body = CommonAutomationUtils.stringToJson(transactionRequest);
        String updatedBody = CommonAutomationUtils.modifyJson(body, "MODIFY", "customer.phone.country_code", 123);
        Response response = AuthenticationTransactionRequest.postRequest(updatedBody, AppConstants.INTERNAL_END_POINT);

        CommonAutomationUtils.verifyCommonResponseSuccessValidation(response, HttpStatus.SC_OK, Map.of(AppConstants.CUSTOMER + "." + AppConstants.PHONE + "." + AppConstants.COUNTRY_CODE, "123"));
    }

    @Test(groups = {"MC:Authentication", "Abdul Rehman", "SECTION:Customer Phone Number Validation", "SC:Customer Phone Number"}, description = "Validates transaction authentication fails with an empty customer phone number value", priority = 82)
    public void transactionAuthenticationEmptyCustomerPhoneNumberCase() {

        TransactionRequest transactionRequest = AppUtils.transactionRequestInstance();
        TransactionRequest.Customer customer = transactionRequest.getCustomer();
        TransactionRequest.Customer.Phone phone = customer.getPhone();
        phone.setCountryCode("965");
        phone.setNumber("");

        String body = CommonAutomationUtils.stringToJson(transactionRequest);
        Response response = AuthenticationTransactionRequest.postRequest(body, AppConstants.INTERNAL_END_POINT);
        String textResponse = response.getBody().asString();
        JsonNode jsonResponse = CommonAutomationUtils.convertToJson(textResponse);

        CommonAutomationUtils.verifyCommonResponseFailedValidation(jsonResponse, response.getStatusCode(), HttpStatus.SC_BAD_REQUEST, AppConstants.REQUIRED_INPUT_INVALID_CODE, AppConstants.REQUIRED_INPUT_INVALID_ERROR);
    }

    @Test(groups = {"MC:Authentication", "Abdul Rehman", "SECTION:Customer Phone Number Validation", "SC:Customer Phone Number"}, description = "Validates transaction authentication fails with a customer phone number exceeding the maximum length", priority = 83)
    public void transactionAuthenticationMoreThanMaxCustomerPhoneNumberCase() {

        TransactionRequest transactionRequest = AppUtils.transactionRequestInstance();
        TransactionRequest.Customer customer = transactionRequest.getCustomer();
        TransactionRequest.Customer.Phone phone = customer.getPhone();
        phone.setCountryCode("965");
        phone.setNumber("12345678912345678912345");

        String body = CommonAutomationUtils.stringToJson(transactionRequest);
        Response response = AuthenticationTransactionRequest.postRequest(body, AppConstants.INTERNAL_END_POINT);
        String textResponse = response.getBody().asString();
        JsonNode jsonResponse = CommonAutomationUtils.convertToJson(textResponse);

        CommonAutomationUtils.verifyCommonResponseFailedValidation(jsonResponse, response.getStatusCode(), HttpStatus.SC_BAD_REQUEST, AppConstants.REQUIRED_INPUT_INVALID_CODE, AppConstants.REQUIRED_INPUT_INVALID_ERROR);
    }

    @Test(groups = {"MC:Authentication", "Abdul Rehman", "SECTION:Customer Phone Number Validation", "SC:Customer Phone Number"}, description = "Validates transaction authentication with a null customer phone number value", priority = 84)
    public void transactionAuthenticationNullCustomerPhoneNumberCase() {

        TransactionRequest transactionRequest = AppUtils.transactionRequestInstance();
        TransactionRequest.Customer customer = transactionRequest.getCustomer();
        TransactionRequest.Customer.Phone phone = customer.getPhone();
        phone.setCountryCode("123");
        phone.setNumber(null);

        String body = CommonAutomationUtils.stringToJson(transactionRequest);
        Response response = AuthenticationTransactionRequest.postRequest(body, AppConstants.INTERNAL_END_POINT);

        CommonAutomationUtils.verifyCommonResponseSuccessValidation(response, HttpStatus.SC_OK);
        CommonAutomationUtils.verifyNonAvailableKey(response, List.of(AppConstants.CUSTOMER + "." + AppConstants.PHONE + "." + AppConstants.NUMBER));
    }

    @Test(groups = {"MC:Authentication", "Abdul Rehman", "SECTION:Customer Phone Number Validation", "SC:Customer Phone Number"}, description = "Validates transaction authentication with a customer phone number without country code", priority = 85)
    public void transactionAuthenticationCustomerWithPhoneNumberWithoutCountryCodeCase() {

        TransactionRequest transactionRequest = AppUtils.transactionRequestInstance();

        String body = CommonAutomationUtils.stringToJson(transactionRequest);
        String updatedBody = CommonAutomationUtils.modifyJson(body, "DELETE", "customer.phone.country_code", null);
        Response response = AuthenticationTransactionRequest.postRequest(updatedBody, AppConstants.INTERNAL_END_POINT);

        CommonAutomationUtils.verifyCommonResponseSuccessValidation(response, HttpStatus.SC_OK, Map.of(AppConstants.CUSTOMER + "." + AppConstants.PHONE + "." + AppConstants.NUMBER, "50000000"));
    }

    @Test(groups = {"MC:Authentication", "Abdul Rehman", "SECTION:Customer Phone Number Validation", "SC:Customer Phone Number"}, description = "Validates transaction authentication with a customer country code without phone number", priority = 86)
    public void transactionAuthenticationCustomerWithoutPhoneNumberWithCountryCodeCase() {

        TransactionRequest transactionRequest = AppUtils.transactionRequestInstance();

        String body = CommonAutomationUtils.stringToJson(transactionRequest);
        String updatedBody = CommonAutomationUtils.modifyJson(body, "DELETE", "customer.phone.number", null);
        Response response = AuthenticationTransactionRequest.postRequest(updatedBody, AppConstants.INTERNAL_END_POINT);

        CommonAutomationUtils.verifyCommonResponseSuccessValidation(response, HttpStatus.SC_OK, Map.of(AppConstants.CUSTOMER + "." + AppConstants.PHONE + "." + AppConstants.COUNTRY_CODE, "965"));
    }

    @Test(groups = {"MC:Authentication", "Abdul Rehman", "SECTION:Customer Phone Number Validation", "SC:Customer Phone Number"}, description = "Validates transaction authentication with a customer email but without phone number", priority = 87)
    public void transactionAuthenticationWithCustomerEmailWithoutPhoneCase() {

        TransactionRequest transactionRequest = AppUtils.transactionRequestInstance();

        String body = CommonAutomationUtils.stringToJson(transactionRequest);
        String updatedBody = CommonAutomationUtils.modifyJson(body, "DELETE", "customer.phone", null);
        Response response = AuthenticationTransactionRequest.postRequest(updatedBody, AppConstants.INTERNAL_END_POINT);

        CommonAutomationUtils.verifyCommonResponseSuccessValidation(response, HttpStatus.SC_OK, Map.of(AppConstants.CUSTOMER + "." + AppConstants.EMAIL, "test@test.com"));
    }

    @Test(groups = {"MC:Authentication", "Abdul Rehman", "SECTION:Customer Phone Number Validation", "SC:Customer Phone Number"}, description = "Validates transaction authentication with a customer phone number but without email", priority = 88)
    public void transactionAuthenticationWithoutCustomerEmailWithPhoneCase() {

        TransactionRequest transactionRequest = AppUtils.transactionRequestInstance();

        String body = CommonAutomationUtils.stringToJson(transactionRequest);
        String updatedBody = CommonAutomationUtils.modifyJson(body, "DELETE", "customer.email", null);
        Response response = AuthenticationTransactionRequest.postRequest(updatedBody, AppConstants.INTERNAL_END_POINT);

        CommonAutomationUtils.verifyCommonResponseSuccessValidation(response, HttpStatus.SC_OK, Map.of(AppConstants.CUSTOMER + "." + AppConstants.PHONE + "." + AppConstants.COUNTRY_CODE, "965", AppConstants.CUSTOMER + "." + AppConstants.PHONE + "." + AppConstants.NUMBER, "50000000"));
    }

    @Test(groups = {"MC:Authentication", "Abdul Rehman", "SECTION:Customer Phone Number Validation", "SC:Customer Phone Number"}, description = "Validates transaction authentication without both customer email and phone number", priority = 89)
    public void transactionAuthenticationWithoutCustomerEmailWithOutPhoneCase() {

        TransactionRequest transactionRequest = AppUtils.transactionRequestInstance();

        String body = CommonAutomationUtils.stringToJson(transactionRequest);
        String updatedBody = CommonAutomationUtils.modifyJson(body, "DELETE", "customer.email", null);
        updatedBody = CommonAutomationUtils.modifyJson(updatedBody, "DELETE", "customer.phone", null);
        Response response = AuthenticationTransactionRequest.postRequest(updatedBody, AppConstants.INTERNAL_END_POINT);
        String textResponse = response.getBody().asString();
        JsonNode jsonResponse = CommonAutomationUtils.convertToJson(textResponse);

        CommonAutomationUtils.verifyCommonResponseFailedValidation(jsonResponse, response.getStatusCode(), HttpStatus.SC_BAD_REQUEST, AppConstants.REQUIRED_FIELD_INVALID_CODE, AppConstants.REQUIRED_FIELD_INVALID_ERROR);
    }

    @Test(groups = {"MC:Authentication", "Abdul Rehman", "SECTION:Device Validation", "SC:Device"}, description = "Validates transaction authentication fails with a null device value", priority = 90)
    public void transactionAuthenticationNullDeviceCase() {

        TransactionRequest transactionRequest = AppUtils.transactionRequestInstance();
        transactionRequest.setDevice(null);

        String body = CommonAutomationUtils.stringToJson(transactionRequest);
        Response response = AuthenticationTransactionRequest.postRequest(body, AppConstants.INTERNAL_END_POINT);
        String textResponse = response.getBody().asString();
        JsonNode jsonResponse = CommonAutomationUtils.convertToJson(textResponse);

        CommonAutomationUtils.verifyCommonResponseFailedValidation(jsonResponse, response.getStatusCode(), HttpStatus.SC_BAD_REQUEST, AppConstants.REQUIRED_FIELD_INVALID_CODE, AppConstants.REQUIRED_FIELD_INVALID_ERROR);
    }

    @Test(groups = {"MC:Authentication", "Abdul Rehman", "SECTION:Device Ipaddress Validation", "SC:Device Ipaddress"}, description = "Validates transaction authentication with a valid IPv4 device IP address", priority = 91)
    public void transactionAuthenticationValidDeviceIpAddressV4Case() {

        TransactionRequest transactionRequest = AppUtils.transactionRequestInstance();

        String body = CommonAutomationUtils.stringToJson(transactionRequest);
        Response response = AuthenticationTransactionRequest.postRequest(body, AppConstants.INTERNAL_END_POINT);

        CommonAutomationUtils.verifyCommonResponseSuccessValidation(response, HttpStatus.SC_OK);
    }

    @Test(groups = {"MC:Authentication", "Abdul Rehman", "SECTION:Device Ipaddress Validation", "SC:Device Ipaddress"}, description = "Validates transaction authentication with a valid IPv6 device IP address", priority = 92)
    public void transactionAuthenticationValidDeviceIpAddressV6Case() {

        TransactionRequest transactionRequest = AppUtils.transactionRequestInstance();
        transactionRequest.getDevice().setIpAddress("2001:0db8:85a3:0000:0000:8a2e:0370:7334");

        String body = CommonAutomationUtils.stringToJson(transactionRequest);
        Response response = AuthenticationTransactionRequest.postRequest(body, AppConstants.INTERNAL_END_POINT);

        CommonAutomationUtils.verifyCommonResponseSuccessValidation(response, HttpStatus.SC_OK);
    }

    @Test(groups = {"MC:Authentication", "Abdul Rehman", "SECTION:Device Ipaddress Validation", "SC:Device Ipaddress"}, description = "Validates transaction authentication with an empty device IP address", priority = 93)
    public void transactionAuthenticationEmptyDeviceIpAddressCase() {

        TransactionRequest transactionRequest = AppUtils.transactionRequestInstance();
        transactionRequest.getDevice().setIpAddress("");

        String body = CommonAutomationUtils.stringToJson(transactionRequest);
        Response response = AuthenticationTransactionRequest.postRequest(body, AppConstants.INTERNAL_END_POINT);

        CommonAutomationUtils.verifyCommonResponseSuccessValidation(response, HttpStatus.SC_OK);
    }

    @Test(groups = {"MC:Authentication", "Abdul Rehman", "SECTION:Device Ipaddress Validation", "SC:Device Ipaddress"}, description = "Validates transaction authentication with an invalid device IP address", priority = 94)
    public void transactionAuthenticationInvalidDeviceIpAddressCase() {

        TransactionRequest transactionRequest = AppUtils.transactionRequestInstance();
        transactionRequest.getDevice().setIpAddress("192.168.-1.-1");

        String body = CommonAutomationUtils.stringToJson(transactionRequest);
        Response response = AuthenticationTransactionRequest.postRequest(body, AppConstants.INTERNAL_END_POINT);

        CommonAutomationUtils.verifyCommonResponseSuccessValidation(response, HttpStatus.SC_OK);
    }

    @Test(groups = {"MC:Authentication", "Abdul Rehman", "SECTION:Device Ipaddress Validation", "SC:Device Ipaddress"}, description = "Validates transaction authentication with a null device IP address value", priority = 95)
    public void transactionAuthenticationNullDeviceIpAddressCase() {

        TransactionRequest transactionRequest = AppUtils.transactionRequestInstance();
        transactionRequest.getDevice().setIpAddress(null);

        String body = CommonAutomationUtils.stringToJson(transactionRequest);
        Response response = AuthenticationTransactionRequest.postRequest(body, AppConstants.INTERNAL_END_POINT);

        CommonAutomationUtils.verifyCommonResponseSuccessValidation(response, HttpStatus.SC_OK);
        CommonAutomationUtils.verifyNonAvailableKey(response, List.of(AppConstants.DEVICE + "." + AppConstants.IP_ADDRESS));
    }

    @Test(groups = {"MC:Authentication", "Abdul Rehman", "SECTION:Device Ipaddress Validation", "SC:Device Ipaddress"}, description = "Validates transaction authentication with a device IP address and port", priority = 96)
    public void transactionAuthenticationNullDeviceIpAddressWithPortCase() {

        TransactionRequest transactionRequest = AppUtils.transactionRequestInstance();
        transactionRequest.getDevice().setIpAddress("37.210.100.199:8080");

        String body = CommonAutomationUtils.stringToJson(transactionRequest);
        Response response = AuthenticationTransactionRequest.postRequest(body, AppConstants.INTERNAL_END_POINT);

        CommonAutomationUtils.verifyCommonResponseSuccessValidation(response, HttpStatus.SC_OK);
    }

    @Test(groups = {"MC:Authentication", "Abdul Rehman", "SECTION:Device Browser Details Validation", "SC:Device Browser Details"}, description = "Validates transaction authentication fails with a null browser details value", priority = 97)
    public void transactionAuthenticationNullBrowserDetailsCase() {

        TransactionRequest transactionRequest = AppUtils.transactionRequestInstance();
        transactionRequest.getDevice().setBrowserDetails(null);

        String body = CommonAutomationUtils.stringToJson(transactionRequest);
        Response response = AuthenticationTransactionRequest.postRequest(body, AppConstants.INTERNAL_END_POINT);
        String textResponse = response.getBody().asString();
        JsonNode jsonResponse = CommonAutomationUtils.convertToJson(textResponse);

        CommonAutomationUtils.verifyCommonResponseFailedValidation(jsonResponse, response.getStatusCode(), HttpStatus.SC_BAD_REQUEST, AppConstants.REQUIRED_FIELD_INVALID_CODE, AppConstants.REQUIRED_FIELD_INVALID_ERROR);
    }

    @Test(groups = {"MC:Authentication", "Abdul Rehman", "SECTION:Device Browser Details Screen Heights Validation", "SC:Device Browser Details Screen Heights"}, description = "Validates transaction authentication with an invalid browser screen height value", priority = 98)
    public void transactionAuthenticationInvalidBrowserDetailsScreenHeightCase() {

        TransactionRequest transactionRequest = AppUtils.transactionRequestInstance();
        transactionRequest.getDevice().getBrowserDetails().setScreenHeight(-111);

        String body = CommonAutomationUtils.stringToJson(transactionRequest);
        Response response = AuthenticationTransactionRequest.postRequest(body, AppConstants.INTERNAL_END_POINT);

        CommonAutomationUtils.verifyCommonResponseSuccessValidation(response, HttpStatus.SC_OK);
    }

    @Test(groups = {"MC:Authentication", "Abdul Rehman", "SECTION:Device Browser Details Screen Heights Validation", "SC:Device Browser Details Screen Heights"}, description = "Validates transaction authentication with a browser screen height of zero", priority = 99)
    public void transactionAuthenticationZeroBrowserDetailsScreenHeightCase() {

        TransactionRequest transactionRequest = AppUtils.transactionRequestInstance();
        transactionRequest.getDevice().getBrowserDetails().setScreenHeight(0);

        String body = CommonAutomationUtils.stringToJson(transactionRequest);
        Response response = AuthenticationTransactionRequest.postRequest(body, AppConstants.INTERNAL_END_POINT);

        CommonAutomationUtils.verifyCommonResponseSuccessValidation(response, HttpStatus.SC_OK);
    }

    @Test(groups = {"MC:Authentication", "Abdul Rehman", "SECTION:Device Browser Details Screen Heights Validation", "SC:Device Browser Details Screen Heights"}, description = "Validates transaction authentication with a null browser screen height value", priority = 100)
    public void transactionAuthenticationNullBrowserDetailsScreenHeightCase() {

        TransactionRequest transactionRequest = AppUtils.transactionRequestInstance();
        transactionRequest.getDevice().getBrowserDetails().setScreenHeight(null);

        String body = CommonAutomationUtils.stringToJson(transactionRequest);
        Response response = AuthenticationTransactionRequest.postRequest(body, AppConstants.INTERNAL_END_POINT);

        CommonAutomationUtils.verifyCommonResponseSuccessValidation(response, HttpStatus.SC_OK);
        CommonAutomationUtils.verifyNonAvailableKey(response, List.of(AppConstants.DEVICE + "." + AppConstants.BROWSER_DETAIL + "." + AppConstants.SCREEN_HEIGHT));
    }

    @Test(groups = {"MC:Authentication", "Abdul Rehman", "SECTION:Device Browser Details Screen Heights Validation", "SC:Device Browser Details Screen Heights"}, description = "Validates transaction authentication with an empty browser details screen height", priority = 101)
    public void transactionAuthenticationEmptyBrowserDetailsScreenHeightCase() {

        TransactionRequest transactionRequest = AppUtils.transactionRequestInstance();

        String body = CommonAutomationUtils.stringToJson(transactionRequest);
        String updatedBody = CommonAutomationUtils.modifyJson(body, "MODIFY", "device.browserDetails.screenHeight", "");
        Response response = AuthenticationTransactionRequest.postRequest(updatedBody, AppConstants.INTERNAL_END_POINT);

        CommonAutomationUtils.verifyCommonResponseSuccessValidation(response, HttpStatus.SC_OK);
    }

    @Test(groups = {"MC:Authentication", "Abdul Rehman", "SECTION:Device Browser Details Screen Width Validation", "SC:Device Browser Details Screen Width"}, description = "Validates transaction authentication with an invalid browser details screen width", priority = 102)
    public void transactionAuthenticationInvalidBrowserDetailsScreenWidthCase() {

        TransactionRequest transactionRequest = AppUtils.transactionRequestInstance();
        transactionRequest.getDevice().getBrowserDetails().setScreenWidth(-111);

        String body = CommonAutomationUtils.stringToJson(transactionRequest);
        Response response = AuthenticationTransactionRequest.postRequest(body, AppConstants.INTERNAL_END_POINT);

        CommonAutomationUtils.verifyCommonResponseSuccessValidation(response, HttpStatus.SC_OK);
    }

    @Test(groups = {"MC:Authentication", "Abdul Rehman", "SECTION:Device Browser Details Screen Width Validation", "SC:Device Browser Details Screen Width"}, description = "Validates transaction authentication with a browser details screen width of zero", priority = 103)
    public void transactionAuthenticationZeroBrowserDetailsScreenWidthCase() {

        TransactionRequest transactionRequest = AppUtils.transactionRequestInstance();
        transactionRequest.getDevice().getBrowserDetails().setScreenWidth(0);

        String body = CommonAutomationUtils.stringToJson(transactionRequest);
        Response response = AuthenticationTransactionRequest.postRequest(body, AppConstants.INTERNAL_END_POINT);

        CommonAutomationUtils.verifyCommonResponseSuccessValidation(response, HttpStatus.SC_OK);
    }

    @Test(groups = {"MC:Authentication", "Abdul Rehman", "SECTION:Device Browser Details Screen Width Validation", "SC:Device Browser Details Screen Width"}, description = "Validates transaction authentication with a null browser details screen width", priority = 104)
    public void transactionAuthenticationNullBrowserDetailsScreenWidthCase() {

        TransactionRequest transactionRequest = AppUtils.transactionRequestInstance();
        transactionRequest.getDevice().getBrowserDetails().setScreenWidth(null);

        String body = CommonAutomationUtils.stringToJson(transactionRequest);
        Response response = AuthenticationTransactionRequest.postRequest(body, AppConstants.INTERNAL_END_POINT);

        CommonAutomationUtils.verifyCommonResponseSuccessValidation(response, HttpStatus.SC_OK);
        CommonAutomationUtils.verifyNonAvailableKey(response, List.of(AppConstants.DEVICE + "." + AppConstants.BROWSER_DETAIL + "." + AppConstants.SCREEN_WIDTH));
    }

    @Test(groups = {"MC:Authentication", "Abdul Rehman", "SECTION:Device Browser Details Screen Width Validation", "SC:Device Browser Details Screen Width"}, description = "Validates transaction authentication with an empty browser details screen width", priority = 105)
    public void transactionAuthenticationEmptyBrowserDetailsScreenWidthCase() {

        TransactionRequest transactionRequest = AppUtils.transactionRequestInstance();

        String body = CommonAutomationUtils.stringToJson(transactionRequest);
        String updatedBody = CommonAutomationUtils.modifyJson(body, "MODIFY", "device.browserDetails.screenWidth", "");
        Response response = AuthenticationTransactionRequest.postRequest(updatedBody, AppConstants.INTERNAL_END_POINT);

        CommonAutomationUtils.verifyCommonResponseSuccessValidation(response, HttpStatus.SC_OK);
    }

    @Test(groups = {"MC:Authentication", "Abdul Rehman", "SECTION:Device Browser Details Language Validation", "SC:Device Browser Details Language"}, description = "Validates transaction authentication with an invalid browser details language", priority = 106)
    public void transactionAuthenticationInvalidBrowserDetailsLanguageCase() {

        TransactionRequest transactionRequest = AppUtils.transactionRequestInstance();
        transactionRequest.getDevice().getBrowserDetails().setLanguage("een-USSS");

        String body = CommonAutomationUtils.stringToJson(transactionRequest);
        Response response = AuthenticationTransactionRequest.postRequest(body, AppConstants.INTERNAL_END_POINT);

        CommonAutomationUtils.verifyCommonResponseSuccessValidation(response, HttpStatus.SC_OK);
    }

    @Test(groups = {"MC:Authentication", "Abdul Rehman", "SECTION:Device Browser Details Language Validation", "SC:Device Browser Details Language"}, description = "Validates transaction authentication with a null browser details language", priority = 107)
    public void transactionAuthenticationNullBrowserDetailsLanguageCase() {

        TransactionRequest transactionRequest = AppUtils.transactionRequestInstance();
        transactionRequest.getDevice().getBrowserDetails().setLanguage(null);

        String body = CommonAutomationUtils.stringToJson(transactionRequest);
        Response response = AuthenticationTransactionRequest.postRequest(body, AppConstants.INTERNAL_END_POINT);

        CommonAutomationUtils.verifyCommonResponseSuccessValidation(response, HttpStatus.SC_OK);
        CommonAutomationUtils.verifyNonAvailableKey(response, List.of(AppConstants.DEVICE + "." + AppConstants.BROWSER_DETAIL + "." + AppConstants.LANGUAGE));
    }

    @Test(groups = {"MC:Authentication", "Abdul Rehman", "SECTION:Device Browser Details Language Validation", "SC:Device Browser Details Language"}, description = "Validates transaction authentication with an empty browser details language", priority = 108)
    public void transactionAuthenticationEmptyBrowserDetailsScreenLanguageCase() {

        TransactionRequest transactionRequest = AppUtils.transactionRequestInstance();

        String body = CommonAutomationUtils.stringToJson(transactionRequest);
        String updatedBody = CommonAutomationUtils.modifyJson(body, "MODIFY", "device.browserDetails.language", "");
        Response response = AuthenticationTransactionRequest.postRequest(updatedBody, AppConstants.INTERNAL_END_POINT);

        CommonAutomationUtils.verifyCommonResponseSuccessValidation(response, HttpStatus.SC_OK);
    }

    @Test(groups = {"MC:Authentication", "Abdul Rehman", "SECTION:Device Browser Details Color Depth Validation", "SC:Device Browser Details Color Depth"}, description = "Validates transaction authentication with a negative browser details color depth", priority = 109)
    public void transactionAuthenticationNegativeBrowserDetailsColorDepthCase() {

        TransactionRequest transactionRequest = AppUtils.transactionRequestInstance();
        transactionRequest.getDevice().getBrowserDetails().setColorDepth(-123);

        String body = CommonAutomationUtils.stringToJson(transactionRequest);
        Response response = AuthenticationTransactionRequest.postRequest(body, AppConstants.INTERNAL_END_POINT);

        CommonAutomationUtils.verifyCommonResponseSuccessValidation(response, HttpStatus.SC_OK);
    }

    @Test(groups = {"MC:Authentication", "Abdul Rehman", "SECTION:Device Browser Details Color Depth Validation", "SC:Device Browser Details Color Depth"}, description = "Validates transaction authentication with a browser details color depth of zero", priority = 110)
    public void transactionAuthenticationZeroBrowserDetailsColorDepthCase() {

        TransactionRequest transactionRequest = AppUtils.transactionRequestInstance();
        transactionRequest.getDevice().getBrowserDetails().setColorDepth(0);

        String body = CommonAutomationUtils.stringToJson(transactionRequest);
        Response response = AuthenticationTransactionRequest.postRequest(body, AppConstants.INTERNAL_END_POINT);

        CommonAutomationUtils.verifyCommonResponseSuccessValidation(response, HttpStatus.SC_OK);
    }

    @Test(groups = {"MC:Authentication", "Abdul Rehman", "SECTION:Device Browser Details Color Depth Validation", "SC:Device Browser Details Color Depth"}, description = "Validates transaction authentication with a null browser details color depth", priority = 111)
    public void transactionAuthenticationNullBrowserDetailsColorDepthCase() {

        TransactionRequest transactionRequest = AppUtils.transactionRequestInstance();
        transactionRequest.getDevice().getBrowserDetails().setColorDepth(null);

        String body = CommonAutomationUtils.stringToJson(transactionRequest);
        Response response = AuthenticationTransactionRequest.postRequest(body, AppConstants.INTERNAL_END_POINT);

        CommonAutomationUtils.verifyCommonResponseSuccessValidation(response, HttpStatus.SC_OK);
        CommonAutomationUtils.verifyNonAvailableKey(response, List.of(AppConstants.DEVICE + "." + AppConstants.BROWSER_DETAIL + "." + AppConstants.COLOR_DEPTH));
    }

    @Test(groups = {"MC:Authentication", "Abdul Rehman", "SECTION:Device Browser Details Color Depth Validation", "SC:Device Browser Details Color Depth"}, description = "Validates transaction authentication with an empty browser details color depth", priority = 112)
    public void transactionAuthenticationEmptyBrowserDetailsColorDepthCase() {

        TransactionRequest transactionRequest = AppUtils.transactionRequestInstance();

        String body = CommonAutomationUtils.stringToJson(transactionRequest);
        String updatedBody = CommonAutomationUtils.modifyJson(body, "MODIFY", "device.browserDetails.colorDepth", "");
        Response response = AuthenticationTransactionRequest.postRequest(updatedBody, AppConstants.INTERNAL_END_POINT);

        CommonAutomationUtils.verifyCommonResponseSuccessValidation(response, HttpStatus.SC_OK);
    }

    @Test(groups = {"MC:Authentication", "Abdul Rehman", "SECTION:Device Browser Details Java Enabled Validation", "SC:Device Browser Details Java Enabled"}, description = "Validates transaction authentication with browser details Java enabled set to false", priority = 113)
    public void transactionAuthenticationFalseBrowserDetailsJavaEnabledCase() {

        TransactionRequest transactionRequest = AppUtils.transactionRequestInstance();
        transactionRequest.getDevice().getBrowserDetails().setJavaEnabled("false");

        String body = CommonAutomationUtils.stringToJson(transactionRequest);
        Response response = AuthenticationTransactionRequest.postRequest(body, AppConstants.INTERNAL_END_POINT);

        CommonAutomationUtils.verifyCommonResponseSuccessValidation(response, HttpStatus.SC_OK);
    }

    @Test(groups = {"MC:Authentication", "Abdul Rehman", "SECTION:Device Browser Details Java Enabled Validation", "SC:Device Browser Details Java Enabled"}, description = "Validates transaction authentication with browser details Java enabled set to true", priority = 114)
    public void transactionAuthenticationTrueBrowserDetailsJavaEnabledCase() {

        TransactionRequest transactionRequest = AppUtils.transactionRequestInstance();
        transactionRequest.getDevice().getBrowserDetails().setJavaEnabled("true");

        String body = CommonAutomationUtils.stringToJson(transactionRequest);
        Response response = AuthenticationTransactionRequest.postRequest(body, AppConstants.INTERNAL_END_POINT);

        CommonAutomationUtils.verifyCommonResponseSuccessValidation(response, HttpStatus.SC_OK);
    }

    @Test(groups = {"MC:Authentication", "Abdul Rehman", "SECTION:Device Browser Details Java Enabled Validation", "SC:Device Browser Details Java Enabled"}, description = "Validates transaction authentication with case-sensitive Java enabled value", priority = 115)
    public void transactionAuthenticationCaseSensitiveBrowserDetailsJavaEnabledCase() {

        TransactionRequest transactionRequest = AppUtils.transactionRequestInstance();
        transactionRequest.getDevice().getBrowserDetails().setJavaEnabled("TRuE");

        String body = CommonAutomationUtils.stringToJson(transactionRequest);
        Response response = AuthenticationTransactionRequest.postRequest(body, AppConstants.INTERNAL_END_POINT);
        String textResponse = response.getBody().asString();
        JsonNode jsonResponse = CommonAutomationUtils.convertToJson(textResponse);

        CommonAutomationUtils.verifyCommonResponseFailedValidation(jsonResponse, response.getStatusCode(), HttpStatus.SC_BAD_REQUEST, AppConstants.UNACCEPTED_JSON_REQUEST_CODE, AppConstants.UNACCEPTED_JSON_REQUEST_ERROR);
    }

    @Test(groups = {"MC:Authentication", "Abdul Rehman", "SECTION:Device Browser Details Java Enabled Validation", "SC:Device Browser Details Java Enabled"}, description = "Validates transaction authentication with a null browser details Java enabled value", priority = 116)
    public void transactionAuthenticationNullBrowserDetailsJavaEnabledCase() {

        TransactionRequest transactionRequest = AppUtils.transactionRequestInstance();
        transactionRequest.getDevice().getBrowserDetails().setJavaEnabled(null);

        String body = CommonAutomationUtils.stringToJson(transactionRequest);
        Response response = AuthenticationTransactionRequest.postRequest(body, AppConstants.INTERNAL_END_POINT);

        CommonAutomationUtils.verifyCommonResponseSuccessValidation(response, HttpStatus.SC_OK);
        CommonAutomationUtils.verifyNonAvailableKey(response, List.of(AppConstants.DEVICE + "." + AppConstants.BROWSER_DETAIL + "." + AppConstants.JAVA_ENABLED));
    }

    @Test(groups = {"MC:Authentication", "Abdul Rehman", "SECTION:Device Browser Details Java Enabled Validation", "SC:Device Browser Details Java Enabled"}, description = "Validates transaction authentication with an empty browser details Java enabled value", priority = 117)
    public void transactionAuthenticationEmptyBrowserDetailsJavaEnabledCase() {

        TransactionRequest transactionRequest = AppUtils.transactionRequestInstance();

        String body = CommonAutomationUtils.stringToJson(transactionRequest);
        String updatedBody = CommonAutomationUtils.modifyJson(body, "MODIFY", "device.browserDetails.javaEnabled", "");
        Response response = AuthenticationTransactionRequest.postRequest(updatedBody, AppConstants.INTERNAL_END_POINT);

        CommonAutomationUtils.verifyCommonResponseSuccessValidation(response, HttpStatus.SC_OK);
    }

    @Test(groups = {"MC:Authentication", "Abdul Rehman", "SECTION:Device Browser Details Java Enabled Validation", "SC:Device Browser Details Java Enabled"}, description = "Validates transaction authentication with an invalid browser details Java enabled value", priority = 118)
    public void transactionAuthenticationInvalidBrowserDetailsJavaEnabledCase() {

        TransactionRequest transactionRequest = AppUtils.transactionRequestInstance();
        transactionRequest.getDevice().getBrowserDetails().setJavaEnabled("invalid");

        String body = CommonAutomationUtils.stringToJson(transactionRequest);
        Response response = AuthenticationTransactionRequest.postRequest(body, AppConstants.INTERNAL_END_POINT);
        String textResponse = response.getBody().asString();
        JsonNode jsonResponse = CommonAutomationUtils.convertToJson(textResponse);

        CommonAutomationUtils.verifyCommonResponseFailedValidation(jsonResponse, response.getStatusCode(), HttpStatus.SC_BAD_REQUEST, AppConstants.UNACCEPTED_JSON_REQUEST_CODE, AppConstants.UNACCEPTED_JSON_REQUEST_ERROR);
    }

    @Test(groups = {"MC:Authentication", "Abdul Rehman", "SECTION:Device Browser Details Timezone Validation", "SC:Device Browser Details Timezone"}, description = "Validates transaction authentication with a null browser details timezone", priority = 119)
    public void transactionAuthenticationNullBrowserDetailsTimezoneCase() {

        TransactionRequest transactionRequest = AppUtils.transactionRequestInstance();
        transactionRequest.getDevice().getBrowserDetails().setTimeZone(null);

        String body = CommonAutomationUtils.stringToJson(transactionRequest);
        Response response = AuthenticationTransactionRequest.postRequest(body, AppConstants.INTERNAL_END_POINT);

        CommonAutomationUtils.verifyCommonResponseSuccessValidation(response, HttpStatus.SC_OK);
        CommonAutomationUtils.verifyNonAvailableKey(response, List.of(AppConstants.DEVICE + "." + AppConstants.BROWSER_DETAIL + "." + AppConstants.BROWSER_DETAIL));
    }

    @Test(groups = {"MC:Authentication", "Abdul Rehman", "SECTION:Device Browser Details Timezone Validation", "SC:Device Browser Details Timezone"}, description = "Validates transaction authentication with an empty browser details timezone", priority = 120)
    public void transactionAuthenticationEmptyBrowserDetailsTimezoneCase() {

        TransactionRequest transactionRequest = AppUtils.transactionRequestInstance();

        String body = CommonAutomationUtils.stringToJson(transactionRequest);
        String updatedBody = CommonAutomationUtils.modifyJson(body, "MODIFY", "device.browserDetails.timeZone", "");
        Response response = AuthenticationTransactionRequest.postRequest(updatedBody, AppConstants.INTERNAL_END_POINT);

        CommonAutomationUtils.verifyCommonResponseSuccessValidation(response, HttpStatus.SC_OK);
    }

    @Test(groups = {"MC:Authentication", "Abdul Rehman", "SECTION:Device Browser Details Timezone Validation", "SC:Device Browser Details Timezone"}, description = "Validates transaction authentication with an invalid browser details timezone", priority = 121)
    public void transactionAuthenticationInvalidBrowserDetailsTimezoneCase() {

        TransactionRequest transactionRequest = AppUtils.transactionRequestInstance();
        transactionRequest.getDevice().getBrowserDetails().setTimeZone(-99999);

        String body = CommonAutomationUtils.stringToJson(transactionRequest);
        Response response = AuthenticationTransactionRequest.postRequest(body, AppConstants.INTERNAL_END_POINT);

        CommonAutomationUtils.verifyCommonResponseSuccessValidation(response, HttpStatus.SC_OK);
    }

    @Test(groups = {"MC:Authentication", "Abdul Rehman", "SECTION:Device Browser Details Timezone Validation", "SC:Device Browser Details Timezone"}, description = "Validates transaction authentication with a valid browser details timezone", priority = 122)
    public void transactionAuthenticationValidBrowserDetailsTimezoneCase() {

        TransactionRequest transactionRequest = AppUtils.transactionRequestInstance();
        transactionRequest.getDevice().getBrowserDetails().setTimeZone(100);

        String body = CommonAutomationUtils.stringToJson(transactionRequest);
        Response response = AuthenticationTransactionRequest.postRequest(body, AppConstants.INTERNAL_END_POINT);

        CommonAutomationUtils.verifyCommonResponseSuccessValidation(response, HttpStatus.SC_OK);
    }

    @Test(groups = {"MC:Authentication", "Abdul Rehman", "SECTION:Device Browser Details Accept Header Validation", "SC:Device Browser Details Accept Header"}, description = "Validates transaction authentication with a null browser details accept headers", priority = 123)
    public void transactionAuthenticationNullBrowserDetailsAcceptHeadersCase() {

        TransactionRequest transactionRequest = AppUtils.transactionRequestInstance();
        transactionRequest.getDevice().getBrowserDetails().setAcceptHeaders(null);

        String body = CommonAutomationUtils.stringToJson(transactionRequest);
        Response response = AuthenticationTransactionRequest.postRequest(body, AppConstants.INTERNAL_END_POINT);

        CommonAutomationUtils.verifyCommonResponseSuccessValidation(response, HttpStatus.SC_OK);
        CommonAutomationUtils.verifyNonAvailableKey(response, List.of(AppConstants.DEVICE + "." + AppConstants.BROWSER_DETAIL + "." + AppConstants.ACCEPT_HEADER));
    }

    @Test(groups = {"MC:Authentication", "Abdul Rehman", "SECTION:Device Browser Details Accept Header Validation", "SC:Device Browser Details Accept Header"}, description = "Validates transaction authentication with an empty browser details accept headers", priority = 124)
    public void transactionAuthenticationEmptyBrowserDetailsAcceptHeadersCase() {

        TransactionRequest transactionRequest = AppUtils.transactionRequestInstance();

        String body = CommonAutomationUtils.stringToJson(transactionRequest);
        String updatedBody = CommonAutomationUtils.modifyJson(body, "MODIFY", "device.browserDetails.acceptHeaders", "");
        Response response = AuthenticationTransactionRequest.postRequest(updatedBody, AppConstants.INTERNAL_END_POINT);

        CommonAutomationUtils.verifyCommonResponseSuccessValidation(response, HttpStatus.SC_OK);
    }

    @Test(groups = {"MC:Authentication", "Abdul Rehman", "SECTION:Device Browser Details Accept Header Validation", "SC:Device Browser Details Accept Header"}, description = "Validates transaction authentication with an invalid browser details accept headers", priority = 125)
    public void transactionAuthenticationInvalidBrowserDetailsAcceptHeadersCase() {

        TransactionRequest transactionRequest = AppUtils.transactionRequestInstance();
        transactionRequest.getDevice().getBrowserDetails().setAcceptHeaders("invalid_header");

        String body = CommonAutomationUtils.stringToJson(transactionRequest);
        Response response = AuthenticationTransactionRequest.postRequest(body, AppConstants.INTERNAL_END_POINT);

        CommonAutomationUtils.verifyCommonResponseSuccessValidation(response, HttpStatus.SC_OK);
    }

    @Test(groups = {"MC:Authentication", "Abdul Rehman", "SECTION:Device Browser Details Accept Header Validation", "SC:Device Browser Details Accept Header"}, description = "Validates transaction authentication with a valid browser details accept headers", priority = 126)
    public void transactionAuthenticationValidBrowserDetailsAcceptHeadersCase() {

        TransactionRequest transactionRequest = AppUtils.transactionRequestInstance();
        transactionRequest.getDevice().getBrowserDetails().setAcceptHeaders("application/json");

        String body = CommonAutomationUtils.stringToJson(transactionRequest);
        Response response = AuthenticationTransactionRequest.postRequest(body, AppConstants.INTERNAL_END_POINT);

        CommonAutomationUtils.verifyCommonResponseSuccessValidation(response, HttpStatus.SC_OK);
    }

    @Test(groups = {"MC:Authentication", "Abdul Rehman", "SECTION:Device Browser Details Accept Header Validation", "SC:Device Browser Details Accept Header"}, description = "Validates transaction authentication with multiple valid browser details accept headers", priority = 127)
    public void transactionAuthenticationMultipleValidBrowserDetailsAcceptHeadersCase() {

        TransactionRequest transactionRequest = AppUtils.transactionRequestInstance();
        transactionRequest.getDevice().getBrowserDetails().setAcceptHeaders("application/json, application/xml");

        String body = CommonAutomationUtils.stringToJson(transactionRequest);
        Response response = AuthenticationTransactionRequest.postRequest(body, AppConstants.INTERNAL_END_POINT);

        CommonAutomationUtils.verifyCommonResponseSuccessValidation(response, HttpStatus.SC_OK);
    }

    @Test(groups = {"MC:Authentication", "Abdul Rehman", "SECTION:Device Browser Details Challenge Window Validation", "SC:Device Browser Details Challenge Window"}, description = "Validates transaction authentication with a valid fullscreen browser details challenge window size", priority = 128)
    public void transactionAuthenticationValidFullScreenBrowserDetailsChallengeWindowSizeCase() {

        TransactionRequest transactionRequest = AppUtils.transactionRequestInstance();
        transactionRequest.getDevice().getBrowserDetails().setChallengeWindowSize("FULL_SCREEN");

        String body = CommonAutomationUtils.stringToJson(transactionRequest);
        Response response = AuthenticationTransactionRequest.postRequest(body, AppConstants.INTERNAL_END_POINT);

        CommonAutomationUtils.verifyCommonResponseSuccessValidation(response, HttpStatus.SC_OK);
    }

    @Test(groups = {"MC:Authentication", "Abdul Rehman", "SECTION:Device Browser Details Challenge Window Validation", "SC:Device Browser Details Challenge Window"}, description = "Validates transaction authentication with a valid 250x400 browser details challenge window size", priority = 129)
    public void transactionAuthenticationValid250X400BrowserDetailsChallengeWindowSizeCase() {

        TransactionRequest transactionRequest = AppUtils.transactionRequestInstance();
        transactionRequest.getDevice().getBrowserDetails().setChallengeWindowSize("250_X_400");

        String body = CommonAutomationUtils.stringToJson(transactionRequest);
        Response response = AuthenticationTransactionRequest.postRequest(body, AppConstants.INTERNAL_END_POINT);

        CommonAutomationUtils.verifyCommonResponseSuccessValidation(response, HttpStatus.SC_OK);
    }

    @Test(groups = {"MC:Authentication", "Abdul Rehman", "SECTION:Device Browser Details Challenge Window Validation", "SC:Device Browser Details Challenge Window"}, description = "Validates transaction authentication with a valid 390x400 browser details challenge window size", priority = 130)
    public void transactionAuthenticationValid390_X_400BrowserDetailsChallengeWindowSizeCase() {

        TransactionRequest transactionRequest = AppUtils.transactionRequestInstance();
        transactionRequest.getDevice().getBrowserDetails().setChallengeWindowSize("390_X_400");

        String body = CommonAutomationUtils.stringToJson(transactionRequest);
        Response response = AuthenticationTransactionRequest.postRequest(body, AppConstants.INTERNAL_END_POINT);

        CommonAutomationUtils.verifyCommonResponseSuccessValidation(response, HttpStatus.SC_OK);
    }

    @Test(groups = {"MC:Authentication", "Abdul Rehman", "SECTION:Device Browser Details Challenge Window Validation", "SC:Device Browser Details Challenge Window"}, description = "Validates transaction authentication with a valid 500x600 browser details challenge window size", priority = 131)
    public void transactionAuthenticationValid500_X_600BrowserDetailsChallengeWindowSizeCase() {

        TransactionRequest transactionRequest = AppUtils.transactionRequestInstance();
        transactionRequest.getDevice().getBrowserDetails().setChallengeWindowSize("500_X_600");

        String body = CommonAutomationUtils.stringToJson(transactionRequest);
        Response response = AuthenticationTransactionRequest.postRequest(body, AppConstants.INTERNAL_END_POINT);

        CommonAutomationUtils.verifyCommonResponseSuccessValidation(response, HttpStatus.SC_OK);
    }

    @Test(groups = {"MC:Authentication", "Abdul Rehman", "SECTION:Device Browser Details Challenge Window Validation", "SC:Device Browser Details Challenge Window"}, description = "Validates transaction authentication with a valid 600x400 browser details challenge window size", priority = 132)
    public void transactionAuthenticationValid600_X_400BrowserDetailsChallengeWindowSizeCase() {

        TransactionRequest transactionRequest = AppUtils.transactionRequestInstance();
        transactionRequest.getDevice().getBrowserDetails().setChallengeWindowSize("600_X_400");

        String body = CommonAutomationUtils.stringToJson(transactionRequest);
        Response response = AuthenticationTransactionRequest.postRequest(body, AppConstants.INTERNAL_END_POINT);

        CommonAutomationUtils.verifyCommonResponseSuccessValidation(response, HttpStatus.SC_OK);
    }

    @Test(groups = {"MC:Authentication", "Abdul Rehman", "SECTION:Device Browser Details Challenge Window Validation", "SC:Device Browser Details Challenge Window"}, description = "Validates transaction authentication with a null browser details challenge window size", priority = 133)
    public void transactionAuthenticationNullBrowserDetailsChallengeWindowSizeCase() {

        TransactionRequest transactionRequest = AppUtils.transactionRequestInstance();
        transactionRequest.getDevice().getBrowserDetails().setChallengeWindowSize(null);

        String body = CommonAutomationUtils.stringToJson(transactionRequest);
        Response response = AuthenticationTransactionRequest.postRequest(body, AppConstants.INTERNAL_END_POINT);

        CommonAutomationUtils.verifyCommonResponseSuccessValidation(response, HttpStatus.SC_OK);
        CommonAutomationUtils.verifyNonAvailableKey(response, List.of(AppConstants.DEVICE + "." + AppConstants.BROWSER_DETAIL + "." + AppConstants.CHALLENGE_WINDOW_SIZE));
    }

    @Test(groups = {"MC:Authentication", "Abdul Rehman", "SECTION:Device Browser Details Challenge Window Validation", "SC:Device Browser Details Challenge Window"}, description = "Validates transaction authentication with an empty browser details challenge window size", priority = 134)
    public void transactionAuthenticationEmptyBrowserDetailsChallengeWindowSizeCase() {

        TransactionRequest transactionRequest = AppUtils.transactionRequestInstance();

        String body = CommonAutomationUtils.stringToJson(transactionRequest);
        String updatedBody = CommonAutomationUtils.modifyJson(body, "MODIFY", "device.browserDetails.challengeWindowSize", "");
        Response response = AuthenticationTransactionRequest.postRequest(updatedBody, AppConstants.INTERNAL_END_POINT);

        CommonAutomationUtils.verifyCommonResponseSuccessValidation(response, HttpStatus.SC_OK);
    }

    @Test(groups = {"MC:Authentication", "Abdul Rehman", "SECTION:Device Browser Details Challenge Window Validation", "SC:Device Browser Details Challenge Window"}, description = "Validates transaction authentication with an invalid browser details challenge window size", priority = 135)
    public void transactionAuthenticationInvalidBrowserDetailsChallengeWindowSizeCase() {

        TransactionRequest transactionRequest = AppUtils.transactionRequestInstance();
        transactionRequest.getDevice().getBrowserDetails().setChallengeWindowSize("invalid_challenge_window_size");

        String body = CommonAutomationUtils.stringToJson(transactionRequest);
        Response response = AuthenticationTransactionRequest.postRequest(body, AppConstants.INTERNAL_END_POINT);

        CommonAutomationUtils.verifyCommonResponseSuccessValidation(response, HttpStatus.SC_OK);
    }

    @Test(groups = {"MC:Authentication", "Abdul Rehman", "SECTION:Device Browser User Agent Validation", "SC:Device Browser User Agent"}, description = "Validates transaction authentication with a valid browser details user agent", priority = 136)
    public void transactionAuthenticationValidBrowserDetailsBrowserUserAgentCase() {

        TransactionRequest transactionRequest = AppUtils.transactionRequestInstance();
        transactionRequest.getDevice().getBrowserDetails().setBrowserUserAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/128.0.0.0 Safari/537.36");

        String body = CommonAutomationUtils.stringToJson(transactionRequest);
        Response response = AuthenticationTransactionRequest.postRequest(body, AppConstants.INTERNAL_END_POINT);

        CommonAutomationUtils.verifyCommonResponseSuccessValidation(response, HttpStatus.SC_OK);
    }

    @Test(groups = {"MC:Authentication", "Abdul Rehman", "SECTION:Device Browser User Agent Validation", "SC:Device Browser User Agent"}, description = "Validates transaction authentication with a null browser details user agent", priority = 137)
    public void transactionAuthenticationNullBrowserDetailsBrowserUserAgentCase() {

        TransactionRequest transactionRequest = AppUtils.transactionRequestInstance();
        transactionRequest.getDevice().getBrowserDetails().setBrowserUserAgent(null);

        String body = CommonAutomationUtils.stringToJson(transactionRequest);
        Response response = AuthenticationTransactionRequest.postRequest(body, AppConstants.INTERNAL_END_POINT);

        CommonAutomationUtils.verifyCommonResponseSuccessValidation(response, HttpStatus.SC_OK);
        CommonAutomationUtils.verifyNonAvailableKey(response, List.of(AppConstants.DEVICE + "." + AppConstants.BROWSER_DETAIL + "." + AppConstants.BROWSER_USER_AGENT));
    }

    @Test(groups = {"MC:Authentication", "Abdul Rehman", "SECTION:Device Browser User Agent Validation", "SC:Device Browser User Agent"}, description = "Validates transaction authentication with an empty browser details user agent", priority = 138)
    public void transactionAuthenticationEmptyBrowserDetailsBrowserUserAgentCase() {

        TransactionRequest transactionRequest = AppUtils.transactionRequestInstance();

        String body = CommonAutomationUtils.stringToJson(transactionRequest);
        String updatedBody = CommonAutomationUtils.modifyJson(body, "MODIFY", "device.browserDetails.browserUserAgent", "");
        Response response = AuthenticationTransactionRequest.postRequest(updatedBody, AppConstants.INTERNAL_END_POINT);

        CommonAutomationUtils.verifyCommonResponseSuccessValidation(response, HttpStatus.SC_OK);
    }

    @Test(groups = {"MC:Authentication", "Abdul Rehman", "SECTION:Device Browser User Agent Validation", "SC:Device Browser User Agent"}, description = "Validates transaction authentication with an invalid browser details user agent", priority = 139)
    public void transactionAuthenticationInvalidBrowserDetailsBrowserUserAgentCase() {

        TransactionRequest transactionRequest = AppUtils.transactionRequestInstance();
        transactionRequest.getDevice().getBrowserDetails().setBrowserUserAgent("invalid_browser_user_agent");

        String body = CommonAutomationUtils.stringToJson(transactionRequest);
        Response response = AuthenticationTransactionRequest.postRequest(body, AppConstants.INTERNAL_END_POINT);

        CommonAutomationUtils.verifyCommonResponseSuccessValidation(response, HttpStatus.SC_OK);
    }

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

    @Test(groups = {"MC:Authentication", "Abdul Rehman", "SECTION:Source Id Validation", "SC:Source Id"}, description = "Validates transaction authentication with a null source ID", priority = 142)
    public void transactionAuthenticationNullSourceIdCase() {

        TransactionRequest transactionRequest = AppUtils.transactionRequestInstance();
        transactionRequest.getSource().setId(null);

        String body = CommonAutomationUtils.stringToJson(transactionRequest);
        Response response = AuthenticationTransactionRequest.postRequest(body, AppConstants.INTERNAL_END_POINT);
        String textResponse = response.getBody().asString();
        JsonNode jsonResponse = CommonAutomationUtils.convertToJson(textResponse);

        CommonAutomationUtils.verifyCommonResponseFailedValidation(jsonResponse, response.getStatusCode(), HttpStatus.SC_BAD_REQUEST, AppConstants.REQUIRED_FIELD_INVALID_CODE, AppConstants.REQUIRED_FIELD_INVALID_ERROR);
    }

    @Test(groups = {"MC:Authentication", "Abdul Rehman", "SECTION:Source Id Validation", "SC:Source Id"}, description = "Validates transaction authentication with an empty source ID", priority = 143)
    public void transactionAuthenticationEmptySourceIdCase() {

        TransactionRequest transactionRequest = AppUtils.transactionRequestInstance();

        String body = CommonAutomationUtils.stringToJson(transactionRequest);
        String updatedBody = CommonAutomationUtils.modifyJson(body, "MODIFY", "source.id", "");
        Response response = AuthenticationTransactionRequest.postRequest(updatedBody, AppConstants.INTERNAL_END_POINT);
        String textResponse = response.getBody().asString();
        JsonNode jsonResponse = CommonAutomationUtils.convertToJson(textResponse);

        CommonAutomationUtils.verifyCommonResponseFailedDescriptionValidation(jsonResponse, response.getStatusCode(), HttpStatus.SC_BAD_REQUEST, AppConstants.INVALID_EMPTY_TOKEN_ID_CODE, AppConstants.INVALID_EMPTY_TOKEN_ID_DESCRIPTION);
    }

    @Test(groups = {"MC:Authentication", "Abdul Rehman", "SECTION:Source Id Validation", "SC:Source Id"}, description = "Validates transaction authentication with an invalid source ID", priority = 144)
    public void transactionAuthenticationInvalidSourceIdCase() {

        TransactionRequest transactionRequest = AppUtils.transactionRequestInstance();
        transactionRequest.getSource().setId("invalid_source_id");

        String body = CommonAutomationUtils.stringToJson(transactionRequest);
        Response response = AuthenticationTransactionRequest.postRequest(body, AppConstants.INTERNAL_END_POINT);
        String textResponse = response.getBody().asString();
        JsonNode jsonResponse = CommonAutomationUtils.convertToJson(textResponse);

        CommonAutomationUtils.verifyCommonResponseFailedDescriptionValidation(jsonResponse, response.getStatusCode(), HttpStatus.SC_BAD_REQUEST, AppConstants.INVALID_TOKEN_ID_CODE, AppConstants.INVALID_TOKEN_ID_DESCRIPTION);
    }

    @Test(groups = {"MC:Authentication", "Abdul Rehman", "SECTION:Source Card First Six Validation", "SC:Source Card First Six"}, description = "Validates transaction authentication with a valid source card first six digits", priority = 145)
    public void transactionAuthenticationValidSourceCardFirstSixCase() {

        TransactionRequest transactionRequest = AppUtils.transactionRequestInstance();
        transactionRequest.getSource().getCard().setFirstSix("484783");

        String body = CommonAutomationUtils.stringToJson(transactionRequest);
        Response response = AuthenticationTransactionRequest.postRequest(body, AppConstants.INTERNAL_END_POINT);

        CommonAutomationUtils.verifyCommonResponseSuccessValidation(response, HttpStatus.SC_OK, Map.of(AppConstants.CARD + "." + AppConstants.FIRST_SIX, "484783"));
    }

    @Test(groups = {"MC:Authentication", "Abdul Rehman", "SECTION:Source Card First Six Validation", "SC:Source Card First Six"}, description = "Validates transaction authentication with less than six digits in source card first six", priority = 146)
    public void transactionAuthenticationLessSixDigitSourceCardFirstSixCase() {

        TransactionRequest transactionRequest = AppUtils.transactionRequestInstance();
        transactionRequest.getSource().getCard().setFirstSix("4847");

        String body = CommonAutomationUtils.stringToJson(transactionRequest);
        Response response = AuthenticationTransactionRequest.postRequest(body, AppConstants.INTERNAL_END_POINT);

        CommonAutomationUtils.verifyCommonResponseSuccessValidation(response, HttpStatus.SC_OK, Map.of(AppConstants.CARD + "." + AppConstants.FIRST_SIX, "4847"));
    }

    @Test(groups = {"MC:Authentication", "Abdul Rehman", "SECTION:Source Card First Six Validation", "SC:Source Card First Six"}, description = "Validates transaction authentication with string value in source card first six digits", priority = 147)
    public void transactionAuthenticationStringSourceCardFirstSixCase() {

        TransactionRequest transactionRequest = AppUtils.transactionRequestInstance();
        transactionRequest.getSource().getCard().setFirstSix("abcdef");

        String body = CommonAutomationUtils.stringToJson(transactionRequest);
        Response response = AuthenticationTransactionRequest.postRequest(body, AppConstants.INTERNAL_END_POINT);
        String textResponse = response.getBody().asString();
        JsonNode jsonResponse = CommonAutomationUtils.convertToJson(textResponse);

        CommonAutomationUtils.verifyCommonResponseFailedValidation(jsonResponse, response.getStatusCode(), HttpStatus.SC_BAD_REQUEST, AppConstants.INVALID_DATA_CODE, AppConstants.INVALID_DATA_ERROR);
    }

    @Test(groups = {"MC:Authentication", "Abdul Rehman", "SECTION:Source Card First Six Validation", "SC:Source Card First Six"}, description = "Validates transaction authentication with leading zeroes in source card first six digits", priority = 148)
    public void transactionAuthenticationLeadingZeroSourceCardFirstSixCase() {

        TransactionRequest transactionRequest = AppUtils.transactionRequestInstance();
        transactionRequest.getSource().getCard().setFirstSix("000000");

        String body = CommonAutomationUtils.stringToJson(transactionRequest);
        Response response = AuthenticationTransactionRequest.postRequest(body, AppConstants.INTERNAL_END_POINT);

        CommonAutomationUtils.verifyCommonResponseSuccessValidation(response, HttpStatus.SC_OK, Map.of(AppConstants.CARD + "." + AppConstants.FIRST_SIX, "000000"));
    }

    @Test(groups = {"MC:Authentication", "Abdul Rehman", "SECTION:Source Card First Six Validation", "SC:Source Card First Six"}, description = "Validates transaction authentication with a null source card first six digits", priority = 149)
    public void transactionAuthenticationNullSourceCardFirstSixCase() {

        TransactionRequest transactionRequest = AppUtils.transactionRequestInstance();
        transactionRequest.getSource().getCard().setFirstSix(null);

        String body = CommonAutomationUtils.stringToJson(transactionRequest);
        Response response = AuthenticationTransactionRequest.postRequest(body, AppConstants.INTERNAL_END_POINT);
        String textResponse = response.getBody().asString();
        JsonNode jsonResponse = CommonAutomationUtils.convertToJson(textResponse);

        CommonAutomationUtils.verifyCommonResponseFailedValidation(jsonResponse, response.getStatusCode(), HttpStatus.SC_BAD_REQUEST, AppConstants.INVALID_DATA_CODE, AppConstants.INVALID_DATA_ERROR);
    }

    @Test(groups = {"MC:Authentication", "Abdul Rehman", "SECTION:Source Card First Six Validation", "SC:Source Card First Six"}, description = "Validates transaction authentication with an empty source card first six digits", priority = 150)
    public void transactionAuthenticationEmptySourceCardFirstSixCase() {

        TransactionRequest transactionRequest = AppUtils.transactionRequestInstance();
        transactionRequest.getSource().getCard().setFirstSix("");

        String body = CommonAutomationUtils.stringToJson(transactionRequest);
        Response response = AuthenticationTransactionRequest.postRequest(body, AppConstants.INTERNAL_END_POINT);
        String textResponse = response.getBody().asString();
        JsonNode jsonResponse = CommonAutomationUtils.convertToJson(textResponse);

        CommonAutomationUtils.verifyCommonResponseFailedValidation(jsonResponse, response.getStatusCode(), HttpStatus.SC_BAD_REQUEST, AppConstants.INVALID_DATA_CODE, AppConstants.INVALID_DATA_ERROR);
    }

    @Test(groups = {"MC:Authentication", "Abdul Rehman", "SECTION:Source Card First Six Validation", "SC:Source Card First Six"}, description = "Validates transaction authentication with no source card first six digits", priority = 151)
    public void transactionAuthenticationNoSourceCardFirstSixCase() {

        TransactionRequest transactionRequest = AppUtils.transactionRequestInstance();
        transactionRequest.getSource().getCard().setFirstSix("");

        String body = CommonAutomationUtils.stringToJson(transactionRequest);
        Response response = AuthenticationTransactionRequest.postRequest(body, AppConstants.INTERNAL_END_POINT);
        String textResponse = response.getBody().asString();
        JsonNode jsonResponse = CommonAutomationUtils.convertToJson(textResponse);

        CommonAutomationUtils.verifyCommonResponseFailedValidation(jsonResponse, response.getStatusCode(), HttpStatus.SC_BAD_REQUEST, AppConstants.INVALID_DATA_CODE, AppConstants.INVALID_DATA_ERROR);
    }

    @Test(groups = {"MC:Authentication", "Abdul Rehman", "SECTION:Source Card Scheme Validation", "SC:Source Card Scheme"}, description = "Validates transaction authentication with a valid source card scheme", priority = 152)
    public void transactionAuthenticationValidSourceCardSchemeCase() {

        TransactionRequest transactionRequest = AppUtils.transactionRequestInstance();
        transactionRequest.getSource().getCard().setScheme("MADA");

        String body = CommonAutomationUtils.stringToJson(transactionRequest);
        Response response = AuthenticationTransactionRequest.postRequest(body, AppConstants.INTERNAL_END_POINT);

        CommonAutomationUtils.verifyCommonResponseSuccessValidation(response, HttpStatus.SC_OK, Map.of(AppConstants.CARD + "." + AppConstants.SCHEME, "MADA"));
    }

    @Test(groups = {"MC:Authentication", "Abdul Rehman", "SECTION:Source Card Scheme Validation", "SC:Source Card Scheme"}, description = "Validates transaction authentication with an invalid source card scheme", priority = 153)
    public void transactionAuthenticationInvalidSourceCardSchemeCase() {

        TransactionRequest transactionRequest = AppUtils.transactionRequestInstance();
        transactionRequest.getSource().getCard().setScheme("invalid_scheme");

        String body = CommonAutomationUtils.stringToJson(transactionRequest);
        Response response = AuthenticationTransactionRequest.postRequest(body, AppConstants.INTERNAL_END_POINT);
        String textResponse = response.getBody().asString();
        JsonNode jsonResponse = CommonAutomationUtils.convertToJson(textResponse);

        CommonAutomationUtils.verifyCommonResponseFailedValidation(jsonResponse, response.getStatusCode(), HttpStatus.SC_BAD_REQUEST, AppConstants.INVALID_DATA_CODE, AppConstants.INVALID_DATA_ERROR);
    }

    @Test(groups = {"MC:Authentication", "Abdul Rehman", "SECTION:Source Card Scheme Validation", "SC:Source Card Scheme"}, description = "Validates transaction authentication with a null source card scheme", priority = 154)
    public void transactionAuthenticationNullSourceCardSchemeCase() {

        TransactionRequest transactionRequest = AppUtils.transactionRequestInstance();
        transactionRequest.getSource().getCard().setScheme(null);

        String body = CommonAutomationUtils.stringToJson(transactionRequest);
        Response response = AuthenticationTransactionRequest.postRequest(body, AppConstants.INTERNAL_END_POINT);
        String textResponse = response.getBody().asString();
        JsonNode jsonResponse = CommonAutomationUtils.convertToJson(textResponse);

        CommonAutomationUtils.verifyCommonResponseFailedValidation(jsonResponse, response.getStatusCode(), HttpStatus.SC_BAD_REQUEST, AppConstants.INVALID_DATA_CODE, AppConstants.INVALID_DATA_ERROR);
    }

    @Test(groups = {"MC:Authentication", "Abdul Rehman", "SECTION:Source Card Scheme Validation", "SC:Source Card Scheme"}, description = "Validates transaction authentication with an empty source card scheme", priority = 155)
    public void transactionAuthenticationEmptySourceCardSchemeCase() {

        TransactionRequest transactionRequest = AppUtils.transactionRequestInstance();
        transactionRequest.getSource().getCard().setScheme("");

        String body = CommonAutomationUtils.stringToJson(transactionRequest);
        Response response = AuthenticationTransactionRequest.postRequest(body, AppConstants.INTERNAL_END_POINT);

        CommonAutomationUtils.verifyCommonResponseSuccessValidation(response, HttpStatus.SC_OK, Map.of(AppConstants.CARD + "." + AppConstants.SCHEME, ""));
    }

    @Test(groups = {"MC:Authentication", "Abdul Rehman", "SECTION:Source Card Scheme Validation", "SC:Source Card Scheme"}, description = "Validates transaction authentication with no source card scheme", priority = 156)
    public void transactionAuthenticationNoSourceCardSchemeCase() {

        TransactionRequest transactionRequest = AppUtils.transactionRequestInstance();

        String body = CommonAutomationUtils.stringToJson(transactionRequest);
        String updatedBody = CommonAutomationUtils.modifyJson(body, "DELETE", AppConstants.SOURCE + "." + AppConstants.CARD + "." + AppConstants.SCHEME, null);
        Response response = AuthenticationTransactionRequest.postRequest(updatedBody, AppConstants.INTERNAL_END_POINT);

        CommonAutomationUtils.verifyCommonResponseSuccessValidation(response, HttpStatus.SC_OK);
    }

    @Test(groups = {"MC:Authentication", "Abdul Rehman", "SECTION:Source Card Brand Validation", "SC:Source Card Brand"}, description = "Validates transaction authentication with a valid source card brand", priority = 157)
    public void transactionAuthenticationValidSourceCardBrandCase() {

        TransactionRequest transactionRequest = AppUtils.transactionRequestInstance();
        transactionRequest.getSource().getCard().setBrand("VISA");

        String body = CommonAutomationUtils.stringToJson(transactionRequest);
        Response response = AuthenticationTransactionRequest.postRequest(body, AppConstants.INTERNAL_END_POINT);

        CommonAutomationUtils.verifyCommonResponseSuccessValidation(response, HttpStatus.SC_OK, Map.of(AppConstants.CARD + "." + AppConstants.BRAND, "VISA"));
    }

    @Test(groups = {"MC:Authentication", "Abdul Rehman", "SECTION:Source Card Brand Validation", "SC:Source Card Brand"}, description = "Validates transaction authentication with an invalid source card brand", priority = 158)
    public void transactionAuthenticationInvalidSourceCardBrandCase() {

        TransactionRequest transactionRequest = AppUtils.transactionRequestInstance();
        transactionRequest.getSource().getCard().setBrand("invalid_brand");

        String body = CommonAutomationUtils.stringToJson(transactionRequest);
        Response response = AuthenticationTransactionRequest.postRequest(body, AppConstants.INTERNAL_END_POINT);

        CommonAutomationUtils.verifyCommonResponseSuccessValidation(response, HttpStatus.SC_OK, Map.of(AppConstants.CARD + "." + AppConstants.BRAND, "invalid_brand"));
    }

    @Test(groups = {"MC:Authentication", "Abdul Rehman", "SECTION:Source Card Brand Validation", "SC:Source Card Brand"}, description = "Validates transaction authentication with a null source card brand", priority = 159)
    public void transactionAuthenticationNullSourceCardBrandCase() {

        TransactionRequest transactionRequest = AppUtils.transactionRequestInstance();
        transactionRequest.getSource().getCard().setBrand(null);

        String body = CommonAutomationUtils.stringToJson(transactionRequest);
        Response response = AuthenticationTransactionRequest.postRequest(body, AppConstants.INTERNAL_END_POINT);
        String textResponse = response.getBody().asString();
        JsonNode jsonResponse = CommonAutomationUtils.convertToJson(textResponse);

        CommonAutomationUtils.verifyCommonResponseFailedValidation(jsonResponse, response.getStatusCode(), HttpStatus.SC_BAD_REQUEST, AppConstants.REQUIRED_FIELD_INVALID_CODE, AppConstants.REQUIRED_FIELD_INVALID_ERROR);
    }

    @Test(groups = {"MC:Authentication", "Abdul Rehman", "SECTION:Source Card Brand Validation", "SC:Source Card Brand"}, description = "Validates transaction authentication with an empty source card brand", priority = 160)
    public void transactionAuthenticationEmptySourceCardBrandCase() {

        TransactionRequest transactionRequest = AppUtils.transactionRequestInstance();
        transactionRequest.getSource().getCard().setBrand("");

        String body = CommonAutomationUtils.stringToJson(transactionRequest);
        Response response = AuthenticationTransactionRequest.postRequest(body, AppConstants.INTERNAL_END_POINT);
        String textResponse = response.getBody().asString();
        JsonNode jsonResponse = CommonAutomationUtils.convertToJson(textResponse);

        CommonAutomationUtils.verifyCommonResponseFailedValidation(jsonResponse, response.getStatusCode(), HttpStatus.SC_BAD_REQUEST, AppConstants.REQUIRED_FIELD_INVALID_CODE, AppConstants.REQUIRED_FIELD_INVALID_ERROR);
    }

    @Test(groups = {"MC:Authentication", "Abdul Rehman", "SECTION:Source Card Brand Validation", "SC:Source Card Brand"}, description = "Validates transaction authentication with no source card brand", priority = 161)
    public void transactionAuthenticationNoSourceCardBrandCase() {

        TransactionRequest transactionRequest = AppUtils.transactionRequestInstance();

        String body = CommonAutomationUtils.stringToJson(transactionRequest);
        String updatedBody = CommonAutomationUtils.modifyJson(body, "DELETE", AppConstants.SOURCE + "." + AppConstants.CARD + "." + AppConstants.BRAND, null);
        Response response = AuthenticationTransactionRequest.postRequest(updatedBody, AppConstants.INTERNAL_END_POINT);
        String textResponse = response.getBody().asString();
        JsonNode jsonResponse = CommonAutomationUtils.convertToJson(textResponse);

        CommonAutomationUtils.verifyCommonResponseFailedValidation(jsonResponse, response.getStatusCode(), HttpStatus.SC_BAD_REQUEST, AppConstants.REQUIRED_FIELD_INVALID_CODE, AppConstants.REQUIRED_FIELD_INVALID_ERROR);
    }
    @Test(groups = {"MC:Authentication", "Abdul Rehman", "SECTION:Source Card Category Validation", "SC:Source Card Category"}, description = "Validates transaction authentication with a valid source card category", priority = 162)
    public void transactionAuthenticationValidSourceCardCategoryCase() {

        TransactionRequest transactionRequest = AppUtils.transactionRequestInstance();
        transactionRequest.getSource().getCard().setCategory("CLASSIC");

        String body = CommonAutomationUtils.stringToJson(transactionRequest);
        Response response = AuthenticationTransactionRequest.postRequest(body, AppConstants.INTERNAL_END_POINT);

        CommonAutomationUtils.verifyCommonResponseSuccessValidation(response, HttpStatus.SC_OK, Map.of(AppConstants.CARD + "." + AppConstants.CATEGORY, "CLASSIC"));
    }

    @Test(groups = {"MC:Authentication", "Abdul Rehman", "SECTION:Source Card Category Validation", "SC:Source Card Category"}, description = "Validates transaction authentication with an invalid source card category", priority = 163)
    public void transactionAuthenticationInvalidSourceCardCategoryCase() {

        TransactionRequest transactionRequest = AppUtils.transactionRequestInstance();
        transactionRequest.getSource().getCard().setCategory("invalid_category");

        String body = CommonAutomationUtils.stringToJson(transactionRequest);
        Response response = AuthenticationTransactionRequest.postRequest(body, AppConstants.INTERNAL_END_POINT);

        CommonAutomationUtils.verifyCommonResponseSuccessValidation(response, HttpStatus.SC_OK, Map.of(AppConstants.CARD + "." + AppConstants.CATEGORY, "invalid_category"));
    }

    @Test(groups = {"MC:Authentication", "Abdul Rehman", "SECTION:Source Card Category Validation", "SC:Source Card Category"}, description = "Validates transaction authentication with a null source card category", priority = 164)
    public void transactionAuthenticationNullSourceCardCategoryCase() {

        TransactionRequest transactionRequest = AppUtils.transactionRequestInstance();
        transactionRequest.getSource().getCard().setCategory(null);

        String body = CommonAutomationUtils.stringToJson(transactionRequest);
        Response response = AuthenticationTransactionRequest.postRequest(body, AppConstants.INTERNAL_END_POINT);

        CommonAutomationUtils.verifyCommonResponseSuccessValidation(response, HttpStatus.SC_OK);
        CommonAutomationUtils.verifyNonAvailableKey(response, List.of(AppConstants.SOURCE + "." + AppConstants.CARD + "." + AppConstants.CATEGORY));
    }

    @Test(groups = {"MC:Authentication", "Abdul Rehman", "SECTION:Source Card Category Validation", "SC:Source Card Category"}, description = "Validates transaction authentication with an empty source card category", priority = 165)
    public void transactionAuthenticationEmptySourceCardCategoryCase() {

        TransactionRequest transactionRequest = AppUtils.transactionRequestInstance();
        transactionRequest.getSource().getCard().setCategory("");

        String body = CommonAutomationUtils.stringToJson(transactionRequest);
        Response response = AuthenticationTransactionRequest.postRequest(body, AppConstants.INTERNAL_END_POINT);

        CommonAutomationUtils.verifyCommonResponseSuccessValidation(response, HttpStatus.SC_OK, Map.of(AppConstants.CARD + "." + AppConstants.CATEGORY, ""));
    }

    @Test(groups = {"MC:Authentication", "Abdul Rehman", "SECTION:Source Card Category Validation", "SC:Source Card Category"}, description = "Validates transaction authentication with no source card category", priority = 166)
    public void transactionAuthenticationNoSourceCardCategoryCase() {

        TransactionRequest transactionRequest = AppUtils.transactionRequestInstance();

        String body = CommonAutomationUtils.stringToJson(transactionRequest);
        String updatedBody = CommonAutomationUtils.modifyJson(body, "DELETE", AppConstants.SOURCE + "." + AppConstants.CARD + "." + AppConstants.CATEGORY, null);
        Response response = AuthenticationTransactionRequest.postRequest(updatedBody, AppConstants.INTERNAL_END_POINT);

        CommonAutomationUtils.verifyCommonResponseSuccessValidation(response, HttpStatus.SC_OK);
    }

    @Test(groups = {"MC:Authentication", "Abdul Rehman", "SECTION:Source Card Last Four Validation", "SC:Source Card Last Four"}, description = "Validates transaction authentication with a valid source card last four digits", priority = 167)
    public void transactionAuthenticationValidSourceCardLastFourCase() {

        TransactionRequest transactionRequest = AppUtils.transactionRequestInstance();
        transactionRequest.getSource().getCard().setLastFour("SALWA HEDAR");

        String body = CommonAutomationUtils.stringToJson(transactionRequest);
        Response response = AuthenticationTransactionRequest.postRequest(body, AppConstants.INTERNAL_END_POINT);

        CommonAutomationUtils.verifyCommonResponseSuccessValidation(response, HttpStatus.SC_OK, Map.of(AppConstants.CARD + "." + AppConstants.LAST_FOUR, "SALWA HEDAR"));
    }

    @Test(groups = {"MC:Authentication", "Abdul Rehman", "SECTION:Source Card Last Four Validation", "SC:Source Card Last Four"}, description = "Validates transaction authentication with an invalid source card last four digits", priority = 168)
    public void transactionAuthenticationInvalidSourceCardLastFourCase() {

        TransactionRequest transactionRequest = AppUtils.transactionRequestInstance();
        transactionRequest.getSource().getCard().setLastFour("ABC");

        String body = CommonAutomationUtils.stringToJson(transactionRequest);
        Response response = AuthenticationTransactionRequest.postRequest(body, AppConstants.INTERNAL_END_POINT);

        CommonAutomationUtils.verifyCommonResponseSuccessValidation(response, HttpStatus.SC_OK, Map.of(AppConstants.CARD + "." + AppConstants.LAST_FOUR, "ABC"));
    }

    @Test(groups = {"MC:Authentication", "Abdul Rehman", "SECTION:Source Card Last Four Validation", "SC:Source Card Last Four"}, description = "Validates transaction authentication with a null source card last four digits", priority = 169)
    public void transactionAuthenticationNullSourceCardLastFourCase() {

        TransactionRequest transactionRequest = AppUtils.transactionRequestInstance();
        transactionRequest.getSource().getCard().setLastFour(null);

        String body = CommonAutomationUtils.stringToJson(transactionRequest);
        Response response = AuthenticationTransactionRequest.postRequest(body, AppConstants.INTERNAL_END_POINT);

        CommonAutomationUtils.verifyCommonResponseSuccessValidation(response, HttpStatus.SC_OK);
        CommonAutomationUtils.verifyNonAvailableKey(response, List.of(AppConstants.SOURCE + "." + AppConstants.CARD + "." + AppConstants.LAST_FOUR));
    }

    @Test(groups = {"MC:Authentication", "Abdul Rehman", "SECTION:Source Card Last Four Validation", "SC:Source Card Last Four"}, description = "Validates transaction authentication with an empty source card last four digits", priority = 170)
    public void transactionAuthenticationEmptySourceCardLastFourCase() {

        TransactionRequest transactionRequest = AppUtils.transactionRequestInstance();
        transactionRequest.getSource().getCard().setLastFour("");

        String body = CommonAutomationUtils.stringToJson(transactionRequest);
        Response response = AuthenticationTransactionRequest.postRequest(body, AppConstants.INTERNAL_END_POINT);

        CommonAutomationUtils.verifyCommonResponseSuccessValidation(response, HttpStatus.SC_OK, Map.of(AppConstants.CARD + "." + AppConstants.LAST_FOUR, ""));
    }

    @Test(groups = {"MC:Authentication", "Abdul Rehman", "SECTION:Source Card Last Four Validation", "SC:Source Card Last Four"}, description = "Validates transaction authentication with no source card last four digits", priority = 171)
    public void transactionAuthenticationNoSourceCardLastFourCase() {

        TransactionRequest transactionRequest = AppUtils.transactionRequestInstance();

        String body = CommonAutomationUtils.stringToJson(transactionRequest);
        String updatedBody = CommonAutomationUtils.modifyJson(body, "DELETE", AppConstants.SOURCE + "." + AppConstants.CARD + "." + AppConstants.LAST_FOUR, null);
        Response response = AuthenticationTransactionRequest.postRequest(updatedBody, AppConstants.INTERNAL_END_POINT);

        CommonAutomationUtils.verifyCommonResponseSuccessValidation(response, HttpStatus.SC_OK);
    }

    @Test(groups = {"MC:Authentication", "Abdul Rehman", "SECTION:Source Card Name Validation", "SC:Source Card Name"}, description = "Validates transaction authentication with a valid source card name", priority = 172)
    public void transactionAuthenticationValidSourceCardNameCase() {

        TransactionRequest transactionRequest = AppUtils.transactionRequestInstance();
        transactionRequest.getSource().getCard().setName("SALWA HEDAR");

        String body = CommonAutomationUtils.stringToJson(transactionRequest);
        Response response = AuthenticationTransactionRequest.postRequest(body, AppConstants.INTERNAL_END_POINT);

        CommonAutomationUtils.verifyCommonResponseSuccessValidation(response, HttpStatus.SC_OK, Map.of(AppConstants.CARD + "." + AppConstants.NAME, "SALWA HEDAR"));
    }

    @Test(groups = {"MC:Authentication", "Abdul Rehman", "SECTION:Source Card Name Validation", "SC:Source Card Name"}, description = "Validates transaction authentication with a null source card name", priority = 173)
    public void transactionAuthenticationNullSourceCardNameCase() {

        TransactionRequest transactionRequest = AppUtils.transactionRequestInstance();
        transactionRequest.getSource().getCard().setName(null);

        String body = CommonAutomationUtils.stringToJson(transactionRequest);
        Response response = AuthenticationTransactionRequest.postRequest(body, AppConstants.INTERNAL_END_POINT);

        CommonAutomationUtils.verifyCommonResponseSuccessValidation(response, HttpStatus.SC_OK);
        CommonAutomationUtils.verifyNonAvailableKey(response, List.of(AppConstants.SOURCE + "." + AppConstants.CARD + "." + AppConstants.NAME));
    }

    @Test(groups = {"MC:Authentication", "Abdul Rehman", "SECTION:Source Card Name Validation", "SC:Source Card Name"}, description = "Validates transaction authentication with an empty source card name", priority = 174)
    public void transactionAuthenticationEmptySourceCardNameCase() {

        TransactionRequest transactionRequest = AppUtils.transactionRequestInstance();
        transactionRequest.getSource().getCard().setName("");

        String body = CommonAutomationUtils.stringToJson(transactionRequest);
        Response response = AuthenticationTransactionRequest.postRequest(body, AppConstants.INTERNAL_END_POINT);

        CommonAutomationUtils.verifyCommonResponseSuccessValidation(response, HttpStatus.SC_OK, Map.of(AppConstants.CARD + "." + AppConstants.NAME, ""));
    }

    @Test(groups = {"MC:Authentication", "Abdul Rehman", "SECTION:Source Card Name Validation", "SC:Source Card Name"}, description = "Validates transaction authentication with no source card name", priority = 175)
    public void transactionAuthenticationNoSourceCardNameCase() {

        TransactionRequest transactionRequest = AppUtils.transactionRequestInstance();

        String body = CommonAutomationUtils.stringToJson(transactionRequest);
        String updatedBody = CommonAutomationUtils.modifyJson(body, "DELETE", AppConstants.SOURCE + "." + AppConstants.CARD + "." + AppConstants.NAME, null);
        Response response = AuthenticationTransactionRequest.postRequest(updatedBody, AppConstants.INTERNAL_END_POINT);

        CommonAutomationUtils.verifyCommonResponseSuccessValidation(response, HttpStatus.SC_OK);
    }

    @Test(groups = {"MC:Authentication", "Abdul Rehman", "SECTION:Source Card Expiry Validation", "SC:Source Card Expiry"}, description = "Validates transaction authentication with a null source card expiry", priority = 176)
    public void transactionAuthenticationNullSourceCardExpiryCase() {

        TransactionRequest transactionRequest = AppUtils.transactionRequestInstance();
        transactionRequest.getSource().getCard().setExpiry(null);

        String body = CommonAutomationUtils.stringToJson(transactionRequest);
        Response response = AuthenticationTransactionRequest.postRequest(body, AppConstants.INTERNAL_END_POINT);
        String textResponse = response.getBody().asString();
        JsonNode jsonResponse = CommonAutomationUtils.convertToJson(textResponse);

        CommonAutomationUtils.verifyCommonResponseFailedValidation(jsonResponse, response.getStatusCode(), HttpStatus.SC_BAD_REQUEST, AppConstants.REQUIRED_FIELD_INVALID_CODE, AppConstants.REQUIRED_FIELD_INVALID_ERROR);
    }

    @Test(groups = {"MC:Authentication", "Abdul Rehman", "SECTION:Source Card Expiry Validation", "SC:Source Card Expiry"}, description = "Validates transaction authentication with a valid month and year in source card expiry", priority = 177)
    public void transactionAuthenticationSourceCardExpiryValidMonthValidYearCase() {

        TransactionRequest transactionRequest = AppUtils.transactionRequestInstance();
        TransactionRequest.Source.Card.Expiry expiry = transactionRequest.getSource().getCard().getExpiry();
        expiry.setMonth("12");
        expiry.setYear("24");

        String body = CommonAutomationUtils.stringToJson(transactionRequest);
        Response response = AuthenticationTransactionRequest.postRequest(body, AppConstants.INTERNAL_END_POINT);

        CommonAutomationUtils.verifyCommonResponseSuccessValidation(response, HttpStatus.SC_OK, Map.of(AppConstants.CARD + "." + AppConstants.EXPIRY + "." + AppConstants.MONTH, "12", AppConstants.CARD + "." + AppConstants.EXPIRY + "." + AppConstants.YEAR, "24"));
    }

    @Test(groups = {"MC:Authentication", "Abdul Rehman", "SECTION:Source Card Expiry Validation", "SC:Source Card Expiry"}, description = "Validates transaction authentication with a valid month and invalid year in source card expiry", priority = 178)
    public void transactionAuthenticationSourceCardExpiryValidMonthInvalidYearCase() {

        TransactionRequest transactionRequest = AppUtils.transactionRequestInstance();
        TransactionRequest.Source.Card.Expiry expiry = transactionRequest.getSource().getCard().getExpiry();
        expiry.setMonth("12");
        expiry.setYear("00");

        String body = CommonAutomationUtils.stringToJson(transactionRequest);
        Response response = AuthenticationTransactionRequest.postRequest(body, AppConstants.INTERNAL_END_POINT);

        CommonAutomationUtils.verifyCommonResponseSuccessValidation(response, HttpStatus.SC_OK, Map.of(AppConstants.CARD + "." + AppConstants.EXPIRY + "." + AppConstants.MONTH, "12", AppConstants.CARD + "." + AppConstants.EXPIRY + "." + AppConstants.YEAR, "00"));
    }

    @Test(groups = {"MC:Authentication", "Abdul Rehman", "SECTION:Source Card Expiry Validation", "SC:Source Card Expiry"}, description = "Validates transaction authentication with an invalid month and valid year in source card expiry", priority = 179)
    public void transactionAuthenticationSourceCardExpiryInValidMonthInvalidYearCase() {

        TransactionRequest transactionRequest = AppUtils.transactionRequestInstance();
        TransactionRequest.Source.Card.Expiry expiry = transactionRequest.getSource().getCard().getExpiry();
        expiry.setMonth("00");
        expiry.setYear("24");

        String body = CommonAutomationUtils.stringToJson(transactionRequest);
        Response response = AuthenticationTransactionRequest.postRequest(body, AppConstants.INTERNAL_END_POINT);

        CommonAutomationUtils.verifyCommonResponseSuccessValidation(response, HttpStatus.SC_OK, Map.of(AppConstants.CARD + "." + AppConstants.EXPIRY + "." + AppConstants.MONTH, "00", AppConstants.CARD + "." + AppConstants.EXPIRY + "." + AppConstants.YEAR, "24"));
    }

    @Test(groups = {"MC:Authentication", "Abdul Rehman", "SECTION:Source Card Expiry Validation", "SC:Source Card Expiry"}, description = "Validates transaction authentication with a non-numeric month and year in source card expiry", priority = 180)
    public void transactionAuthenticationSourceNonNumericCardExpiryCase() {

        TransactionRequest transactionRequest = AppUtils.transactionRequestInstance();
        TransactionRequest.Source.Card.Expiry expiry = transactionRequest.getSource().getCard().getExpiry();
        expiry.setMonth("aa");
        expiry.setYear("bb");

        String body = CommonAutomationUtils.stringToJson(transactionRequest);
        Response response = AuthenticationTransactionRequest.postRequest(body, AppConstants.INTERNAL_END_POINT);

        CommonAutomationUtils.verifyCommonResponseSuccessValidation(response, HttpStatus.SC_OK, Map.of(AppConstants.CARD + "." + AppConstants.EXPIRY + "." + AppConstants.MONTH, "aa", AppConstants.CARD + "." + AppConstants.EXPIRY + "." + AppConstants.YEAR, "bb"));
    }

    @Test(groups = {"MC:Authentication", "Abdul Rehman", "SECTION:Source Card Expiry Validation", "SC:Source Card Expiry"}, description = "Validates transaction authentication with null values in source card expiry", priority = 181)
    public void transactionAuthenticationSourceNullCardExpiryCase() {

        TransactionRequest transactionRequest = AppUtils.transactionRequestInstance();
        TransactionRequest.Source.Card.Expiry expiry = transactionRequest.getSource().getCard().getExpiry();
        expiry.setMonth(null);
        expiry.setYear(null);

        String body = CommonAutomationUtils.stringToJson(transactionRequest);
        Response response = AuthenticationTransactionRequest.postRequest(body, AppConstants.INTERNAL_END_POINT);
        String textResponse = response.getBody().asString();
        JsonNode jsonResponse = CommonAutomationUtils.convertToJson(textResponse);

        CommonAutomationUtils.verifyCommonResponseFailedValidation(jsonResponse, response.getStatusCode(), HttpStatus.SC_BAD_REQUEST, AppConstants.REQUIRED_FIELD_INVALID_CODE, AppConstants.REQUIRED_FIELD_INVALID_ERROR);
    }

    @Test(groups = {"MC:Authentication", "Abdul Rehman", "SECTION:Source Card Funding Validation", "SC:Source Card Funding"}, description = "Validates transaction authentication with a valid source card funding type", priority = 182)
    public void transactionAuthenticationValidSourceCardFundingCase() {

        TransactionRequest transactionRequest = AppUtils.transactionRequestInstance();
        transactionRequest.getSource().getCard().setFunding("CREDIT");

        String body = CommonAutomationUtils.stringToJson(transactionRequest);
        Response response = AuthenticationTransactionRequest.postRequest(body, AppConstants.INTERNAL_END_POINT);

        CommonAutomationUtils.verifyCommonResponseSuccessValidation(response, HttpStatus.SC_OK, Map.of(AppConstants.CARD + "." + AppConstants.FUNDING, "CREDIT"));
    }

    @Test(groups = {"MC:Authentication", "Abdul Rehman", "SECTION:Source Card Funding Validation", "SC:Source Card Funding"}, description = "Validates transaction authentication with an invalid source card funding type", priority = 183)
    public void transactionAuthenticationInvalidSourceCardFundingCase() {

        TransactionRequest transactionRequest = AppUtils.transactionRequestInstance();
        transactionRequest.getSource().getCard().setFunding("invalid_funding");

        String body = CommonAutomationUtils.stringToJson(transactionRequest);
        Response response = AuthenticationTransactionRequest.postRequest(body, AppConstants.INTERNAL_END_POINT);

        CommonAutomationUtils.verifyCommonResponseSuccessValidation(response, HttpStatus.SC_OK, Map.of(AppConstants.CARD + "." + AppConstants.FUNDING, "invalid_funding"));
    }

    @Test(groups = {"MC:Authentication", "Abdul Rehman", "SECTION:Source Card Funding Validation", "SC:Source Card Funding"}, description = "Validates transaction authentication with a null source card funding type", priority = 184)
    public void transactionAuthenticationNullSourceCardFundingCase() {

        TransactionRequest transactionRequest = AppUtils.transactionRequestInstance();
        transactionRequest.getSource().getCard().setFunding(null);

        String body = CommonAutomationUtils.stringToJson(transactionRequest);
        Response response = AuthenticationTransactionRequest.postRequest(body, AppConstants.INTERNAL_END_POINT);

        CommonAutomationUtils.verifyCommonResponseSuccessValidation(response, HttpStatus.SC_OK);
        CommonAutomationUtils.verifyNonAvailableKey(response, List.of(AppConstants.SOURCE + "." + AppConstants.CARD + "." + AppConstants.FUNDING));
    }

    @Test(groups = {"MC:Authentication", "Abdul Rehman", "SECTION:Source Card Funding Validation", "SC:Source Card Funding"}, description = "Validates transaction authentication with an empty source card funding type", priority = 185)
    public void transactionAuthenticationEmptySourceCardFundingCase() {

        TransactionRequest transactionRequest = AppUtils.transactionRequestInstance();
        transactionRequest.getSource().getCard().setFunding("");

        String body = CommonAutomationUtils.stringToJson(transactionRequest);
        Response response = AuthenticationTransactionRequest.postRequest(body, AppConstants.INTERNAL_END_POINT);

        CommonAutomationUtils.verifyCommonResponseSuccessValidation(response, HttpStatus.SC_OK, Map.of(AppConstants.CARD + "." + AppConstants.FUNDING, ""));
    }

    @Test(groups = {"MC:Authentication", "Abdul Rehman", "SECTION:Source Card Funding Validation", "SC:Source Card Funding"}, description = "Validates transaction authentication with no source card funding type", priority = 186)
    public void transactionAuthenticationNoSourceCardFundingCase() {

        TransactionRequest transactionRequest = AppUtils.transactionRequestInstance();

        String body = CommonAutomationUtils.stringToJson(transactionRequest);
        String updatedBody = CommonAutomationUtils.modifyJson(body, "DELETE", AppConstants.SOURCE + "." + AppConstants.CARD + "." + AppConstants.FUNDING, null);
        Response response = AuthenticationTransactionRequest.postRequest(updatedBody, AppConstants.INTERNAL_END_POINT);

        CommonAutomationUtils.verifyCommonResponseSuccessValidation(response, HttpStatus.SC_OK);
    }

    @Test(groups = {"MC:Authentication", "Abdul Rehman", "SECTION:Routing Validation", "SC:Routing"}, description = "Validates transaction authentication with a null routing provider", priority = 187)
    public void transactionAuthenticationNullRoutingCase() {

        TransactionRequest transactionRequest = AppUtils.transactionRequestInstance();
        transactionRequest.setRouting(null);

        String body = CommonAutomationUtils.stringToJson(transactionRequest);
        Response response = AuthenticationTransactionRequest.postRequest(body, AppConstants.INTERNAL_END_POINT);
        String textResponse = response.getBody().asString();
        JsonNode jsonResponse = CommonAutomationUtils.convertToJson(textResponse);

        CommonAutomationUtils.verifyCommonResponseFailedValidation(jsonResponse, response.getStatusCode(), HttpStatus.SC_BAD_REQUEST, AppConstants.ROUTING_DETAIL_NOT_FOUND_CODE, AppConstants.ROUTING_DETAIL_NOT_FOUND_ERROR);
    }

    @Test(groups = {"MC:Authentication", "Abdul Rehman", "SECTION:Routing Provider Validation", "SC:Routing Provider"}, description = "Validates transaction authentication with a valid routing provider", priority = 188)
    public void transactionAuthenticationValidRoutingProviderCase() {

        TransactionRequest transactionRequest = AppUtils.transactionRequestInstance();
        transactionRequest.getRouting().setProvider("MPGS");

        String body = CommonAutomationUtils.stringToJson(transactionRequest);
        Response response = AuthenticationTransactionRequest.postRequest(body, AppConstants.INTERNAL_END_POINT);

        CommonAutomationUtils.verifyCommonResponseSuccessValidation(response, HttpStatus.SC_OK, Map.of(AppConstants.ROUTING + "." + AppConstants.PROVIDER, "MPGS"));
    }

    @Test(groups = {"MC:Authentication", "Abdul Rehman", "SECTION:Routing Provider Validation", "SC:Routing Provider"}, description = "Validates transaction authentication with an invalid routing provider", priority = 189)
    public void transactionAuthenticationInvalidRoutingProviderCase() {

        TransactionRequest transactionRequest = AppUtils.transactionRequestInstance();
        transactionRequest.getRouting().setProvider("invalid_provider");

        String body = CommonAutomationUtils.stringToJson(transactionRequest);
        Response response = AuthenticationTransactionRequest.postRequest(body, AppConstants.INTERNAL_END_POINT);
        String textResponse = response.getBody().asString();
        JsonNode jsonResponse = CommonAutomationUtils.convertToJson(textResponse);

        CommonAutomationUtils.verifyCommonResponseFailedValidation(jsonResponse, response.getStatusCode(), HttpStatus.SC_BAD_REQUEST, AppConstants.REQUIRED_INPUT_INVALID_CODE, AppConstants.REQUIRED_INPUT_INVALID_ERROR);
    }

    @Test(groups = {"MC:Authentication", "Abdul Rehman", "SECTION:Routing Provider Validation", "SC:Routing Provider"}, description = "Validates transaction authentication with a numeric routing provider", priority = 190)
    public void transactionAuthenticationNumericRoutingProviderCase() {

        TransactionRequest transactionRequest = AppUtils.transactionRequestInstance();

        String body = CommonAutomationUtils.stringToJson(transactionRequest);
        String updatedBody = CommonAutomationUtils.modifyJson(body, "MODIFY", AppConstants.ROUTING + "." + AppConstants.PROVIDER, 12345);
        Response response = AuthenticationTransactionRequest.postRequest(updatedBody, AppConstants.INTERNAL_END_POINT);
        String textResponse = response.getBody().asString();
        JsonNode jsonResponse = CommonAutomationUtils.convertToJson(textResponse);

        CommonAutomationUtils.verifyCommonResponseFailedValidation(jsonResponse, response.getStatusCode(), HttpStatus.SC_BAD_REQUEST, AppConstants.REQUIRED_INPUT_INVALID_CODE, AppConstants.REQUIRED_INPUT_INVALID_ERROR);
    }

    @Test(groups = {"MC:Authentication", "Abdul Rehman", "SECTION:Routing Provider Validation", "SC:Routing Provider"}, description = "Validates transaction authentication with leading space in routing provider", priority = 191)
    public void transactionAuthenticationLeadingSpaceRoutingProviderCase() {

        TransactionRequest transactionRequest = AppUtils.transactionRequestInstance();
        transactionRequest.getRouting().setProvider("  MPGS  ");

        String body = CommonAutomationUtils.stringToJson(transactionRequest);
        Response response = AuthenticationTransactionRequest.postRequest(body, AppConstants.INTERNAL_END_POINT);
        String textResponse = response.getBody().asString();
        JsonNode jsonResponse = CommonAutomationUtils.convertToJson(textResponse);

        CommonAutomationUtils.verifyCommonResponseFailedValidation(jsonResponse, response.getStatusCode(), HttpStatus.SC_BAD_REQUEST, AppConstants.REQUIRED_INPUT_INVALID_CODE, AppConstants.REQUIRED_INPUT_INVALID_ERROR);
    }

    @Test(groups = {"MC:Authentication", "Abdul Rehman", "SECTION:Routing Provider Validation", "SC:Routing Provider"}, description = "Validates transaction authentication with an empty routing provider", priority = 192)
    public void transactionAuthenticationEmptyRoutingProviderCase() {

        TransactionRequest transactionRequest = AppUtils.transactionRequestInstance();
        transactionRequest.getRouting().setProvider("");

        String body = CommonAutomationUtils.stringToJson(transactionRequest);
        Response response = AuthenticationTransactionRequest.postRequest(body, AppConstants.INTERNAL_END_POINT);
        String textResponse = response.getBody().asString();
        JsonNode jsonResponse = CommonAutomationUtils.convertToJson(textResponse);

        CommonAutomationUtils.verifyCommonResponseFailedValidation(jsonResponse, response.getStatusCode(), HttpStatus.SC_BAD_REQUEST, AppConstants.ROUTING_DETAIL_NOT_FOUND_CODE, AppConstants.ROUTING_DETAIL_NOT_FOUND_ERROR);
    }

    @Test(groups = {"MC:Authentication", "Abdul Rehman", "SECTION:Routing Provider Validation", "SC:Routing Provider"}, description = "Validates transaction authentication with no routing provider", priority = 193)
    public void transactionAuthenticationNoRoutingProviderCase() {

        TransactionRequest transactionRequest = AppUtils.transactionRequestInstance();

        String body = CommonAutomationUtils.stringToJson(transactionRequest);
        String updatedBody = CommonAutomationUtils.modifyJson(body, "DELETE", AppConstants.ROUTING + "." + AppConstants.PROVIDER, null);
        Response response = AuthenticationTransactionRequest.postRequest(updatedBody, AppConstants.INTERNAL_END_POINT);
        String textResponse = response.getBody().asString();
        JsonNode jsonResponse = CommonAutomationUtils.convertToJson(textResponse);

        CommonAutomationUtils.verifyCommonResponseFailedValidation(jsonResponse, response.getStatusCode(), HttpStatus.SC_BAD_REQUEST, AppConstants.ROUTING_DETAIL_NOT_FOUND_CODE, AppConstants.ROUTING_DETAIL_NOT_FOUND_ERROR);
    }

    @Test(groups = {"MC:Authentication", "Abdul Rehman", "SECTION:Routing Provider Validation", "SC:Routing Provider"}, description = "Validates transaction authentication with a null routing provider", priority = 194)
    public void transactionAuthenticationNullRoutingProviderCase() {

        TransactionRequest transactionRequest = AppUtils.transactionRequestInstance();
        transactionRequest.getRouting().setProvider(null);

        String body = CommonAutomationUtils.stringToJson(transactionRequest);
        Response response = AuthenticationTransactionRequest.postRequest(body, AppConstants.INTERNAL_END_POINT);
        String textResponse = response.getBody().asString();
        JsonNode jsonResponse = CommonAutomationUtils.convertToJson(textResponse);

        CommonAutomationUtils.verifyCommonResponseFailedValidation(jsonResponse, response.getStatusCode(), HttpStatus.SC_BAD_REQUEST, AppConstants.ROUTING_DETAIL_NOT_FOUND_CODE, AppConstants.ROUTING_DETAIL_NOT_FOUND_ERROR);
    }

    @Test(groups = {"MC:Authentication", "Abdul Rehman", "SECTION:Routing Terminal Id Validation", "SC:Routing Terminal Id"}, description = "Validates transaction authentication with a valid routing terminal ID", priority = 195)
    public void transactionAuthenticationValidRoutingTerminalIdCase() {

        TransactionRequest transactionRequest = AppUtils.transactionRequestInstance();
        transactionRequest.getRouting().setTerminalId("ter_p3F64020211320j6XQ1002702");

        String body = CommonAutomationUtils.stringToJson(transactionRequest);
        Response response = AuthenticationTransactionRequest.postRequest(body, AppConstants.INTERNAL_END_POINT);

        CommonAutomationUtils.verifyCommonResponseSuccessValidation(response, HttpStatus.SC_OK, Map.of(AppConstants.ROUTING + "." + AppConstants.TERMINAL_ID, "ter_p3F64020211320j6XQ1002702"));
    }

    @Test(groups = {"MC:Authentication", "Abdul Rehman", "SECTION:Routing Terminal Id Validation", "SC:Routing Terminal Id"}, description = "Validates transaction authentication with an invalid routing terminal ID", priority = 196)
    public void transactionAuthenticationInvalidRoutingTerminalIdCase() {

        TransactionRequest transactionRequest = AppUtils.transactionRequestInstance();
        transactionRequest.getRouting().setTerminalId("invalid_terminal_id");

        String body = CommonAutomationUtils.stringToJson(transactionRequest);
        Response response = AuthenticationTransactionRequest.postRequest(body, AppConstants.INTERNAL_END_POINT);
        String textResponse = response.getBody().asString();
        JsonNode jsonResponse = CommonAutomationUtils.convertToJson(textResponse);

        CommonAutomationUtils.verifyCommonResponseFailedDescriptionValidation(jsonResponse, response.getStatusCode(), HttpStatus.SC_BAD_REQUEST, AppConstants.INVALID_EMPTY_TOKEN_ID_CODE, AppConstants.INVALID_EMPTY_TOKEN_ID_DESCRIPTION);
    }

    @Test(groups = {"MC:Authentication", "Abdul Rehman", "SECTION:Routing Terminal Id Validation", "SC:Routing Terminal Id"}, description = "Validates transaction authentication with a numeric routing terminal ID", priority = 197)
    public void transactionAuthenticationNumericRoutingTerminalIdCase() {

        TransactionRequest transactionRequest = AppUtils.transactionRequestInstance();

        String body = CommonAutomationUtils.stringToJson(transactionRequest);
        String updatedBody = CommonAutomationUtils.modifyJson(body, "MODIFY", AppConstants.ROUTING + "." + AppConstants.TERMINAL_ID, 1234567890);
        Response response = AuthenticationTransactionRequest.postRequest(updatedBody, AppConstants.INTERNAL_END_POINT);
        String textResponse = response.getBody().asString();
        JsonNode jsonResponse = CommonAutomationUtils.convertToJson(textResponse);

        CommonAutomationUtils.verifyCommonResponseFailedDescriptionValidation(jsonResponse, response.getStatusCode(), HttpStatus.SC_BAD_REQUEST, AppConstants.INVALID_EMPTY_TOKEN_ID_CODE, AppConstants.INVALID_EMPTY_TOKEN_ID_DESCRIPTION);
    }

    @Test(groups = {"MC:Authentication", "Abdul Rehman", "SECTION:Routing Terminal Id Validation", "SC:Routing Terminal Id"}, description = "Validates transaction authentication with an empty routing terminal ID", priority = 198)
    public void transactionAuthenticationEmptyRoutingTerminalIdCase() {

        TransactionRequest transactionRequest = AppUtils.transactionRequestInstance();
        transactionRequest.getRouting().setTerminalId("");

        String body = CommonAutomationUtils.stringToJson(transactionRequest);
        Response response = AuthenticationTransactionRequest.postRequest(body, AppConstants.INTERNAL_END_POINT);
        String textResponse = response.getBody().asString();
        JsonNode jsonResponse = CommonAutomationUtils.convertToJson(textResponse);

        CommonAutomationUtils.verifyCommonResponseFailedValidation(jsonResponse, response.getStatusCode(), HttpStatus.SC_BAD_REQUEST, AppConstants.ROUTING_DETAIL_NOT_FOUND_CODE, AppConstants.ROUTING_DETAIL_NOT_FOUND_ERROR);
    }

    @Test(groups = {"MC:Authentication", "Abdul Rehman", "SECTION:Routing Terminal Id Validation", "SC:Routing Terminal Id"}, description = "Validates transaction authentication with no routing terminal ID", priority = 199)
    public void transactionAuthenticationNoRoutingTerminalIdCase() {

        TransactionRequest transactionRequest = AppUtils.transactionRequestInstance();

        String body = CommonAutomationUtils.stringToJson(transactionRequest);
        String updatedBody = CommonAutomationUtils.modifyJson(body, "DELETE", AppConstants.ROUTING + "." + AppConstants.TERMINAL_ID, null);
        Response response = AuthenticationTransactionRequest.postRequest(updatedBody, AppConstants.INTERNAL_END_POINT);
        String textResponse = response.getBody().asString();
        JsonNode jsonResponse = CommonAutomationUtils.convertToJson(textResponse);

        CommonAutomationUtils.verifyCommonResponseFailedValidation(jsonResponse, response.getStatusCode(), HttpStatus.SC_BAD_REQUEST, AppConstants.ROUTING_DETAIL_NOT_FOUND_CODE, AppConstants.ROUTING_DETAIL_NOT_FOUND_ERROR);
    }

    @Test(groups = {"MC:Authentication", "Abdul Rehman", "SECTION:Routing Terminal Id Validation", "SC:Routing Terminal Id"}, description = "Validates transaction authentication with a null routing terminal ID", priority = 200)
    public void transactionAuthenticationNullRoutingTerminalIdCase() {

        TransactionRequest transactionRequest = AppUtils.transactionRequestInstance();
        transactionRequest.getRouting().setTerminalId(null);

        String body = CommonAutomationUtils.stringToJson(transactionRequest);
        Response response = AuthenticationTransactionRequest.postRequest(body, AppConstants.INTERNAL_END_POINT);
        String textResponse = response.getBody().asString();
        JsonNode jsonResponse = CommonAutomationUtils.convertToJson(textResponse);

        CommonAutomationUtils.verifyCommonResponseFailedValidation(jsonResponse, response.getStatusCode(), HttpStatus.SC_BAD_REQUEST, AppConstants.ROUTING_DETAIL_NOT_FOUND_CODE, AppConstants.ROUTING_DETAIL_NOT_FOUND_ERROR);
    }

    @Test(groups = {"MC:Authentication", "Abdul Rehman", "SECTION:Authentication Validation", "SC:Authentication"}, description = "Validates transaction authentication with a null authentication object", priority = 201)
    public void transactionAuthenticationNullAuthenticationCase() {

        TransactionRequest transactionRequest = AppUtils.transactionRequestInstance();
        transactionRequest.setAuthentication(null);

        String body = CommonAutomationUtils.stringToJson(transactionRequest);
        Response response = AuthenticationTransactionRequest.postRequest(body, AppConstants.INTERNAL_END_POINT);
        String textResponse = response.getBody().asString();
        JsonNode jsonResponse = CommonAutomationUtils.convertToJson(textResponse);

        CommonAutomationUtils.verifyCommonResponseFailedValidation(jsonResponse, response.getStatusCode(), HttpStatus.SC_BAD_REQUEST, AppConstants.REQUIRED_FIELD_INVALID_CODE, AppConstants.REQUIRED_FIELD_INVALID_ERROR);
    }

    @Test(groups = {"MC:Authentication", "Abdul Rehman", "SECTION:Authentication Validation", "SC:Authentication"}, description = "Validates transaction authentication with an empty authentication object", priority = 202)
    public void transactionAuthenticationEmptyAuthenticationObjectCase() {

        TransactionRequest transactionRequest = AppUtils.transactionRequestInstance();

        String body = CommonAutomationUtils.stringToJson(transactionRequest);
        String updatedBody = CommonAutomationUtils.modifyJson(body, "DELETE", "authentication.channel", null);
        updatedBody = CommonAutomationUtils.modifyJson(updatedBody, "DELETE", "authentication.purpose", null);
        Response response = AuthenticationTransactionRequest.postRequest(updatedBody, AppConstants.INTERNAL_END_POINT);
        String textResponse = response.getBody().asString();
        JsonNode jsonResponse = CommonAutomationUtils.convertToJson(textResponse);

        CommonAutomationUtils.verifyCommonResponseFailedValidation(jsonResponse, response.getStatusCode(), HttpStatus.SC_BAD_REQUEST, AppConstants.REQUIRED_FIELD_INVALID_CODE, AppConstants.REQUIRED_FIELD_INVALID_ERROR);
    }

    @Test(groups = {"MC:Authentication", "Abdul Rehman", "SECTION:Authentication Validation", "SC:Authentication"}, description = "Validates transaction authentication with an authentication object without channel", priority = 202)
    public void transactionAuthenticationAuthenticationObjectWithoutChannelCase() {

        TransactionRequest transactionRequest = AppUtils.transactionRequestInstance();

        String body = CommonAutomationUtils.stringToJson(transactionRequest);
        String updatedBody = CommonAutomationUtils.modifyJson(body, "DELETE", "authentication.channel", null);
        Response response = AuthenticationTransactionRequest.postRequest(updatedBody, AppConstants.INTERNAL_END_POINT);
        String textResponse = response.getBody().asString();
        JsonNode jsonResponse = CommonAutomationUtils.convertToJson(textResponse);

        CommonAutomationUtils.verifyCommonResponseFailedValidation(jsonResponse, response.getStatusCode(), HttpStatus.SC_BAD_REQUEST, AppConstants.REQUIRED_FIELD_INVALID_CODE, AppConstants.REQUIRED_FIELD_INVALID_ERROR);
    }

    @Test(groups = {"MC:Authentication", "Abdul Rehman", "SECTION:Authentication Validation", "SC:Authentication"}, description = "Validates transaction authentication with an authentication object without purpose", priority = 203)
    public void transactionAuthenticationAuthenticationObjectWithoutPurposeCase() {

        TransactionRequest transactionRequest = AppUtils.transactionRequestInstance();

        String body = CommonAutomationUtils.stringToJson(transactionRequest);
        String updatedBody = CommonAutomationUtils.modifyJson(body, "DELETE", "authentication.purpose", null);
        Response response = AuthenticationTransactionRequest.postRequest(updatedBody, AppConstants.INTERNAL_END_POINT);

        CommonAutomationUtils.verifyCommonResponseSuccessValidation(response, HttpStatus.SC_OK, Map.of(AppConstants.AUTHENTICATION + "." + AppConstants.CHANNEL, AuthenticationChannel.PAYER_BROWSER));
        CommonAutomationUtils.verifyNonEmpty(response, List.of(AppConstants.AUTHENTICATION + "." + AppConstants.URL));
    }

    @Test(groups = {"MC:Authentication", "Abdul Rehman", "SECTION:Authentication Channel Validation", "SC:Authentication Channel"}, description = "Validates transaction authentication with a valid authentication channel", priority = 204)
    public void transactionAuthenticationValidAuthenticationChannelCase() {

        TransactionRequest transactionRequest = AppUtils.transactionRequestInstance();

        String body = CommonAutomationUtils.stringToJson(transactionRequest);
        Response response = AuthenticationTransactionRequest.postRequest(body, AppConstants.INTERNAL_END_POINT);

        CommonAutomationUtils.verifyCommonResponseSuccessValidation(response, HttpStatus.SC_OK, Map.of(AppConstants.AUTHENTICATION + "." + AppConstants.CHANNEL, "PAYER_BROWSER"));
        CommonAutomationUtils.verifyNonEmpty(response, List.of(AppConstants.AUTHENTICATION + "." + AppConstants.URL));
    }

    @Test(groups = {"MC:Authentication", "Abdul Rehman", "SECTION:Authentication Channel Validation", "SC:Authentication Channel"}, description = "Validates transaction authentication with an invalid authentication channel", priority = 205)
    public void transactionAuthenticationInvalidAuthenticationChannelCase() {

        TransactionRequest transactionRequest = AppUtils.transactionRequestInstance();

        String body = CommonAutomationUtils.stringToJson(transactionRequest);
        String updatedBody = CommonAutomationUtils.modifyJson(body, "MODIFY", AppConstants.AUTHENTICATION + "." + AppConstants.CHANNEL, "invalid_authentication_channel");
        Response response = AuthenticationTransactionRequest.postRequest(updatedBody, AppConstants.INTERNAL_END_POINT);
        String textResponse = response.getBody().asString();
        JsonNode jsonResponse = CommonAutomationUtils.convertToJson(textResponse);

        CommonAutomationUtils.verifyCommonResponseFailedValidation(jsonResponse, response.getStatusCode(), HttpStatus.SC_BAD_REQUEST, AppConstants.INVALID_DATA_CODE, AppConstants.INVALID_DATA_ERROR);
    }

    @Test(groups = {"MC:Authentication", "Abdul Rehman", "SECTION:Authentication Channel Validation", "SC:Authentication Channel"}, description = "Validates transaction authentication with a numeric authentication channel", priority = 206)
    public void transactionAuthenticationNumericAuthenticationChannelCase() {

        TransactionRequest transactionRequest = AppUtils.transactionRequestInstance();

        String body = CommonAutomationUtils.stringToJson(transactionRequest);
        String updatedBody = CommonAutomationUtils.modifyJson(body, "MODIFY", AppConstants.AUTHENTICATION + "." + AppConstants.CHANNEL, 123456);
        Response response = AuthenticationTransactionRequest.postRequest(updatedBody, AppConstants.INTERNAL_END_POINT);
        String textResponse = response.getBody().asString();
        JsonNode jsonResponse = CommonAutomationUtils.convertToJson(textResponse);

        CommonAutomationUtils.verifyCommonResponseFailedValidation(jsonResponse, response.getStatusCode(), HttpStatus.SC_BAD_REQUEST, AppConstants.INVALID_DATA_CODE, AppConstants.INVALID_DATA_ERROR);
    }

    @Test(groups = {"MC:Authentication", "Abdul Rehman", "SECTION:Authentication Channel Validation", "SC:Authentication Channel"}, description = "Validates transaction authentication with an empty authentication channel", priority = 207)
    public void transactionAuthenticationEmptyAuthenticationChannelCase() {

        TransactionRequest transactionRequest = AppUtils.transactionRequestInstance();

        String body = CommonAutomationUtils.stringToJson(transactionRequest);
        String updatedBody = CommonAutomationUtils.modifyJson(body, "MODIFY", AppConstants.AUTHENTICATION + "." + AppConstants.CHANNEL, "");
        Response response = AuthenticationTransactionRequest.postRequest(updatedBody, AppConstants.INTERNAL_END_POINT);
        String textResponse = response.getBody().asString();
        JsonNode jsonResponse = CommonAutomationUtils.convertToJson(textResponse);

        CommonAutomationUtils.verifyCommonResponseFailedValidation(jsonResponse, response.getStatusCode(), HttpStatus.SC_BAD_REQUEST, AppConstants.INVALID_DATA_CODE, AppConstants.INVALID_DATA_ERROR);
    }

    @Test(groups = {"MC:Authentication", "Abdul Rehman", "SECTION:Authentication Channel Validation", "SC:Authentication Channel"}, description = "Validates transaction authentication with no authentication channel", priority = 208)
    public void transactionAuthenticationNoAuthenticationChannelCase() {

        TransactionRequest transactionRequest = AppUtils.transactionRequestInstance();

        String body = CommonAutomationUtils.stringToJson(transactionRequest);
        String updatedBody = CommonAutomationUtils.modifyJson(body, "DELETE", AppConstants.AUTHENTICATION + "." + AppConstants.CHANNEL, null);
        Response response = AuthenticationTransactionRequest.postRequest(updatedBody, AppConstants.INTERNAL_END_POINT);
        String textResponse = response.getBody().asString();
        JsonNode jsonResponse = CommonAutomationUtils.convertToJson(textResponse);

        CommonAutomationUtils.verifyCommonResponseFailedValidation(jsonResponse, response.getStatusCode(), HttpStatus.SC_BAD_REQUEST, AppConstants.REQUIRED_FIELD_INVALID_CODE, AppConstants.REQUIRED_FIELD_INVALID_ERROR);
    }

    @Test(groups = {"MC:Authentication", "Abdul Rehman", "SECTION:Authentication Channel Validation", "SC:Authentication Channel"}, description = "Validates transaction authentication with a null authentication channel", priority = 209)
    public void transactionAuthenticationNullAuthenticationChannelCase() {

        TransactionRequest transactionRequest = AppUtils.transactionRequestInstance();
        transactionRequest.getAuthentication().setChannel(null);

        String body = CommonAutomationUtils.stringToJson(transactionRequest);
        Response response = AuthenticationTransactionRequest.postRequest(body, AppConstants.INTERNAL_END_POINT);
        String textResponse = response.getBody().asString();
        JsonNode jsonResponse = CommonAutomationUtils.convertToJson(textResponse);

        CommonAutomationUtils.verifyCommonResponseFailedValidation(jsonResponse, response.getStatusCode(), HttpStatus.SC_BAD_REQUEST, AppConstants.REQUIRED_FIELD_INVALID_CODE, AppConstants.REQUIRED_FIELD_INVALID_ERROR);
    }

    @Test(groups = {"MC:Authentication", "Abdul Rehman", "SECTION:Authentication Purpose Validation", "SC:Authentication Purpose"}, description = "Validates transaction authentication with a null authentication purpose", priority = 210)
    public void transactionAuthenticationNullAuthenticationPurposeCase() {

        TransactionRequest transactionRequest = AppUtils.transactionRequestInstance();
        transactionRequest.getAuthentication().setPurpose(null);

        String body = CommonAutomationUtils.stringToJson(transactionRequest);
        Response response = AuthenticationTransactionRequest.postRequest(body, AppConstants.INTERNAL_END_POINT);

        CommonAutomationUtils.verifyCommonResponseSuccessValidation(response, HttpStatus.SC_OK, Map.of(AppConstants.AUTHENTICATION + "." + AppConstants.CHANNEL, "PAYER_BROWSER", AppConstants.AUTHENTICATION + "." + AppConstants.PURPOSE, "CHARGE"));
        CommonAutomationUtils.verifyNonEmpty(response, List.of(AppConstants.AUTHENTICATION + "." + AppConstants.URL));
    }

    @Test(groups = {"MC:Authentication", "Abdul Rehman", "SECTION:Authentication Purpose Validation", "SC:Authentication Purpose"}, description = "Validates transaction authentication with a valid payment transaction purpose", priority = 211)
    public void transactionAuthenticationValidAuthenticationPurposePaymentTransactionCase() {

        TransactionRequest transactionRequest = AppUtils.transactionRequestInstance();

        String body = CommonAutomationUtils.stringToJson(transactionRequest);
        Response response = AuthenticationTransactionRequest.postRequest(body, AppConstants.INTERNAL_END_POINT);

        CommonAutomationUtils.verifyCommonResponseSuccessValidation(response, HttpStatus.SC_OK, Map.of(AppConstants.AUTHENTICATION + "." + AppConstants.CHANNEL, "PAYER_BROWSER", AppConstants.AUTHENTICATION + "." + AppConstants.PURPOSE, "PAYMENT_TRANSACTION"));
        CommonAutomationUtils.verifyNonEmpty(response, List.of(AppConstants.AUTHENTICATION + "." + AppConstants.URL));
    }

    @Test(groups = {"MC:Authentication", "Abdul Rehman", "SECTION:Authentication Purpose Validation", "SC:Authentication Purpose"}, description = "Validates transaction authentication with a valid charge purpose", priority = 212)
    public void transactionAuthenticationValidAuthenticationPurposeChargeCase() {

        TransactionRequest transactionRequest = AppUtils.transactionRequestInstance();
        transactionRequest.getAuthentication().setPurpose(AuthenticationPurpose.CHARGE);

        String body = CommonAutomationUtils.stringToJson(transactionRequest);
        Response response = AuthenticationTransactionRequest.postRequest(body, AppConstants.INTERNAL_END_POINT);

        CommonAutomationUtils.verifyCommonResponseSuccessValidation(response, HttpStatus.SC_OK, Map.of(AppConstants.AUTHENTICATION + "." + AppConstants.CHANNEL, "PAYER_BROWSER", AppConstants.AUTHENTICATION + "." + AppConstants.PURPOSE, "CHARGE"));
        CommonAutomationUtils.verifyNonEmpty(response, List.of(AppConstants.AUTHENTICATION + "." + AppConstants.URL));
    }

    @Test(groups = {"MC:Authentication", "Abdul Rehman", "SECTION:Authentication Purpose Validation", "SC:Authentication Purpose"}, description = "Validates transaction authentication with a valid authorize purpose", priority = 213)
    public void transactionAuthenticationValidAuthenticationPurposeAuthorizeCase() {

        TransactionRequest transactionRequest = AppUtils.transactionRequestInstance();
        transactionRequest.getAuthentication().setPurpose(AuthenticationPurpose.AUTHORIZE);

        String body = CommonAutomationUtils.stringToJson(transactionRequest);
        Response response = AuthenticationTransactionRequest.postRequest(body, AppConstants.INTERNAL_END_POINT);

        CommonAutomationUtils.verifyCommonResponseSuccessValidation(response, HttpStatus.SC_OK, Map.of(AppConstants.AUTHENTICATION + "." + AppConstants.CHANNEL, "PAYER_BROWSER", AppConstants.AUTHENTICATION + "." + AppConstants.PURPOSE, "AUTHORIZE"));
        CommonAutomationUtils.verifyNonEmpty(response, List.of(AppConstants.AUTHENTICATION + "." + AppConstants.URL));
    }

    @Test(groups = {"MC:Authentication", "Abdul Rehman", "SECTION:Authentication Purpose Validation", "SC:Authentication Purpose"}, description = "Validates transaction authentication with a valid save token purpose", priority = 214)
    public void transactionAuthenticationValidAuthenticationPurposeSaveTokenCase() {

        TransactionRequest transactionRequest = AppUtils.transactionRequestInstance();
        transactionRequest.getAuthentication().setPurpose(AuthenticationPurpose.SAVE_TOKEN);

        String body = CommonAutomationUtils.stringToJson(transactionRequest);
        Response response = AuthenticationTransactionRequest.postRequest(body, AppConstants.INTERNAL_END_POINT);

        CommonAutomationUtils.verifyCommonResponseSuccessValidation(response, HttpStatus.SC_OK, Map.of(AppConstants.AUTHENTICATION + "." + AppConstants.CHANNEL, "PAYER_BROWSER", AppConstants.AUTHENTICATION + "." + AppConstants.PURPOSE, "SAVE_TOKEN"));
        CommonAutomationUtils.verifyNonEmpty(response, List.of(AppConstants.AUTHENTICATION + "." + AppConstants.URL));
    }

    @Test(groups = {"MC:Authentication", "Abdul Rehman", "SECTION:Authentication Purpose Validation", "SC:Authentication Purpose"}, description = "Validates transaction authentication with a valid channel but invalid purpose", priority = 215)
    public void transactionAuthenticationAuthenticationValidChannelInvalidPurposeCase() {

        TransactionRequest transactionRequest = AppUtils.transactionRequestInstance();
        transactionRequest.getAuthentication().setPurpose(AuthenticationPurpose.SAVE_TOKEN);

        String body = CommonAutomationUtils.stringToJson(transactionRequest);
        String updatedBody = CommonAutomationUtils.modifyJson(body, "MODIFY", AppConstants.AUTHENTICATION + "." + AppConstants.PURPOSE, "invalid_authentication_purpose");
        Response response = AuthenticationTransactionRequest.postRequest(updatedBody, AppConstants.INTERNAL_END_POINT);

        CommonAutomationUtils.verifyCommonResponseSuccessValidation(response, HttpStatus.SC_OK, Map.of(AppConstants.AUTHENTICATION + "." + AppConstants.CHANNEL, "PAYER_BROWSER", AppConstants.AUTHENTICATION + "." + AppConstants.PURPOSE, "CHARGE"));
        CommonAutomationUtils.verifyNonEmpty(response, List.of(AppConstants.AUTHENTICATION + "." + AppConstants.URL));
    }

    @Test(groups = {"MC:Authentication", "Abdul Rehman", "SECTION:Merchant Validation", "SC:Merchant"}, description = "Validates transaction authentication with a null merchant object", priority = 216)
    public void transactionAuthenticationNullMerchantCase() {

        TransactionRequest transactionRequest = AppUtils.transactionRequestInstance();
        transactionRequest.setMerchant(null);

        String body = CommonAutomationUtils.stringToJson(transactionRequest);
        Response response = AuthenticationTransactionRequest.postRequest(body, AppConstants.INTERNAL_END_POINT);

        CommonAutomationUtils.verifyCommonResponseSuccessValidation(response, HttpStatus.SC_OK);
        CommonAutomationUtils.verifyNonAvailableKey(response, List.of(AppConstants.MERCHANT));
    }

    @Test(groups = {"MC:Authentication", "Abdul Rehman", "SECTION:Merchant Id Validation", "SC:Merchant Id"}, description = "Validates transaction authentication with a valid merchant ID", priority = 217)
    public void transactionAuthenticationValidMerchantIdCase() {

        UUID uuid = UUID.randomUUID();
        TransactionRequest transactionRequest = AppUtils.transactionRequestInstance();
        transactionRequest.getMerchant().setId("merchant_" + uuid.toString());

        String body = CommonAutomationUtils.stringToJson(transactionRequest);
        Response response = AuthenticationTransactionRequest.postRequest(body, AppConstants.INTERNAL_END_POINT);

        CommonAutomationUtils.verifyCommonResponseSuccessValidation(response, HttpStatus.SC_OK, Map.of(AppConstants.MERCHANT + "." + AppConstants.ID, "merchant_" + uuid.toString()));
    }

    @Test(groups = {"MC:Authentication", "Abdul Rehman", "SECTION:Merchant Id Validation", "SC:Merchant Id"}, description = "Validates transaction authentication with an invalid merchant ID", priority = 218)
    public void transactionAuthenticationinValidMerchantIdCase() {

        TransactionRequest transactionRequest = AppUtils.transactionRequestInstance();
        transactionRequest.getMerchant().setId("invalid_merchant_id");

        String body = CommonAutomationUtils.stringToJson(transactionRequest);
        Response response = AuthenticationTransactionRequest.postRequest(body, AppConstants.INTERNAL_END_POINT);

        CommonAutomationUtils.verifyCommonResponseSuccessValidation(response, HttpStatus.SC_OK, Map.of(AppConstants.MERCHANT + "." + AppConstants.ID, "invalid_merchant_id"));
    }

    @Test(groups = {"MC:Authentication", "Abdul Rehman", "SECTION:Merchant Id Validation", "SC:Merchant Id"}, description = "Validates transaction authentication with an empty merchant ID", priority = 219)
    public void transactionAuthenticationinEmptyMerchantIdCase() {

        TransactionRequest transactionRequest = AppUtils.transactionRequestInstance();
        transactionRequest.getMerchant().setId("");

        String body = CommonAutomationUtils.stringToJson(transactionRequest);
        Response response = AuthenticationTransactionRequest.postRequest(body, AppConstants.INTERNAL_END_POINT);

        CommonAutomationUtils.verifyCommonResponseSuccessValidation(response, HttpStatus.SC_OK, Map.of(AppConstants.MERCHANT + "." + AppConstants.ID, ""));
    }

    @Test(groups = {"MC:Authentication", "Abdul Rehman", "SECTION:Merchant Id Validation", "SC:Merchant Id"}, description = "Validates transaction authentication with a null merchant ID", priority = 220)
    public void transactionAuthenticationinNullMerchantIdCase() {

        TransactionRequest transactionRequest = AppUtils.transactionRequestInstance();
        transactionRequest.getMerchant().setId(null);

        String body = CommonAutomationUtils.stringToJson(transactionRequest);
        Response response = AuthenticationTransactionRequest.postRequest(body, AppConstants.INTERNAL_END_POINT);

        CommonAutomationUtils.verifyCommonResponseSuccessValidation(response, HttpStatus.SC_OK);
        CommonAutomationUtils.verifyNonAvailableKey(response, List.of(AppConstants.MERCHANT + "." + AppConstants.ID));
    }

    @Test(groups = {"MC:Authentication", "Abdul Rehman", "SECTION:Merchant Id Validation", "SC:Merchant Id"}, description = "Validates transaction authentication with no merchant ID", priority = 221)
    public void transactionAuthenticationinNoMerchantIdCase() {

        TransactionRequest transactionRequest = AppUtils.transactionRequestInstance();

        String body = CommonAutomationUtils.stringToJson(transactionRequest);
        String updatedBody = CommonAutomationUtils.modifyJson(body, "DELETE", AppConstants.MERCHANT + "." + AppConstants.ID, null);
        Response response = AuthenticationTransactionRequest.postRequest(updatedBody, AppConstants.INTERNAL_END_POINT);

        CommonAutomationUtils.verifyCommonResponseSuccessValidation(response, HttpStatus.SC_OK);
        CommonAutomationUtils.verifyNonAvailableKey(response, List.of(AppConstants.MERCHANT + "." + AppConstants.ID));
    }

    @Test(groups = {"MC:Authentication", "Abdul Rehman", "SECTION:Metadata Validation", "SC:Metadata"}, description = "Validates transaction authentication with a null metadata object", priority = 222)
    public void transactionAuthenticationNullMetadataCase() {

        TransactionRequest transactionRequest = AppUtils.transactionRequestInstance();
        transactionRequest.setMetadata(null);

        String body = CommonAutomationUtils.stringToJson(transactionRequest);
        Response response = AuthenticationTransactionRequest.postRequest(body, AppConstants.INTERNAL_END_POINT);

        CommonAutomationUtils.verifyCommonResponseSuccessValidation(response, HttpStatus.SC_OK);
        CommonAutomationUtils.verifyNonAvailableKey(response, List.of(AppConstants.META_DATA));
    }

    @Test(groups = {"MC:Authentication", "Abdul Rehman", "SECTION:Metadata Udf1 and Udf2 Validation", "SC:Metadata Udf1 and Udf2"}, description = "Validates transaction authentication with valid metadata UDF1 and UDF2", priority = 223)
    public void transactionAuthenticationValidMetadataUdf1AndUdf2Case() {

        TransactionRequest transactionRequest = AppUtils.transactionRequestInstance();

        String body = CommonAutomationUtils.stringToJson(transactionRequest);
        Response response = AuthenticationTransactionRequest.postRequest(body, AppConstants.INTERNAL_END_POINT);

        CommonAutomationUtils.verifyCommonResponseSuccessValidation(response, HttpStatus.SC_OK, Map.of(AppConstants.META_DATA + "." + AppConstants.UDF1, "test1", AppConstants.META_DATA + "." + AppConstants.UDF2, "test2"));
    }

    @Test(groups = {"MC:Authentication", "Abdul Rehman", "SECTION:Metadata Udf1 and Udf2 Validation", "SC:Metadata Udf1 and Udf2"}, description = "Validates transaction authentication with numeric metadata UDF1 and UDF2", priority = 224)
    public void transactionAuthenticationNumericMetadataUdf1AndUdf2Case() {

        TransactionRequest transactionRequest = AppUtils.transactionRequestInstance();

        String body = CommonAutomationUtils.stringToJson(transactionRequest);
        String updatedBody = CommonAutomationUtils.modifyJson(body, "MODIFY", AppConstants.META_DATA + "." + AppConstants.UDF1, 12345);
        updatedBody = CommonAutomationUtils.modifyJson(updatedBody, "MODIFY", AppConstants.META_DATA + "." + AppConstants.UDF2, 56789);
        Response response = AuthenticationTransactionRequest.postRequest(updatedBody, AppConstants.INTERNAL_END_POINT);

        CommonAutomationUtils.verifyCommonResponseSuccessValidation(response, HttpStatus.SC_OK, Map.of(AppConstants.META_DATA + "." + AppConstants.UDF1, "12345", AppConstants.META_DATA + "." + AppConstants.UDF2, "56789"));
    }

    @Test(groups = {"MC:Authentication", "Abdul Rehman", "SECTION:Metadata Udf1 and Udf2 Validation", "SC:Metadata Udf1 and Udf2"}, description = "Validates transaction authentication with no metadata UDF1 and UDF2", priority = 225)
    public void transactionAuthenticationNoMetadataUdf1AndUdf2Case() {

        TransactionRequest transactionRequest = AppUtils.transactionRequestInstance();

        String body = CommonAutomationUtils.stringToJson(transactionRequest);
        String updatedBody = CommonAutomationUtils.modifyJson(body, "DELETE", AppConstants.META_DATA + "." + AppConstants.UDF1, null);
        updatedBody = CommonAutomationUtils.modifyJson(updatedBody, "DELETE", AppConstants.META_DATA + "." + AppConstants.UDF2, null);
        Response response = AuthenticationTransactionRequest.postRequest(updatedBody, AppConstants.INTERNAL_END_POINT);

        CommonAutomationUtils.verifyCommonResponseSuccessValidation(response, HttpStatus.SC_OK);
        CommonAutomationUtils.verifyNonAvailableKey(response, List.of(AppConstants.META_DATA + "." + AppConstants.UDF1, AppConstants.META_DATA + "." + AppConstants.UDF2));
    }

    @Test(groups = {"MC:Authentication", "Abdul Rehman", "SECTION:Airline Validation", "SC:Airline"}, description = "Validates transaction authentication with a null airline object", priority = 226)
    public void transactionAuthenticationNullAirlineCase() {

        TransactionRequest transactionRequest = AppUtils.transactionRequestInstance();
        transactionRequest.setAirline(null);

        String body = CommonAutomationUtils.stringToJson(transactionRequest);
        Response response = AuthenticationTransactionRequest.postRequest(body, AppConstants.INTERNAL_END_POINT);

        CommonAutomationUtils.verifyCommonResponseSuccessValidation(response, HttpStatus.SC_OK);
        CommonAutomationUtils.verifyNonAvailableKey(response, List.of(AppConstants.AIRLINE));
    }

    @Test(groups = {"MC:Authentication", "Abdul Rehman", "SECTION:Airline Validation", "SC:Airline"}, description = "Validates transaction authentication with a valid airline ID", priority = 227)
    public void transactionAuthenticationValidAirlineIdCase() {

        TransactionRequest transactionRequest = AppUtils.transactionRequestInstance();

        String body = CommonAutomationUtils.stringToJson(transactionRequest);
        Response response = AuthenticationTransactionRequest.postRequest(body, AppConstants.INTERNAL_END_POINT);

        CommonAutomationUtils.verifyCommonResponseSuccessValidation(response, HttpStatus.SC_OK, Map.of(AppConstants.AIRLINE + "." + AppConstants.ID, "test_airline"));
    }

    @Test(groups = {"MC:Authentication", "Abdul Rehman", "SECTION:Airline Validation", "SC:Airline"}, description = "Validates transaction authentication with a numeric airline ID", priority = 228)
    public void transactionAuthenticationNumericAirlineIdCase() {

        TransactionRequest transactionRequest = AppUtils.transactionRequestInstance();

        String body = CommonAutomationUtils.stringToJson(transactionRequest);
        String updatedBody = CommonAutomationUtils.modifyJson(body, "MODIFY", AppConstants.AIRLINE + "." + AppConstants.ID, 12345);
        Response response = AuthenticationTransactionRequest.postRequest(updatedBody, AppConstants.INTERNAL_END_POINT);

        CommonAutomationUtils.verifyCommonResponseSuccessValidation(response, HttpStatus.SC_OK, Map.of(AppConstants.AIRLINE + "." + AppConstants.ID, "12345"));
    }

    @Test(groups = {"MC:Authentication", "Abdul Rehman", "SECTION:Airline Validation", "SC:Airline"}, description = "Validates transaction authentication with no airline ID", priority = 229)
    public void transactionAuthenticationNoAirlineIdCase() {

        TransactionRequest transactionRequest = AppUtils.transactionRequestInstance();

        String body = CommonAutomationUtils.stringToJson(transactionRequest);
        String updatedBody = CommonAutomationUtils.modifyJson(body, "DELETE", AppConstants.AIRLINE + "." + AppConstants.ID, null);
        Response response = AuthenticationTransactionRequest.postRequest(updatedBody, AppConstants.INTERNAL_END_POINT);

        CommonAutomationUtils.verifyCommonResponseSuccessValidation(response, HttpStatus.SC_OK);
        CommonAutomationUtils.verifyNonAvailableKey(response, List.of(AppConstants.AIRLINE + "." + AppConstants.ID));
    }

    @Test(groups = {"MC:Authentication", "Abdul Rehman", "SECTION:Redirect Validation", "SC:Redirect"}, description = "Validates transaction authentication with a null redirect object", priority = 230)
    public void transactionAuthenticationNullRedirectCase() {

        TransactionRequest transactionRequest = AppUtils.transactionRequestInstance();
        transactionRequest.setRedirect(null);

        String body = CommonAutomationUtils.stringToJson(transactionRequest);
        Response response = AuthenticationTransactionRequest.postRequest(body, AppConstants.INTERNAL_END_POINT);
        String textResponse = response.getBody().asString();
        JsonNode jsonResponse = CommonAutomationUtils.convertToJson(textResponse);

        CommonAutomationUtils.verifyCommonResponseFailedValidation(jsonResponse, response.getStatusCode(), HttpStatus.SC_BAD_REQUEST, AppConstants.REQUIRED_FIELD_INVALID_CODE, AppConstants.REQUIRED_FIELD_INVALID_ERROR);
    }

    @Test(groups = {"MC:Authentication", "Abdul Rehman", "SECTION:Redirect Url Validation", "SC:Redirect Url"}, description = "Validates transaction authentication with a valid redirect URL", priority = 231)
    public void transactionAuthenticationValidRedirectUrlCase() {

        TransactionRequest transactionRequest = AppUtils.transactionRequestInstance();

        String body = CommonAutomationUtils.stringToJson(transactionRequest);
        Response response = AuthenticationTransactionRequest.postRequest(body, AppConstants.INTERNAL_END_POINT);

        CommonAutomationUtils.verifyCommonResponseSuccessValidation(response, HttpStatus.SC_OK, Map.of(AppConstants.REDIRECT + "." + AppConstants.URL, "http://yourwebsite.com/redirect_url"));
    }

    @Test(groups = {"MC:Authentication", "Abdul Rehman", "SECTION:Redirect Url Validation", "SC:Redirect Url"}, description = "Validates transaction authentication with an invalid redirect URL", priority = 232)
    public void transactionAuthenticationInvalidRedirectUrlCase() {

        TransactionRequest transactionRequest = AppUtils.transactionRequestInstance();
        transactionRequest.getRedirect().setUrl("http:/");

        String body = CommonAutomationUtils.stringToJson(transactionRequest);
        Response response = AuthenticationTransactionRequest.postRequest(body, AppConstants.INTERNAL_END_POINT);
        String textResponse = response.getBody().asString();
        JsonNode jsonResponse = CommonAutomationUtils.convertToJson(textResponse);

        CommonAutomationUtils.verifyCommonResponseFailedValidation(jsonResponse, response.getStatusCode(), HttpStatus.SC_BAD_REQUEST, AppConstants.INVALID_DATA_CODE, AppConstants.INVALID_MERCHANT_REDIRECT_URL_ERROR);
    }

    @Test(groups = {"MC:Authentication", "Abdul Rehman", "SECTION:Redirect Url Validation", "SC:Redirect Url"}, description = "Validates transaction authentication with no redirect URL", priority = 233)
    public void transactionAuthenticationNoRedirectUrlCase() {

        TransactionRequest transactionRequest = AppUtils.transactionRequestInstance();

        String body = CommonAutomationUtils.stringToJson(transactionRequest);
        String updatedBody = CommonAutomationUtils.modifyJson(body, "DELETE", AppConstants.REDIRECT + "." + AppConstants.URL, null);
        Response response = AuthenticationTransactionRequest.postRequest(updatedBody, AppConstants.INTERNAL_END_POINT);
        String textResponse = response.getBody().asString();
        JsonNode jsonResponse = CommonAutomationUtils.convertToJson(textResponse);

        CommonAutomationUtils.verifyCommonResponseFailedValidation(jsonResponse, response.getStatusCode(), HttpStatus.SC_BAD_REQUEST, AppConstants.REQUIRED_FIELD_INVALID_CODE, AppConstants.REQUIRED_FIELD_INVALID_ERROR);
    }

    @Test(groups = {"MC:Authentication", "Abdul Rehman", "SECTION:Redirect Url Validation", "SC:Redirect Url"}, description = "Validates transaction authentication with an empty redirect URL", priority = 234)
    public void transactionAuthenticationEmptyRedirectUrlCase() {

        TransactionRequest transactionRequest = AppUtils.transactionRequestInstance();
        transactionRequest.getRedirect().setUrl("");

        String body = CommonAutomationUtils.stringToJson(transactionRequest);
        Response response = AuthenticationTransactionRequest.postRequest(body, AppConstants.INTERNAL_END_POINT);
        String textResponse = response.getBody().asString();
        JsonNode jsonResponse = CommonAutomationUtils.convertToJson(textResponse);

        CommonAutomationUtils.verifyCommonResponseFailedValidation(jsonResponse, response.getStatusCode(), HttpStatus.SC_BAD_REQUEST, AppConstants.INVALID_DATA_CODE, AppConstants.INVALID_MERCHANT_REDIRECT_URL_ERROR);
    }

    @Test(groups = {"MC:Authentication", "Abdul Rehman", "SECTION:Redirect Url Validation", "SC:Redirect Url"}, description = "Validates transaction authentication with a null redirect URL", priority = 235)
    public void transactionAuthenticationNullRedirectUrlCase() {

        TransactionRequest transactionRequest = AppUtils.transactionRequestInstance();
        transactionRequest.getRedirect().setUrl(null);

        String body = CommonAutomationUtils.stringToJson(transactionRequest);
        Response response = AuthenticationTransactionRequest.postRequest(body, AppConstants.INTERNAL_END_POINT);
        String textResponse = response.getBody().asString();
        JsonNode jsonResponse = CommonAutomationUtils.convertToJson(textResponse);

        CommonAutomationUtils.verifyCommonResponseFailedValidation(jsonResponse, response.getStatusCode(), HttpStatus.SC_BAD_REQUEST, AppConstants.REQUIRED_FIELD_INVALID_CODE, AppConstants.REQUIRED_FIELD_INVALID_ERROR);
    }

    @Test(groups = {"MC:Authentication", "Abdul Rehman", "SECTION:Post Validation", "SC:Post"}, description = "Validates transaction authentication with a null post object", priority = 236)
    public void transactionAuthenticationNullPostCase() {

        TransactionRequest transactionRequest = AppUtils.transactionRequestInstance();
        transactionRequest.setPost(null);

        String body = CommonAutomationUtils.stringToJson(transactionRequest);
        Response response = AuthenticationTransactionRequest.postRequest(body, AppConstants.INTERNAL_END_POINT);

        CommonAutomationUtils.verifyCommonResponseSuccessValidation(response, HttpStatus.SC_OK);
        CommonAutomationUtils.verifyNonAvailableKey(response, List.of(AppConstants.POST));
    }

    @Test(groups = {"MC:Authentication", "Abdul Rehman", "SECTION:Post Url Validation", "SC:Post Url"}, description = "Validates transaction authentication with a valid post URL", priority = 237)
    public void transactionAuthenticationValidPostUrlCase() {

        TransactionRequest transactionRequest = AppUtils.transactionRequestInstance();

        String body = CommonAutomationUtils.stringToJson(transactionRequest);
        Response response = AuthenticationTransactionRequest.postRequest(body, AppConstants.INTERNAL_END_POINT);

        CommonAutomationUtils.verifyCommonResponseSuccessValidation(response, HttpStatus.SC_OK, Map.of(AppConstants.POST + "." + AppConstants.URL, "http://yourwebsite.com/post_url"));
    }

    @Test(groups = {"MC:Authentication", "Abdul Rehman", "SECTION:Post Url Validation", "SC:Post Url"}, description = "Validates transaction authentication with an invalid post URL", priority = 238)
    public void transactionAuthenticationInvalidPostUrlCase() {

        TransactionRequest transactionRequest = AppUtils.transactionRequestInstance();
        transactionRequest.getPost().setUrl("http:/");

        String body = CommonAutomationUtils.stringToJson(transactionRequest);
        Response response = AuthenticationTransactionRequest.postRequest(body, AppConstants.INTERNAL_END_POINT);

        CommonAutomationUtils.verifyCommonResponseSuccessValidation(response, HttpStatus.SC_OK, Map.of(AppConstants.POST + "." + AppConstants.URL, "http:/"));
    }

    @Test(groups = {"MC:Authentication", "Abdul Rehman", "SECTION:Post Url Validation", "SC:Post Url"}, description = "Validates transaction authentication with no post URL", priority = 239)
    public void transactionAuthenticationNoPostUrlCase() {

        TransactionRequest transactionRequest = AppUtils.transactionRequestInstance();

        String body = CommonAutomationUtils.stringToJson(transactionRequest);
        String updatedBody = CommonAutomationUtils.modifyJson(body, "DELETE", AppConstants.POST + "." + AppConstants.URL, null);
        Response response = AuthenticationTransactionRequest.postRequest(updatedBody, AppConstants.INTERNAL_END_POINT);

        CommonAutomationUtils.verifyCommonResponseSuccessValidation(response, HttpStatus.SC_OK);
        CommonAutomationUtils.verifyNonAvailableKey(response, List.of(AppConstants.POST + "." + AppConstants.URL));
    }

    @Test(groups = {"MC:Authentication", "Abdul Rehman", "SECTION:Post Url Validation", "SC:Post Url"}, description = "Validates transaction authentication with a null post URL", priority = 240)
    public void transactionAuthenticationNullPostUrlCase() {

        TransactionRequest transactionRequest = AppUtils.transactionRequestInstance();
        transactionRequest.getPost().setUrl(null);

        String body = CommonAutomationUtils.stringToJson(transactionRequest);
        Response response = AuthenticationTransactionRequest.postRequest(body, AppConstants.INTERNAL_END_POINT);

        CommonAutomationUtils.verifyCommonResponseSuccessValidation(response, HttpStatus.SC_OK);
        CommonAutomationUtils.verifyNonAvailableKey(response, List.of(AppConstants.POST + "." + AppConstants.URL));
    }

    @Test(groups = {"MC:Authentication", "Abdul Rehman", "SECTION:Post Url Validation", "SC:Post Url"}, description = "Validates transaction authentication with an empty post URL", priority = 241)
    public void transactionAuthenticationEmptyPostUrlCase() {

        TransactionRequest transactionRequest = AppUtils.transactionRequestInstance();
        transactionRequest.getPost().setUrl("");

        String body = CommonAutomationUtils.stringToJson(transactionRequest);
        Response response = AuthenticationTransactionRequest.postRequest(body, AppConstants.INTERNAL_END_POINT);

        CommonAutomationUtils.verifyCommonResponseSuccessValidation(response, HttpStatus.SC_OK);
    }

    @Test(groups = {"MC:Authentication", "Abdul Rehman", "SECTION:Customer Validation", "SC:Customer"}, description = "Validates transaction authentication with a null customer object and save token purpose.", priority = 242)
    public void transactionAuthenticationNullCustomerANDSaveTokenPurposeCase(){

        TransactionRequest transactionRequest = AppUtils.transactionRequestInstance();
        transactionRequest.setCustomer(null);
        transactionRequest.getAuthentication().setPurpose(AuthenticationPurpose.SAVE_TOKEN);

        String body = CommonAutomationUtils.stringToJson(transactionRequest);
        Response response = AuthenticationTransactionRequest.postRequest(body, AppConstants.INTERNAL_END_POINT);
        String textResponse = response.getBody().asString();
        JsonNode jsonResponse = CommonAutomationUtils.convertToJson(textResponse);

        CommonAutomationUtils.verifyCommonResponseFailedValidation(jsonResponse, response.getStatusCode(), HttpStatus.SC_BAD_REQUEST, AppConstants.REQUIRED_FIELD_INVALID_CODE, AppConstants.REQUIRED_FIELD_INVALID_ERROR);
    }

    @Test(groups = {"MC:Authentication", "Abdul Rehman", "SECTION:Customer Name Validation", "SC:Customer Name"}, description = "Validates transaction authentication with a customer first name spaces trailing once.", priority = 243)
    public void transactionAuthenticationCustomerFirstNameTrailingSpaceCase(){

        TransactionRequest transactionRequest = AppUtils.transactionRequestInstance();
        TransactionRequest.Customer customer = transactionRequest.getCustomer();
        List<TransactionRequest.Customer.Name> nameList = customer.getName();
        nameList.get(0).setFirstName("  Osama  ");

        String body = CommonAutomationUtils.stringToJson(transactionRequest);
        Response response = AuthenticationTransactionRequest.postRequest(body, AppConstants.INTERNAL_END_POINT);

        CommonAutomationUtils.verifyCommonResponseSuccessValidation(response, HttpStatus.SC_OK, Map.of(AppConstants.CUSTOMER + "." + AppConstants.NAME + "[0]." + AppConstants.FIRST_NAME, "Osama"));
    }

    @Test(groups = {"MC:Authentication", "Abdul Rehman", "SECTION:Customer Name Validation", "SC:Customer Name"}, description = "Validates transaction authentication with a customer first name full of spaces.", priority = 244)
    public void transactionAuthenticationCustomerFirstNameOnlySpaceCase(){

        TransactionRequest transactionRequest = AppUtils.transactionRequestInstance();
        TransactionRequest.Customer customer = transactionRequest.getCustomer();
        List<TransactionRequest.Customer.Name> nameList = customer.getName();
        nameList.get(0).setFirstName("     ");

        String body = CommonAutomationUtils.stringToJson(transactionRequest);
        Response response = AuthenticationTransactionRequest.postRequest(body, AppConstants.INTERNAL_END_POINT);
        String textResponse = response.getBody().asString();
        JsonNode jsonResponse = CommonAutomationUtils.convertToJson(textResponse);

        CommonAutomationUtils.verifyCommonResponseFailedValidation(jsonResponse, response.getStatusCode(), HttpStatus.SC_BAD_REQUEST, AppConstants.REQUIRED_INPUT_INVALID_CODE, AppConstants.REQUIRED_INPUT_INVALID_ERROR);
    }
}
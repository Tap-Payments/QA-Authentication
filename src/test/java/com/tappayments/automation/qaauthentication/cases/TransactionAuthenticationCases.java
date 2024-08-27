package com.tappayments.automation.qaauthentication.cases;

import com.fasterxml.jackson.databind.JsonNode;
import com.tappayments.automation.qaauthentication.base.BaseTest;
import com.tappayments.automation.qaauthentication.model.TransactionRequest;
import com.tappayments.automation.qaauthentication.model.enums.TransactionType;
import com.tappayments.automation.qaauthentication.requests.AuthenticationTransactionRequest;
import com.tappayments.automation.qaauthentication.utils.AppConstants;
import com.tappayments.automation.qaauthentication.utils.AppUtils;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Map;
import java.util.UUID;

public class TransactionAuthenticationCases extends BaseTest {

    @Test
    public void transactionAuthenticationAmountUSDCurrencyCase() {

        TransactionRequest transactionRequest = AppUtils.transactionRequestInstance();

        transactionRequest.setAmount(10.11);
        transactionRequest.setCurrency("USD");

        String body = AppUtils.transactionRequestToJson(transactionRequest);
        Response response = AuthenticationTransactionRequest.postRequest(body, AppConstants.INTERNAL_END_POINT);
        AppUtils.verifyCommonResponseSuccessValidation(response, HttpStatus.SC_OK, Map.of(AppConstants.AMOUNT, 10.11, AppConstants.CURRENCY, "USD"));
    }

    @Test
    public void transactionAuthenticationAmountMoreDecimalUSDCurrencyCase() {

        TransactionRequest transactionRequest = AppUtils.transactionRequestInstance();

        transactionRequest.setAmount(10.111);
        transactionRequest.setCurrency("USD");

        String body = AppUtils.transactionRequestToJson(transactionRequest);
        Response response = AuthenticationTransactionRequest.postRequest(body, AppConstants.INTERNAL_END_POINT);
        String textResponse = response.getBody().asString();
        JsonNode jsonResponse = AppUtils.convertToJson(textResponse);

        AppUtils.verifyCommonResponseFailedValidation(jsonResponse, response.getStatusCode(), HttpStatus.SC_BAD_REQUEST, AppConstants.INVALID_DATA_CODE, AppConstants.INVALID_DATA_ERROR);
    }

    @Test
    public void transactionAuthenticationAmountMoreDecimalSARCurrencyCase() {

        TransactionRequest transactionRequest = AppUtils.transactionRequestInstance();

        transactionRequest.setAmount(10.111);
        transactionRequest.setCurrency("SAR");

        String body = AppUtils.transactionRequestToJson(transactionRequest);
        Response response = AuthenticationTransactionRequest.postRequest(body, AppConstants.INTERNAL_END_POINT);
        String textResponse = response.getBody().asString();
        JsonNode jsonResponse = AppUtils.convertToJson(textResponse);

        AppUtils.verifyCommonResponseFailedValidation(jsonResponse, response.getStatusCode(), HttpStatus.SC_BAD_REQUEST, AppConstants.INVALID_DATA_CODE, AppConstants.INVALID_DATA_ERROR);
    }

    @Test
    public void transactionAuthenticationAmountKWDCurrencyCase() {

        TransactionRequest transactionRequest = AppUtils.transactionRequestInstance();

        transactionRequest.setAmount(10.13);
        transactionRequest.setCurrency("KWD");

        String body = AppUtils.transactionRequestToJson(transactionRequest);
        Response response = AuthenticationTransactionRequest.postRequest(body, AppConstants.INTERNAL_END_POINT);
        AppUtils.verifyCommonResponseSuccessValidation(response, HttpStatus.SC_OK, Map.of(AppConstants.AMOUNT, 10.13, AppConstants.CURRENCY, "KWD"));
    }

    @Test
    public void transactionAuthenticationAmountMoreDecimalKWDCurrencyCase() {

        TransactionRequest transactionRequest = AppUtils.transactionRequestInstance();

        transactionRequest.setAmount(10.133);
        transactionRequest.setCurrency("KWD");

        String body = AppUtils.transactionRequestToJson(transactionRequest);
        Response response = AuthenticationTransactionRequest.postRequest(body, AppConstants.INTERNAL_END_POINT);
        AppUtils.verifyCommonResponseSuccessValidation(response, HttpStatus.SC_OK, Map.of(AppConstants.AMOUNT, 10.133, AppConstants.CURRENCY, "KWD"));
    }

    @Test
    public void transactionAuthenticationAmountMoreFourDecimalSARCurrencyCase() {

        TransactionRequest transactionRequest = AppUtils.transactionRequestInstance();

        transactionRequest.setAmount(10.1234);
        transactionRequest.setCurrency("KWD");

        String body = AppUtils.transactionRequestToJson(transactionRequest);
        Response response = AuthenticationTransactionRequest.postRequest(body, AppConstants.INTERNAL_END_POINT);
        String textResponse = response.getBody().asString();
        JsonNode jsonResponse = AppUtils.convertToJson(textResponse);

        AppUtils.verifyCommonResponseFailedValidation(jsonResponse, response.getStatusCode(), HttpStatus.SC_BAD_REQUEST, AppConstants.INVALID_DATA_CODE, AppConstants.INVALID_DATA_ERROR);
    }

    @Test
    public void transactionAuthenticationNegativeAmountCase() {

        TransactionRequest transactionRequest = AppUtils.transactionRequestInstance();

        transactionRequest.setAmount(-10.10);

        String body = AppUtils.transactionRequestToJson(transactionRequest);
        Response response = AuthenticationTransactionRequest.postRequest(body, AppConstants.INTERNAL_END_POINT);
        AppUtils.verifyCommonResponseSuccessValidation(response, HttpStatus.SC_OK, Map.of(AppConstants.AMOUNT, -10.10));
    }

    @Test
    public void transactionAuthenticationValidAmountCase() {

        TransactionRequest transactionRequest = AppUtils.transactionRequestInstance();

        transactionRequest.setAmount(10.11);

        String body = AppUtils.transactionRequestToJson(transactionRequest);
        Response response = AuthenticationTransactionRequest.postRequest(body, AppConstants.INTERNAL_END_POINT);
        AppUtils.verifyCommonResponseSuccessValidation(response, HttpStatus.SC_OK, Map.of(AppConstants.AMOUNT, 10.11));
    }

    @Test
    public void transactionAuthenticationNullAmountCase() {

        TransactionRequest transactionRequest = AppUtils.transactionRequestInstance();

        transactionRequest.setAmount(null);

        String body = AppUtils.transactionRequestToJson(transactionRequest);
        Response response = AuthenticationTransactionRequest.postRequest(body, AppConstants.INTERNAL_END_POINT);
    }

    @Test
    public void transactionAuthenticationMaxAmountCase() {

        TransactionRequest transactionRequest = AppUtils.transactionRequestInstance();

        transactionRequest.setAmount(9999999999.99);

        String body = AppUtils.transactionRequestToJson(transactionRequest);
        Response response = AuthenticationTransactionRequest.postRequest(body, AppConstants.INTERNAL_END_POINT);
        AppUtils.verifyCommonResponseSuccessValidation(response, HttpStatus.SC_OK, Map.of(AppConstants.AMOUNT, 9999999999.99));
    }

    @Test
    public void transactionAuthenticationValidCurrencyCase() {

        TransactionRequest transactionRequest = AppUtils.transactionRequestInstance();

        transactionRequest.setCurrency("SAR");

        String body = AppUtils.transactionRequestToJson(transactionRequest);
        Response response = AuthenticationTransactionRequest.postRequest(body, AppConstants.INTERNAL_END_POINT);
        AppUtils.verifyCommonResponseSuccessValidation(response, HttpStatus.SC_OK, Map.of(AppConstants.CURRENCY, "SAR"));
    }

    @Test
    public void transactionAuthenticationValidUnknownCurrencyCase() {

        TransactionRequest transactionRequest = AppUtils.transactionRequestInstance();

        transactionRequest.setCurrency("YEN");

        String body = AppUtils.transactionRequestToJson(transactionRequest);
        Response response = AuthenticationTransactionRequest.postRequest(body, AppConstants.INTERNAL_END_POINT);
        AppUtils.verifyCommonResponseSuccessValidation(response, HttpStatus.SC_OK, Map.of(AppConstants.CURRENCY, "YEN"));
    }

    @Test
    public void transactionAuthenticationInvalidCurrencyCase() {

        TransactionRequest transactionRequest = AppUtils.transactionRequestInstance();

        transactionRequest.setCurrency("ABC");

        String body = AppUtils.transactionRequestToJson(transactionRequest);
        Response response = AuthenticationTransactionRequest.postRequest(body, AppConstants.INTERNAL_END_POINT);
        String textResponse = response.getBody().asString();
        JsonNode jsonResponse = AppUtils.convertToJson(textResponse);

        AppUtils.verifyCommonResponseFailedValidation(jsonResponse, response.getStatusCode(), HttpStatus.SC_BAD_REQUEST, AppConstants.INVALID_DATA_CODE, AppConstants.INVALID_DATA_ERROR);
    }

    @Test
    public void transactionAuthenticationNullCurrencyCase() {

        TransactionRequest transactionRequest = AppUtils.transactionRequestInstance();

        transactionRequest.setCurrency(null);

        String body = AppUtils.transactionRequestToJson(transactionRequest);
        Response response = AuthenticationTransactionRequest.postRequest(body, AppConstants.INTERNAL_END_POINT);
        String textResponse = response.getBody().asString();
        JsonNode jsonResponse = AppUtils.convertToJson(textResponse);

        AppUtils.verifyCommonResponseFailedValidation(jsonResponse, response.getStatusCode(), HttpStatus.SC_BAD_REQUEST, AppConstants.INVALID_DATA_CODE, AppConstants.INVALID_DATA_ERROR);
    }

    @Test
    public void transactionAuthenticationEmptyCurrencyCase() {

        TransactionRequest transactionRequest = AppUtils.transactionRequestInstance();

        transactionRequest.setCurrency("");

        String body = AppUtils.transactionRequestToJson(transactionRequest);
        Response response = AuthenticationTransactionRequest.postRequest(body, AppConstants.INTERNAL_END_POINT);
        String textResponse = response.getBody().asString();
        JsonNode jsonResponse = AppUtils.convertToJson(textResponse);

        AppUtils.verifyCommonResponseFailedValidation(jsonResponse, response.getStatusCode(), HttpStatus.SC_BAD_REQUEST, AppConstants.INVALID_DATA_CODE, AppConstants.INVALID_DATA_ERROR);
    }

    @Test
    public void transactionAuthenticationNumbericCurrencyCase() {

        TransactionRequest transactionRequest = AppUtils.transactionRequestInstance();

        transactionRequest.setCurrency("123");

        String body = AppUtils.transactionRequestToJson(transactionRequest);
        Response response = AuthenticationTransactionRequest.postRequest(body, AppConstants.INTERNAL_END_POINT);
        String textResponse = response.getBody().asString();
        JsonNode jsonResponse = AppUtils.convertToJson(textResponse);

        AppUtils.verifyCommonResponseFailedValidation(jsonResponse, response.getStatusCode(), HttpStatus.SC_BAD_REQUEST, AppConstants.INVALID_DATA_CODE, AppConstants.INVALID_DATA_ERROR);
    }

    @Test
    public void transactionAuthenticationFalseSaveCardCase() {

        TransactionRequest transactionRequest = AppUtils.transactionRequestInstance();

        transactionRequest.setSaveCard(false);

        String body = AppUtils.transactionRequestToJson(transactionRequest);
        Response response = AuthenticationTransactionRequest.postRequest(body, AppConstants.INTERNAL_END_POINT);
        AppUtils.verifyCommonResponseSuccessValidation(response, HttpStatus.SC_OK, Map.of(AppConstants.SAVE_CARD, false));
    }

    @Test
    public void transactionAuthenticationTrueSaveCardCase() {

        TransactionRequest transactionRequest = AppUtils.transactionRequestInstance();

        transactionRequest.setSaveCard(true);

        String body = AppUtils.transactionRequestToJson(transactionRequest);
        Response response = AuthenticationTransactionRequest.postRequest(body, AppConstants.INTERNAL_END_POINT);
        AppUtils.verifyCommonResponseSuccessValidation(response, HttpStatus.SC_OK, Map.of(AppConstants.SAVE_CARD, true));
    }

    @Test
    public void transactionAuthenticationNullSaveCardCase() {

        TransactionRequest transactionRequest = AppUtils.transactionRequestInstance();

        transactionRequest.setSaveCard(null);

        String body = AppUtils.transactionRequestToJson(transactionRequest);
        Response response = AuthenticationTransactionRequest.postRequest(body, AppConstants.INTERNAL_END_POINT);
        AppUtils.verifyCommonResponseSuccessValidation(response, HttpStatus.SC_OK);
    }

    @Test
    public void transactionAuthenticationValidDescriptionCase() {

        TransactionRequest transactionRequest = AppUtils.transactionRequestInstance();
        String body = AppUtils.transactionRequestToJson(transactionRequest);
        Response response = AuthenticationTransactionRequest.postRequest(body, AppConstants.INTERNAL_END_POINT);
        AppUtils.verifyCommonResponseSuccessValidation(response, HttpStatus.SC_OK, Map.of(AppConstants.DESCRIPTION, "Test Description"));
    }

    @Test
    public void transactionAuthenticationEmptyDescriptionCase() {

        TransactionRequest transactionRequest = AppUtils.transactionRequestInstance();
        transactionRequest.setDescription("");
        String body = AppUtils.transactionRequestToJson(transactionRequest);
        Response response = AuthenticationTransactionRequest.postRequest(body, AppConstants.INTERNAL_END_POINT);
        AppUtils.verifyCommonResponseSuccessValidation(response, HttpStatus.SC_OK, Map.of(AppConstants.DESCRIPTION, ""));
    }

    @Test
    public void transactionAuthenticationNullDescriptionCase() {

        TransactionRequest transactionRequest = AppUtils.transactionRequestInstance();
        transactionRequest.setDescription(null);
        String body = AppUtils.transactionRequestToJson(transactionRequest);
        Response response = AuthenticationTransactionRequest.postRequest(body, AppConstants.INTERNAL_END_POINT);
        AppUtils.verifyCommonResponseSuccessValidation(response, HttpStatus.SC_OK);
    }

    @Test
    public void transactionAuthenticationNumbericDescriptionCase() {

        TransactionRequest transactionRequest = AppUtils.transactionRequestInstance();
        transactionRequest.setDescription(12345);
        String body = AppUtils.transactionRequestToJson(transactionRequest);
        Response response = AuthenticationTransactionRequest.postRequest(body, AppConstants.INTERNAL_END_POINT);
        AppUtils.verifyCommonResponseSuccessValidation(response, HttpStatus.SC_OK, Map.of(AppConstants.DESCRIPTION, "12345"));
    }

    @Test
    public void transactionAuthenticationNullTransactionCase() {

        TransactionRequest transactionRequest = AppUtils.transactionRequestInstance();
        transactionRequest.setTransaction(null);
        String body = AppUtils.transactionRequestToJson(transactionRequest);
        Response response = AuthenticationTransactionRequest.postRequest(body, AppConstants.INTERNAL_END_POINT);
        AppUtils.verifyCommonResponseSuccessValidation(response, HttpStatus.SC_OK);
    }

    @Test
    public void transactionAuthenticationChargeTransactionIdCase() {

        UUID uuid = UUID.randomUUID();
        TransactionRequest transactionRequest = AppUtils.transactionRequestInstance();
        TransactionRequest.Transaction transaction = transactionRequest.getTransaction();
        transaction.setId("trx_charge_" + uuid);
        transaction.setType(TransactionType.CHARGE);

        String body = AppUtils.transactionRequestToJson(transactionRequest);
        Response response = AuthenticationTransactionRequest.postRequest(body, AppConstants.INTERNAL_END_POINT);
        AppUtils.verifyCommonResponseSuccessValidation(response, HttpStatus.SC_OK, Map.of(AppConstants.TRANSACTION + "." + AppConstants.ID, "trx_charge_" + uuid,
                AppConstants.TRANSACTION + "." + AppConstants.TYPE, "CHARGE"));
    }

    @Test
    public void transactionAuthenticationAuthorizeTransactionTypeIdCase() {

        UUID uuid = UUID.randomUUID();
        TransactionRequest transactionRequest = AppUtils.transactionRequestInstance();
        TransactionRequest.Transaction transaction = transactionRequest.getTransaction();
        transaction.setId("trx_charge_" + uuid);
        transaction.setType(TransactionType.AUTHORIZE);

        String body = AppUtils.transactionRequestToJson(transactionRequest);
        Response response = AuthenticationTransactionRequest.postRequest(body, AppConstants.INTERNAL_END_POINT);
        AppUtils.verifyCommonResponseSuccessValidation(response, HttpStatus.SC_OK, Map.of(AppConstants.TRANSACTION + "." + AppConstants.ID, "trx_charge_" + uuid,
                AppConstants.TRANSACTION + "." + AppConstants.TYPE, "AUTHORIZE"));
    }

    @Test
    public void transactionAuthenticationEmptyTransactionIdCase() {

        TransactionRequest transactionRequest = AppUtils.transactionRequestInstance();
        TransactionRequest.Transaction transaction = transactionRequest.getTransaction();
        transaction.setId("");
        transaction.setType(TransactionType.CHARGE);

        String body = AppUtils.transactionRequestToJson(transactionRequest);
        Response response = AuthenticationTransactionRequest.postRequest(body, AppConstants.INTERNAL_END_POINT);
        AppUtils.verifyCommonResponseSuccessValidation(response, HttpStatus.SC_OK, Map.of(AppConstants.TRANSACTION + "." + AppConstants.TYPE, "CHARGE"));
    }

    @Test
    public void transactionAuthenticationNullTransactionIdCase() {

        TransactionRequest transactionRequest = AppUtils.transactionRequestInstance();
        TransactionRequest.Transaction transaction = transactionRequest.getTransaction();
        transaction.setId(null);
        transaction.setType(TransactionType.CHARGE);

        String body = AppUtils.transactionRequestToJson(transactionRequest);
        Response response = AuthenticationTransactionRequest.postRequest(body, AppConstants.INTERNAL_END_POINT);
        AppUtils.verifyCommonResponseSuccessValidation(response, HttpStatus.SC_OK, Map.of(AppConstants.TRANSACTION + "." + AppConstants.TYPE, "CHARGE"));
    }

    @Test
    public void transactionAuthenticationEmptyTransactionTypeIdCase() {

        TransactionRequest transactionRequest = AppUtils.transactionRequestInstance();
        TransactionRequest.Transaction transaction = transactionRequest.getTransaction();
        transaction.setId("trx_charge_" + UUID.randomUUID());
        transaction.setType(TransactionType.CHARGE);

        String body = AppUtils.transactionRequestToJson(transactionRequest);
        String updatedBody = AppUtils.modifyJson(body, "MODIFY", "transaction.type", "");
        Response response = AuthenticationTransactionRequest.postRequest(updatedBody, AppConstants.INTERNAL_END_POINT);
        AppUtils.verifyCommonResponseSuccessValidation(response, HttpStatus.SC_OK, Map.of(AppConstants.TRANSACTION + "." + AppConstants.TYPE, ""));
    }

    @Test
    public void transactionAuthenticationInvalidTransactionTypeIdCase() {

        TransactionRequest transactionRequest = AppUtils.transactionRequestInstance();
        TransactionRequest.Transaction transaction = transactionRequest.getTransaction();
        transaction.setId("trx_charge_" + UUID.randomUUID());
        transaction.setType(TransactionType.CHARGE);

        String body = AppUtils.transactionRequestToJson(transactionRequest);
        String updatedBody = AppUtils.modifyJson(body, "MODIFY", "transaction.type", "INVALID_TYPE");

        Response response = AuthenticationTransactionRequest.postRequest(updatedBody, AppConstants.INTERNAL_END_POINT);
        AppUtils.verifyCommonResponseSuccessValidation(response, HttpStatus.SC_OK, Map.of(AppConstants.TRANSACTION + "." + AppConstants.TYPE, "INVALID_TYPE"));
    }

    @Test
    public void transactionAuthenticationNullTransactionTypeIdCase() {

        TransactionRequest transactionRequest = AppUtils.transactionRequestInstance();
        TransactionRequest.Transaction transaction = transactionRequest.getTransaction();
        transaction.setId("trx_charge_" + UUID.randomUUID());
        transaction.setType(null);

        String body = AppUtils.transactionRequestToJson(transactionRequest);

        Response response = AuthenticationTransactionRequest.postRequest(body, AppConstants.INTERNAL_END_POINT);
        AppUtils.verifyCommonResponseSuccessValidation(response, HttpStatus.SC_OK);
    }

    @Test
    public void transactionAuthenticationNullReferenceCase() {

        TransactionRequest transactionRequest = AppUtils.transactionRequestInstance();

        transactionRequest.setReference(null);

        String body = AppUtils.transactionRequestToJson(transactionRequest);
        Response response = AuthenticationTransactionRequest.postRequest(body, AppConstants.INTERNAL_END_POINT);
        String textResponse = response.getBody().asString();
        JsonNode jsonResponse = AppUtils.convertToJson(textResponse);

        AppUtils.verifyCommonResponseFailedValidation(jsonResponse, response.getStatusCode(), HttpStatus.SC_BAD_REQUEST, AppConstants.INVALID_DATA_CODE, AppConstants.INVALID_DATA_ERROR);
    }

    @Test
    public void transactionAuthenticationValidReferenceTransactionCase() {

        TransactionRequest transactionRequest = AppUtils.transactionRequestInstance();

        String body = AppUtils.transactionRequestToJson(transactionRequest);
        Response response = AuthenticationTransactionRequest.postRequest(body, AppConstants.INTERNAL_END_POINT);

        AppUtils.verifyCommonResponseSuccessValidation(response, HttpStatus.SC_OK, Map.of(AppConstants.REFERENCE + "." + AppConstants.TRANSACTION, "tck_LV02G0720231644Xj5u3007999",
                AppConstants.REFERENCE + "." + AppConstants.ORDER, "7730231644079995502"));
    }

    @Test
    public void transactionAuthenticationReferenceNullTransactionCase() {

        TransactionRequest transactionRequest = AppUtils.transactionRequestInstance();
        transactionRequest.getReference().setTransaction(null);

        String body = AppUtils.transactionRequestToJson(transactionRequest);
        Response response = AuthenticationTransactionRequest.postRequest(body, AppConstants.INTERNAL_END_POINT);

        AppUtils.verifyCommonResponseSuccessValidation(response, HttpStatus.SC_OK, Map.of(AppConstants.REFERENCE + "." + AppConstants.ORDER, "7730231644079995502"));
    }

    @Test
    public void transactionAuthenticationReferenceEmptyTransactionCase() {

        TransactionRequest transactionRequest = AppUtils.transactionRequestInstance();
        transactionRequest.getReference().setTransaction("");

        String body = AppUtils.transactionRequestToJson(transactionRequest);
        Response response = AuthenticationTransactionRequest.postRequest(body, AppConstants.INTERNAL_END_POINT);

        AppUtils.verifyCommonResponseSuccessValidation(response, HttpStatus.SC_OK, Map.of(AppConstants.REFERENCE + "." + AppConstants.TRANSACTION, "",
                AppConstants.REFERENCE + "." + AppConstants.ORDER, "7730231644079995502"));
    }

    @Test
    public void transactionAuthenticationReferenceEmptyOrderCase() {

        TransactionRequest transactionRequest = AppUtils.transactionRequestInstance();
        transactionRequest.getReference().setOrder("");

        String body = AppUtils.transactionRequestToJson(transactionRequest);
        Response response = AuthenticationTransactionRequest.postRequest(body, AppConstants.INTERNAL_END_POINT);

        AppUtils.verifyCommonResponseSuccessValidation(response, HttpStatus.SC_OK, Map.of(AppConstants.REFERENCE + "." + AppConstants.TRANSACTION, "tck_LV02G0720231644Xj5u3007999",
                AppConstants.REFERENCE + "." + AppConstants.ORDER, ""));
    }

    @Test
    public void transactionAuthenticationReferenceNullOrderCase() {

        TransactionRequest transactionRequest = AppUtils.transactionRequestInstance();
        transactionRequest.getReference().setOrder(null);

        String body = AppUtils.transactionRequestToJson(transactionRequest);
        Response response = AuthenticationTransactionRequest.postRequest(body, AppConstants.INTERNAL_END_POINT);

        AppUtils.verifyCommonResponseSuccessValidation(response, HttpStatus.SC_OK, Map.of(AppConstants.REFERENCE + "." + AppConstants.TRANSACTION, "tck_LV02G0720231644Xj5u3007999"));
    }

    @Test
    public void transactionAuthenticationNullInvoiceCase() {

        TransactionRequest transactionRequest = AppUtils.transactionRequestInstance();
        transactionRequest.setInvoice(null);

        String body = AppUtils.transactionRequestToJson(transactionRequest);
        Response response = AuthenticationTransactionRequest.postRequest(body, AppConstants.INTERNAL_END_POINT);

        AppUtils.verifyCommonResponseSuccessValidation(response, HttpStatus.SC_OK);
    }

    @Test
    public void transactionAuthenticationValidInvoiceIdCase() {

        TransactionRequest transactionRequest = AppUtils.transactionRequestInstance();
        transactionRequest.getInvoice().setId("invoice_12345");

        String body = AppUtils.transactionRequestToJson(transactionRequest);
        Response response = AuthenticationTransactionRequest.postRequest(body, AppConstants.INTERNAL_END_POINT);

        AppUtils.verifyCommonResponseSuccessValidation(response, HttpStatus.SC_OK, Map.of(AppConstants.INVOICE + "." + AppConstants.ID, "invoice_12345"));
    }

    @Test
    public void transactionAuthenticationEmptyInvoiceIdCase() {

        TransactionRequest transactionRequest = AppUtils.transactionRequestInstance();
        transactionRequest.getInvoice().setId("");

        String body = AppUtils.transactionRequestToJson(transactionRequest);
        Response response = AuthenticationTransactionRequest.postRequest(body, AppConstants.INTERNAL_END_POINT);

        AppUtils.verifyCommonResponseSuccessValidation(response, HttpStatus.SC_OK, Map.of(AppConstants.INVOICE + "." + AppConstants.ID, ""));
    }

    @Test
    public void transactionAuthenticationNullInvoiceIdCase() {

        TransactionRequest transactionRequest = AppUtils.transactionRequestInstance();
        transactionRequest.getInvoice().setId(null);

        String body = AppUtils.transactionRequestToJson(transactionRequest);
        Response response = AuthenticationTransactionRequest.postRequest(body, AppConstants.INTERNAL_END_POINT);

        AppUtils.verifyCommonResponseSuccessValidation(response, HttpStatus.SC_OK);
    }

    @Test
    public void transactionAuthenticationNullCustomerCase() {

        TransactionRequest transactionRequest = AppUtils.transactionRequestInstance();
        transactionRequest.setCustomer(null);

        String body = AppUtils.transactionRequestToJson(transactionRequest);
        Response response = AuthenticationTransactionRequest.postRequest(body, AppConstants.INTERNAL_END_POINT);

        AppUtils.verifyCommonResponseSuccessValidation(response, HttpStatus.SC_OK);
    }

    @Test
    public void transactionAuthenticationValidCustomerIdCase() {

        TransactionRequest transactionRequest = AppUtils.transactionRequestInstance();

        String body = AppUtils.transactionRequestToJson(transactionRequest);
        Response response = AuthenticationTransactionRequest.postRequest(body, AppConstants.INTERNAL_END_POINT);

        AppUtils.verifyCommonResponseSuccessValidation(response, HttpStatus.SC_OK, Map.of(AppConstants.CUSTOMER + "." + AppConstants.ID, "cus_TS06A5420231128h9FP1110651"));
    }

    @Test
    public void transactionAuthenticationInvalidCustomerIdCase() {

        TransactionRequest transactionRequest = AppUtils.transactionRequestInstance();
        transactionRequest.getCustomer().setId("invalid_id");

        String body = AppUtils.transactionRequestToJson(transactionRequest);
        Response response = AuthenticationTransactionRequest.postRequest(body, AppConstants.INTERNAL_END_POINT);

        AppUtils.verifyCommonResponseSuccessValidation(response, HttpStatus.SC_OK, Map.of(AppConstants.CUSTOMER + "." + AppConstants.ID, "invalid_id"));
    }

    @Test
    public void transactionAuthenticationEmptyCustomerIdCase() {

        TransactionRequest transactionRequest = AppUtils.transactionRequestInstance();
        transactionRequest.getCustomer().setId("");

        String body = AppUtils.transactionRequestToJson(transactionRequest);
        Response response = AuthenticationTransactionRequest.postRequest(body, AppConstants.INTERNAL_END_POINT);

        AppUtils.verifyCommonResponseSuccessValidation(response, HttpStatus.SC_OK, Map.of(AppConstants.CUSTOMER + "." + AppConstants.ID, ""));
    }

    @Test
    public void transactionAuthenticationNullCustomerIdCase() {

        TransactionRequest transactionRequest = AppUtils.transactionRequestInstance();
        transactionRequest.getCustomer().setId(null);

        String body = AppUtils.transactionRequestToJson(transactionRequest);
        Response response = AuthenticationTransactionRequest.postRequest(body, AppConstants.INTERNAL_END_POINT);

        AppUtils.verifyCommonResponseSuccessValidation(response, HttpStatus.SC_OK);
    }

    @Test
    public void transactionAuthenticationNullCustomerNameCase() {

        TransactionRequest transactionRequest = AppUtils.transactionRequestInstance();
        transactionRequest.getCustomer().setName(null);

        String body = AppUtils.transactionRequestToJson(transactionRequest);
        Response response = AuthenticationTransactionRequest.postRequest(body, AppConstants.INTERNAL_END_POINT);

        AppUtils.verifyCommonResponseSuccessValidation(response, HttpStatus.SC_OK);
    }

    @Test
    public void transactionAuthenticationEmptyCustomerNameFirstNameCase() {

        TransactionRequest transactionRequest = AppUtils.transactionRequestInstance();
        TransactionRequest.Customer customer = transactionRequest.getCustomer();
        List<TransactionRequest.Customer.Name> nameList = customer.getName();
        nameList.get(0).setFirstName("");

        String body = AppUtils.transactionRequestToJson(transactionRequest);
        Response response = AuthenticationTransactionRequest.postRequest(body, AppConstants.INTERNAL_END_POINT);
        String textResponse = response.getBody().asString();
        JsonNode jsonResponse = AppUtils.convertToJson(textResponse);

        AppUtils.verifyCommonResponseFailedValidation(jsonResponse, response.getStatusCode(), HttpStatus.SC_BAD_REQUEST, AppConstants.REQUIRED_INPUT_INVALID_CODE, AppConstants.REQUIRED_INPUT_INVALID_ERROR);
    }

    @Test
    public void transactionAuthenticationNullCustomerNameFirstNameCase() {

        TransactionRequest transactionRequest = AppUtils.transactionRequestInstance();
        TransactionRequest.Customer customer = transactionRequest.getCustomer();
        List<TransactionRequest.Customer.Name> nameList = customer.getName();
        nameList.get(0).setFirstName(null);

        String body = AppUtils.transactionRequestToJson(transactionRequest);
        Response response = AuthenticationTransactionRequest.postRequest(body, AppConstants.INTERNAL_END_POINT);

        AppUtils.verifyCommonResponseSuccessValidation(response, HttpStatus.SC_OK);
    }

    @Test
    public void transactionAuthenticationValidCustomerNameFirstNameCase() {

        TransactionRequest transactionRequest = AppUtils.transactionRequestInstance();
        TransactionRequest.Customer customer = transactionRequest.getCustomer();
        List<TransactionRequest.Customer.Name> nameList = customer.getName();
        nameList.get(0).setFirstName("Ali Usman");

        String body = AppUtils.transactionRequestToJson(transactionRequest);
        Response response = AuthenticationTransactionRequest.postRequest(body, AppConstants.INTERNAL_END_POINT);

        AppUtils.verifyCommonResponseSuccessValidation(response, HttpStatus.SC_OK, Map.of(AppConstants.CUSTOMER + "." + AppConstants.NAME + "[0]." + AppConstants.FIRST_NAME, "Ali Usman"));
    }

    @Test
    public void transactionAuthenticationNumericCustomerNameFirstNameCase() {

        TransactionRequest transactionRequest = AppUtils.transactionRequestInstance();
        TransactionRequest.Customer customer = transactionRequest.getCustomer();
        List<TransactionRequest.Customer.Name> nameList = customer.getName();
        nameList.get(0).setFirstName("12345");

        String body = AppUtils.transactionRequestToJson(transactionRequest);
        String updatedBody = AppUtils.modifyJson(body, "MODIFY", "customer.name.first_name", 12345);
        Response response = AuthenticationTransactionRequest.postRequest(updatedBody, AppConstants.INTERNAL_END_POINT);

        AppUtils.verifyCommonResponseSuccessValidation(response, HttpStatus.SC_OK, Map.of(AppConstants.CUSTOMER + "." + AppConstants.NAME + "[0]." + AppConstants.FIRST_NAME, "12345"));
    }

    @Test
    public void transactionAuthenticationMaxCustomerNameFirstNameCase() {

        TransactionRequest transactionRequest = AppUtils.transactionRequestInstance();
        TransactionRequest.Customer customer = transactionRequest.getCustomer();
        List<TransactionRequest.Customer.Name> nameList = customer.getName();
        nameList.get(0).setFirstName("MaximillianAlessandroValentinHarringtonTheThirdOfEvergreenEstateOnTheBanksOfRiverThamesWithGoldenHairAndEyesOfEmeraldGreenTheSeekerOfTruthInTheLandOfTheRisingSun");

        String body = AppUtils.transactionRequestToJson(transactionRequest);
        Response response = AuthenticationTransactionRequest.postRequest(body, AppConstants.INTERNAL_END_POINT);
        String textResponse = response.getBody().asString();
        JsonNode jsonResponse = AppUtils.convertToJson(textResponse);

        AppUtils.verifyCommonResponseFailedValidation(jsonResponse, response.getStatusCode(), HttpStatus.SC_BAD_REQUEST, AppConstants.REQUIRED_INPUT_INVALID_CODE, AppConstants.REQUIRED_INPUT_INVALID_ERROR);
    }

    @Test
    public void transactionAuthenticationEmptyCustomerNameMiddleNameCase() {

        TransactionRequest transactionRequest = AppUtils.transactionRequestInstance();
        TransactionRequest.Customer customer = transactionRequest.getCustomer();
        List<TransactionRequest.Customer.Name> nameList = customer.getName();
        nameList.get(0).setMiddleName("");

        String body = AppUtils.transactionRequestToJson(transactionRequest);
        Response response = AuthenticationTransactionRequest.postRequest(body, AppConstants.INTERNAL_END_POINT);

        AppUtils.verifyCommonResponseSuccessValidation(response, HttpStatus.SC_OK, Map.of(AppConstants.CUSTOMER + "." + AppConstants.NAME + "[0]." + AppConstants.MIDDLE_NAME, ""));
    }

    @Test
    public void transactionAuthenticationNullCustomerNameMiddleNameCase() {

        TransactionRequest transactionRequest = AppUtils.transactionRequestInstance();
        TransactionRequest.Customer customer = transactionRequest.getCustomer();
        List<TransactionRequest.Customer.Name> nameList = customer.getName();
        nameList.get(0).setMiddleName(null);

        String body = AppUtils.transactionRequestToJson(transactionRequest);
        Response response = AuthenticationTransactionRequest.postRequest(body, AppConstants.INTERNAL_END_POINT);

        AppUtils.verifyCommonResponseSuccessValidation(response, HttpStatus.SC_OK);
    }

    @Test
    public void transactionAuthenticationValidCustomerNameMiddleNameCase() {

        TransactionRequest transactionRequest = AppUtils.transactionRequestInstance();
        TransactionRequest.Customer customer = transactionRequest.getCustomer();
        List<TransactionRequest.Customer.Name> nameList = customer.getName();
        nameList.get(0).setMiddleName("Ali Usman");

        String body = AppUtils.transactionRequestToJson(transactionRequest);
        Response response = AuthenticationTransactionRequest.postRequest(body, AppConstants.INTERNAL_END_POINT);

        AppUtils.verifyCommonResponseSuccessValidation(response, HttpStatus.SC_OK, Map.of(AppConstants.CUSTOMER + "." + AppConstants.NAME + "[0]." + AppConstants.MIDDLE_NAME, "Ali Usman"));
    }

    @Test
    public void transactionAuthenticationNumericCustomerNameMiddleNameCase() {

        TransactionRequest transactionRequest = AppUtils.transactionRequestInstance();
        TransactionRequest.Customer customer = transactionRequest.getCustomer();
        List<TransactionRequest.Customer.Name> nameList = customer.getName();
        nameList.get(0).setMiddleName("12345");

        String body = AppUtils.transactionRequestToJson(transactionRequest);
        String updatedBody = AppUtils.modifyJson(body, "MODIFY", "customer.name.middle_name", 12345);
        Response response = AuthenticationTransactionRequest.postRequest(updatedBody, AppConstants.INTERNAL_END_POINT);

        AppUtils.verifyCommonResponseSuccessValidation(response, HttpStatus.SC_OK, Map.of(AppConstants.CUSTOMER + "." + AppConstants.NAME + "[0]." + AppConstants.MIDDLE_NAME, "12345"));
    }

    @Test
    public void transactionAuthenticationEmptyCustomerNameLastNameCase() {

        TransactionRequest transactionRequest = AppUtils.transactionRequestInstance();
        TransactionRequest.Customer customer = transactionRequest.getCustomer();
        List<TransactionRequest.Customer.Name> nameList = customer.getName();
        nameList.get(0).setLastName("");

        String body = AppUtils.transactionRequestToJson(transactionRequest);
        Response response = AuthenticationTransactionRequest.postRequest(body, AppConstants.INTERNAL_END_POINT);

        AppUtils.verifyCommonResponseSuccessValidation(response, HttpStatus.SC_OK, Map.of(AppConstants.CUSTOMER + "." + AppConstants.NAME + "[0]." + AppConstants.LAST_NAME, ""));
    }

    @Test
    public void transactionAuthenticationNullCustomerNameLastNameCase() {

        TransactionRequest transactionRequest = AppUtils.transactionRequestInstance();
        TransactionRequest.Customer customer = transactionRequest.getCustomer();
        List<TransactionRequest.Customer.Name> nameList = customer.getName();
        nameList.get(0).setLastName(null);

        String body = AppUtils.transactionRequestToJson(transactionRequest);
        Response response = AuthenticationTransactionRequest.postRequest(body, AppConstants.INTERNAL_END_POINT);

        AppUtils.verifyCommonResponseSuccessValidation(response, HttpStatus.SC_OK);
    }

    @Test
    public void transactionAuthenticationValidCustomerNameLastNameCase() {

        TransactionRequest transactionRequest = AppUtils.transactionRequestInstance();
        TransactionRequest.Customer customer = transactionRequest.getCustomer();
        List<TransactionRequest.Customer.Name> nameList = customer.getName();
        nameList.get(0).setLastName("Ali Usman");

        String body = AppUtils.transactionRequestToJson(transactionRequest);
        Response response = AuthenticationTransactionRequest.postRequest(body, AppConstants.INTERNAL_END_POINT);

        AppUtils.verifyCommonResponseSuccessValidation(response, HttpStatus.SC_OK, Map.of(AppConstants.CUSTOMER + "." + AppConstants.NAME + "[0]." + AppConstants.LAST_NAME, "Ali Usman"));
    }

    @Test
    public void transactionAuthenticationNumericCustomerNameLastNameCase() {

        TransactionRequest transactionRequest = AppUtils.transactionRequestInstance();
        TransactionRequest.Customer customer = transactionRequest.getCustomer();
        List<TransactionRequest.Customer.Name> nameList = customer.getName();
        nameList.get(0).setMiddleName("12345");

        String body = AppUtils.transactionRequestToJson(transactionRequest);
        String updatedBody = AppUtils.modifyJson(body, "MODIFY", "customer.name.last_name", 12345);
        Response response = AuthenticationTransactionRequest.postRequest(updatedBody, AppConstants.INTERNAL_END_POINT);

        AppUtils.verifyCommonResponseSuccessValidation(response, HttpStatus.SC_OK, Map.of(AppConstants.CUSTOMER + "." + AppConstants.NAME + "[0]." + AppConstants.LAST_NAME, "12345"));
    }

    @Test
    public void transactionAuthenticationEmptyCustomerNameLocaleCase() {

        TransactionRequest transactionRequest = AppUtils.transactionRequestInstance();
        TransactionRequest.Customer customer = transactionRequest.getCustomer();
        List<TransactionRequest.Customer.Name> nameList = customer.getName();
        nameList.get(0).setLocale("");

        String body = AppUtils.transactionRequestToJson(transactionRequest);
        Response response = AuthenticationTransactionRequest.postRequest(body, AppConstants.INTERNAL_END_POINT);

        AppUtils.verifyCommonResponseSuccessValidation(response, HttpStatus.SC_OK, Map.of(AppConstants.CUSTOMER + "." + AppConstants.NAME + "[0]." + AppConstants.LOCALE, ""));
    }

    @Test
    public void transactionAuthenticationNullCustomerNameLocaleCase() {

        TransactionRequest transactionRequest = AppUtils.transactionRequestInstance();
        TransactionRequest.Customer customer = transactionRequest.getCustomer();
        List<TransactionRequest.Customer.Name> nameList = customer.getName();
        nameList.get(0).setLocale(null);

        String body = AppUtils.transactionRequestToJson(transactionRequest);
        Response response = AuthenticationTransactionRequest.postRequest(body, AppConstants.INTERNAL_END_POINT);

        AppUtils.verifyCommonResponseSuccessValidation(response, HttpStatus.SC_OK);
    }

    @Test
    public void transactionAuthenticationValidCustomerNameLocaleCase() {

        TransactionRequest transactionRequest = AppUtils.transactionRequestInstance();
        TransactionRequest.Customer customer = transactionRequest.getCustomer();
        List<TransactionRequest.Customer.Name> nameList = customer.getName();
        nameList.get(0).setLocale("en");

        String body = AppUtils.transactionRequestToJson(transactionRequest);
        Response response = AuthenticationTransactionRequest.postRequest(body, AppConstants.INTERNAL_END_POINT);

        AppUtils.verifyCommonResponseSuccessValidation(response, HttpStatus.SC_OK, Map.of(AppConstants.CUSTOMER + "." + AppConstants.NAME + "[0]." + AppConstants.LOCALE, "en"));
    }

    @Test
    public void transactionAuthenticationInvalidCustomerNameLocaleCase() {

        TransactionRequest transactionRequest = AppUtils.transactionRequestInstance();
        TransactionRequest.Customer customer = transactionRequest.getCustomer();
        List<TransactionRequest.Customer.Name> nameList = customer.getName();
        nameList.get(0).setLocale("end");

        String body = AppUtils.transactionRequestToJson(transactionRequest);
        Response response = AuthenticationTransactionRequest.postRequest(body, AppConstants.INTERNAL_END_POINT);

        AppUtils.verifyCommonResponseSuccessValidation(response, HttpStatus.SC_OK, Map.of(AppConstants.CUSTOMER + "." + AppConstants.NAME + "[0]." + AppConstants.LOCALE, "end"));
    }

    @Test
    public void transactionAuthenticationNumericCustomerNameLocaleCase() {

        TransactionRequest transactionRequest = AppUtils.transactionRequestInstance();
        TransactionRequest.Customer customer = transactionRequest.getCustomer();
        List<TransactionRequest.Customer.Name> nameList = customer.getName();
        nameList.get(0).setLocale("123");

        String body = AppUtils.transactionRequestToJson(transactionRequest);
        String updatedBody = AppUtils.modifyJson(body, "MODIFY", "customer.name.locale", 123);
        Response response = AuthenticationTransactionRequest.postRequest(updatedBody, AppConstants.INTERNAL_END_POINT);

        AppUtils.verifyCommonResponseSuccessValidation(response, HttpStatus.SC_OK, Map.of(AppConstants.CUSTOMER + "." + AppConstants.NAME + "[0]." + AppConstants.LOCALE, "123"));
    }

    @Test
    public void transactionAuthenticationEmptyCustomerNameOnCardCase() {

        TransactionRequest transactionRequest = AppUtils.transactionRequestInstance();
        transactionRequest.getCustomer().setNameOnCard("");

        String body = AppUtils.transactionRequestToJson(transactionRequest);
        Response response = AuthenticationTransactionRequest.postRequest(body, AppConstants.INTERNAL_END_POINT);

        AppUtils.verifyCommonResponseSuccessValidation(response, HttpStatus.SC_OK, Map.of(AppConstants.CUSTOMER + "." + AppConstants.NAME_ON_CARD, ""));
    }

    @Test
    public void transactionAuthenticationNullCustomerNameOnCardCase() {

        TransactionRequest transactionRequest = AppUtils.transactionRequestInstance();
        transactionRequest.getCustomer().setNameOnCard(null);

        String body = AppUtils.transactionRequestToJson(transactionRequest);
        Response response = AuthenticationTransactionRequest.postRequest(body, AppConstants.INTERNAL_END_POINT);

        AppUtils.verifyCommonResponseSuccessValidation(response, HttpStatus.SC_OK);
    }

    @Test
    public void transactionAuthenticationValidCustomerNameOnCardCase() {

        TransactionRequest transactionRequest = AppUtils.transactionRequestInstance();
        transactionRequest.getCustomer().setNameOnCard("Test Name");

        String body = AppUtils.transactionRequestToJson(transactionRequest);
        Response response = AuthenticationTransactionRequest.postRequest(body, AppConstants.INTERNAL_END_POINT);

        AppUtils.verifyCommonResponseSuccessValidation(response, HttpStatus.SC_OK, Map.of(AppConstants.CUSTOMER + "." + AppConstants.NAME_ON_CARD, "Test Name"));
    }

    @Test
    public void transactionAuthenticationNumericCustomerNameOnCardCase() {

        TransactionRequest transactionRequest = AppUtils.transactionRequestInstance();
        transactionRequest.getCustomer().setNameOnCard("123");

        String body = AppUtils.transactionRequestToJson(transactionRequest);
        String updatedBody = AppUtils.modifyJson(body, "MODIFY", "customer.name_on_card", 123);
        Response response = AuthenticationTransactionRequest.postRequest(updatedBody, AppConstants.INTERNAL_END_POINT);

        AppUtils.verifyCommonResponseSuccessValidation(response, HttpStatus.SC_OK, Map.of(AppConstants.CUSTOMER + "." + AppConstants.NAME_ON_CARD, "123"));
    }

    @Test
    public void transactionAuthenticationValidCustomerEmailCase() {

        TransactionRequest transactionRequest = AppUtils.transactionRequestInstance();
        transactionRequest.getCustomer().setEmail("test.test@test.com");

        String body = AppUtils.transactionRequestToJson(transactionRequest);
        Response response = AuthenticationTransactionRequest.postRequest(body, AppConstants.INTERNAL_END_POINT);

        AppUtils.verifyCommonResponseSuccessValidation(response, HttpStatus.SC_OK, Map.of(AppConstants.CUSTOMER + "." + AppConstants.EMAIL, "test.test@test.com"));
    }

    @Test
    public void transactionAuthenticationInvalidCustomerEmailCase() {

        TransactionRequest transactionRequest = AppUtils.transactionRequestInstance();
        transactionRequest.getCustomer().setEmail("a@b.c");

        String body = AppUtils.transactionRequestToJson(transactionRequest);
        Response response = AuthenticationTransactionRequest.postRequest(body, AppConstants.INTERNAL_END_POINT);
        String textResponse = response.getBody().asString();
        JsonNode jsonResponse = AppUtils.convertToJson(textResponse);

        AppUtils.verifyCommonResponseFailedValidation(jsonResponse, response.getStatusCode(), HttpStatus.SC_BAD_REQUEST, AppConstants.REQUIRED_INPUT_INVALID_CODE, AppConstants.REQUIRED_INPUT_INVALID_ERROR);
    }

    @Test
    public void transactionAuthenticationMoreThanMaxCustomerEmailCase() {

        TransactionRequest transactionRequest = AppUtils.transactionRequestInstance();
        transactionRequest.getCustomer().setEmail("a.very.long.email.address.with.many.characters.to.reach.the.limit@example.extremely.long.domain.name.with.many.subdomains.subdomains.to.maximize.length.com");

        String body = AppUtils.transactionRequestToJson(transactionRequest);
        Response response = AuthenticationTransactionRequest.postRequest(body, AppConstants.INTERNAL_END_POINT);
        String textResponse = response.getBody().asString();
        JsonNode jsonResponse = AppUtils.convertToJson(textResponse);

        AppUtils.verifyCommonResponseFailedValidation(jsonResponse, response.getStatusCode(), HttpStatus.SC_BAD_REQUEST, AppConstants.REQUIRED_INPUT_INVALID_CODE, AppConstants.REQUIRED_INPUT_INVALID_ERROR);
    }

    @Test
    public void transactionAuthenticationEmptyCustomerEmailCase() {

        TransactionRequest transactionRequest = AppUtils.transactionRequestInstance();
        transactionRequest.getCustomer().setEmail("");

        String body = AppUtils.transactionRequestToJson(transactionRequest);
        Response response = AuthenticationTransactionRequest.postRequest(body, AppConstants.INTERNAL_END_POINT);
        String textResponse = response.getBody().asString();
        JsonNode jsonResponse = AppUtils.convertToJson(textResponse);

        AppUtils.verifyCommonResponseFailedValidation(jsonResponse, response.getStatusCode(), HttpStatus.SC_BAD_REQUEST, AppConstants.REQUIRED_INPUT_INVALID_CODE, AppConstants.REQUIRED_INPUT_INVALID_ERROR);
    }

    @Test
    public void transactionAuthenticationStringOnlyCustomerEmailCase() {

        TransactionRequest transactionRequest = AppUtils.transactionRequestInstance();
        transactionRequest.getCustomer().setEmail("abcabc");

        String body = AppUtils.transactionRequestToJson(transactionRequest);
        Response response = AuthenticationTransactionRequest.postRequest(body, AppConstants.INTERNAL_END_POINT);
        String textResponse = response.getBody().asString();
        JsonNode jsonResponse = AppUtils.convertToJson(textResponse);

        AppUtils.verifyCommonResponseFailedValidation(jsonResponse, response.getStatusCode(), HttpStatus.SC_BAD_REQUEST, AppConstants.REQUIRED_INPUT_INVALID_CODE, AppConstants.REQUIRED_INPUT_INVALID_ERROR);
    }

    @Test
    public void transactionAuthenticationNullCustomerEmailCase() {

        TransactionRequest transactionRequest = AppUtils.transactionRequestInstance();
        transactionRequest.getCustomer().setEmail(null);

        String body = AppUtils.transactionRequestToJson(transactionRequest);
        Response response = AuthenticationTransactionRequest.postRequest(body, AppConstants.INTERNAL_END_POINT);

        AppUtils.verifyCommonResponseSuccessValidation(response, HttpStatus.SC_OK);
    }

    @Test
    public void transactionAuthenticationNullCustomerPhoneCase() {

        TransactionRequest transactionRequest = AppUtils.transactionRequestInstance();
        transactionRequest.getCustomer().setPhone(null);

        String body = AppUtils.transactionRequestToJson(transactionRequest);
        Response response = AuthenticationTransactionRequest.postRequest(body, AppConstants.INTERNAL_END_POINT);
        String textResponse = response.getBody().asString();
        JsonNode jsonResponse = AppUtils.convertToJson(textResponse);

        AppUtils.verifyCommonResponseFailedValidation(jsonResponse, response.getStatusCode(), HttpStatus.SC_BAD_REQUEST, AppConstants.UNACCEPTED_JSON_REQUEST_CODE, AppConstants.UNACCEPTED_JSON_REQUEST_ERROR);
    }

    @Test
    public void transactionAuthenticationValidCustomerPhoneCountryCodeCase() {

        TransactionRequest transactionRequest = AppUtils.transactionRequestInstance();
        TransactionRequest.Customer customer = transactionRequest.getCustomer();
        TransactionRequest.Customer.Phone phone = customer.getPhone();
        phone.setCountryCode("965");
        phone.setNumber("50000000");

        String body = AppUtils.transactionRequestToJson(transactionRequest);
        Response response = AuthenticationTransactionRequest.postRequest(body, AppConstants.INTERNAL_END_POINT);

        AppUtils.verifyCommonResponseSuccessValidation(response, HttpStatus.SC_OK, Map.of(AppConstants.CUSTOMER + "." + AppConstants.PHONE + "." + AppConstants.COUNTRY_CODE, "965",
                AppConstants.CUSTOMER + "." + AppConstants.PHONE + "." + AppConstants.NUMBER, "50000000"));
    }

    @Test
    public void transactionAuthenticationEmptyCustomerPhoneCountryCodeCase() {

        TransactionRequest transactionRequest = AppUtils.transactionRequestInstance();
        TransactionRequest.Customer customer = transactionRequest.getCustomer();
        TransactionRequest.Customer.Phone phone = customer.getPhone();
        phone.setCountryCode("");
        phone.setNumber("50000000");

        String body = AppUtils.transactionRequestToJson(transactionRequest);
        Response response = AuthenticationTransactionRequest.postRequest(body, AppConstants.INTERNAL_END_POINT);
        String textResponse = response.getBody().asString();
        JsonNode jsonResponse = AppUtils.convertToJson(textResponse);

        AppUtils.verifyCommonResponseFailedValidation(jsonResponse, response.getStatusCode(), HttpStatus.SC_BAD_REQUEST, AppConstants.REQUIRED_INPUT_INVALID_CODE, AppConstants.REQUIRED_INPUT_INVALID_ERROR);
    }

    @Test
    public void transactionAuthenticationMoreThanMaxCustomerPhoneCountryCodeCase() {

        TransactionRequest transactionRequest = AppUtils.transactionRequestInstance();
        TransactionRequest.Customer customer = transactionRequest.getCustomer();
        TransactionRequest.Customer.Phone phone = customer.getPhone();
        phone.setCountryCode("123456");
        phone.setNumber("50000000");

        String body = AppUtils.transactionRequestToJson(transactionRequest);
        Response response = AuthenticationTransactionRequest.postRequest(body, AppConstants.INTERNAL_END_POINT);
        String textResponse = response.getBody().asString();
        JsonNode jsonResponse = AppUtils.convertToJson(textResponse);

        AppUtils.verifyCommonResponseFailedValidation(jsonResponse, response.getStatusCode(), HttpStatus.SC_BAD_REQUEST, AppConstants.REQUIRED_INPUT_INVALID_CODE, AppConstants.REQUIRED_INPUT_INVALID_ERROR);
    }

    @Test
    public void transactionAuthenticationNullCustomerPhoneCountryCodeCase() {

        TransactionRequest transactionRequest = AppUtils.transactionRequestInstance();
        TransactionRequest.Customer customer = transactionRequest.getCustomer();
        TransactionRequest.Customer.Phone phone = customer.getPhone();
        phone.setCountryCode(null);
        phone.setNumber("50000000");

        String body = AppUtils.transactionRequestToJson(transactionRequest);
        Response response = AuthenticationTransactionRequest.postRequest(body, AppConstants.INTERNAL_END_POINT);

        AppUtils.verifyCommonResponseSuccessValidation(response, HttpStatus.SC_OK);
    }

    @Test
    public void transactionAuthenticationNumericCustomerPhoneCountryCodeCase() {

        TransactionRequest transactionRequest = AppUtils.transactionRequestInstance();
        TransactionRequest.Customer customer = transactionRequest.getCustomer();
        TransactionRequest.Customer.Phone phone = customer.getPhone();
        phone.setCountryCode("123");
        phone.setNumber("50000000");

        String body = AppUtils.transactionRequestToJson(transactionRequest);
        String updatedBody = AppUtils.modifyJson(body, "MODIFY", "customer.phone.country_code", 123);
        Response response = AuthenticationTransactionRequest.postRequest(updatedBody, AppConstants.INTERNAL_END_POINT);

        AppUtils.verifyCommonResponseSuccessValidation(response, HttpStatus.SC_OK, Map.of(AppConstants.CUSTOMER + "." + AppConstants.PHONE + "." + AppConstants.COUNTRY_CODE, "123"));
    }

    @Test
    public void transactionAuthenticationEmptyCustomerPhoneNumberCase() {

        TransactionRequest transactionRequest = AppUtils.transactionRequestInstance();
        TransactionRequest.Customer customer = transactionRequest.getCustomer();
        TransactionRequest.Customer.Phone phone = customer.getPhone();
        phone.setCountryCode("965");
        phone.setNumber("");

        String body = AppUtils.transactionRequestToJson(transactionRequest);
        Response response = AuthenticationTransactionRequest.postRequest(body, AppConstants.INTERNAL_END_POINT);
        String textResponse = response.getBody().asString();
        JsonNode jsonResponse = AppUtils.convertToJson(textResponse);

        AppUtils.verifyCommonResponseFailedValidation(jsonResponse, response.getStatusCode(), HttpStatus.SC_BAD_REQUEST, AppConstants.REQUIRED_INPUT_INVALID_CODE, AppConstants.REQUIRED_INPUT_INVALID_ERROR);
    }

    @Test
    public void transactionAuthenticationMoreThanMaxCustomerPhoneNumberCase() {

        TransactionRequest transactionRequest = AppUtils.transactionRequestInstance();
        TransactionRequest.Customer customer = transactionRequest.getCustomer();
        TransactionRequest.Customer.Phone phone = customer.getPhone();
        phone.setCountryCode("965");
        phone.setNumber("12345678912345678912345");

        String body = AppUtils.transactionRequestToJson(transactionRequest);
        Response response = AuthenticationTransactionRequest.postRequest(body, AppConstants.INTERNAL_END_POINT);
        String textResponse = response.getBody().asString();
        JsonNode jsonResponse = AppUtils.convertToJson(textResponse);

        AppUtils.verifyCommonResponseFailedValidation(jsonResponse, response.getStatusCode(), HttpStatus.SC_BAD_REQUEST, AppConstants.REQUIRED_INPUT_INVALID_CODE, AppConstants.REQUIRED_INPUT_INVALID_ERROR);
    }

    @Test
    public void transactionAuthenticationNullCustomerPhoneNumberCase() {

        TransactionRequest transactionRequest = AppUtils.transactionRequestInstance();
        TransactionRequest.Customer customer = transactionRequest.getCustomer();
        TransactionRequest.Customer.Phone phone = customer.getPhone();
        phone.setCountryCode("123");
        phone.setNumber(null);

        String body = AppUtils.transactionRequestToJson(transactionRequest);
        Response response = AuthenticationTransactionRequest.postRequest(body, AppConstants.INTERNAL_END_POINT);

        AppUtils.verifyCommonResponseSuccessValidation(response, HttpStatus.SC_OK);
    }

    @Test
    public void transactionAuthenticationCustomerWithPhoneNumberWithoutCountryCodeCase() {

        TransactionRequest transactionRequest = AppUtils.transactionRequestInstance();

        String body = AppUtils.transactionRequestToJson(transactionRequest);
        String updatedBody = AppUtils.modifyJson(body, "DELETE", "customer.phone.country_code", null);
        Response response = AuthenticationTransactionRequest.postRequest(updatedBody, AppConstants.INTERNAL_END_POINT);

        AppUtils.verifyCommonResponseSuccessValidation(response, HttpStatus.SC_OK, Map.of(AppConstants.CUSTOMER + "." + AppConstants.PHONE + "." + AppConstants.NUMBER, "50000000"));
    }

    @Test
    public void transactionAuthenticationCustomerWithoutPhoneNumberWithCountryCodeCase() {

        TransactionRequest transactionRequest = AppUtils.transactionRequestInstance();

        String body = AppUtils.transactionRequestToJson(transactionRequest);
        String updatedBody = AppUtils.modifyJson(body, "DELETE", "customer.phone.number", null);
        Response response = AuthenticationTransactionRequest.postRequest(updatedBody, AppConstants.INTERNAL_END_POINT);

        AppUtils.verifyCommonResponseSuccessValidation(response, HttpStatus.SC_OK, Map.of(AppConstants.CUSTOMER + "." + AppConstants.PHONE + "." + AppConstants.COUNTRY_CODE, "965"));
    }

    @Test
    public void transactionAuthenticationWithCustomerEmailWithoutPhoneCase() {

        TransactionRequest transactionRequest = AppUtils.transactionRequestInstance();

        String body = AppUtils.transactionRequestToJson(transactionRequest);
        String updatedBody = AppUtils.modifyJson(body, "DELETE", "customer.phone", null);
        Response response = AuthenticationTransactionRequest.postRequest(updatedBody, AppConstants.INTERNAL_END_POINT);

        AppUtils.verifyCommonResponseSuccessValidation(response, HttpStatus.SC_OK);
    }

    @Test
    public void transactionAuthenticationWithoutCustomerEmailWithPhoneCase() {

        TransactionRequest transactionRequest = AppUtils.transactionRequestInstance();

        String body = AppUtils.transactionRequestToJson(transactionRequest);
        String updatedBody = AppUtils.modifyJson(body, "DELETE", "customer.email", null);
        Response response = AuthenticationTransactionRequest.postRequest(updatedBody, AppConstants.INTERNAL_END_POINT);

        AppUtils.verifyCommonResponseSuccessValidation(response, HttpStatus.SC_OK);
    }

    @Test
    public void transactionAuthenticationWithoutCustomerEmailWithOutPhoneCase() {

        TransactionRequest transactionRequest = AppUtils.transactionRequestInstance();

        String body = AppUtils.transactionRequestToJson(transactionRequest);
        String updatedBody = AppUtils.modifyJson(body, "DELETE", "customer.email", null);
        updatedBody = AppUtils.modifyJson(updatedBody, "DELETE", "customer.phone", null);
        Response response = AuthenticationTransactionRequest.postRequest(updatedBody, AppConstants.INTERNAL_END_POINT);

        AppUtils.verifyCommonResponseSuccessValidation(response, HttpStatus.SC_OK);
    }

    @Test
    public void transactionAuthenticationNullDeviceCase() {

        TransactionRequest transactionRequest = AppUtils.transactionRequestInstance();
        transactionRequest.setDevice(null);

        String body = AppUtils.transactionRequestToJson(transactionRequest);
        Response response = AuthenticationTransactionRequest.postRequest(body, AppConstants.INTERNAL_END_POINT);
        String textResponse = response.getBody().asString();
        JsonNode jsonResponse = AppUtils.convertToJson(textResponse);

        AppUtils.verifyCommonResponseFailedValidation(jsonResponse, response.getStatusCode(), HttpStatus.SC_BAD_REQUEST, AppConstants.REQUIRED_FIELD_INVALID_CODE, AppConstants.REQUIRED_FIELD_INVALID_ERROR);
    }

    @Test
    public void transactionAuthenticationValidDeviceIpAddressV4Case() {

        TransactionRequest transactionRequest = AppUtils.transactionRequestInstance();

        String body = AppUtils.transactionRequestToJson(transactionRequest);
        Response response = AuthenticationTransactionRequest.postRequest(body, AppConstants.INTERNAL_END_POINT);

        AppUtils.verifyCommonResponseSuccessValidation(response, HttpStatus.SC_OK);
    }

    @Test
    public void transactionAuthenticationValidDeviceIpAddressV6Case() {

        TransactionRequest transactionRequest = AppUtils.transactionRequestInstance();
        transactionRequest.getDevice().setIpAddress("2001:0db8:85a3:0000:0000:8a2e:0370:7334");

        String body = AppUtils.transactionRequestToJson(transactionRequest);
        Response response = AuthenticationTransactionRequest.postRequest(body, AppConstants.INTERNAL_END_POINT);

        AppUtils.verifyCommonResponseSuccessValidation(response, HttpStatus.SC_OK);
    }

    @Test
    public void transactionAuthenticationEmptyDeviceIpAddressCase() {

        TransactionRequest transactionRequest = AppUtils.transactionRequestInstance();
        transactionRequest.getDevice().setIpAddress("");

        String body = AppUtils.transactionRequestToJson(transactionRequest);
        Response response = AuthenticationTransactionRequest.postRequest(body, AppConstants.INTERNAL_END_POINT);

        AppUtils.verifyCommonResponseSuccessValidation(response, HttpStatus.SC_OK);
    }

    @Test
    public void transactionAuthenticationInvalidDeviceIpAddressCase() {

        TransactionRequest transactionRequest = AppUtils.transactionRequestInstance();
        transactionRequest.getDevice().setIpAddress("192.168.-1.-1");

        String body = AppUtils.transactionRequestToJson(transactionRequest);
        Response response = AuthenticationTransactionRequest.postRequest(body, AppConstants.INTERNAL_END_POINT);

        AppUtils.verifyCommonResponseSuccessValidation(response, HttpStatus.SC_OK);
    }

    @Test
    public void transactionAuthenticationNullDeviceIpAddressCase() {

        TransactionRequest transactionRequest = AppUtils.transactionRequestInstance();
        transactionRequest.getDevice().setIpAddress(null);

        String body = AppUtils.transactionRequestToJson(transactionRequest);
        Response response = AuthenticationTransactionRequest.postRequest(body, AppConstants.INTERNAL_END_POINT);

        AppUtils.verifyCommonResponseSuccessValidation(response, HttpStatus.SC_OK);
    }

    @Test
    public void transactionAuthenticationNullDeviceIpAddressWithPortCase() {

        TransactionRequest transactionRequest = AppUtils.transactionRequestInstance();
        transactionRequest.getDevice().setIpAddress("37.210.100.199:8080");

        String body = AppUtils.transactionRequestToJson(transactionRequest);
        Response response = AuthenticationTransactionRequest.postRequest(body, AppConstants.INTERNAL_END_POINT);

        AppUtils.verifyCommonResponseSuccessValidation(response, HttpStatus.SC_OK);
    }

    @Test
    public void transactionAuthenticationNullBrowserDetailsCase() {

        TransactionRequest transactionRequest = AppUtils.transactionRequestInstance();
        transactionRequest.getDevice().setBrowserDetails(null);

        String body = AppUtils.transactionRequestToJson(transactionRequest);
        Response response = AuthenticationTransactionRequest.postRequest(body, AppConstants.INTERNAL_END_POINT);
        String textResponse = response.getBody().asString();
        JsonNode jsonResponse = AppUtils.convertToJson(textResponse);

        AppUtils.verifyCommonResponseFailedValidation(jsonResponse, response.getStatusCode(), HttpStatus.SC_BAD_REQUEST, AppConstants.REQUIRED_FIELD_INVALID_CODE, AppConstants.REQUIRED_FIELD_INVALID_ERROR);
    }

    @Test
    public void transactionAuthenticationInvalidBrowserDetailsScreenHeightCase() {

        TransactionRequest transactionRequest = AppUtils.transactionRequestInstance();
        transactionRequest.getDevice().getBrowserDetails().setScreenHeight(-111);

        String body = AppUtils.transactionRequestToJson(transactionRequest);
        Response response = AuthenticationTransactionRequest.postRequest(body, AppConstants.INTERNAL_END_POINT);

        AppUtils.verifyCommonResponseSuccessValidation(response, HttpStatus.SC_OK);
    }

    @Test
    public void transactionAuthenticationZeroBrowserDetailsScreenHeightCase() {

        TransactionRequest transactionRequest = AppUtils.transactionRequestInstance();
        transactionRequest.getDevice().getBrowserDetails().setScreenHeight(0);

        String body = AppUtils.transactionRequestToJson(transactionRequest);
        Response response = AuthenticationTransactionRequest.postRequest(body, AppConstants.INTERNAL_END_POINT);

        AppUtils.verifyCommonResponseSuccessValidation(response, HttpStatus.SC_OK);
    }

    @Test
    public void transactionAuthenticationNullBrowserDetailsScreenHeightCase() {

        TransactionRequest transactionRequest = AppUtils.transactionRequestInstance();
        transactionRequest.getDevice().getBrowserDetails().setScreenHeight(null);

        String body = AppUtils.transactionRequestToJson(transactionRequest);
        Response response = AuthenticationTransactionRequest.postRequest(body, AppConstants.INTERNAL_END_POINT);

        AppUtils.verifyCommonResponseSuccessValidation(response, HttpStatus.SC_OK);
    }

    @Test
    public void transactionAuthenticationEmptyBrowserDetailsScreenHeightCase() {

        TransactionRequest transactionRequest = AppUtils.transactionRequestInstance();

        String body = AppUtils.transactionRequestToJson(transactionRequest);
        String updatedBody = AppUtils.modifyJson(body, "MODIFY", "device.browserDetails.screenHeight", "");
        Response response = AuthenticationTransactionRequest.postRequest(updatedBody, AppConstants.INTERNAL_END_POINT);

        AppUtils.verifyCommonResponseSuccessValidation(response, HttpStatus.SC_OK);
    }

    @Test
    public void transactionAuthenticationInvalidBrowserDetailsScreenWidthCase() {

        TransactionRequest transactionRequest = AppUtils.transactionRequestInstance();
        transactionRequest.getDevice().getBrowserDetails().setScreenWidth(-111);

        String body = AppUtils.transactionRequestToJson(transactionRequest);
        Response response = AuthenticationTransactionRequest.postRequest(body, AppConstants.INTERNAL_END_POINT);

        AppUtils.verifyCommonResponseSuccessValidation(response, HttpStatus.SC_OK);
    }

    @Test
    public void transactionAuthenticationZeroBrowserDetailsScreenWidthCase() {

        TransactionRequest transactionRequest = AppUtils.transactionRequestInstance();
        transactionRequest.getDevice().getBrowserDetails().setScreenWidth(0);

        String body = AppUtils.transactionRequestToJson(transactionRequest);
        Response response = AuthenticationTransactionRequest.postRequest(body, AppConstants.INTERNAL_END_POINT);

        AppUtils.verifyCommonResponseSuccessValidation(response, HttpStatus.SC_OK);
    }

    @Test
    public void transactionAuthenticationNullBrowserDetailsScreenWidthCase() {

        TransactionRequest transactionRequest = AppUtils.transactionRequestInstance();
        transactionRequest.getDevice().getBrowserDetails().setScreenWidth(null);

        String body = AppUtils.transactionRequestToJson(transactionRequest);
        Response response = AuthenticationTransactionRequest.postRequest(body, AppConstants.INTERNAL_END_POINT);

        AppUtils.verifyCommonResponseSuccessValidation(response, HttpStatus.SC_OK);
    }

    @Test
    public void transactionAuthenticationEmptyBrowserDetailsScreenWidthCase() {

        TransactionRequest transactionRequest = AppUtils.transactionRequestInstance();

        String body = AppUtils.transactionRequestToJson(transactionRequest);
        String updatedBody = AppUtils.modifyJson(body, "MODIFY", "device.browserDetails.screenWidth", "");
        Response response = AuthenticationTransactionRequest.postRequest(updatedBody, AppConstants.INTERNAL_END_POINT);

        AppUtils.verifyCommonResponseSuccessValidation(response, HttpStatus.SC_OK);
    }

    @Test
    public void transactionAuthenticationInvalidBrowserDetailsLanguageCase() {

        TransactionRequest transactionRequest = AppUtils.transactionRequestInstance();
        transactionRequest.getDevice().getBrowserDetails().setLanguage("een-USSS");

        String body = AppUtils.transactionRequestToJson(transactionRequest);
        Response response = AuthenticationTransactionRequest.postRequest(body, AppConstants.INTERNAL_END_POINT);

        AppUtils.verifyCommonResponseSuccessValidation(response, HttpStatus.SC_OK);
    }

    @Test
    public void transactionAuthenticationNullBrowserDetailsLanguageCase() {

        TransactionRequest transactionRequest = AppUtils.transactionRequestInstance();
        transactionRequest.getDevice().getBrowserDetails().setLanguage(null);

        String body = AppUtils.transactionRequestToJson(transactionRequest);
        Response response = AuthenticationTransactionRequest.postRequest(body, AppConstants.INTERNAL_END_POINT);

        AppUtils.verifyCommonResponseSuccessValidation(response, HttpStatus.SC_OK);
    }

    @Test
    public void transactionAuthenticationEmptyBrowserDetailsScreenLanguageCase() {

        TransactionRequest transactionRequest = AppUtils.transactionRequestInstance();

        String body = AppUtils.transactionRequestToJson(transactionRequest);
        String updatedBody = AppUtils.modifyJson(body, "MODIFY", "device.browserDetails.language", "");
        Response response = AuthenticationTransactionRequest.postRequest(updatedBody, AppConstants.INTERNAL_END_POINT);

        AppUtils.verifyCommonResponseSuccessValidation(response, HttpStatus.SC_OK);
    }

    @Test
    public void transactionAuthenticationNegativeBrowserDetailsColorDepthCase() {

        TransactionRequest transactionRequest = AppUtils.transactionRequestInstance();
        transactionRequest.getDevice().getBrowserDetails().setColorDepth(-123);

        String body = AppUtils.transactionRequestToJson(transactionRequest);
        Response response = AuthenticationTransactionRequest.postRequest(body, AppConstants.INTERNAL_END_POINT);

        AppUtils.verifyCommonResponseSuccessValidation(response, HttpStatus.SC_OK);
    }

    @Test
    public void transactionAuthenticationZeroBrowserDetailsColorDepthCase() {

        TransactionRequest transactionRequest = AppUtils.transactionRequestInstance();
        transactionRequest.getDevice().getBrowserDetails().setColorDepth(0);

        String body = AppUtils.transactionRequestToJson(transactionRequest);
        Response response = AuthenticationTransactionRequest.postRequest(body, AppConstants.INTERNAL_END_POINT);

        AppUtils.verifyCommonResponseSuccessValidation(response, HttpStatus.SC_OK);
    }

    @Test
    public void transactionAuthenticationNullBrowserDetailsColorDepthCase() {

        TransactionRequest transactionRequest = AppUtils.transactionRequestInstance();
        transactionRequest.getDevice().getBrowserDetails().setColorDepth(null);

        String body = AppUtils.transactionRequestToJson(transactionRequest);
        Response response = AuthenticationTransactionRequest.postRequest(body, AppConstants.INTERNAL_END_POINT);

        AppUtils.verifyCommonResponseSuccessValidation(response, HttpStatus.SC_OK);
    }

    @Test
    public void transactionAuthenticationEmptyBrowserDetailsColorDepthCase() {

        TransactionRequest transactionRequest = AppUtils.transactionRequestInstance();

        String body = AppUtils.transactionRequestToJson(transactionRequest);
        String updatedBody = AppUtils.modifyJson(body, "MODIFY", "device.browserDetails.colorDepth", "");
        Response response = AuthenticationTransactionRequest.postRequest(updatedBody, AppConstants.INTERNAL_END_POINT);

        AppUtils.verifyCommonResponseSuccessValidation(response, HttpStatus.SC_OK);
    }

    @Test
    public void transactionAuthenticationFalseBrowserDetailsJavaEnabledCase() {

        TransactionRequest transactionRequest = AppUtils.transactionRequestInstance();
        transactionRequest.getDevice().getBrowserDetails().setJavaEnabled("false");

        String body = AppUtils.transactionRequestToJson(transactionRequest);
        Response response = AuthenticationTransactionRequest.postRequest(body, AppConstants.INTERNAL_END_POINT);

        AppUtils.verifyCommonResponseSuccessValidation(response, HttpStatus.SC_OK);
    }

    @Test
    public void transactionAuthenticationTrueBrowserDetailsJavaEnabledCase() {

        TransactionRequest transactionRequest = AppUtils.transactionRequestInstance();
        transactionRequest.getDevice().getBrowserDetails().setJavaEnabled("true");

        String body = AppUtils.transactionRequestToJson(transactionRequest);
        Response response = AuthenticationTransactionRequest.postRequest(body, AppConstants.INTERNAL_END_POINT);

        AppUtils.verifyCommonResponseSuccessValidation(response, HttpStatus.SC_OK);
    }

    @Test
    public void transactionAuthenticationCaseSensitiveBrowserDetailsJavaEnabledCase() {

        TransactionRequest transactionRequest = AppUtils.transactionRequestInstance();
        transactionRequest.getDevice().getBrowserDetails().setJavaEnabled("TRuE");

        String body = AppUtils.transactionRequestToJson(transactionRequest);
        Response response = AuthenticationTransactionRequest.postRequest(body, AppConstants.INTERNAL_END_POINT);
        String textResponse = response.getBody().asString();
        JsonNode jsonResponse = AppUtils.convertToJson(textResponse);

        AppUtils.verifyCommonResponseFailedValidation(jsonResponse, response.getStatusCode(), HttpStatus.SC_BAD_REQUEST, AppConstants.UNACCEPTED_JSON_REQUEST_CODE, AppConstants.UNACCEPTED_JSON_REQUEST_ERROR);
    }

    @Test
    public void transactionAuthenticationNullBrowserDetailsJavaEnabledCase() {

        TransactionRequest transactionRequest = AppUtils.transactionRequestInstance();
        transactionRequest.getDevice().getBrowserDetails().setJavaEnabled(null);

        String body = AppUtils.transactionRequestToJson(transactionRequest);
        Response response = AuthenticationTransactionRequest.postRequest(body, AppConstants.INTERNAL_END_POINT);

        AppUtils.verifyCommonResponseSuccessValidation(response, HttpStatus.SC_OK);
    }

    @Test
    public void transactionAuthenticationEmptyBrowserDetailsJavaEnabledCase() {

        TransactionRequest transactionRequest = AppUtils.transactionRequestInstance();

        String body = AppUtils.transactionRequestToJson(transactionRequest);
        String updatedBody = AppUtils.modifyJson(body, "MODIFY", "device.browserDetails.javaEnabled", "");
        Response response = AuthenticationTransactionRequest.postRequest(updatedBody, AppConstants.INTERNAL_END_POINT);

        AppUtils.verifyCommonResponseSuccessValidation(response, HttpStatus.SC_OK);
    }

    @Test
    public void transactionAuthenticationInvalidBrowserDetailsJavaEnabledCase() {

        TransactionRequest transactionRequest = AppUtils.transactionRequestInstance();
        transactionRequest.getDevice().getBrowserDetails().setJavaEnabled("invalid");

        String body = AppUtils.transactionRequestToJson(transactionRequest);
        Response response = AuthenticationTransactionRequest.postRequest(body, AppConstants.INTERNAL_END_POINT);
        String textResponse = response.getBody().asString();
        JsonNode jsonResponse = AppUtils.convertToJson(textResponse);

        AppUtils.verifyCommonResponseFailedValidation(jsonResponse, response.getStatusCode(), HttpStatus.SC_BAD_REQUEST, AppConstants.UNACCEPTED_JSON_REQUEST_CODE, AppConstants.UNACCEPTED_JSON_REQUEST_ERROR);
    }
}
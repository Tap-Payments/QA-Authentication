package com.tappayments.automation.qaauthentication.cases;

import com.tappayments.automation.qaauthentication.base.BaseTest;
import com.tappayments.automation.qaauthentication.model.TransactionRequest;
import com.tappayments.automation.qaauthentication.requests.AuthenticationTransactionRequest;
import com.tappayments.automation.qaauthentication.utils.AppConstants;
import com.tappayments.automation.qaauthentication.utils.AppUtils;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.testng.annotations.Test;

public class TransactionAuthenticationCases extends BaseTest {

    @Test
    public void sampleTestCase() {

        TransactionRequest transactionRequest = AppUtils.transactionRequestInstance();
        String body = AppUtils.transactionRequestToJson(transactionRequest);


        Response response = AuthenticationTransactionRequest.postRequest(body, AppConstants.INTERNAL_END_POINT);

        response.then()
                .statusCode(HttpStatus.SC_OK);
    }
}

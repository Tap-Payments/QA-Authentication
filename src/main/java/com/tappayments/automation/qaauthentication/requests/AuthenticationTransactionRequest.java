package com.tappayments.automation.qaauthentication.requests;

import com.tappayments.automation.qaauthentication.base.RestAssuredClient;
import io.restassured.response.Response;

public class AuthenticationTransactionRequest extends RestAssuredClient {

    public static Response authenticateTransaction(String endpoint, String body){
        return postRequest(endpoint, body);
    }
}

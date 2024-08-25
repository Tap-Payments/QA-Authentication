package com.tappayments.automation.qaauthentication.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tappayments.automation.qaauthentication.model.TransactionRequest;

import java.util.List;
import java.util.Map;

public class AppUtils {

    public static TransactionRequest transactionRequestInstance(){

        return TransactionRequest.builder()
                .amount(10.000)
                .currency("KWD")
                .saveCard(false)
                .description("Test Description")
                .transaction(TransactionRequest.Transaction.builder()
                        .id("chg_LV03G3220232227Yg5t0507953")
                        .type("CHARGE")
                        .build())
                .reference(TransactionRequest.Reference.builder()
                        .transaction("tck_LV02G0720231644Xj5u3007999")
                        .order("7730231644079995502")
                        .build())
                .invoice(TransactionRequest.Invoice.builder()
                        .id("invoice_xxxxx")
                        .build())
                .customer(TransactionRequest.Customer.builder()
                        .id("cus_TS06A5420231128h9FP1110651")
                        .name(List.of(TransactionRequest.Customer.Name.builder()
                                .firstName("test")
                                .middleName("test")
                                .lastName("test")
                                .locale("en")
                                .build()))
                        .nameOnCard("")
                        .email("test@test.com")
                        .phone(TransactionRequest.Customer.Phone.builder()
                                .countryCode("965")
                                .number("50000000")
                                .build())
                        .build())
                .device(TransactionRequest.Device.builder()
                        .ipAddress("37.210.100.199")
                        .browserDetails(TransactionRequest.Device.BrowserDetails.builder()
                                .screenHeight(968)
                                .screenWidth(852)
                                .language("en-US")
                                .colorDepth(24)
                                .javaEnabled("false")
                                .javaScriptEnabled("true")
                                .timeZone(-180)
                                .acceptHeaders("application/json")
                                .challengeWindowSize("FULL_SCREEN")
                                .browserUserAgent("")
                                .build())
                        .build())
                .source(TransactionRequest.Source.builder()
                        .id("tok_YasK532352aU2U18HO5l162")
                        .card(TransactionRequest.Source.Card.builder()
                                .firstSix("484783")
                                .scheme("MADA")
                                .brand("VISA")
                                .category("CLASSIC")
                                .lastFour("SALWA HEDAR")
                                .name("SALWA HEDAR")
                                .expiry(TransactionRequest.Source.Card.Expiry.builder()
                                        .month("12")
                                        .year("30")
                                        .build())
                                .funding("CREDIT")
                                .build())
                        .build())
                .routing(TransactionRequest.Routing.builder()
                        .provider("MPGS")
                        .terminalId("ter_p3F64020211320j6XQ1002702")
                        .build())
                .authentication(TransactionRequest.Authentication.builder()
                        .channel("PAYER_BROWSER")
                        .purpose("PAYMENT_TRANSACTION")
                        .build())
                .merchant(TransactionRequest.Merchant.builder()
                        .id("merchant_xxxxxx")
                        .build())
                .paymentAgreement(TransactionRequest.PaymentAgreement.builder()
                        .id("payment_agreement_4bs644231855hCLV8i49z821")
                        .type("UNSCHEDULED")
                        .contract(TransactionRequest.PaymentAgreement.Contract.builder()
                                .id("card_vlEQ5423849Ua8y8bq9L121")
                                .type("ORDER")
                                .period(TransactionRequest.PaymentAgreement.Contract.Period.builder()
                                        .startDate(1691317374968L)
                                        .endDate(1691317374968L)
                                        .autoRenewal(true)
                                        .build())
                                .build())
                        .variableAmount(TransactionRequest.PaymentAgreement.VariableAmount.builder()
                                .id("variable_amount_a9s8df7a98df7")
                                .maximumAmount(10.0)
                                .build())
                        .scheduledPayments(TransactionRequest.PaymentAgreement.ScheduledPayments.builder()
                                .id("scheduled_payments_asdf8097asd98f7")
                                .count(100)
                                .frequency(TransactionRequest.PaymentAgreement.ScheduledPayments.Frequency.builder()
                                        .period("MONTHLY")
                                        .count(3)
                                        .build())
                                .build())
                        .build())
                .metadata(Map.of("udf1", "test 1", "udf2", "test 2"))
                .airline(TransactionRequest.Airline.builder()
                        .id("")
                        .build())
                .redirect(TransactionRequest.Redirect.builder()
                        .url("http://yourwebsite.com/redirect_url")
                        .build())
                .post(TransactionRequest.Post.builder()
                        .url("http://yourwebsite.com/redirect_url")
                        .build())
                .build();

    }

    public static String transactionRequestToJson(Object object){

        String payload = "";
        ObjectMapper mapper = new ObjectMapper();
        try {
            payload = mapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        return payload;
    }
}

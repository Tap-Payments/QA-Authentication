package com.tappayments.automation.qaauthentication.utils;

import com.tappayments.automation.qaauthentication.config.ConfigManager;
import com.tappayments.automation.qaauthentication.model.CardRequest;
import com.tappayments.automation.qaauthentication.model.TransactionRequest;
import com.tappayments.automation.qaauthentication.model.enums.AuthenticationChannel;
import com.tappayments.automation.qaauthentication.model.enums.AuthenticationPurpose;
import com.tappayments.automation.qaauthentication.model.enums.TransactionType;
import com.tappayments.automation.qaauthentication.requests.AuthenticationTransactionRequest;
import com.tappayments.automation.utils.CommonAutomationUtils;

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
                        .id("")
                        .type(TransactionType.CHARGE)
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
                        .ipAddress("192.168.1.1")
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
                        .id("tok_jz0r7241212f2kB19mh7V530")
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
                        .channel(AuthenticationChannel.PAYER_BROWSER)
                        .purpose(AuthenticationPurpose.PAYMENT_TRANSACTION)
                        .build())
                .merchant(TransactionRequest.Merchant.builder()
                        .id("1124340")
                        .build())
                .metadata(Map.of("udf1", "test1", "udf2", "test2"))
                .airline(TransactionRequest.Airline.builder()
                        .id("test_airline")
                        .build())
                .redirect(TransactionRequest.Redirect.builder()
                        .url("http://yourwebsite.com/redirect_url")
                        .build())
                .post(TransactionRequest.Post.builder()
                        .url("http://yourwebsite.com/post_url")
                        .build())
                .build();

    }

    public static CardRequest createCardRequest() {

        return CardRequest.builder()
                .card(CardRequest.Card.builder()
                        .number(4508750015741019L)
                        .exp_month(1)
                        .exp_year(2039)
                        .cvc(100)
                        .name("Osama Rabie")
                        .address(CardRequest.Card.Address.builder()
                                .country("Kuwait")
                                .line1("Salmiya, 21")
                                .city("Kuwait city")
                                .street("Salim")
                                .avenue("Gulf")
                                .build())
                        .build())
                .client_ip("192.168.1.20")
                .build();
    }

    public static void updateSourceAndMerchantIdForExternal(TransactionRequest transactionRequest){

        transactionRequest.getSource().setId(AuthenticationTransactionRequest.generateCardDetailToken());
        if(transactionRequest.getMerchant() != null)
            transactionRequest.getMerchant().setId(ConfigManager.getPropertyValue(AppConstants.MERCHANT_ID_VALUE));
    }

    public static String removeCardAndRoutingFromRequest(String body){

        body = CommonAutomationUtils.modifyJson(body, "DELETE", "source.card", null);
        body = CommonAutomationUtils.modifyJson(body, "DELETE", "routing", null);
        return body;
    }
}

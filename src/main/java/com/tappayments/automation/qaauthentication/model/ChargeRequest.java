package com.tappayments.automation.qaauthentication.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.tappayments.automation.qaauthentication.model.enums.AuthenticationChannel;
import com.tappayments.automation.qaauthentication.model.enums.AuthenticationPurpose;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
public class ChargeRequest extends TransactionAuthentication{

    private Authentication authentication;
    @JsonProperty(value = "customer_initiated")
    private Boolean customerInitiated;
    private Boolean threeDSecure;
    private Receipt receipt;
    private Source source;

    @Data
    @Builder
    public static class Authentication {
        private String id;
    }

    @Data
    @Builder
    public static class Receipt {
        private Boolean email;
        private Boolean sms;
    }

    @Data
    @Builder
    public static class Source {
        private String id;
    }
}

package com.tappayments.automation.qaauthentication.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.tappayments.automation.qaauthentication.model.enums.AuthenticationChannel;
import com.tappayments.automation.qaauthentication.model.enums.AuthenticationPurpose;
import com.tappayments.automation.qaauthentication.model.enums.TransactionType;
import com.tappayments.automation.qaauthentication.utils.AppConstants;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.SuperBuilder;

import java.util.List;
import java.util.Map;

@Data
@SuperBuilder
public class TransactionRequest extends TransactionAuthentication {

    private Transaction transaction;
    private Invoice invoice;
    private Device device;
    private Source source;
    private Routing routing;
    private Authentication authentication;
    private Airline airline;

    @Data
    @Builder
    public static class Transaction {
        private String id;
        private TransactionType type;
    }


    @Data
    @Builder
    public static class Invoice {
        private String id;
    }

    @Data
    @Builder
    public static class Device {
        private String ipAddress;
        private BrowserDetails browserDetails;

        @Data
        @Builder
        public static class BrowserDetails {
            private Integer screenHeight;
            private Integer screenWidth;
            private String language;
            private Integer colorDepth;
            private String javaEnabled;
            private String javaScriptEnabled;
            private Integer timeZone;
            private String acceptHeaders;
            private String challengeWindowSize;
            private String browserUserAgent;
        }
    }

    @Data
    @Builder
    public static class Source {
        private String id;
        private Card card;

        @Data
        @Builder
        public static class Card {
            @JsonProperty(value = AppConstants.FIRST_SIX)
            private String firstSix;
            private String scheme;
            private String brand;
            private String category;
            @JsonProperty(value = AppConstants.LAST_FOUR)
            private String lastFour;
            private String name;
            private Expiry expiry;
            private String funding;

            @Data
            @Builder
            public static class Expiry {
                private String month;
                private String year;
            }
        }
    }

    @Data
    @Builder
    public static class Routing {
        private String provider;
        @JsonProperty(value = AppConstants.TERMINAL_ID)
        private String terminalId;
    }

    @Data
    @Builder
    public static class Authentication {
        private AuthenticationChannel channel;
        private AuthenticationPurpose purpose;
    }

    @Data
    @Builder
    public static class Airline {
        private String id;
    }

}

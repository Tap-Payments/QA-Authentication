package com.tappayments.automation.qaauthentication.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.tappayments.automation.qaauthentication.model.enums.TransactionType;
import com.tappayments.automation.qaauthentication.utils.AppConstants;
import lombok.Builder;
import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
@Builder
public class TransactionRequest {

    private Double amount;
    private String currency;
    @JsonProperty(value = AppConstants.SAVE_CARD)
    private Boolean saveCard;
    private Object description;
    private Transaction transaction;
    private Reference reference;
    private Invoice invoice;
    private Customer customer;
    private Device device;
    private Source source;
    private Routing routing;
    private Authentication authentication;
    private Merchant merchant;
    @JsonProperty(value = AppConstants.PAYMENT_AGREEMENT)
    private PaymentAgreement paymentAgreement;
    private Map<String, String> metadata;
    private Airline airline;
    private Redirect redirect;
    private Post post;
    
    @Data
    @Builder
    public static class Transaction {
        private String id;
        private TransactionType type;
    }

    @Data
    @Builder
    public static class Reference {
        private String transaction;
        private String order;
    }

    @Data
    @Builder
    public static class Invoice {
        private String id;
    }

    @Data
    @Builder
    public static class Customer {
        private String id;
        private List<Name> name;
        @JsonProperty(value = AppConstants.NAME_ON_CARD)
        private String nameOnCard;
        private String email;
        private Phone phone;

        @Data
        @Builder
        public static class Name {
            @JsonProperty(value = AppConstants.FIRST_NAME)
            private String firstName;
            @JsonProperty(value = AppConstants.MIDDLE_NAME)
            private String middleName;
            @JsonProperty(value = AppConstants.LAST_NAME)
            private String lastName;
            private String locale;
        }

        @Data
        @Builder
        public static class Phone {
            @JsonProperty(value = AppConstants.COUNTRY_CODE)
            private String countryCode;
            private String number;
        }
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
        private String channel;
        private String purpose;
    }

    @Data
    @Builder
    public static class Merchant {
        private String id;
    }

    @Data
    @Builder
    public static class PaymentAgreement {
        private String id;
        private String type;
        private Contract contract;
        @JsonProperty(value = AppConstants.VARIABLE_AMOUNT)
        private VariableAmount variableAmount;
        @JsonProperty(value = AppConstants.SCHEDULED_PAYMENT)
        private ScheduledPayments scheduledPayments;

        @Data
        @Builder
        public static class Contract {
            private String id;
            private String type;
            private Period period;

            @Data
            @Builder
            public static class Period {
                @JsonProperty(value = AppConstants.START_DATE)
                private long startDate;
                @JsonProperty(value = AppConstants.END_DATE)
                private long endDate;
                @JsonProperty(value = AppConstants.AUTO_RENEWAL)
                private boolean autoRenewal;
            }
        }

        @Data
        @Builder
        public static class VariableAmount {
            private String id;
            @JsonProperty(value = AppConstants.MAXIMUM_AMOUNT)
            private double maximumAmount;
        }

        @Data
        @Builder
        public static class ScheduledPayments {
            private String id;
            private int count;
            private Frequency frequency;

            @Data
            @Builder
            public static class Frequency {
                private String period;
                private int count;
            }
        }
    }

    @Data
    @Builder
    public static class Airline {
        private String id;
    }

    @Data
    @Builder
    public static class Redirect {
        private String url;
    }

    @Data
    @Builder
    public static class Post {
        private String url;
    }

}

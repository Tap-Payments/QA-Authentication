package com.tappayments.automation.qaauthentication.model;

import lombok.Builder;
import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
@Builder
public class TransactionRequest {

    private double amount;
    private String currency;
    private Boolean saveCard;
    private String description;
    private Transaction transaction;
    private Reference reference;
    private Invoice invoice;
    private Customer customer;
    private Device device;
    private Source source;
    private Routing routing;
    private Authentication authentication;
    private Merchant merchant;
    private PaymentAgreement paymentAgreement;
    private Map<String, String> metadata;
    private Airline airline;
    private Redirect redirect;
    private Post post;
    
    @Data
    @Builder
    public static class Transaction {
        private String id;
        private String type;
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
        private String nameOnCard;
        private String email;
        private Phone phone;

        @Data
        @Builder
        public static class Name {
            private String firstName;
            private String middleName;
            private String lastName;
            private String locale;
        }

        @Data
        @Builder
        public static class Phone {
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
            private int screenHeight;
            private int screenWidth;
            private String language;
            private int colorDepth;
            private String javaEnabled;
            private String javaScriptEnabled;
            private int timeZone;
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
            private String firstSix;
            private String scheme;
            private String brand;
            private String category;
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
        private VariableAmount variableAmount;
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
                private long startDate;
                private long endDate;
                private boolean autoRenewal;
            }
        }

        @Data
        @Builder
        public static class VariableAmount {
            private String id;
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

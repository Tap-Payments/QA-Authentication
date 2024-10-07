package com.tappayments.automation.qaauthentication.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.tappayments.automation.qaauthentication.utils.AppConstants;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.SuperBuilder;

import java.util.List;
import java.util.Map;

@Data
@SuperBuilder
public class TransactionAuthentication {

    private Double amount;
    private String currency;
    @JsonProperty(value = AppConstants.SAVE_CARD)
    private Boolean saveCard;
    private Object description;
    private Map<String, String> metadata;
    private Reference reference;
    private Customer customer;
    private Merchant merchant;
    private Post post;
    private Redirect redirect;

    @Data
    @Builder
    public static class Reference {
        private String transaction;
        private String order;
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
    public static class Merchant {
        private String id;
    }

    @Data
    @Builder
    public static class Post {
        private String url;
    }

    @Data
    @Builder
    public static class Redirect {
        private String url;
    }

}

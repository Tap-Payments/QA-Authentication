package com.tappayments.automation.qaauthentication.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CardRequest {
    private Card card;
    private String client_ip;

    @Data
    @Builder
    public static class Card {
        private long number;
        private int exp_month;
        private int exp_year;
        private int cvc;
        private String name;
        private Address address;

        @Data
        @Builder
        public static class Address {
            private String country;
            private String line1;
            private String city;
            private String street;
            private String avenue;
        }
    }
}
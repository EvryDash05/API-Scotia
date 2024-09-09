package com.example.api_scotia.commons.enums;

import lombok.Getter;

@Getter
public enum Identifier {
    CUSTOMER("CST"),
    CARD("CRD"),
    CARD_TYPE("CRT"),
    LOAN("LON"),
    CUSTOMER_PAY("CSP"),
    TRANSACTION("TRS"),
    FINANCIAL_INFO("INF");

    private final String value;

    Identifier(String value) {
        this.value = value;
    }
}

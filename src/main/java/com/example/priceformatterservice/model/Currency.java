package com.example.priceformatterservice.model;

import lombok.Getter;

@Getter
public enum Currency {
    USD("$", false),
    ILS("₪", true);

    private final String symbol;
    private final boolean symbolBeforeValue;

    Currency(String symbol, boolean symbolBeforeValue) {
        this.symbol = symbol;
        this.symbolBeforeValue = symbolBeforeValue;
    }
}
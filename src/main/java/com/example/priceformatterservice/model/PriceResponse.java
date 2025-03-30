package com.example.priceformatterservice.model;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class PriceResponse {
    private BigDecimal value;
    private String valueWithCurrency;
    private String valueWithoutCurrency;
    private BigDecimal netValue;
    private BigDecimal vatAmount;
}


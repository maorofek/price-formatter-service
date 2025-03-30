package com.example.priceformatterservice.model;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class PriceRequest {

    @NotBlank
    private String amount;

    private Currency currency;
}
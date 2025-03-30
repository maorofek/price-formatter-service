package com.example.priceformatterservice.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PriceRequest {

    @Positive
    private Integer amount;

    private Currency currency;
}
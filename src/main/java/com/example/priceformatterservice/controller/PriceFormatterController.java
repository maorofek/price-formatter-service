package com.example.priceformatterservice.controller;

import com.example.priceformatterservice.model.PriceRequest;
import com.example.priceformatterservice.model.PriceResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.example.priceformatterservice.service.PriceFormatterService;

import java.util.List;

@RestController
@RequestMapping("/api")
public class PriceFormatterController {

    private final PriceFormatterService priceFormatterService;

    @Autowired
    public PriceFormatterController(PriceFormatterService service) {
        this.priceFormatterService = service;
    }

    @GetMapping("/currencies")
    public List<String> getCurrencies() {
        return priceFormatterService.getSupportedCurrencies();
    }

    @PostMapping("/format-price")
    public PriceResponse formatPrice(@RequestBody @Valid PriceRequest request) {
        return priceFormatterService.formatPrice(request.getAmount(), request.getCurrency());
    }
}

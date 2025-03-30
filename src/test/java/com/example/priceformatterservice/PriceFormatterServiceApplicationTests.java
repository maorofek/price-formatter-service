package com.example.priceformatterservice;

import com.example.priceformatterservice.controller.PriceFormatterController;
import com.example.priceformatterservice.model.Currency;
import com.example.priceformatterservice.model.PriceRequest;
import com.example.priceformatterservice.model.PriceResponse;
import com.example.priceformatterservice.service.PriceFormatterService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class PriceFormatterServiceApplicationTests {

    private PriceFormatterController controller;

    @BeforeEach
    void setUp() {
        controller = new PriceFormatterController(new PriceFormatterService());
    }

    @Test
    void testGetCurrencies() {
        List<String> currencies = controller.getCurrencies();
        assertNotNull(currencies);
        assertEquals(List.of("USD", "ILS"), currencies);
    }

    @Test
    void testFormatPriceValidUSD() {
        PriceRequest request = new PriceRequest(123, Currency.USD);
        PriceResponse response = controller.formatPrice(request);
        assertNotNull(response);

        assertEquals(BigDecimal.valueOf(1.23), response.getValue());
        assertEquals("1.23$", response.getValueWithCurrency());
        assertEquals("1.23", response.getValueWithoutCurrency());
        assertEquals(BigDecimal.valueOf(1.03), response.getNetValue());
        assertEquals(new BigDecimal("0.20"), response.getVatAmount());

    }

    @Test
    void testFormatPriceValidILS() {
        PriceRequest request = new PriceRequest(988, Currency.ILS);
        PriceResponse response = controller.formatPrice(request);
        assertNotNull(response);
        assertEquals(BigDecimal.valueOf(9.88), response.getValue());
        assertEquals("₪9.88", response.getValueWithCurrency());
        assertEquals("9.88", response.getValueWithoutCurrency());
        assertEquals(new BigDecimal("8.30"), response.getNetValue());
        assertEquals(BigDecimal.valueOf(1.58), response.getVatAmount());
    }
}
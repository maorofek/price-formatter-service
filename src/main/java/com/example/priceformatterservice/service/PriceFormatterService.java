package com.example.priceformatterservice.service;

import com.example.priceformatterservice.model.Currency;
import com.example.priceformatterservice.model.PriceResponse;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

@Service
public class PriceFormatterService {

    private static final BigDecimal VAT_RATE = BigDecimal.valueOf(1.19);

    public List<String> getSupportedCurrencies() {
        return Arrays.stream(Currency.values())
                .map(Enum::name)
                .toList();
    }

    public PriceResponse formatPrice(Integer amountStr, Currency currency) {
        BigDecimal fullPrice = new BigDecimal(amountStr).divide(BigDecimal.valueOf(100), 2, RoundingMode.HALF_UP);
        BigDecimal net = fullPrice.divide(VAT_RATE, 2, RoundingMode.HALF_UP);
        BigDecimal vat = fullPrice.subtract(net).setScale(2, RoundingMode.HALF_UP);

        return PriceResponse.builder()
                .value(fullPrice.setScale(2, RoundingMode.HALF_UP))
                .valueWithCurrency(formatCurrency(fullPrice, currency))
                .valueWithoutCurrency(formatWithoutCurrency(fullPrice))
                .netValue(net.setScale(2, RoundingMode.HALF_UP))
                .vatAmount(vat)
                .build();
    }


    private String formatCurrency(BigDecimal value, Currency currency) {
        String formatted = formatWithSeparators(value);
        return currency.isSymbolBeforeValue()
                ? currency.getSymbol() + formatted
                : formatted + currency.getSymbol();
    }

    private String formatWithoutCurrency(BigDecimal value) {
        return formatWithSeparators(value);
    }

    private String formatWithSeparators(BigDecimal value) {
        DecimalFormat formatter = new DecimalFormat("#,##0.##");
        formatter.setDecimalFormatSymbols(DecimalFormatSymbols.getInstance(Locale.US));
        return formatter.format(value);
    }
}

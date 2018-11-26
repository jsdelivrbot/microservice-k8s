package com.epam.kafka.events.account;

import lombok.Builder;
import lombok.Value;

import java.math.BigDecimal;

@Value
@Builder
public class AccountBalance {

    private BigDecimal amount;
    private String currencyIsoCode;
}

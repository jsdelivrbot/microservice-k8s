package com.epam.kafka.events.account;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class AccountChangedEvent {

    private String id;
    private String owner;
    private String currencyIsoCode;
    private AccountBalance balance;
}

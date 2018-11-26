package com.epam.kafka.events.account;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class AccountDeletedEvent {

    private String id;
}

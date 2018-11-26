package com.epam.kafka.events.alfa;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class AlfaChangedEvent {

    private Long id;
    private String description;
    private String betaId;

}

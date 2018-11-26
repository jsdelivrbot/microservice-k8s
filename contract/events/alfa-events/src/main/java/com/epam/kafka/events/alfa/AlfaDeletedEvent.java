package com.epam.kafka.events.alfa;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class AlfaDeletedEvent {

    private Long id;

}

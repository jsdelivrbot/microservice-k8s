package com.epam.rulerunner.order.converter;

import com.epam.rulerunner.order.event.FixEvent;
import com.epam.rulerunner.order.model.FixMessage;
import org.springframework.core.convert.converter.Converter;

public class FixEventToFixMessageConverter implements Converter<FixEvent, FixMessage> {

    @Override
    public FixMessage convert(FixEvent event) {
        FixMessage message = new FixMessage();
        message.setRawFixMessage(event.getFixMessage());
        return message;
    }

}

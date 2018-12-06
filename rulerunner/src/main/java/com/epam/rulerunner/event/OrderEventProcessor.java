package com.epam.rulerunner.event;

import com.epam.rulerunner.event.consumer.EventProcessor;
import com.epam.rulerunner.event.model.Event;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class OrderEventProcessor implements EventProcessor<OrderEvent> {

    private final static Logger LOG = LoggerFactory.getLogger(OrderEventProcessor.class);

//    private AddressRepository repository;
//    private AddressTransformer transformer;

    public OrderEventProcessor(/*AddressRepository repository, AddressTransformer transformer*/) {
//        this.repository = repository;
//        this.transformer = transformer;
    }

    @Override
    public void process(Event event, OrderEvent payload) {
//        Address address = transformer.transform(payload);
//        updateStandardAddressToFalse(address);
//        repository.save(address);
        LOG.info("Saving modified address to DB: {}", payload
        );
    }
}

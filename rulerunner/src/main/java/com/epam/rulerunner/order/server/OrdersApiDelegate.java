package com.epam.rulerunner.order.server;

import com.epam.rulerunner.order.model.Order;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

/**
 * A delegate to be called by the {@link OrdersApiController}}.
 * Implement this interface with a {@link org.springframework.stereotype.Service} annotated class.
 */

public interface OrdersApiDelegate {

    Logger log = LoggerFactory.getLogger(OrdersApi.class);

    default Optional<ObjectMapper> getObjectMapper() {
        return Optional.empty();
    }

    default Optional<HttpServletRequest> getRequest() {
        return Optional.empty();
    }

    default Optional<String> getAcceptHeader() {
        return getRequest().map(r -> r.getHeader("Accept"));
    }

    /**
     * @see OrdersApi#sendOrder
     */
    default ResponseEntity<Void> sendOrder( Order body) {
        if(getObjectMapper().isPresent() && getAcceptHeader().isPresent()) {
        } else {
            log.warn("ObjectMapper or HttpServletRequest not configured in default OrdersApi interface so no example is generated");
        }
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }

    /**
     * @see OrdersApi#getOrders
     */
    default ResponseEntity<Order> getOrders(String orderId) {
        if(getObjectMapper().isPresent() && getAcceptHeader().isPresent()) {
            if (getAcceptHeader().get().contains("application/json")) {
                try {
                    return new ResponseEntity<>(getObjectMapper().get().readValue("{  \"expiryDate\" : \"expiryDate\",  \"accountId\" : {    \"accountNumber\" : \"accountNumber\"  },  \"serialNumber\" : \"serialNumber\",  \"mainAccount\" : \"mainAccount\",  \"mailAddress\" : {    \"Street2\" : \"Street2\",    \"Street1\" : \"Street1\",    \"State\" : \"State\",    \"Country\" : \"Country\",    \"City\" : \"City\"  },  \"invoiceAddress\" : {    \"Street2\" : \"Street2\",    \"Street1\" : \"Street1\",    \"State\" : \"State\",    \"Country\" : \"Country\",    \"City\" : \"City\"  },  \"pinAddress\" : {    \"Street2\" : \"Street2\",    \"Street1\" : \"Street1\",    \"State\" : \"State\",    \"Country\" : \"Country\",    \"City\" : \"City\"  },  \"eActivation\" : true,  \"activated\" : true}", Order.class), HttpStatus.NOT_IMPLEMENTED);
                } catch (IOException e) {
                    log.error("Couldn't serialize response for content type application/json", e);
                    return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
                }
            }
        } else {
            log.warn("ObjectMapper or HttpServletRequest not configured in default OrdersApi interface so no example is generated");
        }
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }

    /**
     * @see OrdersApi#getOrders
     */
    default ResponseEntity<List<Order>> getOrders() {
        if(getObjectMapper().isPresent() && getAcceptHeader().isPresent()) {
            if (getAcceptHeader().get().contains("application/json")) {
                try {
                    return new ResponseEntity<>(getObjectMapper().get().readValue("[ {  \"clientId\" : \"clientId\",  \"lastFourDigits\" : \"lastFourDigits\",  \"ownerId\" : \"ownerId\"}, {  \"clientId\" : \"clientId\",  \"lastFourDigits\" : \"lastFourDigits\",  \"ownerId\" : \"ownerId\"} ]", List.class), HttpStatus.NOT_IMPLEMENTED);
                } catch (IOException e) {
                    log.error("Couldn't serialize response for content type application/json", e);
                    return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
                }
            }
        } else {
            log.warn("ObjectMapper or HttpServletRequest not configured in default OrdersApi interface so no example is generated");
        }
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }
}

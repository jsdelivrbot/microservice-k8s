package com.epam.event.producer;

import com.epam.event.model.Event;
import com.epam.event.model.EventHeader;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class JsonEventProducerTest {

    public static final String JSON_STRING = "JsonString";
    public static final String PAYLOAD_TYPE = "TestPayload";
    private static final String SERVICE_NAME = "TestService";
    private static final String UUID = "generated-uuid";

    @Mock
    private MessageSender messageSender;

    @Mock
    private UuidGenerator uuidGenerator;

    private EventProducer eventProducer;

    private TestPayload testPayload;

    private ObjectMapper objectMapper = new ObjectMapper();

    @Before
    public void setUp() {
        Mockito.when(uuidGenerator.generateUuid()).thenReturn(UUID);
        eventProducer = new JsonEventProducer(messageSender, SERVICE_NAME, uuidGenerator, objectMapper);
        testPayload = new TestPayload();
        testPayload.setFieldA("A");
        testPayload.setFieldB(1);
        testPayload.setFieldC(2.0);
        testPayload.setAnotherField("B");
    }

    @Test
    public void whenConstructedWithNullMessageSenderThenIllegalArgumentExceptionIsThrown() {
        try {
            new JsonEventProducer(null, SERVICE_NAME, uuidGenerator, objectMapper);
        } catch (NullPointerException e) {
            Assert.assertEquals("messageSender must not be null", e.getMessage());
        }
    }

    @Test
    public void whenConstructedWithNullServiceIdThenIllegalArgumentExceptionIsThrown() {
        try {
            new JsonEventProducer(messageSender, null, uuidGenerator, objectMapper);
        } catch (NullPointerException e) {
            Assert.assertEquals("serviceId must not be null", e.getMessage());
        }
    }

    @Test
    public void whenConstructedWithEmptyServiceIdThenIllegalArgumentExceptionIsThrown() {
        try {
            new JsonEventProducer(messageSender, "   ", uuidGenerator, objectMapper);
        } catch (IllegalArgumentException e) {
            Assert.assertEquals("serviceId must not be null", e.getMessage());
        }
    }

    @Test
    public void whenConstructedWithNullUuidGeneratorThenIllegalArgumentExceptionIsThrown() {
        try {
            new JsonEventProducer(messageSender, SERVICE_NAME, null, objectMapper);
        } catch (NullPointerException e) {
            Assert.assertEquals("uuidGenerator must not be null", e.getMessage());
        }
    }

    @Test
    public void whenSendCalledWithNullThenIllegalArgumentExceptionIsThrown() {
        try {
            eventProducer.send(null);
        } catch (NullPointerException e) {
            Assert.assertEquals("payload must not be null", e.getMessage());
        }
    }

    @Test
    public void whenSendCalledThenExpectedMessageIsSent() {
        // Given
        String expected = "{\"eventHeader\":{\"eventId\":\"TestService-generated-uuid\",\"eventType\":\"TestPayload\",\"payloadFormat\":\"JsonString\"},\"payload\":\"{\\\"fieldA\\\":\\\"A\\\",\\\"fieldB\\\":1,\\\"fieldC\\\":2.0,\\\"anotherField\\\":\\\"B\\\"}\"}";

        // When
        eventProducer.send(testPayload);

        // Then
        Mockito.verify(messageSender).send(expected);
    }

    @Test
    public void whenSendCalledThenExpectedEventIsReturned() {
        // Given
        EventHeader eventHeader = new EventHeader(SERVICE_NAME + "-" + UUID, PAYLOAD_TYPE, JSON_STRING);
        String payload = "{\"fieldA\":\"A\",\"fieldB\":1,\"fieldC\":2.0,\"anotherField\":\"B\"}";
        Event expected = new Event(eventHeader, payload);

        // When
        Event actual = eventProducer.send(testPayload);

        // Then
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void whenSendCalledWithMissingFieldsThenMessageContainsDefault() {
        // Given
        TestPayload customTestPayload = new TestPayload();
        customTestPayload.setFieldC(2.0);
        customTestPayload.setAnotherField("B");
        String expected = "{\"eventHeader\":{\"eventId\":\"TestService-generated-uuid\",\"eventType\":\"TestPayload\",\"payloadFormat\":\"JsonString\"},\"payload\":\"{\\\"fieldA\\\":null,\\\"fieldB\\\":0,\\\"fieldC\\\":2.0,\\\"anotherField\\\":\\\"B\\\"}\"}";;

        // When
        eventProducer.send(customTestPayload);

        // Then
        Mockito.verify(messageSender).send(expected);
    }

    private class TestPayload {
        private String fieldA;
        private int fieldB;
        private double fieldC;
        private String anotherField;

        public TestPayload() {
        }

        public String getFieldA() {
            return fieldA;
        }

        public void setFieldA(String fieldA) {
            this.fieldA = fieldA;
        }

        public int getFieldB() {
            return fieldB;
        }

        public void setFieldB(int fieldB) {
            this.fieldB = fieldB;
        }

        public double getFieldC() {
            return fieldC;
        }

        public void setFieldC(double fieldC) {
            this.fieldC = fieldC;
        }

        public String getAnotherField() {
            return anotherField;
        }

        public void setAnotherField(String anotherField) {
            this.anotherField = anotherField;
        }
    }

}
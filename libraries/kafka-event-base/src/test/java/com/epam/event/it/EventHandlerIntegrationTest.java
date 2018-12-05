package com.epam.event.it;

import com.epam.event.consumer.*;
import com.epam.event.model.Event;
import com.epam.event.producer.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Assert;
import org.junit.Test;

import java.util.*;

public class EventHandlerIntegrationTest {

    private ObjectMapper objectMapper = new ObjectMapper();

    @Test
    public void testJsonStringMessaging() {
        // Store for actual results
        TestJsonStringMessagingReceivedMessages actual = new TestJsonStringMessagingReceivedMessages();

        // Setup services
        TestObject1EventProcessor testObject1EventProcessor = new TestObject1EventProcessor(actual);
        AnotherTestObjectEventProcessor anotherTestObjectEventProcessor = new AnotherTestObjectEventProcessor(actual);

        MessageReceiver messageReceiver = createMessageReceiver(testObject1EventProcessor, anotherTestObjectEventProcessor);

        MessageSender messageSender = new MessageConduit(messageReceiver);
        EventProducer eventProducer = createEventProducer(messageSender, "IntegrationTestService");

        // Messages
        TestObject1 message1 = new TestObject1("a", 1);
        TestObject2 message2 = new TestObject2("b", "c");
        TestObject1 message3 = new TestObject1("d", 2);
        AnotherTestObject message4 = new AnotherTestObject("e", "f");
        TestObject1 message5 = new TestObject1("f", 4);

        // Set expected results
        TestJsonStringMessagingReceivedMessages expected = new TestJsonStringMessagingReceivedMessages();
        expected.testObject1List.add(message1);
        expected.testObject1List.add(message3);
        expected.testObject1List.add(message5);
        expected.anotherTestObjects.add(message4);

        // Sending messages
        eventProducer.send(message1);
        eventProducer.send(message2);
        eventProducer.send(message3);
        eventProducer.send(message4);
        eventProducer.send(message5);

        // Validate result
        Assert.assertEquals(expected.anotherTestObjects, actual.anotherTestObjects);
        Assert.assertEquals(expected.testObject1List, actual.testObject1List);
        Assert.assertEquals(expected.testObject2List, actual.testObject2List);
    }

    private EventProducer createEventProducer(MessageSender messageSender, String serviceName) {
        UuidGenerator uuidGenerator = new RandomUuidGenerator();

        return new JsonEventProducer(messageSender, serviceName, uuidGenerator, objectMapper);
    }

    private MessageReceiver createMessageReceiver(TestObject1EventProcessor testObject1EventProcessor,
                                                  AnotherTestObjectEventProcessor anotherTestObjectEventProcessor) {
        // Adding event processors for routing
        Map<Class, EventHandler> eventProcessorMap = new HashMap<>();
        eventProcessorMap.put(TestObject1.class, new JsonEventProcessorDelegate(TestObject1.class, testObject1EventProcessor, objectMapper));
        eventProcessorMap.put(AnotherTestObject.class, new JsonEventProcessorDelegate(AnotherTestObject.class, anotherTestObjectEventProcessor, objectMapper));

        // Create whitelist based on routing
        Set<Class> whitelistedClasses = eventProcessorMap.keySet();

        // Create router and filter
        EventHandler routingEventHandler = new ClassSimpleNameRoutingEventHandler(eventProcessorMap);
        EventFilter eventFilter = new ClassSimpleNameWhitelistEventFilter(whitelistedClasses);

        // Create message receiver
        return new JsonMessageReceiver(eventFilter, routingEventHandler, objectMapper);
    }

    private class MessageConduit implements MessageSender {

        private final MessageReceiver messageReceiver;

        public MessageConduit(MessageReceiver messageReceiver) {
            this.messageReceiver = messageReceiver;
        }

        @Override
        public void send(String message) {
            messageReceiver.receive(message);
        }
    }

    private class TestJsonStringMessagingReceivedMessages {
        private List<TestObject1> testObject1List = new ArrayList<>();
        private List<TestObject2> testObject2List = new ArrayList<>();
        private List<AnotherTestObject> anotherTestObjects = new ArrayList<>();
    }

    private class TestObject1EventProcessor implements EventProcessor<TestObject1> {

        private final TestJsonStringMessagingReceivedMessages receivedMessages;

        public TestObject1EventProcessor(TestJsonStringMessagingReceivedMessages receivedMessages) {
            this.receivedMessages = receivedMessages;
        }

        @Override
        public void process(Event event, TestObject1 payload) {
            receivedMessages.testObject1List.add(payload);
        }
    }

    private class AnotherTestObjectEventProcessor implements EventProcessor<AnotherTestObject> {

        private final TestJsonStringMessagingReceivedMessages receivedMessages;

        public AnotherTestObjectEventProcessor(TestJsonStringMessagingReceivedMessages receivedMessages) {
            this.receivedMessages = receivedMessages;
        }

        @Override
        public void process(Event event, AnotherTestObject payload) {
            receivedMessages.anotherTestObjects.add(payload);
        }
    }

}

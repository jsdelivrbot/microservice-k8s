# EPAM Event Handler Library

Library for sending and processing messages from a message broker. It can handle event event metadata generation, filtering, payload encryption,  

## Usage
Sample use cases for using the library can be found in *EventHandlerIntegrationTest* class.

## Encryption
Event encryption support is built in the library, but there is no actual implementation for encryption handling. For add encryption handling *com.epam.event.encryption.MessageEncryptor* needs to be implemented as a separate library or in the application that is using the event handling lib. 

## Model
Event and EventHeader class can be found in *com.epam.event.model* package. This two classes are the bases of the message handling.

## Event Consumer
Interfaces for receiving and processing events can be found in the *com.epam.event.consumer* package.

### Interface
- *MessageReceiver* - Entry point to the messing processing chain. Message listener needs to delegate the received string to this interface.
- *EventHandler* - Payload string handler that can process the message payload.
- *EventFilter* - Filter for processing or ignoring the event.
- *EventProcessor* - Processing a payload object, that is already parsed and casted to Java type.

### Classes
- *WhitelistEventFilter, ClassSimpleNameWhitelistEventFilter* - Accepts an event based on its type in the metadata. Can handle strings or class names (by converting them to string). Whitelist filtering, so only accepts those inputs that were provided upfront.
- *JsonMessageReceiver* - Receives string messages from message broker, parse the event with its metadata and runs filtering. If needs to be processed then maps the payload (e.g.: decrypting) and pass it to an *EventHandler*. 
- *RoutingEventHandler, ClassSimpleNameRoutingEventHandler* - Routes the message based on the *eventType* metadata field. Can route based on predefined strings or class names (by converting them to string).
- *JsonEventProcessorDelegate* - Message handler that parse and casts messages to Java types, so it can be processed by an *EventProcessor*.
- *EventProcessor* - Entry point for the message processing application, to process the event.
- *EncryptedJsonMessageReceiver* - Event receiver that can handle encrypted payload.

### Configuration
For creating an instance of the event producer service, the following steps are required:
- Provide implementation for *EventProcessor* interfaces for each message type that needs to be processed.
- Instantiate an implementation for *JsonMessageReceiver*. A sample can be found in *EventHandlerIntegrationTest#createMessageReceiver* method.
- Implement a string message listener that receives string messages (e.g.: listens on strings from kafka topic).
- Inject *JsonMessageReceiver* into your implementation of string message listener.

## Event producer
Interfaces for producing and sending events can be found in the *com.epam.event.producer* package.

### Interface
- *EventProducer* - Main entry point for sending events. Wraps payload into events, fires event and returns the sent event. Main entry point for message producing.
- *MessageSender* - Needs to be implement to send string message (JSON) to the message broker.
- *UuidGenerator* - ID generator for generating event ID, so messages can be traced.

### Classes
- *JsonEventProducer* - Generates a JSON event by creating a JSON from the payload and wrapping it in an event. Event metadata is also generated. Finally the Event is converted to JSON and sent by the *MessageSender*.
- *RandomUuidGenerator* - Default UUID generator that returns a random UUID in string format.
- *PayloadStringWithFormat* - Helper class for passing a payload together with its format.
- *EncryptedJsonEventProducer* - Event producer that can handle encrypted payload.

### Configuration
For creating an instance of the event producer service, the following steps are required:
- Provide implementation for *MessageSender* interface (e.g.: sends the received string to a kafka topic).
- Instantiate an implementation for *EventProducer*. A sample can be found in *EventHandlerIntegrationTest#createEventProducer* method.
- Inject *EventProducer* into your implementation for firing events.
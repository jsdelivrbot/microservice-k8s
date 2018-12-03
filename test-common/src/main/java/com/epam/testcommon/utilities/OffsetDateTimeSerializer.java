package com.epam.testcommon.utilities;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;

import static java.time.format.DateTimeFormatter.ofPattern;

public class OffsetDateTimeSerializer extends JsonSerializer<OffsetDateTime> {

    DateTimeFormatter formatter;

    public OffsetDateTimeSerializer(String pattern) {
        formatter = ofPattern(pattern);
    }

    @Override
    public void serialize(OffsetDateTime value, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeString(value.format(formatter));
    }
}
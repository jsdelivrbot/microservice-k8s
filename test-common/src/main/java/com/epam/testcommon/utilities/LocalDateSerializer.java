package com.epam.testcommon.utilities;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static java.time.format.DateTimeFormatter.ofPattern;

public class LocalDateSerializer extends JsonSerializer<LocalDate> {

    DateTimeFormatter formatter;

    public LocalDateSerializer(String pattern) {
        formatter = ofPattern(pattern);
    }

    @Override
    public void serialize(LocalDate value, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeString(value.format(formatter));
    }
}
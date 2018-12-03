package com.epam.testcommon.utilities;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.time.LocalDate;
import java.time.OffsetDateTime;

public class JsonToStringUtilities {

    private static JavaTimeModule javaTimeModule = new JavaTimeModule();
    private static boolean dateFormatSet = false;

    public static void setupLocalDateTimeWithFormat(String patten) {
        dateFormatSet = true;
        javaTimeModule.addSerializer(LocalDate.class, new LocalDateSerializer(patten));
    }

    public static void setupOffsetDateTimeWithFormat(String patten) {
        dateFormatSet = true;
        javaTimeModule.addSerializer(OffsetDateTime.class, new OffsetDateTimeSerializer(patten));
    }

    public static String objectToJsonString(Object object) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        if (dateFormatSet) {
            objectMapper.registerModule(javaTimeModule);
        }
        return objectMapper.writeValueAsString(object);
    }
}

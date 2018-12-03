package com.epam.testcommon.utilities;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

public class StringToJsonUtilities {

    public static RequestSpecification getJsonBodyFromString(String jsonBody) {
        RequestSpecBuilder requestSpecBuilder = new RequestSpecBuilder();
        requestSpecBuilder.setBody(jsonBody);
        requestSpecBuilder.setContentType(ContentType.JSON);
        return requestSpecBuilder.build();
    }
}

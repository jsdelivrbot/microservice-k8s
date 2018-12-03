package com.epam.testcommon.utilities;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.json.JSONException;
import org.skyscreamer.jsonassert.JSONAssert;
import org.skyscreamer.jsonassert.JSONCompareMode;

public class JsonValidatorUtilities {

    public static void validateJson(Object expectedResponse, String actualResponse) throws JsonProcessingException, JSONException {
        validateJson(JsonToStringUtilities.objectToJsonString(expectedResponse), actualResponse);
    }

    public static void validateJson(String expectedResponse, String actualResponse) throws JSONException {
        JSONAssert.assertEquals(expectedResponse, actualResponse, JSONCompareMode.NON_EXTENSIBLE);
    }
}

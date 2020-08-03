package com.cucumber.testng.application_utils.api_utils;

import com.google.gson.*;
import com.google.gson.internal.bind.TypeAdapters;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import io.restassured.response.Response;

import java.io.IOException;
import java.lang.reflect.Type;

public class APIUtils {
    public static final TypeAdapter<Number> UNRELIABLE_INTEGER = new TypeAdapter<Number>() {
        @Override
        public Number read(JsonReader in) throws IOException {
            JsonToken jsonToken = in.peek();
            switch (jsonToken) {
                case NUMBER:
                case STRING:
                    String s = in.nextString();
                    try {
                        return Integer.parseInt(s);
                    } catch (NumberFormatException ignored) {
                    }
                    try {
                        return (int)Double.parseDouble(s);
                    } catch (NumberFormatException ignored) {
                    }
                    return null;
                case NULL:
                    in.nextNull();
                    return null;
                case BOOLEAN:
                    in.nextBoolean();
                    return null;
                default:
                    throw new JsonSyntaxException("Expecting number, got: " + jsonToken);
            }
        }
        @Override
        public void write(JsonWriter out, Number value) throws IOException {
            out.value(value);
        }
    };
    public static final TypeAdapterFactory UNRELIABLE_INTEGER_FACTORY = TypeAdapters.newFactory(int.class, Integer.class, UNRELIABLE_INTEGER);
}

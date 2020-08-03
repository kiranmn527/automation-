package com.cucumber.testng.application_utils.api_utils;

import io.restassured.RestAssured;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.apache.commons.io.output.WriterOutputStream;

import java.io.PrintStream;
import java.io.StringWriter;

import static com.cucumber.testng.project_constants.constants.Settings.*;
import static io.restassured.RestAssured.given;

public class APIHelper {

    private static ThreadLocal<StringWriter> reponseWriterList = new ThreadLocal<>();

    public static StringWriter getResponseWriterList() {
        return reponseWriterList.get();
    }

    public static void setResponseWriterList(StringWriter errorWriter) {
        reponseWriterList.set(errorWriter);
    }

    public static RequestSpecification givenConfig() {
        setBaseConstants();
        RestAssured.useRelaxedHTTPSValidation();
        StringWriter errorWriter = new StringWriter();
        setResponseWriterList(errorWriter);
        return given()
                .filter(new ResponseLoggingFilter(new PrintStream(new WriterOutputStream(getResponseWriterList()), true)))
                .contentType(ContentType.JSON)
                .auth()
                .oauth(ConsumerKey, ConsumerSecret, Token, TokenSecrete)
                .log()
                .all();
    }

    private static void setBaseConstants() {
        RestAssured.baseURI = TwitterBaseURI;
        RestAssured.basePath = TwitterBasePath;
    }

    public static boolean isThisARestAPITest() {
        if (getResponseWriterList() != null) {
            return true;
        } else {
            return false;
        }
    }
}

package com.cucumber.testng.page_objects.api;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.io.PrintStream;
import java.io.StringWriter;

import static com.cucumber.testng.application_utils.api_utils.APIHelper.givenConfig;
import static com.cucumber.testng.project_constants.constants.Settings.*;
import static com.cucumber.testng.project_constants.constants.Settings.TokenSecrete;
import static io.restassured.RestAssured.given;
import org.apache.commons.io.output.WriterOutputStream;

public class TwitterPage {

    public RequestSpecification login() {
        return givenConfig();
    }

    public Response twiteThisStatus(RequestSpecification requestSpecification, String twite) {
        return requestSpecification
                .queryParam("status", twite)
                .when()
                .post("/update.json")
                .then()
                .extract().response();
    }

    public Response getAllTwites(RequestSpecification requestSpecification, String screenName, int count) {
        return requestSpecification
                .queryParam("screen_name", screenName)
                .queryParam("count", count)
                .when()
                .get("/home_timeline.json")
                .then()
                .extract().response();
    }
}

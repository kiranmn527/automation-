package com.cucumber.testng.action;

import com.cucumber.testng.model.CreateTwiteResponse;
import com.cucumber.testng.model.GetAllTwitesResponse;
import com.cucumber.testng.page_objects.api.TwitterPage;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.lang.reflect.Type;
import java.util.Collection;

import static com.cucumber.testng.application_utils.api_utils.APIUtils.UNRELIABLE_INTEGER_FACTORY;

public class TwitterActions {

    private TwitterPage twitterPage;
    private RequestSpecification requestSpecification;
    private Response response;
    private Gson gson;

    public TwitterActions(TwitterPage twitterPage) {
        this.twitterPage = twitterPage;
        this.gson = new GsonBuilder()
                .registerTypeAdapterFactory(UNRELIABLE_INTEGER_FACTORY)
                .create();
        //this.requestSpecification = requestSpecification;
    }

    public void launchTwitter() {
        requestSpecification = twitterPage.login();
    }

    public CreateTwiteResponse twite(String twite) {
        response = twitterPage.twiteThisStatus(requestSpecification, twite);
        return gson.fromJson(response.asString(), CreateTwiteResponse.class);
    }

    public Collection<GetAllTwitesResponse> getAllTwitesFromMyScreen(String screenName, int count) {
        response = twitterPage.getAllTwites(requestSpecification, screenName, count);
        Type collectionType = new TypeToken<Collection<GetAllTwitesResponse>>(){}.getType();
        Collection<GetAllTwitesResponse> list = gson.fromJson(response.asString(), collectionType);
        return list;
    }
}

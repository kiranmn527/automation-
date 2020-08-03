package com.cucumber.testng.stepdef;

import com.cucumber.testng.action.TwitterActions;
import com.cucumber.testng.model.CreateTwiteResponse;
import com.cucumber.testng.model.GetAllTwitesResponse;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static com.cucumber.testng.utilities.misc_utils.StringUtililties.randomString;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.testng.Assert.assertEquals;

public class TwiteStepDef {

    private TwitterActions twitterActions;
    private String twite = randomString(5) + " " + randomString(6);
    private CreateTwiteResponse createTwiteResponse;

    public TwiteStepDef(TwitterActions twitterActions) {
        this.twitterActions = twitterActions;
    }

    @Given("I am authenticated into Twitter application")
    public void i_am_authenticated_into_Twitter_application() {
        twitterActions.launchTwitter();
    }

    @When("I make a random twite")
    public void i_make_a_random_twite() {
        createTwiteResponse = twitterActions.twite(twite);
    }

    @Then("the twite is created on my wall")
    public void the_twite_is_created_on_my_wall() {
        assertEquals(twite, createTwiteResponse.getText());
    }

    @Then("I can get <{int}> twite's made by me")
    public void i_can_get_twite_s_made_by_me(Integer count) {
        Collection<GetAllTwitesResponse> list = twitterActions.getAllTwitesFromMyScreen(createTwiteResponse.getUser().getName(), count);
        assertThat(list.size(), equalTo(count));
        List<String> twiteList = new ArrayList<>();
        for (GetAllTwitesResponse twite : list) {
            twiteList.add(twite.getText());
        }
        assertThat(twiteList, hasItem(containsStringIgnoringCase(twite)));
    }
}

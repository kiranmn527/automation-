package com.cucumber.testng.stepdef;

import com.cucumber.testng.action.BoxActions;
import com.cucumber.testng.action.LoginActions;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static com.cucumber.testng.utilities.misc_utils.StringUtililties.randomString;

public class BoxStepDef {
    private BoxActions boxActions;
    private LoginActions loginActions;

    public BoxStepDef(LoginActions loginActions, BoxActions boxActions){
        this.loginActions = loginActions;
        this.boxActions = boxActions;
    }

    @When("Create a Box")
    public void create_a_Box() {
       String randomString =  randomString(6);
        System.out.println("Randmoly generated box number " + randomString);
        boxActions.createBox(randomString);
    }

    @Then("Box is Created in the system")
    public void box_is_Created_in_the_system() {
        System.out.println("Box created is ---------> " + boxActions.verifyBoxCreated());
    }
}

package com.cucumber.testng.stepdef;

import com.cucumber.testng.action.BoxActions;
import com.cucumber.testng.action.LoginActions;
import com.cucumber.testng.model.LoginCredentials;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import static org.testng.Assert.assertTrue;

public class LoginStepDef {

    LoginCredentials loginCredentials = new LoginCredentials();
    private LoginActions loginActions;
    private BoxActions boxActions;

    public LoginStepDef(LoginActions loginActions, BoxActions boxActions) {
        this.loginActions = loginActions;
        this.boxActions = boxActions;
    }

    @Given("^Open Application and Enter url$")
    public void open_Application_and_Enter_url() {
        System.out.println("Calling Login Action to launch  the application");
        loginActions.launchApplication();
    }

    @When("^user enter \"([^\"]*)\" and \"([^\"]*)\"$")
    public void user_enter(String username, String password) {
        System.out.println("Perform Login Action");
        loginCredentials.setUsername(username);
        loginCredentials.setPassword(password);
        loginActions.performLogin(loginCredentials);
    }

    @Then("^verify I see Logout Link$")
    public void verify_I_see_Logout_Link() throws Throwable {
        System.out.println("Verifying logout link");
        assertTrue(loginActions.isLogoutLinkVisible());
    }
    @Then("^verify I dont see Logout Link$")
    public void verify_Idon_see_Logout_Link() throws Throwable {
        System.out.println("Verifying dont logout link");
        assertTrue(loginActions.isNOtLogoutLinkVisible());
    }

    @Then("^I see failed status$")
    public void i_see_failed_status() throws Throwable {
        System.out.println("Assert a failure");
        assertTrue(false, "Testing error handling");
    }

    @Then("I can access the box name in Login Step defination")
    public void i_can_access_the_box_name_in_Login_Step_defination() {
        System.out.println("Box number in Login step defination class access from Box Action class " + boxActions.getBoxNameFromBoxAction());
    }

    @Then("I can access the box name in Login Action")
    public void i_can_access_the_box_name_in_Login_Action() {
        loginActions.dummyMethod();
    }
}

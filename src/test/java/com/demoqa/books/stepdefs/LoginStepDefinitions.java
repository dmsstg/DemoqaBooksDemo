package com.demoqa.books.stepdefs;

import com.demoqa.books.base.ScenarioContext;
import com.demoqa.books.utils.BrowserUtils;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

import static com.demoqa.books.base.PageProvider.getPageByUrl;
import static com.demoqa.books.base.TestEnvironment.*;
import static com.demoqa.books.utils.Driver.*;
import static org.junit.Assert.*;

public class LoginStepDefinitions extends BaseStep {

    public LoginStepDefinitions(ScenarioContext scenarioContext){
        super(scenarioContext);
    }

    @Given("guest user is on the main page")
    public void the_user_is_on_the_main_page() {
        PAGE_PROVIDER.getBookStorePage().verify();
    }

    @Then("the user is taken to the {string} page")
    public void the_user_is_taken_to_the_page(String pageTitle) {
        String url = getPageUrlByPageTitle(pageTitle);
        BrowserUtils.waitUntilUrlContains(url, EXPLICIT_TIMEOUT_IN_SECONDS);
        getPageByUrl(url).verify();
    }

    @When("the user clicks the login button")
    public void the_user_clicks_the_login_button() {
        PAGE_PROVIDER.getBookStorePage().clickLoginButton();
    }

    @When("the user enters valid username and password")
    public void enters_valid_username_and_password() {
        PAGE_PROVIDER.getLoginPage().enterValidLoginCredentials(SCENARIO_CONTEXT.getUser());
    }

    @Then("the login functionality is confirmed")
    public void the_login_functionality_is_confirmed() {
        assertTrue("The login functionality is confirmed",
                SCENARIO_CONTEXT.getUser().getUsername()
                        .equals(PAGE_PROVIDER.getBookStorePage().usernameTag.getText()));
    }

    @Given("the user is logged in")
    public void the_user_is_logged_in() {
        PAGE_PROVIDER.getLoginPage().loginUser(SCENARIO_CONTEXT.getUser());
    }

    @When("the user clicks the logout button")
    public void the_user_clicks_the_logout_button() {
        getPageByUrl(getDriver().getCurrentUrl()).logOut();
    }

    @Then("the log out functionality is confirmed")
    public void the_log_out_functionality_is_confirmed() {
        Assert.assertTrue("The log out functionality is confirmed",
                PAGE_PROVIDER.getLoginPage().loginButton.isDisplayed());
    }

    @When("the user clicks the {string} menu item")
    public void the_user_clicks_the_menu_item(String menuItem) {
        getPageByUrl(getDriver().getCurrentUrl()).clickMenuItem(menuItem);
    }

    @When("the user clicks the login button on the main page")
    public void the_user_clicks_the_login_button_on_the_main_page() {
        PAGE_PROVIDER.getBookStorePage().loginButton.click();
    }
}



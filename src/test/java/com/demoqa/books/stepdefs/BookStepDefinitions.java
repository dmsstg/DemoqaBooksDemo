package com.demoqa.books.stepdefs;

import com.demoqa.books.base.ScenarioContext;
import com.demoqa.books.base.TestEnvironment;
import com.demoqa.books.pojos.Book;
import com.demoqa.books.utils.Driver;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.Alert;
import org.openqa.selenium.support.ui.ExpectedConditions;

import static com.demoqa.books.utils.BrowserUtils.*;

public class BookStepDefinitions extends BaseStep {

    private Alert alert;

    private boolean isBookAdditionConfirmed = false;

    public BookStepDefinitions(ScenarioContext scenarioContext){
        super(scenarioContext);
    }

    @Given("the user sees at least {int} book in the book store")
    public void the_user_sees_at_least_book_in_the_book_store(Integer numOfBooks) {
        Assert.assertTrue(PAGE_PROVIDER.getBookStorePage().books.size() >= numOfBooks);
    }

    @When("the user clicks on any book title")
    public void the_user_clicks_on_any_book_title() {
        Book bookToPick = PAGE_PROVIDER.getBookStorePage().pickAnyDisplayedBook();
        SCENARIO_CONTEXT.getUser().collectedBooks.add(bookToPick);
        LOG.info("Picked book to add: " + bookToPick);
        PAGE_PROVIDER.getBookStorePage().clickBookByBookId(bookToPick.getBookId());
    }

    @When("the user clicks the {string} button")
    public void the_user_clicks_the_button(String string) {
        clickElementButBeforeScrollAndAfterWaitPageToLoad(
                PAGE_PROVIDER.getBookPage().addToYourCollectionButton,
                TestEnvironment.EXPLICIT_TIMEOUT_IN_SECONDS);
    }

    @When("the user sees an alert with the message {string}")
    public void the_user_sees_an_alert_with_the_message(String string) {
        getWait(TestEnvironment.EXPLICIT_TIMEOUT_IN_SECONDS).until(ExpectedConditions.alertIsPresent());
        alert = Driver.getDriver().switchTo().alert();
        Assert.assertEquals(string,alert.getText());
    }

    @When("the user clicks the alert's {string} button")
    public void the_user_clicks_the_alert_s_button(String button) {
        switch (button){
            case "OK" :
                alert.accept();
                break;
            default:
                alert.dismiss();
        }
    }

    @Then("the user should see the added book in their profile")
    public void the_user_should_see_the_added_book_in_their_profile() {
        Book bookToVerify =
                SCENARIO_CONTEXT.getUser().collectedBooks.get(SCENARIO_CONTEXT.getUser().collectedBooks.size()-1);
        PAGE_PROVIDER.getProfilePage().searchBox.sendKeys(bookToVerify.getTitle());
        isBookAdditionConfirmed = PAGE_PROVIDER.getProfilePage().booksFromGrid.size() > 0;
    }

    @Then("the book details should match the expected information")
    public void the_book_details_should_match_the_expected_information() {
        Book bookToVerify =
                SCENARIO_CONTEXT.getUser().collectedBooks.get(SCENARIO_CONTEXT.getUser().collectedBooks.size()-1);
        Book actualBook = PAGE_PROVIDER.getProfilePage().retrieveFirstBookFromGrid(bookToVerify.getBookId());
        isBookAdditionConfirmed = bookToVerify.equals(actualBook);
    }

    @Then("the book addition functionality is confirmed")
    public void the_book_addition_functionality_is_confirmed() {
        Assert.assertTrue("The book addition functionality is confirmed",isBookAdditionConfirmed);
    }


}

//Copyright (C) 2023  Dmitry Shcherbakov


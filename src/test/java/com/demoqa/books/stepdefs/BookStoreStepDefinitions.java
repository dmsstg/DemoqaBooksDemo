package com.demoqa.books.stepdefs;

import com.demoqa.books.base.ScenarioContext;
import com.demoqa.books.pojos.Book;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

import java.util.Random;

import static com.demoqa.books.base.TestEnvironment.*;
import static com.demoqa.books.utils.BrowserUtils.*;

public class BookStoreStepDefinitions extends BaseStep {

    private Book book = new Book();
    int verifiedBooksNum = 0;

    public BookStoreStepDefinitions(ScenarioContext scenarioContext){
        super(scenarioContext);
    }

    @Given("the user sees an empty search box")
    public void the_user_sees_an_empty_search_box() {
        Assert.assertTrue(PAGE_PROVIDER.getBookStorePage().searchBox.getAttribute("value").isEmpty());
    }
    @When("the user enters the full title of an existing book into the search box")
    public void the_user_enters_the_full_title_of_an_existing_book_into_the_search_box() {
        int index = new Random().nextInt(SCENARIO_CONTEXT.getBookStore().getBooks().size()-1);
        book = SCENARIO_CONTEXT.getBookStore().getBooks().get(index);
        LOG.info("\tBook to search: " + book);
        fillInputBox(PAGE_PROVIDER.getBookStorePage().searchBox, EXPLICIT_TIMEOUT_IN_SECONDS, book.getTitle());
    }
    @Then("the user should see only books with the searched title in the result grid")
    public void the_user_sees_only_the_searched_book_in_the_result_grid() {
        for(int i = 0; i < PAGE_PROVIDER.getBookStorePage().bookTitles.size(); i++){
            if (PAGE_PROVIDER.getBookStorePage().bookTitles.get(i).getText().equals(book.getTitle())){
                verifiedBooksNum++;
            }
        }
    }

    @Then("the search functionality is confirmed")
    public void the_successful_execution_of_the_search_query_is_confirmed() {
        Assert.assertTrue(
                "The search functionality is confirmed",
                PAGE_PROVIDER.getBookStorePage().bookTitles.size() == verifiedBooksNum);
    }

}

//Copyright (C) 2023  Dmitry Shcherbakov


package com.demoqa.books.stepdefs;

import com.demoqa.books.base.ScenarioContext;
import com.demoqa.books.pojos.Book;
import com.demoqa.books.utils.ApiUtils;
import com.demoqa.books.utils.Driver;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

import java.util.ArrayList;
import java.util.List;

import static com.demoqa.books.utils.JavaUtils.*;

public class ProfileStepDefinition extends BaseStep {

    private Book bookToDelete;
    private List<String> actualNumOfRows = new ArrayList<>();
    private List<String> expectedNumOfRows = new ArrayList<>();

    private boolean isSelectorFunctionalityConfirmed = false;

    private boolean isDeletionFunctionalityConfirmed = false;

    public ProfileStepDefinition(ScenarioContext scenarioContext) {
        super(scenarioContext);
    }

    @Given("the user is on the {string} page")
    public void the_user_is_on_the_page(String string) {
        Assert.assertEquals(Driver.getDriver().getCurrentUrl(),"https://demoqa.com/profile");
    }

    @Given("the user has {int} book[s] in their profile")
    public void the_user_has_book_in_their_profile(int numOfBooks) {
        if (numOfBooks > 0){
            List<Book> booksToAdd = pickRandomElementsFromList(SCENARIO_CONTEXT.getBookStore().getBooks(), numOfBooks);
            ApiUtils.addBookToUser(SCENARIO_CONTEXT.getUser(),booksToAdd);
            SCENARIO_CONTEXT.getUser().collectedBooks.addAll(booksToAdd);
        } else {
            Assert.fail("The specifying other data than positive Integers"
                    + " in the Feature file is not supported. Provided value: "
                    + numOfBooks);
        }
    }

    @Given("the user sees {int} book[s] in their profile")
    public void the_user_sees_book_in_their_profile(int numOfBooks) {
        if (numOfBooks > 0){
            Assert.assertEquals(
                    numOfBooks,
                    PAGE_PROVIDER.getProfilePage().booksFromGrid.size());
        } else {
            Assert.fail("The specifying other data than positive Integers"
                    + " in the Feature file is not supported. Provided value: "
                    + numOfBooks);
        }
    }

    @When("the user clicks the trash can icon in the line with the book that needs to be deleted")
    public void the_user_clicks_the_trash_can_icon_in_the_line_with_the_book_that_needs_to_be_deleted() {
        bookToDelete = SCENARIO_CONTEXT.getUser().collectedBooks.get(0);
        PAGE_PROVIDER.getProfilePage().searchBox.sendKeys(bookToDelete.getTitle());
        PAGE_PROVIDER.getProfilePage().clickDeleteElementButtonByTitle(bookToDelete.getTitle());
    }

    @When("the user click the OK button on popup")
    public void the_user_click_the_ok_button_on_popup() {
        PAGE_PROVIDER.getProfilePage().popUpOkButton.click();
    }

    @Then("the user shouldn't be able to see the deleted book in their profile")
    public void the_user_shouldn_t_be_able_to_see_the_deleted_book_in_their_profile() {
        PAGE_PROVIDER.getProfilePage().searchBox.sendKeys(bookToDelete.getTitle());
        isDeletionFunctionalityConfirmed =
                PAGE_PROVIDER.getProfilePage().getBookElementsByTitle(bookToDelete.getTitle()).size() == 0;
        SCENARIO_CONTEXT.getUser().collectedBooks.remove(bookToDelete);
        bookToDelete = null;
    }

    @Then("the book deletion functionality is confirmed")
    public void the_book_deletion_functionality_is_confirmed() {
        Assert.assertTrue("The book deletion functionality is confirmed",
                isDeletionFunctionalityConfirmed);
    }

    @When("the user selects the number of rows to display")
    public void the_user_selects_rows_to_display(List<String> values) {
        expectedNumOfRows = values;
        for(String each : values){
            PAGE_PROVIDER.getProfilePage().selectRowNumByValue(each);
            actualNumOfRows.add("" + PAGE_PROVIDER.getProfilePage().bookGridLines.size());
        }
    }
    @Then("the displayed rows match the selected count")
    public void the_displayed_rows_match_the_selected_count() {
        isSelectorFunctionalityConfirmed = expectedNumOfRows.equals(actualNumOfRows);
    }

    @Then("the functionality of selecting the number of rows is confirmed")
    public void the_functionality_of_selecting_the_number_of_rows_is_confirmed() {
        Assert.assertTrue(
                "The functionality of selecting the number of rows is confirmed",
                isSelectorFunctionalityConfirmed);

    }
}

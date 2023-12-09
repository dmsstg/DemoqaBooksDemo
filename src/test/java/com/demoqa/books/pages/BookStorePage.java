package com.demoqa.books.pages;

import com.demoqa.books.pojos.Book;
import com.demoqa.books.utils.BrowserUtils;
import com.demoqa.books.base.TestEnvironment;
import com.demoqa.books.utils.Driver;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;
import java.util.Random;

public class BookStorePage extends PageBase {

    public static final String PAGE_ID_FROM_PROPERTIES = "book_store_page";
    public BookStorePage(){
        super(PAGE_ID_FROM_PROPERTIES);
    }

    @FindBy(xpath = "//button[@id='login']")
    public WebElement loginButton;

    @FindBy(xpath = "//*[@class='rt-table']")
    public WebElement booksGrid;

    @FindBy(xpath = "//div[@class='main-header']")
    public WebElement mainHeader;

    @FindBy(xpath = "//*[@id='userName-value']")
    public WebElement usernameTag;

    @FindBy(xpath = "//input[@id='searchBox']")
    public WebElement searchBox;

    @FindBy(xpath = "//*[@class='action-buttons']")
    public List<WebElement> books;

    @FindBy(xpath = "//*[@class='action-buttons']//a")
    public List<WebElement> bookTitles;

    public void verify(){
                Assert.assertTrue("Books page is not displayed",
                mainHeader.getText().equals(PAGE_HEADER)
                        && booksGrid.isDisplayed());
    }

    public void clickLoginButton(){
        BrowserUtils.clickElementButBeforeScrollAndAfterWaitPageToLoad(
                loginButton, TestEnvironment.EXPLICIT_TIMEOUT_IN_SECONDS);
    }
    public Book pickAnyDisplayedBook(){
        int randomIndex = (new Random()).nextInt(books.size() - 1);
        BrowserUtils.scrollToElementWithJsAndWait(books.get(randomIndex),TestEnvironment.EXPLICIT_TIMEOUT_IN_SECONDS);
        String title = books.get(randomIndex).getText();
        return retrieveBookFromGridByTitle(title);
    }
    public Book retrieveBookFromGridByTitle(String bookTitle){
        int listSize = getByTitleBookElements(bookTitle).size();
        if (listSize == 0){
            searchBox.clear();
            searchBox.sendKeys(bookTitle);
            listSize = getByTitleBookElements(bookTitle).size();
        }
        if (listSize == 0){
            throw new RuntimeException("No such book presented in the inventory. Provided book name: " + bookTitle);
        } else if (listSize > 1) {
            throw new RuntimeException("Book name is not unique. Provided book name: " + bookTitle);
        }
        WebElement book = getByTitleBookElement(bookTitle);
        String pictureSrc = book.findElement(By.xpath("./../../../..//img"))
                .getAttribute("src");
        String author = book.findElement(By.xpath("./../../../../div[3]")).getText();
        String publisher = book.findElement(By.xpath("./../../../../div[4]")).getText();
        String bookLink = book.getAttribute("href");
        String bookId = bookLink.substring(bookLink.lastIndexOf('=') + 1);
        return new Book(bookTitle,author,publisher,pictureSrc,bookId);
    }

    public List<WebElement> getByTitleBookElements(String title){
        String locator = "//*[text()=\"" + title + "\"]";
        return Driver.getDriver().findElements(By.xpath(locator));
    }

    public WebElement getByTitleBookElement(String title){
        String locator = "//*[text()=\"" + title + "\"]";
        return Driver.getDriver().findElement(By.xpath(locator));
    }

    public void clickBookByBookId(String bookId){
        Driver.getDriver().findElement(By.xpath("//a[contains(@href,\"" + bookId + "\")]")).click();
    }

}

//Copyright (C) 2023  Dmitry Shcherbakov

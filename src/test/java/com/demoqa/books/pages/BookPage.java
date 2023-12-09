package com.demoqa.books.pages;

import com.demoqa.books.pojos.Book;
import org.junit.Assert;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class BookPage extends PageBase {

    public static final String PAGE_ID_FROM_PROPERTIES = "book_page";
    @FindBy (xpath = "//*[@id='ISBN-wrapper']/*[2]/*")
    public WebElement isbnElement;
    @FindBy (xpath = "//*[@id='title-wrapper']/*[2]/*")
    public WebElement titleElement;
    @FindBy (xpath = "//*[@id='subtitle-wrapper']/*[2]/*")
    public WebElement subTitleElement;
    @FindBy (xpath = "//*[@id='pages-wrapper']/*[2]/*")
    public WebElement totalPagesElement;
    @FindBy (xpath = "//*[@id='description-wrapper']/*[2]/*")
    public WebElement descriptionElement;
    @FindBy (xpath = "//*[@id='website-wrapper']/*[2]/*")
    public WebElement websiteElement;
    @FindBy(xpath = "//*[text()='Back To Book Store']")
    public WebElement backToBookStoreButton;
    @FindBy(xpath = "//*[text()='Add To Your Collection']")
    public WebElement addToYourCollectionButton;

    public BookPage() {
        super(PAGE_ID_FROM_PROPERTIES);
    }
    @Override
    public void verify() {
        Assert.assertTrue(backToBookStoreButton.isDisplayed());
        Assert.assertTrue(addToYourCollectionButton.isDisplayed());
        Assert.assertTrue(titleElement.isDisplayed());
    }


    public Book addInfoFromBookPageToBook(Book book){
        book.setIsbn(isbnElement.getText());
        book.setSubTitle(subTitleElement.getText());
        book.setPages(Integer.parseInt(totalPagesElement.getText()));
        book.setDescription(descriptionElement.getText());
        book.setWebsite(websiteElement.getText());
        return book;
    }




}

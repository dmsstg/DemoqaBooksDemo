package com.demoqa.books.pages;

import com.demoqa.books.pojos.Book;
import com.demoqa.books.utils.Driver;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

public class ProfilePage extends PageBase {

    public static final String PAGE_ID_FROM_PROPERTIES = "profile_page";

    public ProfilePage(){
        super(PAGE_ID_FROM_PROPERTIES);
    }

    @FindBy(xpath = "//div[@class='main-header']")
    public WebElement mainHeader;

    @FindBy(xpath = "//input[@id='searchBox']")
    public WebElement searchBox;

    @FindBy(xpath = "//div[@class='action-buttons']//a")
    public List<WebElement> booksFromGrid;

    @FindBy(xpath = "//div[@class='rt-tr-group']")
    public List<WebElement> bookGridLines;

    @FindBy(xpath = "//*[@id=\"closeSmallModal-ok\"]")
    public WebElement popUpOkButton;

    @FindBy(xpath = "//li//*[text()='Book Store']/..")
    public WebElement bookStoreMenuItem;

    @FindBy(xpath = "//select[@aria-label='rows per page']")
    public WebElement rowSelector;

    public Book retrieveFirstBookFromGrid(String bookId) {

        String locator = "//*[@class='action-buttons']//a[contains(@href,'" + bookId + "')]";
        List<WebElement> booksFromUI = Driver.getDriver().findElements(By.xpath(locator));
        Book bookFromUI = new Book();

        if (booksFromUI.size() == 1) {
            WebElement bookContainer = booksFromUI.get(0);



            String pictureSrcFromUI = bookContainer.findElement(By.xpath("./../../../..//img"))
                    .getAttribute("src");
            String authorFromUI = bookContainer.findElement(By.xpath("./../../../../div[3]")).getText();
            String publisherFromUI = bookContainer.findElement(By.xpath("./../../../../div[4]")).getText();
            String bookTitleFromUI = bookContainer.getText();


            bookFromUI.setTitle(bookTitleFromUI);
            bookFromUI.setAuthor(authorFromUI);
            bookFromUI.setPublisher(publisherFromUI);
            bookFromUI.setPictureSrc(pictureSrcFromUI);
            bookFromUI.setBookId(bookId);

        }
        return bookFromUI;
    }

    public List<WebElement> getBookElementsByTitle(String title){
        String locator = "//*[text()='" + title + "']";
        return Driver.getDriver().findElements(By.xpath(locator));
    }

    public void selectRowNumByValue(String value){
        Select select = new Select(rowSelector);
        select.selectByValue(value);
    }

    public WebElement getBookElementByTitle(String title){
        String locator = "//*[text()='" + title + "']";
        return Driver.getDriver().findElement(By.xpath(locator));
    }

    public WebElement getBookElementById(String id){
        return Driver.getDriver().findElement(By.xpath("//a[contains(@href,\"" + id + "\")]"));
    }

    public void clickDeleteElementButtonByTitle(String title){
        getBookElementByTitle(title).findElement(By.xpath("./../../../..//*[@title='Delete']")).click();
    }

    @Override
    public void verify() {
        Assert.assertEquals("Profile page is not displayed",
                PAGE_HEADER,mainHeader.getText());
    }

}

//Copyright (C) 2023  Dmitry Shcherbakov

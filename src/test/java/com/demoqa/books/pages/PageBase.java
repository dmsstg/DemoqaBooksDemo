package com.demoqa.books.pages;


import com.demoqa.books.utils.JavaUtils;
import com.demoqa.books.utils.Driver;
import com.demoqa.books.base.TestEnvironment;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import static com.demoqa.books.utils.BrowserUtils.*;

public abstract class PageBase {
    public final String PAGE_URL;
    public final String PAGE_HEADER;
    @FindBy(xpath = "//button[text()='Log out']")
    public WebElement logOutButton;


    public PageBase(String pageIdFromProperties) {
        PageFactory.initElements(Driver.getDriver(), this);
        this.PAGE_URL = TestEnvironment.getPageUrlByPageId(pageIdFromProperties);
        this.PAGE_HEADER = TestEnvironment.getPageHeaderByPageId(pageIdFromProperties);
    }

    public abstract void verify();

    public void clickMenuItem(String menuItem){

        if (menuItem.isEmpty() || menuItem.isBlank()) {
            throw new RuntimeException("Requested menu item can not be empty or blank");
        }
        String target = JavaUtils.capitalizeEachWord(menuItem);
        WebElement item;
        try {
            item = Driver.getDriver().findElement(By.xpath("//li//*[text()='" + target + "']"));
        } catch (NoSuchElementException e) {
            e.printStackTrace();
            throw new NoSuchElementException("Menu item name is wrong. Provided name: "
                    + menuItem
                    + ". Normalized name: "
                    + target);
        }
        clickElementButBeforeScrollAndAfterWaitPageToLoad(item, TestEnvironment.EXPLICIT_TIMEOUT_IN_SECONDS);
    }

    public void logOut(){
        clickElementButBeforeScrollAndAfterWaitPageToLoad(logOutButton, TestEnvironment.EXPLICIT_TIMEOUT_IN_SECONDS);
    }

}

//Copyright (C) 2023  Dmitry Shcherbakov

package com.demoqa.books.pages;

import com.demoqa.books.pojos.User;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;

import java.util.List;

import static com.demoqa.books.base.TestEnvironment.*;
import static com.demoqa.books.utils.BrowserUtils.*;
import static com.demoqa.books.utils.Driver.*;

public class LoginPage extends PageBase {

    public static final String PAGE_ID_FROM_PROPERTIES = "login_page";
    public LoginPage() {
        super(PAGE_ID_FROM_PROPERTIES);
    }

    @FindBy(xpath = "//div[@class='main-header']")
    public WebElement mainHeader;

    @FindBy(xpath = "//button[@id='login']")
    public WebElement loginButton;

    @FindBy(xpath = "//*[@id='userName']")
    public WebElement usernameBox;

    @FindBy(xpath = "//*[@id='password']")
    public WebElement passwordBox;

    @Override
    public void verify() {
        Assert.assertEquals("Books page is not displayed", PAGE_HEADER, mainHeader.getText());
    }

    private void enterValidUsername(User user) {
        fillInputBox(usernameBox,
                EXPLICIT_TIMEOUT_IN_SECONDS,
                user.getUsername());
    }
    private void enterValidPassword(User user) {
        fillInputBox(passwordBox,
                EXPLICIT_TIMEOUT_IN_SECONDS,
                user.getPassword());
    }
    public void enterValidLoginCredentials(User user) {
        enterValidUsername(user);
        enterValidPassword(user);
    }
    private void clickLoginButton() {
        clickElementButBeforeScrollAndAfterWaitPageToLoad(
                loginButton, EXPLICIT_TIMEOUT_IN_SECONDS);
    }

    public void loginUser(User user){
        BookStorePage bsp = new BookStorePage();
        ProfilePage prp = new ProfilePage();

        //To navigate to the login page utilizing UI elements
        clickElementButBeforeScrollAndAfterWaitPageToLoad(bsp.loginButton, EXPLICIT_TIMEOUT_IN_SECONDS);


        enterValidUsername(user);
        enterValidPassword(user);
        clickLoginButton();

        waitUntilElementIsClickable(prp.logOutButton,EXPLICIT_TIMEOUT_IN_SECONDS);

        clickMenuItem("Book Store");

        waitUntilUrlToBe(bsp.PAGE_URL,EXPLICIT_TIMEOUT_IN_SECONDS);

        Assert.assertEquals(bsp.PAGE_URL,getDriver().getCurrentUrl());
    }
}

//Copyright (C) 2023  Dmitry Shcherbakov

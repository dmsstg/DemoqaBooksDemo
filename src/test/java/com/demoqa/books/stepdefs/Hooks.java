package com.demoqa.books.stepdefs;

import com.demoqa.books.base.ScenarioContext;
import com.demoqa.books.utils.ApiUtils;
import com.demoqa.books.utils.BrowserUtils;
import com.demoqa.books.base.Driver;
import com.demoqa.books.base.TestEnvironment;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

public class Hooks {

    private final ScenarioContext scenarioContext;
    public Hooks(ScenarioContext scenarioContext){
        TestEnvironment.setup();
        this.scenarioContext = scenarioContext;
    }

    @Before
    public void
    setUp(){
        try {
            scenarioContext.setUser(ApiUtils.createUser(TestEnvironment.VALID_USERNAME,TestEnvironment.VALID_PASSWORD));
        } catch (AssertionError e) {
            e.printStackTrace();
        }
        scenarioContext.getBookStore().getBooks().addAll(ApiUtils.getAllBookstoreBooks());
    }

    @After
    public void tearDown(){
        ApiUtils.deleteUser(TestEnvironment.VALID_USERNAME,TestEnvironment.VALID_PASSWORD);
    }

    @Before ("@ui")
    public void setUpForUISpecifically(){
        Driver.getDriver().get(TestEnvironment.SUT_URL);
        BrowserUtils.waitForPageToLoad(TestEnvironment.EXPLICIT_TIMEOUT_IN_SECONDS);
    }

    @After ("@ui")
    public void tearDownForUISpecifically(Scenario scenario){
        if (scenario.isFailed()){
            byte[] screenshot = ((TakesScreenshot) Driver.getDriver()).getScreenshotAs(OutputType.BYTES);
            scenario.attach(screenshot,"image/png",scenario.getName());
        }
        Driver.closeDriver();
    }
}

//Copyright (C) 2023  Dmitry Shcherbakov

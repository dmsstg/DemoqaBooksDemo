package com.demoqa.books.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public abstract class BrowserUtils {

    private static final Logger LOG = LogManager.getLogger(BrowserUtils.class);

    /**
     * Waits for backgrounds processes of the browser to complete.
     *
     * @param timeOutInSeconds specifies the wait duration in seconds
     */
    public static void waitForPageToLoad(long timeOutInSeconds) {
        LogUtils.logMethodCalling(LOG);
        ExpectedCondition<Boolean> expectation = driver ->
                ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete");
        try {
            getWait(timeOutInSeconds).until(expectation);
        } catch (Throwable e) {
            e.printStackTrace();
        } finally {
            LogUtils.logMethodSuccessfulExecution(LOG);
        }
    }

    /**
     * Returns Actions class object.
     *
     * @return Actions {@link org.openqa.selenium.interactions.Actions}
     */
    public static Actions getActions() {
        return new Actions(Driver.getDriver());
    }

    /**
     * Returns WebDriverWait object with set up given TimeOut.
     *
     * @param timeOut specifies the wait duration in seconds
     * @return Actions {@link org.openqa.selenium.interactions.Actions}
     */
    public static WebDriverWait getWait(long timeOut) {
        return new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(timeOut));
    }

    /**
     * Scrolls to element with {@link  #scrollToElementWithJsAndWait}, clicks
     * with {@link org.openqa.selenium.interactions.Actions} methods, and waits until page is loaded with
     * {@link  #waitForPageToLoad}.
     *
     * @param element          specifies {@link org.openqa.selenium.WebElement} to apply action
     * @param timeOutInSeconds specifies the wait duration in seconds
     */
    public static void clickElementButBeforeScrollAndAfterWaitPageToLoad(WebElement element, long timeOutInSeconds) {
        LogUtils.logMethodCalling(LOG);
        waitUntilElementIsClickable(element, timeOutInSeconds);
        scrollToElementWithJsAndWait(element, timeOutInSeconds);
        element.click();
        waitForPageToLoad(timeOutInSeconds);
        LogUtils.logMethodSuccessfulExecution(LOG);
    }

    /**
     * Waits still current url doesn't match expected {@link org.openqa.selenium.support.ui.ExpectedConditions#urlToBe(String)}.
     *
     * @param url       expected url
     * @param timeOutInSeconds specifies the wait duration in seconds
     */
    public static void waitUntilUrlToBe(String url, long timeOutInSeconds) {
        LogUtils.logMethodCalling(LOG);
        getWait(timeOutInSeconds).until(ExpectedConditions.urlToBe(url));
        LogUtils.logMethodSuccessfulExecution(LOG);

    }

    /**
     * Waits still current url doesn't contained expected {@link org.openqa.selenium.support.ui.ExpectedConditions#urlContains(String)}.
     *
     * @param url       expected url
     * @param timeOutInSeconds specifies the wait duration in seconds
     */
    public static void waitUntilUrlContains(String url, long timeOutInSeconds) {
        LogUtils.logMethodCalling(LOG);
        getWait(timeOutInSeconds).until(ExpectedConditions.urlContains(url));
        LogUtils.logMethodSuccessfulExecution(LOG);

    }

    /**
     * Waits still element is not visible with {@link org.openqa.selenium.support.ui.ExpectedConditions#visibilityOf(WebElement)}.
     *
     * @param webElement       specifies object to apply action
     * @param timeOutInSeconds specifies the wait duration in seconds
     */
    public static void waitUntilElementIsVisible(WebElement webElement, long timeOutInSeconds) {
        LogUtils.logMethodCalling(LOG);
        getWait(timeOutInSeconds).until(ExpectedConditions.visibilityOf(webElement));
        LogUtils.logMethodSuccessfulExecution(LOG);

    }

    /**
     * Waits still element is not active with {@link org.openqa.selenium.support.ui.ExpectedConditions#elementToBeClickable(WebElement)}.
     *
     * @param webElement       specifies object to apply action
     * @param timeOutInSeconds specifies the wait duration in seconds
     */
    public static void waitUntilElementIsClickable(WebElement webElement, long timeOutInSeconds) {
        LogUtils.logMethodCalling(LOG);
        getWait(timeOutInSeconds).until(ExpectedConditions.elementToBeClickable(webElement));
        LogUtils.logMethodSuccessfulExecution(LOG);
    }

    /**
     * Scrolls to an element using the {@link  #scrollToElementWithJsAndWait} method.
     * Fills in an input box with the provided text.
     * @param element          specifies {@link org.openqa.selenium.WebElement} to apply action
     * @param timeOutInSeconds specifies the wait duration in seconds
     * @param text             text to pass to input box
     */
    public static void fillInputBox(WebElement element, long timeOutInSeconds, String text) {
        LogUtils.logMethodCalling(LOG);
        scrollToElementWithJsAndWait(element, timeOutInSeconds);
        element.sendKeys(text);
        LogUtils.logMethodSuccessfulExecution(LOG);
    }

    /**
     * Scrolls to element with {@link  org.openqa.selenium.JavascriptExecutor}, waits until element becomes visible
     * with {@link #waitUntilElementIsVisible}.
     *
     * @param element          specifies {@link org.openqa.selenium.WebElement} to apply action
     * @param timeOutInSeconds specifies the wait duration in seconds
     */
    public static void scrollToElementWithJsAndWait(WebElement element, long timeOutInSeconds) {
        LogUtils.logMethodCalling(LOG);
        JavascriptExecutor js = Driver.getDriver();
        js.executeScript("arguments[0].scrollIntoView(true)", element);
        waitUntilElementIsVisible(element, timeOutInSeconds);
        LogUtils.logMethodSuccessfulExecution(LOG);
    }

    /**
     *  Provides a delay in thread execution
     * @param seconds required time in seconds
     */
    public static void sleep(long seconds){
        LogUtils.logMethodCalling(LOG);
        try {
            Thread.sleep(seconds * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        LogUtils.logMethodSuccessfulExecution(LOG);
    }
}

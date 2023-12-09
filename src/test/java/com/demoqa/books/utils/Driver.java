package com.demoqa.books.utils;

import com.demoqa.books.base.TestEnvironment;
import com.google.common.collect.ImmutableList;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.Browser;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.safari.SafariOptions;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

public class Driver {

    // to avoid custom instantiating
    private Driver() {
    }

    private static final ThreadLocal<RemoteWebDriver> DRIVER_POOL = new ThreadLocal<>();




    public static RemoteWebDriver getDriver() {

        if (DRIVER_POOL.get() == null) {
            PropertiesReader configProperties = new PropertiesReader("configuration.properties");
            String browser = System.getProperty("browser") != null ?
                    System.getProperty("browser") :
                    configProperties.getProperty("browser");

            URL seleniumGridServerUrl = null;

            try {
                if (browser.startsWith("remote")) {
                    seleniumGridServerUrl = new URL("http://" + TestEnvironment.SELENIUM_GRID_URL + ":4444/wd/hub");
                } else {
                    seleniumGridServerUrl = new URL("http://127.0.0.1");
                }
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }

            DesiredCapabilities dc = new DesiredCapabilities();
            switch (browser) {
                case "chrome":
                    WebDriverManager.chromedriver().setup();
                    ChromeOptions chromeOptions = new ChromeOptions();
                    chromeOptions.addArguments("--incognito")
                            .addArguments("--disable-extensions")
                            .addArguments("--no-default-browser-check")
                            .addArguments("--no-first-run")
                            .addArguments("--disable-search-engine-choice-screen")
                            .setExperimentalOption("excludeSwitches", ImmutableList.of("--enable-automation"));
                    DRIVER_POOL.set(new ChromeDriver(chromeOptions));
                    break;
                case "chrome-headless":
                    WebDriverManager.chromedriver().setup();
                    ChromeOptions chromeHeadlessOptions = new ChromeOptions();
                    chromeHeadlessOptions.addArguments("--incognito")
                            .addArguments("--disable-extensions")
                            .addArguments("--no-default-browser-check")
                            .addArguments("--no-first-run")
                            .addArguments("--disable-search-engine-choice-screen")
                            .addArguments("--headless")
                            .setExperimentalOption("excludeSwitches", ImmutableList.of("--enable-automation"));
                    DRIVER_POOL.set(new ChromeDriver(chromeHeadlessOptions));
                    break;
                case "safari":
                    WebDriverManager.safaridriver().setup();
                    SafariOptions safariOptions = new SafariOptions();
                    DRIVER_POOL.set(new SafariDriver(safariOptions));
                    break;
                case "edge":
                    WebDriverManager.edgedriver().setup();
                    EdgeOptions edgeOptions = new EdgeOptions();
                    edgeOptions.addArguments("--disable-extensions")
                            .addArguments("--no-default-browser-check")
                            .addArguments("--no-first-run")
                            .addArguments("--disable-search-engine-choice-screen");
                    DRIVER_POOL.set(new EdgeDriver(edgeOptions));
                    break;
                case "edge-headless":
                    WebDriverManager.edgedriver().setup();
                    EdgeOptions edgeHeadlessOptions = new EdgeOptions();
                    edgeHeadlessOptions.addArguments("--disable-extensions")
                            .addArguments("--no-default-browser-check")
                            .addArguments("--no-first-run")
                            .addArguments("--disable-search-engine-choice-screen")
                            .addArguments("--headless");
                    DRIVER_POOL.set(new EdgeDriver(edgeHeadlessOptions));
                    break;
                case "firefox":
                    WebDriverManager.firefoxdriver().setup();
                    FirefoxOptions firefoxOptions = new FirefoxOptions();
                    DRIVER_POOL.set(new FirefoxDriver(firefoxOptions));
                    break;
                case "firefox-headless":
                    WebDriverManager.firefoxdriver().setup();
                    FirefoxOptions firefoxHeadlessOptions = new FirefoxOptions();
                    firefoxHeadlessOptions.addArguments("--headless");
                    DRIVER_POOL.set(new FirefoxDriver(firefoxHeadlessOptions));
                    break;
                case "remote-chrome":
                    dc.setBrowserName(Browser.CHROME.browserName());
                    DRIVER_POOL.set(new RemoteWebDriver(seleniumGridServerUrl,dc));
                    break;
                case "remote-safari":
                    dc.setBrowserName(Browser.SAFARI.browserName());
                    DRIVER_POOL.set(new RemoteWebDriver(seleniumGridServerUrl,dc));
                    break;
                case "remote-firefox":
                    dc.setBrowserName(Browser.FIREFOX.browserName());
                    DRIVER_POOL.set(new RemoteWebDriver(seleniumGridServerUrl,dc));
                    break;
                default:
                    throw new IllegalArgumentException("\nInvalid or unsupported browser type.\n" +
                            "Supported browser types: chrome, chrome-headless, safari, edge, edge-headless,\n"
                            + "firefox, firefox-headless, remote-chrome, remote-safari, remote-firefox.\n"
                            + "Provided type: " + browser);
            }
            DRIVER_POOL.get().manage().window().maximize();
            DRIVER_POOL.get().manage().timeouts().implicitlyWait(Duration.ofSeconds(TestEnvironment.IMPLICIT_TIMEOUT_IN_SECONDS));
        }
        return DRIVER_POOL.get();
    }

    public static void closeDriver(){
        if (DRIVER_POOL.get() != null){
            DRIVER_POOL.get().manage().deleteAllCookies();
            DRIVER_POOL.get().quit();
            DRIVER_POOL.remove();
        }
    }

}

//Copyright (C) 2023  Dmitry Shcherbakov

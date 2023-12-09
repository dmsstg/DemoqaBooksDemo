package com.demoqa.books.base;

import com.demoqa.books.exceptions.PropertyKeyNotFoundException;
import com.demoqa.books.utils.PropertiesReader;

import java.util.*;

public class TestEnvironment {
    public static final String SELENIUM_GRID_URL;
    public static final long IMPLICIT_TIMEOUT_IN_SECONDS;
    public static final long EXPLICIT_TIMEOUT_IN_SECONDS;
    public static final String SUT_URL;
    public static final String VALID_USERNAME;
    public static final String VALID_PASSWORD;
    public static final String EXISTING_BOOK_NAME;

    public static final String API_BASE_URL;
    public static final Map<String, Map<String, String>> PAGE_DATA = new HashMap<>();


    static {
        PropertiesReader configProperties = new PropertiesReader("configuration.properties");
        String envId = System.getProperty("environment") != null
                ? System.getProperty("environment")
                : configProperties.getProperty("environment");
        String path = System.getProperty("user.dir") + "/src/test/resources/environments/" + envId + ".properties";
        PropertiesReader envProperties = new PropertiesReader(path);

        try {
            SELENIUM_GRID_URL = envProperties.getProperty("selenium.grid.address");
            IMPLICIT_TIMEOUT_IN_SECONDS = Long.parseLong(envProperties.getProperty("implicit.timeout.in.seconds"));
            SUT_URL = envProperties.getProperty("environment.url");
            EXPLICIT_TIMEOUT_IN_SECONDS = Long.parseLong(envProperties.getProperty("explicit.timeout.in.seconds"));
            VALID_USERNAME = envProperties.getProperty("username");
            VALID_PASSWORD = envProperties.getProperty("password");
            EXISTING_BOOK_NAME = envProperties.getProperty("existing.book.name");
            API_BASE_URL =  envProperties.getProperty("environment.api.base.url");
            for (Object key : envProperties.keySet()) {
                if (key.toString().endsWith(".page.id")) {
                    String pageId = envProperties.getProperty(key.toString());
                    PAGE_DATA.put(pageId, new HashMap<>());
                    PAGE_DATA.get(pageId).put("static_key_part", key.toString().replace("id", ""));
                }
            }

            for (Object pageKey : PAGE_DATA.keySet()) {
                String staticKeyPart = PAGE_DATA.get(pageKey.toString()).get("static_key_part");
                for (Map.Entry<Object, Object> each : envProperties.entrySet()) {
                    String key = each.getKey().toString();
                    boolean isPageProperty = key.startsWith(staticKeyPart) && !key.endsWith(".id");
                    if (isPageProperty) {
                        PAGE_DATA.get(pageKey.toString()).put(key.replace(staticKeyPart, ""), each.getValue().toString());
                    }
                }
            }
        } catch (PropertyKeyNotFoundException e){
            e.printStackTrace();
            throw new RuntimeException("Test environment was not set up properly.");
        }
    }

    public static void setup() {
    }

    public static String getPageUrlByPageId(String pageIdFromProperties) {
        return PAGE_DATA.get(pageIdFromProperties).get("url");
    }

    public static String getPageHeaderByPageId(String pageIdFromProperties) {
        return PAGE_DATA.get(pageIdFromProperties).get("header");
    }

    private static String normalizePageId(String pageName) {
        return pageName.toLowerCase().trim().replace(' ', '_') + "_page";
    }

    public static String getPageUrlByPageTitle(String pageTitle) {
        return PAGE_DATA.get(normalizePageId(pageTitle)).get("url");
    }

}

//Copyright (C) 2023  Dmitry Shcherbakov

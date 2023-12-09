package com.demoqa.books.stepdefs;

import com.demoqa.books.base.PageProvider;
import com.demoqa.books.base.ScenarioContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public abstract class BaseStep {
    protected final Logger LOG;
    protected final PageProvider PAGE_PROVIDER;
    protected final ScenarioContext SCENARIO_CONTEXT;

    public BaseStep(ScenarioContext scenarioContext){
        LOG = LogManager.getLogger(this.getClass());
        this.SCENARIO_CONTEXT = scenarioContext;
        PAGE_PROVIDER = new PageProvider();
    }

}

//Copyright (C) 2023  Dmitry Shcherbakov


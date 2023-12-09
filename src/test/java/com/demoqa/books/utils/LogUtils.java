package com.demoqa.books.utils;

import org.apache.logging.log4j.Logger;

public abstract class LogUtils {
    public static void logMethodCalling(Logger log){
        log.info("\tMethod called: "
                + Thread.currentThread().getStackTrace()[2].getMethodName()
                + "() from method "
                + Thread.currentThread().getStackTrace()[3].getClassName()
                + "."
                + Thread.currentThread().getStackTrace()[3].getMethodName() + "()");
    }

    public static void logMethodSuccessfulExecution(Logger log){
        log.info("Closed method: "
                + Thread.currentThread().getStackTrace()[2].getMethodName()
                + "() from method "
                + Thread.currentThread().getStackTrace()[3].getClassName()
                + "."
                + Thread.currentThread().getStackTrace()[3].getMethodName() + "()");
    }

}

//Copyright (C) 2023  Dmitry Shcherbakov

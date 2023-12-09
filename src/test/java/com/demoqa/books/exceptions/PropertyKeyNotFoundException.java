package com.demoqa.books.exceptions;

public class PropertyKeyNotFoundException extends RuntimeException {

    public PropertyKeyNotFoundException(String fullFilePath, String key){
        this("",fullFilePath,key);
    }

    public PropertyKeyNotFoundException(String message, String fullFilePath, String key){
        super("\n\tInvalid key or no such key presented in the properties file at the given path."
                        + "\n\tGiven key: \033[3;31m" + key + "\033[0m"
                        + "\n\tProperties file path: \033[3;31m" + fullFilePath + "\033[0m"
                        + (message.length() == 0 ? "" :"\n\tMessage: " + message) );
    }

}

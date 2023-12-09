package com.demoqa.books.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.lang.reflect.Field;
import java.util.*;

public abstract class JavaUtils {

    private static final Logger LOG = LogManager.getLogger(JavaUtils.class);

    /**
     * Returns the array of declared fields' class types for the given Class type.
     *
     * @param checkedClass - Class<?> type
     * @return Class<?>[]
     */
    public static Class<?>[] getAllDeclaredFieldClasses(Class<?> checkedClass) {
        LogUtils.logMethodCalling(LOG);
        Field[] fields = checkedClass.getDeclaredFields();
        HashSet<Class<?>> tmp = new HashSet<>();
        for (Field field : fields) {
            tmp.add(field.getType());
        }
        Class<?>[] result = new Class<?>[tmp.size()];
        tmp.toArray(result);
        LogUtils.logMethodSuccessfulExecution(LOG);
        return result;
    }

    /**
     * Returns the array of declared fields' class types for the given Object.
     *
     * @param checkedObject - given object
     * @return Class<?>[]
     */
    public static Class<?>[] getAllDeclaredFieldClasses(Object checkedObject) {
        LogUtils.logMethodCalling(LOG);
        Field[] fields = checkedObject.getClass().getDeclaredFields();
        HashSet<Class<?>> tmp = new HashSet<>();
        for (Field field : fields) {
            tmp.add(field.getType());
        }
        Class<?>[] result = new Class<?>[tmp.size()];
        tmp.toArray(result);
        LogUtils.logMethodSuccessfulExecution(LOG);
        return result;
    }

    /**
     * Returns the array of declared fields' class types for the given Object.
     *
     * @param classes    array of the Classes to process
     * @param superClass Class to check
     * @return Class<?>[]
     */
    public static Class<?>[] getAllSubclasses(Class<?>[] classes, Class<?> superClass) {
        LogUtils.logMethodCalling(LOG);
        Class<?>[] result = new Class<?>[classes.length];
        int counter = 0;
        for (Class<?> each : classes) {
            if (each.getSuperclass() == superClass) {
                result[counter++] = each;
            }
        }
        LogUtils.logMethodSuccessfulExecution(LOG);
        return Arrays.copyOf(result, counter);
    }

    /**
     * Checks if provided letter is a lower-case letter, and returns upper-case one. In other cases returns provided character.
     *
     * @param letter character to capitalize
     * @return char
     */
    public static char getUpperCaseLetter(char letter) {
        return (char) ((letter >= 97 && letter <= 122) ? (letter - 32) : letter);
    }

    /**
     * Capitalizes the first letter of each word in the input string and converts all other letters to lowercase.
     *
     * @param inputString String to process
     * @return String
     */
    public static String capitalizeEachWord(String inputString) {
        LogUtils.logMethodCalling(LOG);
        String result;
        if (inputString == null || inputString.isEmpty() || inputString.isBlank()) {
            result = inputString;
        } else {
            inputString = inputString.toLowerCase().trim();
            StringBuilder stb = new StringBuilder(inputString.length());
            char[] chars = inputString.toCharArray();
            stb.append(getUpperCaseLetter(chars[0]));
            for (int i = 1; i < chars.length; i++) {
                if (chars[i] == ' ') {
                    chars[i + 1] = getUpperCaseLetter(chars[i + 1]);
                }
                stb.append(chars[i]);
            }
            result = stb.toString();
        }
        LogUtils.logMethodSuccessfulExecution(LOG);
        return result;
    }

    /**
     * Generates an array of random indexes within a specified range from 0 up to the maximum index provided.
     *
     * @param numOfIndexes The number of random indexes to be generated.
     * @param maxIndex The maximum index within which the random indexes should be generated.
     * @return An array of random indexes within the specified range.
     */
    public static int[] getUniqueRandomIndexes(int numOfIndexes, int maxIndex) {
        if (numOfIndexes <= 0 || numOfIndexes > maxIndex + 1) {
            throw new IllegalArgumentException("Invalid arguments");
        }

        Random random = new Random();
        List<Integer> tmp = new ArrayList<>(numOfIndexes);


        while (tmp.size() < numOfIndexes) {
            int newInt = random.nextInt(maxIndex);
            if (!tmp.contains(newInt)) {
                tmp.add(newInt);
            }
        }

        int[] result = new int[numOfIndexes];

        for (int i = 0; i < result.length; i++) {
            result[i] = tmp.get(i);
        }
        return result;
    }

    public static <T> List<T> pickRandomElementsFromList(List<T> input, int number){
        int[] indexes = getUniqueRandomIndexes(number,input.size()-1);
        List<T> result = new ArrayList<>();
        for (int index : indexes){
            result.add(input.get(index));
        }
        return result;
    }

}

//Copyright (C) 2023  Dmitry Shcherbakov

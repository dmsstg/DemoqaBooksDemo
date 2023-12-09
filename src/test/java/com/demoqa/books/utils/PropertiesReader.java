package com.demoqa.books.utils;

import com.demoqa.books.exceptions.PropertyKeyNotFoundException;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Hashtable;
import java.util.Properties;
import java.util.Set;

public class PropertiesReader {

    private final Properties properties;
    private final File file;

    public PropertiesReader(String path) {
        this (new File(path));
    }

    public PropertiesReader(File file) {
        properties = new Properties();
        this.file = file;
        try {
            FileInputStream stream = new FileInputStream(file);
            properties.load(stream);
            stream.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException("Properties file not found at the given path: \033[3;31m"
                    + file.getAbsolutePath() + "\033[0m");
        } catch (IOException e) {
            throw new RuntimeException("Read error while loading file into Properties instance for the file: "
                    + file.getAbsolutePath());
        }
    }

    /**
     * Returns the value for the given key from the predefined <i>.properties</i> file as a String.
     * Throws PropertyKeyNotFoundException in case of invalid key name or absence of such key.
     *
     * @param key specifies the key of the related value to retrieve
     * @return String
     * @throws PropertyKeyNotFoundException if returned value for the specified key is null
     */
    public String getProperty(String key) {
        if (properties.getProperty(key) == null) {
            throw new PropertyKeyNotFoundException(file.getAbsolutePath(), key);
        }
        return properties.getProperty(key);
    }

    /**
     * @see Hashtable#keySet()
     */
    public Set<Object> keySet() {
        return properties.keySet();
    }

    /**
     * @see Hashtable#entrySet()
     */
    public Set<java.util.Map.Entry<Object, Object>> entrySet() {
        return properties.entrySet();
    }
}

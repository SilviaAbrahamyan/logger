package com.egs.training.logger;

import java.io.*;
import java.util.Properties;

/**
 * Created by home on 12/30/2017.
 */
public class Configuration {

    private String loggerLevel;
    private String loggerHandler;
    private String loggerHandlerFileName;


    private static Configuration instance = null;

    private Configuration() {

    }

    public String getLoggerLevel() {
        return loggerLevel;
    }

    public int getLoggerLevelValue() {
        return Level.valueOf(loggerLevel).getValue();
    }

    public String getLoggerHandler() {
        return loggerHandler;
    }

    public String getLoggerHandlerFileName() {
        return loggerHandlerFileName;
    }

    static public Configuration getConfigInstance() {
        if (instance == null) {
            try {
                instance = new Configuration();
                instance.getProperties();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return instance;
    }

    public void getProperties() {

        String propFileName = "logger.properties";
        Properties properties = new Properties();
        InputStream input = null;

        try {

            input = Configuration.class.getClassLoader().getResourceAsStream(propFileName);
            if(input==null){
                System.out.println("Sorry, unable to find " + propFileName +". Processing default properties.");
                input = new FileInputStream("logger.properties");
            }

            // load a properties file
            properties.load(input);

            // get the property value and print it out
//            System.out.println(properties.getProperty("logger.level"));
//            System.out.println(properties.getProperty("logger.handler"));
//            System.out.println(properties.getProperty("logger.handler.file.name"));

            loggerLevel = properties.getProperty("logger.level");
            loggerHandler = properties.getProperty("logger.handler");
            loggerHandlerFileName = properties.getProperty("logger.handler.file.name");

        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }

}

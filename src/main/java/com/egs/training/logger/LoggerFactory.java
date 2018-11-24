package com.egs.training.logger;

import java.util.HashMap;
import java.util.Map;

public final class LoggerFactory {

    private static final Map<String, Logger> loggers = new HashMap<>();

    private LoggerFactory() {
    }


    public static Logger getLogger(final String name) {
//        you can achieve the same result with one line! using java8 Map.putIfAbsent ;)
//        return loggers.putIfAbsent(name, new Logger(name));

        Configuration config = Configuration.getConfigInstance();
//        String loggerLevel = config.getLoggerLevel();
        String loggerHandler = config.getLoggerHandler();
        String loggerHandlerFileName = config.getLoggerHandlerFileName();
        if (loggers.containsKey(name)) {
            return loggers.get(name);
        }
        if ("file".equals(loggerHandler)) {
            final Logger logger = new Logger(name, new FileHandler(loggerHandlerFileName, new SimpleFormatter()));
            loggers.put(name, logger);
            return logger;
        } else if ("console".equals(loggerHandler)) {
            // TODO later handler and formatter will be configured in a file
            final Logger logger = new Logger(name, new ConsoleHandler(new SimpleFormatter()));
            loggers.put(name, logger);
            return logger;

        } else {
            return null;
        }
    }


    /**
     * the method is for testing
     */
    public static Map<String, Logger> getLoggers() {
        return loggers;
    }

    public static synchronized void closeAll() {
        loggers.clear();
    }


}


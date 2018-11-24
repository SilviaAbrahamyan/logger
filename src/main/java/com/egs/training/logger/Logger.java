package com.egs.training.logger;

import java.util.Objects;

public final class Logger {

    private String name;
    private Handler handler;
    private Filter filter;
    private Level level = Level.ALL;

    Logger(String name, Handler handler) {
        Objects.requireNonNull(name);
        Objects.requireNonNull(handler);

        this.name = name;
        this.handler = handler;
        this.filter = new SimpleFilter();
    }

    void setFilter(Filter filter) {
        this.filter = filter;
    }

    void setLevel(Level level) {
        this.level = level;
    }

    public void trace(String message) {
        log(Level.TRACE, message);
    }

    public void info(String message) {
        log(Level.INFO, message);
    }

    public void warn(String message) {
        log(Level.WARNING, message);
    }

    public void warn(String message, Throwable throwable) {
        log(Level.WARNING, message, throwable);
    }

    public void error(String message) {
        log(Level.ERROR, message);
    }

    public void error(String message, Throwable throwable) {
        log(Level.ERROR, message, throwable);
    }

    private void log(Level level, String message) {
        log(level, message, null);
    }

    private void log(Level level, String message, Throwable throwable) {
        final LogRecord.LogRecordBuilder builder = new LogRecord.LogRecordBuilder();
        final LogRecord logRecord = builder
                .level(level)
                .message(message)
                .loggerName(name)
                .thrown(throwable)
                .time(System.currentTimeMillis()).build();

        if (filter == null || filter.isLoggable(logRecord)) {
            handler.append(logRecord);
        }
    }
}

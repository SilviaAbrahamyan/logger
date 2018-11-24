package com.egs.training.logger;

public interface Filter {
    boolean isLoggable(LogRecord logRecord);
}

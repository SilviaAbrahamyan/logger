package com.egs.training.logger;

public class SimpleFilter implements Filter {
    @Override
    public boolean isLoggable(LogRecord logRecord) {
        if (Configuration.getConfigInstance().getLoggerLevel() != null ) {
            return logRecord.getLevel().getValue() >= Configuration.getConfigInstance().getLoggerLevelValue();
        } else {
            return true;
        }
    }
}

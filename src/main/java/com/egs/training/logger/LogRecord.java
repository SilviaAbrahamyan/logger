package com.egs.training.logger;

class LogRecord {
    private String loggerName;
    private Level level;
    private long time;
    private String message;
    private Throwable thrown;

    private LogRecord(Level level, String message) {
        this.level = level;
        this.message = message;
    }

    String getLoggerName() {
        return loggerName;
    }

    Level getLevel() {
        return level;
    }

    long getTime() {
        return time;
    }

    String getMessage() {
        return message;
    }

    Throwable getThrown() {
        return thrown;
    }

    @Override
    public String toString() {
        return
                "loggerName:'" + loggerName + '\'' +
                " \nlevel:" + level +
                " \ntime:" + time +
                " \nmessage:'" + message + '\'' +
                " \nthrown:" + thrown.getStackTrace();
    }

    static class LogRecordBuilder {
        private String loggerName;
        private Level level;
        private long time;
        private String message;
        private Throwable thrown;

        LogRecordBuilder level(Level level) {
            this.level = level;
            return this;
        }

        LogRecordBuilder message(String message) {
            this.message = message;
            return this;
        }

        LogRecordBuilder loggerName(String loggerName) {
            this.loggerName = loggerName;
            return this;
        }

        LogRecordBuilder time(long time) {
            this.time = time;
            return this;
        }

        LogRecordBuilder thrown(Throwable thrown) {
            this.thrown = thrown;
            return this;
        }

        LogRecord build() {

            if (!validate()) {
                throw new IllegalArgumentException("Check required fields!");
            }

            LogRecord logRecord = new LogRecord(this.level, this.message);
            logRecord.loggerName = this.loggerName;
            logRecord.time = this.time;
            logRecord.thrown = this.thrown;
            return logRecord;
        }


        private boolean validate() {
            return level != null && message != null && loggerName != null;
        }
    }
}

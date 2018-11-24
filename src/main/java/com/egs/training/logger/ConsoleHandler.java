package com.egs.training.logger;

import java.util.Objects;

public class ConsoleHandler implements Handler {
    private Formatter formatter;

    public ConsoleHandler(Formatter formatter) {
        Objects.requireNonNull(formatter);

        this.formatter = formatter;
    }

    @Override
    public void append(LogRecord logRecord) {
        System.out.println(this.formatter.format(logRecord));
    }
}

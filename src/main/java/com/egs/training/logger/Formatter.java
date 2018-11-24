package com.egs.training.logger;

import java.io.File;

public interface Formatter {
    String format(LogRecord logRecord);
}

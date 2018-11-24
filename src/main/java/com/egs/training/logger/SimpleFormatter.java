package com.egs.training.logger;

import java.io.File;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Date;

public class SimpleFormatter implements Formatter {
    @Override

    public String format(LogRecord logRecord) {
        String template = "time: %s \n level: %s \n loggerName: %s \n message: %s ";
        if(logRecord.getThrown() != null) {
            template += "\n thrown: %s";
        }
        final String formatted = String.format( template,
                new Date(logRecord.getTime()).toString(), logRecord.getLevel(), logRecord.getLoggerName(), logRecord.getMessage(),
                logRecord.getThrown());

        if (logRecord.getThrown() != null) {

            try {
                StringWriter stringWriter = new StringWriter();
                PrintWriter writer = new PrintWriter(stringWriter);
                writer.println(formatted);

                logRecord.getThrown().printStackTrace(writer);

                return stringWriter.toString();
            } catch (Exception e) {}

        }

        return formatted;
    }
}

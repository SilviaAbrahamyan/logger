package com.egs.training.logger;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Objects;

/**
 * Created by home on 12/30/2017.
 */
public class FileHandler implements Handler {

    private Formatter formatter;
    String fileName;

    FileHandler(String fileName, Formatter formatter) {
        Objects.requireNonNull(formatter);

        this.formatter = formatter;

        this.fileName = fileName;
    }

    @Override
    public void append(LogRecord logRecord) {


        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date(logRecord.getTime());

        Calendar cal = Calendar.getInstance();
        cal.setTime(date);

        String filename = Configuration.getConfigInstance().getLoggerHandlerFileName();

        String rootPath = this.getClass().getResource("/").getPath();

        File logDir = new File(rootPath+File.separator+"logs");
        processLogFiles(logDir);

        File file = new File(logDir + File.separator + filename + "_" + df.format(date) + ".log");

        try {
            FileWriter fileWriter = new FileWriter(file, true);
            fileWriter.write("\n");
            fileWriter.write(this.formatter.format(logRecord));
            fileWriter.write("\n");
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    public void processLogFiles(File logDir) {

        if ( !logDir.exists() ) {
            logDir.mkdirs();
        } else {

            File[] logFiles = logDir.listFiles();

            Calendar cal = Calendar.getInstance();
            cal.add(Calendar.DATE, -6);

            for (int i = 0; i < logFiles.length; i++) {
                if (logFiles[i].isFile()) {
                    Path path = Paths.get(logFiles[i].getAbsolutePath());
                    try {
                        BasicFileAttributes attr = Files.readAttributes(path, BasicFileAttributes.class);
                        if(cal.getTimeInMillis() > attr.creationTime().toMillis()) {
                            logFiles[i].delete();
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } else if (logFiles[i].isDirectory()) {
                    logFiles[i].delete();
                }
            }

        }

    }


}

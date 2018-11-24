package com.egs.training.logger;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.PrintStream;

import static junit.framework.Assert.assertEquals;

/**
 * Created by hakob on 1/2/2018.
 */
public class TestLogger {

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();

    @Before
    public void setUp() {
        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(errContent));
    }

    @After
    public void cleanUpStreams() {
        System.setOut(null);
        System.setErr(null);
    }

    @Test
    public void testLogger() {
        // start loggers
        Logger logger = LoggerFactory.getLogger("testName");

        logger.info("Info test message.");
        assert(outContent.toString().contains("Info test message."));

        logger.warn("Warn test message.");
        assert(outContent.toString().contains("Warn test message."));

        logger.error("Error test message.", new FileNotFoundException());
        assert(outContent.toString().contains("Error test message."));
        assert(outContent.toString().contains("FileNotFoundException"));

    }

}

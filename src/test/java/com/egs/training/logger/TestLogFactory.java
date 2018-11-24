package com.egs.training.logger;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class TestLogFactory {

    @Before
    public void setup() {

    }

    @After
    public void cleanUpStreams() {
        LoggerFactory.closeAll();
    }

    @Test
    public void testGetLogger() {
        Logger loggerA0 = LoggerFactory.getLogger("logger");
        Logger loggerA1 = LoggerFactory.getLogger("logger");
        Logger loggerB0 = LoggerFactory.getLogger("other_logger");

        assertNotNull(loggerA0);
        assertTrue(loggerA0 == loggerA1);
        assertTrue(loggerA0 != loggerB0);

        loggerA0.trace("This is trace message");
        loggerA0.info("This is info message");
        loggerA0.warn("This is warn message");
        loggerA0.error("This is error message");
    }

    @Test
    public void testItHoldsLoggersOverGC() throws InterruptedException {
        ArrayList<Logger> loggers = new ArrayList<Logger>();
        for (int i = 0; i < 100; i++) {
            loggers.add(LoggerFactory.getLogger("test_logger_" + i));
        }
        System.gc();
        Thread.sleep(1000);
        assertEquals(loggers.size(), LoggerFactory.getLoggers().size());

//        Logger head = loggers.get(0);
//        Logger tail = loggers.get(loggers.size() - 1);
        loggers.clear();
        LoggerFactory.closeAll();
        System.gc();
        Thread.sleep(1000);
        assertEquals(0, LoggerFactory.getLoggers().size());
    }
}
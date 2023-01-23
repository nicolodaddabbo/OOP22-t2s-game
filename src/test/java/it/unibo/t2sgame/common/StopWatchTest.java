package it.unibo.t2sgame.common;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

public class StopWatchTest {

    @Test
    void testStopWatch() {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        //Testing making the stopwatch pass 1 second
        double elapsed;
        while((elapsed = stopWatch.getElapsedSeconds()) < 1){}
        assertEquals(1, elapsed);
        stopWatch.restart();
        //Testing making the stopwatch pass 500 millisecond
        while((elapsed = stopWatch.getElapsedMillis()) < 500){}
        assertEquals(500, elapsed);
        stopWatch.restart();
        //Testing making the stopwatch pass 500000 nanosecond
        while((elapsed = stopWatch.getElapsedNanos()) < 500000){}
        assertEquals(500000, elapsed);
    }

    @Test
    void testStopWatchExceptions() {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        //Testing if starting the stopwatch while already running throws expected exception
        Exception exception = assertThrows(IllegalStateException.class, () -> stopWatch.start());
        assertEquals("Stopwatch is already running", exception.getMessage());
        stopWatch.stop();
        //Testing if stopping the stopwatch while not running throws expected exception
        exception = assertThrows(IllegalStateException.class, () -> stopWatch.stop());
        assertEquals("Stopwatch is not running", exception.getMessage());
        //Testing if restarting the stopwatch while not running throws expected exception
        exception = assertThrows(IllegalStateException.class, () -> stopWatch.restart());
        assertEquals("Stopwatch is not running", exception.getMessage());
    }
    
}

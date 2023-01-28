package it.unibo.t2sgame.common;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

public class StopWatchTest {

    @Test
    void testElapsedSeconds() {
        StopWatch stopWatch = new StopWatch();
        double elapsed;
        double seconds = 1;
        stopWatch.start();
        while((elapsed = stopWatch.getElapsedSeconds()) < seconds){}
        assertEquals(seconds, elapsed);
    }

    @Test
    void testElapsedMilliseconds() {
        StopWatch stopWatch = new StopWatch();
        double elapsed;
        double milliseconds = 500;
        stopWatch.start();
        while((elapsed = stopWatch.getElapsedMillis()) < milliseconds){}
        assertEquals(milliseconds, elapsed);
    }

    @Test
    void testElapsedNanoseconds() {
        StopWatch stopWatch = new StopWatch();
        long elapsed;
        long nanoseconds = 500000;
        stopWatch.start();
        while((elapsed = stopWatch.getElapsedNanos()) < nanoseconds){}
        assertEquals(nanoseconds, elapsed);
    }

    @Test
    void testStartingStopwatchWhileRunningThrowsException() {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        Exception exception = assertThrows(IllegalStateException.class, () -> stopWatch.start());
        assertEquals("Stopwatch is already running", exception.getMessage());
    }

    @Test
    void testStoppingStopwatchWhileNotRunningThrowsException() {
        StopWatch stopWatch = new StopWatch();
        Exception exception = assertThrows(IllegalStateException.class, () -> stopWatch.stop());
        assertEquals("Stopwatch is not running", exception.getMessage());
    }

    @Test
    void testRestartingStopwatchWhileNotRunningThrowsException() {
        StopWatch stopWatch = new StopWatch();
        Exception exception = assertThrows(IllegalStateException.class, () -> stopWatch.restart());
        assertEquals("Stopwatch is not running", exception.getMessage());
    }
    
}

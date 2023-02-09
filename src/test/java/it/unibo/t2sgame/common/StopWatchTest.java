package it.unibo.t2sgame.common;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

public class StopWatchTest {

    private static final double NANO_TO_MILLIS = 1E6;
    private static final double NANO_TO_SECONDS = 1E9;

    @Test
    void testElapsedSeconds() {
        StopWatch stopWatch = new StopWatch();
        double elapsed;
        double seconds = 1;
        stopWatch.start();
        while ((elapsed = stopWatch.getElapsedSeconds()) < seconds) {
            /* Wait for the time to elapse */
        }
        assertEquals(seconds, elapsed, 100 / NANO_TO_SECONDS);
    }

    @Test
    void testElapsedMilliseconds() {
        StopWatch stopWatch = new StopWatch();
        double elapsed;
        double milliseconds = 500;
        stopWatch.start();
        while ((elapsed = stopWatch.getElapsedMillis()) < milliseconds) {
            /* Wait for the time to elapse */
        }
        assertEquals(milliseconds, elapsed, 100 / NANO_TO_MILLIS);
    }

    @Test
    void testElapsedNanoseconds() {
        StopWatch stopWatch = new StopWatch();
        long elapsed;
        long nanoseconds = 500000;
        stopWatch.start();
        while ((elapsed = stopWatch.getElapsedNanos()) < nanoseconds) {
            /* Wait for the time to elapse */
        }
        assertEquals(nanoseconds, elapsed, 100);
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

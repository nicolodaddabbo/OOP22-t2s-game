package it.unibo.t2sgame.common;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

class StopWatchTest {

    private static final long NANO_DELTA = 100;
    private static final double NANO_TO_MILLIS = 1E6;
    private static final double NANO_TO_SECONDS = 1E9;

    @Test
    void testElapsedSeconds() {
        StopWatch stopWatch = new StopWatch();
        double elapsed = 0.0;
        final double seconds = 1;
        stopWatch.start();
        /* Wait for the time to elapse */
        while (elapsed < seconds) {
            elapsed = stopWatch.getElapsedSeconds();
        }
        assertEquals(seconds, elapsed, NANO_DELTA / NANO_TO_SECONDS);
    }

    @Test
    void testElapsedMilliseconds() {
        StopWatch stopWatch = new StopWatch();
        double elapsed = 0.0;
        final double milliseconds = 500;
        stopWatch.start();
        /* Wait for the time to elapse */
        while (elapsed < milliseconds) {
            elapsed = stopWatch.getElapsedMillis();
        }
        assertEquals(milliseconds, elapsed, NANO_DELTA / NANO_TO_MILLIS);
    }

    @Test
    void testElapsedNanoseconds() {
        StopWatch stopWatch = new StopWatch();
        long elapsed = 0;
        final long nanoseconds = 500000;
        stopWatch.start();
        /* Wait for the time to elapse */
        while (elapsed < nanoseconds) {
            elapsed = stopWatch.getElapsedNanos();
        }
        assertEquals(nanoseconds, elapsed, NANO_DELTA);
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

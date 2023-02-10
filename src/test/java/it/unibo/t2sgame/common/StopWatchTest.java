package it.unibo.t2sgame.common;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

class StopWatchTest {

    @Test
    void testStartingStopwatchWhileRunningThrowsException() {
        final StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        final Exception exception = assertThrows(IllegalStateException.class, () -> stopWatch.start());
        assertEquals("Stopwatch is already running", exception.getMessage());
    }

    @Test
    void testStoppingStopwatchWhileNotRunningThrowsException() {
        final StopWatch stopWatch = new StopWatch();
        final Exception exception = assertThrows(IllegalStateException.class, () -> stopWatch.stop());
        assertEquals("Stopwatch is not running", exception.getMessage());
    }

    @Test
    void testRestartingStopwatchWhileNotRunningThrowsException() {
        final StopWatch stopWatch = new StopWatch();
        final Exception exception = assertThrows(IllegalStateException.class, () -> stopWatch.restart());
        assertEquals("Stopwatch is not running", exception.getMessage());
    }

}

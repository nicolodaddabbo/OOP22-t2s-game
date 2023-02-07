package it.unibo.t2sgame.common;

/**
 * This class represents a stopwatch that can be started, stopped and return the
 * elapsed time.
 */
public class StopWatch {
    private long startTime = 0;
    private long endTime = 0;
    private boolean isRunning = false;
    private static final double MILLIS = 1E6;
    private static final double SECONDS = 1E9;

    /**
     * This method <strong>starts</strong> the stopwatch.
     * 
     * @return this started stopwatch
     * @throws IllegalStateException if the stopwatch is already running
     */
    public synchronized StopWatch start() throws IllegalStateException {
        if (this.isRunning) {
            throw new IllegalStateException("Stopwatch is already running");
        }
        this.startTime = System.nanoTime();
        this.isRunning = true;
        return this;
    }

    /**
     * This method <strong>stops</strong> the stopwatch.
     * 
     * @throws IllegalStateException if the stopwatch is not running
     */
    public synchronized void stop() throws IllegalStateException {
        if (!this.isRunning) {
            throw new IllegalStateException("Stopwatch is not running");
        }
        this.endTime = System.nanoTime();
        this.isRunning = false;
    }

    /**
     * This method <strong>restarts</strong> the stopwatch.
     * 
     * @throws IllegalStateException if the stopwatch is not running
     */
    public synchronized void restart() throws IllegalStateException {
        this.stop();
        this.start();
    }

    /**
     * This method is used to get the elapsed time.
     * If it's called while the stopwatch is running
     * it will return the elapsed time from
     * <strong>{@link #start() start time}</strong>
     * to the <strong>current time</strong>.
     * If it's called after the stopwatch is stopped
     * it will return the elapsed time from
     * <strong>{@link #start() start time}</strong>
     * to <strong>{@link #stop() stop time}</strong>.
     * 
     * @return the elapsed time in nanoseconds
     * @see {@link #getElapsedMillis()}
     * @see {@link #getElapsedSeconds()}
     */
    public synchronized long getElapsedNanos() {
        return this.isRunning ? System.nanoTime() - this.startTime : this.endTime - this.startTime;
    }

    /**
     * This method is used to get the elapsed time.
     * If it's called while the stopwatch is running
     * it will return the elapsed time from
     * <strong>{@link #start() start time}</strong>
     * to the <strong>current time</strong>.
     * If it's called after the stopwatch is stopped
     * it will return the elapsed time from
     * <strong>{@link #start() start time}</strong>
     * to <strong>{@link #stop() stop time}</strong>.
     * 
     * @return the elapsed time in milliseconds
     * @see {@link #getElapsedNanos()}
     * @see {@link #getElapsedSeconds()}
     */
    public double getElapsedMillis() {
        return this.getElapsedNanos() / MILLIS;
    }

    /**
     * This method is used to get the elapsed time.
     * If it's called while the stopwatch is running
     * it will return the elapsed time from
     * <strong>{@link #start() start time}</strong>
     * to the <strong>current time</strong>.
     * If it's called after the stopwatch is stopped
     * it will return the elapsed time from
     * <strong>{@link #start() start time}</strong>
     * to <strong>{@link #stop() stop time}</strong>.
     * 
     * @return the elapsed time in seconds
     * @see {@link #getElapsedNanos()}
     * @see {@link #getElapsedMillis()}
     */
    public double getElapsedSeconds() {
        return this.getElapsedNanos() / SECONDS;
    }

}

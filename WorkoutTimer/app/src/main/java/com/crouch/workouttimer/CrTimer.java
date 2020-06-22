package com.crouch.workouttimer;

import java.time.Duration;
import java.time.LocalDateTime;

/**
 * Timer class to keep track of timer value
 */
public class CrTimer {
    private LocalDateTime mStartTime;
    private long mTimer;

    /**
     * Constructor
     */
    public CrTimer() {
        mStartTime = null;
        mTimer = 0;
    }

    /**
     * start the timer and set the value to the current time
     */
    public void startTimer() {
        mStartTime = LocalDateTime.now();
    }

    /**
     * reset the timer value to null; null will indicate it is not started
     */
    public void resetTimer() {
        mStartTime = null;
        mTimer = 0;
    }

    /**
     * record the amount of time since the timer was started and add it to timer long value
     */
    public void pauseTimer() {
        long elapsedTime = getElapsedTime();
        mTimer += elapsedTime;
        mStartTime = null;
    }

    /**
     * get the amount of time as long value that elapsed since start of timer and this function call
     *
     * @return long value of milliseconds representing time elapsed
     */
    private long getElapsedTime() {
        if (mStartTime != null) {
            LocalDateTime now = LocalDateTime.now();
            Duration duration = Duration.between(mStartTime, now);

            return duration.toMillis();
        } else {
            return 0;
        }
    }

    /**
     * get the time from when this function was called and the start of the timer
     *
     * @return long value representing milliseconds amount of time elapsed since initial start of timer
     */
    public long getTimeValue() {
        long totalTime = getElapsedTime();
        totalTime += mTimer;

        return totalTime;
    }
}

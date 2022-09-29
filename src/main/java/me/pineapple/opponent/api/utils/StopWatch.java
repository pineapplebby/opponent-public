package me.pineapple.opponent.api.utils;

public class StopWatch {

    private long current;

    public StopWatch() {
        this.current = System.currentTimeMillis();
    }

    public boolean passed(final long delay) {
        return System.currentTimeMillis() - this.current >= delay;
    }

    public void reset() {
        this.current = System.currentTimeMillis();
    }

    public long getTime() {
        return System.currentTimeMillis() - this.current;
    }

    public long getTimePassed() {
        return System.currentTimeMillis() - this.current;
    }

    public boolean hasReached(long delay) {
        boolean reset = false;
        if (reset) {
            this.reset();
        }
        return System.currentTimeMillis() - this.current >= delay;
    }
    


    public long getCurrent() {
        return current;
    }

    public boolean sleep(final long time) {
        if (getTime() >= time) {
            reset();
            return true;
        }
        return false;
    }


    public void setMs(long ms) {
        current = System.currentTimeMillis() - ms;
    }

}
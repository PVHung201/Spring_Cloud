package com.microservice.blog.handle;

import feign.RetryableException;
import feign.Retryer;

public class TimeoutHandle implements Retryer {

    private int maxAttempts;
    private int interval;
    private int attempts = 1;

    public TimeoutHandle(int maxAttempts, int interval) {
        this.maxAttempts = maxAttempts;
        this.interval = interval;
    }

    public TimeoutHandle() {
        this(3, 1000);
    }

    @Override
    public void continueOrPropagate(RetryableException e) {
        System.out.println("This is " + attempts);
        if (attempts++ > maxAttempts) {
            throw e;
        }

        try {
            Thread.sleep(interval);
        } catch (InterruptedException ie) {
            Thread.currentThread().interrupt();
        }

    }

    @Override
    public Retryer clone() {
        return new TimeoutHandle();
    }
}

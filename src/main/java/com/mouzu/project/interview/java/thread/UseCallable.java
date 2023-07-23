package com.mouzu.project.interview.java.thread;

import java.util.concurrent.Callable;

public class UseCallable implements Callable<Integer> {
    @Override
    public Integer call() throws Exception {
        return 66;
    }
}

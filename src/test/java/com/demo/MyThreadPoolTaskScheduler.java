package com.demo;

import org.springframework.core.task.TaskRejectedException;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.support.TaskUtils;
import org.springframework.util.ErrorHandler;

import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;

/**
 * @author Q1sj
 * @date 2023.3.16 17:01
 */
public class MyThreadPoolTaskScheduler extends ThreadPoolTaskScheduler {

    private volatile ErrorHandler errorHandler;

    @Override
    public void setErrorHandler(ErrorHandler errorHandler) {
        this.errorHandler = errorHandler;
    }

    public ScheduledFuture<?> schedule(Runnable task, TaskTest.AllowReFiresCronTrigger trigger) {
        ScheduledExecutorService executor = getScheduledExecutor();
        try {
            return new MyReschedulingRunnable(task, trigger, executor, TaskUtils.getDefaultErrorHandler(true)).schedule();
        } catch (RejectedExecutionException ex) {
            throw new TaskRejectedException("Executor [" + executor + "] did not accept task: " + task, ex);
        }
    }
}

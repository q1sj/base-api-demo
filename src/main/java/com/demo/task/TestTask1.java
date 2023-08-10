package com.demo.task;

import com.xsy.job.task.ITask;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * @author Q1sj
 * @date 2023.3.1 15:17
 */
@Slf4j
@Component
public class TestTask1 implements ITask {

    @Override
    public void run(String param) {
        log.info("param:{}",param);
        try {
            TimeUnit.SECONDS.sleep(11);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
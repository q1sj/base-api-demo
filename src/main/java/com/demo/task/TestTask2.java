package com.demo.task;

import com.xsy.job.task.ITask;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @author Q1sj
 * @date 2023.3.1 15:17
 */
@Slf4j
@Component
public class TestTask2 implements ITask {

    @Override
    public void run(String param) {
        log.info("param:{}",param);
        int i = 1 / 0;
    }
}
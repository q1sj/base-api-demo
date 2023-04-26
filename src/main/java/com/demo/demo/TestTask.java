package com.demo.demo;

import com.xsy.sys.entity.SysTaskConfigEntity;
import com.xsy.sys.service.DynamicTask;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * @author Q1sj
 * @date 2023.3.1 15:17
 */
@Slf4j
@Component
public class TestTask implements DynamicTask {

    @Override
    public void run() {
        log.info("run....");
        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public SysTaskConfigEntity getDefaultConfig() {
        SysTaskConfigEntity defaultConfig = new SysTaskConfigEntity();
        defaultConfig.setEnable(false);
        return defaultConfig;
    }
}

package com.demo.demo;

import com.xsy.sys.entity.SysTaskConfigEntity;
import com.xsy.sys.service.DynamicTask;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @author Q1sj
 * @date 2023.3.1 15:17
 */
@Slf4j
@Component
public class TestTask2 implements DynamicTask {

    @Override
    public void run() {
        int i = 1 / 0;
    }

    @Override
    public SysTaskConfigEntity getDefaultConfig() {
        SysTaskConfigEntity defaultConfig = new SysTaskConfigEntity();
        defaultConfig.setEnable(true);
        defaultConfig.setCronExpression("0/10 * * * * ?");
        defaultConfig.setAllowReFiresCron(false);
        return defaultConfig;
    }
}
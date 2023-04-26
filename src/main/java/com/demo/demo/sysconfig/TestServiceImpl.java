package com.demo.demo.sysconfig;

import com.xsy.sys.annotation.SysConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Q1sj
 * @date 2023.4.13 16:09
 */
@Slf4j
@Service
public class TestServiceImpl implements TestService {
    @Autowired
    private TestDao testDao;
    @SysConfig("testValue")
    private String testValue;

    @Override
    public void print() {
        log.debug("testValue:{}",testValue);
    }
}

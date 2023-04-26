package com.demo.demo.sysconfig;

import com.xsy.security.annotation.NoAuth;
import com.xsy.sys.annotation.SysConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import java.util.Date;

/**
 * @author Q1sj
 * @date 2023.3.24 15:46
 */
@Slf4j
@RestController
public class RefreshValueController extends BaseController {

    @SysConfig
    public Date testDate;
    @SysConfig
    public final String testValue = "";
    @SysConfig("testValue2")
    public int value2 = 1;
    @SysConfig("testValue3")
    public Boolean value3 = false;
    @SysConfig("testValue3")
    public Boolean value3s = false;
    @SysConfig("testObject")
    public User user;
    @SysConfig("testValue")
    private static String staticValue;
    @SysConfig
    private String url;
    @SysConfig
    private String ip;

    @SysConfig
    private Integer port;

    @Autowired
    private TestService testService;

    @PostConstruct
    public void init() {
        printField();
    }

    @NoAuth
    @GetMapping("/getValue")
    public String get() {
        printField();
        return "ok";
    }

    private void printField() {
        log.info("date:{} value2:{} value3:{} value3s:{} user:{}", testDate, value2, value3, value3s, user);
        log.info("static field {} {} {}", Config.getValue(), Config.getValue2(), Config.getValue3());
        log.info("baseValue:{}", baseValue);
        log.info("url:{}", url);
        testService.print();
    }
}

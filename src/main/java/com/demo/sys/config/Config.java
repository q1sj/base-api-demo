package com.demo.sys.config;

import com.xsy.sys.annotation.SysConfig;
import org.springframework.stereotype.Component;

/**
 * @author Q1sj
 * @date 2023.3.31 14:41
 */
@Component
public class Config {
    @SysConfig("testValue")
    private static String value;

    @SysConfig("testValue2")
    private static int value2;

    @SysConfig("testValue3")
    private static Boolean value3;

    public static String getValue() {
        return value;
    }

    public static int getValue2() {
        return value2;
    }

    public static Boolean getValue3() {
        return value3;
    }
}

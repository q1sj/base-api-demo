package com.demo.sys.config;

import com.xsy.sys.annotation.SysConfig;

/**
 * @author Q1sj
 * @date 2023.3.31 14:44
 */
public class BaseController {
    @SysConfig("testValue")
    public String baseValue;
}
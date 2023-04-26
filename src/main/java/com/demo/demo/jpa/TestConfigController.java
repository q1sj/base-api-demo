package com.demo.demo.jpa;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.xsy.base.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author Q1sj
 * @date 2023.4.25 15:36
 */
@RestController
@RequestMapping("/testConfig")
public class TestConfigController {
    @Autowired
    private TestConfigDao testConfigDao;

    @GetMapping("/test")
    public Result<Void> test(){
        TestConfig testConfig = new TestConfig();
        testConfig.setValue("aaa");
        testConfigDao.insert(testConfig);
        return Result.ok();
    }
    @GetMapping("/list")
    public Result<List<TestConfig>> list() {
        List<TestConfig> list = testConfigDao.selectList(Wrappers.emptyWrapper());
        return Result.ok(list);
    }
}

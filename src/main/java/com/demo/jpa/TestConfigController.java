package com.demo.jpa;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.xsy.base.util.Result;
import com.xsy.base.util.StringUtils;
import com.xsy.security.annotation.NoAuth;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

/**
 * @author Q1sj
 * @date 2023.4.25 15:36
 */
@RestController
@NoAuth
@RequestMapping("/testConfig")
public class TestConfigController {
    @Autowired
    private TestConfigDao testConfigDao;

    @GetMapping("/test")
    public Result<Void> test() {
        TestConfig testConfig = new TestConfig();
        testConfig.setValue(UUID.randomUUID().toString());
        testConfigDao.insert(testConfig);
        return Result.ok();
    }

    @GetMapping("/list")
    public Result<List<TestConfig>> list(@RequestParam(required = false) String value) {
        LambdaQueryWrapper<TestConfig> wrapper = Wrappers.lambdaQuery(TestConfig.class)
                .like(StringUtils.isNotBlank(value), TestConfig::getValue, value);
        List<TestConfig> list = testConfigDao.selectList(wrapper);
        if (StringUtils.isBlank(value)) {
            return Result.ok(list);
        }
        for (TestConfig testConfig : list) {
            if (Objects.equals(value, testConfig.getValue())) {
                return Result.ok(list);
            }
        }
        LinkedList<TestConfig> linkedList = new LinkedList<>(list);
        TestConfig testConfig = new TestConfig();
        testConfig.setId(IdWorker.getId());
        testConfig.setValue(value);
        linkedList.addFirst(testConfig);
        return Result.ok(linkedList);
    }

}

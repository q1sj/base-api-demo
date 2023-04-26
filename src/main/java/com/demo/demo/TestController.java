package com.demo.demo;

import com.xsy.base.util.Result;
import com.xsy.base.util.StringUtils;
import com.xsy.security.annotation.NoAuth;
import com.xsy.sys.service.SysConfigService;
import com.xsy.sys.service.SysUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;

import java.util.concurrent.TimeUnit;

/**
 * @author Q1sj
 * @date 2023.1.4 15:11
 */
@Slf4j
@RestController
public class TestController implements TestApi {
    @Autowired
    private SysConfigService sysConfigService;
    @Autowired
    private SysUserService sysUserService;

    @Override
    @NoAuth
    public String a() {
        return "ok";
    }

    private volatile String r = "";

    @NoAuth
    @GetMapping("/setR")
    public Result<Void> setR(String r) {
        this.r = r;
        return Result.ok();
    }

    @NoAuth
    @GetMapping("/deferredResult")
    public DeferredResult<String> deferredResult() {
        DeferredResult<String> result = new DeferredResult<>(TimeUnit.SECONDS.toMillis(5), "null");
        Thread thread = new Thread(() -> {
            while (true) {
                log.debug("r={}", r);
                if (StringUtils.isNotBlank(r)) {
                    result.setResult(r);
                    r = null;
                    return;
                }
                try {
                    TimeUnit.MILLISECONDS.sleep(100);
                } catch (InterruptedException e) {
                    return;
                }
            }
        });
        result.onTimeout(thread::interrupt);
        result.onError(Throwable::printStackTrace);
        thread.start();
        return result;
    }

    @NoAuth
    @GetMapping("/getL")
    @Cacheable(cacheNames = "test", key = "'testlong'")
    public Long getL() {
        log.info("getL");
        return 1L;
    }
}

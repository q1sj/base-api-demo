package com.demo.event;

import com.xsy.sys.annotation.SysConfig;
import com.xsy.sys.service.SysConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * @author Q1sj
 * @date 2023.10.7 9:19
 */
@Component
public class TestEventListener {
	@Autowired
	private SysConfigService sysConfigService;
	@SysConfig
	private String a = "1";
	@EventListener
	@Order(1)
	public void handle3(TestEvent event) {
		System.out.println(3);
	}

	@EventListener
	@Order(2)
	public void handle2(TestEvent event) {
		System.out.println(2);
	}

	@EventListener
	@Order(3)
	public void handle1(TestEvent event) {
		System.out.println(1);
	}

}

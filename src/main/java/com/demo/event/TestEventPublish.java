package com.demo.event;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

/**
 * @author Q1sj
 * @date 2023.10.7 9:15
 */
@Component
public class TestEventPublish implements CommandLineRunner {

	@Autowired
	private ApplicationContext applicationContext;

	@Override
	public void run(String... args) throws Exception {
		applicationContext.publishEvent(new TestEvent("1"));
	}
}

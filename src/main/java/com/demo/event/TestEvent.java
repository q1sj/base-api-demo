package com.demo.event;

import org.springframework.context.ApplicationEvent;

/**
 * @author Q1sj
 * @date 2023.10.7 9:17
 */
public class TestEvent extends ApplicationEvent {
	public TestEvent(String source) {
		super(source);
	}
}

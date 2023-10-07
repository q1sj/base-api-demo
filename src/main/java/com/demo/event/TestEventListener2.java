package com.demo.event;

import org.springframework.context.event.EventListener;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * @author Q1sj
 * @date 2023.10.7 9:32
 */
@Component
public class TestEventListener2 {
	@EventListener
	@Order(-1)
	public void handle3(TestEvent event) {
		System.out.println(0);
	}
}

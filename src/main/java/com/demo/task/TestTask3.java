package com.demo.task;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * @author Q1sj
 * @date 2023.6.5 14:16
 */
@Slf4j
@Component
public class TestTask3 {

	@Scheduled(fixedRate = 5 * 1000)
	public void run() {
		log.info("task3 run...");
		try {
			TimeUnit.SECONDS.sleep(60);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}

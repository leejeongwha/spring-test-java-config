package com.naver.test.task;

import java.util.concurrent.Future;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;

@Service
public class CustomAsyncService {
	private final Logger logger = LoggerFactory.getLogger(CustomScheduler.class);

	public void notAsync(int i) {
		logger.info("not async({})", i);
	}

	@Async
	public Future<Boolean> async() {
		// Demonstrate that our beans are being injected
		logger.info("async start");

		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		logger.info("async end");

		return new AsyncResult<Boolean>(true);
	}
}

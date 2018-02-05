package com.naver.test.task;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FixedThreadPoolTest {
	@Test
	public void test() throws InterruptedException {
		CustomAsyncService service = new CustomAsyncService();
		ExecutorService execService = Executors.newFixedThreadPool(10);

		service.notAsync(0);

		execService.execute(new MyTask());

		for (int i = 1; i < 6; i++) {
			service.notAsync(i);
		}

		if (execService.awaitTermination(6, TimeUnit.SECONDS)) {
			execService.shutdown();
		}
	}
}

class MyTask implements Runnable {
	private final Logger logger = LoggerFactory.getLogger(MyTask.class);

	@Override
	public void run() {
		// String threadName = Thread.currentThread().getName();
		// System.out.println(threadName);

		logger.info("async start");
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		logger.info("async end");
	}
}

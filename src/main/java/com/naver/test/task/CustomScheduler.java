package com.naver.test.task;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;

//@Component
public class CustomScheduler {
	private final Logger logger = LoggerFactory.getLogger(CustomScheduler.class);

	@Scheduled(fixedDelay = 5000)
	public void fixedDelay() {
		printCurrentTime("fixedDelay");
	}

	@Scheduled(cron = "*/3 * * * * ?")
	public void cron() {
		printCurrentTime("cron");
	}

	private void printCurrentTime(String name) {
		long time = System.currentTimeMillis();
		SimpleDateFormat dayTime = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
		String str = dayTime.format(new Date(time));
		logger.info(name + " current Time : {}", str);
	}
}

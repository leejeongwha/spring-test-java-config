package com.naver.test.task;

import java.text.ParseException;
import java.util.Date;

import org.junit.Before;
import org.junit.Test;
import org.quartz.CronExpression;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CronExpressionTest {
	final static Logger logger = LoggerFactory.getLogger(CronExpressionTest.class);

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void test() throws ParseException {
		int count = 5;
		Date currentDate = new Date();

		String expression = "0 0 0 1 * ?";
		CronExpression cronExpression = new CronExpression(expression);

		for (int i = 0; i < count; i++) {
			Date nextTime = cronExpression.getNextValidTimeAfter(currentDate);

			logger.info("Next Quartz Scheduler execute time : {}", nextTime.toString());

			currentDate = nextTime;
		}

	}

}

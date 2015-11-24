package com.naver.test.task;

import java.util.concurrent.Future;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.naver.test.config.AppConfiguration;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = AppConfiguration.class)
public class CustomAsyncServiceTest {
	@Autowired
	private CustomAsyncService customAsyncService;

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void test() {
		customAsyncService.notAsync(0);

		Future<Boolean> result = customAsyncService.async();

		for (int i = 1; i < 6; i++) {
			customAsyncService.notAsync(i);
		}

		while (!result.isDone()) {
			// wait
		}
	}
}

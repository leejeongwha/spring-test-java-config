package com.naver.test.aop;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.naver.test.aop.bo.AspectTestBO;
import com.naver.test.config.AppConfiguration;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = AppConfiguration.class)
public class AspectTest {
	final static Logger logger = LoggerFactory.getLogger(AspectTest.class);

	@Autowired
	private AspectTestBO aspectTestBO;

	@Before
	public void setUp() throws Exception {
	}

	/**
	 * Pointcut Methods and Reuse
	 */
	@Test
	public void test1() {
		aspectTestBO.test1();
	}

	/**
	 * Custom annotation
	 */
	@Test
	public void test2() {
		aspectTestBO.test2();
	}

	/**
	 * JoinPoint and Advice Arguments
	 */
	@Test
	public void test3() {
		aspectTestBO.test3("test");
	}

	/**
	 * return value change
	 */
	@Test
	public void test4() {
		int result = aspectTestBO.test4(10);
		logger.info("returned result : {}", result);
	}

	/**
	 * Advice order test
	 */
	@Test
	public void test5() {
		aspectTestBO.test5();
	}
}

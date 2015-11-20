package com.naver.test.aop.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Aspect
@Order(1)
public class MyAspect2 {
	final static Logger logger = LoggerFactory.getLogger(MyAspect2.class);

	/**
	 * Advice order test
	 */
	@Pointcut("execution(* com.naver.test.aop.bo..*.test5*(..))")
	public void pointcut() {

	}

	@Before("pointcut()")
	public void before(JoinPoint joinPoint) throws Exception {
		logger.info("MyAspect2:before");
	}

	@After("pointcut()")
	public void after(JoinPoint joinPoint) throws Exception {
		logger.info("MyAspect2:after");
	}
}

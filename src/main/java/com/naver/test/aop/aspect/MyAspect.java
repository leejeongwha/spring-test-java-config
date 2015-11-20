package com.naver.test.aop.aspect;

import java.util.Arrays;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Aspect
@Order(0)
public class MyAspect {
	final static Logger logger = LoggerFactory.getLogger(MyAspect.class);

	/**
	 * Pointcut Methods and Reuse
	 */
	@Pointcut("execution(* com.naver.test.aop.bo..*.*(..))")
	public void pointcut() {

	}

	@Before("pointcut()")
	public void before(JoinPoint joinPoint) throws Exception {
		logger.info("MyAspect:before");
	}

	@After("pointcut()")
	public void after(JoinPoint joinPoint) throws Exception {
		logger.info("MyAspect:after");
	}

	/**
	 * Custom annotation
	 */
	@Before("@annotation(com.naver.test.aop.bo.Loggable)")
	public void annotation() {
		logger.info("MyAspect:before:annotation");
	}

	/**
	 * JoinPoint and Advice Arguments
	 * 
	 * @param joinPoint
	 */
	@Before("execution(* com.naver.test.aop.bo..*.*(java.lang.String))")
	public void loggingAdvice(JoinPoint joinPoint) {
		logger.info("Before running loggingAdvice on method=" + joinPoint.toString());

		logger.info("Agruments Passed=" + Arrays.toString(joinPoint.getArgs()));
	}

	/**
	 * return value change
	 * 
	 * @param proceedingJoinPoint
	 * @return
	 */
	@Around("execution(* com.naver.test.aop.bo..*.test4*(..))")
	public Object employeeAroundAdvice(ProceedingJoinPoint proceedingJoinPoint) {
		int value = 0;
		try {
			value = (int) proceedingJoinPoint.proceed();
			logger.info("after invoking method : {}", value);
			value += 10;
		} catch (Throwable e) {
			logger.error("error");
		}
		return value;
	}
}

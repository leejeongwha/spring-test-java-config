package com.naver.test.aop.bo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class AspectTestBOImpl implements AspectTestBO {
	final static Logger logger = LoggerFactory.getLogger(AspectTestBOImpl.class);

	@Override
	public void test1() {
		logger.info("test1 executed!!");
	}

	@Override
	@Loggable
	public void test2() {
		logger.info("test2 executed!!");
	}

	@Override
	public void test3(String args) {
		logger.info("test3 executed!!");
	}

	@Override
	public int test4(int args) {
		logger.info("test4 executed!!");
		return args;
	}

	@Override
	public void test5() {
		logger.info("test5 executed!!");
	}

}

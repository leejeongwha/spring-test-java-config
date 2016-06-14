package com.naver.test.mvc.bo;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

@Service
@Scope(value = "prototype")
// @Scope(value = "prototype", proxyMode = ScopedProxyMode.INTERFACES)
public class TestBOImpl implements TestBO, InitializingBean, DisposableBean {
	private final Logger logger = LoggerFactory.getLogger(TestBOImpl.class);

	@Override
	public void test() {
		logger.info("test BO executed!!");
	};

	public TestBOImpl() {
		logger.info("TestBOImpl Constructor");
	}

	@PostConstruct
	public void postConstruct() throws Exception {
		logger.info("postConstruct");
	}

	@PreDestroy
	public void preDestroy() throws Exception {
		logger.info("preDestroy");
	}

	@Override
	public void destroy() throws Exception {
		logger.info("destroy");

	}

	@Override
	public void afterPropertiesSet() throws Exception {
		logger.info("afterPropertiesSet");
	};
}

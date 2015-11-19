package com.naver.test.mvc.controller;

import javax.inject.Provider;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.naver.test.mvc.bo.ApplicationContextProvider;
import com.naver.test.mvc.bo.TestBO;
import com.naver.test.mvc.bo.TestBOImpl;

@Controller
@RequestMapping("/mvc")
public class BeanScopeController {
	private final Logger logger = LoggerFactory.getLogger(BeanScopeController.class);

	@Autowired
	private TestBO testBO1;

	@Autowired
	@Lazy
	private TestBO testBO2;

	@Autowired
	ApplicationContextProvider applicationContextProvider;

	@Autowired
	private Provider<TestBO> provider;

	@RequestMapping("scope")
	@ResponseBody
	public String scope() {
		testBO1.test();
		String message = "testBO address : " + testBO1.toString();
		logger.info(message);

		return message;
	};

	@RequestMapping("scope1")
	@ResponseBody
	public String scope1() {
		testBO2.test();
		String message = "testBO address : " + testBO2.toString();
		logger.info(message);

		return message;
	};

	@RequestMapping("scope2")
	@ResponseBody
	public String scope2() {
		ApplicationContext applicationContext = applicationContextProvider.getApplicationContext();
		TestBO testBO = (TestBO) applicationContext.getBean(TestBOImpl.class);

		testBO.test();
		String message = "testBO address : " + testBO.toString();
		logger.info(message);

		return message;
	};

	@RequestMapping("scope3")
	@ResponseBody
	public String scope3() {
		TestBO testBO3 = provider.get();
		testBO3.test();
		String message = "testBO address : " + testBO3.toString();
		logger.info(message);

		return message;
	}
}

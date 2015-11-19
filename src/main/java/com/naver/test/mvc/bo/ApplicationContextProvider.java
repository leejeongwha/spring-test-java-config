package com.naver.test.mvc.bo;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Component
public class ApplicationContextProvider implements ApplicationContextAware {
	private ApplicationContext context;

	@Override
	public void setApplicationContext(ApplicationContext paramApplicationContext) throws BeansException {
		context = paramApplicationContext;
	}

	public ApplicationContext getApplicationContext() {
		return context;
	}
}

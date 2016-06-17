package com.naver.test.config;

import java.util.Arrays;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCache;
import org.springframework.cache.support.SimpleCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.http.converter.xml.MarshallingHttpMessageConverter;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;
import org.springframework.stereotype.Controller;

import com.naver.test.task.CustomAsyncExceptionHandler;

//@Configuration
@ComponentScan(basePackages = { "com.naver.test" }, excludeFilters = @Filter({ Controller.class }) )
@EnableScheduling
@EnableCaching
@EnableAsync
@EnableAspectJAutoProxy
public class AppConfiguration implements SchedulingConfigurer, AsyncConfigurer {
	// cacheManger 설정
	@Bean
	public CacheManager cacheManager() {
		// Spring's CacheManager SPI 설정 필수!!
		SimpleCacheManager cacheManager = new SimpleCacheManager();
		cacheManager.setCaches(Arrays.asList(new ConcurrentMapCache("board")));
		return cacheManager;
	}

	// marshaller 설정
	@Bean
	public MarshallingHttpMessageConverter marshallingMessageConverter() {
		return new MarshallingHttpMessageConverter(jaxb2Marshaller());
	}

	@Bean
	public Jaxb2Marshaller jaxb2Marshaller() {
		Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
		// marshaller.setClassesToBeBound(new Class[] {
		// com.naver.test.oxm.model.BoardUser.class });
		marshaller.setPackagesToScan("com.naver.**.model");
		return marshaller;
	}

	// scheduler 설정
	@Override
	public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {
		taskRegistrar.setScheduler(taskExecutor());
	}

	@Bean(destroyMethod = "shutdown")
	public Executor taskExecutor() {
		return Executors.newScheduledThreadPool(100);
	}

	// async 설정
	@Override
	public Executor getAsyncExecutor() {
		ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
		executor.setCorePoolSize(40);
		executor.setMaxPoolSize(40);
		executor.setQueueCapacity(1000);
		executor.setThreadNamePrefix("TestThread-");
		executor.initialize();
		return executor;
	}

	@Override
	public AsyncUncaughtExceptionHandler getAsyncUncaughtExceptionHandler() {
		return new CustomAsyncExceptionHandler();
	}
}

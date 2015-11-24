package com.naver.test.config;

import java.util.Arrays;

import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCache;
import org.springframework.cache.support.SimpleCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.http.converter.xml.MarshallingHttpMessageConverter;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Controller;

@Configuration
@ComponentScan(basePackages = { "com.naver.test" }, excludeFilters = @Filter({ Controller.class }) )
@EnableScheduling
@EnableCaching
@EnableAsync
@EnableAspectJAutoProxy
public class AppConfiguration {
	@Bean
	public CacheManager cacheManager() {
		// Spring's CacheManager SPI 설정 필수!!
		SimpleCacheManager cacheManager = new SimpleCacheManager();
		cacheManager.setCaches(Arrays.asList(new ConcurrentMapCache("default")));
		return cacheManager;
	}

	@Bean
	public MarshallingHttpMessageConverter marshallingMessageConverter() {
		return new MarshallingHttpMessageConverter(jaxb2Marshaller());
	}

	@Bean
	public Jaxb2Marshaller jaxb2Marshaller() {
		Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
		marshaller.setClassesToBeBound(new Class[] { com.naver.test.oxm.model.BoardUser.class });
		return marshaller;
	}
}

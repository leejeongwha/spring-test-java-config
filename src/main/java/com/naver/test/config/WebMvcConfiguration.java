package com.naver.test.config;

import java.util.Locale;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import com.naver.test.mvc.interceptor.MvcTestInterceptor;

@EnableWebMvc
@Configuration
@ComponentScan(basePackages = {
		"com.naver.test" }, useDefaultFilters = false, includeFilters = @Filter(Controller.class) )
public class WebMvcConfiguration extends WebMvcConfigurationSupport {
	@Bean
	public InternalResourceViewResolver getInternalResourceViewResolver() {
		InternalResourceViewResolver resolver = new InternalResourceViewResolver();
		resolver.setPrefix("/WEB-INF/view/jsp/");
		resolver.setSuffix(".jsp");
		return resolver;
	}

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/resources/**").addResourceLocations("/resources/").setCachePeriod(31556926);
	}

	@Override
	public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
		configurer.enable();
	}

	@Bean
	public MessageSource messageSource() {
		final ReloadableResourceBundleMessageSource ret = new ReloadableResourceBundleMessageSource();
		ret.setBasename("classpath:properties/message");
		ret.setDefaultEncoding("UTF-8");
		return ret;
	}

	@Bean
	public LocaleResolver localeResolver() {
		final CookieLocaleResolver ret = new CookieLocaleResolver();
		ret.setDefaultLocale(new Locale("en_US"));
		return ret;
	}

	@Bean
	@Override
	public RequestMappingHandlerMapping requestMappingHandlerMapping() {
		RequestMappingHandlerMapping handlerMapping = super.requestMappingHandlerMapping();

		// @MatrixVariable을 사용하기 위해서 선언
		handlerMapping.setRemoveSemicolonContent(false);
		handlerMapping.setUseTrailingSlashMatch(false);

		handlerMapping.setInterceptors(new Object[] { getLocaleChangeInterceptor(), getMvcTestInterceptor() });

		return handlerMapping;
	}

	@Bean
	public CommonsMultipartResolver multipartResolver() {
		CommonsMultipartResolver resolver = new CommonsMultipartResolver();
		resolver.setDefaultEncoding("utf-8");
		// 10mb maxsize
		resolver.setMaxUploadSize(10000000);
		return resolver;
	}

	// LocaleChangeInterceptor
	private HandlerInterceptor getLocaleChangeInterceptor() {
		LocaleChangeInterceptor interceptor = new LocaleChangeInterceptor();
		interceptor.setParamName("language");
		return interceptor;
	}

	// Custom Interceptor
	private HandlerInterceptor getMvcTestInterceptor() {
		MvcTestInterceptor interceptor = new MvcTestInterceptor();
		return interceptor;
	}
}

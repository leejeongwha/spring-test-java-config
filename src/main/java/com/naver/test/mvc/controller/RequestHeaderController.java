package com.naver.test.mvc.controller;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/mvc")
public class RequestHeaderController {
	private final Logger logger = LoggerFactory.getLogger(RequestHeaderController.class);

	@RequestMapping("/header")
	public String header(@RequestHeader(value = "Accept") String accept,
			@RequestHeader(value = "Accept-Language") String acceptLanguage,
			@RequestHeader(value = "User-Agent", defaultValue = "foo") String userAgent, HttpServletResponse response) {

		logger.info("accept: " + accept);
		logger.info("acceptLanguage: " + acceptLanguage);
		logger.info("userAgent: " + userAgent);

		return "success";
	};
}

package com.naver.test.mvc.controller;

import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/mvc")
public class LocaleController {
	private final Logger logger = LoggerFactory.getLogger(LocaleController.class);

	@RequestMapping("locale")
	@ResponseBody
	public String locale(Locale locale) {
		logger.info("country : {}", locale.getCountry());
		logger.info("language : {}", locale.getLanguage());
		return locale.getLanguage();
	}

	@RequestMapping("localeView")
	public String localeView() {
		return "message";
	}
}

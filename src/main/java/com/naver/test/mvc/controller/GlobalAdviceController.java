package com.naver.test.mvc.controller;

import java.time.format.TextStyle;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.naver.test.mvc.model.HelloParam;

@RestController
@RequestMapping("/mvc")
public class GlobalAdviceController {
	private final Logger logger = LoggerFactory.getLogger(HelloController.class);

	/**
	 * /spring-test/mvc/conversion?lightYn=on&dateFormat=2016-05-01&priceFormat=
	 * 3,000&dayEnumFormat=SUNDAY&phone=0311234567
	 * 
	 * @param helloParam
	 * @return
	 */
	@RequestMapping("/conversion")
	public String date(HelloParam helloParam) {
		logger.info("lightYn : {}", helloParam.getLightYn().booleanValue());

		logger.info("dateFormat : {}", helloParam.getDateFormat());

		logger.info("priceFormat : {}", helloParam.getPriceFormat());

		logger.info("enumFormat : {}", helloParam.getDayEnumFormat().getDisplayName(TextStyle.FULL, Locale.KOREA));

		// Custom Formatter 등록(FormatTelephone annotation)
		logger.info("customFormatter : {}", helloParam.getPhone());

		return "success";
	};

	@RequestMapping("/nfException")
	public String nfException() throws Exception {
		throw new NumberFormatException("format test");
	};

	@RequestMapping("/bindException")
	public String bindException() throws Exception {
		throw new IndexOutOfBoundsException();
	};
}

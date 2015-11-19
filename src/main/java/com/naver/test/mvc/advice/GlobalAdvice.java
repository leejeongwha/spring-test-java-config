package com.naver.test.mvc.advice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.propertyeditors.CustomBooleanEditor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class GlobalAdvice {
	private final Logger logger = LoggerFactory.getLogger(GlobalAdvice.class);

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.registerCustomEditor(Boolean.class,
				new CustomBooleanEditor(CustomBooleanEditor.VALUE_ON, CustomBooleanEditor.VALUE_OFF, true));
	}

	@ExceptionHandler(value = NumberFormatException.class)
	@ResponseStatus(value = HttpStatus.SERVICE_UNAVAILABLE)
	public ModelAndView handleException(Exception ex) {
		logger.info("Exception : {}", ex.getMessage());
		return new ModelAndView("error").addObject("message", ex.getMessage());
	}
}

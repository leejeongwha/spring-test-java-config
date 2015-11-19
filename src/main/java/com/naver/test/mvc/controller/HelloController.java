package com.naver.test.mvc.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.naver.test.mvc.model.HelloParam;

/**
 * RequestMapping 및 RequestParam 테스트
 * 
 * @author nhn
 *
 */
@Controller
@RequestMapping("/mvc")
public class HelloController {
	private final Logger logger = LoggerFactory.getLogger(HelloController.class);

	@RequestMapping(value = "/hello", method = { RequestMethod.GET })
	public String helloGet(Model model,
			@RequestParam(value = "message", required = false, defaultValue = "test") String message) {
		logger.info("this is hello controller : http method(GET)");
		model.addAttribute("message", message);
		return "showMessage";
	};

	@RequestMapping(value = "/hello", method = { RequestMethod.POST })
	public String helloPost(Model model, @RequestParam String message) {
		logger.info("this is hello controller : http method(POST)");
		model.addAttribute("message", message);
		return "showMessage";
	};

	@RequestMapping(value = { "hello1", "hello2" })
	public String hellos(Model model, @RequestParam String message) {
		logger.info("this is hello controller : http method(GET)");
		model.addAttribute("message", message);
		return "showMessage";
	};

	@RequestMapping("hello3")
	public String hello3(Model model, HelloParam param) {
		logger.info("message value : {}", param.getMessage());
		model.addAttribute("message", param.getMessage());
		return "showMessage";
	};

	@RequestMapping("hello4")
	public String hello4(Model model, @ModelAttribute HelloParam param) {
		logger.info("message value : {}", param.getMessage());
		model.addAttribute("message", param.getMessage());
		return "showMessage";
	};
}

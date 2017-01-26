package com.naver.test.mvc.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

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

	/**
	 * 파라미터에 ModelAttribute 어노테이션 생략 가능
	 * 
	 * @param model
	 * @param param
	 * @return
	 */
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

	@RequestMapping("hello5")
	public String hello5(Model model, HelloParam param) {
		model.addAttribute("message", param.getMessage());

		logger.info("modelAttribute : " + model.asMap().toString());
		return "showMessage";
	};

	/**
	 * RequestMapping보다 먼저 수행된다.
	 * 
	 * @param model
	 */
	@ModelAttribute
	public void addAttributes(Model model) {
		model.addAttribute("msg", "Welcome to My Country");
	}

	/**
	 * 명시적으로 Model & View 리턴
	 * 
	 * @param model
	 * @param param
	 * @return
	 */
	@RequestMapping("hello6")
	public ModelAndView hello6(Model model, HelloParam param) {
		ModelAndView mov = new ModelAndView();
		mov.setViewName("showMessage");

		logger.info("message value : {}", param.getMessage());
		model.addAttribute("message", param.getMessage());
		return mov;
	};
}

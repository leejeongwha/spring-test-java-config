package com.naver.test.mvc.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.support.RequestContextUtils;

import com.naver.test.mvc.model.HelloParam;

/**
 * Redirect 테스트
 * 
 * @author nhn
 *
 */
@Controller
@RequestMapping("mvc")
public class RedirectController {
	private final Logger logger = LoggerFactory.getLogger(RedirectController.class);

	/**
	 * GET 방식으로 전달
	 * 
	 * @param model
	 * @param param
	 * @return
	 */
	@RequestMapping("redirect")
	public String redirect(Model model, HelloParam param) {
		logger.info("redirect {}", param.getMessage());
		model.addAttribute("message", param.getMessage());
		return "redirect:/mvc/target";
	};

	/**
	 * GET 방식으로 전달
	 * 
	 * @param param
	 * @param redirectAttr
	 * @return
	 */
	@RequestMapping("redirect1")
	public String redirect1(HelloParam param, RedirectAttributes redirectAttr) {
		logger.info("redirect1 : {}", param.getMessage());
		redirectAttr.addAttribute("message", param.getMessage());
		return "redirect:/mvc/target";
	};

	/**
	 * session에 값을 저장하는 방식으로 전달, redirect 후 즉시 삭제
	 * 
	 * @param param
	 * @param redirectAttr
	 * @return
	 */
	@RequestMapping("redirect2")
	public String redirect2(HelloParam param, RedirectAttributes redirectAttr) {
		logger.info("redirect2 : {}", param.getMessage());
		redirectAttr.addFlashAttribute("message", param.getMessage());
		return "redirect:/mvc/target";
	};

	@RequestMapping("target")
	public String target(Model model, HttpServletRequest request, HelloParam param) {
		String message = "";

		logger.info("target");

		if (!StringUtils.isEmpty(param.getMessage())) {
			logger.info("param.getMessage() is not empty : {}", message);
			message = param.getMessage();
		} else {
			Map<String, ?> fm = RequestContextUtils.getInputFlashMap(request);
			if (fm != null) {
				message = (String) fm.get("message");
				logger.info("flashMap is not empty : {}", message);
			}
		}

		model.addAttribute("message", message);
		return "redirect";
	};
}

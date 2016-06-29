package com.naver.test.websock.controller;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class WebSocketController {
	private final Logger logger = LoggerFactory.getLogger(WebSocketController.class);

//	@RequestMapping("/socket/page")
	@RequestMapping("/socket/page/{pan}/{userId}")
	public ModelAndView page(@PathVariable("pan") String pan,
			@PathVariable("userId") String userId, HttpServletRequest request) {
//		return "socket";
        ModelAndView mav = new ModelAndView();
        mav.addObject("pan", pan);
        mav.addObject("id", userId);
        mav.setViewName("socket");
        return mav;
	};
}

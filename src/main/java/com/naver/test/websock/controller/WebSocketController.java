package com.naver.test.websock.controller;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class WebSocketController {
	private final Logger logger = LoggerFactory.getLogger(WebSocketController.class);

	@RequestMapping("/socket/page")
	public String page(HttpServletRequest request) {
		return "socket";
	};
}

package com.naver.test.websock.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.naver.test.websock.model.Message;

@Controller
public class StompController {
	private final Logger logger = LoggerFactory.getLogger(StompController.class);

	@RequestMapping("stomp")
	public String stomp() {
		return "stomp";
	}

	@MessageMapping("/hello")
	@SendTo("/topic/greetings")
	public Message greeting(Message message) {
		logger.info("stomp message received!!");
		message.setContent("stomp echo : " + message.getContent());
		return message;
	}
}

package com.naver.test.websock.controller;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.naver.test.websock.model.Message;

@Controller
public class StompController {
	private final Logger logger = LoggerFactory.getLogger(StompController.class);

	@Autowired
	public SimpMessageSendingOperations messagingTemplate;

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

	/**
	 * 10초마다 broadcasting
	 */
	// @Scheduled(cron = "*/10 * * * * ?")
	public void sendMessage() {
		String strMessage = "현재 시작은 " + new Date() + " 입니다.";
		Message message = new Message();
		message.setContent(strMessage);

		logger.info(strMessage);

		messagingTemplate.convertAndSend("/topic/greetings", message);
	}
}

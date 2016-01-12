package com.naver.test.longpolling.controller;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.async.DeferredResult;

import com.naver.test.websock.model.Message;

@Controller
@RequestMapping("/chat")
public class ChatBroadCastController {
	private final Logger logger = LoggerFactory.getLogger(ChatBroadCastController.class);

	private final Queue<DeferredResult<Message>> responseBodyQueue = new ConcurrentLinkedQueue<DeferredResult<Message>>();

	/**
	 * 아래와 같이 톰캣 서버의 Connector asyncTimeout 설정 늘려줄 필요 있음
	 * <Connector connectionTimeout="20000" port="8080" protocol="HTTP/1.1"
	 * redirectPort="8443" asyncTimeout="90000"/>
	 * 
	 * @return
	 */
	@RequestMapping(value = "/broadcast", method = RequestMethod.GET)
	@ResponseBody
	public DeferredResult<Message> broadcast() {
		DeferredResult<Message> result = new DeferredResult<Message>();

		responseBodyQueue.add(result);

		return result;
	}

	@RequestMapping(value = "/form", method = RequestMethod.GET)
	public String form() {
		return "chat";
	}

	@RequestMapping(value = "/add", method = RequestMethod.GET)
	@ResponseBody
	public String add(Message message) {
		logger.info(message.getContent());

		for (DeferredResult<Message> result : this.responseBodyQueue) {
			result.setResult(message);
			this.responseBodyQueue.remove(result);
		}

		return "success";
	}

	/*
	 * @Scheduled(fixedRate = 5000) public void processQueues() { for
	 * (DeferredResult<Message> result : this.responseBodyQueue) {
	 * result.setResult("Deferred result");
	 * this.responseBodyQueue.remove(result); } }
	 */
}

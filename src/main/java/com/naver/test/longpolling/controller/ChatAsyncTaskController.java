package com.naver.test.longpolling.controller;

import java.util.Queue;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentLinkedQueue;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.async.WebAsyncTask;

import com.naver.test.websock.model.Message;

@Controller
@RequestMapping("/chatAsyncTask")
public class ChatAsyncTaskController {
	private final Logger logger = LoggerFactory.getLogger(ChatAsyncTaskController.class);

	private static Queue<Message> responseBodyQueue = new ConcurrentLinkedQueue<Message>();

	@RequestMapping(value = "/broadcast", method = RequestMethod.GET)
	@ResponseBody
	public WebAsyncTask<Message> broadcast() throws Exception {
		logger.info("broadcast request & check queue size  : {}", responseBodyQueue.size());

		WebAsyncTask<Message> webAsyncTask = new WebAsyncTask<Message>(40000L, // Timeout
				new Callable<Message>() {
					@Override
					public Message call() throws InterruptedException {
						while (true) {
							Thread.sleep(500);
							Message message = responseBodyQueue.peek();
							if (message != null) {
								logger.info("return message : {}", message.getContent());

								Thread.sleep(500);
								responseBodyQueue.clear();

								return message;
							}
						}
					}
				});

		webAsyncTask.onTimeout(new Callable<Message>() {
			@Override
			public Message call() {
				// 비동기 요청이 타임아웃 되었을 때 컨테이너 스레드에 의해 호출
				logger.info("webAsyncTask timeout");
				// TODO 해당 Callable 객체 강제 stop 필요?
				return null;
			}
		});

		return webAsyncTask;
	}

	@RequestMapping(value = "/form", method = RequestMethod.GET)
	public String form() {
		return "chatAsyncTask";
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	@ResponseBody
	public String add(Message message) {
		logger.info("send message : {}", message.getContent());

		responseBodyQueue.add(message);

		return "success";
	}
}

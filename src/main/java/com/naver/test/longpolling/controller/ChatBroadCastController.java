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
@RequestMapping("/chatDeferred")
public class ChatBroadCastController {
	private final Logger logger = LoggerFactory.getLogger(ChatBroadCastController.class);

	private final Queue<DeferredResult<Message>> responseBodyQueue = new ConcurrentLinkedQueue<DeferredResult<Message>>();

	/**
	 * 아래와 같이 톰캣 server.xml의 Connector 엘리먼트의 asyncTimeout 설정 늘려줄 필요 있음. JSP에서
	 * 30초마다 호출하므로 40초로 설정하면 최대 2개의 커넥션 유지(default:10초)
	 * <Connector connectionTimeout="20000" port="8080" protocol="HTTP/1.1"
	 * redirectPort="8443" asyncTimeout="40000"/>
	 * 
	 * @return
	 */
	@RequestMapping(value = "/broadcast", method = RequestMethod.GET)
	@ResponseBody
	public DeferredResult<Message> broadcast() {

		DeferredResult<Message> result = new DeferredResult<Message>();

		result.onCompletion(new Runnable() {
			@Override
			public void run() {
				// timeout 이나 네트웍 에러로 인하여 더이상 사용가능하지 않을 때 컨테이너 스레드에 의해 호출
				logger.info("complete & queue remove : {}", responseBodyQueue.remove(result));
			}
		});

		result.onTimeout(new Runnable() {
			@Override
			public void run() {
				// 비동기 요청이 타임아웃 되었을 때 컨테이너 스레드에 의해 호출
				logger.info("timeout & queue remove : {}", responseBodyQueue.remove(result));
			}
		});

		responseBodyQueue.add(result);

		logger.info("broadcast request & check queue size  : {}", responseBodyQueue.size());

		return result;
	}

	@RequestMapping(value = "/form", method = RequestMethod.GET)
	public String form() {
		return "chatDeferred";
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	@ResponseBody
	public String add(Message message) {
		logger.info("send message : {}", message.getContent());

		for (DeferredResult<Message> result : this.responseBodyQueue) {
			// spring mvc가 관리하는 스레드가 컨테이너 스레드에게 알림
			result.setResult(message);
			responseBodyQueue.remove(result);
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

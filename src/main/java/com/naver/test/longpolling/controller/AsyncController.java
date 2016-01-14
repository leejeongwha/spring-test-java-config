package com.naver.test.longpolling.controller;

import java.util.concurrent.Callable;
import java.util.concurrent.CompletableFuture;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.async.DeferredResult;
import org.springframework.web.context.request.async.WebAsyncTask;

@Controller
@RequestMapping("/async")
public class AsyncController {
	private final Logger logger = LoggerFactory.getLogger(AsyncController.class);

	private String execute() {
		try {
			Thread.sleep(5000);
			logger.info("Slow task executed");
			return "Task finished";
		} catch (InterruptedException e) {
			throw new RuntimeException();
		}
	}

	@RequestMapping("block")
	@ResponseBody
	public String block() {
		logger.info("Request received");
		String result = execute();
		logger.info("Servlet thread released");

		return result;
	}

	@RequestMapping("callable")
	@ResponseBody
	public Callable<String> callable() {
		logger.info("Request received");

		Callable<String> callable = new Callable<String>() {
			@Override
			public String call() throws InterruptedException {
				Thread.sleep(5000);
				logger.info("Slow task executed");
				return "Task finished";
			}
		};

		logger.info("Servlet thread released");

		return callable;
	}

	@RequestMapping("deferredResult")
	@ResponseBody
	public DeferredResult<String> deferredResult() {
		logger.info("Request received");
		DeferredResult<String> deferredResult = new DeferredResult<>();
		CompletableFuture.supplyAsync(() -> execute())
				.whenCompleteAsync((result, throwable) -> deferredResult.setResult(result));
		logger.info("Servlet thread released");

		return deferredResult;
	}

	@RequestMapping("webAsyncTask")
	@ResponseBody
	public WebAsyncTask<String> webAsyncTask() {
		logger.info("Request received");

		WebAsyncTask<String> webAsyncTask = new WebAsyncTask<String>(30000L, // Timeout
				new Callable<String>() {
					@Override
					public String call() throws InterruptedException {
						Thread.sleep(5000);
						logger.info("Slow task executed");
						return "Task finished";
					}
				});

		logger.info("Servlet thread released");

		return webAsyncTask;
	}
}

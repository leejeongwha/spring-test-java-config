package com.naver.test.mvc.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.naver.test.mvc.model.Item;

@Controller
@RequestMapping("mvc")
public class RestUriController {
	private final Logger logger = LoggerFactory.getLogger(RestUriController.class);

	@RequestMapping(value = "/item/{id}", method = { RequestMethod.GET })
	@ResponseBody
	public String get(@PathVariable String id) {
		logger.info("read item : {}", id);

		return id;
	};

	@RequestMapping(value = "/item/{id}", method = { RequestMethod.POST })
	@ResponseBody
	public String post(@PathVariable String id) {
		logger.info("create item : {}", id);

		return id;
	};

	@RequestMapping(value = "/item/{id}", method = { RequestMethod.PUT })
	@ResponseBody
	public String put(@PathVariable String id) {
		logger.info("update item : {}", id);

		return id;
	};

	@RequestMapping(value = "/item/{id}", method = { RequestMethod.DELETE })
	@ResponseBody
	public String delete(@PathVariable String id) {
		logger.info("delete item : {}", id);

		return id;
	};

	@RequestMapping(value = "/items", method = { RequestMethod.GET })
	public String body() {
		return "requestBody";
	};

	@RequestMapping(value = "/items", method = { RequestMethod.POST })
	@ResponseBody
	public String getBody(@RequestBody Item item) {
		logger.info("item id : {}", item.getId());
		logger.info("item id : {}", item.getTitle());
		logger.info("item id : {}", item.getContent());

		return Integer.toString(item.getId());
	};
}

package com.naver.test.cache.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.naver.test.cache.service.CacheService;
import com.naver.test.oxm.model.BoardUser;

@RestController
@RequestMapping("/cache")
public class CacheTestController {
	private final Logger logger = LoggerFactory.getLogger(CacheTestController.class);

	@Autowired
	private CacheService cacheService;

	@RequestMapping("/cacheable")
	public String cacheable() {
		BoardUser boardUser = cacheService.getBoardUser();
		logger.info("return value : {}", boardUser.toString());

		return boardUser.toString();
	};

	@RequestMapping("/cacheEvict")
	public String cacheEvict() {
		cacheService.removeCache();
		return "success";
	};
}

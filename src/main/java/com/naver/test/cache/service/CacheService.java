package com.naver.test.cache.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.naver.test.oxm.model.BoardUser;

@Service
public class CacheService {
	private final Logger logger = LoggerFactory.getLogger(CacheService.class);

	@Cacheable(value = "board")
	public BoardUser getBoardUser() {
		logger.info("Cacheable call!!");

		BoardUser boardUser = new BoardUser();
		boardUser.setId(1L);
		boardUser.setPasswd("1234");
		boardUser.setRole("ADMIN");
		boardUser.setUserName("Jane");
		boardUser.setAge(20);
		return boardUser;
	}

	@CacheEvict(value = "board", allEntries = true)
	public void removeCache() {
		logger.info("CacheEvict call!!");
	}
}

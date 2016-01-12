package com.naver.test.orm;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.naver.test.config.AppConfiguration;
import com.naver.test.orm.bo.BoardUserBO;
import com.naver.test.orm.entity.BoardUser;
import com.naver.test.orm.repository.BoardUserRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = AppConfiguration.class)
public class TransactionTest {
	final static Logger logger = LoggerFactory.getLogger(TransactionTest.class);

	@Autowired
	private BoardUserBO boardUserBO;

	@Autowired
	private BoardUserRepository boardUserRepository;

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void doNotTransaction() {
		List<BoardUser> findAll = null;
		try {
			boardUserBO.doNotTransaction();
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		findAll = boardUserRepository.findAll();
		for (BoardUser user : findAll) {
			logger.info("board user : {} : {}", user.getId(), user.getUserName());
		}
	}

	@Test
	public void doTransaction() {
		List<BoardUser> findAll = null;
		try {
			boardUserBO.doTransaction();
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		findAll = boardUserRepository.findAll();
		for (BoardUser user : findAll) {
			logger.info("board user : {} : {}", user.getId(), user.getUserName());
		}
	}

	@Test
	public void callTransaction() {
		List<BoardUser> findAll = null;
		try {
			boardUserBO.callTransactionMethod();
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		findAll = boardUserRepository.findAll();
		for (BoardUser user : findAll) {
			logger.info("board user : {} : {}", user.getId(), user.getUserName());
		}
	}
}

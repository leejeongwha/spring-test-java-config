package com.naver.test.orm;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.naver.test.config.AppConfiguration;
import com.naver.test.orm.entity.BoardUser;
import com.naver.test.orm.repository.BoardUserRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = AppConfiguration.class)
@Transactional(value = "jpaTransaction")
public class JpaTest {
	final static Logger logger = LoggerFactory.getLogger(JpaTest.class);

	@Autowired
	private BoardUserRepository boardUserRepository;

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void findAllTest() {
		List<BoardUser> findAll = boardUserRepository.findAll();
		for (BoardUser user : findAll) {
			logger.info("board user : {} : {}", user.getId(), user.getUserName());
		}
	}

	@Test
	public void pagingTest() {
		Page<BoardUser> boardUsers = boardUserRepository.findAll(new PageRequest(0, 5));
		logger.info("board user size : {}", boardUsers.getSize());
		for (BoardUser user : boardUsers.getContent()) {
			logger.info("board user : {} : {}", user.getId(), user.getUserName());
		}
	}

	@Test
	public void findByTest() {
		List<BoardUser> findAll = boardUserRepository.findByUserName("tom");
		for (BoardUser user : findAll) {
			logger.info("board user : {} : {}", user.getId(), user.getUserName());
		}
	}

	@Test
	public void betweenTest() {
		List<BoardUser> findAll = boardUserRepository.findByAgeBetween(20, 60);
		for (BoardUser user : findAll) {
			logger.info("board user : {} : {} : {}", user.getId(), user.getUserName(), user.getAge());
		}
	}

	@Test
	public void startingWithTest() {
		List<BoardUser> findAll = boardUserRepository.findByUserNameStartingWith("j");
		for (BoardUser user : findAll) {
			logger.info("board user : {} : {}", user.getId(), user.getUserName());
		}
	}

	@Test
	public void orderByTest() {
		List<BoardUser> findAll = boardUserRepository.findByRoleOrderByUserNameDesc("ADMIN");
		for (BoardUser user : findAll) {
			logger.info("board user : {} : {} : {}", user.getId(), user.getUserName(), user.getRole());
		}
	}

	@Test
	public void queryTest() {
		List<BoardUser> findAll = boardUserRepository.findByUserNameEndsWith("m");
		for (BoardUser user : findAll) {
			logger.info("board user : {} : {}", user.getId(), user.getUserName());
		}
	}

	@Test
	public void queryTest2() {
		List<BoardUser> findAll = boardUserRepository.findByUserNameEndsWith2("m");
		for (BoardUser user : findAll) {
			logger.info("board user : {} : {}", user.getId(), user.getUserName());
		}
	}

	@Test
	public void countByTest() {
		Long count = boardUserRepository.countByUserName("tom");
		logger.info("board user count : {}", count);
	}

	@Test
	public void queryTest3() {
		BoardUser boardUser = new BoardUser();
		boardUser.setUserName("m");

		List<BoardUser> findAll = boardUserRepository.findByUserNameEndsWith3(boardUser);
		for (BoardUser user : findAll) {
			logger.info("board user : {} : {}", user.getId(), user.getUserName());
		}
	}
}

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
import org.springframework.transaction.annotation.Transactional;

import com.naver.test.config.AppConfiguration;
import com.naver.test.orm.entity.BoardUser;
import com.naver.test.orm.mapper.BoardUserMapper;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = AppConfiguration.class)
@Transactional(value = "mybatisTransaction")
public class MybatisTest {
	final static Logger logger = LoggerFactory.getLogger(MybatisTest.class);

	@Autowired
	private BoardUserMapper boadUserMapper;

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void getBoardUserListTest() {
		List<BoardUser> boardUserList = boadUserMapper.getBoardUserList();

		for (BoardUser user : boardUserList) {
			logger.info("board user : {}, {}", user.getId(), user.getUserName());
		}
	}
}

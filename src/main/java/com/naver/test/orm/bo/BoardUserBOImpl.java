package com.naver.test.orm.bo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.naver.test.orm.entity.BoardUser;
import com.naver.test.orm.repository.BoardUserRepository;

@Service
public class BoardUserBOImpl implements BoardUserBO {
	@Autowired
	private BoardUserRepository boardUserRepository;

	@Override
	@Transactional(value = "jpaTransaction", readOnly = false)
	public void doTransaction() {
		BoardUser user = makeDummyData();
		boardUserRepository.save(user);

		throw new RuntimeException("test");
	}

	@Override
	public void doNotTransaction() {
		BoardUser user = makeDummyData();
		boardUserRepository.save(user);

		throw new RuntimeException("test");
	}

	@Override
	public void callTransactionMethod() {
		doTransaction();
	}

	private BoardUser makeDummyData() {
		BoardUser user = new BoardUser();
		user.setId(11L);
		user.setPasswd("passwd11");
		user.setUserName("tommy");
		user.setAge(22);
		user.setRole("TESTER");
		return user;
	}
}

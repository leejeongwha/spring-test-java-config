package com.naver.test.orm.mapper;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.naver.test.orm.entity.BoardUser;

@Repository
public interface BoardUserMapper {
	List<BoardUser> getBoardUserList();
}

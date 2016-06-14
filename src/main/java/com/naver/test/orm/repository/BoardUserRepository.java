package com.naver.test.orm.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.naver.test.orm.entity.BoardUser;

public interface BoardUserRepository extends JpaRepository<BoardUser, Long> {
	@Override
	Page<BoardUser> findAll(Pageable pageable);

	List<BoardUser> findByUserName(String userName);

	List<BoardUser> findByAgeBetween(int startAge, int endAge);

	List<BoardUser> findByUserNameStartingWith(String startingName);

	List<BoardUser> findByRoleOrderByUserNameDesc(String role);

	@Query("select u from BoardUser u where u.userName like %?1")
	List<BoardUser> findByUserNameEndsWith(String firstname);

	@Query("select u from BoardUser u where u.userName like %:firstname")
	List<BoardUser> findByUserNameEndsWith2(@Param("firstname") String firstname);

	Long countByUserName(String userName);

	@Query("select u from BoardUser u where u.userName like %:#{#boardUser.userName}")
	List<BoardUser> findByUserNameEndsWith3(@Param("boardUser") BoardUser boardUser);
}

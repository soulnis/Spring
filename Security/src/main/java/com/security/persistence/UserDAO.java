package com.security.persistence;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.security.domain.UserVO;

@Repository
public class UserDAO {

	@Inject
	private SqlSession session;
	private static String namespace = "com.security.mapper.userMapper";

	public UserVO login(String username) {
		return session.selectOne(namespace+".login", username);
	}

}

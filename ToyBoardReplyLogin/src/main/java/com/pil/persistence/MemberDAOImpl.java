package com.pil.persistence;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.pil.domain.MemberVO;
import com.pil.dto.LoginDTO;

@Repository
public class MemberDAOImpl implements MemberDAO {

	@Inject
	private SqlSession session;

	private static String namespace = "com.pil.mapper.memberMapper";

	@Override
	public MemberVO login(LoginDTO dto) throws Exception {
		return session.selectOne(namespace +".login", dto);
	}

}

package com.pil.persistence;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

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

	@Override
	public void keepLogin(String userid, String sessionId, Date next) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("userid", userid);
		map.put("sessionId", sessionId);
		map.put("next", next);
		session.update(namespace+".keepLogin", map);
	}

	@Override
	public MemberVO checkUserWithSessionKey(String value) {
		return session.selectOne(namespace+".checkUserWithSessionKey", value);
	}

}

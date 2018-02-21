package com.pil.service;

import java.util.Date;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.pil.domain.MemberVO;
import com.pil.dto.LoginDTO;
import com.pil.persistence.MemberDAO;

@Service
public class MemberServiceImpl implements MemberService {

	@Inject
	private MemberDAO dao;

	@Override
	public MemberVO login(LoginDTO dto) throws Exception {
		return dao.login(dto);
	}

	@Override
	public void keepLogin(String userid, String sessionId, Date next) throws Exception {
		dao.keepLogin(userid, sessionId, next);
	}

	@Override
	public MemberVO checkLoginBefore(String value) {
		return dao.checkUserWithSessionKey(value);
	}

}

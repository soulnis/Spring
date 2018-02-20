package com.pil.service;

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

}

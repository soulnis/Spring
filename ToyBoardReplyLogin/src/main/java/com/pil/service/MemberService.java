package com.pil.service;

import java.util.Date;

import com.pil.domain.MemberVO;
import com.pil.dto.LoginDTO;

public interface MemberService {
	public MemberVO login(LoginDTO dto) throws Exception;
	public void keepLogin(String userid, String sessionId, Date next) throws Exception;
	public MemberVO checkLoginBefore(String value);
}

package com.pil.persistence;

import java.util.Date;

import com.pil.domain.MemberVO;
import com.pil.dto.LoginDTO;

public interface MemberDAO {
	public MemberVO login(LoginDTO dto) throws Exception;
	public void keepLogin(String userid, String sessionId, Date next);
	public MemberVO checkUserWithSessionKey(String value);
}

package com.pil.service;

import com.pil.domain.MemberVO;
import com.pil.dto.LoginDTO;

public interface MemberService {
	public MemberVO login(LoginDTO dto) throws Exception;
}

package com.pil.persistence;

import com.pil.domain.MemberVO;
import com.pil.dto.LoginDTO;

public interface MemberDAO {
	public MemberVO login(LoginDTO dto) throws Exception;
}

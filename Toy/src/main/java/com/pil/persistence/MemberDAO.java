package com.pil.persistence;

import com.pil.domain.MemberVO;

public interface MemberDAO {
	
	public String getTime();
	public void insertMember(MemberVO vo);

	public MemberVO readMember(String userid)throws Exception;
	public MemberVO readWithPW(String userid, String userpw)throws Exception;
	
}

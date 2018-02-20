package com.pil.persistence;

import com.pil.domain.MessageVO;

public interface MessageDAO {
	public void create(MessageVO vo) throws Exception;
	public MessageVO readMessage(Integer no) throws Exception;
	public void updateState(Integer no) throws Exception;
}

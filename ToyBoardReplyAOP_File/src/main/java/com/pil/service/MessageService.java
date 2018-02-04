package com.pil.service;

import com.pil.domain.MessageVO;

public interface MessageService {
	public void addMessage(MessageVO vo) throws Exception;
	public MessageVO readMessage(String userid, Integer no) throws Exception;
}

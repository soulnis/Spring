package com.pil.service;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pil.domain.MessageVO;
import com.pil.persistence.MessageDAO;
import com.pil.persistence.PointDAO;

@Service
public class MessageServiceImpl implements MessageService {
	
	@Inject
	private MessageDAO messageDAO;
	
	@Inject
	private PointDAO pointDAO;

	@Override
	public MessageVO readMessage(String userid, Integer no) throws Exception {
		messageDAO.updateState(no);
		pointDAO.updatePoint(userid, 5);
		return messageDAO.readMessage(no);
	}

	@Transactional
	@Override
	public void addMessage(com.pil.domain.MessageVO vo) throws Exception {
		messageDAO.create(vo);
		pointDAO.updatePoint(vo.getSender(), 10);
	}

}

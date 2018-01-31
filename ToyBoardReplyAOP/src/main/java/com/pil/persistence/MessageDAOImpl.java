package com.pil.persistence;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.pil.domain.MessageVO;

@Repository
public class MessageDAOImpl implements MessageDAO {

	@Inject
	private SqlSession session;
	private static String namespace = "com.pil.mapper.MessageMapper";
	
	@Override
	public void create(MessageVO vo) throws Exception {
		session.insert(namespace+".create", vo);
	}

	@Override
	public MessageVO readMessage(Integer no) throws Exception {
		return session.selectOne(namespace+".readMessage", no);
	}

	@Override
	public void updateState(Integer no) throws Exception {
		session.update(namespace+".updateState", no);
	}

}

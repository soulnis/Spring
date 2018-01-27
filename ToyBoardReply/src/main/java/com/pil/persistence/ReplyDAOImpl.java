package com.pil.persistence;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.pil.domain.ReplyVO;

@Repository
public class ReplyDAOImpl implements ReplyDAO {

	@Inject
	private SqlSession session;
	private static String namespace ="com.pil.mapper.ReplyMapper";
	
	@Override
	public List<ReplyVO> list(Integer bno) throws Exception {
		return session.selectList(namespace+".list", bno);
	}

	@Override
	public void create(ReplyVO vo) throws Exception {
		session.insert(namespace+".create", vo);
	}

	@Override
	public void update(ReplyVO vo) throws Exception {
		session.update(namespace+".update", vo);
	}

	@Override
	public void delete(Integer no) throws Exception {
		session.delete(namespace+".delete", no);
	}

}

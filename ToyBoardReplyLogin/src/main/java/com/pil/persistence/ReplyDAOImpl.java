package com.pil.persistence;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.pil.domain.Criteria;
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

	@Override
	public List<ReplyVO> listPage(Integer bno, Criteria cri) throws Exception {
		Map<String, Object> map = new HashMap<>();
		map.put("bno", bno);
		map.put("cri", cri);
		return session.selectList(namespace+".listPage", map);
	}

	@Override
	public int count(Integer bno) throws Exception {
		return session.selectOne(namespace+".count", bno);
	}

	@Override
	public int getBno(Integer no) throws Exception {
		return session.selectOne(namespace+".getBno", no);
	}

}

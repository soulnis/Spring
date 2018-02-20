package com.pil.persistence;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.pil.domain.BoardVO;
import com.pil.domain.Criteria;
import com.pil.domain.SearchCriteria;

@Repository
public class BoardDAOImpl implements BoardDAO {

	@Inject
	private SqlSession session;
	
	private static String namespace = "com.pil.mapper.BoardMapper";

	@Override
	public void create(BoardVO vo) throws Exception {
		session.insert(namespace+".create", vo);
	}

	@Override
	public BoardVO read(Integer no) throws Exception {
		return session.selectOne(namespace+".read", no);
	}

	@Override
	public void update(BoardVO vo) throws Exception {
		session.update(namespace+".update", vo);
	}

	@Override
	public void delete(Integer no) throws Exception {
		session.delete(namespace+".delete", no);
	}

	@Override
	public List<BoardVO> listAll() throws Exception {
		return session.selectList(namespace+".listAll");
	}

	@Override
	public List<BoardVO> listPage(int page) throws Exception {
		if(page <= 0) page = 1;
		page = (page - 1)*10;
		return session.selectList(namespace+".listPage", page);
	}

	@Override
	public List<BoardVO> listCriteria(Criteria cri) throws Exception {
		return session.selectList(namespace+".listCriteria", cri);
	}

	@Override
	public int countPaging(Criteria cri) throws Exception {
		return session.selectOne(namespace+".countPaging", cri);
	}

	@Override
	public List<BoardVO> listSearch(SearchCriteria cri) throws Exception {
		return session.selectList(namespace+".listSearch", cri);
	}

	@Override
	public int listSearchCount(SearchCriteria cri) throws Exception {
		return session.selectOne(namespace+".listSearchCount", cri);
	}

	@Override
	public void updateReplyCnt(Integer no, int amount) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("no", no);
		map.put("amount", amount);
		session.update(namespace+".updateReplyCnt", map);
	}

	@Override
	public void updateViewCnt(Integer no) throws Exception {
		session.update(namespace+".updateViewCnt", no);
	}

	@Override
	public void addAttach(String fullName) throws Exception {
		session.insert(namespace+".addAttach", fullName);
	}

	@Override
	public List<String> getAttach(Integer bno) throws Exception {
		return session.selectList(namespace+".getAttach", bno);
	}

	@Override
	public void deleteAttach(Integer bno) throws Exception {
		session.delete(namespace+".deleteAttach", bno);
	}

	@Override
	public void replaceAttach(String fullName, Integer bno) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("bno", bno);
		map.put("fullName", fullName);
		session.insert(namespace+".replaceAttach", map);
	}

}

package com.pil.persistence;

import java.util.List;
import java.util.concurrent.ExecutionException;

import com.pil.domain.BoardVO;
import com.pil.domain.Criteria;
import com.pil.domain.SearchCriteria;

public interface BoardDAO {
	public void create(BoardVO vo) throws Exception;
	public BoardVO read(Integer no) throws Exception;
	public void update(BoardVO vo) throws Exception;
	public void delete(Integer no) throws Exception;
	public List<BoardVO> listAll() throws Exception;
	public List<BoardVO> listPage(int page) throws Exception;
	public List<BoardVO> listCriteria(Criteria cri) throws Exception;
	public int countPaging(Criteria cri) throws Exception;
	public List<BoardVO> listSearch(SearchCriteria cri) throws Exception;
	public int listSearchCount(SearchCriteria cri) throws Exception;
	public void updateReplyCnt(Integer no, int amount) throws Exception;
	public void updateViewCnt(Integer no) throws Exception;
}

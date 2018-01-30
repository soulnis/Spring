package com.pil.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.pil.domain.BoardVO;
import com.pil.persistence.BoardDAO;

@Service
public class BoardServiceImpl implements BoardService {

	@Inject
	private BoardDAO dao;

	@Override
	public void regist(BoardVO vo) throws Exception {
		dao.create(vo);
	}

	@Override
	public BoardVO read(Integer no) throws Exception {
		return dao.read(no);
	}

	@Override
	public List<BoardVO> list() throws Exception {
		return dao.list();
	}

	@Override
	public void remove(Integer no) throws Exception {
		dao.delete(no);
	}

	@Override
	public void update(BoardVO vo) throws Exception {
		
	}
}

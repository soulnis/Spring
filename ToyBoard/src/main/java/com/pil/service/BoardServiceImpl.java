package com.pil.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Repository;

import com.pil.domain.BoardVO;
import com.pil.persistence.BoardDAO;

@Repository
public class BoardServiceImpl implements BoardService {

	@Inject
	private BoardDAO dao;

	@Override
	public void regist(BoardVO board) throws Exception {
		dao.create(board);
	}

	@Override
	public BoardVO read(Integer no) throws Exception {
		return dao.read(no);
	}

	@Override
	public void modify(BoardVO board) throws Exception {
		dao.update(board);
	}

	@Override
	public void remove(Integer no) throws Exception {
		dao.delete(no);
	}

	@Override
	public List<BoardVO> listAll() throws Exception {
		return dao.listAll();
	}

}

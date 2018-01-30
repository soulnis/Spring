package com.pil.service;

import java.util.List;

import com.pil.domain.BoardVO;

public interface BoardService {
	public void regist(BoardVO vo) throws Exception;
	public BoardVO read(Integer no) throws Exception;
	public List<BoardVO> list() throws Exception;
	public void remove(Integer no) throws Exception;
	public void update(BoardVO vo) throws Exception;
}

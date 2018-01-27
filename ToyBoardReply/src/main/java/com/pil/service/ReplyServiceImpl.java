package com.pil.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.pil.domain.ReplyVO;
import com.pil.persistence.ReplyDAO;

@Service
public class ReplyServiceImpl implements ReplyService {

	@Inject
	private ReplyDAO dao;

	@Override
	public List<ReplyVO> listReply(Integer bno) throws Exception {
		return dao.list(bno);
	}

	@Override
	public void addReply(ReplyVO vo) throws Exception {
		dao.create(vo);
	}

	@Override
	public void modifyReply(ReplyVO vo) throws Exception {
		dao.update(vo);
	}

	@Override
	public void removeReply(Integer no) throws Exception {
		dao.delete(no);
	}

}

package com.pil.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pil.domain.Criteria;
import com.pil.domain.ReplyVO;
import com.pil.persistence.BoardDAO;
import com.pil.persistence.ReplyDAO;

@Service
public class ReplyServiceImpl implements ReplyService {

	@Inject private ReplyDAO replyDAO;
	@Inject private BoardDAO boardDAO;

	@Override
	public List<ReplyVO> listReply(Integer bno) throws Exception {
		return replyDAO.list(bno);
	}

	@Transactional
	@Override
	public void addReply(ReplyVO vo) throws Exception {
		replyDAO.create(vo);
		boardDAO.updateReplyCnt(vo.getBno(), 1);
	}

	@Override
	public void modifyReply(ReplyVO vo) throws Exception {
		replyDAO.update(vo);
	}

	@Transactional
	@Override
	public void removeReply(Integer no) throws Exception {
		int bno = replyDAO.getBno(no);
		replyDAO.delete(no);
		boardDAO.updateReplyCnt(bno, -1);
	}

	@Override
	public List<ReplyVO> listReplyPage(Integer bno, Criteria cri) throws Exception {
		return replyDAO.listPage(bno, cri);
	}

	@Override
	public int count(Integer bno) throws Exception {
		return replyDAO.count(bno);
	}

}

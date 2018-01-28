package com.pil.service;

import java.util.List;

import com.pil.domain.Criteria;
import com.pil.domain.ReplyVO;

public interface ReplyService {
	public List<ReplyVO> listReply(Integer bno) throws Exception;
	public void addReply(ReplyVO vo) throws Exception;
	public void modifyReply(ReplyVO vo) throws Exception;
	public void removeReply(Integer no) throws Exception;
	public List<ReplyVO> listReplyPage(Integer bno, Criteria cri) throws Exception;
	public int count(Integer bno) throws Exception;
}

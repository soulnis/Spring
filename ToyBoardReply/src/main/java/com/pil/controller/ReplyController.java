package com.pil.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.pil.domain.Criteria;
import com.pil.domain.PageMaker;
import com.pil.domain.ReplyVO;
import com.pil.service.ReplyService;

@RestController
@RequestMapping("/replies")
public class ReplyController {
	
	@Inject
	private ReplyService service;
	
	@RequestMapping(value = "", method = RequestMethod.POST)
	public ResponseEntity<String> register(@RequestBody ReplyVO vo) {
		ResponseEntity<String> entity = null;
		try {
			service.addReply(vo);
			entity = new ResponseEntity<String>("SUCCESS", HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			entity = new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
		return entity;
	}
	
	// {bno} Node의 :bno
	@RequestMapping(value="/all/{bno}", method = RequestMethod.GET)
	public ResponseEntity<List<ReplyVO>> list(@PathVariable("bno") Integer bno) {
		ResponseEntity<List<ReplyVO>> entity = null;
		try {
			entity = new ResponseEntity<List<ReplyVO>>(service.listReply(bno), HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			entity = new ResponseEntity<List<ReplyVO>>(HttpStatus.BAD_REQUEST);
		}
		return entity;
	}

	@RequestMapping(value="/{no}", method = { RequestMethod.PUT, RequestMethod.PATCH})
	public ResponseEntity<String> update(@PathVariable("no") Integer no, @RequestBody ReplyVO vo) {
		ResponseEntity<String> entity = null;
		try {
			vo.setNo(no);
			service.modifyReply(vo);
			entity = new ResponseEntity<String>("SUCCESS", HttpStatus.OK);
		} catch (Exception e) {
			entity = new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
		return entity;
	}

	@RequestMapping(value="/{no}", method = {RequestMethod.DELETE})
	public ResponseEntity<String> remove(@PathVariable("no") Integer no) {
		ResponseEntity<String> entity = null;
		try {
			service.removeReply(no);
			entity = new ResponseEntity<String>("SUCCESS", HttpStatus.OK);
		} catch (Exception e) {
			entity = new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
		return entity;	
	}

	@RequestMapping(value="/{bno}/{page}", method = {RequestMethod.GET})
	public ResponseEntity<Map<String, Object>> listPage(@PathVariable("bno") Integer bno, @PathVariable("page") Integer page) {
		ResponseEntity<Map<String, Object>> entity = null;
		Map<String, Object> map = null;
		try {
			Criteria cri = new Criteria();
			cri.setPage(page);
			PageMaker pageMaker = new PageMaker();
			pageMaker.setCri(cri);

			map = new HashMap<String, Object>();
			List<ReplyVO> list = service.listReplyPage(bno, cri);
			
			map.put("list", list);
			int replyCount = service.count(bno);
			pageMaker.setTotalCount(replyCount);
			map.put("pageMaker", pageMaker);

			entity = new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
		} catch (Exception e) {
			map = new HashMap<String, Object>();
			e.printStackTrace();
			map.put("ERROR_MESSAGE", e.getMessage());
			entity = new ResponseEntity<Map<String,Object>>(map, HttpStatus.BAD_REQUEST);
		}
		return entity;
	}
}

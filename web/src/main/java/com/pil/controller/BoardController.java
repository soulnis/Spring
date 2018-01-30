package com.pil.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.pil.domain.BoardVO;
import com.pil.service.BoardService;

@Controller
@RequestMapping("/board/*")
public class BoardController {
	private static final Logger logger = LoggerFactory.getLogger(BoardController.class);
	
	@Inject
	private BoardService service;

	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public void registerGET(BoardVO board, Model model) throws Exception{
		logger.info("register get....");
	}

	@RequestMapping(value ="/register", method = RequestMethod.POST)
	public String registPOST(BoardVO board, Model model) throws Exception {
		logger.info("registPOST...");
		logger.info(board.toString());

		service.regist(board);
		List<Map<String, Object>> list = new ArrayList<>();
		Map<String, Object> map = new HashMap<>();
		map.put("a", "aa");
		map.put("r", "rr");
		map.put("b", "bb");
		list.add(map);


//		model.addAttribute("result", "SUCCESS");
		model.addAttribute("result", list);

//		return "board/success";
		return "redirect:/board/list";
	}

	@RequestMapping(value ="/list", method = RequestMethod.GET)
	public void list(Model model) throws Exception {
		logger.info("List All....");
	}
}

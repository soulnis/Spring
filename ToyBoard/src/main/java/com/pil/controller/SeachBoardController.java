package com.pil.controller;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.pil.domain.BoardVO;
import com.pil.domain.Criteria;
import com.pil.domain.PageMaker;
import com.pil.domain.SearchCriteria;
import com.pil.service.BoardService;

@Controller
@RequestMapping("/sboard/*")
public class SeachBoardController {
	private static Logger logger = LoggerFactory.getLogger(SeachBoardController.class);
	
	@Inject
	private BoardService service;
	
	@RequestMapping(value = "/register", method = RequestMethod.GET) // method = GET와 POST방식의 분리 
	public void registerGET(BoardVO board, Model model) throws Exception {
		logger.info("register get...");
	}

	@RequestMapping(value ="/register", method = RequestMethod.POST)
	public String registPOST(BoardVO board, RedirectAttributes rttr) throws Exception {
		// 자동으로 모든 데이터를 BoardVO로 수집하도록 한다.
		// Model 을 통하여 데이터를 수집
		/*
		 * Model Class
		 * 스프링 MVC에서 제공하는 데이터 전달용 객체
		 * Map와 유사하게 키-벨류 쌍으로 데이터를 저장
		 * */
		logger.info("regist post..");
		logger.info(board.toString());

		service.regist(board); 
		/* 
		 * redirect시 전달정보 숨김처리를 위하여 RedirectAttributes를 활용하였다.
		 * 만일 숨김처리가 필요없는 QueryString로 보낼경우에는 Model model를 활용하여 
		 * model.addAttribute()를 활용하도록 한다.
		*/
		rttr.addFlashAttribute("msg", "SUCCESS"); 

		// 결과값을 WEB-INF/views/board/success.jsp로 전달한다.
		// URL 확인 잘하기!
		// return "board/success"; 
		return "redirect:/sboard/list";
	}

	@RequestMapping(value ="/list", method = RequestMethod.GET)
	public void listPage(@ModelAttribute("cri") SearchCriteria cri, Model model) throws Exception {
		logger.info(cri.toString());
//		model.addAttribute("list", service.listCriteria(cri));
		model.addAttribute("list", service.listSearchCriteria(cri));
		PageMaker pageMaker = new PageMaker();
		pageMaker.setCri(cri);
//		pageMaker.setTotalCount(service.listCountCriteria(cri));
		pageMaker.setTotalCount(service.listSearchCount(cri));
		model.addAttribute("pageMaker", pageMaker);
	}

	@RequestMapping(value ="/readPage", method = RequestMethod.GET)
	public void read(@RequestParam("no") int no, @ModelAttribute("cri") SearchCriteria cri, Model model) throws Exception {
		model.addAttribute(service.read(no));
	}

	@RequestMapping(value = "/removePage", method = RequestMethod.POST)
	public String removePage(@RequestParam("no") int no, SearchCriteria cri, RedirectAttributes rttr) throws Exception {
		service.remove(no);
		rttr.addAttribute("page", cri.getPage());
		rttr.addAttribute("perPageNum", cri.getPerPageNum());
		rttr.addAttribute("searchType", cri.getSearchType());
		rttr.addAttribute("keyword", cri.getKeyword());
		rttr.addFlashAttribute("msg", "SUCCESS");
		return "redirect:/sboard/list";
	}

	@RequestMapping(value = "/modifyPage", method = RequestMethod.GET)
	public void modifyGET(int no, Model model, @ModelAttribute("cri") SearchCriteria cri) throws Exception {
		model.addAttribute(service.read(no));
	}

	@RequestMapping(value = "/modifyPage", method = RequestMethod.POST)
	public String modifyPOST(BoardVO board, SearchCriteria cri, RedirectAttributes rttr) throws Exception {
		logger.info("modify post........");
		service.modify(board);
		rttr.addAttribute("page", cri.getPage());
		rttr.addAttribute("perPageNum", cri.getPerPageNum());
		rttr.addAttribute("searchType", cri.getSearchType());
		rttr.addAttribute("keyword", cri.getKeyword());
		rttr.addFlashAttribute("msg", "SUCCESS");
		return "redirect:/sboard/list";
	}
}

package com.pil.controller;

import javax.inject.Inject;

import org.apache.maven.plugin.lifecycle.Execution;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.pil.domain.BoardVO;
import com.pil.service.BoardService;

@Controller
@RequestMapping("/board/*")
public class BoardController {
	private static final Logger logger = LoggerFactory.getLogger(BoardController.class);
	
	@Inject
	private BoardService service;

	/*
	 * return 결과로 register을 뿌려주지는 않지만
	 * 기본적으로 WEB-INF/views/ 까지 들어오게 되어있다.
	 * 추가적으로 value 값으로 /register을 설정 해 주었기 때문에 자동으로 WEB-INF/views/register.jsp와 맵핑되어 실행되어진다.
	 * */
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
		return "redirect:listAll";
	}

	@RequestMapping(value = "/listAll", method = RequestMethod.GET)
	public void listAll(Model model) throws Exception {
		logger.info("show all list.....");
		model.addAttribute("list", service.listAll());
	}

	@RequestMapping(value = "/read", method = RequestMethod.GET)
	public void read(@RequestParam("no") int no, Model model) throws Exception {
		model.addAttribute(service.read(no));
	}

	@RequestMapping(value = "/remove", method = RequestMethod.POST)
	public String remove(@RequestParam("no") int no, RedirectAttributes rttr) throws Exception {
		service.remove(no);
		rttr.addFlashAttribute("msg", "SUCCESS");
		return "redirect:listAll";
	}

	@RequestMapping(value = "/modify", method = RequestMethod.GET)
	public void modifyGET(int no, Model model) throws Exception {
		model.addAttribute(service.read(no));
	}

	@RequestMapping(value = "/modify", method = RequestMethod.POST)
	public String modifyPOST(BoardVO board, RedirectAttributes rttr) throws Exception {
		logger.info("mod post........");
		service.modify(board);
		rttr.addFlashAttribute("msg", "SUCCESS");
		return "redirect:listAll";
	}










}

package com.pil.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.pil.dto.LoginDTO;
import com.pil.service.MemberService;

@Controller
@RequestMapping("/member")
public class MemberController {

	@Inject
	private MemberService service;

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public void loginGET(@ModelAttribute("dto") LoginDTO dto) {

	}

	@RequestMapping(value = "/loginPost", method = RequestMethod.POST)
	public void loginPOST(HttpServletRequest req, HttpSession session, Model model) throws Exception {
		StringBuilder sb = new StringBuilder();
		Map<String, String[]> paramMap = req.getParameterMap();
		for (Map.Entry<String, String[]> e : paramMap.entrySet()) {
			sb.append("(").append(e.getKey()).append(":");
			String[] value = e.getValue();
			if (value.length > 1) {
				sb.append("[");
				for (int i = 0; i < value.length; i++) {
					if (i != 0) {
						sb.append(",");
					}
					sb.append(value[i]);
				}
				sb.append("]");
			} else {
				sb.append(value[0]);
			}
			sb.append(")");
		}
		System.out.println("request.body: " + sb.toString());
	}

	// @RequestMapping(value = "/loginPost", method = RequestMethod.POST)
	// public void loginPOST(LoginDTO dto, HttpSession session, Model model,
	// HttpServletRequest req) throws Exception {
	// process(req);
	//// System.out.println(req+"==");
	//// System.out.println(req.toString()+" reqtostring");
	// MemberVO vo = service.login(dto);
	//// System.out.println("결과 컨트롤러: vo:"+vo);
	// System.out.println(dto+", dto===");
	//// System.out.println(vo.getUserid()+", vo.getUserid()===");
	//// System.out.println(session.getId()+", session.getId()===");
	// if(vo == null) {
	// return ;
	// }
	//// System.out.println(dto.isUseCookie()+", dto.isUseCookie()===");
	// if(dto.isUseCookie()) {
	// int amount = 60*60*24*7;
	// Date sessionLimit = new Date(System.currentTimeMillis()+(1000*amount));
	//
	//// System.out.println(sessionLimit+", sessionLimit===");
	//
	// service.keepLogin(vo.getUserid(), session.getId(), sessionLimit);
	// }
	// model.addAttribute("memberVO", vo);
	// }
}

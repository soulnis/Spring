package com.security.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login(@RequestParam(value = "error", required = false) String error, Model model) {
		if(error != null) {
			model.addAttribute("error", error);
			
		}
		return "login";
	}

	@PreAuthorize("authenticated")
	@RequestMapping(value = "/mypage", method = RequestMethod.GET)
	public String mypage(Model model) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		model.addAttribute("user_name", auth.getName());
		return "mypage";
	}

}

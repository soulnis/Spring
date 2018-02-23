package com.security.controller;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.security.domain.UserVO;
import com.security.service.HomeService;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {

	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

	@Inject
	private HomeService service;

	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@Secured({"ROLE_USER", "ROLE_ADMIN"})
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model, @RequestParam(value="user", defaultValue="", required=true) String user) {
		logger.info("Welcome home! The client locale is {}.", locale);

		UserVO userVO = new UserVO();
		userVO.setUser_name(user);
		service.getUser(userVO);

		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);

		String formattedDate = dateFormat.format(date);

		model.addAttribute("serverTime", formattedDate);

		return "home";
	}

	@Secured("ROLE_ADMIN")
	@RequestMapping(value = "/admin", method = RequestMethod.GET)
	public String admin() {
		return "admin";
	}

	@Secured("ROLE_USER")
	@RequestMapping(value = "/admin/test", method = RequestMethod.GET)
	public String admin_test() {
		return "admin";
	}

	@Secured({"ROLE_USER", "ROLE_ADMIN"})
	public String user() {
		return "admin";
	}
}

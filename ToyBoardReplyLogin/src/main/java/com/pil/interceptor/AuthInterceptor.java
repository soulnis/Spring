package com.pil.interceptor;

import javax.inject.Inject;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.springframework.web.util.WebUtils;

import com.pil.domain.MemberVO;
import com.pil.service.MemberService;

public class AuthInterceptor extends HandlerInterceptorAdapter {

	private static final Logger logger = LoggerFactory.getLogger(AuthInterceptor.class);

	@Inject
	private MemberService service;
	
	@Override
	public boolean preHandle(HttpServletRequest req, HttpServletResponse res, Object handler) throws Exception {
		HttpSession session = req.getSession();
		if(session .getAttribute("login") == null) {
			logger.info("current user is not logined");
			saveDest(req);
			Cookie loginCookie = WebUtils.getCookie(req, "loginCookie");
			if(loginCookie != null) {
				MemberVO memberVO = service.checkLoginBefore(loginCookie.getValue());
				logger.info("MEMBERVO: "+memberVO);
				if(memberVO != null) {
					session.setAttribute("login", memberVO);
					return true;
				}
			}
			res.sendRedirect("/member/login");
			return false;
		}
		return true;
	}

	private void saveDest(HttpServletRequest req) {
		String uri = req.getRequestURI();
		String query = req.getQueryString();

		if(query == null || query.equals("null")) {
			query ="";
		} else {
			query = "?"+query;
		}

		if(req.getMethod().equals("GET")) {
			logger.info("dest: "+(uri+query));
			req.getSession().setAttribute("dest", uri+query);
		}
	}
}

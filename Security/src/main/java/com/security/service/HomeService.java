package com.security.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.security.domain.UserVO;

@Service
public class HomeService {
	private static final Logger logger = LoggerFactory.getLogger(HomeService.class);

	@PreAuthorize("#userVO.user_name == authentication.name or hasRole('ROLE_ADMIN')")
	public UserVO getUser(UserVO userVO) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		System.out.println(auth.toString());

		UserVO user = (UserVO)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		System.out.println(user.toString());

		return userVO;
	}
}

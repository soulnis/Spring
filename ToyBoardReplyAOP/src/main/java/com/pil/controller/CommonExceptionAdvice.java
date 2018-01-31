package com.pil.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

/*
 * Error 관련 컨트롤러.
 * ControllerAdvice클래스의 메소드는 발생한 Exception 객체의 타입만을 파라미터로 사용 가능하다.
 * 따라서 일반 Controller와 같이 Model을 파라미터로 사용하는 것은 지원하지 않기 때문에 직접 ModelAndView타입을 사용하는 형태로 작성해야 함.
 * */
@ControllerAdvice
public class CommonExceptionAdvice {
	private static final Logger logger = LoggerFactory.getLogger(CommonExceptionAdvice.class);

	@ExceptionHandler(Exception.class)
	public ModelAndView errorModelAndView(Exception ex) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("board/error_common");
		modelAndView.addObject("exception", ex);
		return modelAndView;
	}
}

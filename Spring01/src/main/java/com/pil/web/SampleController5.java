package com.pil.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import domain.ProductVO;

@Controller
public class SampleController5 {

	@RequestMapping("doJSON")
	public @ResponseBody ProductVO doJSON() {
		ProductVO vo = new ProductVO("product", 1000);
		return vo;
	}
}

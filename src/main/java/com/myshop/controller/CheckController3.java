package com.myshop.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.myshop.util.ProductVO;

@Controller
@RequestMapping("/check/*")
public class CheckController3 {

	private static final Logger logger = LoggerFactory.getLogger(CheckController3.class);
	
	@RequestMapping(value="check7", method=RequestMethod.GET)
	public String getCheck7(ProductVO pro, Model model) throws Exception{
		model.addAttribute("pro", pro);
		return "check/check7";
	}
	
	@RequestMapping(value="check7.do", method=RequestMethod.POST)
	public String postCheck7(@Validated @ModelAttribute ProductVO pro, Model model, BindingResult result) throws Exception{
		logger.debug("상품명 : "+pro.getPname());
		//model.Attribute("pro", pro);
		String path = "check/result7";
		if(result.hasErrors()) {
			path = "check/error7";
		}
		return path;
	}
}

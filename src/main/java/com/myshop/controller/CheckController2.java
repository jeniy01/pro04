package com.myshop.controller;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.myshop.util.MemberVO;

@Controller
@RequestMapping("/check/*")
public class CheckController2 {

	@RequestMapping(value="check6", method=RequestMethod.GET)
	public String getcheck6(HttpServletRequest request, Model model) throws Exception{
		return "check/check6";
	}
	
	//@Valid에 의한 검증
	@RequestMapping(value="check6.do", method=RequestMethod.POST)
	public String postCheck6(@Valid MemberVO memberVO, BindingResult result) throws Exception{
		String path = "check/result6";
		if(result.hasErrors()) {
			path="check/error6";
		}
		return path;
	}
}

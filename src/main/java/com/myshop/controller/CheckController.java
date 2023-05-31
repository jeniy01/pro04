package com.myshop.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.myshop.dto.MemberDTO;
import com.myshop.util.MemberValidator;

@Controller
@RequestMapping("/check/*")
public class CheckController {

	@GetMapping("check1")
	public String check1(HttpServletRequest request, Model model) throws Exception{
		return "check/check1";
	}
	
	//input 태그의 pattern 속성을 이요한 폼 검증
	@PostMapping("check1")
	public String postCheck1(HttpServletRequest request, Model model) throws Exception{
		String id = request.getParameter("id");
		String pw = request.getParameter("pw");
		model.addAttribute("id", id);
		model.addAttribute("pw", pw);
		return "check/result1";
	}
	
	@GetMapping("check2")
	public String check2(HttpServletRequest request, Model model) throws Exception{
		return "check/check2";
	}
	
	//자바스크립트에 의한 폼 검증
	@PostMapping("check2")
	public String postCheck2(@RequestParam("id") String id, @RequestParam("pw") String pw, Model model) throws Exception{
		model.addAttribute("id", id);
		model.addAttribute("pw", pw);
		return "check/result2";
	}
	
	@GetMapping("check3")
	public String check3(HttpServletRequest request, Model model) throws Exception{
		return "check/check3";
	}
	
	//jQuery에 의한 폼 검증
	@GetMapping("check3.do")	//GetMapping이란 이름이 위랑 똑같아서 오류날 수 있으므로 .do를 붙여서 이름 바꿔주기
	public String postCheck3(@RequestParam("id") String id, @RequestParam("pw") String pw, Model model) throws Exception{
		model.addAttribute("id", id);
		model.addAttribute("pw", pw);
		return "check/result3";
	}
	
	@GetMapping("check4")
	public String check4(HttpServletRequest request, Model model) throws Exception{
		return "check/check4";
	}
	
	//validation-api를 활용한 폼 검증
	@PostMapping("check4")
	public String postCheck4(@ModelAttribute("member") MemberDTO member, Model model, BindingResult result) throws Exception{
		String page = "check/result4";
		MemberValidator memValidator = new MemberValidator();
		memValidator.validate(member, result);
		if(result.hasErrors()) {
			page = "check/error4";
		}
		return page;
	}
	
	@GetMapping("check5")
	public String check5(HttpServletRequest request, Model model) throws Exception{
		return "check/check5";
	}
	
	MemberValidator memVal = new MemberValidator();
	
	//Validator에 의한 검증(@Valid+@InitBinder)
	@RequestMapping(value="check5", method = RequestMethod.POST)
	public String postCheck5(@ModelAttribute MemberDTO member, Model model, BindingResult result, Errors errors) throws Exception{
		String path = "check/result5";
		memVal.validate(member, result);
		if(result.hasErrors()) {
			path = "check/error5";
		} // 또는 if(errors.hasErrors()){ path = "check/error5";
		return path;
	}
	
	@InitBinder
	protected void initBinder(WebDataBinder binder) {
		binder.setValidator(new MemberValidator());
	}
}

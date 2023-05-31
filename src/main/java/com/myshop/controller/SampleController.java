package com.myshop.controller;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.myshop.dto.MemberDTO;

@Controller
@RequestMapping("/sample/*")
public class SampleController {

	@RequestMapping("main")
	public String sample(Locale locale, Model model) {
		return "sample/main";
	}
	
	//GET 방식
	
	//@GetMapping
	@GetMapping("get1")
	public String get1(HttpServletRequest request, Model model) {
		String id = request.getParameter("id");
		String pw = request.getParameter("pw");
		model.addAttribute("id", id);
		model.addAttribute("pw", pw);
		return "sample/get1";
	}
	
	//@RequestMapping
	@RequestMapping(value="get2", method=RequestMethod.GET)
	public String get2(HttpServletRequest request, Model model) {
		String id = request.getParameter("id");
		String pw = request.getParameter("pw");
		model.addAttribute("id", id);
		model.addAttribute("pw", pw);
		return "sample/get2";
	}
	
	//@RequestParam
	@RequestMapping(value="get3", method=RequestMethod.GET)
	public String get3(@RequestParam("id") String id, @RequestParam("pw") String pw, Model model) {
		model.addAttribute("id", id);
		model.addAttribute("pw", pw);
		return "sample/get3";
	}
	
	//DTO나 VO에 의한 데이터 전달은 RequestMethod가 POST로만 전달해야함
	//value, method, model
	@RequestMapping(value="get4", method=RequestMethod.GET)
	public String get4(MemberDTO member, Model model) {
		model.addAttribute("id", member.getId());
		model.addAttribute("pw", member.getPw());
		return "sample/get4";
	}
	
	//가장 간편한 방법
	//@ModelAttribute
	@RequestMapping(value="get5", method=RequestMethod.GET)
	public String get5(@ModelAttribute("mem") MemberDTO member, Model model) {
		return "sample/get5";
	}
	
	//최근에 가장 많이 쓰는 방법
	//@PathVariable - 주로 GET 방식으로 씀
	@RequestMapping(value="get6/{id}/{pw}", method=RequestMethod.GET)
	public String get6(@PathVariable("id") String id, @PathVariable("pw") String pw, Model model) {
		model.addAttribute("id", id);
		model.addAttribute("pw", pw);
		return "sample/get6";
	}
	
	//ModelAndView
	@RequestMapping(value="get7", method=RequestMethod.GET)
	public ModelAndView get7(@RequestParam("id") String id, @RequestParam("pw") String pw, ModelAndView mav) {
		mav.addObject("id", id);	//필드가 아닌 객체도 허용
		mav.addObject("pw", pw);
		mav.setViewName("sample/get7");
		return mav;
	}
	
	
	//POST 방식
	
	//@PostMapping
	@PostMapping("post1")
	public String post1(HttpServletRequest request, Model model) {
		String id = request.getParameter("id");
		String pw = request.getParameter("pw");
		model.addAttribute("id", id);
		model.addAttribute("pw", pw);
		return "sample/post1";
	}
	
	//@RequestMapping
	@RequestMapping(value="post2", method=RequestMethod.POST)
	public String post2(HttpServletRequest request, Model model) {
		String id = request.getParameter("id");
		String pw = request.getParameter("pw");
		model.addAttribute("id", id);
		model.addAttribute("pw", pw);
		return "sample/post2";
	}
	
	//@RequestParam
	@RequestMapping(value="post3", method=RequestMethod.POST)
	public String post3(@RequestParam("id") String id, @RequestParam("pw") String pw, Model model) {
		model.addAttribute("id", id);
		model.addAttribute("pw", pw);
		return "sample/post3";
	}
	
	//DTO나 VO에 의한 데이터 전달은 RequestMethod가 POST로만 전달해야함
	//value, method, model
	@RequestMapping(value="post4", method=RequestMethod.POST)
	public String post4(MemberDTO member, Model model) {
		model.addAttribute("id", member.getId());
		model.addAttribute("pw", member.getPw());
		return "sample/post4";
	}
	
	//가장 간편한 방법
	//@ModelAttribute
	@PostMapping("post5")
	public String post5(@ModelAttribute("mem") MemberDTO member, Model model) {
		return "sample/post5";
	}
	
	//최근에 가장 많이 쓰는 방법
	//@PathVariable - 주로 GET 방식으로 씀
	@RequestMapping(value="post6/{id}/{pw}", method=RequestMethod.POST)
	public String post6(@PathVariable("id") String id, @PathVariable("pw") String pw, Model model) {
		model.addAttribute("id", id);
		model.addAttribute("pw", pw);
		return "sample/post6";
	}
	
	//ModelAndView
	@RequestMapping(value="post7", method=RequestMethod.POST)
	public ModelAndView post7(@RequestParam("id") String id, @RequestParam("pw") String pw, ModelAndView mav) {
		mav.addObject("id", id);	//필드가 아닌 객체도 허용
		mav.addObject("pw", pw);
		mav.setViewName("sample/post7");
		return mav;
	}
}

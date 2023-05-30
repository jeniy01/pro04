package com.myshop.controller;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.myshop.dto.MemberDTO;
import com.myshop.service.MemberService;

@Controller
@RequestMapping("/member/*")
public class MemberController {

	private static final Logger logger = LoggerFactory.getLogger(MemberController.class);
	
	@Autowired
	MemberService memberService;
	
	@Autowired
	HttpSession session;
	
	//약관 페이지 로딩
	@GetMapping("agree.do")
	public String getAgree(Model model) throws Exception {
		return "member/agree";
	}
	
	//회원가입 폼 로딩
	@GetMapping("join.do")
	public String joinForm(Model model) throws Exception {
		return "member/memberInsert";
	}
	
	//아이디 중복체크
	@RequestMapping(value="idCheck.do", method=RequestMethod.POST)
	public void idCheck(HttpServletResponse response, HttpServletRequest request, Model model) throws Exception {
		String id = request.getParameter("id");
		boolean result = false;
		MemberDTO dto = memberService.getMember(id);

		if(dto!=null){	//이미 있는 아이디임
			result = false;
		} else {
			result = true;
		}
		JSONObject json = new JSONObject();
		json.put("result", result);
		PrintWriter out = response.getWriter();
		out.println(json.toString());
	}
	
	//회원 가입 - 회원 가입 처리
	@RequestMapping(value="insert.do", method = RequestMethod.POST)
	public String memberWrite(MemberDTO member, Model model) throws Exception {
		memberService.memberInsert(member);
		return "redirect:/";
	}
	
	//로그인 폼 로딩 (RequestMethod 기술하지 않으면, 기본이 GET임)
	@RequestMapping("loginForm.do")  
	public String memberLoginForm(Model model) throws Exception {
		return "member/loginForm";
	}
	
	//로그인 	- 컨트롤러에서 세션 처리
	@RequestMapping(value="signin.do", method = RequestMethod.POST)
	public String memberSignin(@RequestParam String id, @RequestParam String pw, HttpServletRequest req, RedirectAttributes rttr) throws Exception {
		session.invalidate();
		MemberDTO mdto = new MemberDTO();
		mdto.setPw(pw);
		mdto.setId(id);
		MemberDTO login = memberService.signIn(mdto);
		if(login!=null && login.getPw().equals(mdto.getPw())) {
			session.setAttribute("member", login);
			session.setAttribute("sid", id);
			return "redirect:/";
		} else {
			return "redirect:loginForm.do";
		}
	} 
	
	//로그인 - Service에서 세션 처리
	@RequestMapping(value="login.do", method = RequestMethod.POST)
	public String memberLogin(MemberDTO mdto, HttpServletRequest req, RedirectAttributes rttr) throws Exception {
		MemberDTO member = memberService.login(mdto);
		if(member!=null) {		
			return "home";
		} else {
			return "redirect:loginForm.do";
		}
	}
	
	//로그인 : DAO에서 처리
	@RequestMapping(value="loginCheck.do", method = RequestMethod.POST)
	public String memberLoginCtrl(MemberDTO mdto, RedirectAttributes rttr) throws Exception {
		session.getAttribute("member");
		MemberDTO member = memberService.loginCheck(mdto);
		if(member!=null) {
			logger.info("로그인 성공");
			session.setAttribute("member", member);
			session.setAttribute("sid", member.getId());
			rttr.addFlashAttribute("msg", "로그인 성공");
			return "redirect:/";
		} else {
			logger.info("로그인 실패");
			session.setAttribute("member", null);
			rttr.addFlashAttribute("msg", false);
			return "redirect:loginForm.do";
		}
	}
	
	//로그아웃
	@RequestMapping("logout.do")
	public String memberLogout(HttpSession session) throws Exception {
		session.invalidate();
		return "redirect:/";
	}
	
}

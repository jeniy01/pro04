package com.myshop.controller;

import java.io.PrintWriter;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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
	
	@Inject
	BCryptPasswordEncoder pwdEncoder;
	
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
	
	
	//회원 가입 - 회원 가입 처리시 암호화하여 비밀번호 저장
	@RequestMapping(value="insert.do", method = RequestMethod.POST)
	public String memberWrite(MemberDTO member, Model model) throws Exception {
		member.setPw(pwdEncoder.encode(member.getPw()));
		logger.info("비밀번호 암호화 : "+pwdEncoder.encode(member.getPw()));
		memberService.memberInsert(member);
		return "redirect:/";
	}
	
	//로그인 폼 로딩 (RequestMethod 기술하지 않으면, 기본이 GET임)
	@RequestMapping("loginForm.do")  
	public String memberLoginForm(Model model) throws Exception {
		return "member/loginForm";
	}
	
	//로그인 	- 컨트롤러에서 세션 처리(로그인시 저장된 비밀번호와 입력된 비밀번호를 비교 - matches)
	@RequestMapping(value="signin.do", method = RequestMethod.POST)
	public String memberSignin(@RequestParam String id, @RequestParam String pw, HttpServletRequest request, RedirectAttributes rttr) throws Exception {
		session.invalidate();
		MemberDTO mdto = new MemberDTO();
		mdto.setPw(pw);  //mdto.setPw(request.getParameter("pw"));
		mdto.setId(id);	//mdto.setPw(request.getParameter("id"));
		MemberDTO login = memberService.signIn(mdto);
		boolean loginSuccess = pwdEncoder.matches(mdto.getPw(), login.getPw());
		if(login!=null && loginSuccess) {
			session.setAttribute("member", login);
			session.setAttribute("sid", id);
			return "redirect:/";
		} else {
			return "redirect:loginForm.do";
		}
	} 
	
	//로그인 폼 로딩 (RequestMethod 기술하지 않으면, 기본이 GET임)
	@RequestMapping("loginForm2.do")  
	public String memberLoginForm2(Model model) throws Exception {
		return "member/loginForm2";
	}
	
	//로그인 - Service에서 세션 처리
	@RequestMapping(value="login.do", method = RequestMethod.POST)
	public String memberLogin(MemberDTO mdto, HttpServletRequest req, RedirectAttributes rttr) throws Exception {
		//MemberDTO member = memberService.login(mdto);
		boolean loginSuccess = memberService.login(req);
		if(loginSuccess) {		
			return "home";
		} else {
			return "redirect:loginForm2.do";
		}
	}
	
	//로그인 폼 로딩 (RequestMethod 기술하지 않으면, 기본이 GET임)
	@RequestMapping("loginForm3.do")  
	public String memberLoginForm3(Model model) throws Exception {
		return "member/loginForm3";
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
	
	//회원목록
	@RequestMapping(value="list.do", method = RequestMethod.GET)
	public String memberList(Model model) throws Exception {
		List<MemberDTO> memberList = memberService.memberList();
		model.addAttribute("memberList", memberList);
		return "member/memberList";
	}
	
	/* 관리자 회원 정보 보기 */
	@RequestMapping(value="info.do", method = RequestMethod.GET)
	public String getMember(@RequestParam String id, Model model) throws Exception {
		MemberDTO member = memberService.getMember(id);
		model.addAttribute("member", member);
		return "member/memberDetail";
	}
	
	/* 일반회원 정보보기 */
	@RequestMapping(value="read.do", method = RequestMethod.GET)
	public String memberRead(Model model, HttpServletRequest request) throws Exception {
		String id = (String) session.getAttribute("sid");
		MemberDTO member = memberService.getMember(id);
		model.addAttribute("member", member);
		return "member/memberRead";
	}
	
	//회원 탈퇴
	@RequestMapping("delete.do")
	public String memberDelete(@RequestParam String id, Model model, HttpSession session) throws Exception {
		memberService.memberDelete(id);
		memberLogout(session);
		return "redirect:/";
	}
	
	//회원 정보 변경
	@RequestMapping(value="update.do", method = RequestMethod.POST)
	public String memberUpdate(MemberDTO mdto, Model model) throws Exception {
		String pwd = pwdEncoder.encode(mdto.getPw());
		mdto.setPw(pwd);
		memberService.memberUpdate(mdto);
		return "redirect:/";
	}
}

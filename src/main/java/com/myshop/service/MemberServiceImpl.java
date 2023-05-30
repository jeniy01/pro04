package com.myshop.service;

import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.myshop.dao.MemberDAO;
import com.myshop.dto.MemberDTO;

@Service
public class MemberServiceImpl implements MemberService {

	@Autowired
	MemberDAO memberDao;
	
	@Inject
	BCryptPasswordEncoder pwdEncoder;
	
	//회원 목록
	@Override
	public List<MemberDTO> memberList() throws Exception {
		return memberDao.memberList();
	}

	//회원수
	@Override
	public int memberCount() throws Exception {
		return memberDao.memberCount();
	}

	//특정 회원정보
	@Override
	public MemberDTO getMember(String id) throws Exception {
		return memberDao.getMember(id);
	}

	//로그인 : 컨트롤러에서 로그인 처리
	@Override
	public MemberDTO signIn(MemberDTO mdto) throws Exception {
		return memberDao.signIn(mdto);
	}

	//로그인 : DAO에서 로그인 처리
	@Override
	public MemberDTO loginCheck(MemberDTO mdto) throws Exception {
		return memberDao.loginCheck(mdto);
	}

	//로그인 : AJax로 서비스에서 로그인 처리
	@Override
	public boolean login(HttpServletRequest req) throws Exception {
		boolean loginSuccess = false;
		MemberDTO mdto = new MemberDTO();
		mdto.setId(req.getParameter("id"));
		mdto.setPw(req.getParameter("pw"));
		HttpSession session = req.getSession();
		MemberDTO login = memberDao.login(mdto);
		loginSuccess = pwdEncoder.matches(mdto.getPw(), login.getPw());
		if(loginSuccess && login!=null) {
			session.setAttribute("member", login);
			session.setAttribute("sid", mdto.getId());
			loginSuccess = true;
		}
		return loginSuccess;
	}

	//회원 가입
	@Override
	public void memberInsert(MemberDTO mdto) throws Exception {
		memberDao.memberInsert(mdto);
	}

	//회원정보 수정
	@Override
	public void memberUpdate(MemberDTO mdto) throws Exception {
		memberDao.memberUpdate(mdto);
	}

	//회원 탈퇴
	@Override
	public void memberDelete(String id) throws Exception {
		memberDao.memberDelete(id);
	}
}

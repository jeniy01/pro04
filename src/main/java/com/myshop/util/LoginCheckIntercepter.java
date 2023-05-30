package com.myshop.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class LoginCheckIntercepter extends HandlerInterceptorAdapter {
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		//세션이 있는 경우만 servlet-context.xml의 interceptor에 기재된 내용 접근이 가능함.
		boolean flag = false;
		HttpSession session = request.getSession(false);
		//세션이 있는(로그인한) 경우 true, 세션이 없는(로그인을 하지 않은) 경우 false
		if(session!=null&&session.getAttribute("member")!=null){
			flag=true;
			System.out.println("**핸들러 인터셉터 실행**"+request.getRequestURI());
		} else {
			flag=false;
			System.out.println("핸들러 인터셉터 : 세션이 만료되어 home으로 리다이렉트");
			response.sendRedirect("../member/loginForm.do");
		}
		return flag;
	}
}

package com.myshop.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class AdminIntercepter extends HandlerInterceptorAdapter {
    //메인 액션이 실행되기 전
    @Override
    public boolean preHandle(HttpServletRequest request
            , HttpServletResponse response, Object handler) 
                    throws Exception {
        HttpSession session = request.getSession();          //세션 객체 생성
        String sid = (String) session.getAttribute("sid");
        if(session.getAttribute("sid")==null) {          //세션이 없으면(로그인되지 않은 상태)
            response.sendRedirect("/");			            //메인 페이지로 이동
            return false; 
        }
        
        if(sid.equals("admin")) {
            return true; //메인 액션으로 이동(매핑된 url)
        } else {
            response.sendRedirect("/");   //메인 페이지로 이동
            return false; 
        }
    }
    @Override
    public void postHandle(HttpServletRequest request  //메인 액션이 실행된 후
            , HttpServletResponse response, Object handler,
            ModelAndView modelAndView) throws Exception {
        super.postHandle(request, response, handler, modelAndView);
    }
}
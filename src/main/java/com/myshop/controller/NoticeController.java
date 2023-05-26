package com.myshop.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.myshop.dto.NoticeDTO;
import com.myshop.service.NoticeService;

@Controller
@RequestMapping("/notice/*")
public class NoticeController {
	
	@Autowired
	private NoticeService noticeService;
	
	@GetMapping("list.do")	//  <a href="${path1 }/notice/list.do">목록</a>
	public String getNoticeList(Model model) throws Exception {
		List<NoticeDTO> noticeList = noticeService.noticeList();
		model.addAttribute("noticeList", noticeList);
		return "notice/noticeList";  //  /WEB-INF/views/notice/noticeList.jsp
	}
	
	@GetMapping("detail.do")
	public String getNotice(HttpServletRequest request, Model model) throws Exception {
		int no = Integer.parseInt(request.getParameter("no"));
		noticeService.visitCount(no);
		NoticeDTO notice = noticeService.noticeDetail(no);
		model.addAttribute("notice", notice);
		return "notice/noticeDetail";  //  /WEB-INF/views/notice/noticeDetail.jsp
	}
	
	@GetMapping("insert.do")
	public String insertNotice() {
		return "notice/noticeInsert";
	}
	
	@PostMapping("insert.do")
	public String insertProNotice(HttpServletRequest request) throws Exception {
		NoticeDTO dto = new NoticeDTO();
		dto.setTitle(request.getParameter("title"));
		dto.setContent(request.getParameter("content"));
		dto.setAuthor("admin");
		noticeService.noticeInsert(dto);
		return "redirect:list.do";
	}
	
	@GetMapping("delete.do")
	public String noticeDelete(HttpServletRequest request, Model model) throws Exception {
		int no = Integer.parseInt(request.getParameter("no"));
		noticeService.noticeDelete(no);
		return "redirect:list.do";
	}
	
	@GetMapping("edit.do")
	public String noticeEdit(HttpServletRequest request, Model model) throws Exception {
		int no = Integer.parseInt(request.getParameter("no"));
		NoticeDTO dto = noticeService.noticeDetail(no);
		model.addAttribute("dto", dto);
		return "notice/noticeEdit";
	}
	
	@PostMapping("edit.do")
	public String noticeEditPro(HttpServletRequest request, Model model) throws Exception {
		int no = Integer.parseInt(request.getParameter("no"));
		NoticeDTO dto = new NoticeDTO();
		dto.setNo(no);
		dto.setTitle(request.getParameter("title"));
		dto.setContent(request.getParameter("content"));
		noticeService.noticeEdit(dto);
		return "redirect:list.do";
	}
}

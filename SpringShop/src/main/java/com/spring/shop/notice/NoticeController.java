package com.spring.shop.notice;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class NoticeController {
	
	@Autowired
	NoticeService noticeService;
	
	// notice ������ �̵�
	@RequestMapping(value = "/notice", method = RequestMethod.GET)
	public String getNoticePage(HttpServletRequest req) {
		req.setAttribute("content", "notice/notice.jsp");
		return "home";
	}	
	
	// �� �ۼ� ������ �̵�
	@RequestMapping(value = "/notice/write-page", method = RequestMethod.GET)
	public String getNoticeWritePage(HttpServletRequest req) {
		req.setAttribute("content", "notice/write.jsp");
		return "home";
	}
	
}

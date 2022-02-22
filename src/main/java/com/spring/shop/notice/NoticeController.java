package com.spring.shop.notice;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.spring.shop.signUp.UserDTO;



@Controller
public class NoticeController {

	@Autowired
	NoticeService noticeService;

	// notice ������ �̵�
	@RequestMapping(value = "/notice", method = RequestMethod.GET)
	public String getNoticePage(NoticeDTO dto, HttpServletRequest req) {

		List<NoticeDTO> noticeList = noticeService.getNoticeListAll(dto);

		if (noticeList.isEmpty()) {
			req.setAttribute("MSG", "�Խñ��� �����ϴ�");
			req.setAttribute("content", "notice/notice.jsp");
			
		} else {
			req.setAttribute("notice", noticeList);
			req.setAttribute("content", "notice/notice.jsp");
			
		}
		return "home";
	}
	
	// notice ������ �̵�
	@RequestMapping(value = "/getNoticeList", method = RequestMethod.POST)
	@ResponseBody
	public List<NoticeDTO> getNoticeList(NoticeDTO dto, HttpServletRequest req) {
		List<NoticeDTO> noticeList = noticeService.getNoticeListAll(dto);
		return noticeList;
	}
	
	
	
	

	// �Խñ� ���� ������ �̵�
	@RequestMapping(value = "/notice/noticeInfo", method = RequestMethod.GET)
	public String getNoticeInfo(@RequestParam("ni_no") int ni_no, NoticeDTO dto, HttpServletRequest req) {
		// �� ��ȣ�� �Ѱ�����Ѵ�
		NoticeDTO noticeInfo = noticeService.getNotice(ni_no);
		
		req.setAttribute("notice", noticeInfo);
		req.setAttribute("content", "notice/noticeInfo.jsp");
		
		return "home";
	}

	// �� �ۼ� ������ �̵�
	@RequestMapping(value = "/notice/write", method = RequestMethod.GET)
	public String noticeWritePage(HttpServletRequest req) {
		
		req.setAttribute("content", "notice/write.jsp");
		
		return "home";
	}

	// �Խñ� ����ϱ�
	@RequestMapping(value = "/notice/notice-write", method = RequestMethod.POST)
	public String noticeInsert(NoticeDTO dto, HttpServletRequest req) {
		
		int noticeInsert = noticeService.noticeInsert(dto, req);
		
		if(noticeInsert > 0) {
			req.setAttribute("MSG", "�Խñ� ��� �Ϸ�!");
		}else {
			req.setAttribute("MSG", "�Խñ� ��� ����!");
		}
		
		List<NoticeDTO> noticeList = noticeService.getNoticeListAll(dto);
		req.setAttribute("notice", noticeList);
		req.setAttribute("content", "notice/notice.jsp");
		
		return "home";
	}
	
	// �Խñ� �����ϱ�
	@RequestMapping(value ="/notice/notice-delete", method = RequestMethod.POST)
	public String noticeDelete(int ni_no, NoticeDTO dto,HttpServletRequest req) {
		int rst = noticeService.noticeDelete(ni_no);
		// �߰� ��� writer�� ������ �ƴ� ��� ���� ���� �ʰ�
		
		if(rst > 0) {
			req.setAttribute("MSG", "�Խñ��� ���������� �����Ǿ����ϴ�.");
			req.setAttribute("content", "notice/notice.jsp");
		} else {
			req.setAttribute("MSG", "�Խñ� ������ �����߽��ϴ�.");
			req.setAttribute("content", "notice/noticeInfo.jsp");
		}
		
		List<NoticeDTO> noticeList = noticeService.getNoticeListAll(dto);
		req.setAttribute("notice", noticeList);
		
		return "home";
	}
	
	// �Խñ� ���� ������ �̵�
	@RequestMapping(value = "/notice/notice-updatePage", method = RequestMethod.GET)
	public String noticeUpdate(NoticeDTO dto ,HttpServletRequest req) {
		// �Խñ� ���� �������� �Ѿ�� 
		NoticeDTO noticeInfo = noticeService.getNotice(dto.getNi_no()); // �Խñ� �ϳ� select
		
		req.setAttribute("notice", noticeInfo);
		req.setAttribute("content", "notice/noticeUpdate.jsp");
		
		return "home";
	}
	
	// �Խñ� �����ϱ�
	@RequestMapping(value = "/notice/notice-update", method = RequestMethod.POST)
	public String getnoticeUpdate(NoticeDTO dto, HttpServletRequest req) {
		
		dto.getNi_no();
		int rst = noticeService.noticeUpdate(dto, req);
		
		if(rst > 0) { // �����۾� �Ϸ�
			req.setAttribute("MSG", "�Խñ��� ���������� ����Ǿ����ϴ�.");
			NoticeDTO noticeInfo = noticeService.getNotice(dto.getNi_no()); // �Խñ� �ϳ� select
			req.setAttribute("notice", noticeInfo);
			req.setAttribute("content", "notice/noticeInfo.jsp");
		} else {
			req.setAttribute("MSG", "�Խñ� ������ �����߽��ϴ�.");
			req.setAttribute("content", "notice/noticeUpdate.jsp");
		}
		
		return "home";
	}
}

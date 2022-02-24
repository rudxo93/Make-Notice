package com.spring.shop.notice;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
public class NoticeController {

	@Autowired
	NoticeService noticeService;

	// notice 페이지 이동
	@RequestMapping(value = "/notice", method = RequestMethod.GET)
	public String getNoticePage(NoticeDTO dto, HttpServletRequest req) {
		req.setAttribute("content", "notice/notice.jsp");
		return "home";
	}

	// 페이징
	@RequestMapping(value = "/getNoticeList", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> getNoticeList(HttpServletRequest req) {
		Map<String, Object> noticeList = noticeService.getNoticeListAll(req);
		return noticeList;
	}

	// 게시글 정보 페이지 이동
	@RequestMapping(value = "/notice/noticeInfo", method = RequestMethod.GET)
	public String getNoticeInfo(@RequestParam("ni_no") int ni_no, NoticeDTO dto, HttpServletRequest req) {
		// 글 번호를 넘겨줘야한다
		NoticeDTO noticeInfo = noticeService.getNotice(ni_no);

		req.setAttribute("notice", noticeInfo);
		req.setAttribute("content", "notice/noticeInfo.jsp");

		return "home";
	}

	// 글 작성 페이지 이동
	@RequestMapping(value = "/notice/write", method = RequestMethod.GET)
	public String noticeWritePage(HttpServletRequest req) {

		req.setAttribute("content", "notice/write.jsp");

		return "home";
	}

	// 게시글 등록하기
	@RequestMapping(value = "/notice/notice-write", method = RequestMethod.POST)
	public String noticeInsert(NoticeDTO dto, HttpServletRequest req) {

		int noticeInsert = noticeService.noticeInsert(dto, req);

		if (noticeInsert > 0) {
			req.setAttribute("MSG", "게시글 등록 완료!");
		} else {
			req.setAttribute("MSG", "게시글 등록 실패!");
		}

		req.setAttribute("content", "notice/notice.jsp");

		return "home";
	}

	// 게시글 삭제하기
	@RequestMapping(value = "/notice/notice-delete", method = RequestMethod.POST)
	public String noticeDelete(int ni_no, NoticeDTO dto, HttpServletRequest req) {
		int rst = noticeService.noticeDelete(ni_no);
		// 추가 기능 writer가 본인이 아닐 경우 삭제 되지 않게

		if (rst > 0) {
			req.setAttribute("MSG", "게시글이 정상적으로 삭제되었습니다.");
			req.setAttribute("content", "notice/notice.jsp");
		} else {
			req.setAttribute("MSG", "게시글 삭제를 실패했습니다.");
			req.setAttribute("content", "notice/noticeInfo.jsp");
		}

		return "home";
	}

	// 게시글 수정 페이지 이동
	@RequestMapping(value = "/notice/notice-updatePage", method = RequestMethod.GET)
	public String noticeUpdate(NoticeDTO dto, HttpServletRequest req) {
		// 게시글 수정 페이지로 넘어갈때
		NoticeDTO noticeInfo = noticeService.getNotice(dto.getNi_no()); // 게시글 하나 select

		req.setAttribute("notice", noticeInfo);
		req.setAttribute("content", "notice/noticeUpdate.jsp");

		return "home";
	}

	// 게시글 수정하기
	@RequestMapping(value = "/notice/notice-update", method = RequestMethod.POST)
	public String getnoticeUpdate(NoticeDTO dto, HttpServletRequest req) {

		dto.getNi_no();
		int rst = noticeService.noticeUpdate(dto, req);

		if (rst > 0) { // 수정작업 완료
			req.setAttribute("MSG", "게시글이 정상적으로 변경되었습니다.");
			NoticeDTO noticeInfo = noticeService.getNotice(dto.getNi_no()); // 게시글 하나 select
			req.setAttribute("notice", noticeInfo);
			req.setAttribute("content", "notice/noticeInfo.jsp");
		} else {
			req.setAttribute("MSG", "게시글 변경이 실패했습니다.");
			req.setAttribute("content", "notice/noticeUpdate.jsp");
		}

		return "home";
	}
}

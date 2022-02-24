package com.spring.shop.notice;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.shop.commons.Page;
import com.spring.shop.signUp.UserDTO;

@Service
public class NoticeService {

	@Autowired
	NoticeDAO noticeDAO;
	
	@Autowired
	Page page;
	

	// 게시글 조회
	public Map<String, Object> getNoticeListAll(HttpServletRequest req) {
		Map<String, Object> result = new HashMap<String, Object>();
		
		int totalCnt = noticeDAO.getTotalCnt();
		int pagePerCnt = Integer.parseInt(req.getParameter("pagePerCnt"));
		int curPage = Integer.parseInt(req.getParameter("curPage"));
		
		Map<String, Object> param =  page.pageGenerator(totalCnt, pagePerCnt, curPage);
		List<NoticeDTO> list =  noticeDAO.getNoticeListAll(param);
		
		result.put("list", list);
		result.put("paging", param);
		
		return result;
	}

	// 게시글 정보 페이지 이동
	public NoticeDTO getNotice(int ni_no) {
		// 게시글 번호 필요
		return noticeDAO.getNotice(ni_no);
	}

	// 게시글 작성하기
	public int noticeInsert(NoticeDTO dto, HttpServletRequest req) {
		// 우선 유저 세션에서 유저 id값 가져와서 writer에 넣어준다.
		UserDTO user = (UserDTO) req.getSession().getAttribute("loginMember");
		String sessionId = user.getMi_id();

		dto.setNi_writer(sessionId);
		dto.setNi_title(req.getParameter("ni_title"));
		dto.setNi_content(req.getParameter("ni_content"));
		
		// 비로그인이라면 ? 글 작성이 되지 않고 로그인 페이지로 이동
		
		return noticeDAO.noticeInsert(dto);
	}

	// 게시글 삭제하기
	public int noticeDelete(int ni_no) {
		return noticeDAO.noticeDelete(ni_no);
	}

	// 게시글 업데이트
	public int noticeUpdate(NoticeDTO dto, HttpServletRequest req) {
		// 업데이트 시 게시글 넘버 -> 게시글 제목, 내용(수정한 것) writer과 넘버가 없다 지금
		dto.setNi_title(req.getParameter("ni_title"));
		dto.setNi_content(req.getParameter("ni_content"));

		return noticeDAO.noticeUpdate(dto);
	}
}

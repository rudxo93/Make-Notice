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
	

	// �Խñ� ��ȸ
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

	// �Խñ� ���� ������ �̵�
	public NoticeDTO getNotice(int ni_no) {
		// �Խñ� ��ȣ �ʿ�
		return noticeDAO.getNotice(ni_no);
	}

	// �Խñ� �ۼ��ϱ�
	public int noticeInsert(NoticeDTO dto, HttpServletRequest req) {
		// �켱 ���� ���ǿ��� ���� id�� �����ͼ� writer�� �־��ش�.
		UserDTO user = (UserDTO) req.getSession().getAttribute("loginMember");
		String sessionId = user.getMi_id();

		dto.setNi_writer(sessionId);
		dto.setNi_title(req.getParameter("ni_title"));
		dto.setNi_content(req.getParameter("ni_content"));
		
		// ��α����̶�� ? �� �ۼ��� ���� �ʰ� �α��� �������� �̵�
		
		return noticeDAO.noticeInsert(dto);
	}

	// �Խñ� �����ϱ�
	public int noticeDelete(int ni_no) {
		return noticeDAO.noticeDelete(ni_no);
	}

	// �Խñ� ������Ʈ
	public int noticeUpdate(NoticeDTO dto, HttpServletRequest req) {
		// ������Ʈ �� �Խñ� �ѹ� -> �Խñ� ����, ����(������ ��) writer�� �ѹ��� ���� ����
		dto.setNi_title(req.getParameter("ni_title"));
		dto.setNi_content(req.getParameter("ni_content"));

		return noticeDAO.noticeUpdate(dto);
	}
}

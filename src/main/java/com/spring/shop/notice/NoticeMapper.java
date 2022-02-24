package com.spring.shop.notice;

import java.util.List;
import java.util.Map;

public interface NoticeMapper {
	// 게시글 list 보여주기
	public abstract List<NoticeDTO> getNoticeListAll(Map<String, Object> param);
	// 게시글 정보 페이지 이동
	public abstract NoticeDTO getNotice(int ni_no);
	// 게시글 작성하기
	public abstract int noticeInsert(NoticeDTO dto);
	// 게시글 삭제하기
	public abstract int noticeDelete(int ni_no);
	// 게시글 업데이트
	public abstract int noticeUpdate(NoticeDTO dto);
	// 총 게시글 수 구하기
	public abstract int getTotalCnt();
	
	
}

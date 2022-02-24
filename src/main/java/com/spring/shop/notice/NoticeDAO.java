package com.spring.shop.notice;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class NoticeDAO {

	@Autowired
	SqlSession ss;
	
	// 게시글 list 보여주기
	public List<NoticeDTO> getNoticeListAll(Map<String, Object> param){
		return ss.getMapper(NoticeMapper.class).getNoticeListAll(param);
	}
	// 게시글 정보 페이지 이동
	public NoticeDTO getNotice(int ni_no) {
		return ss.getMapper(NoticeMapper.class).getNotice(ni_no);
	}
	// 게시글 작성
	public int noticeInsert(NoticeDTO dto) {
		return ss.getMapper(NoticeMapper.class).noticeInsert(dto);
	}
	// 게시글 삭제하기
	public int noticeDelete(int ni_no) {
		return ss.getMapper(NoticeMapper.class).noticeDelete(ni_no);
	}
	// 게시글 업데이트
	public int noticeUpdate(NoticeDTO dto) {
		return ss.getMapper(NoticeMapper.class).noticeUpdate(dto);
	}
	// 총 게시글 수 구하기
	public int getTotalCnt() {
		return ss.getMapper(NoticeMapper.class).getTotalCnt();
	}
	
}
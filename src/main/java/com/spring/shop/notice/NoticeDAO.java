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
	
	// �Խñ� list �����ֱ�
	public List<NoticeDTO> getNoticeListAll(Map<String, Object> param){
		return ss.getMapper(NoticeMapper.class).getNoticeListAll(param);
	}
	// �Խñ� ���� ������ �̵�
	public NoticeDTO getNotice(int ni_no) {
		return ss.getMapper(NoticeMapper.class).getNotice(ni_no);
	}
	// �Խñ� �ۼ�
	public int noticeInsert(NoticeDTO dto) {
		return ss.getMapper(NoticeMapper.class).noticeInsert(dto);
	}
	// �Խñ� �����ϱ�
	public int noticeDelete(int ni_no) {
		return ss.getMapper(NoticeMapper.class).noticeDelete(ni_no);
	}
	// �Խñ� ������Ʈ
	public int noticeUpdate(NoticeDTO dto) {
		return ss.getMapper(NoticeMapper.class).noticeUpdate(dto);
	}
	// �� �Խñ� �� ���ϱ�
	public int getTotalCnt() {
		return ss.getMapper(NoticeMapper.class).getTotalCnt();
	}
	
}
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
	public List<NoticeDTO> getNoticeListAll(Map<String, Object> param) {
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


	// �Խñ� ������Ʈ
	public int noticeUpdate(NoticeDTO dto) {
		return ss.getMapper(NoticeMapper.class).noticeUpdate(dto);
	}
	
	// ÷������ ������Ʈ
	public int boardUpate(Map<String, Object> param2) {
		return ss.getMapper(NoticeMapper.class).boardUpate(param2);
	}

	// �� �Խñ� �� ���ϱ�
	public int getTotalCnt(Map<String, Object> param) {
		return ss.getMapper(NoticeMapper.class).getTotalCnt(param);
	}
	
	// ���� ���ε�
	public int insertBoardAttach(Map<String, Object> param) {
		return ss.getMapper(NoticeMapper.class).insertBoardAttach(param);
	}
	
	// �Խñ� �����ϱ�
	public int noticeDelete(int ni_no) {
		return ss.getMapper(NoticeMapper.class).noticeDelete(ni_no);
	}
	
	// ÷������ �׸� ��������
	public NoticeDTO getBoard(int ni_no) {
		return ss.getMapper(NoticeMapper.class).getBoard(ni_no);
	}
	
	// ÷������ �����ϱ�
	public int boardDelete(String saved_file_name) {
		return ss.getMapper(NoticeMapper.class).boardDelete(saved_file_name);
	}
}
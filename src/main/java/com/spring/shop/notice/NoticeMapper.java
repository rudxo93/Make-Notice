package com.spring.shop.notice;

import java.util.List;
import java.util.Map;

public interface NoticeMapper {
	// �Խñ� list �����ֱ�
	public abstract List<NoticeDTO> getNoticeListAll(Map<String, Object> param);

	// �Խñ� ���� ������ �̵�
	public abstract NoticeDTO getNotice(int ni_no);

	// �Խñ� �ۼ��ϱ�
	public abstract int noticeInsert(NoticeDTO dto);
	
	// ���� ���ε�
	public abstract int insertBoardAttach(Map<String, Object> param);

	// �Խñ� ������Ʈ
	public abstract int noticeUpdate(NoticeDTO dto);
	
	//÷������ ������Ʈ
	public abstract int boardUpate(Map<String, Object> param2);

	// �� �Խñ� �� ���ϱ�
	public abstract int getTotalCnt(Map<String, Object> param);
	
	// �Խñ� �����ϱ�
	public abstract int noticeDelete(int ni_no);
	
	// ÷������ �׸� ��������
	public abstract NoticeDTO getBoard(int ni_no);
	
	// ÷������ �����ϱ�
	public abstract int boardDelete(String saved_file_name);
}

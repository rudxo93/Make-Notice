package com.spring.shop.notice;

import java.util.List;

public interface NoticeMapper {
	// �Խñ� list �����ֱ�
	public abstract List<NoticeDTO> getNoticeListAll(NoticeDTO dto);
	// �Խñ� ���� ������ �̵�
	public abstract NoticeDTO getNotice(int ni_no);
	// �Խñ� �ۼ��ϱ�
	public abstract int noticeInsert(NoticeDTO dto);
	// �Խñ� �����ϱ�
	public abstract int noticeDelete(int ni_no);
	// �Խñ� ������Ʈ
	public abstract int noticeUpdate(NoticeDTO dto);
	
}

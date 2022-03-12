package com.spring.shop.gallery;

import java.util.List;
import java.util.Map;

public interface GalleryMapper {
//==========================================================================
	// ������ List �����ֱ�
	public abstract List<GalleryDTO> getGalleryListAll(Map<String, Object>param);
	
	// ������ ���� ������ �̵�
	public abstract GalleryDTO getGallery(int gi_no);
	
	// �� ������ �� ���ϱ�
	public abstract int getTotalCnt(Map<String, Object>param);
//==========================================================================
	// ������ �Խñ� �ۼ�
	public abstract int galleryInsert(GalleryDTO dto);
	
	// ������ ���� ���� ���ε�
	public abstract int galleryInsertBoard(Map<String, Object>param);
//==========================================================================
}

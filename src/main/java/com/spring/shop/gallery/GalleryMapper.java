package com.spring.shop.gallery;

import java.util.List;
import java.util.Map;

public interface GalleryMapper {
//==========================================================================
	// 갤러리 List 보여주기
	public abstract List<GalleryDTO> getGalleryListAll(Map<String, Object>param);
	
	// 갤러리 정보 페이지 이동
	public abstract GalleryDTO getGallery(int gi_no);
	
	// 총 갤러리 수 구하기
	public abstract int getTotalCnt(Map<String, Object>param);
//==========================================================================
	// 갤러리 게시글 작성
	public abstract int galleryInsert(GalleryDTO dto);
	
	// 갤러리 다중 파일 업로드
	public abstract int galleryInsertBoard(Map<String, Object>param);
//==========================================================================
}

package com.spring.shop.gallery;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


@Repository
public class GalleryDAO {
	
	@Autowired
	SqlSession ss;
//==========================================================================
	// 갤러리 List 보여주기
	public List<GalleryDTO> getGalleryListAll(Map<String, Object>param) {
		return ss.getMapper(GalleryMapper.class).getGalleryListAll(param);
	}
	
	// 갤러리 정보 페이지 이동
	public GalleryDTO getGallery(int gi_no) {
		return ss.getMapper(GalleryMapper.class).getGallery(gi_no);
	}
	
	// 총 갤러리 수 구하기
	public int getTotalCnt(Map<String, Object>param) {
		return ss.getMapper(GalleryMapper.class).getTotalCnt(param);
	}
//==========================================================================
	// 갤러리 게시글 작성
	public int galleryInsert(GalleryDTO dto) {
		return ss.getMapper(GalleryMapper.class).galleryInsert(dto);
	}
	
	// 갤러리 다중 파일 업로드
	public int galleryInsertBoard(Map<String, Object>param) {
		return ss.getMapper(GalleryMapper.class).galleryInsertBoard(param);
	}
//==========================================================================
	
}

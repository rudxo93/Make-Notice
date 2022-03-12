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
	// ������ List �����ֱ�
	public List<GalleryDTO> getGalleryListAll(Map<String, Object>param) {
		return ss.getMapper(GalleryMapper.class).getGalleryListAll(param);
	}
	
	// ������ ���� ������ �̵�
	public GalleryDTO getGallery(int gi_no) {
		return ss.getMapper(GalleryMapper.class).getGallery(gi_no);
	}
	
	// �� ������ �� ���ϱ�
	public int getTotalCnt(Map<String, Object>param) {
		return ss.getMapper(GalleryMapper.class).getTotalCnt(param);
	}
//==========================================================================
	// ������ �Խñ� �ۼ�
	public int galleryInsert(GalleryDTO dto) {
		return ss.getMapper(GalleryMapper.class).galleryInsert(dto);
	}
	
	// ������ ���� ���� ���ε�
	public int galleryInsertBoard(Map<String, Object>param) {
		return ss.getMapper(GalleryMapper.class).galleryInsertBoard(param);
	}
//==========================================================================
	
}

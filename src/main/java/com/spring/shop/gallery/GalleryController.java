package com.spring.shop.gallery;

import java.io.IOException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
public class GalleryController {
	
	@Autowired
	GalleryService galleryService; 
	
	// 갤러리 페이지 이동
	@RequestMapping(value = "/gallery", method = RequestMethod.GET)
	public String getGalleryPage(HttpServletRequest req) {
		req.setAttribute("content", "gallery/gallery.jsp");
		return "home";
	}
	
	// 갤러리 페이징
	@RequestMapping(value = "/getGalleryList", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> getGalleryList(HttpServletRequest req) {
		Map<String, Object> galleryList = galleryService.getGalleryListAll(req);
		return galleryList;
	}
	
	
	// 갤러리 작성 페이지 이동
	@RequestMapping(value = "/gallery/write", method = RequestMethod.GET)
	public String galleryWritePage(HttpServletRequest req) {
		
		req.setAttribute("content", "gallery/galleryWrite.jsp");
		
		return "home";
	}
	
	// 갤러리 게시글 등록하기
	@RequestMapping(value = "/gallery/gallery-write", method = RequestMethod.POST) 
	public String galleryInsert(GalleryDTO dto ,HttpServletRequest req) throws IOException {		
		int rst = galleryService.galleryInsert(dto, req);
		
		if(rst > 0) {
			req.setAttribute("MSG", "게시글 작성 완료!");
			req.setAttribute("content", "gallery/gallery.jsp");
		} else {
			req.setAttribute("MSG", "게시글 작성 실패!");
			req.setAttribute("content", "gallery/galleryWriter.jsp");
		}
		
		return "home";
		
	}
}

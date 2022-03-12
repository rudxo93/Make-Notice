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
	
	// ������ ������ �̵�
	@RequestMapping(value = "/gallery", method = RequestMethod.GET)
	public String getGalleryPage(HttpServletRequest req) {
		req.setAttribute("content", "gallery/gallery.jsp");
		return "home";
	}
	
	// ������ ����¡
	@RequestMapping(value = "/getGalleryList", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> getGalleryList(HttpServletRequest req) {
		Map<String, Object> galleryList = galleryService.getGalleryListAll(req);
		return galleryList;
	}
	
	
	// ������ �ۼ� ������ �̵�
	@RequestMapping(value = "/gallery/write", method = RequestMethod.GET)
	public String galleryWritePage(HttpServletRequest req) {
		
		req.setAttribute("content", "gallery/galleryWrite.jsp");
		
		return "home";
	}
	
	// ������ �Խñ� ����ϱ�
	@RequestMapping(value = "/gallery/gallery-write", method = RequestMethod.POST) 
	public String galleryInsert(GalleryDTO dto ,HttpServletRequest req) throws IOException {		
		int rst = galleryService.galleryInsert(dto, req);
		
		if(rst > 0) {
			req.setAttribute("MSG", "�Խñ� �ۼ� �Ϸ�!");
			req.setAttribute("content", "gallery/gallery.jsp");
		} else {
			req.setAttribute("MSG", "�Խñ� �ۼ� ����!");
			req.setAttribute("content", "gallery/galleryWriter.jsp");
		}
		
		return "home";
		
	}
}

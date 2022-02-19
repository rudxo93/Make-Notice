package com.spring.shop.gallery;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class GalleryController {
	
	@RequestMapping(value = "/gallery", method = RequestMethod.GET)
	public String getGalleryPage(HttpServletRequest req) {
		
		req.setAttribute("content", "gallery/gallery.jsp");
		
		return "home";
	}

}

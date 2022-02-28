package com.spring.shop.commons;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

@Component
public class Page {

	// totalCnt = 총 게시글 수, pagePerCnt = 한 페이지당 보여주고자 하는 게시글 수, curPage = 현재 페이지
	public Map<String, Object> pageGenerator(float totalCnt, float pagePerCnt, int curPage) {
		// pagePerCnt = 2 한 페이지당 보여주고자 하는 게시글 수
		// curpage =1 현재 페이지
		// 쿼리 조회를 하려고
		Map<String, Object> pageValue = new HashMap<String, Object>();
		float pageSize = totalCnt / pagePerCnt;
		// 오프셋
		float s_offset = (curPage - 1) * pagePerCnt; // 0 //2 //4 //6
		float e_offset = (curPage - 1) * pagePerCnt + pagePerCnt; // 2 //4 //6 // 8

		pageValue.put("e_offset", e_offset);
		pageValue.put("s_offset", s_offset);
		pageValue.put("CUR_PAGE", curPage);
		pageValue.put("TOTALCNT", totalCnt);
		pageValue.put("PAGEPERCNT", pagePerCnt);
		pageValue.put("PAGESIZE", pageSize);

		return pageValue;

	}
}

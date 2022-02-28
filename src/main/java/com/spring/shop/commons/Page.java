package com.spring.shop.commons;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

@Component
public class Page {

	// totalCnt = �� �Խñ� ��, pagePerCnt = �� �������� �����ְ��� �ϴ� �Խñ� ��, curPage = ���� ������
	public Map<String, Object> pageGenerator(float totalCnt, float pagePerCnt, int curPage) {
		// pagePerCnt = 2 �� �������� �����ְ��� �ϴ� �Խñ� ��
		// curpage =1 ���� ������
		// ���� ��ȸ�� �Ϸ���
		Map<String, Object> pageValue = new HashMap<String, Object>();
		float pageSize = totalCnt / pagePerCnt;
		// ������
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

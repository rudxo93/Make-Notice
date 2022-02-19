package com.spring.shop.notice;

public class NoticeDTO {
	private int ni_no;
	private String ni_title;
	private String ni_content;
	private String ni_writer;
	private String ni_insertdt;

	public NoticeDTO() {
		
	}

	public NoticeDTO(int ni_no, String ni_title, String ni_content, String ni_writer, String ni_insertdt) {
		super();
		this.ni_no = ni_no;
		this.ni_title = ni_title;
		this.ni_content = ni_content;
		this.ni_writer = ni_writer;
		this.ni_insertdt = ni_insertdt;
	}
	
	public int getNi_no() {
		return ni_no;
	}

	public void setNi_no(int ni_no) {
		this.ni_no = ni_no;
	}

	public String getNi_title() {
		return ni_title;
	}

	public void setNi_title(String ni_title) {
		this.ni_title = ni_title;
	}

	public String getNi_content() {
		return ni_content;
	}

	public void setNi_content(String ni_content) {
		this.ni_content = ni_content;
	}

	public String getNi_writer() {
		return ni_writer;
	}

	public void setNi_writer(String ni_writer) {
		this.ni_writer = ni_writer;
	}

	public String getNi_insertdt() {
		return ni_insertdt;
	}

	public void setNi_insertdt(String ni_insertdt) {
		this.ni_insertdt = ni_insertdt;
	}

	@Override
	public String toString() {
		return "NoticeDTO [ni_no=" + ni_no + ", ni_title=" + ni_title + ", ni_content=" + ni_content + ", ni_writer="
				+ ni_writer + ", ni_insertdt=" + ni_insertdt + "]";
	}
	
	
}



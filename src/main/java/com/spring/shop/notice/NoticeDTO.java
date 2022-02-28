package com.spring.shop.notice;

public class NoticeDTO {
	// notice_info
	private int ni_no;
	private String ni_title;
	private String ni_content;
	private String ni_writer;
	private String ni_insertdt;

	// board_attach
	private String file_name;
	private String saved_file_name;
	private long file_size;
	private int file_num;

	public NoticeDTO() {

	}
	
	public NoticeDTO(int ni_no, String ni_title, String ni_content, String ni_writer, String ni_insertdt,
			String file_name, String saved_file_name, long file_size, int file_num) {
		super();
		this.ni_no = ni_no;
		this.ni_title = ni_title;
		this.ni_content = ni_content;
		this.ni_writer = ni_writer;
		this.ni_insertdt = ni_insertdt;
		this.file_name = file_name;
		this.saved_file_name = saved_file_name;
		this.file_size = file_size;
		this.file_num = file_num;
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

	public String getFile_name() {
		return file_name;
	}

	public void setFile_name(String file_name) {
		this.file_name = file_name;
	}

	public String getSaved_file_name() {
		return saved_file_name;
	}

	public void setSaved_file_name(String saved_file_name) {
		this.saved_file_name = saved_file_name;
	}

	public long getFile_size() {
		return file_size;
	}

	public void setFile_size(long file_size) {
		this.file_size = file_size;
	}
	
	public int getFile_num() {
		return file_num;
	}

	public void setFile_num(int file_num) {
		this.file_num = file_num;
	}

	@Override
	public String toString() {
		return "NoticeDTO [ni_no=" + ni_no + ", ni_title=" + ni_title + ", ni_content=" + ni_content + ", ni_writer="
				+ ni_writer + ", ni_insertdt=" + ni_insertdt + ", file_name=" + file_name + ", saved_file_name="
				+ saved_file_name + ", file_size=" + file_size + ", file_num=" + file_num + "]";
	}



}

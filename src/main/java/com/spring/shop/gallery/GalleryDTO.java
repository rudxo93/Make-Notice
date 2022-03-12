package com.spring.shop.gallery;

public class GalleryDTO {
	
	// gallery_info
	private int gi_no;
	private String gi_title;
	private String gi_content;
	private String gi_writer;
	
	// board_attach
	private String board_type;
	private int file_num;
	private String file_name;
	private String saved_file_name;
	private long file_size;
	
	public GalleryDTO() {
	
	}

	public GalleryDTO(int gi_no, String gi_title, String gi_content, String gi_writer, String board_type, int file_num,
			String file_name, String saved_file_name, long file_size) {
		super();
		this.gi_no = gi_no;
		this.gi_title = gi_title;
		this.gi_content = gi_content;
		this.gi_writer = gi_writer;
		this.board_type = board_type;
		this.file_num = file_num;
		this.file_name = file_name;
		this.saved_file_name = saved_file_name;
		this.file_size = file_size;
	}

	public int getGi_no() {
		return gi_no;
	}

	public void setGi_no(int gi_no) {
		this.gi_no = gi_no;
	}

	public String getGi_title() {
		return gi_title;
	}

	public void setGi_title(String gi_title) {
		this.gi_title = gi_title;
	}

	public String getGi_content() {
		return gi_content;
	}

	public void setGi_content(String gi_content) {
		this.gi_content = gi_content;
	}

	public String getGi_writer() {
		return gi_writer;
	}

	public void setGi_writer(String gi_writer) {
		this.gi_writer = gi_writer;
	}

	public String getBoard_type() {
		return board_type;
	}

	public void setBoard_type(String board_type) {
		this.board_type = board_type;
	}

	public int getFile_num() {
		return file_num;
	}

	public void setFile_num(int file_num) {
		this.file_num = file_num;
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

	@Override
	public String toString() {
		return "GalleryDTO [gi_no=" + gi_no + ", gi_title=" + gi_title + ", gi_content=" + gi_content + ", gi_writer="
				+ gi_writer + ", board_type=" + board_type + ", file_num=" + file_num + ", file_name=" + file_name
				+ ", saved_file_name=" + saved_file_name + ", file_size=" + file_size + "]";
	}

	
	
}

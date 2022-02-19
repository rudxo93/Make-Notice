package com.spring.shop.signUp;

public class UserDTO {
	private String mi_id;
	private String mi_pw;
	private String mi_name;
	private String mi_moblie;
	private String mi_tell;
	private String mi_email;
	private String mi_addr;
	private String mi_insertdt;
	
	public UserDTO() {
		
	}

	public UserDTO(String mi_id, String mi_pw, String mi_name, String mi_moblie, String mi_tell, String mi_email,
			String mi_addr, String mi_insertdt) {
		super();
		this.mi_id = mi_id;
		this.mi_pw = mi_pw;
		this.mi_name = mi_name;
		this.mi_moblie = mi_moblie;
		this.mi_tell = mi_tell;
		this.mi_email = mi_email;
		this.mi_addr = mi_addr;
		this.mi_insertdt = mi_insertdt;
	}

	public String getMi_id() {
		return mi_id;
	}

	public void setMi_id(String mi_id) {
		this.mi_id = mi_id;
	}

	public String getMi_pw() {
		return mi_pw;
	}

	public void setMi_pw(String mi_pw) {
		this.mi_pw = mi_pw;
	}

	public String getMi_name() {
		return mi_name;
	}

	public void setMi_name(String mi_name) {
		this.mi_name = mi_name;
	}

	public String getMi_moblie() {
		return mi_moblie;
	}

	public void setMi_moblie(String mi_moblie) {
		this.mi_moblie = mi_moblie;
	}

	public String getMi_tell() {
		return mi_tell;
	}

	public void setMi_tell(String mi_tell) {
		this.mi_tell = mi_tell;
	}

	public String getMi_email() {
		return mi_email;
	}

	public void setMi_email(String mi_email) {
		this.mi_email = mi_email;
	}

	public String getMi_addr() {
		return mi_addr;
	}

	public void setMi_addr(String mi_addr) {
		this.mi_addr = mi_addr;
	}

	public String getMi_insertdt() {
		return mi_insertdt;
	}

	public void setMi_insertdt(String mi_insertdt) {
		this.mi_insertdt = mi_insertdt;
	}

	@Override
	public String toString() {
		return "UserDTO [mi_id=" + mi_id + ", mi_pw=" + mi_pw + ", mi_name=" + mi_name + ", mi_moblie=" + mi_moblie
				+ ", mi_tell=" + mi_tell + ", mi_email=" + mi_email + ", mi_addr=" + mi_addr + ", mi_insertdt="
				+ mi_insertdt + "]";
	}
	
	
	
	
}

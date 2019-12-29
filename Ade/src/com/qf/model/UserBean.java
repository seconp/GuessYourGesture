package com.qf.model;

public class UserBean {
	private String id;
	private String name;
	private String word;
	private String likename;
	private String phone;
	public UserBean(String id, String name, String word, String likename, String phone) {
		super();
		this.id = id;
		this.name = name;
		this.word = word;
		this.likename = likename;
		this.phone = phone;
	}
	public UserBean() {
		super();
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getWord() {
		return word;
	}
	public void setWord(String word) {
		this.word = word;
	}
	public String getLikename() {
		return likename;
	}
	public void setLikename(String likename) {
		this.likename = likename;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	@Override
	public String toString() {
		return "UserBean [id=" + id + ", name=" + name + ", word=" + word + ", likename=" + likename + ", phone="
				+ phone + "]";
	}
	
}


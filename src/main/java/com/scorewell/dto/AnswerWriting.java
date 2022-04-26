package com.scorewell.dto;

import org.springframework.web.multipart.MultipartFile;

public class AnswerWriting {
	private String name;
	private String phone;
	private String email;
	private MultipartFile answer;
	public AnswerWriting(String name, String phone, String email, MultipartFile answer) {
		super();
		this.name = name;
		this.phone = phone;
		this.email = email;
		this.answer = answer;
	}
	public AnswerWriting() {
		super();
		// TODO Auto-generated constructor stub
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public MultipartFile getAnswer() {
		return answer;
	}
	public void setAnswer(MultipartFile answer) {
		this.answer = answer;
	}
	@Override
	public String toString() {
		return "AnswerWriting [name=" + name + ", phone=" + phone + ", email=" + email + ", answer=" + answer + "]";
	}
}

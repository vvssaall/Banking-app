package com.banking.dtos;

public class EmailDto {

	private String name;
	private String to;
	private String from;
	private String subject;
	private String body;
	private String registrationLink;
	private String username;
	private String password;

	public EmailDto() {
	}

	public EmailDto(String name, String to, String from, String subject, String body) {
		this.name = name;
		this.to = to;
		this.from = from;
		this.subject = subject;
		this.body = body;

	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTo() {
		return to;
	}

	public void setTo(String to) {
		this.to = to;
	}

	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public String getRegistrationLink() {
		return registrationLink;
	}

	public void setRegistrationLink(String registrationLink) {
		this.registrationLink = registrationLink;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}

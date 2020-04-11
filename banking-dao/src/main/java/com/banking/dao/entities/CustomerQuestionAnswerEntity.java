package com.banking.dao.entities;

import java.sql.Timestamp;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "customer_question_answer_table")
public class CustomerQuestionAnswerEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private String question;
	private String answer;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "loginid", nullable = false)
	private LoginEntity login;

	@Column(name = "date_of_access")
	private Timestamp doa;

	@Column(name = "date_of_modified")
	private Timestamp dom;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	public LoginEntity getLogin() {
		return login;
	}

	public void setLogin(LoginEntity login) {
		this.login = login;
	}

	public Timestamp getDoa() {
		return doa;
	}

	public void setDoa(Timestamp doa) {
		this.doa = doa;
	}

	public Timestamp getDom() {
		return dom;
	}

	public void setDom(Timestamp dom) {
		this.dom = dom;
	}

}

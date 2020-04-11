package com.banking.dao.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "security_question")
public class SecurityQuestionEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int qid;
	private String questions;

	public SecurityQuestionEntity() {

	}

	public SecurityQuestionEntity(int qid, String questions) {
		super();
		this.qid = qid;
		this.questions = questions;
	}

	public int getQid() {
		return qid;
	}

	public void setQid(int qid) {
		this.qid = qid;
	}

	public String getQuestions() {
		return questions;
	}

	public void setQuestions(String questions) {
		this.questions = questions;
	}

}

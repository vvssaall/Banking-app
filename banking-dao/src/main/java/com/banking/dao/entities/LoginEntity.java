package com.banking.dao.entities;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "user_login_table")
public class LoginEntity {

    @Id
	@Column(name = "loginid")
	private String loginid;

	private String name;
	private String password;
	private String locked;

	@Column(name = "no_of_attempt")
	private int noOfAttempt;

	@Column(name = "last_login_time")
	private Timestamp llt;

	private Timestamp lastsession;
	private String email;

	@Column(name = "password_expire")
	private Date passwordExpire;
	private String token;

	@OneToMany(mappedBy = "login", cascade = CascadeType.ALL)
	private List<CustomerQuestionAnswerEntity> customerQuestionAnswers;

	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "user_roles", joinColumns = @JoinColumn(name = "loginid"), inverseJoinColumns = @JoinColumn(name = "roleid"))
	private Set<RoleEntity> roles;

	public String getLoginId() {
		return loginid;
	}

	public void setLoginId(String loginId) {
		this.loginid = loginId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getLocked() {
		return locked;
	}

	public void setLocked(String locked) {
		this.locked = locked;
	}

	public int getNoOfAttempt() {
		return noOfAttempt;
	}

	public void setNoOfAttempt(int noOfAttempt) {
		this.noOfAttempt = noOfAttempt;
	}

	public Timestamp getLlt() {
		return llt;
	}

	public void setLlt(Timestamp llt) {
		this.llt = llt;
	}

	public Timestamp getLastsession() {
		return lastsession;
	}

	public void setLastsession(Timestamp lastsession) {
		this.lastsession = lastsession;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getPasswordExpire() {
		return passwordExpire;
	}

	public void setPasswordExpire(Date passwordExpire) {
		this.passwordExpire = passwordExpire;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public List<CustomerQuestionAnswerEntity> getCustomerQuestionAnswers() {
		return customerQuestionAnswers;
	}

	public void setCustomerQuestionAnswers(List<CustomerQuestionAnswerEntity> customerQuestionAnswers) {
		this.customerQuestionAnswers = customerQuestionAnswers;
	}

	public Set<RoleEntity> getRoles() {
		return roles;
	}

	public void setRoles(Set<RoleEntity> roles) {
		this.roles = roles;
	}

}

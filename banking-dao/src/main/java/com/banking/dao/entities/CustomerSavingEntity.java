package com.banking.dao.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "customer_saving_enquiry_table")
public class CustomerSavingEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int cseId;

	@Column(length = 100)
	private String name;

	@Column(length = 100)
	private String email;

	@Column(length = 15)
	private String mobile;

	@Column(length = 100)
	private String location;

	@Column(length = 100)
	private String uuid;

	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "account_type", nullable = false)
	private AccountType accType;

	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "status", nullable = false)
	private AccountStatus status;

	@Column(name = "Date_of_access")
	private Date doa;

	@Column(length = 30, name = "application_refrence")
	private String appref;

	public int getCseId() {
		return cseId;
	}

	public void setCseId(int cseId) {
		this.cseId = cseId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}
	

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public AccountType getAccType() {
		return accType;
	}

	public void setAccType(AccountType accType) {
		this.accType = accType;
	}

	public AccountStatus getStatus() {
		return status;
	}

	public void setStatus(AccountStatus status) {
		this.status = status;
	}

	public Date getDoa() {
		return doa;
	}

	public void setDoa(Date doa) {
		this.doa = doa;
	}

	public String getAppref() {
		return appref;
	}

	public void setAppref(String appref) {
		this.appref = appref;
	}

}

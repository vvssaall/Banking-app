package com.banking.dtos;

import java.util.Date;

public class CustomerSavingEnquiryDto {

	private int cseId;
	private String name;
	private String email;
	private String mobile;
	private String location;
	private String accType;
	private String status;
	private Date doa;
	private String appref;

	public int getCseid() {
		return cseId;
	}

	public void setCseid(int cseid) {
		this.cseId = cseid;
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

	public String getAccType() {
		return accType;
	}

	public void setAccType(String accType) {
		this.accType = accType;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
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

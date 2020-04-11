package com.banking.dao.entities;

import java.sql.Timestamp;

import javax.persistence.CascadeType;
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
@Table(name = "customer_table")
public class CustomerEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String name;

	@OneToOne(mappedBy = "customer", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private AddressEntity address;

	private String email;
	private String mobile;
	private String ssn;
	private int age;
	private String gender;
	private String dob;
	private String father;
	private String qualification;

	@Column(name = "job_title")
	private String jobTitle;

	@Column(name = "photo_name")
	private String photoName;

	@Column(columnDefinition = "longblob")
	private byte[] image;

	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "userid")
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public AddressEntity getAddress() {
		return address;
	}

	public void setAddress(AddressEntity address) {
		this.address = address;
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

	public String getSsn() {
		return ssn;
	}

	public void setSsn(String ssn) {
		this.ssn = ssn;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getDob() {
		return dob;
	}

	public void setDob(String dob) {
		this.dob = dob;
	}

	public String getFather() {
		return father;
	}

	public void setFather(String father) {
		this.father = father;
	}

	public String getQualification() {
		return qualification;
	}

	public void setQualification(String qualification) {
		this.qualification = qualification;
	}

	public String getJobTitle() {
		return jobTitle;
	}

	public void setJobTitle(String jobTitle) {
		this.jobTitle = jobTitle;
	}

	public String getPhotoName() {
		return photoName;
	}

	public void setPhotoName(String photoName) {
		this.photoName = photoName;
	}

	public byte[] getImage() {
		return image;
	}

	public void setImage(byte[] image) {
		this.image = image;
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

package com.banking.dao.entities;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "roles_table")
public class RoleEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "roleid")
	private int roleId;

	@Column(length = 30)
	private String name;

	@Column(length = 100)
	private String description;

	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "user_roles", joinColumns = @JoinColumn(name = "roleid"), inverseJoinColumns = @JoinColumn(name = "loginid"))
	private Set<LoginEntity> logins;

	public RoleEntity() {

	}

	public RoleEntity(int roleId, String name, String description) {
		super();
		this.roleId = roleId;
		this.name = name;
		this.description = description;
	}

	public int getRoleId() {
		return roleId;
	}

	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Set<LoginEntity> getLogins() {
		return logins;
	}

	public void setLogins(Set<LoginEntity> logins) {
		this.logins = logins;
	}

}

package com.banking.dao.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.banking.dao.entities.LoginEntity;

public interface LoginRepository extends JpaRepository<LoginEntity, Integer>{
	
	public Optional<LoginEntity> findByLoginidAndPassword(String loginid,String password);

	public Optional<LoginEntity> findByLoginid(String employeeUsername);

}

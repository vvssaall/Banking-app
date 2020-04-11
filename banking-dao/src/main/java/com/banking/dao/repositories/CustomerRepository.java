package com.banking.dao.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.banking.dao.entities.CustomerEntity;

public interface CustomerRepository extends JpaRepository<CustomerEntity, Integer>{
	
	public Optional<CustomerEntity> findByEmail(String email);
	

}

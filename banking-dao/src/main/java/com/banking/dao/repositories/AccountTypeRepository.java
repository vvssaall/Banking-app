package com.banking.dao.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.banking.dao.entities.AccountType;

public interface AccountTypeRepository extends JpaRepository<AccountType, Integer> {

	public Optional<AccountType> findByName(String name);

	public Optional<AccountType> findByNameAndCode(String name, String code);

}

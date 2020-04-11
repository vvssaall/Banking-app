package com.banking.dao.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.banking.dao.entities.AccountStatus;

public interface AccountStatusRepository extends JpaRepository<AccountStatus, Integer> {

	public Optional<AccountStatus> findByName(String name);

}

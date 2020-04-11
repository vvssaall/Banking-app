package com.banking.dao.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.banking.dao.entities.CustomerSavingEntity;

public interface CustomerAccountEnquiryRepository extends JpaRepository<CustomerSavingEntity, Integer> {

	Optional<CustomerSavingEntity> findByEmail(String email);

	Optional<CustomerSavingEntity> findByUuid(String uuid);
	Optional<CustomerSavingEntity> findByAppref(String appref);

	@Query("SELECT tt FROM CustomerSavingEntity tt where tt.status.name = :name")
	List<CustomerSavingEntity> findPendingEnquiries(@Param("name") String name);

}

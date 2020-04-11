package com.banking.dao.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.banking.dao.entities.SecurityQuestionEntity;

public interface SecurityQuestionRepository extends JpaRepository<SecurityQuestionEntity, Integer>{

}

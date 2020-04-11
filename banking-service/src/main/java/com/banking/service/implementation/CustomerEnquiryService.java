package com.banking.service.implementation;

import java.util.List;
import java.util.Optional;

import com.banking.dtos.CustomerSavingEnquiryDto;

public interface CustomerEnquiryService {

	public CustomerSavingEnquiryDto save(CustomerSavingEnquiryDto customersavingenquirydto);

	List<CustomerSavingEnquiryDto> findAll();

	CustomerSavingEnquiryDto findById(int cseId);

	void deleteById(int cseId);

	boolean emailNotExist(String email);

	CustomerSavingEnquiryDto changeEnquiryStatus(String cseId, String status);

	Optional<CustomerSavingEnquiryDto> findCustomerEnquiryByUuid(String uuid);

	String updateEnquiryRegId(String cseid, String uuid);

	List<CustomerSavingEnquiryDto> findPendingEnquiry();

}

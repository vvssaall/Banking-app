package com.banking.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.banking.dtos.CustomerSavingEnquiryDto;
import com.banking.service.exception.BankServiceException;
import com.banking.service.implementation.CustomerEnquiryService;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/v3")
public class CustomerAccountEnquiryController {

	@Autowired
	private CustomerEnquiryService customerenquiryservice;

	@PostMapping("/customers/enquiry")
	public CustomerSavingEnquiryDto saveEnquiry(@RequestBody CustomerSavingEnquiryDto customerSavingDto) {
		CustomerSavingEnquiryDto response = null;
		if (customerenquiryservice.emailNotExist(customerSavingDto.getEmail())) {
			response = customerenquiryservice.save(customerSavingDto);
		} else {
			throw new BankServiceException("Sorry! email " + customerSavingDto.getEmail() + " is already in use");
		}
		return response;
	}

	@GetMapping("/customers/enquiry")
	public List<CustomerSavingEnquiryDto> getAllEnquiry() {
		List<CustomerSavingEnquiryDto> responses = customerenquiryservice.findAll();
		return responses;
	}

	@GetMapping("/customers/enquiry/pending")
	public List<CustomerSavingEnquiryDto> getAllPendingEnquiry() {
		List<CustomerSavingEnquiryDto> responses = customerenquiryservice.findPendingEnquiry();
		return responses;
	}

	@GetMapping(value = "/customers/enquiry/{id}")
	public CustomerSavingEnquiryDto getEnquiry(@PathVariable int id) {
		CustomerSavingEnquiryDto response = customerenquiryservice.findById(id);
		return response;
	}

	@DeleteMapping("customers/delete/{id}")
	public void deletCustomerEnquiryById(@PathVariable int id) {
		customerenquiryservice.deleteById(id);

	}

}

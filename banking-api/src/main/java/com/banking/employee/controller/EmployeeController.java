package com.banking.employee.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.banking.dtos.ApplicationResponseDto;
import com.banking.dtos.CustomerSavingEnquiryDto;
import com.banking.dtos.EmailDto;
import com.banking.email.service.EmailService;
import com.banking.service.implementation.CustomerEnquiryService;
import com.banking.utils.BankHttpUtils;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/v3")
public class EmployeeController {

	@Autowired
	private CustomerEnquiryService customerEnquiryService;

	@Autowired
	private EmailService emailservice;

	@Value("${customer.registration.url}")
	private String registrationURL;

	@PostMapping("/employee/enquiry/approve")
	public ApplicationResponseDto customerEnquiryApprovel(@RequestParam String appref, HttpServletRequest request) {
		CustomerSavingEnquiryDto customerSavingEnquiryDto = customerEnquiryService.changeEnquiryStatus(appref, "APPROVED");
		String cuuid = BankHttpUtils.generateToken();
		customerEnquiryService.updateEnquiryRegId(appref, cuuid);
		// String registrationLink = "http://localhost:8080/v3/customer/registration/complete";
		String registrationLink = BankHttpUtils.getServerBaseURL(request) + "/" + registrationURL + cuuid;
		EmailDto mail = new EmailDto();
		mail.setName(customerSavingEnquiryDto.getName());
		mail.setFrom("BankofMundrey&Tundrey");
		mail.setSubject("Regarding Customer " + customerSavingEnquiryDto.getName() + " Account registration");
		mail.setTo(customerSavingEnquiryDto.getEmail());
		mail.setBody("");
		mail.setRegistrationLink(registrationLink);

		emailservice.sendRegistrationEmail(mail);

		ApplicationResponseDto applicationResponseDto = new ApplicationResponseDto();
		applicationResponseDto.setCode(200);
		applicationResponseDto.setMessage(
				"Hi your registration link has been sent to your at email" + customerSavingEnquiryDto.getEmail());

		return applicationResponseDto;

	}

}

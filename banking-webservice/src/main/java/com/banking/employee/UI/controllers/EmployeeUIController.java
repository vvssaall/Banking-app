package com.banking.employee.UI.controllers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.banking.dtos.CustomerSavingEnquiryDto;
import com.banking.dtos.EmailDto;
import com.banking.email.service.EmailService;
import com.banking.service.implementation.CustomerEnquiryService;
import com.banking.utils.BankHttpUtils;

@Controller
public class EmployeeUIController {
	
	private static final Logger logger = LoggerFactory.getLogger(EmployeeUIController.class);
	
	@Autowired
	private CustomerEnquiryService customerEnquiryService;
	
	@Autowired
	private EmailService emailservice;
	
	@Value("${customer.registration.url}")
	private String registrationURL;

	
	@GetMapping(value= {"/customer/enquiries"})
    @PreAuthorize("hasAuthority('EMPLOYEE')")
	public String showCustomerEnquiry(Model model) {
		logger.info("showCustomerEnquiry is called!!!");
		List<CustomerSavingEnquiryDto> pendingApplications = customerEnquiryService.findPendingEnquiry();
		model.addAttribute("applicants", pendingApplications);
		return "employee/customerEnquiryList";	//login.html
	}
	
	@PostMapping("/customers/enquiry/approve")
	public String customerEnquiryApprove(@RequestParam String appref,HttpServletRequest request) {
		CustomerSavingEnquiryDto customerSavingEnquiryDto=customerEnquiryService.changeEnquiryStatus(appref, "APPROVED");
		String cuuid=BankHttpUtils.generateToken();
		customerEnquiryService.updateEnquiryRegId(appref, cuuid);
		String registrationLink=BankHttpUtils.getServerBaseURL(request)+"/"+ registrationURL +cuuid;
		//String registrationLink ="http://localhost:8080/v3/customer/registration/complete";
		EmailDto mail=new EmailDto();
		mail.setName(customerSavingEnquiryDto.getName());
		mail.setFrom("BankofMundrey&Tundrey");
		mail.setSubject("Regarding Customer " + customerSavingEnquiryDto.getName() + " Account registration");
		mail.setTo(customerSavingEnquiryDto.getEmail());
		mail.setBody("");
		mail.setRegistrationLink(registrationLink);
		emailservice.sendRegistrationEmail(mail);
		
		return "redirect:/customer/enquiries";
	}

}

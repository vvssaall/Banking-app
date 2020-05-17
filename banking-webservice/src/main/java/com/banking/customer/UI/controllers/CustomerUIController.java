package com.banking.customer.UI.controllers;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.banking.dtos.CustomerDto;
import com.banking.dtos.CustomerSavingEnquiryDto;
import com.banking.dtos.EmailDto;
import com.banking.dtos.LoginDto;
import com.banking.email.service.EmailService;
import com.banking.service.implementation.CustomerEnquiryService;
import com.banking.service.implementation.CustomerService;

@Controller
public class CustomerUIController {
	
	private static final Logger logger = LoggerFactory.getLogger(CustomerUIController.class);

	@Autowired
	private CustomerEnquiryService customerEnquiryService;

	@Autowired
	private CustomerService customerService;
	
	@Autowired
	private EmailService emailService;

	// http://localhost:8080/customer/account/registration?cuid=1585a34b5277-dab2-475a-b7b4-042e032e8121603186515
	@GetMapping("/customer/account/registration")
	public String showCustomerRegistrationPage(@RequestParam String cuid, Model model) {
		
		logger.debug("cuid = "+cuid);
		Optional<CustomerSavingEnquiryDto> optional = customerEnquiryService.findCustomerEnquiryByUuid(cuid);
		CustomerDto customerdto = null;

		if (optional.isPresent()) {
			customerdto = new CustomerDto();
			// model is used to carry data from controller to the view =- JSP/
			CustomerSavingEnquiryDto customerSavingdto = optional.get();

			customerdto.setEmail(customerSavingdto.getEmail());
			customerdto.setName(customerSavingdto.getName());
			customerdto.setMobile(customerSavingdto.getMobile());

			customerdto.setToken(cuid);
			// model - is hash map which is used to carry data from controller to
			// thymeleaf!!!!!
			// model is similar to request scope in jsp and servlet
			model.addAttribute("customerdto", customerdto);

			return "customer/customerRegistration"; // thyme leaf

		} else {
			return "error";
		}
	}

	@PostMapping("/customer/account/registration")
	public String createCustomer(@ModelAttribute CustomerDto customerdto, Model model) {
		// saving customer into database
		logger.debug(customerdto.toString());
		customerService.createAccount(customerdto);
		// Write code to send email
		EmailDto mail = new EmailDto();
		mail.setName(customerdto.getName());
		mail.setFrom("BankofMundrey&Tundrey");
		mail.setSubject("Regarding Customer " + customerdto.getName() + " login information");
		mail.setTo(customerdto.getEmail());
		mail.setBody("");
		mail.setUsername(customerdto.getEmail());
		mail.setPassword(customerdto.getPassword());
		emailService.sendUsernamePassword(mail);
		        System.out.println(customerdto);
		model.addAttribute("loginVO", new LoginDto());
		model.addAttribute("message", "Your account has been setup successfully , please check your email.");
		return "customer/login";
	}

	@GetMapping(value = { "/customer/account/enquiry", "/", "/mocha", "/welcome" })
	public String showCustomerEnquiryPage(Model model) {
		CustomerSavingEnquiryDto customerSavingdto = new CustomerSavingEnquiryDto();
		// model is map which is used to carry object from controller to view
		model.addAttribute("customerSavingdto", customerSavingdto);
		return "customer/customerEnquiry"; // customerEnquiry.html
	}

	@PostMapping("/customer/account/enquiry")
	public String submitEnquiryData(@ModelAttribute CustomerSavingEnquiryDto customerSavingEnquiryDto, Model model) {
		boolean status = customerEnquiryService.emailNotExist(customerSavingEnquiryDto.getEmail());
		if (status) {
			CustomerSavingEnquiryDto response = customerEnquiryService.save(customerSavingEnquiryDto);
			model.addAttribute("message",
					"Hey Customer , your enquiry form has been submitted successfully!!! and appref "
							+ response.getAppref());
		} else {   
			model.addAttribute("message",
					"Sorry , this email is already in use " + customerSavingEnquiryDto.getEmail());
		}
		return "customer/success"; // customerEnquiry.html

	}
	
	@GetMapping(value= {"/customer/account/forget"})
	public String showforgetPasswordPage(Model model){
		CustomerSavingEnquiryDto customerSavingdto = new CustomerSavingEnquiryDto();
		// model is map which is used to carry object from controller to view
		model.addAttribute("customerSavingdto", customerSavingdto);
		return "customer/forgetPassword"; // customerEnquiry.html
	}
	
	@PostMapping("/customer/acccount/passwordReset")
	public String Resetpassword(@ModelAttribute CustomerSavingEnquiryDto customerSavingEnquiryDto, Model model) {
		boolean status = customerEnquiryService.emailNotExist(customerSavingEnquiryDto.getEmail());
//		if (status) {
//			CustomerSavingEnquiryDto response = customerEnquiryService.save(customerSavingEnquiryDto);
//			model.addAttribute("message",
//					"Hey Customer , your enquiry form has been submitted successfully!!! and appref "
//							+ response.getAppref());
//		} else {   
//			model.addAttribute("message",
//					"Sorry , this email is already in use " + customerSavingEnquiryDto.getEmail());
//		}
		return "customer/success"; // customerEnquiry.html

	}
	
		
		
	
	

}

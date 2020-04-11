package com.banking.email.service;

import com.banking.dtos.EmailDto;

public interface EmailService {

	String sendEquiryEmail(EmailDto mail);

	String sendRegistrationEmail(EmailDto mail);

	String sendUsernamePassword(EmailDto mail);

}

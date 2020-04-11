package com.banking.email.service;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.Map;

import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.InputStreamSource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;

import com.banking.dtos.EmailDto;

@Service
public class EmailServiceImpl implements EmailService {

	@Autowired
	private JavaMailSender javamailsender;

	@Autowired
	private SpringTemplateEngine templateengine;

	@Override
	@Async("threadPool")
	public String sendEquiryEmail(EmailDto mail) {

		try {
			MimeMessage message = javamailsender.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(message, MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED,
					StandardCharsets.UTF_8.name());

			Context context = new Context();
			Map<String, Object> props = new HashMap<>();
			props.put("name", mail.getName());
			props.put("sign", "Banking Application");
			props.put("location", "Fairfax VA, USA");
			props.put("email", "customerservice@M&TBank.com");
			context.setVariables(props);
			String html = templateengine.process("newsletter-template", context);
			helper.setTo(mail.getTo());
			helper.setText(html, true);
			helper.setSubject("Regarding Account enquiry to open an Account.");
			helper.setFrom(mail.getFrom());

			File file = new ClassPathResource("images/bankwelcome.jpg", EmailServiceImpl.class.getClassLoader())
					.getFile();
			byte[] bytes = Files.readAllBytes(file.toPath());
			InputStreamSource imagesource = new ByteArrayResource(bytes);
			helper.addInline("images", imagesource, "image/jpg");

			File file1 = new ClassPathResource("images/banklogo.png", EmailServiceImpl.class.getClassLoader())
					.getFile();
			byte[] bytes1 = Files.readAllBytes(file1.toPath());
			InputStreamSource imagesource1 = new ByteArrayResource(bytes1);
			helper.addInline("images3", imagesource1, "image/png");

			javamailsender.send(message);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "done";
	}

	@Override
	@Async("threadPool")
	public String sendRegistrationEmail(EmailDto mail) {

		try {
			MimeMessage message = javamailsender.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(message, MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED,
					StandardCharsets.UTF_8.name());

			Context context = new Context();
			Map<String, Object> props = new HashMap<>();
			props.put("name", mail.getName());
			props.put("sign", "By Mundrey&Tundrey Bank");
			props.put("location", "Fairfax VA, USA");
			props.put("email", "customerservice@M&TBank.com");
			props.put("registrationlink", mail.getRegistrationLink());
			context.setVariables(props);
			String html = templateengine.process("send-registration-link", context);
			helper.setTo(mail.getTo());
			helper.setText(html, true);
			helper.setSubject(mail.getSubject());
			helper.setFrom(mail.getFrom());

			File file = new ClassPathResource("images/MYBankingPage2019.jpg", EmailServiceImpl.class.getClassLoader())
					.getFile();
			byte[] bytes = Files.readAllBytes(file.toPath());
			InputStreamSource imagesource = new ByteArrayResource(bytes);
			helper.addInline("mybanking", imagesource, "image/jpg");

			File file1 = new ClassPathResource("images/banklogo.png", EmailServiceImpl.class.getClassLoader())
					.getFile();
			byte[] bytes1 = Files.readAllBytes(file1.toPath());
			InputStreamSource imagesource1 = new ByteArrayResource(bytes1);
			helper.addInline("banklogo", imagesource1, "image/png");

			javamailsender.send(message);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "done";
	}
	
	@Override
	@Async("threadPool")
	public String sendUsernamePassword(EmailDto mail) {

		try {
			MimeMessage message = javamailsender.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(message, MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED,
					StandardCharsets.UTF_8.name());

			Context context = new Context();
			Map<String, Object> props = new HashMap<>();
			props.put("name", mail.getName());
			props.put("username", mail.getUsername());
			props.put("password", mail.getPassword());
			props.put("sign", "By Mundrey&Tundrey Bank");
			props.put("location", "Fairfax VA, USA");
			props.put("email", "customerservice@M&TBank.com");
			context.setVariables(props);
			String html = templateengine.process("password-email-template", context);
			helper.setTo(mail.getTo());
			helper.setText(html, true);
			helper.setSubject("Regarding your banking login information.");
			helper.setFrom(mail.getFrom());

			File file = new ClassPathResource("images/bankwelcome.jpg", EmailServiceImpl.class.getClassLoader())
					.getFile();
			byte[] bytes = Files.readAllBytes(file.toPath());
			InputStreamSource imagesource = new ByteArrayResource(bytes);
			helper.addInline("mybanking", imagesource, "image/jpg");

			File file1 = new ClassPathResource("images/banklogo.png", EmailServiceImpl.class.getClassLoader())
					.getFile();
			byte[] bytes1 = Files.readAllBytes(file1.toPath());
			InputStreamSource imagesource1 = new ByteArrayResource(bytes1);
			helper.addInline("banklogo", imagesource1, "image/png");

			javamailsender.send(message);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "done";
	}
	
	

}

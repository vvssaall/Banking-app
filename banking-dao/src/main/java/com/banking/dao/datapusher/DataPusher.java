package com.banking.dao.datapusher;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.banking.dao.entities.AccountStatus;
import com.banking.dao.entities.AccountType;
import com.banking.dao.entities.AddressEntity;
import com.banking.dao.entities.CustomerEntity;
import com.banking.dao.entities.LoginEntity;
import com.banking.dao.entities.RoleEntity;
import com.banking.dao.entities.SecurityQuestionEntity;
import com.banking.dao.repositories.AccountStatusRepository;
import com.banking.dao.repositories.AccountTypeRepository;
import com.banking.dao.repositories.CustomerRepository;
import com.banking.dao.repositories.LoginRepository;
import com.banking.dao.repositories.RoleRepository;
import com.banking.dao.repositories.SecurityQuestionRepository;

@Component
public class DataPusher implements CommandLineRunner {

	@Value("${spring.mail.username:javatech1000@gmail.com}")
	private String employeeUsername;

	@Autowired
	private AccountStatusRepository accountstatusrepository;

	@Autowired
	private AccountTypeRepository accountTypeRepository;

	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	private CustomerRepository customerRepository;

	@Autowired
	private LoginRepository loginRepository;
	
	@Autowired
	private SecurityQuestionRepository securityQuestionRepository;
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@Override
	@Transactional
	public void run(String... args) throws Exception {
		Optional<AccountStatus> optional1 = accountstatusrepository.findById(1);
		if (!optional1.isPresent()) {
			AccountStatus accountStatus1 = new AccountStatus(1, "AS01", "PENDING", "PENDING");
			AccountStatus accountStatus2 = new AccountStatus(2, "AS02", "PROCESSING", "PROCESSING");
			AccountStatus accountStatus3 = new AccountStatus(3, "AS03", "DORMANT", "DORMANT");
			AccountStatus accountStatus4 = new AccountStatus(4, "AS04", "APPROVED", "APPROVED");
			AccountStatus accountStatus5 = new AccountStatus(5, "AS05", "ACTIVE", "ACTIVE");
			accountstatusrepository.save(accountStatus1);
			accountstatusrepository.save(accountStatus2);
			accountstatusrepository.save(accountStatus3);
			accountstatusrepository.save(accountStatus4);
			accountstatusrepository.save(accountStatus5);
		}

		Optional<AccountType> optional2 = accountTypeRepository.findById(1);
		if (!optional2.isPresent()) {
			AccountType accountType1 = new AccountType(1, "AC001", "SAVING", "SAVING");
			AccountType accountType2 = new AccountType(2, "AC002", "CURRENT", "CURRENT");
			AccountType accountType3 = new AccountType(3, "AC003", "CORPORATE", "CORPORATE");
			AccountType accountType4 = new AccountType(4, "AC004", "CHECKING", "CHECKING");

			List<AccountType> accountTypes = new ArrayList<>();
			accountTypes.add(accountType1);
			accountTypes.add(accountType2);
			accountTypes.add(accountType3);
			accountTypes.add(accountType4);

			accountTypeRepository.saveAll(accountTypes);

		}

		Optional<RoleEntity> optional3 = roleRepository.findById(1);
		if (!optional3.isPresent()) {
			RoleEntity role1 = new RoleEntity(1, "ADMIN", "ADMIN");
			RoleEntity role2 = new RoleEntity(2, "EMPLOYEE", "EMPLOYEE");
			RoleEntity role3 = new RoleEntity(3, "CUSTOMER", "CUSTOMER");
			RoleEntity role4 = new RoleEntity(4, "MANAGER", "MANAGER");

			List<RoleEntity> roles = new ArrayList<>();
			roles.add(role1);
			roles.add(role2);
			roles.add(role3);
			roles.add(role4);

			roleRepository.saveAll(roles);

		}
		
		Optional<LoginEntity> optional=loginRepository.findByLoginid(employeeUsername);
		
		if(!optional.isPresent()){

			CustomerEntity customerEntity = new CustomerEntity();
			customerEntity.setName("James Robert");
			AddressEntity addressEntity = new AddressEntity();
			addressEntity.setAddress1("2771 Prospiraty ave");
			addressEntity.setAddress2("Suite 1011");
			addressEntity.setCity("Falls Church");
			addressEntity.setState("Virginia");
			addressEntity.setZip("22031");
			customerEntity.setAddress(addressEntity);
			customerEntity.setMobile("320432043");
			customerEntity.setGender("Male");
			customerEntity.setJobTitle("Bank Employee");
			customerEntity.setSsn("23432");
			customerEntity.setFather("Mr. Jack");
			customerEntity.setQualification("NA");
			customerEntity.setDom(new Timestamp(new Date().getTime()));
			customerEntity.setDob("12-03-2020");
			customerEntity.setDoa(new Timestamp(new Date().getTime()));
			customerEntity.setEmail(employeeUsername);
			LoginEntity login = new LoginEntity();
			login.setNoOfAttempt(3);
			login.setLoginId(customerEntity.getEmail());
			login.setName(customerEntity.getName());
			login.setPassword("cool@123$");
			login.setPassword(bCryptPasswordEncoder.encode("cool@123$"));
			login.setToken("2230303");
			login.setLocked("no");

			RoleEntity entity = roleRepository.findById(2).get();
			Set<RoleEntity> roles = new HashSet<>();
			roles.add(entity);
			// setting roles inside login
			login.setRoles(roles);
			// setting login inside
			customerEntity.setLogin(login);
			customerRepository.save(customerEntity);
		}
		
		Optional<SecurityQuestionEntity> seOptional=securityQuestionRepository.findById(1);
		if(!seOptional.isPresent()) {
			SecurityQuestionEntity securityQuestions1=new SecurityQuestionEntity (1,"What is your birth place?");
			SecurityQuestionEntity securityQuestions2=new SecurityQuestionEntity (2,"What is your mother's maiden name?");
			SecurityQuestionEntity securityQuestions3=new SecurityQuestionEntity (3,"What is your favourite author's name?");
			SecurityQuestionEntity securityQuestions4=new SecurityQuestionEntity (4,"What is your pet name?");
			SecurityQuestionEntity securityQuestions5=new SecurityQuestionEntity (5,"What is your favourite soccer team?");
			SecurityQuestionEntity securityQuestions6=new SecurityQuestionEntity (6,"What is the name of your childhood hero?");
			SecurityQuestionEntity securityQuestions7=new SecurityQuestionEntity (7,"What is your father's middle name?");
			SecurityQuestionEntity securityQuestions8=new SecurityQuestionEntity (8,"What is the name of your first crush?");
			SecurityQuestionEntity securityQuestions9=new SecurityQuestionEntity (9,"What was the name of your first school?");
			SecurityQuestionEntity securityQuestions10=new SecurityQuestionEntity (10,"What is your favourite vacation spot?");
			
			List<SecurityQuestionEntity> securityQuestions=new ArrayList<>();
			securityQuestions.add(securityQuestions1);
			securityQuestions.add(securityQuestions2);
			securityQuestions.add(securityQuestions3);
			securityQuestions.add(securityQuestions4);
			securityQuestions.add(securityQuestions5);
			securityQuestions.add(securityQuestions6);
			securityQuestions.add(securityQuestions7);
			securityQuestions.add(securityQuestions8);
			securityQuestions.add(securityQuestions9);
			securityQuestions.add(securityQuestions10);
			securityQuestionRepository.saveAll(securityQuestions);
		}
		


	}

}

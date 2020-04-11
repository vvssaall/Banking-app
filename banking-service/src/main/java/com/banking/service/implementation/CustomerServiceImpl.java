package com.banking.service.implementation;

import java.sql.Timestamp;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.banking.dao.entities.AddressEntity;
import com.banking.dao.entities.CustomerEntity;
import com.banking.dao.entities.LoginEntity;
import com.banking.dao.entities.RoleEntity;
import com.banking.dao.repositories.CustomerRepository;
import com.banking.dao.repositories.RoleRepository;
import com.banking.dtos.AddressDto;
import com.banking.dtos.CustomerDto;
import com.banking.utils.PasswordGenerator;

@Service
@Transactional
public class CustomerServiceImpl implements CustomerService{
	
	@Autowired
	private CustomerRepository customerRepository;
	
	@Autowired
	private RoleRepository roleRepository;
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Override
	public CustomerDto createAccount(CustomerDto customerdto) {
		CustomerEntity customerEntity = new CustomerEntity();
		BeanUtils.copyProperties(customerdto, customerEntity);
		
		AddressDto dto  = customerdto.getAddress();
		AddressEntity entity = new AddressEntity();
		BeanUtils.copyProperties(dto, entity);
		entity.setCustomer(customerEntity);
		customerEntity.setAddress(entity);
		
		LoginEntity login = new LoginEntity();
		login.setEmail(customerdto.getEmail());
		login.setNoOfAttempt(3);
		login.setLoginId(customerdto.getEmail());
		login.setName(customerdto.getName());
		login.setLastsession(new Timestamp(new Date().getTime()));
		login.setLlt(new Timestamp(new Date().getTime()));
		String genPassword = (PasswordGenerator.generateRandomPassword(8));
		customerdto.setPassword(genPassword);
		login.setPassword(bCryptPasswordEncoder.encode(genPassword));
		login.setToken(customerdto.getToken());
		login.setLocked("no");
		
		RoleEntity roleentity=roleRepository.findById(3).get();
		Set<RoleEntity> roles=new HashSet<>();
		roles.add(roleentity);
		//setting roles inside login
		login.setRoles(roles);
		//setting login inside
		customerEntity.setLogin(login);
		CustomerEntity customerentity=customerRepository.save(customerEntity);
		customerdto.setId(customerentity.getId());
		customerdto.setUserid(customerdto.getUserid());
		
		return customerdto;
	}
	
	

}
 
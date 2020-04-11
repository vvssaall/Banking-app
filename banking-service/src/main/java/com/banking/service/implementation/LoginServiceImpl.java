package com.banking.service.implementation;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.banking.dao.entities.LoginEntity;
import com.banking.dao.entities.RoleEntity;
import com.banking.dao.repositories.LoginRepository;
import com.banking.dtos.LoginDto;

@Service
@Transactional
public class LoginServiceImpl implements LoginService {

	@Autowired
	private LoginRepository loginRepository;
	
	
	
    @Override
	public Optional<LoginDto> authorizeUser(LoginDto logindto) {

		Optional<LoginEntity> optional = loginRepository.findByLoginidAndPassword(logindto.getUsername(),logindto.getPassword());
		if (optional.isPresent()) {
			LoginEntity login = optional.get();
			logindto.setEmail(login.getEmail());
			Set<RoleEntity> rolesSet = login.getRoles();
			List<String> roleList = new ArrayList<>();
			// List<String> roles=rolesSet.stream().map(Role::getName).collect(Collectors.toList());
			for (RoleEntity role : rolesSet) {
				roleList.add(role.getName());
			}
			logindto.setRoles(roleList);
			return Optional.of(logindto);
		} else {
			return Optional.empty();
		}

	}
    
    @Override
	public Optional<LoginDto> findUserByUsername(String loginid) {
		LoginDto loginDto =new LoginDto();
		loginDto.setUsername(loginid);
		Optional<LoginEntity>  optional=loginRepository.findByLoginid(loginid);
		if(optional.isPresent()) {
			LoginEntity login=optional.get();
			loginDto.setEmail(login.getEmail());
			loginDto.setPassword(login.getPassword());
			Set<RoleEntity> rolesSet=login.getRoles();
			List<String> roleList=new ArrayList<>();
			//List<String> roles= rolesSet.stream().map(Role::getName).collect(Collectors.toList());
			for(RoleEntity role:rolesSet) {
				roleList.add(role.getName());
			}
			loginDto.setRoles(roleList);
			return Optional.of(loginDto);
		}else {
			return Optional.empty();
		}
	}

}

package com.banking.service.implementation;

import java.util.Optional;

import com.banking.dtos.LoginDto;

public interface LoginService {

	Optional<LoginDto> authorizeUser(LoginDto logindto);

	Optional<LoginDto> findUserByUsername(String loginid);

}

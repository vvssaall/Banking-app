package com.banking.admin.controller;

import java.sql.Timestamp;
import java.util.Date;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.banking.dtos.RoleDto;



@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/role")
public class RoleController {

	@GetMapping("/{id}")
	public RoleDto getRoleVO(@PathVariable int id) {
		RoleDto dto = new RoleDto();
		dto.setRoleId(id);
		dto.setName("ADMIN_ROLE");
		dto.setDescription("This super power role!");
		dto.setDoe(new Timestamp(new Date().getTime()));
		dto.setDom(new Timestamp(new Date().getTime()));

		return dto;
	}

}

package com.utkarsh.taskflow.dto;

import com.utkarsh.taskflow.enums.Role;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class UserRequestDTO {
	@NotBlank(message = "Name cannot be empty")
	private String name;
	
	@NotBlank(message = "Email cannot be empty")
	@Email(message = "Enter a valid email")
	private String email;
	
	@NotBlank(message = "Password cannot be empty")
	private String password;
	
}

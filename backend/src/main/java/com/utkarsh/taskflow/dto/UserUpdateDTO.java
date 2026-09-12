package com.utkarsh.taskflow.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UserUpdateDTO {
	@NotBlank(message = "Name cannot be empty")
	private String name;
	
	@NotBlank(message = "Email cannot be empty")
	@Email(message = "Enter a valid email")
	private String email;
}

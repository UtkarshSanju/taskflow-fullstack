package com.utkarsh.taskflow.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class ChangePasswordDTO {
	@NotBlank(message = "New password cannot be empty")
	private String password;
}

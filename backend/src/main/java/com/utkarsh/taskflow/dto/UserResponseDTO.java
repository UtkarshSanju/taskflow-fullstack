package com.utkarsh.taskflow.dto;

import com.utkarsh.taskflow.enums.Role;

import lombok.Data;

@Data
public class UserResponseDTO {
	private Long id;
	private String email;
	private String name;
	private Role role;
}

package com.utkarsh.taskflow.mapper;

import com.utkarsh.taskflow.dto.UserRequestDTO;
import com.utkarsh.taskflow.dto.UserResponseDTO;
import com.utkarsh.taskflow.entity.User;

public class UserMapper {
	public static User toEntity(UserRequestDTO requestDTO) {
		User user = new User();
		user.setEmail(requestDTO.getEmail());
		user.setName(requestDTO.getName());
		user.setPassword(requestDTO.getPassword());
		user.setRole(requestDTO.getRole());
		
		return user;
	}
	
	public static UserResponseDTO toResponseDTO(User user) {
		UserResponseDTO responseDTO = new UserResponseDTO();
		responseDTO.setEmail(user.getEmail());
		responseDTO.setId(user.getId());
		responseDTO.setName(user.getName());
		responseDTO.setRole(user.getRole());
		
		return responseDTO;
	}
}

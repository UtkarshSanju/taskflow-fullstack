package com.utkarsh.taskflow.service;

import java.util.List;

import com.utkarsh.taskflow.dto.ChangePasswordDTO;
import com.utkarsh.taskflow.dto.UserRequestDTO;
import com.utkarsh.taskflow.dto.UserResponseDTO;
import com.utkarsh.taskflow.dto.UserUpdateDTO;

public interface UserService {
	UserResponseDTO createUser(UserRequestDTO requestDTO);
	
	List<UserResponseDTO> getAllUsers();
	
	UserResponseDTO getUserById(Long id);
	
	UserResponseDTO updateUser(Long id, UserUpdateDTO updatedRequestDTO);
	
	void deleteUser(Long id);
	
	String changePassword(Long id, ChangePasswordDTO dto);
}

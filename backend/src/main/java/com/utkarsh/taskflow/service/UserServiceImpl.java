package com.utkarsh.taskflow.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.utkarsh.taskflow.dto.ChangePasswordDTO;
import com.utkarsh.taskflow.dto.UserRequestDTO;
import com.utkarsh.taskflow.dto.UserResponseDTO;
import com.utkarsh.taskflow.dto.UserUpdateDTO;
import com.utkarsh.taskflow.entity.User;
import com.utkarsh.taskflow.enums.Role;
import com.utkarsh.taskflow.exception.ResourceNotFoundException;
import com.utkarsh.taskflow.mapper.UserMapper;
import com.utkarsh.taskflow.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{
	private final UserRepository userRepository;
	private final PasswordEncoder passwordEncoder;
	
	public UserResponseDTO createUser(UserRequestDTO requestDTO) {
		User user = UserMapper.toEntity(requestDTO);
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		user.setRole(Role.MEMBER);
		User savedUser = userRepository.save(user);
		return UserMapper.toResponseDTO(savedUser);
	}
	
	public List<UserResponseDTO> getAllUsers(){
		List<User> users = userRepository.findAll();
		
		return users.stream()
				.map(UserMapper :: toResponseDTO)
				.collect(Collectors.toList());
	}
	
	public UserResponseDTO getUserById(Long id) {
		User user = userRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("User not found with id : " + id));
		
		return UserMapper.toResponseDTO(user);
	}
	
	public UserResponseDTO updateUser(Long id, UserUpdateDTO updatedRequestDTO) {
		User existingUser = userRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("User not found with id : " + id));
		
		existingUser.setName(updatedRequestDTO.getName());
		existingUser.setEmail(updatedRequestDTO.getEmail());
		
		User updatedUser = userRepository.save(existingUser);
		return UserMapper.toResponseDTO(updatedUser);
	}
	
	public void deleteUser(Long id) {
		User existingUser = userRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("User not found with id : " + id));
		
		userRepository.delete(existingUser);
	}
	
	public String changePassword(Long id, ChangePasswordDTO dto) {
		User existingUser = userRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("User not found with id : " + id));
		
		if(passwordEncoder.matches(dto.getPassword(), existingUser.getPassword())) {
			throw new IllegalArgumentException("New password must be different from current passowd");
		}
		
		existingUser.setPassword(passwordEncoder.encode(dto.getPassword()));
		userRepository.save(existingUser);
		
		return "Password changed successfully";
	}
}

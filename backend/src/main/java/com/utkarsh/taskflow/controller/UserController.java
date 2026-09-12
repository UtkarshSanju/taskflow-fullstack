package com.utkarsh.taskflow.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.utkarsh.taskflow.dto.ChangePasswordDTO;
import com.utkarsh.taskflow.dto.UserRequestDTO;
import com.utkarsh.taskflow.dto.UserResponseDTO;
import com.utkarsh.taskflow.dto.UserUpdateDTO;
import com.utkarsh.taskflow.entity.User;
import com.utkarsh.taskflow.service.UserService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {
	private final UserService userService;
	
	@PostMapping
	public ResponseEntity<UserResponseDTO> createUser(@Valid @RequestBody UserRequestDTO requestDTO){
		UserResponseDTO savedUser = userService.createUser(requestDTO);
		return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
	}
	
	@GetMapping
	public ResponseEntity<List<UserResponseDTO>> getAllUsers(){
		List<UserResponseDTO> allUsers = userService.getAllUsers();
		return ResponseEntity.ok(allUsers);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<UserResponseDTO> getUserById(@PathVariable Long id){
		UserResponseDTO user = userService.getUserById(id);
		return ResponseEntity.ok(user);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<UserResponseDTO> updateUser(@PathVariable Long id, 
			@Valid @RequestBody UserUpdateDTO updatedRequestDTO){
		UserResponseDTO updatedUser = userService.updateUser(id, updatedRequestDTO);
		return ResponseEntity.ok(updatedUser);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteUser(@PathVariable Long id){
		userService.deleteUser(id);
		return ResponseEntity.noContent().build();
	}
	
	@PutMapping("/{id}/change-password")
	public ResponseEntity<String> changePassword(@PathVariable Long id, @Valid @RequestBody ChangePasswordDTO dto){
		String message = userService.changePassword(id, dto);
		return ResponseEntity.ok(message);
	}
}

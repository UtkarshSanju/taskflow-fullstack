package com.utkarsh.taskflow.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.utkarsh.taskflow.dto.LoginRequestDTO;
import com.utkarsh.taskflow.dto.LoginResponseDTO;
import com.utkarsh.taskflow.service.AuthService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
	private final AuthService authService;
	
	@PostMapping("/login")
	public ResponseEntity<LoginResponseDTO> login(@Valid @RequestBody LoginRequestDTO requestDTO){
		LoginResponseDTO responseDTO = authService.login(requestDTO);
		return ResponseEntity.ok(responseDTO);
	}
}

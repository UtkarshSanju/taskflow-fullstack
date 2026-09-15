package com.utkarsh.taskflow.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.utkarsh.taskflow.dto.LoginRequestDTO;
import com.utkarsh.taskflow.dto.LoginResponseDTO;
import com.utkarsh.taskflow.entity.User;
import com.utkarsh.taskflow.exception.InvalidCredentialsException;
import com.utkarsh.taskflow.exception.ResourceNotFoundException;
import com.utkarsh.taskflow.mapper.UserMapper;
import com.utkarsh.taskflow.repository.UserRepository;
import com.utkarsh.taskflow.security.JwtUtil;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService{
	private final UserRepository userRepository;
	private final PasswordEncoder passwordEncoder;
	private final JwtUtil jwtUtil;
	
	public LoginResponseDTO login(LoginRequestDTO requestDTO) {
		User user = userRepository.findByEmail(requestDTO.getEmail())
				.orElseThrow(() -> new InvalidCredentialsException("Invalid email or Password"));
		
		if(!passwordEncoder.matches(requestDTO.getPassword(), user.getPassword())) {
			throw new InvalidCredentialsException("Invaild email or password");
		}
		
		String token = jwtUtil.generateToken(requestDTO.getEmail());
		
		LoginResponseDTO responseDTO = new LoginResponseDTO();
		responseDTO.setToken(token);
		responseDTO.setUser(UserMapper.toResponseDTO(user));
		
		return responseDTO;
	}
}

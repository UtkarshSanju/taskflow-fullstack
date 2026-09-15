package com.utkarsh.taskflow.service;

import com.utkarsh.taskflow.dto.LoginRequestDTO;
import com.utkarsh.taskflow.dto.LoginResponseDTO;

public interface AuthService {
	LoginResponseDTO login(LoginRequestDTO requestDTO);
}

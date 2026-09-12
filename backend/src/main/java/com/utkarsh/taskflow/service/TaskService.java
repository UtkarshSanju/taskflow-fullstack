package com.utkarsh.taskflow.service;


import java.util.List;

import com.utkarsh.taskflow.dto.TaskRequestDTO;
import com.utkarsh.taskflow.dto.TaskResponseDTO;
import com.utkarsh.taskflow.enums.Status;

public interface TaskService {
	TaskResponseDTO createTask(TaskRequestDTO requestDTO);
	
	List<TaskResponseDTO> getAllTasks();
	
	TaskResponseDTO getTaskById(Long id);
	
	TaskResponseDTO updateTask(Long id, TaskRequestDTO updatedRequestDTO);
	
	void deleteTask(Long id);
	
	TaskResponseDTO updateStatus(Long id, Status status);
}

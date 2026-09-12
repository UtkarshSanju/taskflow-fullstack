package com.utkarsh.taskflow.mapper;

import com.utkarsh.taskflow.dto.TaskRequestDTO;
import com.utkarsh.taskflow.dto.TaskResponseDTO;
import com.utkarsh.taskflow.entity.Task;
import com.utkarsh.taskflow.entity.User;

public class TaskMapper {
	public static Task toEntity(TaskRequestDTO requestDTO, User user) {
		Task task = new Task();
		task.setTitle(requestDTO.getTitle());
		task.setDescription(requestDTO.getDescription());
		task.setStatus(requestDTO.getStatus());
		task.setDueDate(requestDTO.getDueDate());
		task.setAssignedUser(user);
		return task;
	}
	
	public static TaskResponseDTO toResponseDTO(Task task) {
		TaskResponseDTO responseDTO = new TaskResponseDTO();
		responseDTO.setId(task.getId());
		responseDTO.setTitle(task.getTitle());
		responseDTO.setDescription(task.getDescription());
		responseDTO.setStatus(task.getStatus());
		responseDTO.setDueDate(task.getDueDate());
		if(task.getAssignedUser() != null) {
			responseDTO.setUserId(task.getAssignedUser().getId());
			responseDTO.setUserName(task.getAssignedUser().getName());
		}
		return responseDTO;
	}
}

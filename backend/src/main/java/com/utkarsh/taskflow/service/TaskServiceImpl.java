package com.utkarsh.taskflow.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.utkarsh.taskflow.dto.TaskRequestDTO;
import com.utkarsh.taskflow.dto.TaskResponseDTO;
import com.utkarsh.taskflow.entity.Task;
import com.utkarsh.taskflow.entity.User;
import com.utkarsh.taskflow.enums.Status;
import com.utkarsh.taskflow.exception.ResourceNotFoundException;
import com.utkarsh.taskflow.mapper.TaskMapper;
import com.utkarsh.taskflow.repository.TaskRepository;
import com.utkarsh.taskflow.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService{
	
	private final TaskRepository taskRepository;
	private final UserRepository userRepository;
	
	public TaskResponseDTO createTask(TaskRequestDTO requestDTO) {
		User user = userRepository.findById(requestDTO.getUserId())
				.orElseThrow(() -> new ResourceNotFoundException("User not found with id : " + requestDTO.getUserId()));
		Task task = TaskMapper.toEntity(requestDTO, user);
		Task savedTask = taskRepository.save(task);
		return TaskMapper.toResponseDTO(savedTask);
	}
	
	public List<TaskResponseDTO> getAllTasks(){
		List<Task> tasks =  taskRepository.findAll();
		
		return tasks.stream()
				.map(TaskMapper :: toResponseDTO)
				.collect(Collectors.toList());
	}
	
	public TaskResponseDTO getTaskById(Long id) {
		Task task = taskRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Task not found with id : " + id));
		
		return TaskMapper.toResponseDTO(task);
	}
	
	public TaskResponseDTO updateTask(Long id, TaskRequestDTO updatedRequestDTO) {
		Task existingTask = taskRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Task not found with id : " + id));
		
		User user = userRepository.findById(updatedRequestDTO.getUserId())
				.orElseThrow(() -> new ResourceNotFoundException("User not found with id : " + updatedRequestDTO.getUserId()));
		
		existingTask.setTitle(updatedRequestDTO.getTitle());
		existingTask.setStatus(updatedRequestDTO.getStatus());
		existingTask.setDescription(updatedRequestDTO.getDescription());
		existingTask.setDueDate(updatedRequestDTO.getDueDate());
		existingTask.setAssignedUser(user);
		
		Task updatedTask = taskRepository.save(existingTask);
		
		return TaskMapper.toResponseDTO(updatedTask);
	}
	
	public void deleteTask(Long id) {
		Task existingTask = taskRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Task not found with id : " + id));
		
		taskRepository.delete(existingTask);
	}
	
	public TaskResponseDTO updateStatus(Long id, Status status) {
		Task existingTask = taskRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Task not found with id : " + id));
		
		existingTask.setStatus(status);
		Task updatedTask = taskRepository.save(existingTask);
		
		return TaskMapper.toResponseDTO(updatedTask);
	}
}

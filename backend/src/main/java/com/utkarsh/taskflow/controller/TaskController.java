package com.utkarsh.taskflow.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.utkarsh.taskflow.dto.StatusChangeDTO;
import com.utkarsh.taskflow.dto.TaskRequestDTO;
import com.utkarsh.taskflow.dto.TaskResponseDTO;
import com.utkarsh.taskflow.entity.Task;
import com.utkarsh.taskflow.service.TaskService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/tasks")
@RequiredArgsConstructor
public class TaskController {
	private final TaskService taskService;
	
	@PostMapping
	public ResponseEntity<TaskResponseDTO> createTask(@Valid @RequestBody TaskRequestDTO requestDTO){
		TaskResponseDTO savedTask = taskService.createTask(requestDTO);
		return new ResponseEntity<>(savedTask, HttpStatus.CREATED);
	}
	
	@GetMapping
	public ResponseEntity<List<TaskResponseDTO>> getAllTasks(){
		List<TaskResponseDTO> tasks = taskService.getAllTasks();
		return new ResponseEntity<>(tasks, HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<TaskResponseDTO> getTaskById(@PathVariable Long id){
		TaskResponseDTO taskById = taskService.getTaskById(id);
		return new ResponseEntity<>(taskById, HttpStatus.OK);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<TaskResponseDTO> updateTask(@PathVariable Long id, 
			@Valid @RequestBody TaskRequestDTO requestDTO){
		TaskResponseDTO updatedTask = taskService.updateTask(id, requestDTO);
		return ResponseEntity.ok(updatedTask);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteTask(@PathVariable Long id){
		taskService.deleteTask(id);
		return ResponseEntity.noContent().build();
	}
	
	@PatchMapping("/{id}/status")
	public ResponseEntity<TaskResponseDTO> updateStatus(@PathVariable Long id, @Valid @RequestBody StatusChangeDTO statusDTO){
		TaskResponseDTO updatedStatus = taskService.updateStatus(id, statusDTO.getStatus());
		return ResponseEntity.ok(updatedStatus);
	}
}

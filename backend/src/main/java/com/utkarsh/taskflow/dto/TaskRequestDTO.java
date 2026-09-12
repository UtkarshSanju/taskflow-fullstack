package com.utkarsh.taskflow.dto;

import java.time.LocalDate;

import com.utkarsh.taskflow.enums.Status;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class TaskRequestDTO {
	@NotBlank(message = "Title cannot be empty.")
	private String title;
	
	private String description;
	
	@NotNull(message = "Status must be provided.")
	private Status status;
	
	@NotNull(message = "Due date must be provided.")
	@FutureOrPresent(message = "Due date cannot be in the past.")
	private LocalDate dueDate;
	
	private Long userId;
}

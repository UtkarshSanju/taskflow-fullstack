package com.utkarsh.taskflow.dto;

import java.time.LocalDate;

import com.utkarsh.taskflow.enums.Status;

import lombok.Data;

@Data
public class TaskResponseDTO {
	private Long id;
	private String title;
	private String description;
	private Status status;
	private LocalDate dueDate;
	private Long userId;
	private String userName;
}

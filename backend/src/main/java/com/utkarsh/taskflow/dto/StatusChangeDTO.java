package com.utkarsh.taskflow.dto;


import com.utkarsh.taskflow.enums.Status;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class StatusChangeDTO {
	@NotNull(message = "Status must be provided")
	private Status status;
}

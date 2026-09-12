package com.utkarsh.taskflow.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.utkarsh.taskflow.entity.Task;

public interface TaskRepository extends JpaRepository<Task, Long> {

}

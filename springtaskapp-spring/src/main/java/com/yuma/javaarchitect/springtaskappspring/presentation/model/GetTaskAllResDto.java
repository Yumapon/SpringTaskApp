package com.yuma.javaarchitect.springtaskappspring.presentation.model;

import lombok.Getter;

import java.util.List;

import com.yuma.javaarchitect.springtaskappcore.domain.entity.Task;

import lombok.Builder;

@Builder
@Getter
public class GetTaskAllResDto {

    List<Task> taskList;
    
}

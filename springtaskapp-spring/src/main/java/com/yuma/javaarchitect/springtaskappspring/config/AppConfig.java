package com.yuma.javaarchitect.springtaskappspring.config;

import com.yuma.javaarchitect.springtaskappcore.domain.entity.repository.TaskRepo;
import com.yuma.javaarchitect.springtaskappcore.usecase.AddTaskUsecase;
import com.yuma.javaarchitect.springtaskappcore.usecase.ChangeTaskUsecase;
import com.yuma.javaarchitect.springtaskappcore.usecase.DeleteTaskUsecase;
import com.yuma.javaarchitect.springtaskappcore.usecase.GetTaskUsecase;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
    public AddTaskUsecase addTaskUsecase(TaskRepo repo) {
        return new AddTaskUsecase(repo);
    }

    @Bean
    public ChangeTaskUsecase changeTaskUsecase(TaskRepo repo) {
        return new ChangeTaskUsecase(repo);
    }

    @Bean
    public DeleteTaskUsecase deleteTaskUsecase(TaskRepo repo) {
        return new DeleteTaskUsecase(repo);
    }

    @Bean
    public GetTaskUsecase getTaskUsecase(TaskRepo repo) {
        return new GetTaskUsecase(repo);
    }
    
}

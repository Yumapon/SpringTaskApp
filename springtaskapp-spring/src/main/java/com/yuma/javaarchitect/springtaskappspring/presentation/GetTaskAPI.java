package com.yuma.javaarchitect.springtaskappspring.presentation;

import java.util.List;

import com.yuma.javaarchitect.springtaskappcore.domain.entity.Task;
import com.yuma.javaarchitect.springtaskappcore.usecase.GetTaskUsecase;
import com.yuma.javaarchitect.springtaskappspring.presentation.model.GetTaskAllResDto;
import com.yuma.javaarchitect.springtaskappspring.presentation.model.GetTaskByIdResDto;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.web.bind.annotation.CrossOrigin;
import lombok.AllArgsConstructor;
import lombok.NonNull;

@AllArgsConstructor
@RestController
public class GetTaskAPI {

    @NonNull
    private final GetTaskUsecase usecase;

    @NonNull
    @GetMapping(path = "/task/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @CrossOrigin
    public GetTaskByIdResDto getTaskById(@NonNull @PathVariable("id") String id) {

        Task task = usecase.invoke(id);

        GetTaskByIdResDto result = GetTaskByIdResDto.builder()
                                        .id(task.getNum())
                                        .name(task.getName())
                                        .content(task.getContent())
                                        .deadline(task.getDeadline())
                                        .client(task.getClient())
                                        .build();

        return result;

    }

    @NonNull
    @GetMapping(path = "getall", produces = MediaType.APPLICATION_JSON_VALUE)
    @CrossOrigin
    public GetTaskAllResDto getTaskAll(){

        List<Task> taskList = usecase.invoke();

        GetTaskAllResDto result = GetTaskAllResDto.builder().taskList(taskList).build();

        return result;
    }

}

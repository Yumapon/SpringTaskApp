package com.yuma.javaarchitect.springtaskappspring.presentation;

import com.yuma.javaarchitect.springtaskappcore.usecase.ChangeTaskUsecase;
import com.yuma.javaarchitect.springtaskappspring.presentation.model.ChangeTaskReqDto;
import com.yuma.javaarchitect.springtaskappspring.presentation.model.ChangeTaskResDto;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;
import lombok.NonNull;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;

@AllArgsConstructor
@RestController
@RequestMapping("yuma/task")
public class ChangeTaskAPI {

    @NonNull
    private final ChangeTaskUsecase usecase;

    @NonNull
    @PostMapping(path = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @CrossOrigin
    public ChangeTaskResDto changeTask(@NonNull @PathVariable("id") String id, @NonNull @RequestBody ChangeTaskReqDto reqDto){

        usecase.invoke(id, reqDto.getName(), reqDto.getContent(), reqDto.getDeadline(), reqDto.getClient());

        ChangeTaskResDto result = ChangeTaskResDto.builder().id(reqDto.getNum()).build();

        return result;

    }
    
}

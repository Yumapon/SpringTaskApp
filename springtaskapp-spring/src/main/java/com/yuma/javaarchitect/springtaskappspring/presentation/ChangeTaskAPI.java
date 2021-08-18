package com.yuma.javaarchitect.springtaskappspring.presentation;

import com.yuma.javaarchitect.springtaskappcore.usecase.ChangeTaskUsecase;
import com.yuma.javaarchitect.springtaskappspring.presentation.model.ChangeTaskReqDto;
import com.yuma.javaarchitect.springtaskappspring.presentation.model.ChangeTaskResDto;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;
import lombok.NonNull;

@AllArgsConstructor
@RestController
public class ChangeTaskAPI {

    @NonNull
    private final ChangeTaskUsecase usecase;

    @NonNull
    @PostMapping(path = "/changetask", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ChangeTaskResDto changeTask(@NonNull @RequestBody ChangeTaskReqDto reqDto){

        usecase.invoke(reqDto.getNum(), reqDto.getName(), reqDto.getContent(), reqDto.getDeadline(), reqDto.getClient());

        ChangeTaskResDto result = ChangeTaskResDto.builder().id(reqDto.getNum()).build();

        return result;

    }
    
}

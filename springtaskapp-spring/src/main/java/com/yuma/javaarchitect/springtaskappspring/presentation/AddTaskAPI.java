package com.yuma.javaarchitect.springtaskappspring.presentation;

import com.yuma.javaarchitect.springtaskappcore.usecase.AddTaskUsecase;
import com.yuma.javaarchitect.springtaskappspring.presentation.model.AddTaskReqDto;
import com.yuma.javaarchitect.springtaskappspring.presentation.model.AddTaskResDto;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;
import lombok.NonNull;

@AllArgsConstructor
@RestController
@RequestMapping("yuma/task")
public class AddTaskAPI {

    @NonNull
    private final AddTaskUsecase usecase;

    @NonNull
    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    @CrossOrigin
    public AddTaskResDto add(@NonNull @RequestBody AddTaskReqDto reqDto){

        //
        String id = usecase.invoke(reqDto.getName(), reqDto.getContent(), reqDto.getDeadline(), reqDto.getClient());

        AddTaskResDto result = AddTaskResDto.builder().id(id).build();

        return result;
        
    }
    
}

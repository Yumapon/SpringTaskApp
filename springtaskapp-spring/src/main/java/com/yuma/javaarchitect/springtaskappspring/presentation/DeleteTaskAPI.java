package com.yuma.javaarchitect.springtaskappspring.presentation;

import com.yuma.javaarchitect.springtaskappcore.usecase.DeleteTaskUsecase;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;
import lombok.NonNull;

@AllArgsConstructor
@RestController
@RequestMapping("yuma/task")
public class DeleteTaskAPI {

    @NonNull
    private DeleteTaskUsecase usecase;

    @NonNull
    @DeleteMapping(path = "/{id}")
    @CrossOrigin
    public void changeTask(@NonNull @PathVariable("id") String id){
        usecase.invoke(id);
    }
    
}
package com.yuma.javaarchitect.springtaskappspring.presentation;

import com.yuma.javaarchitect.springtaskappcore.usecase.DeleteTaskUsecase;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;
import lombok.NonNull;

@AllArgsConstructor
@RestController
public class DeleteTaskAPI {

    @NonNull
    private DeleteTaskUsecase usecase;

    @NonNull
    @DeleteMapping(path = "/deletetask/{id}")
    public void changeTask(@NonNull @PathVariable("id") String id){
        usecase.invoke(id);
    }
    
}
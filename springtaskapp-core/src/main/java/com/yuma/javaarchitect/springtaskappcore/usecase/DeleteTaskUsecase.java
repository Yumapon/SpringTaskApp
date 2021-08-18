package com.yuma.javaarchitect.springtaskappcore.usecase;

import com.yuma.javaarchitect.springtaskappcore.domain.entity.repository.TaskRepo;

import lombok.AllArgsConstructor;
import lombok.NonNull;

@AllArgsConstructor
public class DeleteTaskUsecase {

    @NonNull
    private final TaskRepo repo;

    public void invoke(@NonNull String num){

        //Taskを削除
        repo.remove(num);
    }
    
}

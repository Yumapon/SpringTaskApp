package com.yuma.javaarchitect.springtaskappcore.usecase;

import java.time.LocalDate;

import com.yuma.javaarchitect.springtaskappcore.domain.entity.Task;
import com.yuma.javaarchitect.springtaskappcore.domain.entity.repository.TaskRepo;

import lombok.AllArgsConstructor;
import lombok.NonNull;

@AllArgsConstructor
public class ChangeTaskUsecase {

    @NonNull
    private final TaskRepo repo;

    public void invoke(@NonNull String num, @NonNull String inputName, String inputContent, LocalDate inputdatatime, String inputclient){

        //Entityクラスを生成
        Task task = Task.builder().num(num).name(inputName).content(inputContent).deadline(inputdatatime).client(inputclient).build();

        //TaskをDBに格納
        repo.update(task);

    }
    
}

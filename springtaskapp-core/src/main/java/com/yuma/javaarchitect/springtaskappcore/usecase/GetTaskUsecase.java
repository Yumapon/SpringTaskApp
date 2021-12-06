package com.yuma.javaarchitect.springtaskappcore.usecase;

import java.util.List;

import com.yuma.javaarchitect.springtaskappcore.domain.entity.Task;
import com.yuma.javaarchitect.springtaskappcore.domain.entity.repository.TaskRepo;
import com.yuma.javaarchitect.springtaskappcore.usecase.exception.SystemException;

import lombok.AllArgsConstructor;
import lombok.NonNull;

@AllArgsConstructor
public class GetTaskUsecase {

    @NonNull
    private final TaskRepo repo;

    public Task invoke(@NonNull String inputid){

        Task task;

        if(repo.exists(inputid)) {
            task  = repo.getbyid(inputid);
            System.out.println(task.getNum() + task.getName() + task.getContent() + task.getDeadline() + task.getClient());
        }else{
            task = Task.builder().build();
        }

        return task;

    }

    public List<Task> invoke(){
       
        try{
            return repo.getAll();
        } catch (Exception e) {
            throw new SystemException(e);
        }

    }
    
}

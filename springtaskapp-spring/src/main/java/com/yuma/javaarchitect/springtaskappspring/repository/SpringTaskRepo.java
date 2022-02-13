package com.yuma.javaarchitect.springtaskappspring.repository;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import com.amazonaws.xray.spring.aop.XRayEnabled;
import com.yuma.javaarchitect.springtaskappcore.domain.entity.Task;
import com.yuma.javaarchitect.springtaskappcore.domain.entity.repository.TaskRepo;
import com.yuma.javaarchitect.springtaskappspring.repository.dao.TaskDao;
import com.yuma.javaarchitect.springtaskappspring.repository.dao.TaskDaoEntity;

import org.springframework.stereotype.Repository;

import lombok.AllArgsConstructor;
import lombok.NonNull;

@AllArgsConstructor
@Repository
@XRayEnabled
public class SpringTaskRepo implements TaskRepo{

    @NonNull
    private final TaskDao repository;

    private TaskDaoEntity buildEntity(@NonNull Task task, boolean isNew){
        return TaskDaoEntity.builder()
                .num(task.getNum())
                .name(task.getName())
                .content(task.getContent())
                .deadline(task.getDeadline())
                .client(task.getClient())
                .isNew(isNew)
                .build();
    }

    @Override
    public boolean exists(String id) {
        return repository.existsById(id);
    }

    @Override
    public List<Task> getAll() {
        return StreamSupport.stream(repository.findAll().spliterator(), false).map(repositoryEntity -> {
            return Task.builder()
                    .num(repositoryEntity.getId())
                    .name(repositoryEntity.getName())
                    .content(repositoryEntity.getContent())
                    .deadline(repositoryEntity.getDeadline())
                    .client(repositoryEntity.getClient())
                    .build();
        }).collect(Collectors.toList());
    }

    @Override
    public Task getbyid(String id) {

        //TODO 対応したtask番号がない場合にInternalServerエラー出てしまう。時間ないので未対応

        TaskDaoEntity dao = (repository.findById(id)).get();
        return Task.builder().num(dao.getNum()).name(dao.getName()).deadline(dao.getDeadline()).content(dao.getContent()).client(dao.getClient()).build();
        
    }
    
}

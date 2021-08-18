package com.yuma.javaarchitect.springtaskappcore.domain.entity.repository;

import java.util.List;

import com.yuma.javaarchitect.springtaskappcore.domain.entity.Task;

public interface TaskRepo {
    
    boolean exists(String num);

    void add(Task task);

    List<Task> getAll();

    Task getbyid(String num);

    void update(Task task);

    void remove(String num);
}

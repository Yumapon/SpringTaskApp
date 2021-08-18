package com.yuma.javaarchitect.springtaskappspring.repository.dao;

import org.springframework.data.repository.CrudRepository;

public interface TaskDao extends CrudRepository<TaskDaoEntity, String>{
    
}

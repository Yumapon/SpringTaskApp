package com.yuma.javaarchitect.springtaskappcore.domain.entity.repository;

import java.util.List;


import com.yuma.javaarchitect.springtaskappcore.domain.entity.User;

public interface UserRepo {

    boolean exists(int id);

    void add(User user);

    List<User> getAll();

    User getbyid(int id);

    void update(User user);

    void remove(int id);
    
}
package com.yuma.javaarchitect.springtaskappcore.domain.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class User {

    //User id
    private int id;

    //password
    private String password;
    
}
package com.yuma.javaarchitect.springtaskappspring.presentation.model;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

@Getter
@Setter
public class GetTaskByIdReqDto {

    @NonNull
    private String id;   
    
}

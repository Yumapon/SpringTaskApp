package com.yuma.javaarchitect.springtaskappspring.presentation.model;

import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;

@Builder
@Getter
public class AddTaskResDto {

    @NonNull
    private final String id;
    
}

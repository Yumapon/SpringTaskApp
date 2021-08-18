package com.yuma.javaarchitect.springtaskappspring.presentation.model;

import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;

@Builder
@Getter
public class ChangeTaskResDto {

    @NonNull
    private final String id;
    
}

package com.yuma.javaarchitect.springtaskappspring.presentation.model;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;

@Getter
@Builder
public class GetTaskByIdResDto {

    //Task番号
    @NonNull
    private String id;

    //Task名
    @NonNull
    private String name;

    //Task内容
    private String content;

    //期日
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate deadline;

    //Task依頼主
    private String client;

}

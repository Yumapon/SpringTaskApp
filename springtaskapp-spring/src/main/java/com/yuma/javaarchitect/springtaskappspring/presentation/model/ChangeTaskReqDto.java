package com.yuma.javaarchitect.springtaskappspring.presentation.model;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

@Getter
@Setter
public class ChangeTaskReqDto {

    //Task番号
    @NonNull
    private String num;

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

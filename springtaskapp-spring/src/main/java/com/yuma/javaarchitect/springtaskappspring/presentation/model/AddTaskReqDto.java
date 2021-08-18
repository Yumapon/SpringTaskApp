package com.yuma.javaarchitect.springtaskappspring.presentation.model;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

/**
 * AddTaskAPIを呼び出す際のエンティティクラス
 */

@Getter
@Setter
public class AddTaskReqDto {
    
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

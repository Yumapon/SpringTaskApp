package com.yuma.javaarchitect.springtaskappcore.domain.entity;

import java.time.LocalDate;

import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

/**
 * Taskのエンティティクラス
 */
@Builder
@Getter
@Setter
public class Task {

    //Task番号（システム的に使用）
    @NonNull
    private String num;

    //Task名
    private String name;

    //Task内容
    private String content;

    //期日
    private LocalDate deadline;

    //Task依頼主
    private String client;
    
}

package com.yuma.javaarchitect.springtaskappspring.repository.dao;

import java.time.LocalDate;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.data.domain.Persistable;
import org.springframework.data.relational.core.mapping.Table;
import org.springframework.data.annotation.Transient;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@AllArgsConstructor(access = AccessLevel.PACKAGE)
@RequiredArgsConstructor(onConstructor = @__({ @PersistenceConstructor }))
@Builder
@Getter
@Table("TASK_LIST")
public class TaskDaoEntity implements Persistable<String>{

    @Id
    @NonNull
    private final String num;
    
    //Task名
    private final String name;

    //Task内容
    private final String content;

    //期日
    private final LocalDate deadline;

    //Task依頼主
    private final String client;

    @Builder.Default
    @Setter
    @Transient
    private boolean isNew = false;

    @Override
    public String getId(){
        return num;
    }
}

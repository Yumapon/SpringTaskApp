package com.yuma.javaarchitect.springtaskappcore.usecase;

import java.time.LocalDate;
import java.util.UUID;

import com.yuma.javaarchitect.springtaskappcore.domain.entity.Task;
import com.yuma.javaarchitect.springtaskappcore.domain.entity.repository.TaskRepo;

import lombok.AllArgsConstructor;
import lombok.NonNull;

/**
 * Taskを格納するユースケース
 */
@AllArgsConstructor
public class AddTaskUsecase {

    @NonNull
    private final TaskRepo repo;

    public String invoke(@NonNull String inputName, String inputContent, LocalDate inputdatatime, String inputclient){

        //Task番号を採番する
        String num = this.getTaskNum();

        //Entityクラスを生成
        Task task = Task.builder().num(num).name(inputName).content(inputContent).deadline(inputdatatime).client(inputclient).build();

        //TaskをDBに格納
        repo.add(task);

        return num;
        
    }

    // TaskIDを採番するメソッド
    public String getTaskNum() {

		UUID uuid = UUID.randomUUID();
        String taskNum = uuid.toString();

		return taskNum;
	}
    
}

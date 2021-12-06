package com.yuma.javaarchitect.springtaskappspring.presentation;

import com.yuma.javaarchitect.springtaskappcore.usecase.DeleteTaskUsecase;
import com.yuma.javaarchitect.springtaskappspring.presentation.error.UnauthorizedError;
import com.yuma.javaarchitect.springtaskappspring.service.CallApiService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;
import lombok.NonNull;

/**
 * DELETE MethodのAPIを定義するクラス
 * 
 * 現状定義しているAPIは以下1つ
 * {@link DeleteTaskAPI#deleteTask(String)}
 */
@AllArgsConstructor
@RestController
@RequestMapping("yuma/task")
public class DeleteTaskAPI {

    //Task削除実行クラス
    @NonNull
    private DeleteTaskUsecase usecase;

    //API呼び出しクラス
    @Autowired
    private final CallApiService service;

    //Loggerクラス
    private static final Logger logger = LoggerFactory.getLogger(DeleteTaskAPI.class);

    /**
     * idで指定されたtaskをDBから削除する
     * 
     * 実行種別はwrite
     * 
     * 実行可能Roleはadminのみ
     * 
     * @param id
     * @throws UnauthorizedError
     */
    @NonNull
    @DeleteMapping(path = "/{id}")
    @CrossOrigin
    public void deleteTask(@NonNull @PathVariable("id") String id, @RequestParam("email") String email) throws UnauthorizedError{

        //Log記録
        logger.info("start deleteTask api");

        //権限チェック
        if(service.checkRole(email, "write")){

            usecase.invoke(id);

        }
        //Userに権限がない場合、エラー出力
        else {
            logger.warn("deleteTaskAPIを権限のないユーザが呼び出しました");
            throw new UnauthorizedError();
        }
    }
    
}
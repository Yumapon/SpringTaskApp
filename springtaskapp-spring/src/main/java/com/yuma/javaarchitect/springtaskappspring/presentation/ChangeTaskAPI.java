package com.yuma.javaarchitect.springtaskappspring.presentation;

import com.yuma.javaarchitect.springtaskappcore.usecase.ChangeTaskUsecase;
import com.yuma.javaarchitect.springtaskappspring.presentation.error.UnauthorizedError;
import com.yuma.javaarchitect.springtaskappspring.presentation.model.ChangeTaskReqDto;
import com.yuma.javaarchitect.springtaskappspring.presentation.model.ChangeTaskResDto;
import com.yuma.javaarchitect.springtaskappspring.service.CallApiService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;

import lombok.AllArgsConstructor;
import lombok.NonNull;

/**
 * PUT MethodのAPIを定義するクラス
 * 
 * 現状定義しているAPIは以下1つ
 * {@link ChangeTaskAPI#changeTask(String, ChangeTaskReqDto)}
 */
@AllArgsConstructor
@RestController
@RequestMapping("yuma/task")
public class ChangeTaskAPI {

    //Task編集実行クラス
    @NonNull
    private final ChangeTaskUsecase usecase;

    //API呼び出しクラス
    @Autowired
    private final CallApiService service;

    //Loggerクラス
    private static final Logger logger = LoggerFactory.getLogger(ChangeTaskAPI.class);

    /**
     * idで指定されたtaskの内容を変更するAPI
     * 
     * 実行種別はwrite
     * 
     * 実行可能Roleはadminのみ
     * 
     * @param id
     * @param reqDto
     * @return
     */
    @NonNull
    @PostMapping(path = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @CrossOrigin
    public ChangeTaskResDto changeTask(@NonNull @PathVariable("id") String id, @NonNull @RequestBody ChangeTaskReqDto reqDto, @RequestParam("email") String email) throws UnauthorizedError{

        //Log記録
        logger.info("start changeTask api");

        //権限チェック
        if(service.checkRole(email, "write")){

            usecase.invoke(id, reqDto.getName(), reqDto.getContent(), reqDto.getDeadline(), reqDto.getClient());

            ChangeTaskResDto result = ChangeTaskResDto.builder().id(reqDto.getNum()).build();

            return result;

        }
        //Userに権限がない場合、エラー出力
        else {
            logger.warn("changeTaskAPIを権限のないユーザが呼び出しました");
            throw new UnauthorizedError();
        }

    }
    
}

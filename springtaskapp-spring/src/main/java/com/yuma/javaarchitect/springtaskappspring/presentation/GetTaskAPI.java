package com.yuma.javaarchitect.springtaskappspring.presentation;

import java.util.List;

import com.yuma.javaarchitect.springtaskappcore.domain.entity.Task;
import com.yuma.javaarchitect.springtaskappcore.usecase.GetTaskUsecase;
import com.yuma.javaarchitect.springtaskappspring.presentation.error.UnauthorizedError;
import com.yuma.javaarchitect.springtaskappspring.presentation.model.GetTaskAllResDto;
import com.yuma.javaarchitect.springtaskappspring.presentation.model.GetTaskByIdResDto;
import com.yuma.javaarchitect.springtaskappspring.service.CallApiService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CrossOrigin;

import lombok.AllArgsConstructor;
import lombok.NonNull;

/**
 * GET MethodのAPIを定義するクラス
 * 
 * 現状定義しているAPIは以下二つ
 * {@link GetTaskAPI#getTaskById(String, String)}
 * {@link GetTaskAPI#getTaskAll(String)}
 * 
 */
@AllArgsConstructor
@RestController
@RequestMapping("yuma/task")
public class GetTaskAPI {

    //Task取得実行クラス
    @NonNull
    private final GetTaskUsecase usecase;

    //API呼び出しクラス
    @Autowired
    private final CallApiService service;

    //Loggerクラス
    private static final Logger logger = LoggerFactory.getLogger(GetTaskAPI.class);

    /**
     * idで渡されたtaskを一つ返す。
     * 権限種別はread
     * 
     * 実行可能Roleはadminとguest
     * 
     * これも本当はemailは使いたくないけど、、、いちいち一意な値をつけるの面倒
     * 
     * @param id
     * @param email
     * @return
     * @throws UnauthorizedError
     */
    @NonNull
    @GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @CrossOrigin
    public GetTaskByIdResDto getTaskById(@NonNull @PathVariable("id") String id, @RequestParam("email") String email) throws UnauthorizedError {

        //Log記録
        logger.info("start getTask api");

        //権限チェック
        if(service.checkRole(email, "read")){
            Task task = usecase.invoke(id);

            GetTaskByIdResDto result = GetTaskByIdResDto.builder()
                                            .id(task.getNum())
                                            .name(task.getName())
                                            .content(task.getContent())
                                            .deadline(task.getDeadline())
                                            .client(task.getClient())
                                            .build();

            return result;
        }
        //Userに権限がない場合、エラー出力
        else {
            logger.warn("getTaskAPIを権限のないユーザが呼び出しました");
            throw new UnauthorizedError();
        }

    }

    /**
     * DBに格納されているtaskを全て返す。
     * 権限種別はread
     * 
     * 実行可能Roleはadminとguest
     * 
     * 本当はemailとかDBに入れたくない。個人情報なので。
     * 
     * @param email
     * @return
     * @throws UnauthorizedError
     */
    @NonNull
    @GetMapping(path = "/getall", produces = MediaType.APPLICATION_JSON_VALUE)
    @CrossOrigin
    public GetTaskAllResDto getTaskAll(@RequestParam("email") String email) throws UnauthorizedError{

        //Log記録
        logger.info("start getTaskAll api");

        //権限チェック
        if(service.checkRole(email, "read")){
            List<Task> taskList = usecase.invoke();

            GetTaskAllResDto result = GetTaskAllResDto.builder().taskList(taskList).build();

            //Log記録Error
            logger.info("end getTaskAll api");
        
            return result;
        }
        //Userに権限がない場合、エラー出力
        else {
            logger.warn("getallTaskAPIを権限のないユーザが呼び出しました");
            throw new UnauthorizedError();
        }
    }

}


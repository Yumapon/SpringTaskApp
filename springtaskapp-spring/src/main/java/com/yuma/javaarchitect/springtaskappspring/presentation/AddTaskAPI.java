package com.yuma.javaarchitect.springtaskappspring.presentation;

import com.yuma.javaarchitect.springtaskappcore.usecase.AddTaskUsecase;
import com.yuma.javaarchitect.springtaskappspring.presentation.error.UnauthorizedError;
import com.yuma.javaarchitect.springtaskappspring.presentation.model.AddTaskReqDto;
import com.yuma.javaarchitect.springtaskappspring.presentation.model.AddTaskResDto;
import com.yuma.javaarchitect.springtaskappspring.service.CallApiService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;
import lombok.NonNull;

/**
 * PUT MethodのAPIを定義するクラス
 * 
 * 現状定義しているAPIは以下1つ
 * {@link AddTaskAPI#add(AddTaskReqDto)}
 */
@AllArgsConstructor
@RestController
@RequestMapping("yuma/task")
public class AddTaskAPI {

    //Task格納実行クラス
    @NonNull
    private final AddTaskUsecase usecase;

    //API呼び出しクラス
    @Autowired
    private final CallApiService service;

    //Loggerクラス
    private static final Logger logger = LoggerFactory.getLogger(AddTaskAPI.class);

    /**
     * 
     * @param reqDto
     * @return
     */
    @NonNull
    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    @CrossOrigin
    public AddTaskResDto add(@NonNull @RequestBody AddTaskReqDto reqDto, @RequestParam("email") String email) throws UnauthorizedError{

        //Log記録
        logger.info("start getTask api");

        //権限チェック
        if(service.checkRole(email, "write")){

            //Taskを格納
            String id = usecase.invoke(reqDto.getName(), 
                                        reqDto.getContent(), 
                                        reqDto.getDeadline(), 
                                        reqDto.getClient());

            //レスポンスを作成
            AddTaskResDto result = AddTaskResDto.builder().id(id).build();

            return result;
        }
        //Userに権限がない場合、エラー出力
        else {
            logger.warn("addTaskAPIを権限のないユーザが呼び出しました");
            throw new UnauthorizedError();
        }
    }
}

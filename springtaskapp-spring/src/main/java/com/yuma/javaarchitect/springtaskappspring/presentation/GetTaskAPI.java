package com.yuma.javaarchitect.springtaskappspring.presentation;

import java.util.List;
import java.util.Optional;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.yuma.javaarchitect.springtaskappcore.domain.entity.Task;
import com.yuma.javaarchitect.springtaskappcore.usecase.GetTaskUsecase;
import com.yuma.javaarchitect.springtaskappspring.dynamo.APIUser;
import com.yuma.javaarchitect.springtaskappspring.presentation.model.GetTaskAllResDto;
import com.yuma.javaarchitect.springtaskappspring.presentation.model.GetTaskByIdResDto;

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

@AllArgsConstructor
@RestController
@RequestMapping("yuma/task")
public class GetTaskAPI {

    @NonNull
    private final GetTaskUsecase usecase;

    //DynamoDBのMapperClass
    @Autowired
    private final DynamoDBMapper mapper;

    /**
     * TODO コメント
     * 
     * @param id
     * @param email
     * @return
     */
    @NonNull
    @GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @CrossOrigin
    public GetTaskByIdResDto getTaskById(@NonNull @PathVariable("id") String id, @RequestParam("email") String email) {

        System.out.println("email" + email);

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

    /**
     * TODO コメント
     * 
     * @param email
     * @return
     */
    @NonNull
    @GetMapping(path = "/getall", produces = MediaType.APPLICATION_JSON_VALUE)
    @CrossOrigin
    public GetTaskAllResDto getTaskAll(@RequestParam("email") String email){

        /*
        System.out.println("email:" + email);

        System.out.println("処理開始");
        
        APIUser example = new APIUser("example@gmail.com", "admin");
        repository.save(example);

        Optional<APIUser> result = repository.findById("example@gmail.com");
        if(result.isPresent())
            System.out.println(result.get());
        
        repository.delete(example);
        
        System.out.println("処理終了");

        List<Task> taskList = usecase.invoke();

        GetTaskAllResDto result2 = GetTaskAllResDto.builder().taskList(taskList).build();

        return result2;
        */

        //データ格納
        mapper.save(APIUser.builder().email(email).role("guest").build());

        //データ取り出し
        String id = email;
        APIUser user = mapper.load(APIUser.class, id);
        System.out.println(user);

        return null;
    }

}
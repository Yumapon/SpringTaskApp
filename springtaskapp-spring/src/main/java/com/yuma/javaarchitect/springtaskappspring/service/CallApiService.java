package com.yuma.javaarchitect.springtaskappspring.service;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.yuma.javaarchitect.springtaskappspring.dynamo.APIUser;
import com.yuma.javaarchitect.springtaskappspring.service.component.Opacheckres;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class CallApiService {

    //API呼び出し用クラス
    @Autowired
    private RestTemplate restTemplate;

    //API情報の定義クラス
    @Autowired
    private APIDestinations apiDestinations;

    //DynamoDBのMapperClass
    @Autowired
    private final DynamoDBMapper mapper;
    
    //Loggerクラス
    private static final Logger logger = LoggerFactory.getLogger(CallApiService.class);

    /**
     * id(email)のユーザにactionの動作を実行する権限があるかどうかをチェックし、booleanで返す。
     * 
     * idのユーザのRoleはDynamoDBに格納されているため、そちらから取得し、
     * 取得したRoleと、引数のactionを元にOpen Policy AgentへReqquestを飛ばしtrue or falseの結果を受け取る
     * 
     * OPAのURLは{@link com.yuma.javaarchitect.springtaskappspring.service.APIDestinations}を参照
     * 
     * @param id
     * @param action
     * @return
     */
    public boolean checkRole(String id, String action) {

        logger.info("start check policy");

        //DynamoDBからidを元にRoleを取得
        APIUser user = mapper.load(APIUser.class, id);
        String role = user.getRole();

        //OPAにAPIRequestを投げ、Policyチェック
        //Headerの設定
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        //URLの作成
        String url = "http://" + apiDestinations.getOpaapiurl() + "/v1/data/httpapi/authz/allow"; 

        //requestparamの設定
        String requestJson = "{\"input\": {\"user\": \"" + role + "\",\"action\": \"" + action + "\"}}";

        //Requestの実施
        HttpEntity<String> entity = new HttpEntity<String>(requestJson, headers);
        ResponseEntity<Opacheckres> response = restTemplate.exchange(url, HttpMethod.POST, entity, Opacheckres.class);

        //結果(String)をbooleanに変換
        boolean result = Boolean.valueOf(response.getBody().getResult());

        //log
        logger.info("opaのPolicyチェック結果は '" + result +"' でした");

        return result;
    }
    
}

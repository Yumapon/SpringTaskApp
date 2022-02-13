package com.yuma.javaarchitect.springtaskappspring.config;

import com.amazonaws.xray.AWSXRay;
import com.amazonaws.xray.entities.Segment;
import com.amazonaws.xray.entities.Subsegment;
import com.amazonaws.xray.entities.TraceHeader;
import com.yuma.javaarchitect.springtaskappcore.domain.entity.repository.TaskRepo;
import com.yuma.javaarchitect.springtaskappcore.usecase.GetTaskUsecase;
import com.yuma.javaarchitect.springtaskappspring.service.APIDestinations;

import org.springframework.web.reactive.function.client.ClientRequest;
import org.springframework.web.reactive.function.client.ExchangeFilterFunction;
import org.springframework.web.reactive.function.client.WebClient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
    public GetTaskUsecase getTaskUsecase(TaskRepo repo) {
        return new GetTaskUsecase(repo);
    }

    //API情報の定義クラス
    @Autowired
    private APIDestinations apiDestinations;

    @Bean
    //WebClientに共通して行うフィルタ処理を定義、実際の処理はexchangeFunction()にて定義
    public WebClient userWebClient(){
        return WebClient.builder()
                .baseUrl("")
                .filter(exchangeFilterFunction())
                .build();
    }

    //com.amazonaws.xray.AWSXRayから実行中のセグメントオブジェクトを取得します。
    private ExchangeFilterFunction exchangeFilterFunction(){
        return (clientRequest, nextFilter) -> {
            //セグメント情報を取得
            Segment segment = AWSXRay.getCurrentSegment();
            Subsegment subsegment = AWSXRay.getCurrentSubsegment();
            //Traceにヘッダーを追加
            TraceHeader traceHeader = new TraceHeader(segment.getTraceId(),
                    segment.isSampled() ? subsegment.getId() : null,
                    segment.isSampled() ? TraceHeader.SampleDecision.SAMPLED : TraceHeader.SampleDecision.NOT_SAMPLED);

            ClientRequest newClientRequest = ClientRequest.from(clientRequest)
                    .header(TraceHeader.HEADER_KEY, traceHeader.toString())
                    .build();
            return nextFilter.exchange(newClientRequest);
        };
    }
    
}

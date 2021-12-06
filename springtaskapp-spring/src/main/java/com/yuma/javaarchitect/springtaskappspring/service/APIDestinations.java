package com.yuma.javaarchitect.springtaskappspring.service;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import io.micrometer.core.lang.NonNull;
import lombok.Getter;
import lombok.Setter;

/**
 * API定義を設定ファイルから取得するクラス+API呼び出し用のクラスを用意
 * 定義自体はapplication.ymlファイルに記載
 */
@Configuration
@ConfigurationProperties(prefix = "api.destinations")
public class APIDestinations {

    @NonNull
    @Getter
    @Setter
    private String opaapiurl;//OPAのURL

    @Bean
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }

}

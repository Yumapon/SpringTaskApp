package com.yuma.javaarchitect.springtaskappspring.service.component;

import org.springframework.stereotype.Component;

import lombok.Getter;

/**
 * Open Policy AgentのResponseを受け取るクラス
 * {@link com.yuma.javaarchitect.springtaskappspring.service.CallApiService}で使用
 * 
 * OPAのPolicyを記載しているregoファイルは{@link https://github.com/Yumapon/TaskAppAPIPolicy}を参照
 */
@Component
@Getter
public class Opacheckres{

    private String result;
    
}

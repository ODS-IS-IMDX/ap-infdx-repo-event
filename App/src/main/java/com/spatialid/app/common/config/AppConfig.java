// © 2025 NTT DATA Japan Co., Ltd. & NTT InfraNet All Rights Reserved.

package com.spatialid.app.common.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * アプリケーション全般の設定を行うクラス．
 * 
 * @author ukai jun
 * @version 1.1 2024/11/18
 *
 */
@Configuration
public class AppConfig {

    /**
     * ModelMapperのBean登録をするメソッド.
     * 
     * @return ModelMapper
     */
    @Bean
    ModelMapper modelMapper() {

        return new ModelMapper();
    }
}
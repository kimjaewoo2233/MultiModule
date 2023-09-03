package org.delivery.api.config.swagger;


import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.v3.core.jackson.ModelResolver;
import org.delivery.api.config.objectmapper.ObjectMapperConfig;
import org.springframework.boot.Banner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
 * API요청에 따른 ObjectMapper 설정이 있는데 해당 설정이 Swagger에 반영도지 않아서 따로 설정클래스로 관리
 * */
@Configuration
public class SwaggerConfig {

    @Bean
    public ModelResolver modelResolver(ObjectMapper objectMapper){ //ObjectMapperConfig에 등록된 빈이 들어옴
        return new ModelResolver(objectMapper);
    }
}

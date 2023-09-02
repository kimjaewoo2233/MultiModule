package org.delivery.api.config.jpa;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EntityScan(basePackages = "org.delivery.db") //해당 모듈에 있는 엔티티 스캔해옴
@EnableJpaRepositories(basePackages = "org.delivery.db") // 해당 모듈에 있는 레포지토리 스캔해옴
public class JpaConfig {


}

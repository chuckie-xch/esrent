package com.bestcode.esrent.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * jpa配置
 *
 * @author xch
 * @create 2018-07-07 21:52
 **/
@Configuration
@EnableJpaRepositories(basePackages = "com.bestcode.esrent.repository")
@EnableTransactionManagement
public class JPAConfig {


}

package com.svn.test.spring.boot.eureka.demo.server.account;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * The accounts Spring configuration.
 */
@Configuration
@ComponentScan
public class AccountsConfiguration {

  @Bean
  RestTemplate restTemplate() {
    return new RestTemplate();
  }
}

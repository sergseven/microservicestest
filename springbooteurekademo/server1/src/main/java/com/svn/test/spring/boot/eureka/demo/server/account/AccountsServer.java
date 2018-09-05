package com.svn.test.spring.boot.eureka.demo.server.account;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableAutoConfiguration
@EnableDiscoveryClient
//@Import(AccountsConfiguration.class)
public class AccountsServer {

//  @Autowired
//  AccountRepository accountRepository;

  public static void main(String[] args) {
    // Will configure using accounts-server.yml
    System.setProperty("spring.config.name", "accounts-server");

    SpringApplication.run(AccountsServer.class, args);
  }
}

package com.svn.test.spring.mongo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;

@EnableReactiveMongoRepositories
@SpringBootApplication
public class SpringMongoApplication {

  public static void main(String[] args) {
    SpringApplication.run(SpringMongoApplication.class, args);
  }

}

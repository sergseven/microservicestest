package com.svn.test.spring.boot.eureka.demo.reactive.api.account.handler;

import com.svn.test.spring.boot.eureka.demo.reactive.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
public class AccountHandler {

  @Autowired
  private AccountService accountService;

  public Mono<ServerResponse> getAccountNumber(ServerRequest request) {
    return ServerResponse
        .ok()
        .contentType(MediaType.TEXT_PLAIN)
        .body(BodyInserters.fromPublisher(accountService.getAccountNumber(), String.class));
  }

}

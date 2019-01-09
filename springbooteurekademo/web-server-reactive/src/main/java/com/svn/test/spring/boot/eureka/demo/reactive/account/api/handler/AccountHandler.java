package com.svn.test.spring.boot.eureka.demo.reactive.account.api.handler;

import com.svn.test.spring.boot.eureka.demo.reactive.account.api.dto.AccountDto;
import com.svn.test.spring.boot.eureka.demo.reactive.account.api.mapper.AccountMapper;
import com.svn.test.spring.boot.eureka.demo.reactive.account.service.AccountService;
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

  public Mono<ServerResponse> getAccount(ServerRequest request) {
    return ServerResponse
        .ok()
        .contentType(MediaType.APPLICATION_JSON)
        .body(BodyInserters.fromPublisher(
            accountService
                .getAccountNumber()
                .map(AccountMapper::from),
            AccountDto.class));
  }

}

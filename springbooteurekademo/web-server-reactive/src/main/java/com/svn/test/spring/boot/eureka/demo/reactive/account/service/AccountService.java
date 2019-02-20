package com.svn.test.spring.boot.eureka.demo.reactive.account.service;

import java.time.Duration;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@Slf4j
public class AccountService {

  public Mono<String> getAccountNumber() {
    Mono<String> service0Response = Mono.just("serv0").delayElement(Duration.ofSeconds(5));
    Mono<String> service1Response = Mono.just("serv1");
    Mono<String> service2Response = Mono.just("serv2");

    Flux<String> res = Flux.combineLatest(
        service1Response, service2Response,
        (s, s2) -> String.format("%s %s", s, s2));

    Mono<Long> numberOfResponses = Flux.zip(
        service1Response,
        service2Response,
        service1Response,
        service1Response,
        service1Response).collect(Collectors.counting());

    //    return numberOfResponses.map(String::valueOf);
    return Mono.just("12345");
  }

  public Mono<String> getAccountOwnerCode() {
    return Mono.just("OWNER_CODE_1");
  }

}

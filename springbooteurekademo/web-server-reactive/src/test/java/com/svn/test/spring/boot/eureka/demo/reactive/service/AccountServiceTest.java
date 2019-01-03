package com.svn.test.spring.boot.eureka.demo.reactive.service;

import static java.time.Duration.ofSeconds;
import static java.util.stream.Collectors.toList;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.junit.Ignore;
import org.junit.Test;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;
import reactor.util.function.Tuple2;

/**
 * TODO: javadoc
 */
@Slf4j
public class AccountServiceTest {

  private AccountService uut = new AccountService();

  @Test
  @Ignore
  public void name() {
    //    String result = Mono.from(uut.getAccountNumber()).block();
    String result = "";

    Mono<String> mono1 = Mono.just("1").delayElement(ofSeconds(2));
    Mono<String> mono2 = Mono.just("2");

    //    Mono<String> mono = Mono.first(mono1, mono2);

    Tuple2<String, String> tuple2 = Mono.zip(mono1, mono2).block();

    log.info("res {}", tuple2);
  }

  @Test
  @Ignore
  public void flux() {
    //    String result = Mono.from(uut.getAccountNumber()).block();
    String result = "";

    Flux<String> flux1 = Flux.concat(Mono.just("4"), Mono.just("5").delayElement(ofSeconds(2)), Mono.just("6"));
    Flux<String> flux2 = Flux.just("1", "2", "3");

    //    Flux<String> resFlux = Flux.combineLatest(flux1, flux2, (s1, s2) -> s1 + s2);
    Flux<String> resMergedFlux = Flux.merge(flux1, flux2);

    Mono<List<String>> collected = resMergedFlux.collect(toList());

    log.info("res {}", collected.block());
  }

  @Test
  public void name2() {
    Mono<String> mono0 = Mono
        .fromRunnable(() -> foo())
        .then(Mono.just("123"));

    Mono<String> objectMono = Mono.fromSupplier(() -> Void.TYPE)
        .doOnNext(v -> foo())
        .flatMap(p -> Mono.just("123"));
    Mono.error(new IllegalArgumentException()).subscribe(res -> log.info("fail"), e -> {});

    log.info("mono0 {}", mono0.block());
    log.info("mono1 {}", objectMono.block());
    //    log.info("mono3 {}", mono3.block());
    Flux<Long> longFlux = Flux.interval(Duration.ofMillis(100L)).take(10);
    Mono<List<Long>> collect = longFlux.collect(toList());
    List<Long> block = collect.block();
    log.info("block {}", block);

    StepVerifier
        .create(collect)
        .expectNext(Arrays.asList(0L, 1L, 2L, 3L, 4L, 5L, 6L, 7L, 8L, 9L))
        //        .expectError(RuntimeException.class)
        .verifyComplete();
    //    StepVerifier.create(collect).expectNextCount(10).verifyComplete().toMillis();

  }

  private void foo() {}
}
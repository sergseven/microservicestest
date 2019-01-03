package com.svn.test.spring.boot.eureka.demo.reactive.api.account.handler;

import java.time.Duration;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.reactive.socket.WebSocketHandler;
import org.springframework.web.reactive.socket.WebSocketMessage;
import org.springframework.web.reactive.socket.WebSocketSession;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * TODO: javadoc
 */
@Slf4j
public class AccountWebSocketHandler implements WebSocketHandler {

  @Override
  public Mono<Void> handle(WebSocketSession session) {

    //    Flux<WebSocketMessage> output = session.receive()
    //        .doOnNext(webSocketMessage -> log.info("doOnNext"))
    //        //        .concatMap(()->{
    //        //          return null;
    //        //        })
    //        .map(value -> session.textMessage("Echo " + value.getPayloadAsText()))
    //        .doOnComplete(() -> log.info("doOnComplete"));
    //    //    session.send()
    //    return session.send(output);
    return session.send(
        Flux.interval(Duration.ofSeconds(1L))
            .map(String::valueOf)
            .map(session::textMessage))
        .and(session.receive()
            .map(WebSocketMessage::getPayloadAsText)
            .log());

  }
}


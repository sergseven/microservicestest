package com.svn.test.spring.mongo;

import com.mongodb.client.model.Filters;
import com.mongodb.reactivestreams.client.FindPublisher;
import lombok.extern.slf4j.Slf4j;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.ReactiveMongoOperations;
import org.springframework.web.reactive.socket.WebSocketHandler;
import org.springframework.web.reactive.socket.WebSocketMessage;
import org.springframework.web.reactive.socket.WebSocketSession;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
public class TestDocumentWebSocketHandler implements WebSocketHandler {

  @Autowired
  private ReactiveMongoOperations mongoOperations;

  @Override
  public Mono<Void> handle(WebSocketSession session) {
    Flux.from(fetchFoo())
        .map(Document::toJson)
        .doOnNext(json -> log.info("json next = {}", json))
        .subscribe();

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
        //        Mono.justOrEmpty((String) null)
        //            .map(session::textMessage))
        //        Flux.from(fetchFoo())
        //            .map(Document::toJson)
        //            .doOnNext(json -> log.info("json next = {}", json))
        //            .map(session::textMessage))
        //        .and(
        Mono.just("foo mono 2")
            .map(session::textMessage))
        .and(session.receive()
            .map(WebSocketMessage::getPayloadAsText)
            .log());

    //    return session.send(
    //        Flux.interval(Duration.ofSeconds(1L))
    //            .map(String::valueOf)
    //            .map(session::textMessage))
    //        .and(session.receive()
    //            .map(WebSocketMessage::getPayloadAsText)
    //            .log());

  }

  private FindPublisher<Document> fetchFoo() {
    return mongoOperations
        .getCollection("clients")
        .find(Filters.eq("foo_field", "123"));
  }

}

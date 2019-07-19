package com.svn.test.spring.mongo;

import static org.springframework.http.MediaType.TEXT_HTML;
import static org.springframework.web.reactive.function.BodyInserters.fromPublisher;
import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RequestPredicates.accept;

import com.mongodb.client.model.Filters;
import com.mongodb.reactivestreams.client.FindPublisher;
import java.util.HashMap;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.bson.Document;
import org.reactivestreams.Publisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.ReactiveMongoOperations;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.HandlerMapping;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;
import org.springframework.web.reactive.handler.SimpleUrlHandlerMapping;
import org.springframework.web.reactive.socket.WebSocketHandler;
import org.springframework.web.reactive.socket.server.support.WebSocketHandlerAdapter;
import reactor.core.publisher.Flux;

@Slf4j
@Configuration
public class SpringMongoRouter {

  @Autowired
  private SpringMongoRepository mongoRepository;

  @Autowired
  private ReactiveMongoOperations mongoOperations;

  @Autowired
  private TestDocumentWebSocketViewHandler testDocumentWebSocketViewHandler;


  @Bean
  RouterFunction<ServerResponse> routeMongoApi() {
    return RouterFunctions
        .route(
            GET("/mongo"),
            sr -> ServerResponse
                .ok()
                .contentType(MediaType.TEXT_PLAIN)
                .body(fromPublisher(mongoRepository.findAll(), TestDocument.class)))
        .andRoute(
            GET("/mongo-read"),
            sr -> ServerResponse
                .ok()
                .contentType(MediaType.TEXT_PLAIN)
                .body(fromPublisher(getExCollection(), String.class)))
        .andRoute(
            GET("/mongoClient.html").and(accept(TEXT_HTML)),
            testDocumentWebSocketViewHandler::getClientPage);
  }

  private Publisher<String> getExCollection() {
    Flux.from(fetchFoo())
        .map(Document::toJson)
        .doOnNext(json -> log.info("2 json next = {}", json))
        .subscribe();

    return Flux.from(fetchFoo())
        .map(Document::toJson)
        //        .doOnNext(json -> log.info("json next = {}", json))
        ;
    //    return Flux.from(mongoOperations.getCollection("clients").find()).map(doc -> doc.toJson());
  }

  private FindPublisher<Document> fetchFoo() {
    return mongoOperations
        .getCollection("clients")
        .find(Filters.eq("foo_field", "123"));
  }

  @Bean
  public HandlerMapping handlerMapping() {
    Map<String, WebSocketHandler> map = new HashMap<>();
    map.put("/mongo-ws", new TestDocumentWebSocketHandler());

    SimpleUrlHandlerMapping mapping = new SimpleUrlHandlerMapping();
    mapping.setUrlMap(map);
    mapping.setOrder(-1); // before annotated controllers
    return mapping;
  }

  @Bean
  public WebSocketHandlerAdapter handlerAdapter() {
    return new WebSocketHandlerAdapter();
  }
}

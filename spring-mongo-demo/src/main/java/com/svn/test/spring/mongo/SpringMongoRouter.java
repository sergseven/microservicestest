package com.svn.test.spring.mongo;

import static org.springframework.web.reactive.function.BodyInserters.fromPublisher;
import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

import org.reactivestreams.Publisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.ReactiveMongoOperations;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;

@Configuration
public class SpringMongoRouter {

  @Autowired
  private SpringMongoRepository mongoRepository;

  @Autowired
  private ReactiveMongoOperations mongoOperations;


  @Bean
  RouterFunction<ServerResponse> routeMongoApi() {
    return route(
        GET("/mongo"),
        sr -> ServerResponse
            .ok()
            .contentType(MediaType.TEXT_PLAIN)
            .body(fromPublisher(mongoRepository.findAll(), TestDocument.class))).and(route(
        GET("/mongo-read"),
        sr -> ServerResponse
            .ok()
            .contentType(MediaType.TEXT_PLAIN)
            .body(fromPublisher(getExCollection(), String.class))));
  }

  private Publisher<String> getExCollection() {
    return Flux.from(mongoOperations.getCollection("clients").find()).map(doc -> doc.toJson());
  }
}

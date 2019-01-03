package com.svn.test.spring.boot.eureka.demo.reactive;

import com.svn.test.spring.boot.eureka.demo.reactive.api.account.handler.AccountWebSocketHandler;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.HandlerMapping;
import org.springframework.web.reactive.handler.SimpleUrlHandlerMapping;
import org.springframework.web.reactive.socket.WebSocketHandler;
import org.springframework.web.reactive.socket.server.support.WebSocketHandlerAdapter;
import reactor.core.publisher.Flux;

@SpringBootApplication
@Slf4j
public class ReactiveWebServer {

  public static void main(String[] args) {
    Flux
        .interval(Duration.ofSeconds(1))
        .doOnEach(signal -> log.info("{}", signal.get()))
        .blockFirst();

    SpringApplication.run(ReactiveWebServer.class, args);
  }

  @Configuration
  static class WebConfig {

    @Bean
    public HandlerMapping handlerMapping() {
      Map<String, WebSocketHandler> map = new HashMap<>();
      map.put("/account-ws", new AccountWebSocketHandler());

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
}

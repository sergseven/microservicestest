package com.svn.test.spring.boot.eureka.demo.reactive.account.api;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.http.MediaType.TEXT_HTML;
import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RequestPredicates.accept;

import com.svn.test.spring.boot.eureka.demo.reactive.account.api.handler.AccountHandler;
import com.svn.test.spring.boot.eureka.demo.reactive.account.api.handler.AccountWebSocketHandler;
import com.svn.test.spring.boot.eureka.demo.reactive.account.api.handler.AccountWebSocketViewHandler;
import java.util.HashMap;
import java.util.Map;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.HandlerMapping;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;
import org.springframework.web.reactive.handler.SimpleUrlHandlerMapping;
import org.springframework.web.reactive.socket.WebSocketHandler;
import org.springframework.web.reactive.socket.server.support.WebSocketHandlerAdapter;

@Configuration
public class AccountRouter {

  @Bean
  RouterFunction<ServerResponse> route(
      AccountHandler accountHandler,
      AccountWebSocketViewHandler accountWebSocketViewHandler) {
    return RouterFunctions
        .route(
            GET("/account").and(accept(APPLICATION_JSON)), accountHandler::getAccount)
        .andRoute(
            GET("/accountClient.html").and(accept(TEXT_HTML)), accountWebSocketViewHandler::getClientPage);
  }

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

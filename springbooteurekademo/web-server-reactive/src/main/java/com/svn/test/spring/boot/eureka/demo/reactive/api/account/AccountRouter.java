package com.svn.test.spring.boot.eureka.demo.reactive.api.account;

import com.svn.test.spring.boot.eureka.demo.reactive.api.account.handler.AccountHandler;
import com.svn.test.spring.boot.eureka.demo.reactive.api.account.handler.AccountWebSocketViewHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

@Configuration
public class AccountRouter {

  @Bean
  RouterFunction<ServerResponse> route(
      AccountHandler accountHandler,
      AccountWebSocketViewHandler accountWebSocketViewHandler) {
    return RouterFunctions
        .route(
            RequestPredicates.GET("/account").and(RequestPredicates.accept(MediaType.TEXT_PLAIN)),
            accountHandler::getAccountNumber)
        .andRoute(
            RequestPredicates.GET("/accountClient.html").and(RequestPredicates.accept(MediaType.TEXT_HTML)),
            accountWebSocketViewHandler::getClientPage);
  }

}

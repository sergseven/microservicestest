package com.svn.test.spring.boot.eureka.demo.reactive.account.api.handler;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
public class AccountWebSocketViewHandler {

  @Value("classpath:/static/accountClient.html")
  private Resource accountClientHtml;

  public Mono<ServerResponse> getClientPage(@SuppressWarnings("unused") ServerRequest request) {
    return ServerResponse
        .ok()
        .contentType(MediaType.TEXT_HTML)
        .syncBody(accountClientHtml);
  }
}

package com.svn.test.spring.mongo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
public class TestDocumentWebSocketViewHandler {

  @Value("classpath:/static/mongoClient.html")
  private Resource mongoClientHtml;

  public Mono<ServerResponse> getClientPage(@SuppressWarnings("unused") ServerRequest request) {
    return ServerResponse
        .ok()
        .contentType(MediaType.TEXT_HTML)
        .syncBody(mongoClientHtml);
  }
}

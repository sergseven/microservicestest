package com.svn.test.spring.boot.eureka.demo.server.account.entity;


import static org.assertj.core.api.Assertions.assertThat;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import org.junit.jupiter.api.Test;

/**
 * TODO: javadoc
 */
class AccountTest {

  @Test
  void shouldParseJsonByJackson() throws IOException {
    ObjectMapper objectMapper = new ObjectMapper();

    Account account = objectMapper.readValue("{\"number\": \"foo\", \"bank\": \"foo bank\"}", Account.class);

    assertThat(account.getNumber()).isEqualTo("foo");
  }

  @Test
  void shouldSerializeToJsonByJackson() throws IOException {
    ObjectMapper objectMapper = new ObjectMapper();
    Account account = Account.builder().number("foo number").bank("bank").build();

    String json = objectMapper.writeValueAsString(account);

    assertThat(json).isEqualTo("{\"number\":\"foo number\",\"bank\":\"bank\"}");
  }

}
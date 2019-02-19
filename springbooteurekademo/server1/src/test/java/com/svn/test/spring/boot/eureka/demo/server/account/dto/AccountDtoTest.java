package com.svn.test.spring.boot.eureka.demo.server.account.dto;

import static org.assertj.core.api.Assertions.assertThat;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import org.junit.jupiter.api.Test;

/**
 * TODO: javadoc
 */
class AccountDtoTest {

  @Test
  void shouldParseDefaultJsonByObjectMapper() throws IOException {
    ObjectMapper objectMapper = new ObjectMapper();

    AccountDto accountDto = objectMapper.readValue("{\"accountNumber\": \"foo\"}", AccountDto.class);

    assertThat(accountDto.getAccountNumber()).isEqualTo("foo");
  }

}
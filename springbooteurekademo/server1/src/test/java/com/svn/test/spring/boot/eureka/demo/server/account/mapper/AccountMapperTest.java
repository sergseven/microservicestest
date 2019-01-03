package com.svn.test.spring.boot.eureka.demo.server.account.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import com.svn.test.spring.boot.eureka.demo.server.account.dto.AccountDto;
import com.svn.test.spring.boot.eureka.demo.server.account.entity.Account;
import org.junit.jupiter.api.Test;

class AccountMapperTest {

  AccountMapper uut = AccountMapper.INSTANCE;

  @Test
  void shouldConvertAllProperties() {
    AccountDto dto = uut.from(Account.builder().number("num1").bank("pkb").build());

    assertThat(dto.getNumber()).isEqualTo("num2");
  }
}
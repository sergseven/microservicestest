package com.svn.test.spring.boot.eureka.demo.server.account.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import com.svn.test.spring.boot.eureka.demo.server.account.dto.AccountDto;
import com.svn.test.spring.boot.eureka.demo.server.account.entity.Account;
import org.junit.jupiter.api.Test;

class AccountMapperTest {

  //  AccountMapper uut = AccountMapper.INSTANCE;

  @Test
  void shouldConvertAllProperties() {
    ManualAccountMapper uut = new ManualAccountMapper();

    Account account = uut.from(AccountDto
        .builder()
        .accountNumber("num1")
        .bankCode("pkb")
        .owner("mr owner")
        .ownerCode("own1")
        .build());

    assertThat(account.getNumber()).isEqualTo("num1");
    assertThat(account.getBank()).isEqualTo("pkb");
    assertThat(account.getOwner()).isEqualTo("mr owner");
  }
}
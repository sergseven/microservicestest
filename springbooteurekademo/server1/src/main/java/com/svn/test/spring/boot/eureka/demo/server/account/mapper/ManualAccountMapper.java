package com.svn.test.spring.boot.eureka.demo.server.account.mapper;

import com.svn.test.spring.boot.eureka.demo.server.account.dto.AccountDto;
import com.svn.test.spring.boot.eureka.demo.server.account.entity.Account;
import org.springframework.stereotype.Component;

/**
 * TODO: javadoc
 */
@Component
public class ManualAccountMapper implements AccountMapper {

  @Override
  public Account from(AccountDto dto) {
    return Account.builder().number(dto.getAccountNumber()).bank(dto.getBankCode()).owner(dto.getOwnerCode()).build();
  }
}

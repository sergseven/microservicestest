package com.svn.test.spring.boot.eureka.demo.reactive.account.api.mapper;

import com.svn.test.spring.boot.eureka.demo.reactive.account.api.dto.AccountDto;

/**
 * TODO: javadoc
 */
public class AccountMapper {

  public static AccountDto from(String accountNumber, String owner, String ownerCode) {
    return new AccountDto(accountNumber, owner, ownerCode);
  }

}

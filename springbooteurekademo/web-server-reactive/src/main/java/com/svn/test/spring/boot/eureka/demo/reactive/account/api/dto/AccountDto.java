package com.svn.test.spring.boot.eureka.demo.reactive.account.api.dto;

import lombok.Value;

@Value
public class AccountDto {

  String accountNumber;
  String owner;
  String ownerCode;
}

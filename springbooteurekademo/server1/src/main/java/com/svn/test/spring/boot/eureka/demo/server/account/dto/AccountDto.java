package com.svn.test.spring.boot.eureka.demo.server.account.dto;

import lombok.Builder;
import lombok.Value;

@Builder
@Value
public class AccountDto {

  String number;
  String bankCode;
}

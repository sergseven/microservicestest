package com.svn.test.spring.boot.eureka.demo.server.account.entity;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class Account {
  private final String number;
}

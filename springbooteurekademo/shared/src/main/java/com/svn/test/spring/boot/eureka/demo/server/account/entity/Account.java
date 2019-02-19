package com.svn.test.spring.boot.eureka.demo.server.account.entity;

import com.fasterxml.jackson.annotation.JsonRootName;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
@JsonRootName("Account")
@JsonDeserialize(builder = Account.AccountBuilder.class)
public class Account {

  private final String number;
  private final String bank;

  @JsonPOJOBuilder(withPrefix = "")
  public static class AccountBuilder {

  }
}

package com.svn.test.spring.boot.eureka.demo.server.account.dto;

import com.fasterxml.jackson.annotation.JsonRootName;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import lombok.Builder;
import lombok.Value;

@Builder
@Value
@JsonRootName("Account")
@JsonDeserialize(builder = AccountDto.AccountDtoBuilder.class)
public class AccountDto {

  String accountNumber;
  String bankCode;
  String owner;
  String ownerCode;

  @JsonPOJOBuilder(withPrefix = "")
  public static class AccountDtoBuilder {

  }
}

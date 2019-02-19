package com.svn.test.spring.boot.eureka.demo.server.account.mapper;

import com.svn.test.spring.boot.eureka.demo.server.account.dto.AccountDto;
import com.svn.test.spring.boot.eureka.demo.server.account.entity.Account;

//@Mapper
public interface AccountMapper {

  //  AccountMapper INSTANCE = Mappers.getMapper(AccountMapper.class);

  //  @Mapping(source = "bank", target = "bankCode")
  Account from(AccountDto dto);
}

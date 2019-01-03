package com.svn.test.spring.boot.eureka.demo.server.account.mapper;

import com.svn.test.spring.boot.eureka.demo.server.account.dto.AccountDto;
import com.svn.test.spring.boot.eureka.demo.server.account.entity.Account;
import org.mapstruct.factory.Mappers;

//@Mapper
public interface AccountMapper {

  AccountMapper INSTANCE = Mappers.getMapper(AccountMapper.class);

  //  @Mapping(source = "bank", target = "bankCode")
  AccountDto from(Account account);
}

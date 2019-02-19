package com.svn.test.spring.boot.eureka.demo.server.account;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.svn.test.spring.boot.eureka.demo.server.account.dto.AccountDto;
import com.svn.test.spring.boot.eureka.demo.server.account.entity.Account;
import com.svn.test.spring.boot.eureka.demo.server.account.mapper.AccountMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@Slf4j
@RestController
public class AccountsController {

  @Autowired
  RestTemplate restTemplate;

  @Autowired
  AccountMapper accountMapper;

  /**
   * Fetch an account with the specified account number.
   */
  @RequestMapping("/accounts/{accountNumber}")
  public Account byNumber(@PathVariable("accountNumber") String accountNumber) {
    log.info("accounts-service byNumber() invoked: " + accountNumber);

    ObjectMapper mapper = new ObjectMapper();

    String accountDtoBody = restTemplate.getForObject("http://localhost:8080/account", String.class);
    log.info("got response from remote accountDtoBody: " + accountDtoBody);

    AccountDto accountDto = restTemplate.getForObject("http://localhost:8080/account", AccountDto.class);
    log.info("got response from remote AccountDto: " + accountDto);
    Account account = accountMapper.from(accountDto);
    log.info("accounts-service byNumber() found: " + account);
    return account;
  }

}
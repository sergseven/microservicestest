package com.svn.test.spring.boot.eureka.demo.server.account;

import com.svn.test.spring.boot.eureka.demo.server.account.entity.Account;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.logging.Logger;

@RestController
public class AccountsController {

  private final Logger logger = Logger.getLogger(AccountsController.class
          .getName());

  /**
   * Fetch an account with the specified account number.
   */
  @RequestMapping("/accounts/{accountNumber}")
  public Account byNumber(@PathVariable("accountNumber") String accountNumber) {
    logger.info("accounts-service byNumber() invoked: " + accountNumber);
    Account account = Account.builder().number(accountNumber).build();
    logger.info("accounts-service byNumber() found: " + account);
    return account;
  }

}
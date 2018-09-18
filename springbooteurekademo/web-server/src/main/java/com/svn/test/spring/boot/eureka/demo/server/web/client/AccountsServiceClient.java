package com.svn.test.spring.boot.eureka.demo.server.web.client;

import com.svn.test.spring.boot.eureka.demo.server.account.entity.Account;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 * Hide the access to the microservice inside this local service.
 *
 * @author Paul Chapman
 */
@Service
@Slf4j
public class AccountsServiceClient {

  @Autowired
//  @LoadBalanced
  protected RestTemplate restTemplate;

  protected String serviceUrl;

  public AccountsServiceClient(String serviceUrl) {
    this.serviceUrl = serviceUrl.startsWith("http") ? serviceUrl
        : "http://" + serviceUrl;
  }

  /**
   * The RestTemplate works because it uses a custom request-factory that uses
   * Ribbon to look-up the service to use. This method simply exists to show
   * this.
   */
  @PostConstruct
  private void demoOnly() {
    // Can't do this in the constructor because the RestTemplate injection
    // happens afterwards.
    log.warn("The RestTemplate request factory is "
        + restTemplate.getRequestFactory().getClass());
  }

  public Optional<Account> findByNumber(String accountNumber) {
    log.info("findByNumber() invoked: for " + accountNumber);
    return Optional.ofNullable(restTemplate.getForObject(serviceUrl + "/accounts/{number}",
        Account.class, accountNumber));
  }

//  public List<Account> byOwnerContains(String name) {
//    log.info("byOwnerContains() invoked:  for " + name);
//    Account[] accounts = null;
//
//    try {
//      accounts = restTemplate.getForObject(serviceUrl
//          + "/accounts/owner/{name}", Account[].class, name);
//    } catch (HttpClientErrorException e) { // 404
//      // Nothing found
//    }
//
//    if (accounts == null || accounts.length == 0)
//      return null;
//    else
//      return Arrays.asList(accounts);
//  }

//  public Account getByNumber(String accountNumber) {
//    Account account = restTemplate.getForObject(serviceUrl
//        + "/accounts/{number}", Account.class, accountNumber);
//
//    if (account == null)
//      throw new RuntimeException(accountNumber);
//    else
//      return account;
//  }
}
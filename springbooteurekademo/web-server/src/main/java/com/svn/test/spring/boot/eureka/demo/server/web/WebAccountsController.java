package com.svn.test.spring.boot.eureka.demo.server.web;

import com.svn.test.spring.boot.eureka.demo.server.account.entity.Account;
import com.svn.test.spring.boot.eureka.demo.server.web.client.AccountsServiceClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Client controller, fetches Account info from the microservice via
 * {@link AccountsServiceClient}.
 *
 * @author Paul Chapman
 */
@RestController
@Slf4j
public class WebAccountsController {

  @Autowired
  protected AccountsServiceClient accountsClient;

//  public WebAccountsController(AccountsServiceClient accountsClient) {
//    this.accountsClient = accountsClient;
//  }

//  @InitBinder
//  public void initBinder(WebDataBinder binder) {
//    binder.setAllowedFields("accountNumber", "searchText");
//  }

//  @RequestMapping("/accounts")
//  public String goHome() {
//    return "index";
//  }

//  @RequestMapping("/accounts/{accountNumber}")
//  public String byNumber(Model model,
//                         @PathVariable("accountNumber") String accountNumber) {
//
//    log.info("web-service byNumber() invoked: " + accountNumber);
//
//    Account account = accountsService.findByNumber(accountNumber);
//    logger.info("web-service byNumber() found: " + account);
//    model.addAttribute("account", account);
//    return "account";
//  }

  /**
   * Fetch an account with the specified account number.
   */
  @RequestMapping("/accounts/{accountNumber}")
  public Account byNumber(@PathVariable("accountNumber") String accountNumber) {
    log.info("accounts-service byNumber() invoked: " + accountNumber);

    Account account = accountsClient.findByNumber(accountNumber).orElse(Account.builder().number("not found").build());

    log.info("accounts-service byNumber() found: " + account);
    return account;
  }

//  @RequestMapping("/accounts/owner/{text}")
//  public String ownerSearch(Model model, @PathVariable("text") String name) {
//    logger.info("web-service byOwner() invoked: " + name);
//
//    List<Account> accounts = accountsService.byOwnerContains(name);
//    logger.info("web-service byOwner() found: " + accounts);
//    model.addAttribute("search", name);
//    if (accounts != null)
//      model.addAttribute("accounts", accounts);
//    return "accounts";
//  }
//
//  @RequestMapping(value = "/accounts/search", method = RequestMethod.GET)
//  public String searchForm(Model model) {
//    model.addAttribute("searchCriteria", new SearchCriteria());
//    return "accountSearch";
//  }
//
//  @RequestMapping(value = "/accounts/dosearch")
//  public String doSearch(Model model, SearchCriteria criteria,
//                         BindingResult result) {
//    logger.info("web-service search() invoked: " + criteria);
//
//    criteria.validate(result);
//
//    if (result.hasErrors())
//      return "accountSearch";
//
//    String accountNumber = criteria.getAccountNumber();
//    if (StringUtils.hasText(accountNumber)) {
//      return byNumber(model, accountNumber);
//    } else {
//      String searchText = criteria.getSearchText();
//      return ownerSearch(model, searchText);
//    }
//  }
}
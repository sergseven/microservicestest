package com.svn.test.spring.boot.eureka.demo.server.account;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {

  @RequestMapping("/")
  public String home() {
    return "index";
  }

}
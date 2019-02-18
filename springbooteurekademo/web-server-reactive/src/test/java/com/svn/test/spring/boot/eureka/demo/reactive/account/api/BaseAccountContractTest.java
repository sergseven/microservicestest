package com.svn.test.spring.boot.eureka.demo.reactive.account.api;

import com.svn.test.spring.boot.eureka.demo.reactive.ReactiveWebServer;
import io.restassured.module.webtestclient.RestAssuredWebTestClient;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;

/**
 * TODO: javadoc
 */
@RunWith(SpringRunner.class)
@SpringBootTest(
    classes = {ReactiveWebServer.class},
    webEnvironment = WebEnvironment.RANDOM_PORT)
@Slf4j
public abstract class BaseAccountContractTest {

  @Autowired
  private WebTestClient webTestClient;

  @Before
  public void setup() {
    log.info("webtestclient = " + webTestClient);
    RestAssuredWebTestClient.webTestClient(webTestClient);
  }

}

package com.svn.test.spring.boot.eureka.demo.server.account;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.contract.stubrunner.spring.AutoConfigureStubRunner;
import org.springframework.cloud.contract.stubrunner.spring.StubRunnerProperties;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = AccountsServer.class)
@AutoConfigureMockMvc
@AutoConfigureStubRunner(ids = {"com.svn.test:web-server-reactive:0.0.1-SNAPSHOT:stubs:8080"},
    stubsMode = StubRunnerProperties.StubsMode.LOCAL)
class AccountsControllerTest {

  @Autowired
  MockMvc mockMvc;

  @Test
  void shouldMakeACallToRemoteServer() throws Exception {
    mockMvc.perform(
        get("/accounts/{accountNumber}", "123")
            .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(content().json("{'number': '12345'}"))
        .andExpect(content().json("{'owner': 'OWNER_CODE_1'}"));
  }
}
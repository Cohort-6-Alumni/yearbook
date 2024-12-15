package com.obsidi.yearbook;

import org.h2.tools.Server;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

@SpringBootTest
@ActiveProfiles("test")
class YearbookApplicationTests {

  @MockitoBean Server server;

  @DisplayName("Demo Test")
  @Test
  void contextLoads() {}
}

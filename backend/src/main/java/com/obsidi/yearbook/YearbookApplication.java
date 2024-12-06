package com.obsidi.yearbook;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@EnableAsync
@SpringBootApplication
public class YearbookApplication {

  public static void main(String[] args) {
    SpringApplication.run(YearbookApplication.class, args);
  }
}

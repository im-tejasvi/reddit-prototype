package com.springredditprototype;

import com.springredditprototype.config.SwaggerConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
@Import(SwaggerConfiguration.class)
public class SpringRedditPrototypeApplication {

  public static void main(String[] args) {
    SpringApplication.run(
      com.springredditprototype.SpringRedditPrototypeApplication.class,
      args
    );
  }
}

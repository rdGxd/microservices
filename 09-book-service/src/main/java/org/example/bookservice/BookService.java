package org.example.bookservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@EnableFeignClients
@PropertySource(value = "classpath:application.yml", encoding = "UTF-8")
public class BookService {

  public static void main(String[] args) {
    SpringApplication.run(BookService.class, args);
  }

}

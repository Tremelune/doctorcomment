package com.quieteyes.doctorcomment;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
//@ComponentScan("com.quieteyes.doctorcomment") // This...doesn't work, so this class needs to be in the root package.
public class Application {
  public static void main(String[] args) {
    SpringApplication.run(Application.class, args);
  }
}

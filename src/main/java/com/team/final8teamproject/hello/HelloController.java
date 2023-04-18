package com.team.final8teamproject.hello;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * test 용 api
 */

@RestController
public class HelloController {
  @GetMapping("/hello")
  public String getHello( ){
    return  "Hello?? success!";
  }

}

package Soma.CLOVI.api.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class rootController {
  @GetMapping("/")
  public String running(){
    return "server is running !!!";
  }
}

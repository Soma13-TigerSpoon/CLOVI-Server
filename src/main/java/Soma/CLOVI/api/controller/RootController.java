package Soma.CLOVI.api.controller;

import static Soma.CLOVI.common.Common.getClientIP;

import javax.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class RootController {
  @GetMapping("/")
  public String running() {
    return "Server is running!!";
  }

  @GetMapping("/ip")
  public String myIp(HttpServletRequest request){
    return getClientIP(request);
  }
}

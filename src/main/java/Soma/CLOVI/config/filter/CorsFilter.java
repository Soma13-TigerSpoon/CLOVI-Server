package Soma.CLOVI.config.filter;

import java.util.List;
import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
public class CorsFilter implements Filter {
  @Value("${allow-cors.list}")
  private List<String> allowCorsUrl;

  @Override
  public void init(FilterConfig filterConfig) throws ServletException {
    Filter.super.init(filterConfig);
  }

  @Override
  public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
      throws IOException, ServletException {
    HttpServletRequest req = (HttpServletRequest) request;
    HttpServletResponse res = (HttpServletResponse) response;
    String originUrl = req.getHeader("origin");
    System.out.println(req.getRemoteUser());
    System.out.println(req.getRequestURL());
    System.out.println(req.getRequestURI());
    for(String url: allowCorsUrl){
      if(originUrl.startsWith(url)){ // Origin url이 내가 허용하고자 하는 url들과 동일하다면 Header를 변경해 줍니다.
        res.setHeader("Access-Control-Allow-Origin", originUrl);
        break;
      }
    }
    res.setHeader("Access-Control-Allow-Credentials", "true");
    res.setHeader("Access-Control-Allow-Methods","*");
    res.setHeader("Access-Control-Max-Age", "3600");
    res.setHeader("Access-Control-Allow-Headers",
        "Origin, X-Requested-With, Content-Type, Accept, Authorization");

    if("OPTIONS".equalsIgnoreCase(req.getMethod())) {
      res.setStatus(HttpServletResponse.SC_OK);
    }else {
      chain.doFilter(request, response);
    }

  }

  @Override
  public void destroy() {
    Filter.super.destroy();
  }
}

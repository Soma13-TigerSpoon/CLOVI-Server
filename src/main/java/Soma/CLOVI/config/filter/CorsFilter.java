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

    //여기에 내가 허용하고자 하는 클라이언트의 url을 입력해 줍니다.
    //주의사항 "https://myurl.com/" 처럼 마지막에 '/'를 붙이면 CORS에러가 그대로 발생하게 됩니다.
    for(String url: allowCorsUrl){
      res.setHeader("Access-Control-Allow-Origin", url);
    }
    //req.setHeader("Access-Control-Allow-Origin", "*");  //이렇게 해서 모든 요청에 대해서 허용할 수도 있습니다.
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

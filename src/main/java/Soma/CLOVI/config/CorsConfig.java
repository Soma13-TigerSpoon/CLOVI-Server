package Soma.CLOVI.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig implements WebMvcConfigurer {

  @Override
  public void addCorsMappings(CorsRegistry registry) {
    registry.addMapping("/**")
        .allowedOrigins("https://data.clovi.app")
        .allowedOrigins("http://localhost:2000")
        .allowedOrigins("http://localhost:2001")
        .allowedMethods("GET","POST")
        .maxAge(3000);
  }

}
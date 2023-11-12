package com.system.futurity.configs;

import java.io.IOException;

import org.springframework.stereotype.Component;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class CorsConfig implements Filter {

  @Override
  public void init(FilterConfig filterConfig) throws ServletException {

  }

  @Override
  public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
      throws IOException, ServletException {
    HttpServletResponse response = (HttpServletResponse) servletResponse;
    response.setHeader("Access-Control-Allow-Origin", "http://localhost:4200");
    response.setHeader("Access-Control-Allow-Methods", "POST, GET, PUT, OPTIONS, DELETE, PATCH");
    response.setHeader("Access-Control-Max-Age", "3600");
    response.setHeader("Access-Control-Allow-Headers", "Origin, Content-Type, Accept, X-Custom-Header");
    response.setHeader("Access-Control-Expose-Headers", "Location");
    filterChain.doFilter(servletRequest, servletResponse);
  }

  @Override
  public void destroy() {
  }
}

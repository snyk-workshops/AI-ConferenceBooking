package org.workshop.aiconferencebooking.config;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@Order(1)
public class ResponseFilter implements Filter {

    @Override
    public void doFilter(
        ServletRequest request, ServletResponse response, FilterChain chain
    ) throws IOException, ServletException {
        javax.servlet.http.HttpServletResponse newResponse =
            new javax.servlet.http.HttpServletResponse((HttpServletResponse) response);
        chain.doFilter(request, newResponse);
    }
}

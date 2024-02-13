package com.unevento.api.infra.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class SecurityFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        var token = request.getHeader("Authorization");
        if(token == null || token.isBlank() || !token.startsWith("Bearer ")){
            throw new RuntimeException("Token inválido");
        }
        token = token.replace("Bearer ", "");
        System.out.println("Token: " + token);

        filterChain.doFilter(request, response);
    }


}

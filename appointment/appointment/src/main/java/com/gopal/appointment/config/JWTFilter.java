package com.gopal.appointment.config;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.ArrayList;

@Component
public class JWTFilter  extends OncePerRequestFilter {

    private final JWTUtil jwtUtil;

    public JWTFilter(JWTUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain)
            throws ServletException, IOException {

        String token = null;
        String username = null;


        String header = request.getHeader("Authorization");
        if(header != null && header.startsWith("Bearer ")) {
              token = header.substring(7);
              username =  jwtUtil.getUsername(token);
        }

        if(username != null && SecurityContextHolder.getContext().getAuthentication() == null
                && jwtUtil.validateToken(token)) {

            UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken
                             (username, null, new ArrayList<>());
            SecurityContextHolder.getContext().setAuthentication(authToken);

        }

    }
}

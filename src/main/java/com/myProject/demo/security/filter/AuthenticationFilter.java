//package com.myProject.demo.security.filter;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.myProject.demo.security.properties.JwtRequest;
//import com.myProject.demo.security.util.JwtTokenUtil;
//import jakarta.servlet.FilterChain;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import jakarta.servlet.ServletException;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.AuthenticationException;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
//import org.springframework.stereotype.Component;
//
//import java.io.IOException;
//import java.util.ArrayList;
//import java.util.stream.Collectors;
//
//@Slf4j
//@Component
//public class AuthenticationFilter extends UsernamePasswordAuthenticationFilter {
//
//    @Autowired
//    JwtTokenUtil jwtTokenUtil;
//
//    private final AuthenticationManager authenticationManager;
//
//
//    public AuthenticationFilter(AuthenticationManager authenticationManager) {
//        this.authenticationManager = authenticationManager;
//    }
//
//    @Override
//    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
//        try {
//            JwtRequest creds = new ObjectMapper().readValue(request.getInputStream(), JwtRequest.class);
//            System.out.println("Username: " + creds.getEmail());
//            System.out.println("Password: " + creds.getPassword());
//            return authenticationManager.authenticate(
//                    new UsernamePasswordAuthenticationToken(creds.getEmail(), creds.getPassword(), new ArrayList<>())
//            );
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//    }
//
//    @Override
//    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
//        String token = jwtTokenUtil.createAccessToken(authResult);
//        response.addHeader("Authorization", "Bearer " + token);
//
//        String roles = authResult.getAuthorities().stream()
//                .map(GrantedAuthority::getAuthority)
//                .collect(Collectors.joining(","));
//
//        response.addHeader("Role", roles);
//        System.out.println("Authentication successful for user: " + authResult.getName());
//        System.out.println("Authentication successful with role: " + roles);
//    }
//
//    @Override
//    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException, ServletException {
//        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
//        response.getWriter().write("Authentication Failed: " + failed.getMessage());
//        System.err.println("Authentication failed: " + failed.getMessage());
//    }
//}
//

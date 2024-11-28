//package com.myProject.demo.security.filter;
//
//import com.myProject.demo.security.util.JwtTokenUtil;
//import jakarta.servlet.FilterChain;
//import jakarta.servlet.ServletException;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
//import org.springframework.stereotype.Component;
//
//import java.io.IOException;
//
//@Slf4j
//@Component
//public class AuthorizationFilter extends BasicAuthenticationFilter {
//
//    @Autowired
//    JwtTokenUtil jwtTokenUtil;
//
//    public AuthorizationFilter(AuthenticationManager authenticationManager) {
//        super(authenticationManager);
//    }
//
//    @Override
//    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
//        String header = request.getHeader("Authorization");
//
//        String logoutUrl = "/api/auth/logout";
//        String refreshUrl = "/api/auth/refreshToken";
//        String requestUri = request.getRequestURI();
//
//        if (logoutUrl.equals(requestUri) || refreshUrl.equals(requestUri)) {
//            // È una richiesta di logout, continua senza fare nulla
//            chain.doFilter(request, response);
//            return;
//        }
//
//        if (header == null || !header.startsWith("Bearer ")) {
//            chain.doFilter(request, response);
//            return;
//        }
//
//        chain.doFilter(request, response);
//    }
//
//}
//
//

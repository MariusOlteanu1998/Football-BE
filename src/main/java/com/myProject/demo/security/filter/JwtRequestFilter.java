package com.myProject.demo.security.filter;

import com.myProject.demo.security.service.JwtBlacklistService;
import com.myProject.demo.security.util.JwtTokenUtil;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@Component
public class JwtRequestFilter extends OncePerRequestFilter {

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private JwtBlacklistService jwtBlacklistService;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    private final List<String> excludeUrls = Arrays.asList(
            "/swagger-ui.html", "/swagger-ui/", "/v3/api-docs", "/webjars/" , "/api/auth/"
    );


    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain chain) throws ServletException, IOException {

        String requestUri = request.getRequestURI();
        logger.info("Intercepting request URI: " + requestUri);
        if (excludeUrls.stream().anyMatch(requestUri::startsWith)) {
            chain.doFilter(request, response);
            return;
        }


        String requestTokenHeader = request.getHeader("Authorization");

        String username = null;
        String jwtToken = null;

        if (requestTokenHeader != null && requestTokenHeader.startsWith("Bearer ")) {

            jwtToken = requestTokenHeader.substring(7);

            if (jwtBlacklistService.isTokenBlacklisted(jwtToken)) {
                response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Token is blacklisted");
                return;
            }
                username = jwtTokenUtil.extractUsername(jwtToken);
        } else {
            logger.warn("JWT Token does not begin with Bearer String");
        }

        logger.info("Authentication in security context: " + SecurityContextHolder.getContext().getAuthentication());
        logger.info("JWT Token: " + jwtToken);
        logger.info("Username from token: " + username);

        setAuthentication(request, username, jwtToken);

        chain.doFilter(request, response);
    }

    public void setAuthentication(HttpServletRequest request, String username, String jwtToken) {
        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            UserDetails userDetails = this.userDetailsService.loadUserByUsername(username);

            if (jwtTokenUtil.validateToken(jwtToken, userDetails.getUsername())) {

                List<SimpleGrantedAuthority> authorities = userDetails.getAuthorities()
                        .stream()
                        .map(authority -> new SimpleGrantedAuthority(authority.getAuthority()))
                        .toList();

                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
                        userDetails, null, authorities);
                usernamePasswordAuthenticationToken
                        .setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
            }
        }
    }
}

package com.myProject.demo.security.controller;

import com.myProject.demo.security.properties.JwtRefreshRequest;
import com.myProject.demo.security.properties.JwtRequest;
import com.myProject.demo.security.properties.JwtResponse;
import com.myProject.demo.security.service.JwtBlacklistService;
import com.myProject.demo.security.util.JwtTokenUtil;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final JwtTokenUtil jwtTokenUtil;
    private final JwtBlacklistService jwtBlacklistService;


    @PostMapping("/login")
    public ResponseEntity<JwtResponse> createAuthenticationToken(@RequestBody JwtRequest authenticationRequest) {

        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authenticationRequest.getEmail(), authenticationRequest.getPassword())
            );

            SecurityContextHolder.getContext().setAuthentication(authentication);
            String token = jwtTokenUtil.createAccessToken(authentication);

            String role = authentication.getAuthorities().stream()
                    .map(GrantedAuthority::getAuthority)
                    .findFirst()
                    .orElse("");

            return ResponseEntity.ok()
                    .header("Authorization", token)
                    .body(JwtResponse.builder()
                    		.username(jwtTokenUtil.extractUsername(token))
                    		.role(role)
                    		.expireAt(jwtTokenUtil.getExpirationDateFromToken(token))
                    		.build());
        } catch (AuthenticationException e) {
            throw new RuntimeException(e);
        }
    }

    @PostMapping("/refreshToken")
    public ResponseEntity<JwtResponse> refreshToken(@RequestBody JwtRefreshRequest auth) throws Exception {
        try {
            String newToken = jwtTokenUtil.createRefreshToken(auth);

            return ResponseEntity.ok()
                    .header("Authorization", newToken)
                    .body(JwtResponse.builder()
                            .username(jwtTokenUtil.extractUsername(newToken))
                            .role(auth.getRole())
                            .expireAt(jwtTokenUtil.getExpirationDateFromToken(newToken))
                            .build());
        } catch (Exception e) {
            throw new Exception("ERROR_REFRESHING_TOKEN", e);
        }
    }

    @PostMapping("/logout")
    public ResponseEntity<String> logout(HttpServletRequest request) {
        String authHeader = request.getHeader("Authorization");
        if (authHeader != null) {
            String jwt = authHeader.substring(7);
            jwtBlacklistService.blacklistToken(jwt);
        }
        SecurityContextHolder.clearContext();
        return ResponseEntity.ok("Logout effettuato con successo");
    }
}


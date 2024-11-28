//package com.myProject.demo.security.util;
//
//import com.myProject.demo.security.properties.JwtProperties;
//import com.myProject.demo.security.properties.JwtRefreshRequest;
//import io.jsonwebtoken.Claims;
//import io.jsonwebtoken.Jwts;
//import io.jsonwebtoken.security.Keys;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.boot.context.properties.EnableConfigurationProperties;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.stereotype.Component;
//
//import javax.crypto.SecretKey;
//import java.util.Base64;
//import java.util.Collection;
//import java.util.Date;
//import java.util.List;
//import java.util.function.Function;
//import java.util.stream.Collectors;
//
//@Component
//@EnableConfigurationProperties(JwtProperties.class)
//public class JwtTokenUtil {
//
//     @Value("${sicurezza.secret}")
//     private String  getSecretjwt;
//
//
//     @Value("${sicurezza.jwtExpirationMs}")
//     private long getJwtExpirationMs;
//
//    public String createAccessToken(Authentication authentication) {
//        CustomUserDetails userPrincipal = (CustomUserDetails) authentication.getPrincipal();
//
//        return Jwts.builder()
//                .setSubject(userPrincipal.getUsername()) // Usa setSubject() al posto di subject()
//                .claim("role", authentication.getAuthorities().stream()
//                        .map(GrantedAuthority::getAuthority)
//                        .findFirst()
//                        .orElse(""))
//                .setIssuedAt(new Date())
//                .setExpiration(new Date((new Date()).getTime() + getJwtExpirationMs))
//                .signWith(this.generateSecretKey())
//                .compact();
//    }
//
//        public String createRefreshToken(JwtRefreshRequest auth) {
//        return Jwts.builder()
//                .setSubject((auth.getUsername()))
//                .claim("role", auth.getRole())
//                .setIssuedAt(new Date())
//                .setExpiration(new Date((new Date()).getTime() + getJwtExpirationMs))
//                .signWith(this.generateSecretKey())
//                .compact();
//    }
//
//    public Boolean validateToken(String token, String username) {
//        final String tokenUsername = getUsernameFromToken(token);
//        return (tokenUsername.equals(username) && !isTokenExpired(token));
//    }
//
//
//    public String getRoleFromToken(String token) {
//        Claims claims = getClaimsFromToken(token);
//        Object roleObject = claims.get("role");
//
//        if (roleObject instanceof String) {
//            return (String) roleObject;
//        }
//
//        return null;
//    }
//
//    public String getUsernameFromToken(String token) {
//
//        return getClaimFromToken(token, Claims::getSubject);
//    }
//
//    public Date getExpirationDateFromToken(String token) {
//
//        return getClaimFromToken(token, Claims::getExpiration);
//    }
//
//    public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
//        final Claims claims = getAllClaimsFromToken(token);
//        return claimsResolver.apply(claims);
//    }
//
//    private Claims getAllClaimsFromToken(String token) {
//        return Jwts.parserBuilder()  // Usa parserBuilder() al posto di parser()
//                .setSigningKey(this.generateSecretKey())
//                .build()  // Questo metodo esiste in parserBuilder()
//                .parseClaimsJws(token)
//                .getBody();
//    }
//
//
//    public Boolean isTokenExpired(String token) {
//        final Date expiration = getExpirationDateFromToken(token);
//        return expiration.before(new Date());
//    }
//
//    public Collection<? extends GrantedAuthority> extractAuthorities(String token) {
//        Claims claims = Jwts.parserBuilder()
//                .setSigningKey(generateSecretKey())
//                .build()
//                .parseClaimsJws(token)
//                .getBody();
//
//        List<String> roles = claims.get("roles", List.class);
//
//        return roles.stream()
//                .map(SimpleGrantedAuthority::new)
//                .collect(Collectors.toList());
//    }
//
//
//    public String extractUsername(String token) {
//
//        return getClaimsFromToken(token).getSubject();
//    }
//
//    public Claims getClaimsFromToken(String token) {
//        try {
//            if (token.startsWith("Bearer ")) {
//                token = token.substring(7);
//            }
//
//            // Parsing e verifica del token
//            return Jwts.parserBuilder()
//                    .setSigningKey(generateSecretKey())
//                    .build()
//                    .parseClaimsJws(token)
//                    .getBody();
//        } catch (Exception e) {
//            e.printStackTrace(); // Stampa lo stack trace per un'analisi più dettagliata
//            throw new RuntimeException("Error processing JWT", e);
//        }
//    }
//
//    public SecretKey generateSecretKey() {
//
//        byte[] decodedKey = Base64.getDecoder().decode(getSecretjwt);
//        return Keys.hmacShaKeyFor(decodedKey);
//    }
//
//}

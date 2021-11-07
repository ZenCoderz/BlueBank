package com.zencoderz.bluebank.auth.filter;

import com.zencoderz.bluebank.auth.AuthExceptionHandler;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.*;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;

@Component
public class AppAuthorizationFilter extends OncePerRequestFilter {

    private final AuthExceptionHandler authExceptionHandler = new AuthExceptionHandler();

    private final Set<String> nonProhibitedURI =  Set.of(
            "/login",
            "/auth/refreshToken",
            "/auth/register",
            "/h2-console",
            "/swagger-ui/",
            "/swagger-ui/**"
    );

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        if (nonProhibitedURI.contains(request.getServletPath()) ||
                request.getServletPath().contains("swagger-ui") ||
                request.getServletPath().contains("swagger-resources") ||
                request.getServletPath().contains("api-docs")) {
            filterChain.doFilter(request, response);
            return;
        }
        String authorizationHeader = request.getHeader(AUTHORIZATION);
        if (authorizationHeader == null) {
            response.sendError(HttpServletResponse.SC_FORBIDDEN, "Auth Token not Found");
            return;
        }
        if (authorizationHeader.startsWith("Bearer ")) {
            try{
                String token = authorizationHeader.substring("Bearer ".length());
                Algorithm algorithm = Algorithm.HMAC256("secret".getBytes());
                JWTVerifier verifier = JWT.require(algorithm).build();
                DecodedJWT decodedJWT = verifier.verify(token);
                String username = decodedJWT.getSubject();
                String[] roles = decodedJWT.getClaim("authority").asArray(String.class);
                Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
                Arrays.stream(roles).forEach(role -> authorities.add(new SimpleGrantedAuthority(role)));
                UsernamePasswordAuthenticationToken authToken =
                        new UsernamePasswordAuthenticationToken(username, null, authorities);
                SecurityContextHolder.getContext().setAuthentication(authToken);
            } catch(Exception exception) {
                authExceptionHandler.addUnauthorizedToResponse(exception, response);
            }
        }
        filterChain.doFilter(request, response);
    }
}

package com.zencoderz.bluebank.auth.filter;

import com.auth0.jwt.JWT;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.zencoderz.bluebank.auth.AuthExceptionHandler;
import com.zencoderz.bluebank.auth.dto.LoginFormDTO;
import com.zencoderz.bluebank.auth.util.AuthUtil;
import org.apache.commons.io.IOUtils;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class AppAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private final AuthenticationManager authenticationManager;

    private final AuthUtil authUtil;

    private final AuthExceptionHandler authExceptionHandler = new AuthExceptionHandler();

    public AppAuthenticationFilter(AuthenticationManager authenticationManager, AuthUtil authUtil) {
        this.authenticationManager = authenticationManager;
        this.authUtil = authUtil;
    }

    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) {
        String username = "";
        String password = "";
        if (request.getContentType().equals(MediaType.APPLICATION_JSON_VALUE) || request.getContentType().equals("loginRequestBody")) {
            try {
                LoginFormDTO form = this.extractJsonUser(request);
                username = form.getUsername();
                password = form.getPassword();
            } catch(Exception exception) {
                authExceptionHandler.addInputErrorToResponse(exception, response);
            }
        } else {
            username = request.getParameter("username");
            password = request.getParameter("password");
        }

        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(username, password);
        return authenticationManager.authenticate(authToken);
    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) {
        authExceptionHandler.addUnauthorizedToResponse(failed.getMessage(), response);
    }

    @Override
    public void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException {
        User user = (User) authResult.getPrincipal();
        String accessToken = JWT.create()
                .withSubject(user.getUsername())
                .withExpiresAt(authUtil.jwtTimeToLive())
                .withIssuer(request.getRequestURL().toString())
                .withClaim("authority", user.getAuthorities()
                        .stream()
                        .map(GrantedAuthority::getAuthority)
                        .collect(Collectors.toList()))
                .sign(authUtil.algorithm());
        String refreshToken = JWT.create()
                .withSubject(user.getUsername())
                .withExpiresAt(authUtil.jwtTimeToLive() )
                .withIssuer(request.getRequestURL().toString())
                .sign(authUtil.algorithm());
        Map<String, String> tokens = new HashMap<>();
        tokens.put("access_token", accessToken);
        tokens.put("refresh_token", refreshToken);
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        new ObjectMapper().writeValue(response.getOutputStream(), tokens);
    }

    public LoginFormDTO extractJsonUser(HttpServletRequest request) throws Exception {
        String body = IOUtils.toString(request.getReader());
        LoginFormDTO form = new Gson().fromJson(body, LoginFormDTO.class);
        if (form.getUsername() != null && form.getPassword() != null) {
            return form;
        }
        throw new Exception("Username and Password couldn't be parsed");
    }
}

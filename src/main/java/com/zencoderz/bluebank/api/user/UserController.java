package com.zencoderz.bluebank.api.user;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.zencoderz.bluebank.api.user.dto.UserDTO;
import com.zencoderz.bluebank.auth.AuthExceptionHandler;
import com.zencoderz.bluebank.auth.util.AuthUtil;
import com.zencoderz.bluebank.api.user.dto.UserAuthorityDTO;
import com.zencoderz.bluebank.api.user.dto.UserFormCreateDTO;

import lombok.AllArgsConstructor;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import java.net.URI;
import java.util.*;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;

@RestController
@RequestMapping("/auth")
@AllArgsConstructor
public class UserController {

    private UserService userService;
    private AuthUtil authUtil;
    private AuthExceptionHandler authExceptionHandler;

    @PostMapping("/register")
    public ResponseEntity<UserDTO> registerUser(@RequestBody @Valid UserFormCreateDTO userFormCreateDTO) {
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/login").toUriString());
        return ResponseEntity.created(uri).body(this.userService.saveUser(userFormCreateDTO));
    }

    @GetMapping()
    public ResponseEntity<Set<UserDTO>> getUsers() {
        Set<UserDTO> userDTOS = this.userService.getUsersDTO();
        return ResponseEntity.ok(userDTOS);
    }

    @GetMapping("/currentUser")
    public ResponseEntity<UserDTO> getUser(Authentication authentication) {
        UserDTO userDTO = this.userService.getUserDTO(authentication.getName());
        return ResponseEntity.ok().body(userDTO);
    }

    @PatchMapping("/user/exchangeAuthority")
    @PreAuthorize("hasAuthority('admin')")
    public ResponseEntity<?> exchangeUserAuthority(@RequestBody UserAuthorityDTO dto) throws Exception {
        this.userService.changeUserAuthority(dto.getUsername(), dto.getAuthority());
        return ResponseEntity.ok().build();
    }

    @GetMapping("/refreshToken")
    public void refreshToken(HttpServletRequest request, HttpServletResponse response) {
        String authorizationHeader = request.getHeader(AUTHORIZATION);
        if(authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            try{
                String refreshToken = authorizationHeader.substring("Bearer ".length());
                Algorithm algorithm = Algorithm.HMAC256("secret".getBytes());
                JWTVerifier verifier = JWT.require(algorithm).build();
                DecodedJWT decodedJWT = verifier.verify(refreshToken);
                String username = decodedJWT.getSubject();
                User user = userService.getUser(username);
                String accessToken = JWT.create()
                        .withSubject(user.getUsername())
                        .withExpiresAt(authUtil.jwtTimeToLive())
                        .withIssuer(request.getRequestURL().toString())
                        .withClaim("authority", List.of(user.getAuthority().toString()))
                        .sign(authUtil.algorithm());
                Map<String, String> tokens = new HashMap<>();
                tokens.put("access_token", accessToken);
                tokens.put("refresh_token", refreshToken);
                response.setContentType(MediaType.APPLICATION_JSON_VALUE);
                new ObjectMapper().writeValue(response.getOutputStream(), tokens);
            } catch(Exception exception) {
                authExceptionHandler.addUnauthorizedToResponse(exception, response);
            }
        } else {
            throw new RuntimeException("Refresh Token is Missing");
        }
    }

}

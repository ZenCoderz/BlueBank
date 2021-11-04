package com.zencoderz.bluebank.auth;

import com.google.gson.Gson;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@Component
public class AuthExceptionHandler {

    public final Gson gson = new Gson();

    public void addInputErrorToResponse(Exception e, HttpServletResponse response) {
        this.addError(e.getMessage(), response, HttpServletResponse.SC_BAD_REQUEST);
    }

    public void addUnauthorizedToResponse(Exception exception, HttpServletResponse response) {
        this.addError(exception.getMessage(), response, HttpServletResponse.SC_UNAUTHORIZED);
    }

    public void addUnauthorizedToResponse(String exception, HttpServletResponse response) {
        this.addError(exception, response, HttpServletResponse.SC_UNAUTHORIZED);
    }

    private void addError(String error, HttpServletResponse response, Integer code) {
        try{
            response.setContentType(MediaType.APPLICATION_JSON_VALUE);
            response.setStatus(code);
            PrintWriter out = response.getWriter();
            AuthResponse authResponse = new AuthResponse(error);
            out.print(gson.toJson(authResponse));
            out.flush();
        } catch (IOException exception) {
            response.setHeader("error", "Internal Error");
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }

    }

    @AllArgsConstructor
    static class AuthResponse {
        private String message;
    }

}

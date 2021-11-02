package com.zencoderz.bluebank.exception;

public class AuthorizationException extends RuntimeException {

    public AuthorizationException(String errorMessage) {
        super(errorMessage);
    }

}

package com.zencoderz.bluebank.exception;


public class ContentNotFoundException extends RuntimeException {

    public ContentNotFoundException(String errorMessage) {
        super(errorMessage);
    }

}


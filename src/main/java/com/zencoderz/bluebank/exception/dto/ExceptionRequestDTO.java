package com.zencoderz.bluebank.exception.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import lombok.Data;

import java.util.List;

@Data
public class ExceptionRequestDTO {

    private String message;

    @JsonInclude(Include.NON_NULL)
    private List<String> errors;

    public ExceptionRequestDTO(Exception e) {
        this.message = e.getMessage();
    }

    public ExceptionRequestDTO(String message, List<String> errors) {
        this.message = message;
        this.errors = errors;
    }

}

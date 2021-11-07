package com.zencoderz.bluebank.exception;

import com.zencoderz.bluebank.exception.dto.ExceptionRequestDTO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
public class BlueBankRunTimeExceptionHandler extends ResponseEntityExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(BlueBankRunTimeExceptionHandler.class);

    @ExceptionHandler(value = { InvalidInputException.class })
    public ResponseEntity<Object> handleInvalidInputException(InvalidInputException exception) {
        logger.error("Invalid Input Exception: " + exception.getMessage());
        return new ResponseEntity<>(new ExceptionRequestDTO(exception), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = { ContentNotFoundException.class })
    public ResponseEntity<Object> handleContentNotFoundException(ContentNotFoundException exception) {
        logger.error(exception.getMessage());
        return new ResponseEntity<>(new ExceptionRequestDTO(exception), HttpStatus.BAD_REQUEST);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        List<String> errors = ex.getAllErrors().stream().map(ObjectError::getDefaultMessage).collect(Collectors.toList());
        return new ResponseEntity<>(new ExceptionRequestDTO("Invalid Parameters: ", errors), HttpStatus.BAD_REQUEST);
    }

}

package com.example.clinica.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptions {

    @ExceptionHandler({ResourceNotFoundException.class})
    public ResponseEntity<String> tratarErrorNoFound(ResourceNotFoundException ex) {
        return  ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage()+ " --GLOBAL");
    }

    @ExceptionHandler({BadRequestException.class})
    public ResponseEntity<String> tratarErrorBadRequest(BadRequestException ex) {
        return  ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage()+ " --GLOBAL");
    }
}

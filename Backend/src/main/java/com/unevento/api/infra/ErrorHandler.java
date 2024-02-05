package com.unevento.api.infra;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ErrorHandler {


    @ExceptionHandler(EntityNotFoundException.class)

    public ResponseEntity ErrorHandler404 (Exception ex){
        return ResponseEntity.notFound().build();
    }



    @ExceptionHandler(MethodArgumentNotValidException.class)

    public ResponseEntity ErrorHandler400 (MethodArgumentNotValidException e){
        var errores = e.getFieldErrors().stream().map(ErrorResponse::new).toList();
        return ResponseEntity.badRequest().body(errores);
    }

    private record ErrorResponse(String camp, String error) {

        public ErrorResponse(FieldError error) {
            this(error.getField(), error.getDefaultMessage());
        }
    }
}

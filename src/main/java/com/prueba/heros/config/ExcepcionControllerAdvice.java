package com.prueba.heros.config;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.prueba.heros.model.ErrorResponse;

@ControllerAdvice
public class ExcepcionControllerAdvice {

    @ExceptionHandler(HerosException.class)
    public ResponseEntity<ErrorResponse> manejarHerosException(HerosException ex) {
        ErrorResponse errorResponse = new ErrorResponse(ex.getHttpStatus().value(), ex.getMensaje());
        return new ResponseEntity<>(errorResponse, ex.getHttpStatus());
    }
}
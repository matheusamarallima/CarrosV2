package com.example.Carros.api.exception;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class ExceptionConfig extends ResponseEntityExceptionHandler {
    //Aqui declara qual erro sera tratado
    @ExceptionHandler({
            EmptyResultDataAccessException.class
    })
    //aqui como ele sera tratado, qual mensagem mostrará
    public ResponseEntity errorNotFound(Exception exception){
        return ResponseEntity.notFound().build();
    }
    //Aqui declara qual erro sera tratado
    @ExceptionHandler({
            IllegalArgumentException.class
    })
    //aqui como ele sera tratado, qual mensagem mostrará
    public ResponseEntity errorBadRequest(Exception exception){
        return ResponseEntity.badRequest().build();
    }

}

package com.example.hospitalalert.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(AlertException.class)
    public ResponseEntity<String> handleAlertException(AlertException ex) {
        // Customize a mensagem e o status de acordo com sua lógica
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }
    
    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleGeneralException(Exception ex) {
        // Captura quaisquer exceções não tratadas
        return new ResponseEntity<>("Ocorreu um erro: " + ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}

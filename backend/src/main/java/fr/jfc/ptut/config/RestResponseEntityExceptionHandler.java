package fr.jfc.ptut.config;

import java.util.NoSuchElementException;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestResponseEntityExceptionHandler
        extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = { // Quelles sont les exceptions qu'ont veut traiter
        IllegalArgumentException.class, IllegalStateException.class, NoSuchElementException.class 
    })
    protected ResponseEntity<Object> handleConflict(RuntimeException ex, WebRequest request) {
        String bodyOfResponse = ex.getMessage(); // Quel message doit être envoyé en réponse
        HttpStatus code = HttpStatus.CONFLICT; // Quel code HTTP doit être envoyé en réponse
        return handleExceptionInternal(ex, bodyOfResponse, new HttpHeaders(), code, request);
    }
}
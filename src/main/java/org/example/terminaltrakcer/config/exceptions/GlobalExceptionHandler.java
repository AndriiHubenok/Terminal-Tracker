package org.example.terminaltrakcer.config.exceptions;

import org.example.terminaltrakcer.dto.error.ErrorResult;
import org.example.terminaltrakcer.dto.error.NoTerminalsNearbyException;
import org.example.terminaltrakcer.dto.error.TerminalNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(TerminalNotFoundException.class)
    public ResponseEntity<ErrorResult> handleTerminalNotFound(TerminalNotFoundException ex){
        ErrorResult result = new ErrorResult(
                "NOT_FOUND",
                ex.getMessage(),
                LocalDateTime.now()
        );
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(result);
    }

    @ExceptionHandler(NoTerminalsNearbyException.class)
    public ResponseEntity<ErrorResult> handleNoTerminalsNearby(NoTerminalsNearbyException ex){
        ErrorResult result = new ErrorResult(
                "NOT_FOUND",
                ex.getMessage(),
                LocalDateTime.now()
        );
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(result);
    }
}

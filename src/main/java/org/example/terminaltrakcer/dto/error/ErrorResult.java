package org.example.terminaltrakcer.dto.error;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class ErrorResult {
    String errorCode;
    String message;
    LocalDateTime timestamp;
}

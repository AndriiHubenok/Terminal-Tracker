package org.example.terminaltrakcer.dto.error;

public class TerminalNotFoundException extends RuntimeException{
    public TerminalNotFoundException(String id) {
        super(String.format("Terminal with id %s not found", id));
    }
}

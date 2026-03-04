package org.example.terminaltrakcer.dto.error;

public class NoTerminalsNearbyException extends RuntimeException{
    public NoTerminalsNearbyException(Double radius) {
        super(String.format("There is no terminals in radius %.2f", radius));
    }
}
